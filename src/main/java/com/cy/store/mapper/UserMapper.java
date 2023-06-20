package com.cy.store.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.cy.store.entity.User;

/**
 * 用户模块的持久层接口
 * @author BP-changxiaolong
 *
 */
public interface UserMapper {
	/**
	 * 插入用户的数据
	 * @param user 用户的实体类
	 * @return 返回受影响的行数（增删改都受影响，有返回值，根据返回值判断是否执行成功）
	 */
	Integer insert(User user);
	
	/**
	 * 根据用户名来查询用户的数据
	 * @param username 用户名
	 * @return 如果查询到用户，则返回用户数据。没有找到，返回null
	 */
	User findByUsername(String username);
	
	/**
	 * 根据用户的uid来修改用户密码
	 * @param uid 用户uid
	 * @param password 用户密码
	 * @param modifiedUser 修改者
	 * @param modifiedTime 修改时间
	 * @return 返回值为受影响的行数
	 */
	Integer updatePasswordByUid(Integer uid,
							String password,
							String modifiedUser,
							Date modifiedTime);
	
	/**
	 * 根据用户id查询用户数据
	 * @param uid 用户id
	 * @return 如果找到则返回对象，反之返回null值
	 */
	User findByUid(Integer uid);
	
	/**
	 * 更新用户的信息
	 * @param user 用户的实体类
	 * @return 返回值为受影响的行数
	 */
	Integer updateInfoByUid(User user);
	
	/**
	 * @Param("SQL映射文件中#{}占位符的变量名")
	 * 解决的问题是当SQL语句的占位符和映射的接口方法参数名不一致时，
	 * 需要将某个参数强行注入到某个占位符变量上是,
	 * 可以使用@Param这个注解来标注映射的关系
	 * ------------------------------------------------
	 * 根据用户uid来修改用户头像
	 * @param uid 用户id
	 * @param avarat 用户头像
	 * @param modifiedUser 修改者
	 * @param modifiedTime 修改时间
	 * @return 返回值为受影响的行数
	 */
	// @Param - 代表传递的值和sql中写的不一致时，可以绑定在一起(起别名)
	// 使用方法如下，(下面的uid和sql写的是一样的)
	// 相同可以省略，不同可以强行匹配
	Integer updateAvatarByUid(@Param("uid") Integer uid,
							  @Param("avatar") String avarat,
							  @Param("modifiedUser") String modifiedUser,
							  @Param("modifiedTime") Date modifiedTime);
	
}
