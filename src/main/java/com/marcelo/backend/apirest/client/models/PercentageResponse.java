package com.marcelo.backend.apirest.client.models;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PercentageResponse implements Serializable {
    private double percentage;
}
