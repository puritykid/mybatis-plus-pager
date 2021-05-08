package com.lunz.rule;

import com.lunz.query.parse.RuleParser;

public class LessOrEqualThanRuleParser extends RuleParser{

	public LessOrEqualThanRuleParser() {
		super("%s <= %s");
	}
}
