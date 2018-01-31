package com.accn.ppes.magellan.entity;

import org.junit.Assert;
import org.junit.Test;

import com.accn.ppes.magellan.dto.NifiProductDTO;

public class NifiProductDTOTest {

	@Test
	public void testNifiProductDTOWithConstructor() {

		NifiProductDTO nifiProductDTO = new NifiProductDTO("iPhone 7","High end Phones","2016 Release",4,55000.0);
		Assert.assertEquals("iPhone 7", nifiProductDTO.getProductName());
		Assert.assertEquals("High end Phones", nifiProductDTO.getCategoryDesc());
		Assert.assertEquals("2016 Release", nifiProductDTO.getProductDesc());
		Assert.assertEquals(4, nifiProductDTO.getLevel());
		Assert.assertEquals(55000.0, nifiProductDTO.getPrice(),0);

	}
}
