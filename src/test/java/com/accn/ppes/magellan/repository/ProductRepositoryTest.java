package com.accn.ppes.magellan.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import com.accn.ppes.magellan.AbstractTest;
import com.accn.ppes.magellan.ProductApplication;
import com.accn.ppes.magellan.exception.ProductNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.accn.ppes.magellan.entity.Brand;
import com.accn.ppes.magellan.entity.Category;
import com.accn.ppes.magellan.entity.Product;
import com.accn.ppes.magellan.repository.ProductRespository;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Test cases for Repository class(ProductRepository) methods
 * @author v.rama.krishnan
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductApplication.class)
@DataJpaTest
public class ProductRepositoryTest{

	@Autowired
	private ProductRespository productRepo;

	@Autowired
	private BrandRepository brandRepository;

	@Autowired
	private CategoryRepository categoryRepository;
	


	@Before
	public void setupProducts() {

		productRepo.deleteAll();
		categoryRepository.deleteAll();
		brandRepository.deleteAll();
		Category category = new Category("ELE","Electronic devices");
		Brand brand = new Brand(1001L,"ASUS","ASUS Mobiles",null);
		Brand savedBrand=brandRepository.save(brand);
		Category savedCategory=categoryRepository.save(category);
		Product productOne = new Product("ASUS Selfie 3GB", "For selfie lovers", "6.5", savedCategory, "YES", 15000.00, 2.0, "BLACK", savedBrand);
		Product productTwo = new Product("ASUS Max 3GB", "For Long lasting ", "5", savedCategory, "NO", 13500.00, 0.0, "GREY", savedBrand);

		productRepo.save(productOne);
		productRepo.save(productTwo);
	}
	
	@Test
	public void testSaveProduct() {
		
		Product product=productRepo.save(new Product("ASUS LAPTOP", "LAPTOP HIGHEND ", "13.5", categoryRepository.findAll().get(0), "NO", 35000.00, 0.0, "SILVER", brandRepository.findAll().get(0)));
		Assert.assertEquals("ASUS LAPTOP",product.getName());
	}
	
	@Test
	public void testFindByProductName() {

		Product product=productRepo.findByName("ASUS Max 3GB");
		Assert.assertEquals("ASUS Max 3GB",product.getName());

	}
	
	@Test
	public void testFindByBrandName() {

		Brand brand = brandRepository.findByBrandName("ASUS");
		List<Product> product=productRepo.findByBrand(brand);
		Assert.assertEquals(2,product.size());

	}
	


}
