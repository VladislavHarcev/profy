package com.profy.profuru.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.profy.profuru.annotationEntity.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "executor")
@Setter
@Getter
@Schema(description = "Класс, представляющий исполнителя заказов")
@RequiredArgsConstructor
public class Executor extends BaseEntity {

    @Column(name="title")
    @Schema(description = "Имя исполнителя")
    private String title;

    @OneToMany(mappedBy = "executor",fetch = FetchType.LAZY)
    @JsonManagedReference
    @Schema(description = "Список заказов, связанных с исполнителем")
    private List<Orders> orders;

    @OneToOne(mappedBy = "executor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Schema(description = "Связанный баланс исполнителя")
    private Balance balance;


}
