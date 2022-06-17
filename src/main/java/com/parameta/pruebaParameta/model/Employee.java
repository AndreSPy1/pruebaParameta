package com.parameta.pruebaParameta.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "employee")
@Getter @Setter @NoArgsConstructor @ToString
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "names")
    private String names;
    @Column(name = "lastnames")
    private String lastnames;
    @Column(name = "documentType")
    private String documentType;
    @Column(name = "documentNumber")
    private String documentNumber;
    @Column(name = "dateOfBirth")
    private Date dateOfBirth;
    @Column(name = "dateOfJoiningTheCompany")
    private Date dateOfJoiningTheCompany;
    @Column(name = "position")
    private String position;
    @Column(name = "salary")
    private double salary;
}
