package ru.yandex.practicum.commerce.order.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.yandex.practicum.commerce.order.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, UUID> {
}
