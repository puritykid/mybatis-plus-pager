package com.lunz.rule;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lunz.interfaces.IRuleParser;
import com.lunz.query.Rule;
import com.lunz.util.ConvertFieldUtil;

public class IsNullRuleParser implements IRuleParser{

	@Override
	public <T> QueryWrapper<T> Parse(String op, Rule rule, QueryWrapper<T> queryWrapper) {
		if ("and".equals(op)) {
			queryWrapper = queryWrapper.isNull(ConvertFieldUtil.camelToUnderline(rule.getField()));
		}
		if ("or".equals(op)) {
			queryWrapper = queryWrapper.or().isNull(ConvertFieldUtil.camelToUnderline(rule.getField()));
		}
		return queryWrapper;
	}

}
