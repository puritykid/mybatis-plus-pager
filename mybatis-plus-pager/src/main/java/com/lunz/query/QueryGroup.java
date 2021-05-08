package com.lunz.query;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.ToString;

/**
 * 查询组
 * @author haha
 *
 */
@Data
@ToString
public class QueryGroup<T> {
	
	private String op;
	private List<Rule> rules;
	private List<QueryGroup<T>> groups;
	public QueryGroup() {
		this.groups = new ArrayList<QueryGroup<T>>();
		this.rules = new ArrayList<Rule>();
	}
}
