/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.prodes.hospital.entity.user;

/**
 *
 * @author АРТЁМ
 */
public class Patient extends AbstractUser{
    private Doctor doctor;
    private static final long serialVersionUID = 2L;
    
    public Patient(){
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
