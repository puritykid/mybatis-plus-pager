package com.lunz.interfaces;

import java.util.List;
import java.util.Map;

import com.lunz.query.QueryGroup;
import com.lunz.query.Rule;

/**
 * 查询组解析器
 * @author haha
 *
 */
public interface IQueryGroupParser {
	
	/**
	 * @param type 实体类型
	 * @param rules 规则集合
	 * @param groups 子查询组
	 * @param parameters 参数
	 * @return
	 * @throws Exception 
	 */
	public String Parse(Class<?> type, List<Rule> rules, List<QueryGroup> groups,Map<String, Object> parameters) throws Exception;
}
