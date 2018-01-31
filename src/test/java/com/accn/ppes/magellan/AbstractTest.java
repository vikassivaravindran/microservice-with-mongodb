package com.accn.ppes.magellan;

import com.accn.ppes.magellan.entity.Brand;
import com.accn.ppes.magellan.entity.Category;
import com.accn.ppes.magellan.entity.ProductDTO;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.accn.ppes.magellan.ProductApplication;

import java.util.Arrays;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.HSQL)
@ActiveProfiles("test")
public abstract class AbstractTest {



    protected List<ProductDTO> getProducts() {

        List<Brand> brands=getBrands();
        List<Category> categories=getCategories();
        ProductDTO productDto1 = new ProductDTO("LAPTOP", "APPLE LAPTOP", "4", brands.get(0).getBrandName(), categories.get(0).getCategoryCode(), "true", 40000.0, 0, "BLUE");

        ProductDTO productDto2 = new ProductDTO("MONTEX", "WRITING PEN", "4", brands.get(1).getBrandName(),  categories.get(1).getCategoryCode(), "true", 20000.0, 10.0, "BLACK");

        return Arrays.asList(productDto1, productDto2);
    }

    protected List<Category> getCategories() {


        Category parentCategory = new Category();
        parentCategory.setCategoryCode("ELE");
        parentCategory.setDescription("ELECTRONICS");

        Category secondParentCategory = new Category();
        secondParentCategory.setCategoryCode("APP");
        secondParentCategory.setDescription("Appliances");

        return Arrays.asList(parentCategory,secondParentCategory);


    }

    protected List<Brand> getBrands() {

        Brand brand = new Brand();
        brand.setBrandName("APPLE");
        brand.setDescription("APPLE");
        Brand secondbrand = new Brand();
        secondbrand.setBrandName("MOTOROLO");
        secondbrand.setDescription("MOTOROLO");
        return Arrays.asList(brand, secondbrand);
    }




}
