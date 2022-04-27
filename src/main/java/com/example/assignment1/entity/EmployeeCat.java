package com.example.assignment1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Data  //Generates getters for all fields, a useful toString method, and hashCode and equals implementations that check all non-transient fields

@AllArgsConstructor   //automatically generates a constructor with a parameter for each field in your class
@NoArgsConstructor     // generates a constructor with no parameter

@Entity                 // specifies that the class is an entity and is mapped to a database table
@Table(  //allows you to specify the details of the table that will be used to persist the entity in the database
        name = "category_tbl", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})}
)

//Example of unique constraint on a composite key
//@Table(uniqueConstraints = {
//        @UniqueConstraint(name = "UniqueNameAndIsActive", columnNames = {"name", "isActive"}),
//        })

public class EmployeeCat {
    @Id   //declare the primary key
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "address", nullable = true)
    private String Address;
    @Column(name = "ADNumber", nullable = true)
    private int AdNumber;
    @Column(name = "email", nullable = true)
    private String email;

    @Column
    private Boolean isActive;

    @Column
    private Date CreationDate;

}
