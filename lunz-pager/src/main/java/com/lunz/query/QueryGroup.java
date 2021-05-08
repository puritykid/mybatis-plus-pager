package com.lunz.query;

import java.util.ArrayList;
import java.util.List;

/**
 * 查询组
 * @author haha
 *
 */
public class QueryGroup {
	
	private String op;
	private List<Rule> rules;
	private List<QueryGroup> groups;
	public QueryGroup() {
		this.groups = new ArrayList<QueryGroup>();
		this.rules = new ArrayList<Rule>();
	}
	public String getOp() {
		return op;
	}
	public void setOp(String op) {
		this.op = op;
	}
	public List<Rule> getRules() {
		return rules;
	}
	public void setRules(List<Rule> rules) {
		this.rules = rules;
	}
	public List<QueryGroup> getGroups() {
		return groups;
	}
	public void setGroups(List<QueryGroup> groups) {
		this.groups = groups;
	}
	
}
