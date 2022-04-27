package com.example.assignment1.controller;

import com.example.assignment1.dto.CategoryDto;
import com.example.assignment1.dto.EmployeeDto;
import com.example.assignment1.exception.BadRequestException;
import com.example.assignment1.service.CategoryService;
import com.example.assignment1.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/employee")
public class EmployeeResource {
    private final Logger log = LoggerFactory.getLogger(EmployeeResource.class);

    private EmployeeService EmployeeService; //the use of interface rather than class is important for loose coupling

// Constructor based  injection
    public EmployeeResource(EmployeeService EmployeeService) {
        this.EmployeeService = EmployeeService;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllCategories() {
        return ResponseEntity.ok().body(EmployeeService.getAllEmployee()); //ResponseEntity represents an HTTP response,
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getCategoryById(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(EmployeeService.getEmployeeById(id));
    }


    @PostMapping
    public ResponseEntity<EmployeeDto> createCategory(@Valid @RequestBody EmployeeDto employeeDto) {
        if (employeeDto.getId() != null) {
            log.error("No ID {}", EmployeeService);
            throw new BadRequestException(EmployeeResource.class.getSimpleName(),
                    "Id");
        }
        return new ResponseEntity<>(EmployeeService.createEmployee(employeeDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateCategory(@Valid @RequestBody EmployeeDto employeeDto
            , @PathVariable(name = "id") long id) {
        return new ResponseEntity<>(EmployeeService.updateEmployee(employeeDto, id), HttpStatus.OK);
    }

    //maping
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable(name = "id") long id) {
        EmployeeService.deleteEmployeeById(id);
//        return ResponseEntity
        return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
    }
}
