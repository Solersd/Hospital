/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.prodes.hospital.constants;

/**
 *
 * @author АРТЁМ
 */
public class Role{
    public enum UserRole{
        DOCTOR, PATIENT;
    }
    
    public Role(){}
    
    public UserRole getDoctor(){
        return UserRole.DOCTOR;
    }
    
    public UserRole getPatient(){
        return UserRole.PATIENT;
    }
}
