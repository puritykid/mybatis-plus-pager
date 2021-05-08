
package com.lunz.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lunz.query.*;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 将Command<T>对象转为MyQueryWrapper<T>
 * 
 * @author haha
 *
 */
public class MpUtil {

	public static <T> MyQueryWrapper<T> convertObjectToMP(MpPager<T> mpPager) {

		MyQueryWrapper<T> myQueryWrapper = new MyQueryWrapper<T>();

		QueryWrapper<T> queryWrapper = new QueryWrapper();

		if (mpPager == null) {
			return myQueryWrapper;
		}else {
			// 生成page对象,默认10条
			if (StringUtils.isEmpty(mpPager.getPageIndex()) || StringUtils.isEmpty(mpPager.getPageSize())) {
				myQueryWrapper.setPage(new Page<T>());
			} else {
				myQueryWrapper.setPage(new Page<T>(mpPager.getPageIndex(), mpPager.getPageSize()));
			}

			if (!CollectionUtils.isEmpty(mpPager.getSort())) {
				List<Sort> pagingSorts = mpPager.getSort();

				for (Sort pagingSort : pagingSorts) {
					queryWrapper.orderBy(true, "asc".equals(pagingSort.isSort()), ConvertFieldUtil.camelToUnderline(pagingSort.getField()));
				}

				myQueryWrapper.setQueryWrapper(queryWrapper);

			}

			mpPager = cleanMpPager(mpPager);
			if (mpPager == null) {
				return myQueryWrapper;
			} else {
				if (null != mpPager.getFilter()) {
					try {
						queryWrapper = QueryExtensions.toWrapper(mpPager.getFilter(), myQueryWrapper.getQueryWrapper());
					} catch (Exception e) {
						e.printStackTrace();
						return myQueryWrapper;
					}
				} else {
					queryWrapper = myQueryWrapper.getQueryWrapper();
				}
				myQueryWrapper.setQueryWrapper(queryWrapper);
				return myQueryWrapper;
			}
		}
	}

	private static <T> MpPager<T> cleanMpPager(MpPager<T> mpPager) {
		List<QueryGroup<T>> groups = mpPager.getFilter().getGroups();
		List<Rule> rules = mpPager.getFilter().getRules();
		cleanRules(rules);
		cleanGroups(groups);
		if (CollectionUtils.isEmpty(groups) && CollectionUtils.isEmpty(rules)) {
			mpPager = null;
		}

		return mpPager;
	}

	private static <T> void cleanGroups(List<QueryGroup<T>> groups) {
		for(int i = 0; i < groups.size(); ++i) {
			List<QueryGroup<T>> childrenGroup = ((QueryGroup)groups.get(i)).getGroups();
			List<Rule> rules = ((QueryGroup)groups.get(i)).getRules();
			if (!CollectionUtils.isEmpty(rules)) {
				cleanRules(rules);
			}

			if (!CollectionUtils.isEmpty(childrenGroup)) {
				cleanGroups(childrenGroup);
			}

			if (CollectionUtils.isEmpty(rules) && CollectionUtils.isEmpty(childrenGroup)) {
				groups.remove(groups.get(i));
				--i;
			}
		}

	}

	private static void cleanRules(List<Rule> rules) {
		for(int i = 0; i < rules.size(); ++i) {
			Rule rule = (Rule)rules.get(i);
			if (StringUtils.isEmpty(rule.getData()) && (CollectionUtils.isEmpty(rule.getDatas()) || StringUtils.isEmpty(rule.getDatas().get(0)) || StringUtils.isEmpty(rule.getDatas().get(1)))) {
				rules.remove(rule);
				--i;
			}
		}

	}
}
