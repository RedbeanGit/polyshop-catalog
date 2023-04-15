package fr.dopolytech.polyshop.catalog.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import fr.dopolytech.polyshop.catalog.dtos.UpdateProductDto;
import fr.dopolytech.polyshop.catalog.models.Product;
import fr.dopolytech.polyshop.catalog.services.ProductService;

@RestController
@RequestMapping("/products")
class ProductController {
	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping(value = "/{id}", produces = "application/json")
	public Mono<Product> findOne(@PathVariable("id") String productId) {
		return productService.getProduct(productId);
	}

	@GetMapping(produces = "application/json")
	public Flux<Product> findAll() {
		return productService.getAllProducts();
	}

	@PutMapping(value = "/{id}", produces = "application/json")
	public Mono<Product> update(@PathVariable("id") String productId, @RequestBody UpdateProductDto dto) {
		return productService.updateProduct(productId, dto);
	}

}
