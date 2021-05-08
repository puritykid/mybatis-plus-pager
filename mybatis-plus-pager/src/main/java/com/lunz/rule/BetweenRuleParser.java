package com.lunz.rule;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lunz.interfaces.IRuleParser;
import com.lunz.query.Rule;
import com.lunz.util.ConvertFieldUtil;

public class BetweenRuleParser implements IRuleParser {

	@Override
	public <T> QueryWrapper<T> Parse(String op, Rule rule, QueryWrapper<T> queryWrapper) {
		if ("and".equals(op)) {
			queryWrapper = queryWrapper.between(ConvertFieldUtil.camelToUnderline(rule.getField()),
					rule.getDatas().get(0), rule.getDatas().get(1));
		}
		if ("or".equals(op)) {
			queryWrapper = queryWrapper.or().between(ConvertFieldUtil.camelToUnderline(rule.getField()),
					rule.getDatas().get(0), rule.getDatas().get(1));
		}
		return queryWrapper;
	}

}
