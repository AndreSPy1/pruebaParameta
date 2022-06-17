package com.parameta.pruebaParameta.controller;

import com.parameta.pruebaParameta.client.EmployeeClient;
import com.parameta.pruebaParameta.payload.EmployeeResponse;
import com.parameta.pruebaParameta.payload.MessageResponse;
import com.parameta.pruebaParameta.util.EmployeeControllerUtil;
import com.parameta.pruebaParameta.wsdl.AddEmployeeRequest;
import com.parameta.pruebaParameta.wsdl.AddEmployeeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeControllerUtil employeeControllerUtil;
    @Autowired
    private EmployeeClient employeeClient;

    @GetMapping("/employee")
    public ResponseEntity<Object> getEmployee(@RequestParam("names") String names,
                                              @RequestParam("lastnames") String lastnames,
                                              @RequestParam("documentType") String documentType,
                                              @RequestParam("documentNumber") String documentNumber,
                                              @RequestParam("dateOfBirth") String dateOfBirth,
                                              @RequestParam("dateOfJoiningTheCompany") String dateOfJoiningTheCompany,
                                              @RequestParam("position") String position,
                                              @RequestParam("salary") double salary) throws ParseException {

        if(!employeeControllerUtil.validFields(names, lastnames, documentType,
                documentNumber, dateOfBirth, dateOfJoiningTheCompany,
                position, salary)){
            return ResponseEntity.ok(new MessageResponse(employeeControllerUtil.getMessage()));
        }

        if(!employeeControllerUtil.validateFormatDates(dateOfBirth, dateOfJoiningTheCompany))
            return ResponseEntity.ok(new MessageResponse(employeeControllerUtil.getMessage()));

        if(!employeeControllerUtil.validateAdult(employeeControllerUtil.getDateOfBirth()))
            return ResponseEntity.ok(new MessageResponse(employeeControllerUtil.getMessage()));

        AddEmployeeRequest requestBody = buildBodyEmployeeRequest(names, lastnames, documentType, documentNumber, position, salary);

        AddEmployeeResponse response = consumingSOAPClient(requestBody);

        if(response == null)
            return ResponseEntity.internalServerError().body(
                    new MessageResponse("Error al Contactar el servicio SOAP"));

        if(response.getEmployeeInfo() == null)
            return ResponseEntity.ok(response);

        EmployeeResponse responseBody = buildResponseRest(response);

        return ResponseEntity.ok(responseBody);
    }

    private AddEmployeeRequest buildBodyEmployeeRequest(String names, String lastnames, String documentType,
                                                        String documentNumber, String position, double salary){
        AddEmployeeRequest body = new AddEmployeeRequest();
        body.setNames(names);
        body.setLastnames(lastnames);
        body.setDocumentType(documentType);
        body.setDocumentNumber(documentNumber);
        body.setDateOfBirth(employeeControllerUtil.getDateOfBirth());
        body.setDateOfJoiningTheCompany(employeeControllerUtil.getDateOfJoiningTheCompany());
        body.setPosition(position);
        body.setSalary(salary);

        return body;
    }

    private AddEmployeeResponse consumingSOAPClient(AddEmployeeRequest request){
        return employeeClient.addEmployee(request);
    }

    private EmployeeResponse buildResponseRest(AddEmployeeResponse response){
        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setServiceStatus(response.getServiceStatus());
        employeeResponse.setEmployeeInfo(response.getEmployeeInfo());
        employeeResponse.setTimeInTheCompany(employeeControllerUtil.extractTime(response.getEmployeeInfo().getDateOfJoiningTheCompany()));
        employeeResponse.setEmployeeAge(employeeControllerUtil.extractTime(response.getEmployeeInfo().getDateOfBirth()));
        return employeeResponse;
    }
}
