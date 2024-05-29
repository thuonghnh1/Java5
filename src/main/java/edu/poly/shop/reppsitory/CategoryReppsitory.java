package edu.poly.shop.reppsitory;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.poly.shop.model.Category;

public interface CategoryReppsitory extends JpaRepository<Category, Long>{

}
