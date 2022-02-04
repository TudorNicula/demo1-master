package com.example.demo.repository;

import com.example.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = """              
            SELECT p.*
            FROM product p
            INNER JOIN user_product up on p.id = up.product_id
            WHERE up.user_id = :userId
            """, nativeQuery = true)
    List<Product> findAllById(@Param("userId") Long userId);

}
