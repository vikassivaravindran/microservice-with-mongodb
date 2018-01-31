package com.accn.ppes.magellan.entity;

import org.junit.Assert;
import org.junit.Test;

public class ProductDTOTest {

	@Test
	public void testProductDTOWithConstructor() {

		ProductDTO productDTO = new ProductDTO("TV", "TELEVISION", "SIZE-32", "LG", "ELECTRONICS", "true", 10000.00, 10,
				"GREY");
		Assert.assertEquals("TV", productDTO.getName());
		Assert.assertEquals("TELEVISION", productDTO.getDescription());
		Assert.assertEquals("LG", productDTO.getBrandName());
		Assert.assertEquals("ELECTRONICS", productDTO.getCategoryName());
		Assert.assertEquals("SIZE-32", productDTO.getSize());
		Assert.assertEquals("GREY", productDTO.getColor());

	}
}
