package com.epam.rd.autotasks.springemployeecatalog.controllers;


import com.epam.rd.autotasks.springemployeecatalog.dto.EmployeeDto;
import com.epam.rd.autotasks.springemployeecatalog.models.EmployeeModel;
import com.epam.rd.autotasks.springemployeecatalog.service.DepartmentService;
import com.epam.rd.autotasks.springemployeecatalog.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class EmployeesController {

    @Autowired
    private ModelMapper modelMapper;

    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    public EmployeesController(EmployeeService employees, DepartmentService departmentService) {
        this.employeeService = employees;
        this.departmentService = departmentService;
    }


    @GetMapping("/employees")
    public List<EmployeeModel> getEmployees(@RequestParam(value = "page", required = false) Integer page,
                                            @RequestParam(value = "size", required = false) Integer size,
                                            @RequestParam(value = "sort", required = false) String sort){
        List<EmployeeModel> employeesPage;
        if (page != null && size != null) {
            if (sort != null) {
                if (sort.equals("lastName")){
                    sort = "fullName." + sort;
                }
                employeesPage = employeeService.getAllEmployees(PageRequest.of(page, size, Sort.by(sort).ascending())).getContent();
            } else {
                employeesPage = employeeService.getAllEmployees(PageRequest.of(page, size)).getContent();
            }
        } else{
            employeesPage = employeeService.getAllEmployees();
        }
        return employeesPage
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/employees/{employee_id}")
    public EmployeeModel getEmployeeById(@PathVariable Long employeeId,
                                         @RequestParam(value = "full_chain", required = false) boolean fullChain){
        Optional<EmployeeModel> employeeModel = employeeService.getEmployeeById(employeeId);
        if (employeeModel.isEmpty()){
            return null;
        }
        if (fullChain){
            return employeeModel.get();
        } else {
            return convertToDto(employeeModel.get());
        }
    }

    @GetMapping("/employees/by_manager/{managerId}")
    public List<EmployeeModel> getEmployeeByManager(@PathVariable Long managerId,
                                                    @RequestParam(value = "page", required = false) Integer page,
                                                    @RequestParam(value = "size", required = false) Integer size,
                                                    @RequestParam(value = "sort", required = false) String sort){
        List<EmployeeModel> employeesPage;
        if (page != null && size != null) {
            if (sort != null) {
                if (sort.equals("hired")){
                    sort = "hiredate";
                }
                employeesPage = employeeService.getAllEmployeesByManager(PageRequest.of(page, size, Sort.by(sort).ascending()), managerId).getContent();
            } else {
                employeesPage = employeeService.getAllEmployeesByManager(PageRequest.of(page, size), managerId).getContent();
            }
        } else{
            employeesPage = employeeService.getEmployeeByManager(managerId);
        }
        return employeesPage.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/employees/by_department/{departmentId:\\d+}")
    public List<EmployeeModel> getEmployeeByDepartmentId(@PathVariable Long departmentId,
                                                    @RequestParam(value = "page", required = false) Integer page,
                                                    @RequestParam(value = "size", required = false) Integer size,
                                                    @RequestParam(value = "sort", required = false) String sort){
        List<EmployeeModel> employeesPage;
        if (page != null && size != null) {
            if (sort != null) {
                if (sort.equals("hired")){
                    sort = "hiredate";
                }
                employeesPage = employeeService.getAllEmployeesByDepartment(PageRequest.of(page, size, Sort.by(sort).ascending()), departmentId).getContent();
            } else {
                employeesPage = employeeService.getAllEmployeesByDepartment(PageRequest.of(page, size), departmentId).getContent();
            }
        } else{
            employeesPage = employeeService.getEmployeeByDepartment(departmentId);
        }
        return employeesPage.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/employees/by_department/{departmentName:[A-Z]+}")
    public List<EmployeeModel> getEmployeeByDepartmentName(@PathVariable String departmentName,
                                                    @RequestParam(value = "page", required = false) Integer page,
                                                    @RequestParam(value = "size", required = false) Integer size,
                                                    @RequestParam(value = "sort", required = false) String sort){
        Long departmentId = departmentService.getDepartmentByName(departmentName).get(0).getId();
        return getEmployeeByDepartmentId(departmentId, page,size, sort);
    }

    public EmployeeModel convertToDto(EmployeeModel employeeModel){
        return modelMapper.map(employeeModel, EmployeeDto.class);
    }
}
