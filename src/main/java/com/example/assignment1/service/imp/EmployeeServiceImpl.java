package com.example.assignment1.service.imp;

import com.example.assignment1.dto.CategoryDto;
import com.example.assignment1.dto.EmployeeDto;
import com.example.assignment1.entity.Category;
import com.example.assignment1.entity.EmployeeCat;
import com.example.assignment1.exception.ResourceNotFoundException;
import com.example.assignment1.reporitory.EmployeeRepo;
import com.example.assignment1.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service //To enable this class for component scanning
public class EmployeeServiceImpl implements EmployeeService {

    private com.example.assignment1.reporitory.EmployeeRepo EmployeeRepo;

    public EmployeeServiceImpl(EmployeeRepo EmployeeRepo) {
        this.EmployeeRepo = EmployeeRepo;
    }

    @Override
    public EmployeeDto createEmployee(EmployeeDto EmployeeDto) {

        // convert DTO to entity
        EmployeeCat employeeCat = mapToEntity(EmployeeDto);
        EmployeeCat newEmployee = EmployeeRepo.save(employeeCat);

        // convert entity to DTO
        EmployeeDto EmployeeResponse = mapToDTO(newEmployee);
        return EmployeeResponse;
    }

    @Override
    public List<EmployeeDto> getAllEmployee() {
        List<EmployeeCat> Employee = EmployeeRepo.findAll();
        return Employee.stream().map(EmployeeCat -> mapToDTO(EmployeeCat)).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto getEmployeeById(long id) {
        EmployeeCat EmployeeCat = EmployeeRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
        return mapToDTO(EmployeeCat);
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto EmployeeDto, long id) {
        // get Employee by id from the database
        EmployeeCat EmployeeCat = EmployeeRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));

        EmployeeCat.setName(EmployeeDto.getName());
        EmployeeCat.setDescription(EmployeeDto.getDescription());
        EmployeeCat.setIsActive(EmployeeDto.getIsActive());
        EmployeeCat.setCreationDate(EmployeeDto.getCreationDate());

        EmployeeCat updatedEmployee = EmployeeRepo.save(EmployeeCat);
        return mapToDTO(updatedEmployee);
    }

    @Override
    public void deleteEmployeeById(long id) {
        // get Employee by id from the database
        EmployeeCat EmployeeCat = EmployeeRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
        EmployeeRepo.delete(EmployeeCat);
    }

    // convert Entity into DTO
    private EmployeeDto mapToDTO(EmployeeCat EmployeeCat){
        EmployeeDto EmployeeDto = new EmployeeDto();
        EmployeeDto.setId(EmployeeCat.getId());
        EmployeeDto.setName(EmployeeCat.getName());
        EmployeeDto.setDescription(EmployeeCat.getDescription());
        EmployeeDto.setIsActive(EmployeeCat.getIsActive());
        EmployeeDto.setCreationDate(EmployeeCat.getCreationDate());
        return EmployeeDto;
    }

    // convert DTO to entity
    private EmployeeCat mapToEntity(EmployeeDto EmployeeDto){
        EmployeeCat EmployeeCat = new EmployeeCat();
        EmployeeCat.setName(EmployeeDto.getName());
        EmployeeCat.setDescription(EmployeeDto.getDescription());
        EmployeeCat.setIsActive(EmployeeDto.getIsActive());
        EmployeeCat.setCreationDate(EmployeeDto.getCreationDate());
        return EmployeeCat;
    }
}
