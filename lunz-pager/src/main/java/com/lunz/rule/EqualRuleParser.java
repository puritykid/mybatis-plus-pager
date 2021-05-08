package com.lunz.rule;

import com.lunz.query.parse.RuleParser;

public class EqualRuleParser extends RuleParser{

	public EqualRuleParser() {
		super("%s = %s");
	}
}
