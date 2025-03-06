package ru.yandex.practicum.payment.dto;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentDto {
    private UUID paymentId;
    private BigDecimal totalPayment;
    private BigDecimal deliveryPayment;
    private BigDecimal feePayment;
}
