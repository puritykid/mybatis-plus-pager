package com.lunz.rule;

import com.lunz.query.parse.RuleParser;

public class LessThanRuleParser extends RuleParser{

	public LessThanRuleParser() {
		super("%s < %s");
	}
}
