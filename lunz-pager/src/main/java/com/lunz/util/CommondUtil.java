
package com.lunz.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lunz.query.Command;
import com.lunz.query.QueryExtensions;
import com.lunz.query.parse.QueryGroupSqlResult;
import com.lunz.sort.PagingSort;

/**
 * 页面排序
 * @author haha
 *
 */

public class CommondUtil {

	public static Map<String, Object> convertObjectToPage(Command command,Class<?> clazz){
		
		Map<String, Object> map = new HashMap<String, Object>();
		if (command == null) {
			return map;
		}
		QueryGroupSqlResult sqlResult;
		try {
			if (command.getFilter().getRules().size() > 0) {
				sqlResult = QueryExtensions.toSql(command.getFilter(), clazz);
				map.put("sql", sqlResult.getSql() != null ? ConvertFieldUtil.camelToUnderline(sqlResult.getSql()) : ""); 
				map.putAll(sqlResult.getParameters());
			}
			
			if (command.getSort().size() > 0) {
				String sortSql = getSortSql(command.getSort());
				map.put("sortSql", sortSql != null ? ConvertFieldUtil.camelToUnderline(sortSql) : ""); 
			}
			
			if (command.getPageIndex() != null) {
				map.put("pageIndex",command.getPageIndex());
			}
			
			if (command.getPageSize() != null) {
				map.put("pageSize",command.getPageSize());
			}
			
		} catch (Exception e) {
			return map;
		}
		return map;
	}
	
public static Map<String, Object> convertObjectToCount(Command command,Class<?> clazz){
		
		Map<String, Object> map = new HashMap<String, Object>();
		if (command == null) {
			return map;
		}
		QueryGroupSqlResult sqlResult;
		try {
			
			if (command.getFilter().getRules().size() > 0) {
				sqlResult = QueryExtensions.toSql(command.getFilter(), clazz);
				map.put("sql", sqlResult.getSql() != null ? ConvertFieldUtil.camelToUnderline(sqlResult.getSql()) : ""); 
				map.putAll(sqlResult.getParameters());
			}
			
			map.put("countSql", "1");  // 默认标识
		} catch (Exception e) {
			return map;
		}
		return map;
	}
	
	// 拼接排序sql
	private static String getSortSql(List<PagingSort> sortList) {
		StringBuffer sb = new StringBuffer();
		if (sortList !=null && sortList.size()>0) {
			sb.append(" order by ");
			for (int i = 0; i < sortList.size(); i++) {
				PagingSort pagingSort = sortList.get(i);
				sb.append(pagingSort.getSortOrder());
				if (i < (sortList.size()-1)) {
					sb.append(",");
				}
			}
		}
		return sb.toString();
	}
	
}
