package com.profy.profuru.repository;

import com.profy.profuru.models.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.UUID;


@RepositoryRestResource(exported = false)
public interface CustomerRepository extends CrudRepository<Customer, UUID> {
    List<Customer> findByTitleContainingIgnoreCase(String titlePart);
}