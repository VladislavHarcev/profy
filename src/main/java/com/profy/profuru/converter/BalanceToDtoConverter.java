package com.profy.profuru.converter;

import com.profy.profuru.DTO.BalanceDTO;
import com.profy.profuru.models.Balance;
import org.modelmapper.AbstractConverter;

public class BalanceToDtoConverter extends AbstractConverter<Balance, BalanceDTO> {
    @Override
    protected BalanceDTO convert(Balance source) {
        BalanceDTO destination = new BalanceDTO();
        destination.setId(source.getId());
        destination.setCreateDate(source.getCreateDate());
        destination.setBalance(source.getBalance());
        return destination;
    }
}
