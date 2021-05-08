package com.lunz.query;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.ToString;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

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

	public static void main(String[] args) {
		String s = "{\n" +
				"\t\"field\": \"loginTimes\",\n" +
				"\t\"op\": \"bt\",\n" +
				"\t\"data\": \"\",\n" +
				"\t\"datas\": [\n" +
				"\t\t\"\",\n" +
				"\t\t\"\"\n" +
				"\t]\n" +
				"}";

		Rule rule = JSON.parseObject(s, Rule.class);
		System.out.println(rule);
		System.out.println(rule.getDatas().size());
		System.out.println(StringUtils.isEmpty(rule.getDatas().get(0)));
		System.out.println(CollectionUtils.isEmpty(rule.getDatas()));
	}
}
