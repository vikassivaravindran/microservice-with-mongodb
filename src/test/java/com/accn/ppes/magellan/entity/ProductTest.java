package com.accn.ppes.magellan.entity;

import org.junit.Assert;
import org.junit.Test;

import com.accn.ppes.magellan.AbstractTest;
import com.accn.ppes.magellan.entity.Product;

public class ProductTest extends AbstractTest{

	@Test
	public void testProduct() throws Exception {
		
		Product product = new Product("ASUS Selfie 3GB","With 13MP cameras","6.5",getCategories().get(0),"Yes",14000.0,2.5,"Black",getBrands().get(0));
		Product secondProduct = new Product();
		secondProduct.setName("ASUS Selfie 3GB");
		secondProduct.setDescription("With 13MP cameras");
		secondProduct.setSize("6.5");
		secondProduct.setCategory(getCategories().get(0));
		secondProduct.setActiveflag("Yes");
		secondProduct.setPrice(14000.0);
		secondProduct.setDiscount(2.5);
		secondProduct.setColor("Black");
		secondProduct.setBrand(getBrands().get(0));
		
		Long SerialID = product.getSerialversionuid();
		Brand brand = product.getBrand();
		
		Assert.assertEquals(product.getName(),secondProduct.getName());
		
	}
	

}
