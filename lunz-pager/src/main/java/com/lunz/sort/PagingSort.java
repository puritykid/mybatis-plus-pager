
package com.lunz.sort;

/**
 * 页面排序
 * @author haha
 *
 */

public class PagingSort {

	private String field;

	private String sort;
	
	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getSortOrder() {
		return String.format("%s %s", field, sort);
	}
	
}
