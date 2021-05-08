package com.lunz.query;

import java.util.ArrayList;
import java.util.List;

public class Rule {

	// 查询字段
	private String field;
	
	// 查询字段匹配操作 例如：eq(等于)
	private String op;
	
	// 查询数据
	private String data;
	
	// 查询数据集合
	private List<String> datas;

	public Rule() {
		this.datas = new ArrayList<String>();
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public List<String> getDatas() {
		return datas;
	}

	public void setDatas(List<String> datas) {
		this.datas = datas;
	}

	@Override
	public String toString() {
		return "Rule [field=" + field + ", op=" + op + ", data=" + data + ", datas=" + datas + "]";
	}
	
	
}
