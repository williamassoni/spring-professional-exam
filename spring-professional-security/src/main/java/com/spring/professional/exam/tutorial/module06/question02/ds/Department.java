package com.spring.professional.exam.tutorial.module06.question02.ds;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    private String code;
    private String name;
    private String country;

    @SuppressWarnings("unused")
    public Department() {
    }
}
