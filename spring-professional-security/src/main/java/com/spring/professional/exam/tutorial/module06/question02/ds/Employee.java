package com.spring.professional.exam.tutorial.module06.question02.ds;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String cubicleNo;

    @SuppressWarnings("unused")
    public Employee() {
    }
}
