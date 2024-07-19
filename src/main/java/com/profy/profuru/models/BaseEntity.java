package com.profy.profuru.models;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.envers.Audited;

import java.util.UUID;

@MappedSuperclass
@ToString(includeFieldNames = true)
@Setter
@Getter
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Уникальный идентификатор")
    private UUID id;

    @Schema(description = "Дата создания объекта")
    @Column(name ="create_date")
    private Long createDate;

    @PrePersist
    public void onPrePersist(){
        this.setCreateDate(System.currentTimeMillis()/1000);
    }

}