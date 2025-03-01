package com.springboot.employeepayrollapp.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private Double salary;

    @NonNull
    private String gender;

    @NonNull
    private LocalDate startDate;

    private String note;
    private String profilePic;

    @ElementCollection
    private List<String> department;
}
