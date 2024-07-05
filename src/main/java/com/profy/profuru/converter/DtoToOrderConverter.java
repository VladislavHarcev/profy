package com.profy.profuru.converter;

import com.profy.profuru.DTO.OrdersDTO;
import com.profy.profuru.models.Orders;
import org.modelmapper.AbstractConverter;

public class DtoToOrderConverter extends AbstractConverter<OrdersDTO, Orders> {
    @Override
    protected Orders convert(OrdersDTO source) {
        Orders destination = new Orders();
        destination.setId(source.getId());
        destination.setTitle(source.getTitle());
        destination.setCreateDate(source.getCreateDate());
        destination.setDescript(source.getDescript());
        destination.setStatus(source.getStatus());
        destination.setPrice(source.getPrice());
        return destination;
    }
}
