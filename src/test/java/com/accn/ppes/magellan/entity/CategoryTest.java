package com.accn.ppes.magellan.entity;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;


public class CategoryTest {
	
	@Test
	public void testGetterAndSetter() {
		
		Category parent = new Category("SHIPPABLE","All shippable products");
		Category child = new Category("PHONES","All type of phones");
		Category child2 = new Category("HOME_NEEDS","Home Appliances");
		
		 Product product1=new Product();
         product1.setName("IPAD");
         Product product2=new Product();
         product2.setName("IPHONE");
         Product product3=new Product();
         product3.setName("MACBOOK");

       	Category category = new Category("ELECTONICS", "Electornic goods");
		category.setProduct(Arrays.asList(product1,product2,product3).stream().collect(Collectors.toSet()));
		category.setParent(parent);
		category.setSubCategories(Arrays.asList(child,child2).stream().collect(Collectors.toSet()));
		
		Category anotherCategory = new Category("ELECTONICS_NEW","Electornic goods",category.getParent(),category.getSubCategories(),category.getProduct());
		anotherCategory.setCategoryCodeId(101L);
		Assert.assertEquals(category.getDescription(), anotherCategory.getDescription());
	}
	
}
