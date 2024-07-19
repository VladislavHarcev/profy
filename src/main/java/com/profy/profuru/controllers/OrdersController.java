package com.profy.profuru.controllers;

import com.profy.profuru.DTO.OrdersDTO;
import com.profy.profuru.DTO.OrdersStatisticDTO;
import com.profy.profuru.models.BalanceHistory;
import com.profy.profuru.models.CalendarData;
import com.profy.profuru.models.HistoryService;
import com.profy.profuru.repository.BalanceHistoryRepository;
import com.profy.profuru.repository.CustomerRepository;
import com.profy.profuru.repository.ExecutorRepository;
import com.profy.profuru.service.CalendarService;
import com.profy.profuru.component.OrderBean;
import com.profy.profuru.models.OrderStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
@RestController
@RequestMapping("/order")
public class OrdersController {

    private final OrderBean orderBean;
    private final HistoryService historyService;

    @Operation(summary = "Получить заказ по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Найден заказ по ID",
                    content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "Заказ не найден",
                    content = @Content) })
    @GetMapping("/{id}")
    public ResponseEntity<OrdersDTO> getOrderDTOById(@PathVariable UUID id) {
        OrdersDTO ordersDTO = orderBean.findById(id);
        return ResponseEntity.ok(ordersDTO);
    }

    @Operation(summary = "Получить все заказы")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Все заказы найдены",
                    content = { @Content(mediaType = "application/json") })})
    @GetMapping
    public ResponseEntity<List<OrdersDTO>> getAllOrders() {
        List<OrdersDTO> orders = orderBean.findAll();
        return ResponseEntity.ok(orders);
    }

    @Operation(summary = "Создать новый заказ")
    @PostMapping
    public ResponseEntity<OrdersDTO> createOrdersDTO(@RequestBody OrdersDTO ordersDTO) {
        OrdersDTO createdOrdersDTO = orderBean.save(ordersDTO);
        return new ResponseEntity<>(createdOrdersDTO, HttpStatus.CREATED);
    }

    @Operation(summary = "Изменить заказ по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Заказ изменен по id",
                    content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "Заказ не найден",
                    content = @Content) })
    @PutMapping
    public ResponseEntity<OrdersDTO> updateOrdersDTO(@RequestBody OrdersDTO ordersDTO) {
        OrdersDTO updatedOrdersDTO = orderBean.updateOrder(ordersDTO);
        return ResponseEntity.ok(updatedOrdersDTO);
    }

    @Operation(summary = "Изменить стутус заказа по ID")
    @PatchMapping("/{id}/status")
    public ResponseEntity<OrdersDTO> updateOrderStatus(@PathVariable UUID id,
                                                  @RequestParam OrderStatus newStatus) {
        return ResponseEntity.ok(orderBean.updateOrderStatus(id, newStatus));
    }

    @Operation(summary = "Удалить заказ по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Заказ удален по id",
                    content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "Заказ не найден",
                    content = @Content) })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable UUID id) {
        orderBean.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Статистика выполненных заказов")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Все заказы найдены",
                    content = { @Content(mediaType = "application/json") })})
    @GetMapping("/statistic")
    public ResponseEntity<List<OrdersStatisticDTO>> getStatistic() {
        return ResponseEntity.ok(orderBean.getStatistic());
    }

    @Operation(summary = "Статистика выполненных заказов по нативному запросу")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Все заказы найдены",
                    content = { @Content(mediaType = "application/json") })})
    @GetMapping("/native_statistic")
    public ResponseEntity<List<OrdersStatisticDTO>> getStatisticNative() {
        return ResponseEntity.ok(orderBean.findStatisticNative());
    }

    @GetMapping("/history")
    @Transactional
    public ResponseEntity<Iterable<BalanceHistory>> popa() {
        return ResponseEntity.ok(historyService.findAll());
    }


}