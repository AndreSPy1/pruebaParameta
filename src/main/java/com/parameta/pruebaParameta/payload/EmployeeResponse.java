package com.parameta.pruebaParameta.payload;

import com.parameta.pruebaParameta.wsdl.EmployeeInfo;
import com.parameta.pruebaParameta.wsdl.ServiceStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class EmployeeResponse {
    private ServiceStatus serviceStatus;
    private EmployeeInfo employeeInfo;
    private String timeInTheCompany;
    private String employeeAge;
}
