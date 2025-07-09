package com.dailycodework.buynowdotcom.repository;

import com.dailycodework.buynowdotcom.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
  List<Product> findByCategoryNameAndBrand(String category, String brand);

  List<Product> findByCategoryName(String category);

  List<Product> findByBrandAndName(String brand, String name);

//  JPQL を実行するためのアノテーション
//  JPQL : SQL ライクな JPA 独自のクエリ言語
  @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))")
  List<Product> findByName(String name);

  List<Product> findByBrand(String brand);

  boolean existsByNameAndBrand(String name, String brand);
}