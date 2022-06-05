package com.elca.app.exercise;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    private int id;
    private String name;
    private String Date;
    private int capital;
    private String country;
    private Boolean isHeadQuarter;
}
