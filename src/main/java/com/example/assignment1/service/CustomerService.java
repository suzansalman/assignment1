package com.example.assignment1.service;



import com.example.assignment1.dto.CategoryDto;
import com.example.assignment1.dto.CustomerDto;

import java.util.List;


public interface CustomerService {
    CustomerDto createCustomer(CustomerDto CustomerDto);

    List<CustomerDto> getAllCustomer();

    CustomerDto getCustomerById(long id);

    CustomerDto updateCustomer(CustomerDto CustomerDto, long id);

    void deleteCustomerById(long id);
}
