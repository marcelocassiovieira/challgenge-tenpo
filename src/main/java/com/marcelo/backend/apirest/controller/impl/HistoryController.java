package com.marcelo.backend.apirest.controller.impl;

import com.marcelo.backend.apirest.controller.IHistoryController;
import com.marcelo.backend.apirest.dto.HistoryDto;
import com.marcelo.backend.apirest.service.IHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/history")
public class HistoryController implements IHistoryController {

    public static final String PAGE_DEFAULT = "0";
    public static final String CANT_ELEMENTS_DEFAULT = "10";
    IHistoryService historyService;

    @Autowired
    public HistoryController(IHistoryService historyService) {
        this.historyService = historyService;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<HistoryDto>> getPaginatedHistories(
            @RequestParam(defaultValue = PAGE_DEFAULT) int page,
            @RequestParam(defaultValue = CANT_ELEMENTS_DEFAULT) int pageSize) {
        try {
            List<HistoryDto> historyDtos = historyService.getPaginatedHistories(page, pageSize);
            return ResponseEntity.ok(historyDtos);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.emptyList());
        }
    }
}
