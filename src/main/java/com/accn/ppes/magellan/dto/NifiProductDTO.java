package com.accn.ppes.magellan.dto;

/**
 * 
 * @author v.rama.krishnan
 *
 */
public class NifiProductDTO {

	private String productName;
	private String categoryDesc;
	private String productDesc;
	private int level;
	private double price;
	
	public NifiProductDTO() {
		
	}
	public NifiProductDTO(String productName, String categoryDesc, String productDesc, int level, double price) {
		super();
		this.productName = productName;
		this.categoryDesc = categoryDesc;
		this.productDesc = productDesc;
		this.level = level;
		this.price = price;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCategoryDesc() {
		return categoryDesc;
	}

	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
}
