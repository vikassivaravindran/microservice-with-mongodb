package com.accn.ppes.magellan.entity;

import java.io.Serializable;


import java.util.HashSet;
import java.util.Set;

import com.accn.ppes.magellan.entity.Product;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Document(collection = "category")
public class Category implements Serializable{
	
	@Id
	@Field(value="categoryCodeId")
	private Long categoryCodeId;
	
	@Field(value = "categorycode")
	private String categoryCode;
	
	@Field(value="description")
	private String description;
	
	
	private Category parent;
	
	private Set<Category> subCategories;
	
	@JsonManagedReference("Category")
	private Set<Product> product ;
	
	

	public Category() {
	}

	public Category(String categoryCode, String description, Category parent, Set<Category> subCategories,
			Set<Product> product) {
		super();		
		this.categoryCode = categoryCode;
		this.description = description;
		this.parent = parent;
		this.subCategories = subCategories;
		this.product = product;
	}	
	

	public Category(String categoryCode, String description) {
		super();
		this.categoryCode = categoryCode;
		this.description = description;
	}

	public Long getCategoryCodeId() {
		return categoryCodeId;
	}

	public void setCategoryCodeId(Long categoryCodeId) {
		this.categoryCodeId = categoryCodeId;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Category getParent() {
		return parent;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}

	public Set<Category> getSubCategories() {
		return subCategories;
	}

	public void setSubCategories(Set<Category> subCategories) {
		this.subCategories = subCategories;
	}

	public Set<Product> getProduct() {
		return product;
	}

	public void setProduct(Set<Product> product) {
		this.product = product;
	}


	
	

}
