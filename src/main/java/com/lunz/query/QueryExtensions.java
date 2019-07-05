package com.lunz.query;

import java.util.function.Function;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lunz.interfaces.IQueryGroupParser;
import com.lunz.query.parse.QueryParsers;

public class QueryExtensions {
	/**
	 * 用于解析QueryGroup，将QueryGroup中的条件，解析成QueryWrapper
	 * @param <T>
	 * @param group
	 * @param queryWrapper
	 * @return
	 * @throws Exception
	 */
	public static <T> QueryWrapper<T> toWrapper(QueryGroup<T> group, QueryWrapper<T> queryWrapper) throws Exception {
		Function<QueryWrapper<T>, QueryWrapper<T>> function = getFuncByQueryGroup(group);
		//获取group解析（or和and）
		IQueryGroupParser parser = QueryParsers.getQueryGroupParser(group.getOp());
		queryWrapper = parser.parseWrapperByFunc(function,queryWrapper);
		return queryWrapper;
	}

	public static <T> Function<QueryWrapper<T>, QueryWrapper<T>> getFuncByQueryGroup(QueryGroup<T> group)
			throws Exception {
		Function<QueryWrapper<T>, QueryWrapper<T>> func;
		IQueryGroupParser parser = QueryParsers.getQueryGroupParser(group.getOp());
		func = parser.parseFunc(group);
		return func;
	}
}
