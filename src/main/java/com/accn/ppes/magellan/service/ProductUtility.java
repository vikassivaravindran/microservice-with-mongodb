package com.accn.ppes.magellan.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import com.accn.ppes.magellan.entity.Brand;
import com.accn.ppes.magellan.entity.Category;
import com.accn.ppes.magellan.entity.Product;
import com.accn.ppes.magellan.entity.ProductDTO;
import com.accn.ppes.magellan.messaging.MessagePublisher;
import com.accn.ppes.magellan.repository.BrandRepository;
import com.accn.ppes.magellan.repository.CategoryRepository;
import com.accn.ppes.magellan.repository.ProductRespository;
import com.accn.ppes.magellan.seq.SequenceDao; 

/**
 * Implementation class for all the methods provided in 
 * 		productservice interface
 * @author v.rama.krishnan
 *
 */
@Service
public class ProductUtility implements ProductService {
	
	private static final String HOSTING_SEQ_KEY = "product";
	
	@Autowired
	private MongoOperations mongoOperation;
	
	@Autowired
	private SequenceDao sequenceDao;
	
	@Autowired
	ProductRespository productRespository;
	
	@Autowired
	CategoryRepository categoryRepository;

	
	@Autowired
	BrandRepository brandRepository;
	
	@Autowired(required=false)
	private MessagePublisher messagePublisher;
	
	@Override
	public Product getProductById(Long id) {
		Product productcount = productRespository.findOne(id);

		if (productcount == null)
			throw new com.accn.ppes.magellan.exception.ProductNotFoundException(id);
		else {
			return productcount;
		}
	}
	
	@Override
	public Product saveProduct(ProductDTO productDTO) {
		try {
			
			Brand brand= brandRepository.findByBrandName(productDTO.getBrandName());
			Category cat=categoryRepository.findByCategoryCode(productDTO.getCategoryName());
			Product product=new Product( productDTO.getName(), productDTO.getDescription(), productDTO.getSize(), cat, productDTO.getActiveflag(), productDTO.getPrice(), productDTO.getDiscount(), productDTO.getColor(), brand);
			product.setProductID(sequenceDao.getNextSequenceId(HOSTING_SEQ_KEY));
			Product created = productRespository.save(product);
			
			//Optional.ofNullable(messagePublisher).ifPresent(publisher -> publisher.publishMessage(product));
			return product;
		} catch (Exception e) {
			throw new com.accn.ppes.magellan.exception.ProductNotFoundException(productDTO.getName());
		}
		
	}
	
	@Override
	public String deleteProductbyID(Long id) {
		   try {
			   productRespository.delete(id);	
				} catch (Exception e) {
					throw new com.accn.ppes.magellan.exception.ProductNotFoundException(id);
				}
				return "product deleted!";
	}
	
	@Override
	public Product updateProductbyID(Product product) {
		
		try {
			   productRespository.save(product);	
			   Optional.ofNullable(messagePublisher).ifPresent(publisher -> publisher.publishMessage(product));
		} catch (Exception e) {
					throw new com.accn.ppes.magellan.exception.ProductNotFoundException(product.getProductID());
				}
				return product;
	}

	@Override
	public List<Product> getAllProduct() {
		
		return productRespository.findAll();
	}
	
	@Override
	public List<Category> getAllCategory() {
		
		return categoryRepository.findAll();
	}
	
	@Override
	public List<Brand> getAllBrand() {
		
		return brandRepository.findAll();
	}
	

	@Override
	public Product getByName(String productName) {
		
		return productRespository.findByName(productName);
	}

	@Override
	public List<Product> getByDescription(String description) {
		
		return productRespository.findByDescription(description);
	}

	@Override
	public List<Product> getBySize(String size) {
		
		return productRespository.findBySize(size);
	}

	@Override
	public List<Product> getByCategoryId(Category categoryId) {
		
		return productRespository.findByCategoryCategoryCodeId(categoryId.getCategoryCodeId());
	}
	
	@Override
	public List<Category> getByParent(Category parentCategory) {

		return categoryRepository.findByParent(parentCategory);
	}
	@Override
	public Category getByCategoryCode(String productCategoryCode) {
		
		return categoryRepository.findByCategoryCode(productCategoryCode);
	}
	

	@Override
	public List<Product> getByActiveflag(String activeflag) {
		return productRespository.findByActiveflag(activeflag);
	}

	@Override
	public List<Product> getByPrice(Double price) {
		return productRespository.findByPrice(price);
	}

	@Override
	public List<Product> getByDiscount(Double discount) {
		return productRespository.findByDiscount(discount);
	}

	@Override
	public List<Product> getByColor(String color) {
		
		return productRespository.findByColor(color);
	}

	@Override
	public Product updateProductbyName(Product product) {
		
		try {
			   productRespository.save(product);	
				} catch (Exception e) {
					throw new com.accn.ppes.magellan.exception.ProductNotFoundException(product.getName());
				}
				return product;
		
	}

	@Override
	public String deleteProductbyName(String name) {
		try {
			   Product product = productRespository.findByName(name);
			  productRespository.delete(product);
				} catch (Exception e) {
					throw new com.accn.ppes.magellan.exception.ProductNotFoundException(name);
				}
				return "product deleted!";
	}

	@Override
	public Brand getByBrandName(String brandName) {
		return brandRepository.findByBrandName(brandName);
	}

	@Override
	public List<Product> getByBrandId(Brand brandId) {
		return productRespository.findByBrand(brandId);
	}

	@Override
	public Category saveCategory(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public Brand saveBrand(Brand brand) {
		return brandRepository.save(brand);
	}

	@Override
	public List<Product> getProductsByParentCategory(String parentCategoryCode) {
		Category parentCategory = getByCategoryCode(parentCategoryCode);
		List<Category> subCategories = getByParent(parentCategory);
		List<Product> products = new ArrayList<Product>();
	 		System.out.println("Sub Categories: "+subCategories);
	 		for(Category category : subCategories)
	 		{
	 			System.out.println("Sub Categories product: "+getByCategoryId(category));
	 			products.addAll(getByCategoryId(category));
	 		}
	 		System.out.println("Sub Categories all product: "+products);
		return products;
	}

}
