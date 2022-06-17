package com.parameta.pruebaParameta.util;

import com.parameta.pruebaParameta.payload.ValidDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.xml.datatype.XMLGregorianCalendar;
import java.text.ParseException;
import java.time.*;
import java.time.format.DateTimeFormatter;

@Component
@Getter @NoArgsConstructor @ToString
public class EmployeeControllerUtil {
    private String message;
    private String dateOfBirth;
    private String dateOfJoiningTheCompany;

    public boolean validFields(String names, String lastnames, String documentType,
                                      String documentNumber, String dateOfBirth, String dateOfJoiningTheCompany,
                                      String position, double salary){
        if(EmployeeUtil.validateEmptyString(names)){
            this.message = "The \"names\" field is empty.";
            return false;
        }
        if(EmployeeUtil.validateEmptyString(lastnames)){
            this.message = "The \"lastnames\" field is empty.";
            return false;
        }
        if(EmployeeUtil.validateEmptyString(documentType)){
            this.message = "The \"documentType\" field is empty.";
            return false;
        }
        if(EmployeeUtil.validateEmptyString(documentNumber)){
            this.message = "The \"documentNumber\" field is empty.";
            return false;
        }
        if(EmployeeUtil.validateEmptyString(dateOfBirth)){
            this.message = "The \"dateOfBirth\" field is empty.";
            return false;
        }
        if(EmployeeUtil.validateEmptyString(dateOfJoiningTheCompany)) {
            this.message = "The \"dateOfJoiningTheCompany\" field is empty.";
            return false;
        }
        if(EmployeeUtil.validateEmptyString(position)){
            this.message = "The \"position\" field is empty.";
            return false;
        }
        if(EmployeeUtil.validateZeroAndMinorDouble(salary)){
            this.message = "The \"salary\" field is empty or is not greater than 0.";
            return false;
        }
        return true;
    }

    public boolean validateFormatDates(String dateOfBirth, String dateOfJoiningTheCompany){
        ValidDate validDateDateOfBirth = EmployeeUtil.validateFormatDate(dateOfBirth);
        if(!validDateDateOfBirth.isValid()) {
            this.message = "The \"dateOfBirth\" field has an invalid format, default format (dd/MM/yyyy).";
            return false;
        }
        ValidDate validDateDteOfJoiningTheCompany = EmployeeUtil.validateFormatDate(dateOfJoiningTheCompany);
        if(!validDateDteOfJoiningTheCompany.isValid()) {
            this.message = "The \"dateOfJoiningTheCompany\" field has an invalid format, default format (dd/MM/yyyy).";
            return false;
        }

        this.dateOfBirth = validDateDateOfBirth.getDate();
        this.dateOfJoiningTheCompany = validDateDteOfJoiningTheCompany.getDate();
        return true;
    }

    public boolean validateAdult(String dateOfBirth) throws ParseException {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechNacimiento = LocalDate.parse(dateOfBirth, fmt);
        LocalDate fechaActual = LocalDate.now();

        Period periodo = Period.between(fechNacimiento, fechaActual);

        if(periodo.getYears() < 18) {
            this.message = "The employee is a minor.";
            return false;
        }

        return true;
    }

    public String extractTime(XMLGregorianCalendar dateOf){
        LocalDate localDate = LocalDate.of(dateOf.getYear(), dateOf.getMonth(), dateOf.getDay());
        LocalDate fechaActual = LocalDate.now();

        Period period = Period.between(localDate, fechaActual);
        return period.getYears()+" años, "+period.getMonths()+" meses, "+period.getDays()+" dias.";
    }
}
