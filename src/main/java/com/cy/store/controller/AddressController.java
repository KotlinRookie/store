package com.cy.store.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cy.store.entity.Address;
import com.cy.store.service.IAddressService;
import com.cy.store.util.JsonResult;

@RestController
@RequestMapping("addresses")
public class AddressController extends BaseController {

	@Autowired
	private IAddressService addressService;

	@RequestMapping("add_new_address")
	public JsonResult<Void> addNewAddress(Address address,HttpSession session){
		Integer uid = getuidFromSession(session);
		String username = getUsernameFromSession(session);
		addressService.addNewAddress(uid, username, address);
		return new JsonResult<>(OK);
	}
	
	@RequestMapping({"/",""})
	public JsonResult<List<Address>> getByUid(HttpSession session){
		Integer uid = getuidFromSession(session);
		List<Address> data = addressService.getByUid(uid);
		return new JsonResult<>(OK,data);
	}
	
	// RestFul风格的请求编写
	@RequestMapping("{aid}/set_default")
	public JsonResult<Void> setDefault(@PathVariable("aid") Integer aid,
									   HttpSession session ){
		Integer uid = getuidFromSession(session);
		String username = getUsernameFromSession(session);
		addressService.setDefault(aid, uid, username);
		return new JsonResult<>(OK);
	}
	
	@RequestMapping("{aid}/delete")
	public JsonResult<Void> delete(@PathVariable("aid") Integer aid,
									HttpSession session){
		Integer uid = getuidFromSession(session);
		String username = getUsernameFromSession(session);
		addressService.delete(aid, uid, username);
		return new JsonResult<>(OK);
	}
	
	
	 @RequestMapping("{aid}/find_by_aid") 
	 public JsonResult<Address> findByAid(@PathVariable("aid") Integer aid, HttpSession session){ 
		 Integer uid = getuidFromSession(session); 
		 Address data = addressService.findByAid(aid,uid); 
		 return new JsonResult<Address>(OK,data); 
	}

}
