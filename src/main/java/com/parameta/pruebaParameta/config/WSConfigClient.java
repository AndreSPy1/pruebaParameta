package com.parameta.pruebaParameta.config;

import com.parameta.pruebaParameta.client.EmployeeClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class WSConfigClient {
    @Bean
    public Jaxb2Marshaller marshaller(){
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.parameta.pruebaParameta.wsdl");
        return marshaller;
    }
    @Bean
    public EmployeeClient employeeClient(Jaxb2Marshaller marshaller){
        EmployeeClient client = new EmployeeClient();
        client.setDefaultUri("http://localhost:8080/soapws/employee.wsdl");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
