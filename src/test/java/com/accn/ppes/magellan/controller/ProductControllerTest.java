package com.accn.ppes.magellan.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.accn.ppes.magellan.AbstractTest;
import com.accn.ppes.magellan.entity.Product;
import com.accn.ppes.magellan.entity.ProductDTO;
import com.accn.ppes.magellan.exception.ProductNotFoundException;
import com.accn.ppes.magellan.repository.BrandRepository;
import com.accn.ppes.magellan.repository.CategoryRepository;
import com.accn.ppes.magellan.repository.ProductRespository;
import com.accn.ppes.magellan.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Test class for testing ProductController API (REST) endpoints!
 * 
 * @author v.rama.krishnan
 *
 */
public class ProductControllerTest extends AbstractTest {

	private static final String BASE_PATH = "/api";

	private static final String GET_PRODUCT_BY_ID = BASE_PATH.concat("/product/{productNumber}");
	private static final String GET_PRODUCT_BY_NAME = BASE_PATH.concat("/product/getByName/{productName}");
	private static final String GET_PRODUCT_BY_DESCRIPTION = BASE_PATH.concat("/product/getByDescription/{productDescription}");
	private static final String GET_PRODUCT_BY_SIZE = BASE_PATH.concat("/product/getBySize/{productSize}");
	private static final String GET_PRODUCT_BY_PARENT_CODE = BASE_PATH.concat("/product/getByParentCategoryCode/{productCategoryCode}");
	private static final String GET_PRODUCT_BY_DISCOUNT = BASE_PATH.concat("/product/getByDiscount/{productDiscount}");
	private static final String GET_PRODUCT_BY_COLOR = BASE_PATH.concat("/product/getByColor/{productColor}");
	private static final String GET_PRODUCT_BY_BRAND = BASE_PATH.concat("/product/getByBrandName/{productBrand}");
	private static final String GET_PRODUCT_BY_PRICE = BASE_PATH.concat("/product/getByPrice/{productPrice}");
	private static final String GET_PRODUCT_BY_ACTIVE_FLAG = BASE_PATH.concat("/product/getByActiveflag/{productActiveflag}");
	private static final String GET_PRODUCT_BY_CATEGORY_ID = BASE_PATH.concat("/product/getByCategoryCode/{productCategoryId}");
	private static final String GET_ALL_PRODUCTS = BASE_PATH.concat("/product");
	private static final String GET_PRODUCTS_BY_BRAND = BASE_PATH.concat("/product/getByBrandName/{productBrand}");
	private static final String GET_PRODUCTS_BY_CATEGORYCODE = BASE_PATH.concat("/product/getBySubCategoryCode/{productCategoryCode}");

	private static final String CREATE_PRODUCT = BASE_PATH.concat("/product");

	private static final String DELETE_PRODUCT_BY_ID = BASE_PATH.concat("/product/deletebyId/{productNumber}");
	private static final String DELETE_PRODUCT_BY_NAME = BASE_PATH.concat("/product/deletebyName/{productName}");

	private static final String UPDATE_PRODUCT_BY_ID = BASE_PATH.concat("/product/updatebyID");
	private static final String UPDATE_PRODUCT_BY_NAME = BASE_PATH.concat("/product/updatebyName");

	private static final ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ProductRespository productRepo;

	@Autowired
	private ProductService productService;

	@Autowired
	private BrandRepository brandRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Before
	public void setupProducts() {
		productRepo.deleteAll();
		categoryRepository.deleteAll();
		brandRepository.deleteAll();
		getBrands().forEach(brandRepository::save);
		getCategories().forEach(categoryRepository::save);
	}

	@Test
	public void testCreateProduct() throws Exception {

		ProductDTO productDTO = getProducts().stream().findFirst().get();
		this.mockMvc
				.perform(post(CREATE_PRODUCT).contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(mapper.writeValueAsString(productDTO)))
				.andExpect(status().isOk()).andExpect(jsonPath("$.name").value("LAPTOP"));
	}

	@Test
	public void testGetProductByProductId() throws Exception {

		Product product = productService.saveProduct(getProducts().get(0));
		System.out.println("Product " + product.getProductID());
		this.mockMvc.perform(get(GET_PRODUCT_BY_ID, product.getProductID())).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.name", is("LAPTOP"))).andExpect(jsonPath("$.size", is("4")))
				.andExpect(jsonPath("$.color", is("BLUE")));

	}

	@Test
	public void testByName() throws Exception {

		Product product = productService.saveProduct(getProducts().get(0));
		this.mockMvc.perform(get(GET_PRODUCT_BY_NAME, product.getName())).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.name", is("LAPTOP"))).andExpect(jsonPath("$.size", is("4")))
				.andExpect(jsonPath("$.color", is("BLUE")));
	}

	@Test
	public void testByDescription() throws Exception {

		Product product = productService.saveProduct(getProducts().get(0));
		this.mockMvc.perform(get(GET_PRODUCT_BY_DESCRIPTION, product.getDescription())).andDo(print())
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$[0].name", is("LAPTOP"))).andExpect(jsonPath("$[0].size", is("4")));
	}

	@Test
	public void testBySize() throws Exception {

		Product product = productService.saveProduct(getProducts().get(1));
		this.mockMvc.perform(get(GET_PRODUCT_BY_SIZE, product.getSize())).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$[0].name", is("MONTEX")))
				.andExpect(jsonPath("$[0].description", is("WRITING PEN")));
	}

	@Test
	public void testByActiveFlag() throws Exception {

		Product product = productService.saveProduct(getProducts().get(1));
		this.mockMvc.perform(get(GET_PRODUCT_BY_ACTIVE_FLAG, product.getActiveflag())).andDo(print())
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$[0].name", is("MONTEX")))
				.andExpect(jsonPath("$[0].description", is("WRITING PEN")));
	}

	@Test
	public void testByPrice() throws Exception {

		Product product = productService.saveProduct(getProducts().get(1));
		this.mockMvc.perform(get(GET_PRODUCT_BY_PRICE, product.getPrice())).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$[0].name", is("MONTEX")))
				.andExpect(jsonPath("$[0].description", is("WRITING PEN")));
	}

	@Test
	public void testByDiscount() throws Exception {

		Product product = productService.saveProduct(getProducts().get(1));
		this.mockMvc.perform(get(GET_PRODUCT_BY_DISCOUNT, product.getDiscount())).andDo(print())
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$[0].name", is("MONTEX")))
				.andExpect(jsonPath("$[0].description", is("WRITING PEN")));
	}

	@Test
	public void testByColor() throws Exception {

		Product product = productService.saveProduct(getProducts().get(1));
		this.mockMvc.perform(get(GET_PRODUCT_BY_COLOR, product.getColor())).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$[0].name", is("MONTEX")))
				.andExpect(jsonPath("$[0].description", is("WRITING PEN")));
	}

/*	@Test
	@Ignore
	public void testByBrand() throws Exception {

		Product product = productService.saveProduct(getProducts().get(1));
		System.out.println("Brand:::" + product.getBrand());
		String brand = product.getBrand().getBrandName();
		System.out.println("BRand:" + brand);
		this.mockMvc.perform(get(GET_PRODUCT_BY_BRAND, brand)).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$[0].name", is("MONTEX")))
				.andExpect(jsonPath("$[0].description", is("WRITING PEN")));
	}

	@Test
	@Ignore
	public void testByParentCode() throws Exception {

		Product product = productService.saveProduct(getProducts().get(1));
		this.mockMvc.perform(get(GET_PRODUCT_BY_PARENT_CODE, product.getCategory().getParent())).andDo(print())
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$[0].name", is("MONTEX")))
				.andExpect(jsonPath("$[0].description", is("WRITING PEN")));
	}

	@Test
	@Ignore
	public void testByCategoryId() throws Exception {

		Product product = productService.saveProduct(getProducts().get(1));
		this.mockMvc.perform(get(GET_PRODUCT_BY_CATEGORY_ID, product.getCategory())).andDo(print())
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.name", is("MONTEX"))).andExpect(jsonPath("$.description", is("WRITING PEN")));
	}

	@Test(expected = ProductNotFoundException.class)
	@Ignore
	public void testGetProductByProductIdExeption() throws Exception {

		Product product = new Product();
		this.mockMvc.perform(get(GET_PRODUCT_BY_ID, product.getProductID())).andDo(print()).andExpect(status().isOk());

	}*/

	@Test
	@Ignore
	public void testUpdatebyId() throws Exception {

		Product product = productService.saveProduct(getProducts().get(1));
		product.setName("MONTEX NEW");;
		this.mockMvc.perform(put(UPDATE_PRODUCT_BY_ID).contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(mapper.writeValueAsString(product))).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.name", is("MONTEX NEW")));
	}
	
	@Test
	@Ignore
	public void testUpdatebyName() throws Exception {

		Product product = productService.saveProduct(getProducts().get(1));
		product.setActiveflag("no");
	
		this.mockMvc.perform(put(UPDATE_PRODUCT_BY_NAME) .contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(mapper.writeValueAsString(product))).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.activeflag", is("no")));
	}

	@Test
	public void testDeletebyNumber() throws Exception {

		Product product = productService.saveProduct(getProducts().get(0));
		this.mockMvc.perform(delete(DELETE_PRODUCT_BY_ID, product.getProductID())).andDo(print())
				.andExpect(status().isOk()).andExpect(content().string("product deleted!"));

	}

	/*@Test(expected = ProductNotFoundException.class)
	@Ignore
	public void testDeletebyNumberExeption() throws Exception {

		Product product = new Product();
		product.setProductID(1L);
		this.mockMvc.perform(get(DELETE_PRODUCT_BY_ID, product.getProductID()));

	}*/

	@Test
	public void testDeletebyName() throws Exception {

		Product product = productService.saveProduct(getProducts().get(0));
		this.mockMvc.perform(delete(DELETE_PRODUCT_BY_NAME, product.getName())).andDo(print())
				.andExpect(status().isOk()).andExpect(content().string("product deleted!"));

	}

	/*@Test(expected = ProductNotFoundException.class)
	@Ignore
	public void testDeletebyNameExeption() throws Exception {

		Product product = new Product();
		product.setName("VIVO 5 Plus");
		this.mockMvc.perform(get(DELETE_PRODUCT_BY_NAME, product.getName()));

	}*/

	@Test
	public void testByCategoryCode() throws Exception {

		Product product = productService.saveProduct(getProducts().get(0));
		Product secondProduct = productService.saveProduct(getProducts().get(1));

		this.mockMvc.perform(get(GET_PRODUCTS_BY_CATEGORYCODE, secondProduct.getCategory().getCategoryCode()))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$[0].name", is("MONTEX"))).andExpect(jsonPath("$[0].size", is("4")));

	}
	
	@Test
	public void testGetAllProduct() throws Exception {

		Product product = productService.saveProduct(getProducts().get(0));
		Product secondProduct = productService.saveProduct(getProducts().get(1));

		this.mockMvc.perform(get(GET_ALL_PRODUCTS))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$[0].name", is("LAPTOP"))).andExpect(jsonPath("$[1].name", is("MONTEX")));

	}
	
	

	@Test
	public void testIndexPath() throws Exception {
		this.mockMvc.perform(get(BASE_PATH)).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string("Welcome to APP PES Product API"));
	}

}