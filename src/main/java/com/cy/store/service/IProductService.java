package com.cy.store.service;
/*
 * 处理商品数据的业务层接口
 */

import java.util.List;

import com.cy.store.entity.Product;

public interface IProductService {
	/**
	 * 查询热销商品的前四名
	 * @return 热销商品前四名的集合
	 */
	List<Product> findHotList();
	
	Product findById(Integer id);
}
