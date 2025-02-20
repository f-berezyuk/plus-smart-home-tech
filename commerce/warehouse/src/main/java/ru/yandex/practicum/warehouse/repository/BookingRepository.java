package ru.yandex.practicum.warehouse.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.yandex.practicum.warehouse.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, UUID> {
    Optional<Booking> findByShoppingCartId(UUID shoppingCartId);
}
