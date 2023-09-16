package com.marcelo.backend.apirest.service.impl;

import com.marcelo.backend.apirest.entity.ClientPercentage;
import com.marcelo.backend.apirest.repositories.ClientPercentageRepository;
import com.marcelo.backend.apirest.service.IClientePercentageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class ClientePercentageServiceImpl implements IClientePercentageService {

    private ClientPercentageRepository clientePercentageRepository;

    @Autowired
    public ClientePercentageServiceImpl(ClientPercentageRepository clientePercentageRepository) {
        this.clientePercentageRepository = clientePercentageRepository;
    }

    @Override
    @Transactional
    public ClientPercentage save(ClientPercentage clientPercentage) {
        return clientePercentageRepository.save(clientPercentage);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ClientPercentage> getLastlientPercentage() {
        return clientePercentageRepository.getNewerPercentage();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        clientePercentageRepository.deleteById(id);
    }
}
