package com.lunz.query;

import java.util.HashMap;
import java.util.Map;

import com.lunz.interfaces.IQueryGroupParser;
import com.lunz.query.parse.QueryGroupSqlResult;
import com.lunz.query.parse.QueryParsers;

public class QueryExtensions {
	
    public static QueryGroupSqlResult toSql(QueryGroup queryGroup,Class<?> clazz) throws Exception
    {
        IQueryGroupParser parser = QueryParsers.getInstence().GetQueryGroupParser(queryGroup.getOp());
        Map<String, Object> parameters = new HashMap<String, Object>();
        String sql = parser.Parse(clazz, queryGroup.getRules(), queryGroup.getGroups(), parameters);

        return parameters.size() > 0 ?
            new QueryGroupSqlResult(sql, parameters) :
            new QueryGroupSqlResult(null, new HashMap<String, Object>());
    }

}
