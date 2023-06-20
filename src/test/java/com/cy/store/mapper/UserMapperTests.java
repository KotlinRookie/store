package com.cy.store.mapper;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cy.store.entity.User;
import com.cy.store.mapper.UserMapper;

@SpringBootTest
//@RunWith:表示启动这个单元测试类(自己新创建的测试类没有这个注解，单元测试类不能够运行)
//需要传递一个参数，必须是SpringRunner的实例(class)类型
//@RunWith(SpringRunner.class)
public class UserMapperTests {

	@Autowired
	private UserMapper userMapper;

	
	 @Test 
	 public void insert() { 
		 User user = new User();
		 user.setUsername("测试用户2"); 
		 user.setPassword("abc123");
		 Integer rows = userMapper.insert(user); 
		 System.out.println(rows); 
	 }
	 
	 @Test
	 public void findByUsername() {
		 User user = userMapper.findByUsername("测试用户2");
		 System.out.println(user);
	 }
	 
	 @Test
	 public void updatePasswordByUid() {
		 Integer result = userMapper.updatePasswordByUid
		 (34, "qwe","管理员", new Date());
		 System.out.println(result);
	 }
	 
	 @Test
	 public void findByUid() {
		 User findByUid = userMapper.findByUid(38);
		 System.out.println(findByUid);
	 }
	 
	 @Test
	 public void updateInfoByUid() {
		 User user = new User();
		 user.setUid(40);
		 user.setPhone("12345678");
		 user.setEmail("12345678@qq.com");
		 user.setGender(1);
		 user.setModifiedUser("测试");
		 user.setModifiedTime(new Date());
		 Integer result = userMapper.updateInfoByUid(user);
		 System.out.println(result);
	 }
	 
	 @Test
	 public void updateAvatarByUid() {
		 userMapper.updateAvatarByUid(40, "C:\\img\\a.png", "管理员", new Date());
	 }
}
