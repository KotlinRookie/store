package com.cy.store.mapper;



import static org.mockito.ArgumentMatchers.startsWith;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import com.cy.store.entity.Address;
import com.cy.store.entity.District;

@SpringBootTest
//@RunWith:表示启动这个单元测试类(自己新创建的测试类没有这个注解，单元测试类不能够运行)
//需要传递一个参数，必须是SpringRunner的实例(class)类型
//@RunWith(SpringRunner.class)
public class DistrictMapperTests {
	
	@Autowired
	private DistrictMapper districtMapper;
	
	@Test
	public void findNameByCode() {
		
		String findNameByCode = districtMapper.findNameByCode("110101");
		System.out.println(findNameByCode);
	}



}
