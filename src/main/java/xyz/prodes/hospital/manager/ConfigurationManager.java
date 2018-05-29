/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.prodes.hospital.manager;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 *
 * @author АРТЁМ
 */
public class ConfigurationManager {
    
    public enum ConfigEnum {
        DATABASE_DRIVER_NAME, DATABASE_URL, LOGIN_PAGE_PATH, MAIN_DOCTOR_PAGE_PATH, MAIN_PATIENT_PAGE_PATH, 
        SHOW_TREATMENTS_PAGE, SHOW_NEW_TREATMENT_PAGE, ERROR_PAGE_PATH, SHOW_TREATMENT_DETAILS_PAGE, 
        SHOW_EDIT_PATIENT_PAGE, SHOW_TREATMENT_DETAILS_FOR_PATIENT_PAGE_PATH, MAIN_PAGE;
    }
    
    private final String BUNDLE_NAME =  "configuration.configuration";
    private final ResourceBundle resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);

    public String getProperty(ConfigEnum key) {
        try {
            return resourceBundle.getString(key.name());
        } catch (MissingResourceException ex) {
            return "!" + ex.getKey() + "!";
        }
    }    
}
