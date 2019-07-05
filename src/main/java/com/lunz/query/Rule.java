package com.lunz.query;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
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
	
}
