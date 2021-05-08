package com.lunz.rule;

import com.lunz.query.parse.RuleParser;

public class GreaterOrEqualThanRuleParser extends RuleParser{

	public GreaterOrEqualThanRuleParser() {
		super("%s >= %s");
	}
}
