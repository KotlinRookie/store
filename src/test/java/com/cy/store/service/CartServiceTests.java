package com.cy.store.service;

import java.util.Date;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cy.store.entity.Address;
import com.cy.store.entity.User;


@SpringBootTest
//@RunWith:表示启动这个单元测试类(自己新创建的测试类没有这个注解，单元测试类不能够运行)
//需要传递一个参数，必须是SpringRunner的实例(class)类型
//@RunWith(SpringRunner.class)

public class CartServiceTests {
	
	@Autowired
	private ICartService cartService;
	
	@Test
	public void addToCart() {
		cartService.addToCart(3, 10000002, 10, "管理员");
	}
	
	
}
