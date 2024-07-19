package com.profy.profuru.DTO;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@Schema(description = "Класс DTO для исполнителя, содержащий информацию для передачи данных.")
public class ExecutorDTO {
    @Schema(description="Уникальный идентификатор исполнителя")
    private UUID id;

    @Schema(description = "Имя исполнителя")
    private String title;

    @Schema(description = "Дата создания записи исполнителя" )
    private Long createDate;

    @JsonIgnoreProperties({"executor", "customer"})
    @Schema(description = "Список заказов, связанных с исполнителем")
    private List<OrdersDTO> orders;

    @Schema(description = "Баланс")
    private BalanceDTO balance;

}
