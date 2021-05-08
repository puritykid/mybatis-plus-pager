package com.lunz.rule;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.lunz.query.StringUtil;
import com.lunz.query.parse.RuleParser;

public class BetweenRuleParser extends RuleParser{

	private String betweenExpression;
	
	public BetweenRuleParser() {
		super("(%s>=%s and %s<=%s)");
	}

	@Override
	public String Parse(String field, Object dataType, String[] data, Map<String, Object> parameters) throws Exception {
		if (data == null) {
			return null;
		}
		// data中的值全部为空，返回null
		List<String> nullList = Arrays.asList(data).stream().filter(x->StringUtil.IsNullOrWhiteSpace(x)).collect(Collectors.toList());
		if (nullList != null && nullList.size() == data.length) {
			return null;
		}
		
		if (data.length != 2) {
			throw new Exception(field + "对应数组的length异常。");
		}
		
		if (StringUtil.IsNullOrWhiteSpace(data[0]) || StringUtil.IsNullOrWhiteSpace(data[1])) {
			if (!StringUtil.IsNullOrWhiteSpace(data[0])) {
				betweenExpression = "%s>=%s";   // "{0}>={1}"
			} else {
				betweenExpression = "%s<=%s";   // "{0}<={2}"
				data = (String[]) Arrays.asList(data).stream().filter(x->x!=null).toArray();  //重新将不为null的data赋值
			}
		} else {
			betweenExpression = Expression;
		}
		
		String[] vars = null;
		if (data.length == 2) {
			vars =  new String[data.length+2];
			vars[0] = field;
			vars[2] = field;
			for (int i = 0; i < data.length; i++) {
				String key = "Param" + parameters.size();
				parameters.put(key, data[i]);
				if (i==0) {
					vars[1] = "#{" + key + "}";
				}else {
					vars[3] = "#{" + key + "}";
				}
			}
		}else {
			vars =  new String[data.length+1];
			vars[0] = field;
			for (int i = 0; i < data.length; i++) {
				String key = "Param" + parameters.size();
				vars[i+1] = "#{" + key + "}";
				parameters.put(key,data[i]);
			}
		}
		return String.format(betweenExpression, vars);
	}

}
