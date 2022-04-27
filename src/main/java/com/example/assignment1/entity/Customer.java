package com.example.assignment1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Data  //Generates getters for all fields,
@AllArgsConstructor   //automatically generates a constructor with a parameter for each field in your class
@NoArgsConstructor     // generates a constructor with no parameter

@Entity                 // specifies that the class is an entity and is mapped to a database table
@Table(  //allows you to specify the details of the table that will be used to persist the entity in the database
        name = "category_tbl", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})}
)


public class Customer {
    @Id   //declare the primary key
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "address")
    private String Address;
    @Column(name = "phoneNum")
    private int phoneNum;

    @Column
    private Boolean isActive;

    @Column
    private Date CreationDate;

}
