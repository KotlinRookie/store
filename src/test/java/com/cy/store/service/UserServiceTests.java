package com.cy.store.service;



import java.util.Date;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cy.store.entity.User;


@SpringBootTest
//@RunWith:表示启动这个单元测试类(自己新创建的测试类没有这个注解，单元测试类不能够运行)
//需要传递一个参数，必须是SpringRunner的实例(class)类型
//@RunWith(SpringRunner.class)
public class UserServiceTests {

	@Autowired
	private IUserService iUserService;

	
	 @Test 
	 public void reg() {
		 User user = new User();
		 user.setUsername("测试用户4");
		 user.setPassword("abc123");
		 iUserService.reg(user);
	 }
	 
	 @Test
	 public void login() {
		 User user = new User();
		 user.setUsername("测试用户2");
		 user.setPassword("abc123");
		 User userData = iUserService.login(user.getUsername(), user.getPassword());
		 System.out.println(userData);
	 }
	 
	 @Test
	 public void changePassword() {
		 iUserService.changePassword(40,"测试担当","test001","test002");
	 }
	 
	 @Test
	 public void getByUid() {
		 User user = iUserService.getByUid(40);
		 System.out.println(user);
	 }
	 
	 @Test
	 public void changeInfo() {
		 User user = new User();
		 user.setPhone("09011112222");
		 user.setEmail("999999999@qq.com");
		 user.setGender(0);
		 iUserService.changeInfo(40, "测试用户", user);
	 }
	 
	 @Test
	 public void changeAvatar() {
		 iUserService.changeAvatar(40, "C:\\img\\b.png", "管理员");
	 }
}
