package com.microservices.productservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.productservice.dto.ProductRequest;
import com.microservices.productservice.dto.ProductResponse;
import com.microservices.productservice.model.Product;
import com.microservices.productservice.repository.ProductRepository;
import com.microservices.productservice.service.ProductService;

@RestController
@RequestMapping("api/product")
//@RequiredArgsConstructor
public class ProductController {
	
	private final ProductService productService;
	private final ProductRepository productRepository;
	
	 ProductController(ProductService productService,ProductRepository productRepository){
		this.productService=productService;
		this.productRepository=productRepository;
		
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createProduct(@RequestBody ProductRequest pr){
		productService.createProduct(pr);
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	List<ProductResponse> getProductInfo(){ 
		List<Product> productList=productRepository.findAll();
		
		return productList.stream().map(this::mapToProductResponse).toList();
}
	private ProductResponse	 mapToProductResponse(Product product) {
		return ProductResponse.builder().id(product.getId()).name(product.getName()).description(product.getDescription()).price(product.getPrice()).build();
}
}