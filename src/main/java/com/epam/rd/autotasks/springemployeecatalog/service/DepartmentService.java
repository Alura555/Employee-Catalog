package com.epam.rd.autotasks.springemployeecatalog.service;

import com.epam.rd.autotasks.springemployeecatalog.data.DepartmentRepository;
import com.epam.rd.autotasks.springemployeecatalog.models.DepartmentModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<DepartmentModel> getDepartmentByName(String department){
        return departmentRepository.findByFullName(department);
    }
}
