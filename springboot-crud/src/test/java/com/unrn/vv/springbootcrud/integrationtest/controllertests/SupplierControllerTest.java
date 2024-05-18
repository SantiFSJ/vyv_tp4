package com.unrn.vv.springbootcrud.integrationtest.controllertests;

import com.unrn.vv.springbootcrud.model.Product;
import com.unrn.vv.springbootcrud.model.Sale;
import com.unrn.vv.springbootcrud.model.Supplier;
import com.unrn.vv.springbootcrud.service.ProductService;
import com.unrn.vv.springbootcrud.service.SaleService;
import com.unrn.vv.springbootcrud.service.SupplierService;
import org.aspectj.weaver.ast.Var;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SupplierControllerTest {
    @LocalServerPort
    private int port;

    private String baseUrl = "http://localhost";

    private static RestTemplate restTemplate;

    @Autowired
    private SupplierService supplierService;


    @BeforeAll
    public static void init() {
        restTemplate = new RestTemplate();
    }

    @BeforeEach
    public void setUp() {
        baseUrl = baseUrl.concat(":").concat("9191").concat("/suppliers");
    }

    @Test
    @Sql(statements = "DELETE FROM suppliers", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "TRUNCATE TABLE suppliers", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void addSupplier(){
        Supplier supplier= new Supplier("San Juan","Patagones","Carmen de Patagones","8504","Alexis");
        Supplier response = restTemplate.postForObject(baseUrl, supplier, Supplier.class);
        assertEquals("Alexis", response.getName());
        assertEquals(1, supplierService.getSuppliers().size());
    }
    @Test
    @Sql(statements = "TRUNCATE TABLE suppliers", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "TRUNCATE TABLE suppliers", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testUpdateSupplier(){
        Supplier supplier = new Supplier("San Juan","Patagones","Carmen de Patagones","8504","Alexis");
        Supplier response = restTemplate.postForObject(baseUrl, supplier, Supplier.class);

        supplier = new Supplier("San Juan","Patagones","Carmen de Patagones","8504","Santiago");
        restTemplate.put(baseUrl+"/update/{id}", supplier,  response.getId());
        var productFromDB = this.supplierService.getSupplierById(response.getId());
        assertAll(
                () -> assertNotNull(productFromDB),
                () -> assertEquals("Santiago", productFromDB.getName())
        );
    }

    @Test
    @Sql(statements = "TRUNCATE TABLE suppliers", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "TRUNCATE TABLE suppliers", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testDeleteProduct(){
        Supplier supplier = new Supplier("San Juan","Patagones","Carmen de Patagones","8504","Alexis");
        Supplier response = restTemplate.postForObject(baseUrl, supplier, Supplier.class);
        assertEquals(1, this.supplierService.getSuppliers().size());
        restTemplate.delete(baseUrl+"/{id}", response.getId());
        assertEquals(0, this.supplierService.getSuppliers().size());

    }
}
