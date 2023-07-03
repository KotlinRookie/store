package com.cy.store.vo;

import java.io.Serializable;
import java.util.Objects;



/**
 * 购物车数据的VO类(Value Object)
 * 多表查询实体类
 * @author BP-changxiaolong
 *
 */
public class CartVO implements Serializable {
	private Integer cid;
	private Integer uid;
	private Integer pid;
	private Long price;
	private Integer num;
	private String title;
	private String image;
	private Long realPrice;
	
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
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}
	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}
	/**
	 * @return the realPrice
	 */
	public Long getRealPrice() {
		return realPrice;
	}
	/**
	 * @param realPrice the realPrice to set
	 */
	public void setRealPrice(Long realPrice) {
		this.realPrice = realPrice;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(cid, image, num, pid, price, realPrice, title, uid);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartVO other = (CartVO) obj;
		return Objects.equals(cid, other.cid) && Objects.equals(image, other.image) && Objects.equals(num, other.num)
				&& Objects.equals(pid, other.pid) && Objects.equals(price, other.price)
				&& Objects.equals(realPrice, other.realPrice) && Objects.equals(title, other.title)
				&& Objects.equals(uid, other.uid);
	}
	
	@Override
	public String toString() {
		return "CartVO [cid=" + cid + ", uid=" + uid + ", pid=" + pid + ", price=" + price + ", num=" + num + ", title="
				+ title + ", image=" + image + ", realPrice=" + realPrice + "]";
	}
	
}
