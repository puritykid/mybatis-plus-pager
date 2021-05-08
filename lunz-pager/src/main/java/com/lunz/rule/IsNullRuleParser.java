package com.lunz.rule;

import java.util.Map;

import com.lunz.query.parse.RuleParser;

public class IsNullRuleParser extends RuleParser{

	public IsNullRuleParser() {
		super("%s IS NULL");
	}
	
	@Override
	public String Parse(String field, Object dataType, String[] data, Map<String, Object> parameters) {
		return String.format(Expression, field);
	}
}
