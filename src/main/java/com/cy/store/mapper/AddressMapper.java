package com.cy.store.mapper;

import java.util.Date;
import java.util.List;

import com.cy.store.entity.Address;

/**
 * 用户地址模块的持久层接口
 * @author BP-changxiaolong
 *
 */
public interface AddressMapper {
	/**
	 * 插入用户的收货地址数据
	 * @param address 收货地址数据
	 * @return 受影响的行数
	 */
	Integer insert(Address address);
	
	/**
	 * 根据用户的id统计收货地址数量
	 * @param uid 用户id
	 * @return 当前用户的收货地址总数
	 */
	Integer countByUid(Integer uid);
	
	/**
	 * 根据用户的id查询用户的收货地址数据
	 * @param uid 用户id
	 * @return 收货地址数据
	 */
	List<Address> findByUid(Integer uid);
	
	/**
	 * 根据aid查询收货地址数据
	 * @param aid 收货地址id
	 * @return 收货地址数据，如果没有找到则返回null
	 */
	Address findByAid(Integer aid);

	/**
	 * 根据用户的uid来修改用户的收货地址设置为非默认
	 * @param uid 用户id
	 * @return 受影响的行数
	 */
	Integer updatenonDefault(Integer uid);
	
	/**
	 * 根据用户的aid来修改用户的收货地址设置为默认
	 * @param aid 用户id
	 * @param modifieduser 更新用户
	 * @param modifiedTime 更新时间
	 * @return 受影响的行数
	 */
	Integer updateDefaultByAid(Integer aid,
							   String modifiedUser,
							   Date modifiedTime);
	
	/**
	 * 根据收货地址id来删除收货地址数据
	 * @param aid 收货地址id
	 * @return 受影响的行数
	 */
	Integer deleteByAid(Integer aid);
	
	/**
	 * 根据用户uid查询当前用户最后一次被修改的收货地址数据
	 * @param uid 用户id
	 * @return 收货地址数据
	 */

	Address findLastModified(Integer uid);
	
	/**
	 * 根据用户的aid来修改用户收货地址
	 * @param aid 用户aid
	 * @param modifiedUser 修改者
	 * @param modifiedTime 修改时间
	 * @return
	 */
	Integer updateAddress(Address address);
}
