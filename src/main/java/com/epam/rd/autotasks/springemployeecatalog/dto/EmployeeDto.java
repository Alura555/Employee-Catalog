package com.epam.rd.autotasks.springemployeecatalog.dto;

import com.epam.rd.autotasks.springemployeecatalog.models.EmployeeModel;

public class EmployeeDto extends EmployeeModel {

    @Override
    public void setManager(EmployeeModel manager) {
        if (manager!=null){
            try {
                super.manager = (EmployeeModel) manager.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            super.manager.setManager(null);
        } else {
            super.manager = null;
        }
    }
}
