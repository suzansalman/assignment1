package com.example.assignment1.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;


@Data //Generates getters for all fields, a useful toString method, and hashCode and equals implementations that check all non-transient fields

public class CustomerDto {
    private Long id;

    @NotNull
    @Size(min = 4, max = 500)
    private String name;

    private String description;

    private String CreatedBy;
    private  int phoneNum;
    private String Address;
    private Boolean isActive;

    private Date CreationDate;
}
