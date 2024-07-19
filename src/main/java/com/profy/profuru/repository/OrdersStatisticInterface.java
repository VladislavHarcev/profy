package com.profy.profuru.repository;

import java.util.UUID;

public interface OrdersStatisticInterface {
    UUID getId();
    String getTitle();
    String getCustomerTitle();
    String getExecutorTitle();
    Long getCreateDate();
}
