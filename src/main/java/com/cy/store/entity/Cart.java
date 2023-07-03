package com.cy.store.entity;

import java.util.Objects;

/**
 * 购物车的实体类
 * @author BP-changxiaolong
 *
 */
public class Cart extends BaseEntity{
	private Integer cid;
	private Integer uid;
	private Integer pid;
	private Long price;
	private Integer num;
	
	/**
	 * @return the cid
	 */
	public Integer getCid() {
		return cid;
	}
	/**
	 * @param cid the cid to set
	 */
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	/**
	 * @return the uid
	 */
	public Integer getUid() {
		return uid;
	}
	/**
	 * @param uid the uid to set
	 */
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	/**
	 * @return the pid
	 */
	public Integer getPid() {
		return pid;
	}
	/**
	 * @param pid the pid to set
	 */
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	/**
	 * @return the price
	 */
	public Long getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(Long price) {
		this.price = price;
	}
	/**
	 * @return the num
	 */
	public Integer getNum() {
		return num;
	}
	/**
	 * @param num the num to set
	 */
	public void setNum(Integer num) {
		this.num = num;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(cid, num, pid, price, uid);
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cart other = (Cart) obj;
		return Objects.equals(cid, other.cid) && Objects.equals(num, other.num) && Objects.equals(pid, other.pid)
				&& Objects.equals(price, other.price) && Objects.equals(uid, other.uid);
	}
	
	@Override
	public String toString() {
		return "Cart [cid=" + cid + ", uid=" + uid + ", pid=" + pid + ", price=" + price + ", num=" + num + "]";
	}
	
}
