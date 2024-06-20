package com.profy.profuru.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;



@Entity
@Table(name="orderes")
public class Orderes {

    @Id
    private UUID id;

    @Column(name="executor_id")
    private UUID executor_id;

    @Column(name="customer_id")
    private UUID customer_id;

    @Column(name="state_id")
    private UUID state_id;

    @Column(name="date")
    private Long date;

    @Column(name="title")
    private String title;

    @Column(name="descript")
    private String descript;

    public Orderes(UUID id, UUID executor_id, UUID customer_id, UUID state_id, Long date, String title, String descript){
        this.id =id;
        this.executor_id = executor_id;
        this.customer_id = customer_id;
        this.state_id = state_id;
        this.date = date;
        this.title = title;
        this.descript = descript;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getExecutor_id() {
        return executor_id;
    }

    public void setExecutor_id(UUID executor_id) {
        this.executor_id = executor_id;
    }

    public UUID getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(UUID customer_id) {
        this.customer_id = customer_id;
    }

    public UUID getState_id() {
        return state_id;
    }

    public void setState_id(UUID state_id) {
        this.state_id = state_id;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }
}
