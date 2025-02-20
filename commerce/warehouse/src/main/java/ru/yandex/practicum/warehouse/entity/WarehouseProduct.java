package ru.yandex.practicum.warehouse.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "warehouse_products")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseProduct {

    @Id
    private UUID productId;

    @Column(nullable = false)
    private boolean fragile;

    @Embedded
    private Dimension dimension;

    @Column(nullable = false)
    private double weight;

    @Column(nullable = false)
    private int quantityAvailable;
}
