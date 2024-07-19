package com.profy.profuru.DTO;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@Schema(description = "Класс, представляющий клиента в системе")
public class CustomerDTO {
    @Schema(description = "Уникальный идентификатор клиента")
    private UUID id;

    @Schema(description = "Имя клиента")
    private String title;

    @Schema(description = "Дата создания записи клиента")
    private Long createDate;

    @JsonIgnoreProperties({"executor", "customer"})
    @Schema(description = "Cписок заказов, связанных с клиентом")
    private List<OrdersDTO> orders;

    @Schema(description = "Баланс")
    private BalanceDTO balance;

}