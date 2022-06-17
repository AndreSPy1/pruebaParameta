package com.parameta.pruebaParameta.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter @Setter @NoArgsConstructor @ToString
public class EmployeeDTO {
    private Integer id;
    private String names;
    private String lastnames;
    private String documentType;
    private String documentNumber;
    private Date dateOfBirth;
    private Date dateOfJoiningTheCompany;
    private String position;
    private double salary;
}
