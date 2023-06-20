package com.cy.store.util;

import java.io.Serializable;

/**
 * Json格式的数据进行响应
 * 想要响应JSON格式的数据，需要实现 Serializable 接口
 * @author BP-changxiaolong
 *
 */
public class JsonResult<E> implements Serializable {
	/**
	 * 状态码
	 */
	private Integer state;
	/**
	 * 描述信息
	 */
	private String message;
	/**
	 * 数据
	 * 因为查询的数据不确定类型，所以用泛型表示：E
	 * 一个类中有泛型的数据类型声明时，需要把这个类的类型也声明成泛型
	 */
	private E data;
	
	public JsonResult() {}

	public JsonResult(Integer state) {
		this.state = state;
	}
	
	public JsonResult(Integer state, String message) {
		this.state = state;
		this.message = message;
	}

	public JsonResult(Integer state, E data) {
		this.state = state;
		this.data = data;
	}
	
	public JsonResult(Integer state, String message, E data) {
		this.state = state;
		this.message = message;
		this.data = data;
	}

	//异常的捕获
	public JsonResult(Throwable e) {
		this.message = e.getMessage();
	}


	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public E getData() {
		return data;
	}

	public void setData(E data) {
		this.data = data;
	}
}
