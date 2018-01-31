package com.accn.ppes.magellan;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class ProductApplication {
	
	  public static void main(String[] args) {
			SpringApplication.run(ProductApplication.class, args);
		}


}

@Configuration
@EnableSwagger2
class SwaggerConfiguration {
	@Bean
	public Docket api() {
		
		 Parameter headerParam = new ParameterBuilder().name("tenant-id").defaultValue("microservices").parameterType("header")
	                .modelRef(new ModelRef("string")).description("Tenant Identity").required(false).build();
	     return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).globalOperationParameters(Arrays.asList(headerParam)) 
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.accn.ppes.magellan"))
				.paths(PathSelectors.any())
				.build();
	}
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Product API")
				.description("Product API reference for developers").build();
	}

}