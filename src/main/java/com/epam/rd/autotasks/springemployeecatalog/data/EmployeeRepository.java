package com.epam.rd.autotasks.springemployeecatalog.data;

import com.epam.rd.autotasks.springemployeecatalog.models.EmployeeModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<EmployeeModel, Long> {

    @Query(value = "SELECT * FROM Employee WHERE MANAGER = :id", nativeQuery = true)
    List<EmployeeModel> findByManager(@Param("id") Long id);

    @Query(value = "SELECT * FROM Employee WHERE MANAGER = :id", nativeQuery = true)
    Page<EmployeeModel> findByManager(Pageable pageable, @Param("id") Long id);

    @Query(value = "SELECT * FROM Employee WHERE department = :id", nativeQuery = true)
    List<EmployeeModel> findByDepartment(@Param("id") Long id);

    @Query(value = "SELECT * FROM Employee WHERE department = :id", nativeQuery = true)
    Page<EmployeeModel> findByDepartment(Pageable pageable, @Param("id") Long id);

    @Override
    Page<EmployeeModel> findAll(Pageable pageable);
}
