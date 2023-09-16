package com.marcelo.backend.apirest.service;

import com.marcelo.backend.apirest.entity.ClientPercentage;
import java.util.Optional;

public interface IClientePercentageService {

    public ClientPercentage save(ClientPercentage clientPercentage);

    public Optional<ClientPercentage> getLastlientPercentage();

    public void delete(Long id);
}
