package com.cy.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.store.entity.District;
import com.cy.store.mapper.DistrictMapper;
import com.cy.store.service.IDistrictService;

@Service
public class DistrictServiceImpl implements IDistrictService {
	
	@Autowired
	private DistrictMapper districtMapper;
	
	@Override
	public List<District> getByParent(String parent) {
		List<District> list = districtMapper.findByParent(parent);
		// 在进行网络数据传输时，为了尽量避免无效数据的传递，可以将无效数据设置为null
		for (District d : list) {
			d.setId(null);
			d.setParent(null);
		}
		return list;
	}

	@Override
	public String getNameByCode(String code) {
		return districtMapper.findNameByCode(code);	 
	}	
}
