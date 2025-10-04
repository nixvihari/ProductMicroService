package com.example.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.repo.Product;
import com.example.app.repo.ProductRepository;

/**
 * Product Service class handles the business logic and processing of products related operations such as CRUD.
 * <br>Injected by Product Controller
 * <br>Injects Product Repository
 * 
 * @author Nikunj Vihari Konakalla
 * @version 1.0.0
 */
@Service
public class ProductService {

	
	@Autowired
	private ProductRepository productRepository;
	
	//helpers
	/**
	 * Helper method used by updateProductById() method to map properties from input product to existing product.
	 * 
	 * @param sent		Input product with updation properties
	 * @param existing	Existing product with properties to be updated.
	 */
	public void updateHelper(Product sent, Product existing) {
		existing.setName(sent.getName());
		existing.setPrice(sent.getPrice());
		existing.setQuantity(sent.getQuantity());
	}
	
	//product services
	/**
	 * Fetch all products from products table.
	 * 
	 * @return List of products
	 */
	public List<Product> getProducts() {
		return productRepository.findAll();
	}
	
	/**
	 * Fetch a product using product ID
	 * 
	 * @param productId ID of the product to find
	 * @return Optional of Product if found, else empty optional.
	 */
	public Optional<Product> getProductById(Long productId) {
		return productRepository.findById(productId);
	}
	
	/**
	 * Add a new product.
	 * 
	 * @param product Object of type Product
	 * @return The saved product as object of type Product.
	 */
	public Product addProduct(Product product) {
		return productRepository.save(product);
	}
	
	/**
	 * Deletes an existing product, does nothing if product does not exist.
	 * 
	 * @param productId ID of the product to be deleted.
	 */
	public void deleteProduct(Long productId) {
		productRepository.deleteById(productId);
	}
	
	/**
	 * Updates properties of an existing product
	 * 
	 * @param productId 	ID of the product to be updated.
	 * @param inputProduct	Product object with updation properties.
	 * @return updated product if found existing, else null
	 */
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
	
	/**
	 * Gets the price of a product by its ID.
	 * 
	 * @param productId <b>id</b> must not be null.
	 * @return The price of the product as Double.
	 */
	public Double getProductPriceById(Long productId) {
		return productRepository.getProductPriceById(productId);
	}
	
	/**
	 * Get the quantity of a product in stock.
	 * 
	 * @param productId <b>id</b> must not be null.
	 * @return The quantity of the product as Integer.
	 */
	public Integer getProductQuantityById(Long productId) {
		return productRepository.getProductQuantityById(productId);
	}
	
	/**
	 * Update the quantity of a product in stock.
	 * 
	 * @param prodId <b>id</b> must not be null.
	 * @param updatedQuantity Input quantity, must be positive.
	 */
	public void updateProductQuantityById(Long prodId, Integer updatedQuantity) {
		productRepository.updateProductQuantityById(prodId, updatedQuantity);
	}
}
