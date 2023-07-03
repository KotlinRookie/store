package com.cy.store.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cy.store.service.ICartService;
import com.cy.store.util.JsonResult;
import com.cy.store.vo.CartVO;

@RestController
@RequestMapping("carts")
public class CartController extends BaseController {
	
	@Autowired
	private ICartService cartService;
	
	@RequestMapping("add_to_cart")
	public JsonResult<Void> addToCart(Integer pid, Integer amount,HttpSession session){
		Integer uid = getuidFromSession(session);
		String username = getUsernameFromSession(session);
		cartService.addToCart(uid, pid, amount, username);
		return new JsonResult<>(OK);
	}
	
	@RequestMapping({"","/"})
	public JsonResult<List<CartVO>> getVOByUid(HttpSession session){
		Integer uid = getuidFromSession(session);
		List<CartVO> data = cartService.getVOByUid(uid);
		return new JsonResult<>(OK,data);
	}
	
	@RequestMapping("{cid}/num/add")
	public JsonResult<Integer> addNum(HttpSession session,@PathVariable("cid") Integer cid){
		Integer uid = getuidFromSession(session);
		String username = getUsernameFromSession(session);
		Integer data = cartService.addNum(cid, uid, username);
		return new JsonResult<>(OK,data);
	}
	
	@RequestMapping("{cid}/num/minus")
	public JsonResult<Integer> minusNum(HttpSession session,@PathVariable("cid") Integer cid){
		Integer uid = getuidFromSession(session);
		String username = getUsernameFromSession(session);
		Integer data = cartService.minusNum(cid, uid, username);
		return new JsonResult<>(OK,data);
	}
	
	@RequestMapping("list")
	public JsonResult<List<CartVO>> getVOByCid(HttpSession session,Integer[] cids){
		Integer uid = getuidFromSession(session);
		List<CartVO> data = cartService.getVOByCid(uid, cids);
		return new JsonResult<>(OK,data);
	}
}
