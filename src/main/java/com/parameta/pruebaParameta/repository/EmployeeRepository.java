package com.parameta.pruebaParameta.repository;

import com.parameta.pruebaParameta.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee findByDocumentNumber(String documentNumber);
}
