
package com.lunz.util;

import java.util.List;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lunz.query.Command;
import com.lunz.query.PagingSort;
import com.lunz.query.QueryExtensions;

/**
 * 将Command<T>对象转为MyQueryWrapper<T>
 * 
 * @author haha
 *
 */
public class CommondUtil {

	public static <T> MyQueryWrapper<T> convertObjectToMP(Command<T> command) {

		MyQueryWrapper<T> myQueryWrapper = new MyQueryWrapper<T>();
		if (command == null) {
			return myQueryWrapper;
		}
		// 生成page对象
		if (StringUtils.isEmpty(command.getPageIndex()) || StringUtils.isEmpty(command.getPageSize())) {
			myQueryWrapper.setPage(null);
		} else {
			myQueryWrapper.setPage(new Page<T>(command.getPageIndex(), command.getPageSize()));
		}

		if (command.getFilter() == null && CollectionUtils.isEmpty(command.getPagingSorts())) {
			return myQueryWrapper;
		}
		// 生成queryWrapper对象
		QueryWrapper<T> queryWrapper;

		try {
			
			if (command.getFilter() != null) {
				queryWrapper = QueryExtensions.toWrapper(command.getFilter(), myQueryWrapper.getQueryWrapper());
			} else {
				queryWrapper = myQueryWrapper.getQueryWrapper();
			}

			if (!CollectionUtils.isEmpty(command.getPagingSorts())) {
				List<PagingSort> pagingSorts = command.getPagingSorts();
				for (PagingSort pagingSort : pagingSorts) {
					queryWrapper.orderBy(true, pagingSort.isAsc(), ConvertFieldUtil.camelToUnderline(pagingSort.getField()));
				}
			}
			myQueryWrapper.setQueryWrapper(queryWrapper);
		} catch (Exception e) {
			e.printStackTrace();
			return myQueryWrapper;
		}

		return myQueryWrapper;
	}

}
