package com.lunz.rule;

import java.util.Map;

import com.lunz.query.parse.RuleParser;

public class NotIsNullRuleParser extends RuleParser{

	public NotIsNullRuleParser() {
		super("%s IS NOT NULL");
	}
	@Override
	public String Parse(String field, Object dataType, String[] data, Map<String, Object> parameters) {
		return String.format(Expression, field);
	}
}
