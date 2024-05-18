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
import static org.mockito.Mockito.doThrow;
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
	public void getProductByIdTest() {
		int id = 1;
		Product product = new Product(id, "headset", 100, 54.3 );
		when(repository.findById(id)).thenReturn(java.util.Optional.of(product));
		Product getProduct = service.getProductById(id);
		assertEquals(product, getProduct);
	}
	@Test
	public void getProductByNameTest() {
		String name = "headset";
		Product product = new Product(1, name, 100, 54.3 );
		when(repository.findByName(name)).thenReturn(product);
		Product getProduct = service.getProductByName(name);
		assertEquals(product, getProduct);
	}
	@Test
	public void deleteProductTest() {
		int id = 1;
		ExceptionCollector collector = new ExceptionCollector();
		collector.execute(() -> {
			String response = service.deleteProduct(id);
			assertEquals("product deleted !! " + id, response);
		});
	}
	@Test
	public void updateProductTest() {
		int id = 1;
		Product product = new Product(id, "headset", 100, 54.3 );
		when(repository.findById(id)).thenReturn(java.util.Optional.of(product));
		when(repository.save(product)).thenReturn(product);
		Product updatedProduct = service.updateProduct(id, product);
		assertEquals(product, updatedProduct);
	}
	@Test
	public void saveProductsTest() {
		List<Product> products = List.of(
			new Product(1, "headset", 100, 54.3 ),
			new Product(1, "phone", 120, 980 )
		);
		when(repository.saveAll(products)).thenReturn(products);
		List<Product> savedProducts = service.saveProducts(products);
		assertEquals(products, savedProducts);
	}
	@Test
	public void getProductsTest() throws MyException {
		List<Product> products = List.of(
			new Product(1, "headset", 100, 54.3 ),
			new Product(1, "phone", 120, 980 )
		);
		when(repository.findAll()).thenReturn(products);
		List<Product> getProducts = service.getProducts();
		assertEquals(products, getProducts);
	}
	@Test
	public void getProductsExceptionTest() {
		when(repository.findAll()).thenThrow(new RuntimeException());
		assertThrows(MyException.class, () -> service.getProducts());
	}
	@Test
	public void getProductByIdNullTest() {
		int id = 1;
		when(repository.findById(id)).thenReturn(java.util.Optional.empty());
		Product getProduct = service.getProductById(id);
		assertNull(getProduct);
	}
	@Test
	public void getProductByNameNullTest() {
		String name = "headset";
		when(repository.findByName(name)).thenReturn(null);
		Product getProduct = service.getProductByName(name);
		assertNull(getProduct);
	}
	@Test
	public void updateProductNullTest() {
		//El .orElse(null) de ProductService.updateProduct rompe todo
		int id = 1;
		Product product = new Product(id, "headset", 100, 54.3 );
		when(repository.findById(id)).thenReturn(java.util.Optional.empty());
		Product updatedProduct = service.updateProduct(id, product);
		assertNull(updatedProduct);
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
