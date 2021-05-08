package com.lunz.rule;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lunz.interfaces.IRuleParser;
import com.lunz.query.Rule;
import com.lunz.util.ConvertFieldUtil;

public class LessOrEqualThanRuleParser implements IRuleParser{

	@Override
	public <T> QueryWrapper<T> Parse(String op, Rule rule, QueryWrapper<T> queryWrapper) {
		if ("and".equals(op)) {
			queryWrapper = queryWrapper.le(ConvertFieldUtil.camelToUnderline(rule.getField()), rule.getData());
		}
		if ("or".equals(op)) {
			queryWrapper = queryWrapper.or().le(ConvertFieldUtil.camelToUnderline(rule.getField()), rule.getData());
		}
		return queryWrapper;
	}

}
