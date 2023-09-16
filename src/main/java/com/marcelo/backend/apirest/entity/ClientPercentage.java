package com.marcelo.backend.apirest.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "client_percentage")
@Getter
@Setter
public class  ClientPercentage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private Double percentage;

    @PrePersist
    protected void onCreate() {
        date = new Date();
    }

}
