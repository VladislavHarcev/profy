package com.profy.profuru.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.UUID;


@Getter
@Setter
@Schema(description = "Баланс заказчика или исполнителя")
public class BalanceDTO {

    @Schema(description = "Уникальный идентификатор клиента")
    private UUID id;

    @JsonIgnore
    @Schema(description = "Исполнитель")
    private ExecutorDTO executor;

    @JsonIgnore
    @Schema(description = "Заказчик")
    private CustomerDTO customer;

    @Schema(description = "Дата создания")
    private Long createDate;

    @Schema(description = "Баланс")
    private BigDecimal balance;

}
