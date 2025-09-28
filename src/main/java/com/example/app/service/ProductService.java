package com.example.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.repo.Product;
import com.example.app.repo.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	//helpers
	public void updateHelper(Product sent, Product existing) {
		existing.setName(sent.getName());
		existing.setPrice(sent.getPrice());
		existing.setQuantity(sent.getQuantity());
	}
	
	//product services
	public List<Product> getProducts() {
		return productRepository.findAll();
	}
	
	public Optional<Product> getProductById(Long productId) {
		return productRepository.findById(productId);
	}
	
	public Product addProduct(Product product) {
		return productRepository.save(product);
	}
	
	public void deleteProduct(Long productId) {
		productRepository.deleteById(productId);
	}
	
	public Product updateProductById(Long productId, Product inputProduct) {
		Optional<Product> existingProductContainer = productRepository.findById(productId);
		if (existingProductContainer.isPresent()) {
			Product existingProduct = existingProductContainer.get();
			updateHelper(inputProduct, existingProduct);
			return productRepository.save(existingProduct);
		}
		
		return null; 
	}
	
	
	//custom queries implmentation
	
	public Double getProductPriceById(Long productId) {
		return productRepository.getProductPriceById(productId);
	}
	
	public Integer getProductQuantityById(Long productId) {
		return productRepository.getProductQuantityById(productId);
	}
}
