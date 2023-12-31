package com.cy.store.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.store.entity.Cart;
import com.cy.store.entity.Product;
import com.cy.store.mapper.CartMapper;
import com.cy.store.mapper.ProductMapper;
import com.cy.store.service.ICartService;
import com.cy.store.service.ex.AccessDeniedException;
import com.cy.store.service.ex.CartNotFoundException;
import com.cy.store.service.ex.InsertException;
import com.cy.store.service.ex.UpdateException;
import com.cy.store.service.ex.UsernameDuplicatedException;
import com.cy.store.vo.CartVO;

@Service
public class CartServiceImpl implements ICartService {

	@Autowired
	private CartMapper cartMapper;
	@Autowired
	private ProductMapper productMapper;
	
	@Override
	public void addToCart(Integer uid, Integer pid, Integer amount, String username) {
		// 查询当前要添加的这个购物车是否在表中已存在
		Cart result = cartMapper.findByUidAndPid(uid, pid);
		Date date = new Date();
		if(result == null) {	//表示这个商品从来没有被添加到购物车中
			//添加操作
			// 创建一个Cart对象
			Cart cart = new Cart();
			// 补全数据
			cart.setUid(uid);
			cart.setPid(pid);
			cart.setNum(amount);
			// 价格： 来自于商品中的数据
			Product product = productMapper.findById(pid);
			cart.setPrice(product.getPrice());
			// 补全四项日志
			cart.setCreatedUser(username);
			cart.setCreatedTime(date);
			cart.setModifiedUser(username);
			cart.setModifiedTime(date);
			// 执行数据的插入操作
			Integer rows = cartMapper.insert(cart);
			if(rows != 1) {
				throw new InsertException("插入数据时产生未知的异常");
			}
		}else {
			//表示当前的商品在购物车中已经存在，则需要更新这条数据的num值
			Integer rows = cartMapper.updateNumByCid
					(result.getCid(), 
					amount + result.getNum(),
					username, date);
			
			if(rows != 1) {
				throw new UpdateException("更新数据时产生未知的异常");
			}
		}
	}

	@Override
	public List<CartVO> getVOByUid(Integer uid) {
		return cartMapper.findVOByUid(uid);
	}

	@Override
	public Integer addNum(Integer cid, Integer uid, String username) {
		Cart result = cartMapper.findByCid(cid);
		if(result == null) {
			throw new CartNotFoundException("数据不存在");
		}
		if(!result.getUid().equals(uid)) {
			throw new AccessDeniedException("非法的数据访问");
		}
		Integer num = result.getNum() + 1;
		Integer rows = cartMapper.updateNumByCid(cid, num, username, new Date());
		if(rows != 1) {
			throw new UpdateException("更新数据失败");
		}
		return num;
	}

	@Override
	public Integer minusNum(Integer cid, Integer uid, String username) {
		Cart result = cartMapper.findByCid(cid);
		if(result == null) {
			throw new CartNotFoundException("数据不存在");
		}
		if(!result.getUid().equals(uid)) {
			throw new AccessDeniedException("非法的数据访问");
		}
		Integer num = result.getNum() - 1;
		if(num < 0) {
			throw new UpdateException("购物车数据不能为0以下");
		}
		Integer rows = cartMapper.updateNumByCid(cid, num, username, new Date());
		if(rows != 1) {
			throw new UpdateException("更新数据失败");
		}
		return num;
	}

	@Override
	public List<CartVO> getVOByCid(Integer uid, Integer[] cids) {
		List<CartVO> result = cartMapper.findVOByCid(cids);
		for (CartVO cartVO : result) {
			if(!cartVO.getUid().equals(uid)) {
				throw new AccessDeniedException("非法的数据访问");
			}
		}
		return result;
	}
}
