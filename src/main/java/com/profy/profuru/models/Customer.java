package com.profy.profuru.models;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.profy.profuru.annotationEntity.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.util.List;


@Entity
@Table(name = "customer")
@Setter
@Getter
@Schema(description = "Класс, представляющий клиента в системе" )
@RequiredArgsConstructor
public class Customer extends BaseEntity {

    @Column(name="title")
    @Schema(description = "Имя клиента")
    private String title;

    @OneToMany(mappedBy = "customer",fetch = FetchType.LAZY)
    @JsonManagedReference
    @Schema(description = "Список заказов клиента")
    private List<Orders> orders;

    @OneToOne(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Schema(description = "Связанный баланс заказчика")
    private Balance balance;

}
