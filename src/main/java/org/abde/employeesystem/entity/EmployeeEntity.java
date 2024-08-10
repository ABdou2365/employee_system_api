package org.abde.employeesystem.entity;

import jakarta.persistence.*;
import lombok.Data;

@Table(name="employees")
@Data
@Entity
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;

    private String lastName;

    private String email;

}
