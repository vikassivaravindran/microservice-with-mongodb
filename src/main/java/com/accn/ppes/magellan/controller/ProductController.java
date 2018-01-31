package com.accn.ppes.magellan.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.accn.ppes.magellan.entity.Brand;
import com.accn.ppes.magellan.entity.Category;
import com.accn.ppes.magellan.entity.Product;
import com.accn.ppes.magellan.entity.ProductDTO;
import com.accn.ppes.magellan.service.ProductService;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins="*")
public class ProductController {
	
	protected ProductService productservice;
	
	@Autowired
	public ProductController(ProductService productservice) {
		this.productservice = productservice;
	}
	
	@RequestMapping(value = "/product/{productNumber}", method = RequestMethod.GET)
	public @ResponseBody Product byNumber( @PathVariable("productNumber") Long id) {
		Product productcount = productservice.getProductById(id);

		if (productcount == null)
			throw new com.accn.ppes.magellan.exception.ProductNotFoundException(id);
		else {
			return productcount;
		}
	}
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody String index(){
            return "Welcome to APP PES Product API";

    }
    
	@RequestMapping(value="/product", method = RequestMethod.GET)
	public  Collection<Product> getAllproduct(){
		Collection<Product> products = productservice.getAllProduct();
		return products;
		
	}
	
	@CrossOrigin(origins="*")
    @RequestMapping(value="/product", method = RequestMethod.POST)
   public @ResponseBody Product create(@RequestBody ProductDTO product) {
	   
    	Product created = productservice.saveProduct(product);
    	return created;
    }
   
   @RequestMapping(value = "/product/deletebyId/{productNumber}", method = RequestMethod.DELETE)
	public @ResponseBody String deletebyNumber( @PathVariable("productNumber") Long id) {
		String result = productservice.deleteProductbyID(id);

		if (result == null)
			throw new com.accn.ppes.magellan.exception.ProductNotFoundException(id);
		else {
			return result;
		}
	}
   
   
   @RequestMapping(value = "/product/updatebyID", method = RequestMethod.PUT)
   public @ResponseBody Product updatebyID(@RequestBody Product product) {
	    product.setProductID(product.getProductID());
	    Product created = productservice.updateProductbyID(product);
    	return created;
    }
   
   @RequestMapping(value = "/product/deletebyName/{productName}", method = RequestMethod.DELETE)
  	public @ResponseBody String deletebyName( @PathVariable("productName") String name) {
  		String result = productservice.deleteProductbyName(name);

  		if (result == null)
  			throw new com.accn.ppes.magellan.exception.ProductNotFoundException(name);
  		else {
  			return result;
  		}
  	}
     
     
     @RequestMapping(value = "/product/updatebyName", method = RequestMethod.PUT)
     public @ResponseBody Product updatebyName(@RequestBody Product product) {
  	
    	Product entity = productservice.getByName(product.getName());
  	    product.setProductID(entity.getProductID());
        product.setName(entity.getName());
        Product created = productservice.updateProductbyName(product);
        return created;
  	   
      }
     
     
   @RequestMapping(value = "/product/getByName/{productName}", method = RequestMethod.GET)
	public @ResponseBody Product byName( @PathVariable("productName") String name) {
		Product product = productservice.getByName(name);
		return product;
	}
   @RequestMapping(value = "/product/getByDescription/{productDescription}", method = RequestMethod.GET)
  	public @ResponseBody Collection<Product> byDescription( @PathVariable("productDescription") String productDescription) {
  		Collection<Product> product = productservice.getByDescription(productDescription);
  		return product;
  	}
   @RequestMapping(value = "/product/getBySize/{productSize}", method = RequestMethod.GET)
 	public  @ResponseBody Collection<Product> bySize( @PathVariable("productSize") String productSize) {
 		Collection<Product> product = productservice.getBySize(productSize);
 		return product;
 	}
   @RequestMapping(value = "/product/getByCategoryCode/{productCategoryId}", method = RequestMethod.GET)
	public @ResponseBody Collection<Product> byCategoryId( @PathVariable("productCategoryId") Category productCategoryId) {
		Collection<Product> product = productservice.getByCategoryId(productCategoryId);
		return product;
	}
   @RequestMapping(value="/productCatagory", method = RequestMethod.POST)
   public @ResponseBody Category createCatagory(@RequestBody Category productCatagory) {
	   System.out.println(productCatagory.toString());
	   Category created = productservice.saveCategory(productCatagory);
	   return created;
    }
   @RequestMapping(value="/productBrand", method = RequestMethod.POST)
   public @ResponseBody Brand createBrand(@RequestBody Brand productBrand) {
	   System.out.println(productBrand.toString());
	   Brand created = productservice.saveBrand(productBrand);
	   return created;
    }
	@RequestMapping(value="/product/getCategory", method = RequestMethod.GET)
	public  List<Category> getAllCategory(){
		List<Category> categories = productservice.getAllCategory();
		return categories;
	}
	@RequestMapping(value="/product/getBrand", method = RequestMethod.GET)
	public  List<Brand> getAllBrand(){
		List<Brand> brands = productservice.getAllBrand();
		return brands;
	}
   @RequestMapping(value = "/product/getBySubCategoryCode/{productCategoryCode}", method = RequestMethod.GET)
  	public @ResponseBody Collection<Product> byCategoryCode( @PathVariable("productCategoryCode") String productCategoryCode) {
  		Category category = productservice.getByCategoryCode(productCategoryCode);
  		Collection<Product> products = productservice.getByCategoryId(category);
  		return products;
  	}
   @RequestMapping(value = "/product/getByParentCategoryCode/{productCategoryCode}", method = RequestMethod.GET)
 	public @ResponseBody Collection<Product> byParentCode( @PathVariable("productCategoryCode") String productCategoryCode) {
	   
	   Collection<Product> products = productservice.getProductsByParentCategory(productCategoryCode);
		return products;
 		
 	}
 
   
   @RequestMapping(value = "/product/getByActiveflag/{productActiveflag}", method = RequestMethod.GET)
	public @ResponseBody Collection<Product> byActiveflag( @PathVariable("productActiveflag") String productActiveflag) {
		Collection<Product> product = productservice.getByActiveflag(productActiveflag);
		return product;
	}
   @RequestMapping(value = "/product/getByPrice/{productPrice}", method = RequestMethod.GET)
  	public @ResponseBody Collection<Product> byPrice( @PathVariable("productPrice") Double productPrice) {
  		Collection<Product> product = productservice.getByPrice(productPrice);
  		return product;
  	}
   @RequestMapping(value = "/product/getByDiscount/{productDiscount}", method = RequestMethod.GET)
 	public @ResponseBody Collection<Product> byDiscount( @PathVariable("productDiscount") Double productDiscount) {
 		Collection<Product> product = productservice.getByDiscount(productDiscount);
 		return product;
 	}
   @RequestMapping(value = "/product/getByColor/{productColor}", method = RequestMethod.GET)
	public @ResponseBody Collection<Product> byColor( @PathVariable("productColor") String productColor) {
		Collection<Product> product = productservice.getByColor(productColor);
		return product;
	}
   @RequestMapping(value = "/product/getByBrandName/{productBrand}", method = RequestMethod.GET)
	public @ResponseBody Collection<Product> byBrand( @PathVariable("productBrand") String productBrand) {
		Brand brand = productservice.getByBrandName(productBrand);
		Collection<Product> products = productservice.getByBrandId(brand);
		return products;
	}
   
   

}
