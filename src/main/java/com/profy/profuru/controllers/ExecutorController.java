package com.profy.profuru.controllers;

import com.profy.profuru.DTO.ExecutorDTO;
import com.profy.profuru.component.ExecutorBean;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
@RestController
@RequestMapping("/executor")
public class ExecutorController {

    private final ExecutorBean executorBean;

    @Operation(summary = "Получить всех исполнителей")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Найдены все исполнители",
                    content = { @Content(mediaType = "application/json") })})
    @GetMapping
    public ResponseEntity<List<ExecutorDTO>> getAllExecutors() {
        List<ExecutorDTO> executors = executorBean.findAll();
        return ResponseEntity.ok(executors);
    }
    @Operation(summary = "Получить исполнителя по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Найден исполнитель по ID",
                    content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "Исполнитель не найден",
                    content = @Content) })
    @GetMapping("/{id}")
    public ResponseEntity<ExecutorDTO> getExecutorDTOById(@PathVariable UUID id) {
        ExecutorDTO executorDTO = executorBean.findById(id);
        return ResponseEntity.ok(executorDTO);
    }

    @Operation(summary = "Создать нового исполнителя")
    @PostMapping
    public ResponseEntity<ExecutorDTO> createExecutorDTO(@RequestBody ExecutorDTO executorDTO) {
        ExecutorDTO createdExecutorDTO = executorBean.save(executorDTO);
        return new ResponseEntity<>(createdExecutorDTO, HttpStatus.CREATED);
    }

    @Operation(summary = "Изменить исполнителя по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Исполнитель изменен",
                    content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "Исполнитель не найден",
                    content = @Content) })
    @PutMapping
    public ResponseEntity<ExecutorDTO> updateExecutorDTO(@RequestBody ExecutorDTO executorDTO) {
        ExecutorDTO updatedExecutorDTO = executorBean.update(executorDTO);
        return ResponseEntity.ok(updatedExecutorDTO);
    }

    @Operation(summary = "Удалить исполнителя по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Исполнитель удален по ID",
                    content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "Исполнитель не найден",
                    content = @Content) })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExecutor(@PathVariable UUID id) {
        executorBean.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Поиск исполнителя по критериям")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Найдены исполнители",
                    content = { @Content(mediaType = "application/json") })})
    @PostMapping("/search")
    public ResponseEntity<List<ExecutorDTO>> getExecutorsByCriteria(@RequestBody ExecutorDTO criteria){
        List<ExecutorDTO> executorDTOS = executorBean.findByCriteria(criteria);
        return ResponseEntity.ok(executorDTOS);
    }
}

