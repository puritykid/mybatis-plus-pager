package com.lunz.query.parse;

import java.util.HashMap;
import java.util.Map;

import com.lunz.group.AndQueryGroupParser;
import com.lunz.group.OrQueryGroupParser;
import com.lunz.interfaces.IQueryGroupParser;
import com.lunz.interfaces.IRuleParser;
import com.lunz.rule.BeginWithRuleParser;
import com.lunz.rule.BetweenRuleParser;
import com.lunz.rule.ContainsRuleParser;
import com.lunz.rule.EndWithRuleParser;
import com.lunz.rule.EqualRuleParser;
import com.lunz.rule.GreaterOrEqualThanRuleParser;
import com.lunz.rule.GreaterThanRuleParser;
import com.lunz.rule.IsNullRuleParser;
import com.lunz.rule.LessOrEqualThanRuleParser;
import com.lunz.rule.LessThanRuleParser;
import com.lunz.rule.NotBeginWithRuleParser;
import com.lunz.rule.NotContainsRuleParser;
import com.lunz.rule.NotEndWithRuleParser;
import com.lunz.rule.NotEqualRuleParser;
import com.lunz.rule.NotIsNullRuleParser;

public class QueryParsers {
	
	private static Map<String, IQueryGroupParser> queryGroupParsers = new HashMap<String, IQueryGroupParser>();
	private static Map<String, IRuleParser> ruleParsers = new HashMap<String, IRuleParser>();
	
	
	private static QueryParsers instence = new QueryParsers();
	private QueryParsers() {}
	public static QueryParsers getInstence() {
		
		queryGroupParsers.put("and", new AndQueryGroupParser());
		queryGroupParsers.put("or", new OrQueryGroupParser());
		
		ruleParsers.put("eq", new EqualRuleParser());
		ruleParsers.put("ne", new NotEqualRuleParser());
		ruleParsers.put("lt", new LessThanRuleParser());
		ruleParsers.put("le", new LessOrEqualThanRuleParser());
		ruleParsers.put("gt", new GreaterThanRuleParser());
		ruleParsers.put("ge", new GreaterOrEqualThanRuleParser());
		ruleParsers.put("bw", new BeginWithRuleParser());
		ruleParsers.put("bn", new NotBeginWithRuleParser());
		ruleParsers.put("ew", new EndWithRuleParser());
		ruleParsers.put("en", new NotEndWithRuleParser());
		ruleParsers.put("cn", new ContainsRuleParser());
		ruleParsers.put("nc", new NotContainsRuleParser());
		ruleParsers.put("nu", new IsNullRuleParser());
		ruleParsers.put("nn", new NotIsNullRuleParser());
		ruleParsers.put("bt", new BetweenRuleParser());
		return instence;
	}
	
	/**
	 * 注册组解析器
	 * @param key
	 * @param queryGroupParser
	 */
	public void Register(String key, IQueryGroupParser queryGroupParser)
    {
		 queryGroupParser = queryGroupParsers.get(key.toLowerCase());
    }

	/**
	 * 注册规则解析器
	 * @param key
	 * @param ruleParser
	 */
    public void Register(String key, IRuleParser ruleParser)
    {
    	ruleParser = ruleParsers.get(key.toLowerCase());
    }

    /**
     * 获取组解析器
     * @param key
     * @return
     * @throws Exception
     */
    public IQueryGroupParser GetQueryGroupParser(String key) throws Exception
    {
    	
    	String _key = key.toLowerCase();
    	if (!queryGroupParsers.containsKey(_key)) {
			throw new Exception("未能找到 " + key + "对应的 IQueryGroupParser实例。");
		}
    	return queryGroupParsers.get(_key);
    }

    /**
     * 获取规则条件解析器
     * @param key
     * @return
     * @throws Exception
     */
    public IRuleParser GetRuleParser(String key) throws Exception
    {
    	String _key = key.toLowerCase();
    	if (!ruleParsers.containsKey(_key)) {
    		throw new Exception("未能找到 " + key + "对应的 IRuleParser 实例。");
		}

        return ruleParsers.get(_key);
    }
}
