package com.accn.ppes.magellan.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.accn.ppes.magellan.entity.Category; 

@Document(collection = "product")
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2049669398774154151L;

	/**
	 * 
	 */
	
	@Id
	@Field(value = "product_id")
	private  Long productID;

	@Field(value = "product_name")
	private String name;

	@Field(value = "product_desc")
	private String description;

	@Field(value="size")
	private String size;

	@JsonBackReference("Category")
	private Category category;
	
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	

	@Field
	private String activeflag;

	@Field
	private double price;

	@Field
	private double discount;

	@Field
	private String color;

	@JsonBackReference("Brand")
	private Brand brand;


	public Long getProductID() {
		return productID;
	}
	public void setProductID(Long productID) {
		this.productID = productID;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	
	public Brand getBrand() {
		return brand;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	public Product() {
		super();
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Product(String name, String description, String size, Category category, String activeflag, double price,
			double discount, String color, Brand brand) {
		super();
		this.name = name;
		this.description = description;
		this.size = size;
		this.category = category;
		this.activeflag = activeflag;
		this.price = price;
		this.discount = discount;
		this.color = color;
		this.brand = brand;
	}

	
	
	

}
