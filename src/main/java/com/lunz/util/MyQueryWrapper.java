package com.lunz.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import lombok.Data;
import lombok.ToString;

/**
 * 自定义用于接收page和queryWrapper的对象，使用时注意判断page是否为空，为空则调用不分页的方法，直接将queryWrapper传入即可，queryWrapper则无需判断是否为空
 * @author haha
 *
 * @param <T>
 */
@Data
@ToString
public class MyQueryWrapper<T> {

	private IPage<T> page;
	
	private QueryWrapper<T> queryWrapper;
	
	public MyQueryWrapper() {
		this.queryWrapper = new QueryWrapper<>();
	}
}
