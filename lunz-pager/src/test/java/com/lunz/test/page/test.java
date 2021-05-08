package com.lunz.test.page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.lunz.query.Command;
import com.lunz.query.QueryGroup;
import com.lunz.query.Rule;
import com.lunz.util.CommondUtil;

public class test {
	
	@Test
	public void TestPage() {
		

		String json = "{\"filter\":{\"groups\":[{\"groups\":[],\"rules\":[]}],\"op\":\"and\",\"rules\":[{\"data\":\"李兆杰\",\"datas\":[],\"field\":\"userName\",\"op\":\"cn\"},{\"data\":\"jerry\",\"datas\":[],\"field\":\"loginName\",\"op\":\"cn\"}]},\"pageIndex\":10,\"pageSize\":1,\"sort\":[]}";
		
		
		Command command = JSON.parseObject(json, Command.class);
		
		QueryGroup queryGroup = new QueryGroup();
		queryGroup.setOp("or");
		command.getFilter().getGroups().add(queryGroup);
		
		
		ArrayList<String> datas = new ArrayList<String>();
		datas.add("1");
		datas.add("2");
		Rule rule = new Rule();
		rule.setField("organizationId");
		rule.setDatas(datas);
		rule.setOp("or");
		
		command.getFilter().getRules().add(rule);
		Map<String, Object> convertObjectToPage = CommondUtil.convertObjectToPage(command, CommandDTO.class);
		
		System.out.println(convertObjectToPage.toString());
		
	}
	
	
	@Test
	public void rertainTest() {
		
		
		
		List<String> a = new ArrayList<String>();
		a.add("1");
		a.add("2");
		a.add("3");
		
		List<String> b = new ArrayList<String>();
		
		
		List<String> temp = new ArrayList<String>();
		temp.addAll(a);
		
		List<String> c = new ArrayList<String>();
		c.add("2");
		c.add("3");
		c.add("4");
		
		List<List<String>> lists = new ArrayList<List<String>>();
		lists.add(a);
		lists.add(b);
		lists.add(c);
		lists.add(null);
		
		List<String> list = lists.stream().filter(obj->obj!=null&&obj.size()>0).reduce((o1,o2)->{
			o1.retainAll(o2);
			return o1;
		}).get();
		
		System.out.println(list.toString());
		
		List<String> retainList = retainList(lists);
		System.out.println(retainList.toString());
		
		List<Map<String, Object>> alist = new ArrayList<Map<String,Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "aa");
		map.put("age","12");
		
		alist.add(map);
		
		List<Map<String, Object>> blist = new ArrayList<Map<String,Object>>();
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("name", "aa");
		map2.put("age","12");
		
		blist.add(map2);
		
		List<List<Map<String, Object>>> tMapList = new ArrayList<List<Map<String,Object>>>();
		
		tMapList.add(alist);
		tMapList.add(blist);
		
		List<Map<String, Object>> retainList2 = retainList2(tMapList);
		
		System.out.println(retainList2);
		
		List<String> retainList3 = retainList(new ArrayList<List<String>>());
		System.out.println(retainList3);
		
		List<String> retainList4 = retainList(lists);
		System.out.println(retainList4);
		
	}
	
	private static <E> List<E> retainList(List<List<E>> lists){
		
		if (lists!=null && lists.size()>0) {
			return lists.parallelStream().filter(obj->obj!=null&&obj.size()>0).reduce((o1,o2)->{
				o1.retainAll(o2);
				return o1;
			}).get();
		}
		return new ArrayList<E>();
	}
	
	private <K,V> List<Map<K, V>> retainList2(List<List<Map<K, V>>> lists){
			
			if (lists!=null && lists.size()>0) {
				return lists.stream().filter(obj->obj!=null&&obj.size()>0).reduce((o1,o2)->{
					o1.retainAll(o2);
					return o1;
				}).get();
			}
			return new ArrayList<Map<K, V>>();
		}

}
