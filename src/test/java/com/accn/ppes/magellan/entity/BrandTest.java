package com.accn.ppes.magellan.entity;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

public class BrandTest {


    @Test
    public void testBrandWithConstructor(){

        Product product1=new Product();
        product1.setName("IPAD");
        Product product2=new Product();
        product2.setName("IPHONE");
        Product product3=new Product();
        product3.setName("MACBOOK");
        Brand brand=new Brand(10L,"APPLE","APPLE", Arrays.asList(product1,product2,product3).stream().collect(Collectors.toSet()));
        Assert.assertEquals(10L,brand.getBrandId().longValue());
        Assert.assertEquals("APPLE",brand.getBrandName());
        Assert.assertEquals("APPLE",brand.getDescription());


    }

    @Test
    public void testBrandWithGetterAndSetter(){

    	String toStringMessage= "Brand [id=10, name=APPLE, description=APPLE]";
        Product product1=new Product();
        product1.setName("IPAD");
        Product product2=new Product();
        product2.setName("IPHONE");
        Product product3=new Product();
        product3.setName("MACBOOK");
        Brand brand=new Brand();
        brand.setBrandId(10L);
        brand.setBrandName("APPLE");
        brand.setDescription("APPLE");
        brand.setProduct(Arrays.asList(product1,product2,product3).stream().collect(Collectors.toSet()));
       Assert.assertEquals(toStringMessage,brand.toString());
        Assert.assertEquals(3, brand.getProduct().size());
        Assert.assertEquals(10L,brand.getBrandId().longValue());
        Assert.assertEquals("APPLE",brand.getBrandName());
        Assert.assertEquals("APPLE",brand.getDescription());

    }
}
