package com.example.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	
	@Query("SELECT p.price FROM Product p WHERE p.productId = :prodId")
	public Double getProductPriceById(@Param("prodId") Long prodId);
	
	@Query("SELECT p.quantity FROM Product p WHERE p.productId = :prodId")
	public Integer getProductQuantityById(@Param("prodId") Long prodId);
	
	//write update quantity custom query
	@Modifying
	@Transactional
	@Query("UPDATE Product p SET p.quantity = :updatedQuantity WHERE p.productId = :prodId")
	public void updateProductQuantityById(
			@Param("prodId") Long productId,
			@Param("updatedQuantity") Integer updatedQuantity);
}
