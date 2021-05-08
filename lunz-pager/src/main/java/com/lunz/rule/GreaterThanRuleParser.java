package com.lunz.rule;

import com.lunz.query.parse.RuleParser;

public class GreaterThanRuleParser extends RuleParser{

	public GreaterThanRuleParser() {
		super("%s > %s");
	}
}
