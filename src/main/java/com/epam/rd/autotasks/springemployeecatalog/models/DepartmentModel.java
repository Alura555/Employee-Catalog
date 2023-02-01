package com.epam.rd.autotasks.springemployeecatalog.models;

import javax.persistence.*;

@Entity
@Table(name = "DEPARTMENT")
public class DepartmentModel {

    @Id
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    private String fullName;
    @Column(name = "LOCATION")
    private String position;

    
    public DepartmentModel() {
    }

    public DepartmentModel(Long id, String name, String location) {
        this.id = id;
        this.fullName = name;
        this.position = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
