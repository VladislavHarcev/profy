package com.profy.profuru.controllers;

import com.profy.profuru.DTO.OrdersDTO;
import com.profy.profuru.models.OrderStatus;
import com.profy.profuru.service.BalanceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import com.profy.profuru.service.OrdersService;
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

    private final OrdersService ordersService;
    private final BalanceService balanceService;

    @Operation(summary = "Получить заказ по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Найден заказ по ID",
                    content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "Заказ не найден",
                    content = @Content) })
    @GetMapping("/{id}")
    public ResponseEntity<OrdersDTO> getOrderDTOById(@PathVariable UUID id) {
        OrdersDTO ordersDTO = ordersService.findDTOById(id);
        return ResponseEntity.ok(ordersDTO);
    }

    @Operation(summary = "Получить все заказы")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Все заказы найдены",
                    content = { @Content(mediaType = "application/json") })})
    @GetMapping
    public ResponseEntity<List<OrdersDTO>> getAllOrders() {
        List<OrdersDTO> orders = ordersService.findAll();
        return ResponseEntity.ok(orders);
    }

    @Operation(summary = "Создать новый заказ")
    @PostMapping
    public ResponseEntity<OrdersDTO> createOrdersDTO(@RequestBody OrdersDTO ordersDTO) {
        OrdersDTO createdOrdersDTO = ordersService.saveDTO(ordersDTO);
        return new ResponseEntity<>(createdOrdersDTO, HttpStatus.CREATED);
    }

    @Transactional
    @Operation(summary = "Изменить заказ по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Заказ изменен по id",
                    content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "Заказ не найден",
                    content = @Content) })
    @PutMapping
    public ResponseEntity<OrdersDTO> updateOrdersDTO(@RequestBody OrdersDTO ordersDTO) {
        OrdersDTO updatedOrdersDTO = ordersService.updateDTO(ordersDTO);
        balanceService.updateExBalanceByOrdDto(ordersDTO);
        return ResponseEntity.ok(updatedOrdersDTO);
    }

    @Transactional
    @Operation(summary = "Изменить стутус заказа по ID")
    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> updateOrderStatus(@PathVariable UUID id,
                                                  @RequestParam OrderStatus newStatus) {
        ordersService.updateOrderStatus(id, newStatus);
        balanceService.updateExBalanceByOrdId(id, newStatus);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Удалить заказ по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Заказ удален по id",
                    content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "Заказ не найден",
                    content = @Content) })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable UUID id) {
        ordersService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}