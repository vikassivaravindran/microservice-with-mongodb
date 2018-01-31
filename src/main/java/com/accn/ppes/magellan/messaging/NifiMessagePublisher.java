package com.accn.ppes.magellan.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.accn.ppes.magellan.dto.NifiProductDTO;
import com.accn.ppes.magellan.entity.Category;
import com.accn.ppes.magellan.entity.Product;

/**
 * Implementation class of MessagePublisher for nifi profile
 * 
 * @author v.rama.krishnan
 *
 */
@Component
@Profile("nifi")
public class NifiMessagePublisher implements MessagePublisher{
	
	private static final Logger LOGGER=LoggerFactory.getLogger(NifiMessagePublisher.class);
	private RestTemplate restTemplate=new RestTemplate();

	@Value("${nifi.server.url}")
	private String nifiServerUrl;
	
	@Override
	public void publishMessage(Product product){
		
		ResponseEntity<String> response;
		Category category = product.getCategory();
		NifiProductDTO nifiProduct = new NifiProductDTO();
		
		nifiProduct.setProductName(product.getName());
		nifiProduct.setCategoryDesc(category.getDescription());
		nifiProduct.setProductDesc(product.getDescription());
		nifiProduct.setLevel(4);								//Default level - 4
		nifiProduct.setPrice(product.getPrice());
		
		LOGGER.info(" publishing the message to NIFI {}",product.getName());
	
		HttpHeaders headers=new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		HttpEntity<Object> requestEntity=new HttpEntity<>(nifiProduct, headers);
	
		response = restTemplate.exchange(nifiServerUrl,HttpMethod.POST,requestEntity, String.class);
		
		if(response.getStatusCode().is2xxSuccessful()){
			LOGGER.info("Successfully sent to Nifi");
		}
		else{
			LOGGER.info("Error occurred in Nifi!!! and the statuscode is {}",response.getStatusCode());
		}
		
	}

	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

	public String getNifiServerUrl() {
		return nifiServerUrl;
	}

	public void setNifiServerUrl(String nifiServerUrl) {
		this.nifiServerUrl = nifiServerUrl;
	}

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
}
