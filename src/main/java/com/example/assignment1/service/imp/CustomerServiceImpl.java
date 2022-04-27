package com.example.assignment1.service.imp;

import com.example.assignment1.dto.CustomerDto;
import com.example.assignment1.entity.Customer;
import com.example.assignment1.exception.ResourceNotFoundException;


import com.example.assignment1.reporitory.CustomerRepository;
import com.example.assignment1.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service //To enable this class for component scanning
public class CustomerServiceImpl implements CustomerService {

    private com.example.assignment1.reporitory.CustomerRepository CustomerRepository;

    public CustomerServiceImpl(CustomerRepository CustomerRepository) {
        this.CustomerRepository = CustomerRepository;
    }

    @Override
    public CustomerDto createCustomer(CustomerDto CustomerDto) {

        // convert DTO to entity
       Customer Customer = mapToEntity(CustomerDto);
        Customer newCustomer = CustomerRepository.save(Customer);

        // convert entity to DTO
        CustomerDto CustomerResponse = mapToDTO(newCustomer);
        return CustomerResponse;
    }

    @Override
    public List<CustomerDto> getAllCustomer() {
        List<Customer> Customer = CustomerRepository.findAll();
        return Customer.stream().map(Customer2 -> mapToDTO(Customer2)).collect(Collectors.toList());
    }

    @Override
    public CustomerDto getCustomerById(long id) {
        Customer Customer = CustomerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));
        return mapToDTO(Customer);
    }

    @Override
    public CustomerDto updateCustomer(CustomerDto CustomerDto, long id) {
        // get Customer by id from the database
        Customer Customer = CustomerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));

        Customer.setName(CustomerDto.getName());
        Customer.setDescription(CustomerDto.getDescription());
        Customer.setIsActive(CustomerDto.getIsActive());
        Customer.setCreationDate(CustomerDto.getCreationDate());

        Customer updatedCustomer = CustomerRepository.save(Customer);
        return mapToDTO(updatedCustomer);
    }

    @Override
    public void deleteCustomerById(long id) {
        // get Customer by id from the database
        Customer Customer = CustomerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));
        CustomerRepository.delete(Customer);
    }

    // convert Entity into DTO
    private CustomerDto mapToDTO(Customer Customer){
        CustomerDto CustomerDto = new CustomerDto();
        CustomerDto.setId(Customer.getId());
        CustomerDto.setName(Customer.getName());
        CustomerDto.setDescription(Customer.getDescription());
        CustomerDto.setIsActive(Customer.getIsActive());
        CustomerDto.setCreationDate(Customer.getCreationDate());
        return CustomerDto;
    }

    // convert DTO to entity
    private Customer mapToEntity(CustomerDto CustomerDto){
        Customer Customer = new Customer();
        Customer.setName(CustomerDto.getName());
        Customer.setDescription(CustomerDto.getDescription());
        Customer.setIsActive(CustomerDto.getIsActive());
        Customer.setCreationDate(CustomerDto.getCreationDate());
        return Customer;
    }
}
