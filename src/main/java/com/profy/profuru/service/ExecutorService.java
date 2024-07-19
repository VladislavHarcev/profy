package com.profy.profuru.service;

import com.profy.profuru.DTO.ExecutorDTO;
import com.profy.profuru.expection.EntityNotFoundException;
import com.profy.profuru.models.Executor;
import com.profy.profuru.repository.ExecutorRepository;
import com.profy.profuru.specification.ExecutorSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExecutorService {

    private final ExecutorRepository executorRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Transactional
    public List<ExecutorDTO> findAll(ExecutorDTO executorDTO) {
        log.info("ExecutorService start findAll() EXECUTORS");
        Iterable<Executor> executors = executorRepository.findAll(ExecutorSpecification.byCriteria(executorDTO));
        List<ExecutorDTO> executorDTOs = StreamSupport.stream(executors.spliterator(), false)
                .map(executor -> {
                    return modelMapper.map(executor, ExecutorDTO.class);
                }).collect(Collectors.toList());
        log.info("ExecutorService findAll() EXECUTORS suc Count: {}", executorDTOs.size());
        return executorDTOs;
    }


    @Transactional(readOnly = true)
    public ExecutorDTO findById(UUID id) {
        log.info("ExecutorService start findDTOById() EXECUTORS ID: {}", id);
        Executor executor = executorRepository.findById(id).orElseThrow(() -> {
            log.warn("ExecutorService findDTOById() EXECUTOR n/f ID: {}", id);
            return new EntityNotFoundException("Executor not found");
        });
       ExecutorDTO executorDTO = modelMapper.map(executor, ExecutorDTO.class);
        log.info("ExecutorService findDTOById() EXECUTOR suc ID: {}", id);
        return executorDTO;
    }


    @Transactional
    public ExecutorDTO save(ExecutorDTO executorDTO) {
        log.info("ExecutorService start saveDTO() EXECUTOR");
        Executor executor = modelMapper.map(executorDTO, Executor.class);
        executorRepository.save(executor);
        log.info("ExecutorService saveDTO() EXECUTOR suc ID: {}", executor.getId());
        return modelMapper.map(executor, ExecutorDTO.class);
    }

    @Transactional
    public ExecutorDTO update(ExecutorDTO executorDTO) {
        log.info("ExecutorService start updateDTO() EXECUTOR ID: {}", executorDTO.getId());
        Executor updatedExecutor = executorRepository.save(modelMapper.map(executorDTO, Executor.class));
        log.info("ExecutorService updateDTO() EXECUTOR suc ID: {}", updatedExecutor.getId());
        return modelMapper.map(updatedExecutor, ExecutorDTO.class);
    }

    @Transactional
    public void delete(UUID id) {
        log.info("ExecutorService start delete() EXECUTOR ID: {}", id);
        executorRepository.deleteById(id);
        log.info("ExecutorService delete() EXECUTOR suc ID: {}", id);
    }

}
