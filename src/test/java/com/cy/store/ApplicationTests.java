package com.cy.store;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cy.store.mapper.UserMapper;

@SpringBootTest
class ApplicationTests {

	@Autowired
	private DataSource dataSource;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	void getConnection() throws SQLException {
		System.out.println(dataSource.getConnection());
	}

	
	private UserMapper userMapper;
	
}
