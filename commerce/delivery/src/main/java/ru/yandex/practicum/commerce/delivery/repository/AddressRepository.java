package ru.yandex.practicum.commerce.delivery.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.yandex.practicum.commerce.delivery.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, UUID> {
}
