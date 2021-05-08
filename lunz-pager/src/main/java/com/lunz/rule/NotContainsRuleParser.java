package com.lunz.rule;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.lunz.query.StringUtil;
import com.lunz.query.parse.RuleParser;

public class NotContainsRuleParser extends RuleParser{

	public NotContainsRuleParser() {
		super("%s NOT LIKE %s");
	}
	
	@Override
	public String Parse(String field, Object dataType, String[] data, Map<String, Object> parameters) {
		if (data == null) {
			return null;
		}
		// 判断是否存在空值
		List<String> nullList = Arrays.asList(data).stream().filter(x->StringUtil.IsNullOrWhiteSpace(x)).collect(Collectors.toList());
		if (nullList != null && nullList.size()==data.length) {
			return null;
		}
		String[] vars = new String[data.length+1];
		vars[0] = field;
		for (int i = 0; i < data.length; i++) {
			String key = "Param" + parameters.size();
			vars[i+1] = "#{" + key + "}";
			parameters.put(key,"%" + data[i] + "%");
		}
		return String.format(Expression, vars);
	}
}
