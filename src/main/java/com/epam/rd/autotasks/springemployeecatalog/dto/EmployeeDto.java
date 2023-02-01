package com.epam.rd.autotasks.springemployeecatalog.dto;

import com.epam.rd.autotasks.springemployeecatalog.models.EmployeeModel;

public class EmployeeDto extends EmployeeModel {

    @Override
    public void setManager(EmployeeModel manager) {
        if (manager!=null){
            super.manager = new EmployeeModel(manager);
        } else {
            super.manager = null;
        }
    }
}
