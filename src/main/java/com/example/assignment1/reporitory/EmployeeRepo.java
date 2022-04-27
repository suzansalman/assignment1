package com.example.assignment1.reporitory;


import com.example.assignment1.entity.Category;
import com.example.assignment1.entity.EmployeeCat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<EmployeeCat, Long> {

}
