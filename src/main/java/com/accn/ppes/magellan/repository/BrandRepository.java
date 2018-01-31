package com.accn.ppes.magellan.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.accn.ppes.magellan.entity.Brand; 

public interface BrandRepository extends MongoRepository<Brand, Long> {
	
	Brand findByBrandName(String brandName);

}
