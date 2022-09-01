package com.example.demo.student;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Getter @Setter
public class Student {
    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName =  "student_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private Long id;
    private String name;
    private Integer age;

    public Student(){

    }

    public Student(Long id, String name, Integer age){

    }
}
