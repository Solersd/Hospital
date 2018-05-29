/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.prodes.hospital.db.builder;

import java.sql.ResultSet;
import java.sql.SQLException;
import xyz.prodes.hospital.entity.user.Doctor;
import xyz.prodes.hospital.entity.user.Patient;

/**
 *
 * @author АРТЁМ
 */
public class PatientBuilder extends AbstractBuilder<Patient>{
    public PatientBuilder(ResultSet result) {
        super(result);
    } 

    @Override
    public Patient getObject() throws SQLException {
        Patient patient = new Patient();
        patient.setId(result.getInt("patients.id"));
        patient.setLogin(result.getString("patients.login"));
        patient.setPassword(result.getString("patients.password"));
        patient.setName(result.getString("patients.name"));
        patient.setPatronymic(result.getString("patients.patronymic"));
        patient.setSurname(result.getString("patients.surname"));            
        patient.setDateOfBirth(result.getDate("patients.date_of_birth"));
        Doctor doctor = new Doctor();
        doctor.setId(result.getInt("doctors.id"));
        doctor.setLogin(result.getString("doctors.login"));
        doctor.setPassword(result.getString("doctors.password"));
        doctor.setName(result.getString("doctors.name"));
        doctor.setPatronymic(result.getString("doctors.patronymic"));
        doctor.setSurname(result.getString("doctors.surname")); 
        doctor.setDateOfBirth(result.getDate("doctors.date_of_birth"));
        patient.setDoctor(doctor);
        return patient;
    }     
}
