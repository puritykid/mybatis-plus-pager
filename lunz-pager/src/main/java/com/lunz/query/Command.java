package com.lunz.query;

import java.util.ArrayList;
import java.util.List;

import com.lunz.sort.PagingSort;

public class Command {
	
	private List<PagingSort> sort;
	private QueryGroup filter;
	private Integer pageIndex;
	private Integer pageSize;
	
	public Command() {
		this.sort = new ArrayList<PagingSort>();
		this.filter = new QueryGroup();
	}
	public List<PagingSort> getSort() {
		return sort;
	}
	public void setSort(List<PagingSort> sort) {
		this.sort = sort;
	}
	public QueryGroup getFilter() {
		return filter;
	}
	public void setFilter(QueryGroup filter) {
		this.filter = filter;
	}
	public Integer getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}
