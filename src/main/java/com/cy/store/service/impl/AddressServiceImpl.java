package com.cy.store.service.impl;

import static org.junit.Assert.assertThrows;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cy.store.entity.Address;
import com.cy.store.mapper.AddressMapper;
import com.cy.store.service.IAddressService;
import com.cy.store.service.IDistrictService;
import com.cy.store.service.ex.AccessDeniedException;
import com.cy.store.service.ex.AddressCountLimitException;
import com.cy.store.service.ex.AddressNotFountException;
import com.cy.store.service.ex.InsertException;
import com.cy.store.service.ex.UpdateException;
/**
 * 新增用户收货地址的实现类
 * @author BP-changxiaolong
 *
 */
@Service
public class AddressServiceImpl implements IAddressService{
	
	@Autowired
	private AddressMapper addressMapper;
	// 在添加用户的收货地址的业务层依赖于DistrictService的业务层接口
	@Autowired
	private IDistrictService districtService;
	
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
		
		// 通过 districtService 拿到的省市数据对 address 对象中的数据进行补全
		String provinceName = districtService.getNameByCode(address.getProvinceCode());
		String cityName = districtService.getNameByCode(address.getCityCode());
		String areaName = districtService.getNameByCode(address.getAreaCode());
		address.setProvinceName(provinceName);
		address.setCityName(cityName);
		address.setAreaName(areaName);
		
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

	@Override
	public List<Address> getByUid(Integer uid) {
		List<Address> list = addressMapper.findByUid(uid);
		// 简化用户信息
		for (Address address : list) {
			address.setUid(null);
			address.setProvinceCode(null);
			address.setCityCode(null);
			address.setAreaCode(null);
			address.setTel(null);
			address.setIsDefault(null);
			address.setCreatedTime(null);
			address.setCreatedUser(null);
			address.setModifiedTime(null);
			address.setModifiedUser(null);
		}
		return list;
	}

	@Override
	@Transactional
	public void setDefault(Integer aid, Integer uid, String username) {
		Address result = addressMapper.findByAid(aid);
		if(result == null) {
			throw new AddressNotFountException("收货地址不存在");
		}
		// 检查当前获取到的收货地址数据归属
		if(!result.getUid().equals(uid)) {
			throw new AccessDeniedException("非法数据访问");
		}
		// 先将所有的收货地址设置为非默认
		Integer rows = addressMapper.updatenonDefault(uid);
		if(rows < 1) {
			throw new UpdateException("更新数据产生未知的异常");
		}
		// 将用户选中的某条地址设置为默认收货地址
		rows = addressMapper.updateDefaultByAid(aid, username, new Date());
		if(rows != 1) {
			throw new UpdateException("更新数据产生未知的异常");
		}
	}
}
