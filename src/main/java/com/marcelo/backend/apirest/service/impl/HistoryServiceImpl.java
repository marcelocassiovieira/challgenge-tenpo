package com.marcelo.backend.apirest.service.impl;

import com.marcelo.backend.apirest.dto.HistoryDto;
import com.marcelo.backend.apirest.entity.History;
import com.marcelo.backend.apirest.exception.HistoryException;
import com.marcelo.backend.apirest.repositories.HistoryRepository;
import com.marcelo.backend.apirest.service.IHistoryService;
import com.marcelo.backend.apirest.utils.ExceptionsConstantes;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HistoryServiceImpl implements IHistoryService {
    private ModelMapper modelMapper;

    @Autowired
    public HistoryServiceImpl(HistoryRepository historyRepository,
                              ModelMapper modelMapper) {
        this.historyRepository = historyRepository;
        this.modelMapper = modelMapper;
    }

    private HistoryRepository historyRepository;

    @Override
    public History save(History history) {
        return historyRepository.save(history);
    }

    @Override
    public List<HistoryDto> getPaginatedHistories(int page, int pageSize) {
        try {
            Pageable pageable = PageRequest.of(page, pageSize);
            List<History> histories = historyRepository.findAll(pageable).getContent();
            List<HistoryDto> historyDtos = convertHistoryListToDtoList(histories);
            return historyDtos;
        } catch (Exception e) {
            e.printStackTrace();
            throw new HistoryException(ExceptionsConstantes.ERROR_AL_OBTENER_HISTORIAS_PAGINADAS, e);
        }
    }

    public List<HistoryDto> convertHistoryListToDtoList(List<History> historyList) {
        try {
            return historyList.stream()
                    .map(history -> modelMapper.map(history, HistoryDto.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            throw new HistoryException(ExceptionsConstantes.ERROR_AL_CONVERTIR_LISTA_DE_HISTORIAS_A_LISTA_DE_DTOS, e);
        }
    }
}
