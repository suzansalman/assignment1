package com.example.assignment1.controller;

import com.example.assignment1.dto.CategoryDto;
import com.example.assignment1.dto.CustomerDto;
import com.example.assignment1.exception.BadRequestException;
import com.example.assignment1.service.CategoryService;
import com.example.assignment1.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/customer")
public class CustomerResource {
    private final Logger log = LoggerFactory.getLogger(CustomerResource.class);

    private CustomerService CustomerService; //the use of interface rather than class is important for loose coupling

// Constructor based  injection
    public CustomerResource(CustomerService CustomerService) {
        this.CustomerService = CustomerService;
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAllCategories() {
        return ResponseEntity.ok().body(CustomerService.getAllCustomer()); //ResponseEntity represents an HTTP response, including headers, body, and status.
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(CustomerService.getCustomerById(id));
    }


    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@Valid @RequestBody CustomerDto CustomerDto) {
        if (CustomerDto.getId() != null) {
            log.error("No ID {}", CustomerDto);
            throw new BadRequestException(CustomerResource.class.getSimpleName(),
                    "Id");
        }
        return new ResponseEntity<>(CustomerService.createCustomer(CustomerDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@Valid @RequestBody CustomerDto CustomerDto
            , @PathVariable(name = "id") long id) {
        return new ResponseEntity<>(CustomerService.updateCustomer(CustomerDto, id), HttpStatus.OK);
    }

    //maping
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable(name = "id") long id) {
        CustomerService.deleteCustomerById(id);
//        return ResponseEntity
        return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
    }
}
