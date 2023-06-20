package com.cy.store.service.impl;

import java.util.Date;
import java.util.UUID;

import org.apache.ibatis.javassist.expr.NewArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.cy.store.entity.User;
import com.cy.store.mapper.UserMapper;
import com.cy.store.service.IUserService;
import com.cy.store.service.ex.InsertException;
import com.cy.store.service.ex.PasswordNotMatchException;
import com.cy.store.service.ex.UpdateException;
import com.cy.store.service.ex.UserNotFoundException;
import com.cy.store.service.ex.UserPasswordException;
import com.cy.store.service.ex.UsernameDuplicatedException;
import com.cy.store.service.ex.UsernameEmptyException;
@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public void reg(User user) {
		//通过user参数来获取传递过来的username
		String username = user.getUsername();
		//调用findByUsername(username)来判断用户是否被注册过
		User result = userMapper.findByUsername(username);
		//判断结果是否为null，为null则可以注册。不为null则抛出用户名已注册的异常
		if(result != null) {
			//新注册用户名不为空，抛出异常
			throw new UsernameDuplicatedException("用户名被占用");
		}
		if(username == "") {
			//用户名为空，抛出异常
			throw new UsernameEmptyException("用户名不能为空");
		}
		//判断密码
		//1.不小于6位数
		//2.不包含空格
		//3.不为空
		if(user.getPassword().length() < 6 || user.getPassword().contains(" ") || user.getPassword() == null) {
			throw new UserPasswordException("用户密码不符合规范");
		}
		//密码加密开始
		String oldPassword = user.getPassword();
		//获取盐值（随机生成一个盐值）
		String salt = UUID.randomUUID().toString().toUpperCase();
		//将密码和盐值作为一个整体进行加密处理
		String md5Password = getMD5Password(oldPassword, salt);
		//设置盐值
		user.setSalt(salt);
		//将加密后的密码重新设置到user对象中
		user.setPassword(md5Password);
		
		//补全数据:is_delete初始值为0，代表未删除。1代表删除。
		user.setIsDelete(0);
		//补全数据:4个日志字段信息
		user.setCreatedUser(user.getUsername());
		user.setModifiedUser(user.getUsername());
		Date date = new Date();
		user.setCreatedTime(date);
		user.setModifiedTime(date);
		
		//执行注册业务功能的实现
		Integer rows = userMapper.insert(user);
		if(rows != 1) {
			throw new InsertException("在用户注册过程中产生了未知的异常");
		}
	}
	
	
	@Override
	public User login(String username, String password) {
		//根据用户名来查询用户数据是否存在，如果不存在则抛出异常
		User result = userMapper.findByUsername(username);
		//没有查到该用户，或者该用户已被删除，抛出异常
		if(result == null || result.getIsDelete() == 1) {
			throw new UserNotFoundException("用户数据不存在");
		}
		//检测用户密码是否匹配
		//1.先获取到数据库中的加密后的密码
		String oldPassword = result.getPassword();
		
		//2.和用户传递过来的未加密的密码进行比较
		//2.1 先获取盐值
		String salt = result.getSalt();
		
		//2.2 将用户的密码按照相同的md5算法进行加密
		String newMd5Password = getMD5Password(password, salt);
		
		//3.比较数据库的密码和加密后的用户传递的密码是否相同
		if(!oldPassword.equals(newMd5Password)) {
			throw new PasswordNotMatchException("密码错误");
		}
		
		//返回时去掉不需要的用户数据
		User user = new User();
		user.setUid(result.getUid());
		user.setUsername(result.getUsername());
		user.setAvatar(result.getAvatar());
		return user;
	}
	
	// 修改密码
	@Override
	public void changePassword(Integer uid, String username, String oldPassword, String newPassword) {
		User result = userMapper.findByUid(uid);
		if(result == null || result.getIsDelete() == 1) {
			throw new UsernameDuplicatedException("用户数据不存在");
		}
		
		// 原始密码和数据库中的密码进行比较
		String md5Password = getMD5Password(oldPassword, result.getSalt());
		if(!md5Password.equals(result.getPassword())) {
			throw new PasswordNotMatchException("密码错误");
		}
		// 判断新密码是否符合规范
		if(newPassword.length() < 6 || newPassword.contains(" ") || newPassword == null) {
			throw new UserPasswordException("用户密码不符合规范");
		}
		// 将用户传递的新密码设定
		// 1.将新密码进行加密
		String newMd5Password = getMD5Password(newPassword, result.getSalt());
		// 2.设定新密码
		Integer rows = userMapper.updatePasswordByUid(uid, newMd5Password, username, new Date());
		// 3.判断是否插入数据成功
		if(rows != 1) {
			throw new UpdateException("修改密码时产生未知的异常");
		}
	}
	
	@Override
	public User getByUid(Integer uid) {
		User result = userMapper.findByUid(uid);
		if(result == null || result.getIsDelete() == 1) {
			throw new UserNotFoundException("用户数据不存在");
		}
		// 只留下必要的数据
		User user = new User();
		user.setUsername(result.getUsername());
		user.setPhone(result.getPhone());
		user.setEmail(result.getEmail());
		user.setGender(result.getGender());
		return user;
	}

	/**
	 * user对象中的数据phone,email,gender,手动再将uid,username
	 * 封装进user对象中
	 */
	@Override
	public void changeInfo(Integer uid, String username, User user) {
		 User result = userMapper.findByUid(uid);
		 if(result == null || result.getIsDelete() == 1) {
			 throw new UserNotFoundException("用户数据不存在");
		 }
		 user.setUid(uid);
		 user.setUsername(username);
		 user.setModifiedUser(username);
		 user.setModifiedTime(new Date());
		 
		 //更新用户数据
		 Integer rows = userMapper.updateInfoByUid(user);
		 if(rows != 1) {
			 throw new UpdateException("更新数据时产生未知的异常");
		 }
	}
	
	@Override
	public void changeAvatar(Integer uid, String avatar, String username) {
		// 判断用户数据是否存在
		User result = userMapper.findByUid(uid);
		if(result == null || result.getIsDelete() == 1) {
			throw new UserNotFoundException("用户数据不存在");
		}
		
		Integer rows = userMapper.updateAvatarByUid(uid, avatar, username, new Date());
		if(rows != 1) {
			throw new UpdateException("更新用户头像产生未知的异常");
		}
	}
	
	private String getMD5Password(String password,String salt) {
		for (int i = 0; i < 3; i++) {
			//MD5加密算法的调用(进行三次加密)
			password = DigestUtils.md5DigestAsHex((salt+password+salt).getBytes()).toUpperCase();
		}
		//返回加密之后的密码
		return password;
	}
}
