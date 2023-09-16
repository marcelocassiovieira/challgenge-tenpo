package com.marcelo.backend.apirest.service;

import com.marcelo.backend.apirest.dto.HistoryDto;
import com.marcelo.backend.apirest.entity.History;
import java.util.List;

public interface IHistoryService {

    public History save(History history);

    public List<HistoryDto> getPaginatedHistories(int page, int pageSize);





}
