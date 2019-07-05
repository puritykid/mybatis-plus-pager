package com.lunz.interfaces;

import java.util.function.Function;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lunz.query.QueryGroup;

/**
 * 查询组解析器
 * @author haha
 *
 */
public interface IQueryGroupParser {
	
	public <T> Function<QueryWrapper<T>, QueryWrapper<T>> parseFunc(QueryGroup<T> group) throws Exception;

	public <T> QueryWrapper<T> parseWrapperByFunc(Function<QueryWrapper<T>, QueryWrapper<T>> function,
			QueryWrapper<T> queryWrapper);
}
