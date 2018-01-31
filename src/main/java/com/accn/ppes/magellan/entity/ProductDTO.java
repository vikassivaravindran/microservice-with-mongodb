package com.accn.ppes.magellan.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDTO {
	
	private String name;
	
	private String description;
	
	private String size;
	
	private String brandName;
	
	
	private String categoryName;
	
	
	private String activeflag;

	
	private double price;

	
	private double discount;

	
	private String color;


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getSize() {
		return size;
	}


	public void setSize(String size) {
		this.size = size;
	}


	public String getBrandName() {
		return brandName;
	}


	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}


	public String getCategoryName() {
		return categoryName;
	}


	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}


	public String getActiveflag() {
		return activeflag;
	}


	public void setActiveflag(String activeflag) {
		this.activeflag = activeflag;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public double getDiscount() {
		return discount;
	}


	public void setDiscount(double discount) {
		this.discount = discount;
	}


	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}


	public ProductDTO(String name, String description, String size, String brandName, String categoryName,
			String activeflag, double price, double discount, String color) {
		super();
		this.name = name;
		this.description = description;
		this.size = size;
		this.brandName = brandName;
		this.categoryName = categoryName;
		this.activeflag = activeflag;
		this.price = price;
		this.discount = discount;
		this.color = color;
	}


	public ProductDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
