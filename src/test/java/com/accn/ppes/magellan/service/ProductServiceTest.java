package com.accn.ppes.magellan.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.accn.ppes.magellan.AbstractTest;
import com.accn.ppes.magellan.exception.ProductNotFoundException;
import com.accn.ppes.magellan.repository.BrandRepository;
import com.accn.ppes.magellan.repository.CategoryRepository;
import com.accn.ppes.magellan.repository.ProductRespository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.accn.ppes.magellan.entity.Product;
import com.accn.ppes.magellan.entity.ProductDTO;
import com.accn.ppes.magellan.entity.Brand; 
import com.accn.ppes.magellan.entity.Category;


public class ProductServiceTest extends AbstractTest {

	@Autowired
	private ProductService productService;

	@Autowired
	private BrandRepository brandRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductRespository productRespository;



	@Before
	public void setup(){

		categoryRepository.deleteAll();
		brandRepository.deleteAll();
		productRespository.deleteAll();
		getBrands().forEach(brandRepository::save);
		getCategories().forEach(categoryRepository::save);
		getProducts().forEach(productService::saveProduct);
	}



	@Test
	public void testSaveProduct() {
        ProductDTO productDTO=new ProductDTO("IPHONEX", "APPLE PHONE", "4", getBrands().get(0).getBrandName(), getCategories().get(0).getCategoryCode(), "true", 40000.0, 0, "BLUE");

		Product product=productService.saveProduct(productDTO);
		Assert.assertEquals("IPHONEX",product.getName());

	}

	@Test
	public void testFindAllProducts() {

		Collection<Product> list = productService.getAllProduct();

		Assert.assertNotNull("failure - expected not null", list);
		Assert.assertEquals("failure - expected list size", 2, list.size());

	}

	@Test
	public void testFindOneByProductId() {

		Product product = productService.getAllProduct().stream().findFirst().get();
		Product entity = productService.getProductById(product.getProductID());
		Assert.assertNotNull("failure - expected not null", entity);
		Assert.assertEquals("failure - expected id attribute match", product.getProductID(), entity.getProductID());

	}
	
	@Test
	public void testFindByCategory()
	{
		
		Category category = productService.getByCategoryCode("ELE");
  		Assert.assertNotNull("failure - expected not null", category);
		Assert.assertEquals("failure - expected list size", "ELE", category.getCategoryCode());

		
	}
	
	@Test
	public void testFindByParentCategory()
	{
		
		
  		Collection<Product> products = productService.getProductsByParentCategory("APP");  		
  		Assert.assertNotNull("failure - expected not null", products);		

		
	}
	
	@Test
	public void testUpdateProductByID() {

		Product product = productService.getAllProduct().get(1);
		Product entity = productService.getProductById(product.getProductID());
		Assert.assertNotNull("failure - expected not null", entity);

		String updatedText = "PARKER";
		entity.setName(updatedText);
		Product updatedEntity = productService.updateProductbyID(entity);

		Assert.assertNotNull("failure - expected not null", updatedEntity);
		Assert.assertEquals("failure - expected id attribute match", product.getProductID(), updatedEntity.getProductID());
		Assert.assertEquals("failure - expected text attribute match", updatedText, updatedEntity.getName());

	}
	
	@Test
	public void testUpdateProductByName() {

		Product product = productService.getAllProduct().get(0);
		Product entity = productService.getByName(product.getName());
		Assert.assertNotNull("failure - expected not null", entity);

		double newDiscount = 5.0;
		entity.setDiscount(newDiscount);
		Product updatedEntity = productService.updateProductbyName(entity);

		Assert.assertNotNull("failure - expected not null", updatedEntity);
		Assert.assertEquals("failure - expected id attribute match", product.getProductID(), updatedEntity.getProductID());
		Assert.assertEquals("failure - expected discount attribute match", newDiscount, updatedEntity.getDiscount(),0);

	}

	@Test
	public void testDeleteProductById() {



		Product product= productService.getAllProduct().stream().findFirst().get();
		Product entity = productService.getProductById(product.getProductID());
		Assert.assertNotNull("failure - expected not null", entity);
		productService.deleteProductbyID(product.getProductID());
		Collection<Product> list = productService.getAllProduct();
		Assert.assertEquals("failure - expected size", 1, list.size());

	}

	@Test
	public void testSaveBrand(){

		Brand brand=new Brand();
		brand.setBrandName("PENDRIVE");
		brand.setDescription("PENDRIVE BY HP");
		Brand savedBrand=productService.saveBrand(brand);
		Assert.assertEquals(brand.getBrandName(),savedBrand.getBrandName());
	}

	@Test
	public void testSaveCategory(){

		Category category=productService.saveCategory(new Category("SPORTS","SPORTS ACCESSCORIES"));
		Assert.assertEquals("SPORTS",category.getCategoryCode());
	}

	@Test
	public void testFindProductByName(){

		Product product=productService.getByName("LAPTOP");
		Assert.assertEquals("LAPTOP",product.getName());

	}

	@Test
	public void testFindProductsByDescription(){
		List<Product> products=productService.getByDescription("APPLE LAPTOP");
		Assert.assertEquals("APPLE LAPTOP",products.stream().findFirst().get().getDescription());
	}

	@Test
	public void testFindProductsBySize(){
		List<Product> products=productService.getBySize("4");
		Assert.assertEquals("4",products.stream().findFirst().get().getSize());
	}

	@Test
	public void testFindProductsByActiveFlag(){
		List<Product> products=productService.getByActiveflag("true");
		Assert.assertEquals("true",products.stream().findFirst().get().getActiveflag());
	}

	@Test
	public void testFindProductsByColor(){
		List<Product> products=productService.getByColor("BLACK");
		for (Product product : products) {
			Assert.assertEquals("BLACK",product.getColor());
		}
	}

	@Test
	public void testFindProductsByPrice(){
		List<Product> products=productService.getByPrice(40000.0);
		for (Product product : products) {
			Assert.assertEquals(40000.0,product.getPrice(),0.001);
		}
	}

	@Test
	public void testFindProductsByDiscount(){
		List<Product> products=productService.getByDiscount(10.0);
		for (Product product : products) {
			Assert.assertEquals(10.0,product.getDiscount(),0.001);
		}
	}

	@Test
	public void testFindBrandById(){

		Brand brand = brandRepository.findByBrandName(getBrands().get(0).getBrandName());
		List<Product> product=productService.getByBrandId(brand);
		Assert.assertEquals(1,product.size());

	}

	@Test
	public void testAllCategories(){

		List<Category> categories=productService.getAllCategory();
		Assert.assertEquals(2,categories.size());
	}

	@Test
	public void testAllProducts(){

		List<Product> products=productService.getAllProduct();
		Assert.assertEquals(2,products.size());
	}

	@Test
	public void testAllBrands(){

		List<Brand> brands=productService.getAllBrand();
		Assert.assertEquals(2,brands.size());
	}



	@Test(expected = ProductNotFoundException.class)
	public  void testProductNotFoundException(){
		productService.deleteProductbyID(100L);
	}

	@Test(expected = ProductNotFoundException.class)
	public  void testProductNotFoundExceptionByName(){
		productService.deleteProductbyName("TEST");
	}



	}
