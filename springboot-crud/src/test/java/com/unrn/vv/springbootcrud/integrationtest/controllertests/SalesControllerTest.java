package com.unrn.vv.springbootcrud.integrationtest.controllertests;

import com.unrn.vv.springbootcrud.SpringbootCrudApplication;
import com.unrn.vv.springbootcrud.model.Product;
import com.unrn.vv.springbootcrud.model.Sale;
import com.unrn.vv.springbootcrud.model.Supplier;
import com.unrn.vv.springbootcrud.service.ProductService;
import com.unrn.vv.springbootcrud.service.SaleService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SalesControllerTest {


    @LocalServerPort
    private int port;

    private String baseUrl = "http://localhost";

    private static RestTemplate restTemplate;

    @Autowired
    private SaleService saleService;
    @Autowired
    private ProductService productService;

    @BeforeAll
    public static void init() {
        restTemplate = new RestTemplate();
    }

    @BeforeEach
    public void setUp() {
        baseUrl = baseUrl.concat(":").concat("9191").concat("/sales");
    }

    @Test
    @Sql(statements = "DELETE FROM products", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "TRUNCATE TABLE products", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Sql(statements = "DELETE FROM sales", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "TRUNCATE TABLE sales", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testAddSale() {
        Product product = new Product("headset", 2, 7999);
        productService.saveProduct(product);
        List<Product> prods= productService.getProducts();
        Sale sale = new Sale(LocalDate.now(),15000,"PENDIENTE",prods);
        Sale response = restTemplate.postForObject(baseUrl, sale, Sale.class);
        assertEquals(15000, response.getTotal());
        assertEquals(1, saleService.getSales().size());
    }

    @Test
    @Sql(statements = "DELETE FROM products", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "TRUNCATE TABLE products", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Sql(statements = "DELETE FROM sales", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "TRUNCATE TABLE sales", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testUpdateSale(){
        Product product = new Product("headset", 2, 7999);
        productService.saveProduct(product);
        List<Product> prods= productService.getProducts();
        Sale sale = new Sale(LocalDate.now(),15000,"PENDIENTE",prods);
        Sale response = restTemplate.postForObject(baseUrl, sale, Sale.class);

        sale = new Sale(response.getDate(), 14999, "PAGADO",response.getProducts());
        restTemplate.put(baseUrl+"/update/{id}", sale,  response.getId());
        Sale saleFromDB = this.saleService.getSaleById(response.getId());
        assertAll(
                () -> assertNotNull(saleFromDB),
                () -> assertEquals("PAGADO", saleFromDB.getState()),
                () -> assertEquals(14999, saleFromDB.getTotal())
        );
    }
}
