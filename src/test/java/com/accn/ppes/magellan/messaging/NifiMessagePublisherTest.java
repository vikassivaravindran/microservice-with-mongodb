package com.accn.ppes.magellan.messaging;


import com.accn.ppes.magellan.AbstractTest;
import com.accn.ppes.magellan.entity.Brand;
import com.accn.ppes.magellan.entity.Category;
import com.accn.ppes.magellan.entity.Product;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class NifiMessagePublisherTest extends AbstractTest {

    @MockBean
    private RestTemplate restTemplate;

    private  NifiMessagePublisher publisher=new NifiMessagePublisher();


    @Test
    public void testPublishMessageSuccess(){

        ResponseEntity<String> responseEntity=new ResponseEntity<String>(HttpStatus.OK);
        Mockito.when(restTemplate.exchange(Matchers.eq("/nifi/test"),Matchers.eq(HttpMethod.POST),Matchers.<HttpEntity<Object>>any(),Matchers.<Class<String>> any())).thenReturn(responseEntity);
        Product product=getProduct();
        publisher.setNifiServerUrl("/nifi/test");
        publisher.setRestTemplate(restTemplate);
        publisher.publishMessage(product);
        publisher.getRestTemplate();
        publisher.getNifiServerUrl();
        Mockito.verify(restTemplate,Mockito.times(1)).exchange(Matchers.eq("/nifi/test"),Matchers.eq(HttpMethod.POST),Matchers.<HttpEntity<Object>>any(),Matchers.<Class<String>> any());

    }

    @Test
    public void testPublishMessageFailure(){

        ResponseEntity<String> responseEntity=new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        Mockito.when(restTemplate.exchange(Matchers.eq("/nifi/test"),Matchers.eq(HttpMethod.POST),Matchers.<HttpEntity<Object>>any(),Matchers.<Class<String>> any())).thenReturn(responseEntity);
        Product product=getProduct();
        publisher.setNifiServerUrl("/nifi/test");
        publisher.setRestTemplate(restTemplate);
        publisher.publishMessage(product);
        Mockito.verify(restTemplate,Mockito.times(1)).exchange(Matchers.eq("/nifi/test"),Matchers.eq(HttpMethod.POST),Matchers.<HttpEntity<Object>>any(),Matchers.<Class<String>> any());

    }

    private Product getProduct(){
        Category category = new Category("ELEC-1","ELECTRONICS");
        Brand brand = new Brand();
        brand.setBrandName("APPLE");
        brand.setDescription("APPLE");
        Product product=new Product("MACBOOK","APPLE LAPTOP",
                "17-INCHES",
                category,
                "true",
                10000.0,
                0, "BLACK",
                brand);
        return product;

    }



}
