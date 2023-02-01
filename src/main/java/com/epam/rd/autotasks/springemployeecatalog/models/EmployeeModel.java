package com.epam.rd.autotasks.springemployeecatalog.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="EMPLOYEE")
public class EmployeeModel{
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Embedded
    private FullNameModel fullName;

    @Column(name = "POSITION")
    @Enumerated(EnumType.STRING)
    private PositionModel position;

    @Column(name = "HIREDATE")
    private LocalDate hired;
    @Column(name = "SALARY")
    private BigDecimal salary;

    @OneToOne()
    @JoinColumn(name = "MANAGER")
    protected EmployeeModel manager;

    @ManyToOne
    @JoinColumn(name = "DEPARTMENT")
    private DepartmentModel department;

    public EmployeeModel() {
    }

    public EmployeeModel(EmployeeModel model) {
        if (model!=null) {
            this.id = model.id;
            this.department = model.department;
            this.fullName = model.fullName;
            this.hired = model.hired;
            this.position = model.position;
            this.salary = model.salary;
            this.manager = null;
        }
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public FullNameModel getFullName() {
        return fullName;
    }

    public void setFullName(FullNameModel fullName) {
        this.fullName = fullName;
    }

    public PositionModel getPosition() {
        return position;
    }

    public void setPosition(PositionModel position) {
        this.position = position;
    }

    public LocalDate getHired() {
        return hired;
    }

    public void setHired(LocalDate hired) {
        this.hired = hired;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public EmployeeModel getManager() {
        return manager;
    }

    public void setManager(EmployeeModel manager) {
        this.manager = manager;
    }

    public DepartmentModel getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentModel department) {
        this.department = department;
    }
}
