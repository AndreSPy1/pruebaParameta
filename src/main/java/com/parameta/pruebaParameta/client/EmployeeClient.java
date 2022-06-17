package com.parameta.pruebaParameta.client;

import com.parameta.pruebaParameta.wsdl.AddEmployeeRequest;
import com.parameta.pruebaParameta.wsdl.AddEmployeeResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class EmployeeClient extends WebServiceGatewaySupport {
    public AddEmployeeResponse addEmployee(AddEmployeeRequest request){
        AddEmployeeResponse response = (AddEmployeeResponse) getWebServiceTemplate()
                .marshalSendAndReceive(request,
                        new SoapActionCallback("http://localhost:8080/soapws/addEmployeeRequest"));
        return response;
    }
}
