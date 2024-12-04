package com.profy.profuru.service;

import com.profy.profuru.DTO.BalanceDTO;
import com.profy.profuru.DTO.CustomerDTO;
import com.profy.profuru.DTO.ExecutorDTO;
import com.profy.profuru.DTO.OrdersDTO;
import com.profy.profuru.expection.EntityNotFoundException;
import com.profy.profuru.models.Customer;
import com.profy.profuru.models.Executor;
import com.profy.profuru.repository.ExecutorRepository;
import com.profy.profuru.specification.ExecutorSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExecutorService {

    private final ExecutorRepository executorRepository;
    private final ModelMapper modelMapper;
    private final BalanceCreation balanceCreation;

    @Transactional
    public List<ExecutorDTO> findAll() {
        log.info("ExecutorService start findAll() EXECUTORS");
        Iterable<Executor> executors = executorRepository.findAll();
        List<ExecutorDTO> executorDTOs = StreamSupport.stream(executors.spliterator(), false)
                .map(executor -> {
                    ExecutorDTO executorDTO = modelMapper.map(executor, ExecutorDTO.class);
                    BalanceDTO balanceDTO = modelMapper.map(executor.getBalance(), BalanceDTO.class);
                    balanceDTO.setExecutor(executorDTO);
                    executorDTO.setBalance(balanceDTO);
                    return executorDTO;
                }).collect(Collectors.toList());
        log.info("ExecutorService findAll() EXECUTORS suc Count: {}", executorDTOs.size());
        return executorDTOs;
    }

    @Transactional(readOnly = true)
    public ExecutorDTO findDTOById(UUID id) {
        log.info("ExecutorService start findDTOById() EXECUTORS ID: {}", id);
        Executor executor = executorRepository.findById(id).orElseThrow(() -> {
            log.warn("ExecutorService findDTOById() EXECUTOR n/f ID: {}", id);
            return new EntityNotFoundException("Executor not found");
        });
        ExecutorDTO executorDTO = modelMapper.map(executor, ExecutorDTO.class);
        BalanceDTO balanceDTO = modelMapper.map(executor.getBalance(), BalanceDTO.class);
        balanceDTO.setExecutor(executorDTO);
        executorDTO.setBalance(balanceDTO);
        if (executor.getOrders() != null) {
            Hibernate.initialize(executor.getOrders());
            List<OrdersDTO> ordersDtoList = executor.getOrders().stream()
                    .map(order -> modelMapper.map(order, OrdersDTO.class))
                    .collect(Collectors.toList());
            executorDTO.setOrders(ordersDtoList);
        }
        log.info("ExecutorService findDTOById() EXECUTOR suc ID: {}", id);
        return executorDTO;
    }


    @Transactional
    public ExecutorDTO saveDTO(ExecutorDTO executorDTO) {
        log.info("ExecutorService start saveDTO() EXECUTOR");
        Executor executor = modelMapper.map(executorDTO, Executor.class);
        Executor savedExecutor = executorRepository.save(executor);
        balanceCreation.onExecutorSaved(savedExecutor);
        log.info("ExecutorService saveDTO() EXECUTOR suc ID: {}", savedExecutor.getId());
        return modelMapper.map(savedExecutor, ExecutorDTO.class);
    }

    @Transactional
    public ExecutorDTO updateDTO( ExecutorDTO executorDTO) {
        log.info("ExecutorService start updateDTO() EXECUTOR ID: {}", executorDTO.getId());
        Executor existingExecutor = executorRepository.findById(executorDTO.getId()).orElseThrow(() -> {
            log.warn("ExecutorService updateDTO() EXECUTOR n/f ID: {}", executorDTO.getId());
            return new EntityNotFoundException("Executor not found");
        });
        BeanUtils.copyProperties(executorDTO, existingExecutor, "createDate");
        Executor updatedExecutor = executorRepository.save(existingExecutor);
        log.info("ExecutorService updateDTO() EXECUTOR suc ID: {}", updatedExecutor.getId());
        return modelMapper.map(updatedExecutor, ExecutorDTO.class);
    }

    @Transactional
    public void delete(UUID id) {
        log.info("ExecutorService start delete() EXECUTOR ID: {}", id);
        executorRepository.deleteById(id);
        log.info("ExecutorService delete() EXECUTOR suc ID: {}", id);
    }


    @Transactional
    public List<ExecutorDTO> findByCriteria(Map<String, Object> criteria) {
        return executorRepository.findAll(ExecutorSpecification.byCriteria(criteria))
                .stream()
                .map(executor -> modelMapper.map(executor, ExecutorDTO.class))
                .collect(Collectors.toList());
    }

}
