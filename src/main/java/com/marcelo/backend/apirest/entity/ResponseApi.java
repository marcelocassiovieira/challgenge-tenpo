package com.marcelo.backend.apirest.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "response")
@Getter
@Setter
public class ResponseApi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double operandOne;
    private double operandTwo;
    private double percentage;
    private double total;
    @OneToOne(mappedBy = "response")
    private History request;
}
