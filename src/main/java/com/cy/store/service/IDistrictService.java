package com.cy.store.service;

import java.util.List;

import com.cy.store.entity.District;

public interface IDistrictService {
	/**
	 * 根据父代号来查询区域信息(省市区)
	 * @param parent 附代码
	 * @return 多个区域的信息
	 */
	List<District> getByParent(String parent);
	
	/**
	 * 根据code查询name
	 * @param code code
	 * @return 某个父取区域下的所有区域列表
	 */
	String getNameByCode(String code);
}
