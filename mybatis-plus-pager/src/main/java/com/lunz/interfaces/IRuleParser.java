package com.lunz.interfaces;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lunz.query.Rule;

/**
 * 数据转换规则
 * 
 * @author haha
 *
 */
public interface IRuleParser {

	public <T> QueryWrapper<T> Parse(String op, Rule rule, QueryWrapper<T> queryWrapper);
}
