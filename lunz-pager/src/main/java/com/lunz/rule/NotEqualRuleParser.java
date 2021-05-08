package com.lunz.rule;

import com.lunz.query.parse.RuleParser;

public class NotEqualRuleParser extends RuleParser{

	public NotEqualRuleParser() {
		super("%s <> %s");
	}
}
