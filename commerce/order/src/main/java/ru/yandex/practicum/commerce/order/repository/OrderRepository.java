package ru.yandex.practicum.commerce.order.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.yandex.practicum.commerce.order.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> findByUsername(String username);
}
