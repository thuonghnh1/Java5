package edu.poly.shop.reppsitory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.poly.shop.model.Product;

@Repository
public interface ProductReppsitory extends JpaRepository<Product, Long> {
	@Query("SELECT o FROM Product o WHERE o.price >= ?1 and o.price <= ?2")
	public Page<Product> searchByPrice(Double min, Double max, Pageable pageable);

	public Page<Product> findByPriceBetween(Double min, Double max, Pageable pageable);
}
