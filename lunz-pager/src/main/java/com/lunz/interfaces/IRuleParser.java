package com.lunz.interfaces;

import java.util.Map;

/**
 * 数据转换规则
 * @author haha
 *
 */
public interface IRuleParser {
	public String Parse(String field, Object dataType, String[] data, Map<String, Object> parameters) throws Exception;
}
