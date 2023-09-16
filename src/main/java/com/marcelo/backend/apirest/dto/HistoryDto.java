package com.marcelo.backend.apirest.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class HistoryDto {
    private Long id;
    private Date date;
    private String call;
    private ResponseDto response;

}
