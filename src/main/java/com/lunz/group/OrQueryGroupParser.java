package com.lunz.group;

import java.util.function.Function;

import org.springframework.util.CollectionUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lunz.interfaces.IQueryGroupParser;
import com.lunz.interfaces.IRuleParser;
import com.lunz.query.QueryExtensions;
import com.lunz.query.QueryGroup;
import com.lunz.query.Rule;
import com.lunz.query.parse.QueryParsers;

public class OrQueryGroupParser implements IQueryGroupParser {

	private static final String opration = "or";

	@Override
	public <T> Function<QueryWrapper<T>, QueryWrapper<T>> parseFunc(QueryGroup<T> group) {
		Function<QueryWrapper<T>, QueryWrapper<T>> func = new Function<QueryWrapper<T>, QueryWrapper<T>>() {

			@Override
			public QueryWrapper<T> apply(QueryWrapper<T> t) {
				if (!CollectionUtils.isEmpty(group.getRules())) {
					try {
						for (Rule rule : group.getRules()) {
							// 根据rule和QueryWrapper<T>获取新的QueryWrapper<T>
							t = parseWrapperRule(rule, t);
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (!CollectionUtils.isEmpty(group.getGroups())) {
					try {
						for (QueryGroup<T> queryGroup : group.getGroups()) {
							Function<QueryWrapper<T>, QueryWrapper<T>> func = QueryExtensions
									.getFuncByQueryGroup(queryGroup);
							t = t.or(func);
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				return t;
			}
		};
		return func;
	}

	private <T> QueryWrapper<T> parseWrapperRule(Rule rule, QueryWrapper<T> queryWrapper) throws Exception {
		IRuleParser parser = QueryParsers.getRuleParser(rule.getOp());
		queryWrapper = parser.Parse(opration, rule, queryWrapper);
		return queryWrapper;
	}

	@Override
	public <T> QueryWrapper<T> parseWrapperByFunc(Function<QueryWrapper<T>, QueryWrapper<T>> function,
			QueryWrapper<T> queryWrapper) {
		// TODO Auto-generated method stub
		return queryWrapper.or(function);
	}
}
