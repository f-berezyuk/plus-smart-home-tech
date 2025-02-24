package ru.yandex.practicum.shopping.cart.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.yandex.practicum.shopping.cart.entity.ShoppingCart;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, UUID> {
    Optional<ShoppingCart> findByUsernameAndActive(String username, boolean active);
}
