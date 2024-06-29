package com.javaweb.Utils;

public class validateDataInput {
    public static Boolean StringValidate(String data) {
    	return data != null && !data.equals("");
    }
    public static Boolean isNumber(String data) {
        try {
            Double.parseDouble(data);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
