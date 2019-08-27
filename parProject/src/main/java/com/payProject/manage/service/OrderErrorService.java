package com.payProject.manage.service;

import java.util.List;

import com.payProject.manage.entity.ExceptionOrderEntity;

public interface OrderErrorService {
	/**
	 * <p>分页查询异常订单数据</p>
	 * @param orderError
	 * @return
	 */
	List<ExceptionOrderEntity> findPageOrderErrorByOrderError(ExceptionOrderEntity orderError);
}
