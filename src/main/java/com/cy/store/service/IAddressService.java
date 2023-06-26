package com.cy.store.service;

import com.cy.store.entity.Address;

/**
 * 收货地址业务层的抽象方法
 * @author BP-changxiaolong
 *
 */
public interface IAddressService {
	/**
	 * 新增用户收货地址
	 * @param uid 用户id
	 * @param username 用户名
	 * @param address 用户收货地址实体类
	 */
	void addNewAddress(Integer uid,String username,Address address);
}
