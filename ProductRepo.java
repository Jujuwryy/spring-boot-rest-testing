package com.george.restapitest;

import org.springframework.data.jpa.repository.JpaRepository;



public interface ProductRepo extends JpaRepository<Product, Long> {
	
	
}
