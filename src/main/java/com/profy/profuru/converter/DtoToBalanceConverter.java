package com.profy.profuru.converter;

import com.profy.profuru.DTO.BalanceDTO;
import com.profy.profuru.models.Balance;
import org.modelmapper.AbstractConverter;

public class DtoToBalanceConverter extends AbstractConverter<BalanceDTO, Balance> {
    @Override
    protected Balance convert(BalanceDTO source) {
        Balance destination = new Balance();
        destination.setId(source.getId());
        destination.setCreateDate(source.getCreateDate());
        destination.setBalance(source.getBalance());
        return destination;
    }
}
