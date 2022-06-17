package com.parameta.pruebaParameta.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@Configuration
@EnableWs
public class WSConfig {
    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext){
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/soapws/*");
    }

    @Bean(name = "employee")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema employeeSchema){
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("EmployeePort");
        wsdl11Definition.setLocationUri("/soapws");
        wsdl11Definition.setTargetNamespace("http://www.parameta.com/employee-ws");
        wsdl11Definition.setSchema(employeeSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema employeeSchema(){
        return new SimpleXsdSchema(new ClassPathResource("xsds/soap.xsd"));
    }
}
