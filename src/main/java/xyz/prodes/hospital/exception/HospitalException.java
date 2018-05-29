/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.prodes.hospital.exception;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 *
 * @author АРТЁМ
 */
public class HospitalException extends Exception{

    public enum ErrorEnum {
        LOGIN_ERROR("error.login"), PAGE_NOT_FOUND("error.page"), DB_ERROR("error.db"),;
        
        private String error;

        private ErrorEnum(String error) {
            this.error = error;
        }

        public String getError() {
            return error;
        }
    }
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("localization.errors");
    private String error;

    public HospitalException() {
    }

    public HospitalException(ErrorEnum errorEnum) {
        this.error = errorEnum.getError();
    }

    public String getError() {
        return error;
    }

    public String getMessage(String key) {
        try {
            return RESOURCE_BUNDLE.getString(key);
        } catch (MissingResourceException ex) {
            return "!" + ex.getKey() + "!";
        }
    }
}
