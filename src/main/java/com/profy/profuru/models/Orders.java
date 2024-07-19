package com.profy.profuru.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Entity
@Table(name="orders")
@Getter
@Setter
@Schema(description = "Класс, представляющий заказ в системе")

public class Orders extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="executor_id")
    @JsonBackReference
    @Schema(description = "Идентификатор исполнителя заказа")
    private Executor executor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="customer_id")
    @JsonBackReference
    @Schema(description = "Идентификатор клиента заказа")
    private Customer customer;

    @Column(name="state")
    @Schema(description = "Статус заказа")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name="title")
    @Schema(description = "Название заказа")
    private String title;

    @Column(name="descript")
    @Schema(description = "Описание заказа")
    private String descript;

    @Column(name = "price")
    @Schema(description = "Стоимость заказа")
    private BigDecimal price;

}

