package com.cy.store.service;

import com.cy.store.entity.User;


/**
 * 用户模块业务层接口
 * @author BP-changxiaolong
 *
 */
public interface IUserService {
	/**
	 * 用户注册方法
	 * @param user 用户的数据对象
	 */
	void reg(User user);
	
	/**
	 * 用户登录功能
	 * @param username 用户名
	 * @param password 用户密码
	 * @return 返回当前匹配的用户的数据，若果没有则返回null
	 */
	User login(String username, String password);
	
	/**
	 * 修改密码
	 * @param uid	用户uid
	 * @param username 用户名
	 * @param oldPassword 原始密码
	 * @param newPassword 新密码
	 */
	void changePassword(Integer uid,
						String username,
						String oldPassword,
						String newPassword);
	
	/**
	 * 根据用户的id查询用户的数据
	 * @param uid 用户id
	 * @return 用户的数据
	 */
	User getByUid(Integer uid);
	
	/**
	 * 更新用户的数据操作
	 * @param uid 用户id
	 * @param username 用户名
	 * @param user 用户对象数据
	 */
	void changeInfo(Integer uid,String username,User user);

	/**
	 * 修改用户头像
	 * @param uid　用户id
	 * @param avatar　用户头像地址
	 * @param username 修改者
	 */
	void changeAvatar(Integer uid,
					  String avatar,
					  String username);
}
