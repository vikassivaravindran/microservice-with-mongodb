package com.accn.ppes.magellan.repository;




//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.Repository;


import com.accn.ppes.magellan.entity.Product; 
import com.accn.ppes.magellan.entity.Brand; 
import com.accn.ppes.magellan.entity.Category;

import java.util.List;


public interface ProductRespository extends MongoRepository<Product, Long> {

	Product findByName(String productName);
	List<Product> findByDescription(String description);
	List<Product> findBySize(String size);
	List<Product> findByCategoryCategoryCodeId(Long categoryCodeId);
	List<Product> findByActiveflag(String activeflag);
	List<Product> findByPrice(Double price);
	List<Product> findByDiscount(Double discount);
	List<Product> findByColor(String color);
	List<Product> findByBrand(Brand brandName);
}