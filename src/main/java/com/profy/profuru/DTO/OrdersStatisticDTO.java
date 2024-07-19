package com.profy.profuru.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
@Schema(description = "Класс DTO для получения статистики заказов")
public class OrdersStatisticDTO {

    @Schema(description = "Уникальный идентификатор заказа.")
    private UUID id;

    @Schema(description = "Название заказа.")
    private String title;

    @Schema(description = "Исполнитель, связанный с заказом")
    private String executor;

    @Schema(description = "Клиент, связанный с заказом")
    private String customer;

    @Schema(description = "Дата создания записи заказа")
    private Long createDate;

}
