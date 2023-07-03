package com.cy.store.service;

import java.util.List;

import com.cy.store.vo.CartVO;

public interface ICartService {
	/**
	 * 将商品添加到购物车中
	 * @param uid 用户id
	 * @param pid 商品id
	 * @param acount 新增的数量
	 * @param username 用户名
	 */
	void addToCart(Integer uid,Integer pid,
			Integer amount,String username);
	
	/**
	 * 根据uid查询VO类中数据
	 * @param uid 用户id
	 * @return 一个VO类的集合
	 */
	List<CartVO> getVOByUid(Integer uid);
	
	/**
	 * 更新(增加)用户的购物车数据的数量
	 * @param cid 
	 * @param uid
	 * @param username
	 * @return 增加成功后新的数量
	 */
	Integer addNum(Integer cid,Integer uid,String username);
	
	/**
	 * 更新(减少)用户的购物车数据的数量
	 * @param cid 
	 * @param uid
	 * @param username
	 * @return 增加成功后新的数量
	 */
	Integer minusNum(Integer cid,Integer uid,String username);
	
	/**
	 * 实现商品结算页面传递值
	 * @param uid
	 * @param cids
	 * @return 一个VO类的集合
	 */
	List<CartVO> getVOByCid(Integer uid, Integer[] cids);
}
