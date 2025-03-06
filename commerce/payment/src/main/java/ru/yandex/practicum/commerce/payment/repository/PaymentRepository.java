package ru.yandex.practicum.commerce.payment.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.yandex.practicum.commerce.payment.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, UUID> {
}
