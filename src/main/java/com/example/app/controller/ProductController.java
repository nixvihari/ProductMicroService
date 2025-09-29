package com.example.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.repo.Product;
import com.example.app.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping
	public List<Product> getProducts() {
		return productService.getProducts();
	}
	
	@GetMapping("/{prodId}")
	public Optional<Product> getProductById(@PathVariable("prodId") Long prodId) {
		return productService.getProductById(prodId);
	}
	
	@PostMapping("/addProduct")
	public Product addProduct(@RequestBody Product product) {
		return productService.addProduct(product);
	}
	
	@PutMapping("/updateProduct/{prodId}")
	public Product updateProduct(@PathVariable("prodId") Long prodId, @RequestBody Product product) {
		return productService.updateProductById(prodId, product);
	}
	
	@DeleteMapping("/deleteProduct/{prodId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteProduct(@PathVariable("prodId") Long prodId) {
		productService.deleteProduct(prodId);
	}
	
	//custom queries implementation
	
	@GetMapping("/getProductPriceById/{prodId}")
	public Double getProductPriceById(@PathVariable("prodId") Long productId) {
		return productService.getProductPriceById(productId);
	}
	
	@GetMapping("/getProductQuantityById/{prodId}")
	public Integer getProductQuantityById(@PathVariable("prodId") Long productId) {
		return productService.getProductQuantityById(productId);
	}
	
	@PutMapping("/updateProductQuantityById/{prodId}-{quantity}")
	public String updateProductQuantityById(
			@PathVariable("prodId") Long productId,
			@PathVariable("quantity") Integer updatedQuantity) {
		productService.updateProductQuantityById(productId, updatedQuantity);
		return "";
	}
}