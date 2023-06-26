package com.cy.store.service.impl;

import static org.junit.Assert.assertThrows;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cy.store.entity.Address;
import com.cy.store.mapper.AddressMapper;
import com.cy.store.service.IAddressService;
import com.cy.store.service.ex.AddressCountLimitException;
import com.cy.store.service.ex.InsertException;
/**
 * 新增用户收货地址的实现类
 * @author BP-changxiaolong
 *
 */
@Service
public class AddressServiceImpl implements IAddressService{
	
	@Autowired
	private AddressMapper addressMapper;
	
	//使用配置文件的方式定义最大收货地址数量的变量
	@Value("${user.address.max-count}")
	private Integer maxCount;
	
	@Override
	public void addNewAddress(Integer uid, String username, Address address) {
		 //调用收货地址查询sql，查看用户收货地址是否超过界限（20条以上）
		Integer count = addressMapper.countByUid(uid);
		if(count >= maxCount) {
			throw new AddressCountLimitException("用户收货地址超出上限");
		}
		
		// uid,isDefault
		address.setUid(uid);
		// 确认收货地址是否为0条，如果为0就把第一条插入的数据设为默认收货地址
		Integer isDefault = count == 0 ? 1 : 0;
		address.setIsDefault(isDefault);
		// 补全4项日志
		address.setCreatedUser(username);
		address.setCreatedTime(new Date());
		address.setModifiedUser(username);
		address.setModifiedTime(new Date());
		
		// 调用插入收货地址的方法
		Integer rows = addressMapper.insert(address);
		if(rows != 1) {
			throw new InsertException("插入收货地址时产生未知的异常");
		}
	}
}
