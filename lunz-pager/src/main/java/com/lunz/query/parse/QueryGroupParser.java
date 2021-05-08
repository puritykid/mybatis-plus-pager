package com.lunz.query.parse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.lunz.interfaces.IQueryGroupParser;
import com.lunz.interfaces.IRuleParser;
import com.lunz.query.DataTypeParser;
import com.lunz.query.QueryGroup;
import com.lunz.query.Rule;
import com.lunz.query.StringUtil;

public class QueryGroupParser implements IQueryGroupParser {

	private String op;
	public QueryGroupParser(String op) {
		this.op = op;
	}

	@Override
	public String Parse(Class<?> type, List<Rule> rules, List<QueryGroup> groups, Map<String, Object> parameters) throws Exception {
		 List<String> expressions = new ArrayList<String>();
		 expressions.add(ParseRules(type, rules, parameters));
		 expressions.add(ParseGroups(type, groups, parameters));
         List<String> collect = expressions.stream().filter(x->!StringUtil.IsNullOrWhiteSpace(x)).filter(x-> x.contains("Param")).collect(Collectors.toList()); // 筛选出需要的字段
         
//         expressions = expressions.Where(x => !string.IsNullOrWhiteSpace(x)).Select(x => $"({x})").ToList();
         
//         expressions = expressions.Where(x => !obj.IsNullOrWhiteSpace(x)).Select(obj => $"({x})").ToList();

         return String.join(op, collect);
	}

	private String ParseRules(Class<?> type, List<Rule> rules, Map<String, Object> parameters) throws Exception {
		List<String> sqls = new ArrayList<String>();

        for(Rule rule : rules) {
            List<String> values = new ArrayList<String>();
            if (rule.getDatas().size() > 0)
            {
            	values.addAll(rule.getDatas());
            } 
            else
            {
                values.add(rule.getData());
            }

            IRuleParser parser = QueryParsers.getInstence().GetRuleParser(rule.getOp());

            Map<String, Object> propertyMap = DataTypeParser.Parse(type, rule.getField());

            String propertyName = (String) propertyMap.get("propertyName");
            Object propertyType =  propertyMap.get("propertyType");
            String expression = parser.Parse(propertyName, propertyType, values.toArray(new String[values.size()]), parameters);
            if (expression != null && !StringUtil.IsNullOrWhiteSpace(expression))
            {
                sqls.add(expression);
            }
        }

        return String.join(op, sqls);
		
	}
	
	private String ParseGroups(Class<?> type, List<QueryGroup> groups, Map<String, Object> parameters) throws Exception {
		
		
		List<QueryGroup> filterGroup = new ArrayList<QueryGroup>();
		if (groups !=null && groups.size() > 0) {
			
			for (QueryGroup queryGroup : groups) {
				if (!StringUtil.IsNullOrWhiteSpace(queryGroup.getOp())) {
					filterGroup.add(queryGroup);
				}
			}
		}
		
		List<String> sqls = new ArrayList<String>();

        for (QueryGroup group : filterGroup)
        {
            IQueryGroupParser parser = QueryParsers.getInstence().GetQueryGroupParser(group.getOp());

            String expression = parser.Parse(type, group.getRules(), group.getGroups(), parameters);
            sqls.add(expression);
        }

        return String.join(op, sqls);
	}


	
	

}
