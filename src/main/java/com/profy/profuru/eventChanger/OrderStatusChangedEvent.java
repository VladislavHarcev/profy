package com.profy.profuru.eventChanger;


import com.profy.profuru.models.OrderStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.util.UUID;


@Getter
@Setter
@RequiredArgsConstructor
public class OrderStatusChangedEvent{
    private final UUID orderId;
    private final OrderStatus newStatus;
}