package com.cy.store.mapper;

import java.util.Date;
import java.util.List;

import com.cy.store.entity.Cart;
import com.cy.store.vo.CartVO;

public interface CartMapper {
	/**
	 * 插入购物车数据
	 * @param cart 购物车实体类
	 * @return 受影响的行数
	 */
	Integer insert(Cart cart);
	
	/**
	 * 更新购物车某件商品的数量
	 * @param cid 购物车id
	 * @param num 商品数量
	 * @param modifiedUser 更新者
	 * @param modifiedTime 更新时间
	 * @return 受影响的行数
	 */
	Integer updateNumByCid(Integer cid,
							Integer num,
							String modifiedUser,
							Date modifiedTime);
	
	/**
	 * 根据用户的id和商品的id来查询购物车中的数据
	 * @param uid 用户id
	 * @param pid 商品id
	 * @return Cart的集合
	 */
	Cart findByUidAndPid(Integer uid, Integer pid);
	
	/**
	 * 多表查询
	 * @param uid 用户id
	 * @return
	 */
	List<CartVO> findVOByUid(Integer uid);
	
	/**
	 * 根据cid查询cart表
	 * @param cid
	 * @return cart表结构
	 */
	Cart findByCid(Integer cid);
	
	/**
	 * 多表查询
	 * @param uid 用户id
	 * @return
	 */
	List<CartVO> findVOByCid(Integer[] cids);
}
