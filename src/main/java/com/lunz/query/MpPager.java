package com.lunz.query;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * 解析json数据的一般对象，需要传入对应的泛型
 * @author haha
 *
 * @param <T>
 */
@Data
@ToString
public class MpPager<T> {

	private QueryGroup<T> filter;
	private Integer pageIndex;
	private Integer pageSize;
	private List<Sort> sort;
}
