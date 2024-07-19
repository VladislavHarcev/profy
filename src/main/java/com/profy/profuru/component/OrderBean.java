package com.profy.profuru.component;


import com.profy.profuru.DTO.OrdersDTO;
import com.profy.profuru.DTO.OrdersStatisticDTO;
import com.profy.profuru.expection.ClosedOrderException;
import com.profy.profuru.expection.WeekendExeption;
import com.profy.profuru.models.OrderStatus;
import com.profy.profuru.repository.OrdersStatisticInterface;
import com.profy.profuru.service.BalanceService;
import com.profy.profuru.service.CalendarService;
import com.profy.profuru.service.OrdersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.stream.Collectors;


@Slf4j
@Component
@RequiredArgsConstructor
public class OrderBean {
    private final OrdersService ordersService;
    private final BalanceService balanceService;
    private final CalendarService calendarService;
    private final ModelMapper modelMapper = new ModelMapper();

    @Transactional
    public OrdersDTO updateOrder(OrdersDTO ordersDTO) {
        OrdersDTO updatedOrdersDTO = ordersService.findById(ordersDTO.getId());
        if (updatedOrdersDTO.getStatus() == OrderStatus.CLOSED){
            throw new ClosedOrderException("Cannot change a closed order");
        }
        updateIfNotNull(updatedOrdersDTO::setTitle, ordersDTO.getTitle());
        updateIfNotNull(updatedOrdersDTO::setDescript, ordersDTO.getDescript());
        updateIfNotNull(updatedOrdersDTO::setExecutor, ordersDTO.getExecutor());
        updateIfNotNull(updatedOrdersDTO::setCustomer, ordersDTO.getCustomer());
        updateIfNotNull(updatedOrdersDTO::setStatus, ordersDTO.getStatus());
        updateIfNotNull(updatedOrdersDTO::setPrice, ordersDTO.getPrice());
        if (ordersDTO.getStatus() == OrderStatus.CLOSED){
            balanceService.closingOrder(ordersDTO.getId());
        }
        return ordersService.update(updatedOrdersDTO);
    }

    @Transactional
    public List<OrdersDTO> findAll() {
        return ordersService.findAll(null);
    }

    @Transactional
    public List<OrdersStatisticDTO> getStatistic(){
        OrdersDTO ordersDTO = new OrdersDTO();
        ordersDTO.setStatus(OrderStatus.CLOSED);
        List<OrdersDTO> findOrders = ordersService.findAll(ordersDTO);
        return findOrders.stream()
                .map(order -> {
                    OrdersStatisticDTO statistic = modelMapper.map(order, OrdersStatisticDTO.class);
                    statistic.setExecutor(order.getExecutor().getTitle());
                    statistic.setCustomer(order.getCustomer().getTitle());
                    return statistic;
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public OrdersDTO findById(UUID id){
        return ordersService.findById(id);
    }

    @Transactional
    public OrdersDTO save(OrdersDTO ordersDTO){
        if (calendarService.checkDay()) {
            throw new WeekendExeption("You cannot create orders on weekends");
        }
        ordersDTO.setStatus(OrderStatus.CREATED);
        return ordersService.save(ordersDTO);

    }

    @Transactional
    public OrdersDTO updateOrderStatus(UUID id, OrderStatus newStatus){
        OrdersDTO ordersDTO = ordersService.findById(id);
        if (ordersDTO.getStatus() == OrderStatus.CLOSED){
            throw new ClosedOrderException("Cannot change a closed order");
        }
        ordersDTO.setStatus(newStatus);
        if (newStatus == OrderStatus.CLOSED){
            balanceService.closingOrder(id);
        }
        return ordersService.update(ordersDTO);
    }

    @Transactional
    public void delete(UUID id){
        ordersService.delete(id);
    }

    @Transactional
    public List<OrdersStatisticDTO> findStatisticNative() {
        List<OrdersStatisticInterface> results = ordersService.findStatistic();
        return results.stream().map(result -> {
            return modelMapper.map(result, OrdersStatisticDTO.class);
        }).collect(Collectors.toList());
    }

    private <T> void updateIfNotNull(Consumer<T> setter, T value) {
        if (value != null) {
            setter.accept(value);
        }
    }

}
