package com.lunz.intercept;

import java.sql.Connection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import com.lunz.query.StringUtil;

/**
 * 拦截sql语法构建处理过程
 */
@Intercepts({
		@Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class, Integer.class }) })
public class QueryIntercept implements Interceptor {

	@SuppressWarnings("unchecked")
	@Override
	public Object intercept(Invocation invocation) throws Throwable {

		// 获取StatementHandler，默认是RoutingStatementHandler
		StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
		// 获取statementHandler包装类
		MetaObject MetaObjectHandler = SystemMetaObject.forObject(statementHandler);

		// 分离代理对象链
		while (MetaObjectHandler.hasGetter("h")) {
			Object obj = MetaObjectHandler.getValue("h");
			MetaObjectHandler = SystemMetaObject.forObject(obj);
		}

		while (MetaObjectHandler.hasGetter("target")) {
			Object obj = MetaObjectHandler.getValue("target");
			MetaObjectHandler = SystemMetaObject.forObject(obj);
		}

		// 获取查询接口映射的相关信息
		MappedStatement mappedStatement = (MappedStatement) MetaObjectHandler.getValue("delegate.mappedStatement");
		String operateType = getOperateType(mappedStatement);
		// 拦截一切查询
		if ("select".equals(operateType)) {
			// 获取进行数据库操作时管理参数的handler
			ParameterHandler parameterHandler = (ParameterHandler) MetaObjectHandler
					.getValue("delegate.parameterHandler");
			// 获取请求时的参数
			Map<String, Object> paraObject = (Map<String, Object>) parameterHandler.getParameterObject();
			// 判断是否有分页参数
			if (paraObject != null && paraObject.containsKey("pageIndex")) {

				// 也可以这样获取
				// paraObject = (Map<String, Object>)
				// statementHandler.getBoundSql().getParameterObject();

				// 参数名称和在service中设置到map中的名称一致
				int currPage = (int) paraObject.get("pageIndex");
				int pageSize = (int) paraObject.get("pageSize");
				paraObject.remove("pageIndex");
				paraObject.remove("pageSize");

				if (currPage < 1) {
					currPage = 1;
				}
				if (pageSize < 0) {
					pageSize = 10;
				}

				String sortSql = (String) paraObject.get("sortSql");

				StringBuffer sb = new StringBuffer();
				if (!StringUtil.IsNullOrWhiteSpace(sortSql)) {
					sb.append(sortSql);
				}

				// 拼接分页排序sql
				String limitSql = sb.toString() + " limit " + (currPage - 1) * pageSize + "," + pageSize;
				setSql(MetaObjectHandler, paraObject, limitSql);

			} else if (paraObject != null && paraObject.containsKey("countSql")) {
				// 获取总条数时的sql 剔除order by limit 等参数
				setSql(MetaObjectHandler, paraObject, "");
			}
		}
		// 调用原对象的方法，进入责任链的下一级
		return invocation.proceed();
	}

	// 重新设置sql
	private void setSql(MetaObject MetaObjectHandler, Map<String, Object> paraObject, String limitSql) {
		// 获取xml中写好的单表sql语句（多表创建视图）
		String sql = (String) MetaObjectHandler.getValue("delegate.boundSql.sql");

		// 也可以通过statementHandler直接获取
		// sql = statementHandler.getBoundSql().getSql();

		// 从传入参数中获取拼接好的sql
		String whereSql = (String) paraObject.get("sql");
		paraObject.remove("sql");

		if (!StringUtil.IsNullOrWhiteSpace(whereSql)) {
			for (Entry<String, Object> entry : paraObject.entrySet()) {
				String key = entry.getKey();
				Object value = entry.getValue();
				if (whereSql.contains(key)) {
					// 过滤空值，空值不作为查询条件
					if (value != null && !value.equals("")) {
						// 替换sql中的#{Param0} 参数为实际值，相当于占位符的替换，并赋值参数值为字符串
						whereSql = whereSql.replace("#{" + key + "}", "'" + value.toString() + "'");
					}
				}
				System.out.println(key + "==" + value);
			}
		} else {
			whereSql = "";
		}

		String resetSql = sql + whereSql + limitSql;
		// 将构建完成的分页sql语句赋值个体'delegate.boundSql.sql'，偷天换日
		MetaObjectHandler.setValue("delegate.boundSql.sql", resetSql);
		System.out.println("被拦截的查询sql为：" + resetSql);
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	// 插件中需要定义的属性
	@Override
	public void setProperties(Properties properties) {
		String dbsql = (String) properties.get("dbsql");
		System.out.println(dbsql);

	}

	// 获取执行sql的操作
	private String getOperateType(MappedStatement ms) {
		SqlCommandType commondType = ms.getSqlCommandType();
		if (commondType.compareTo(SqlCommandType.SELECT) == 0) {
			return "select";
		}
		if (commondType.compareTo(SqlCommandType.INSERT) == 0) {
			return "insert";
		}
		if (commondType.compareTo(SqlCommandType.UPDATE) == 0) {
			return "update";
		}
		if (commondType.compareTo(SqlCommandType.DELETE) == 0) {
			return "delete";
		}
		return null;
	}

}
