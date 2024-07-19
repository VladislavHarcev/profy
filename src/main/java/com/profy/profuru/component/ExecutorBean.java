package com.profy.profuru.component;

import com.profy.profuru.DTO.BalanceDTO;
import com.profy.profuru.DTO.ExecutorDTO;
import com.profy.profuru.expection.NoMandatoryFieldsSet;
import com.profy.profuru.service.ExecutorService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
@Component
public class ExecutorBean {

    private final ExecutorService executorService;

    @Transactional
    public List<ExecutorDTO> findAll(){
        return executorService.findAll(null);
    }
    @Transactional
    public ExecutorDTO findById(UUID id){
        return executorService.findById(id);
    }
    @Transactional
    public List<ExecutorDTO> findByCriteria(ExecutorDTO executorDto){
        return executorService.findAll(executorDto);
    }

    @Transactional
    public ExecutorDTO save(ExecutorDTO executorDto){
        if (StringUtils.isBlank(executorDto.getTitle())){
            throw new NoMandatoryFieldsSet("Title cannot be null");
        }
        BalanceDTO balanceDTO = new BalanceDTO();
        balanceDTO.setExecutor(executorDto);
        balanceDTO.setBalance(BigDecimal.ZERO);
        executorDto.setBalance(balanceDTO);
        return executorService.save(executorDto);
    }

    @Transactional
    public void delete(UUID id){
        executorService.delete(id);
    }

    @Transactional
    public ExecutorDTO update(ExecutorDTO executorDto){
        ExecutorDTO updateCustomer = executorService.findById(executorDto.getId());
        BeanUtils.copyProperties(executorDto, updateCustomer, "createDate", "title");
        if (executorDto.getTitle() != null){
            updateCustomer.setTitle(executorDto.getTitle());
        }
        return executorService.update(updateCustomer);
    }
}
