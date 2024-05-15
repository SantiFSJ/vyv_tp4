package com.unrn.vv.crud;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.util.ExceptionCollector;

import com.unrn.vv.crud.model.Product;
import com.unrn.vv.crud.repository.ProductRepository;
import com.unrn.vv.crud.service.MyException;
import com.unrn.vv.crud.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringBootCrudApplicationTests {

    @Autowired
	private ProductService service;

	@MockBean
	private ProductRepository repository;


	@Test
	public void addProductTest() {
		Product product = new Product(1, "headset", 100, 54.3 );
		when(repository.save(product)).thenReturn(product);
		Product savedProduct = service.saveProduct(product);
		assertEquals(product, savedProduct);
	}


	@Test
	public void listProductTest() throws MyException {
		// Mockear el repository
		when(repository.findAll()).thenReturn(Stream.of(
			new Product(1, "headset", 100, 54.3 ),
			new Product(1, "phone", 120, 980 )
		).collect(Collectors.toList()));

		List<Product> products = service.getProducts();
		assertAll(
			() -> assertNotNull(products),
			() -> assertEquals(2, products.size())
		);
	}


}
