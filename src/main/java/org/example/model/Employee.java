package org.example.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table(name = "employees")
public class Employee {
    @Id
    private Long id;
    private String name;
    private Integer salary;
    private Integer age;
    private String sex;
}
