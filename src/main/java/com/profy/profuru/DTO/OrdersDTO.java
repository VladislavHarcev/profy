package com.profy.profuru.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.profy.profuru.models.OrderStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;


@Getter
@Setter
@Schema(description = "Класс DTO для заказа, содержащий информацию для передачи данных. ")
public class OrdersDTO {

    @Schema(description = "Уникальный идентификатор заказа.")
    private UUID id;

    @Schema(description = "Название заказа.")
    private String title;

    @Schema(description = "Описание заказа.")
    private String descript;

    @Schema(description = "Дата создания записи заказа")
    private Long createDate;

    @JsonIgnoreProperties({"orders"})
    @Schema(description = "Исполнитель, связанный с заказом")
    private ExecutorDTO executor;

    @JsonIgnoreProperties({"orders"})
    @Schema(description = "Клиент, связанный с заказом")
    private CustomerDTO customer;

    @Schema(description = "Идентификатор состояния заказа")
    private OrderStatus status;

    @Schema(description = "Стоимость заказа")
    private BigDecimal price;

}
