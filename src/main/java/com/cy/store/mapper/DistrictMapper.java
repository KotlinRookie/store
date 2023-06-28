package com.cy.store.mapper;

import java.util.List;

import com.cy.store.entity.District;

public interface DistrictMapper {
	/**
	 * 根据父代号查询区域信息
	 * @param parent 父代号
	 * @return 某个父取区域下的所有区域列表
	 */
	List<District> findByParent(String parent);
	
	/**
	 * 根据code查询name
	 * @param code code
	 * @return 某个父取区域下的所有区域列表
	 */
	String findNameByCode(String code);

}
