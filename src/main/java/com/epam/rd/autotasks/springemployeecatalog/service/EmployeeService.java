package com.epam.rd.autotasks.springemployeecatalog.service;

import com.epam.rd.autotasks.springemployeecatalog.data.EmployeeRepository;
import com.epam.rd.autotasks.springemployeecatalog.models.EmployeeModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Page<EmployeeModel> getAllEmployees(Pageable pageable){
        return employeeRepository.findAll(pageable);
    }

    public List<EmployeeModel> getAllEmployees(){
        return employeeRepository.findAll();
    }

    public Page<EmployeeModel> getAllEmployeesByManager(Pageable pageable, Long id){
        return employeeRepository.findByManager(pageable, id);
    }

    public List<EmployeeModel> getEmployeeByManager(Long id){
        return employeeRepository.findByManager(id);
    }

    public Page<EmployeeModel> getAllEmployeesByDepartment(Pageable pageable, Long id){
        return employeeRepository.findByDepartment(pageable, id);
    }

    public List<EmployeeModel> getEmployeeByDepartment(Long id){
        return employeeRepository.findByDepartment(id);
    }

    public Optional<EmployeeModel> getEmployeeById(Long id){
        return employeeRepository.findById(id);
    }

}
