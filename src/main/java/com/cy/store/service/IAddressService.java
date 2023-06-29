package com.cy.store.service;

import java.util.List;

import com.cy.store.entity.Address;

/**
 * 收货地址业务层的抽象方法
 * 
 * @author BP-changxiaolong
 *
 */
public interface IAddressService {
	/**
	 * 新增用户收货地址
	 * 
	 * @param uid      用户id
	 * @param username 用户名
	 * @param address  用户收货地址实体类
	 */
	void addNewAddress(Integer uid, String username, Address address);

	/**
	 * 根据用户id获取用户收货地址信息
	 * 
	 * @param uid 用户id
	 * @return 用户地址信息List集合
	 */
	List<Address> getByUid(Integer uid);

	/**
	 * 修改某个用户的某条收货地址数据为默认收货地址
	 * 
	 * @param aid      收货地址的id
	 * @param uid      用户id
	 * @param username 修改者
	 */
	void setDefault(Integer aid, Integer uid, String username);

	/**
	 * 删除用户选择的收货地址数据
	 * 
	 * @param aid      收货地址的id
	 * @param uid      用户id
	 * @param username 修改者
	 */
	void delete(Integer aid, Integer uid, String username);

	/**
	 * 查询用户当前修改的收货地址信息
	 * 
	 * @param aid 收货地址的id
	 * @param uid 用户id
	 * @return
	 */
	 Address findByAid(Integer aid,Integer uid);
	 
	 /**
	 * 修改用户收货地址方法
	 * 
	 * @param address 用户收货地址实体类
	 * @return 用户收货地址数据
	 */
	 Address updateAddress(Address address);
}
