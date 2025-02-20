package ru.yandex.practicum.shopping.store.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ru.yandex.practicum.shopping.store.entity.Product;
import ru.yandex.practicum.shopping.store.enums.ProductCategory;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    @Query("SELECT p FROM Product p WHERE p.productCategory = :category")
    Page<Product> findAllByProductCategory(@Param("category") ProductCategory category, Pageable pageable);
}
