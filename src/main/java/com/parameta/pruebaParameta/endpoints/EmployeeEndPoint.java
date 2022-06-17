package com.parameta.pruebaParameta.endpoints;

import com.parameta.pruebaParameta.dto.EmployeeDTO;
import com.parameta.pruebaParameta.gs_ws.AddEmployeeRequest;
import com.parameta.pruebaParameta.gs_ws.AddEmployeeResponse;
import com.parameta.pruebaParameta.gs_ws.EmployeeInfo;
import com.parameta.pruebaParameta.gs_ws.ServiceStatus;
import com.parameta.pruebaParameta.service.IEmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

@Endpoint
public class EmployeeEndPoint {
    private static final String NAMESPACE_URI = "http://www.parameta.com/employee-ws";
    @Autowired
    private IEmployeeService employeeService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addEmployeeRequest")
    @ResponsePayload
    public AddEmployeeResponse addEmployee(@RequestPayload AddEmployeeRequest request) throws ParseException, DatatypeConfigurationException {
        AddEmployeeResponse response = new AddEmployeeResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        EmployeeDTO employeeDTO = buildEmployeeDTO(request);
        if(employeeDTO == null){
            serviceStatus.setStatusCode("CONFILCT");
            serviceStatus.setMessage("Content Already Available");
            response.setServiceStatus(serviceStatus);
        }else{
            EmployeeInfo employeeInfo = buildEmployeeInfo(employeeDTO);
            response.setEmployeeInfo(employeeInfo);
            serviceStatus.setStatusCode("SUCCESS");
            serviceStatus.setMessage("Content Added Successfully");
            response.setServiceStatus(serviceStatus);
        }
        return response;
    }

    private EmployeeDTO buildEmployeeDTO(AddEmployeeRequest request) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setNames(request.getNames());
        employeeDTO.setLastnames(request.getLastnames());
        employeeDTO.setDocumentType(request.getDocumentType());
        employeeDTO.setDocumentNumber(request.getDocumentNumber());
        employeeDTO.setDateOfBirth(simpleDateFormat.parse(request.getDateOfBirth()));
        employeeDTO.setDateOfJoiningTheCompany(simpleDateFormat.parse(request.getDateOfJoiningTheCompany()));
        employeeDTO.setPosition(request.getPosition());
        employeeDTO.setSalary(request.getSalary());
        employeeDTO = employeeService.addEmployee(employeeDTO);
        return employeeDTO;
    }

    private EmployeeInfo buildEmployeeInfo(EmployeeDTO employeeDTO) throws DatatypeConfigurationException {
        EmployeeInfo employeeInfo = new EmployeeInfo();
        BeanUtils.copyProperties(employeeDTO, employeeInfo);
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(employeeDTO.getDateOfBirth());
        employeeInfo.setDateOfBirth(DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar));
        gregorianCalendar.setTime(employeeDTO.getDateOfJoiningTheCompany());
        employeeInfo.setDateOfJoiningTheCompany(DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar));
        return employeeInfo;
    }
}
