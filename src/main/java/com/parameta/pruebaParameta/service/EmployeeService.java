package com.parameta.pruebaParameta.service;

import com.parameta.pruebaParameta.dto.EmployeeDTO;
import com.parameta.pruebaParameta.model.Employee;
import com.parameta.pruebaParameta.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService implements IEmployeeService{
    @Autowired
    private EmployeeRepository employeeRepository;
    ModelMapper modelMapper = new ModelMapper();

    @Override
    public EmployeeDTO addEmployee(EmployeeDTO employeeDTO) {
        Employee findEmployee = employeeRepository.findByDocumentNumber(employeeDTO.getDocumentNumber());
        if(findEmployee != null)
            return null;

        Employee employee = modelMapper.map(employeeDTO, Employee.class);
        EmployeeDTO employeeDTOFeedback = modelMapper.map(employeeRepository.save(employee), EmployeeDTO.class);
        return employeeDTOFeedback;
    }
}
