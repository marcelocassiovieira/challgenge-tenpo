package com.marcelo.backend.apirest.repositories;

import com.marcelo.backend.apirest.entity.History;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface HistoryRepository extends CrudRepository<History, Long> {

    Page<History> findAll(Pageable pageable);


}
