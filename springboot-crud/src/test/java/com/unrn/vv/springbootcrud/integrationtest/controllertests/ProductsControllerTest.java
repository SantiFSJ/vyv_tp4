package com.unrn.vv.springbootcrud.integrationtest.controllertests;

import com.unrn.vv.springbootcrud.model.Product;
import com.unrn.vv.springbootcrud.service.ProductService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductsControllerTest {

    @LocalServerPort
    private int port;

    private String baseUrl = "http://localhost";

    private static RestTemplate restTemplate;

    @Autowired
    private ProductService productService;

    @BeforeAll
    public static void init() {
        restTemplate = new RestTemplate();
    }

    @BeforeEach
    public void setUp() {
        baseUrl = baseUrl.concat(":").concat("9191").concat("/products");
    }

    @Test
    @Sql(statements = "DELETE FROM products", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "TRUNCATE TABLE products", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testAddProduct() {
       Product product = new Product("headset", 2, 7999);
        Product response = restTemplate.postForObject(baseUrl, product, Product.class);
        assertEquals("headset", response.getName());
        assertEquals(1, productService.getProducts().size());
    }

    @Test
    @Sql(statements = "TRUNCATE TABLE products", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "TRUNCATE TABLE products", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testAddProduct2() {
        HttpEntity<Product> request = new HttpEntity<>(new Product("headset2", 2, 7999));
        ResponseEntity<Product> response = restTemplate
                .exchange(baseUrl, HttpMethod.POST, request, Product.class);
        assertEquals( HttpStatus.CREATED, response.getStatusCode());
        Product prod = response.getBody();
        assertNotNull(prod);
        assertEquals("headset2", prod.getName());
        assertEquals(1, this.productService.getProducts().size());
    }


    @Test
    @Sql(statements = "TRUNCATE TABLE products", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "TRUNCATE TABLE products", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testGetProducts() {
        Product product = new Product("headset", 2, 7999);
        Product product2 = new Product("Chair", 2, 17999);
        Product response = restTemplate.postForObject(baseUrl, product, Product.class);
        Product response2 = restTemplate.postForObject(baseUrl, product2, Product.class);

        List<Product> products = restTemplate.getForObject(baseUrl, List.class);
        assertEquals(2, products.size());
    }


    @Test
    @Sql(statements = "INSERT INTO products (id,name, quantity, price) VALUES (1,'CAR', 1, 334000)", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "DELETE FROM products WHERE id=1", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testFindProductById() {
       Product product = restTemplate.getForObject(baseUrl + "/{id}", Product.class, 1);
        assertAll(
                () -> assertNotNull(product),
                () -> assertEquals(1, product.getId()),
                () -> assertEquals("CAR", product.getName())
        );

    }

    @Test
    @Sql(statements = "TRUNCATE TABLE products", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "TRUNCATE TABLE products", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testUpdateProduct(){
        Product product = new Product("headset", 2, 7999);
        Product response = restTemplate.postForObject(baseUrl, product, Product.class);

        product = new Product("headset", 99, 8000);
        restTemplate.put(baseUrl+"/update/{id}", product,  response.getId());
        Product productFromDB = this.productService.getProductById(response.getId());
        assertAll(
                () -> assertNotNull(productFromDB),
                () -> assertEquals(8000, productFromDB.getPrice())
        );
    }

    @Test
    @Sql(statements = "TRUNCATE TABLE products", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "TRUNCATE TABLE products", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testDeleteProduct(){
        Product product = new Product("headset", 2, 7999);
        Product response = restTemplate.postForObject(baseUrl, product, Product.class);
        assertEquals(1, this.productService.getProducts().size());
        restTemplate.delete(baseUrl+"/{id}", response.getId());
        assertEquals(0, this.productService.getProducts().size());

    }


}
