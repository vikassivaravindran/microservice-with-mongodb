package com.accn.ppes.magellan.repository;


import java.util.Collection;


import java.util.List;

//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.accn.ppes.magellan.entity.Category; 
public interface CategoryRepository extends MongoRepository<Category,	Long> {
	
	Category findByCategoryCode(String categoryName);
	List<Category> findByParent(Category categoryName);
	Category save(Category productCatagory);
	List<Category> findAll();

}
