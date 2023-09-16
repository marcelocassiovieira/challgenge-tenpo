package com.marcelo.backend.apirest.repositories;

import com.marcelo.backend.apirest.entity.ClientPercentage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface ClientPercentageRepository extends CrudRepository<ClientPercentage, Long> {
    @Query(value = "SELECT * FROM client_percentage ORDER BY date DESC LIMIT 1", nativeQuery = true)
    Optional<ClientPercentage> getNewerPercentage();

    //Otro metodo usando convencion JPA
    //ClientPercentage findTopByOrderByDateDesc();

}
