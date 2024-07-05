package com.profy.profuru.controllers;

import com.profy.profuru.DTO.CustomerDTO;
import com.profy.profuru.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.EscapedErrors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    @Operation(summary = "Получить всех клиентов")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Найдены все клиенты",
                    content = { @Content(mediaType = "application/json") })})
    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<CustomerDTO> customers = customerService.findAll();
        return ResponseEntity.ok(customers);
    }

    @Operation(summary = "Получить клиента по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Найден клиент по ID",
                    content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "Клиент не найден",
                    content = @Content) })
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerDTOById(@PathVariable UUID id) {
        CustomerDTO customerDTO = customerService.findDTOById(id);
        return ResponseEntity.ok(customerDTO);
    }


    @Operation(summary = "Создать нового клиента")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Создан клиен",
                    content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", description = "Клиент не создан",
                    content = @Content) })
    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomerDTO(@RequestBody CustomerDTO customerDTO) {
        CustomerDTO createdCustomerDTO = customerService.saveDTO(customerDTO);
        return new ResponseEntity<>(createdCustomerDTO, HttpStatus.CREATED);
    }

    @Operation(summary = "Изменить клиента по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Клиент изменен",
                    content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "Клиент не найден",
                    content = @Content) })
    @PutMapping
    public ResponseEntity<CustomerDTO> updateCustomerDTO(@RequestBody CustomerDTO customerDTO) {
        CustomerDTO updatedCustomerDTO = customerService.updateDTO(customerDTO);
        return ResponseEntity.ok(updatedCustomerDTO);
    }

    @Operation(summary = "Удалить клиента по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Клиент удален по ID",
                    content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "Клиент не найден",
                    content = @Content) })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable UUID id) {
        customerService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @Operation(summary = "Поиск исполнителя по имени")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Найдены клиенты",
                    content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "Клиенты не найдены",
                    content = @Content) })
    @GetMapping("/search")
    public ResponseEntity<List<CustomerDTO>> getCustomerByTitle(@RequestParam String titlePart){
        List<CustomerDTO> customerDTOS = customerService.findByTitle(titlePart);
        return ResponseEntity.ok(customerDTOS);
    }

}

