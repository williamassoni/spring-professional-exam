package com.spring.professional.exam.tutorial.module06.question02.ds;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    private String code;
    private String firstName;
    private String lastName;
    private String address;

    @SuppressWarnings("unused")
    public Customer() {
    }
}
