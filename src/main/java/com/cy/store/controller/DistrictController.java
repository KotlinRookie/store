package com.cy.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cy.store.entity.District;
import com.cy.store.service.IDistrictService;
import com.cy.store.util.JsonResult;

@RestController
@RequestMapping("districts")
public class DistrictController extends BaseController{

	@Autowired
	private IDistrictService districtService;
	
	// districts开头的请求都会被拦截到getByParent()方法中
	@RequestMapping({"/",""})
	public JsonResult<List<District>> getByParent(String parent){
		List<District> data = districtService.getByParent(parent);
		return new JsonResult<>(OK,data);
	}
}
