package com.cy.store.mapper;



import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.startsWith;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.validator.PublicClassValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import com.cy.store.entity.Address;
import com.cy.store.service.impl.AddressServiceImpl;

@SpringBootTest
//@RunWith:表示启动这个单元测试类(自己新创建的测试类没有这个注解，单元测试类不能够运行)
//需要传递一个参数，必须是SpringRunner的实例(class)类型
//@RunWith(SpringRunner.class)
public class AddressMapperTests {

	@Autowired
	private AddressMapper addressMapper;

	@Test
	public void updateAddress() {
		Address address= new Address();
		address.setAid(59);
		address.setName("测试");
		address.setModifiedTime(new Date());
		Integer updateAddress = addressMapper.updateAddress(address);
		System.out.println(updateAddress);
	}


}
