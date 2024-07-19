package com.profy.profuru.repository;

import com.profy.profuru.models.Executor;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.UUID;

@RepositoryRestResource(exported = false)
public interface ExecutorRepository extends CrudRepository<Executor, UUID>, JpaSpecificationExecutor<Executor> {
}
