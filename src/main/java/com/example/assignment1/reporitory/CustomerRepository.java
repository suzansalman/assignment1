package com.example.assignment1.reporitory;


import com.example.assignment1.entity.Category;
import com.example.assignment1.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
