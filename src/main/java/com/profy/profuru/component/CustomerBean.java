package com.profy.profuru.component;

import com.profy.profuru.DTO.BalanceDTO;
import com.profy.profuru.DTO.CustomerDTO;
import com.profy.profuru.expection.NoMandatoryFieldsSet;
import com.profy.profuru.service.CustomerService;
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
public class CustomerBean {

    private final CustomerService customerService;


    @Transactional
    public List<CustomerDTO> findAll(){
        return customerService.findAll(null);
    }

    @Transactional
    public CustomerDTO findById(UUID id){
        return customerService.findById(id);
    }

    @Transactional
    public List<CustomerDTO> findByCriteria(CustomerDTO customerDTO){
        return customerService.findAll(customerDTO);
    }

    @Transactional
    public CustomerDTO save(CustomerDTO customerDTO){
        if (StringUtils.isBlank(customerDTO.getTitle())){
            throw new NoMandatoryFieldsSet("Title cannot be null");
        }
        BalanceDTO balanceDTO = new BalanceDTO();
        balanceDTO.setCustomer(customerDTO);
        balanceDTO.setBalance(BigDecimal.ZERO);
        customerDTO.setBalance(balanceDTO);
        return customerService.save(customerDTO);
    }

    @Transactional
    public void delete(UUID id){
        customerService.delete(id);
    }

    @Transactional
    public CustomerDTO update(CustomerDTO customerDTO){
        CustomerDTO updateCustomer = customerService.findById(customerDTO.getId());
        BeanUtils.copyProperties(customerDTO, updateCustomer, "createDate", "title");
        if (customerDTO.getTitle() != null){
            updateCustomer.setTitle(customerDTO.getTitle());
        }
        return customerService.update(updateCustomer);
    }
}
