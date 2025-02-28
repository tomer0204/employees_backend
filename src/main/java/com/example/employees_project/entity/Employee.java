package com.example.employees_project.entity;
import jakarta.persistence.*;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name ="employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="first_name",length = 20)
    private String firstName;

    @Column(name = "last_name",length = 20)
    private String lastName;

    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;
}
