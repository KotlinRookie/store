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

}
