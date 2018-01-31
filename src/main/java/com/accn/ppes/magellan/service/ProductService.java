package com.accn.ppes.magellan.service;

import java.util.Collection;
import java.util.List;

import com.accn.ppes.magellan.entity.Product;
import com.accn.ppes.magellan.entity.ProductDTO;
import com.accn.ppes.magellan.entity.Brand; 
import com.accn.ppes.magellan.entity.Category; 

//Comments
public interface ProductService {

	Product getProductById(Long id);

	Product saveProduct(ProductDTO productDTO);

	List<Product> getAllProduct();
	
	List<Category> getAllCategory();
	
	List<Brand> getAllBrand();
	
	Product updateProductbyID(Product commodity);

	String deleteProductbyID(Long id);

	Product updateProductbyName(Product commodity);

	String deleteProductbyName(String name);

	Product getByName(String productName);

	List<Product> getByDescription(String description);

	List<Product> getBySize(String size);

	List<Product> getByCategoryId(Category categoryId);
	
	Category getByCategoryCode(String productCategoryCode);

	List<Category> getByParent(Category parentCategory);

	List<Product> getProductsByParentCategory(String parentCategory);

	List<Product> getByActiveflag(String activeflag);

	List<Product> getByPrice(Double price);

	List<Product> getByDiscount(Double discount);

	List<Product> getByColor(String color);

	List<Product> getByBrandId(Brand brandId);

	Brand getByBrandName(String brandName);
	
	Category saveCategory(Category category);
	
	Brand saveBrand(Brand brand);

}
