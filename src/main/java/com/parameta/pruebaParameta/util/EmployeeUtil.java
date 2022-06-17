package com.parameta.pruebaParameta.util;

import com.parameta.pruebaParameta.payload.ValidDate;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class EmployeeUtil {
    public static ValidDate validateFormatDate(String strDate) {
        ValidDate validDate = new ValidDate();
        SimpleDateFormat sdfrmt = new SimpleDateFormat("dd/MM/yyyy");
        sdfrmt.setLenient(false);
        try {
            sdfrmt.parse(strDate);
            System.out.println(strDate + " is valid date format");
            validDate.setDate(strDate);
            validDate.setValid(true);
        } catch (ParseException e) {
            System.out.println(strDate + " is Invalid Date format");
            validDate.setValid(false);
            return validDate;
        }
        return validDate;
    }

    public static boolean validateEmptyString(String variable){
        return variable.trim().equals("");
    }

    public static boolean validateZeroAndMinorDouble(double variable){
        return variable == 0.0 || variable < 0;
    }
}
