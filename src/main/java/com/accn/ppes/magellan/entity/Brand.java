package com.accn.ppes.magellan.entity;

import java.io.Serializable;


import java.util.HashSet;
import java.util.Set;

import javax.annotation.Generated;

import com.accn.ppes.magellan.entity.Product;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Document(collection = "Brand")
public class Brand implements Serializable{
	
	@Id
	@Field(value="brandId")
	private Long brandId;
	
	@Field(value = "brandname")
	private String brandName;
	
	@Field(value="description")
	private String description;
	
	@JsonManagedReference("Brand")
	private Set<Product> product;
	
	
	public Brand(Long brandId, String brandName, String description, Set<Product> product) {
		super();
		this.brandId = brandId;
		this.brandName = brandName;
		this.description = description;
		this.product = product;
	}
	public Brand() {
		
	}
	
	
	public Long getBrandId() {
		return brandId;
	}
	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<Product> getProduct() {
		return product;
	}
	public void setProduct(Set<Product> product) {
		this.product = product;
	}
	@Override
	public String toString() {
		return "Brand [id=" + brandId + ", name=" + brandName + ", description=" + description + "]";
	}
	
}
