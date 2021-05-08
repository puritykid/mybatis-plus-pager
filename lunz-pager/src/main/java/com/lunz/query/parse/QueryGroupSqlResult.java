package com.lunz.query.parse;

import java.util.HashMap;
import java.util.Map;

import com.lunz.query.StringUtil;

public class QueryGroupSqlResult {
	
	private String sql;
	private Object parameters;
	
	public QueryGroupSqlResult(String sql, Object parameters) {
		this.sql = sql;
		this.parameters = parameters;
	}
	
	public String getSql() {
		return StringUtil.IsNullOrWhiteSpace(sql) ? "" : " where " +   sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	@SuppressWarnings("unchecked")
	public Map<String,Object> getParameters() {
		return (Map<String,Object>)parameters;
	}
	public void setParameters(Object parameters) {
		this.parameters = parameters;
	}

	@SuppressWarnings("unchecked")
	public Map<String,Object> ToTuble()
     {
		Map<String, Object> map = new HashMap<String, Object>();
         if (StringUtil.IsNullOrWhiteSpace(sql))
             return null;
         else {
        	 map.put("sql", this.getSql());
        	 map.putAll(this.getParameters());
             return map;
         }
    }
}
