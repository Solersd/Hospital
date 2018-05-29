/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.prodes.hospital.db.builder;

import java.sql.ResultSet;
import java.sql.SQLException;
import xyz.prodes.hospital.entity.user.Doctor;

/**
 *
 * @author АРТЁМ
 */
public class DoctorBuilder extends AbstractBuilder<Doctor>{
    
    public DoctorBuilder(ResultSet result) {
        super(result);
    } 

    @Override
    public Doctor getObject() throws SQLException {
        Doctor doctor = new Doctor();
        doctor.setId(result.getInt("id"));
        doctor.setLogin(result.getString("login"));
        doctor.setPassword(result.getString("password"));
        doctor.setName(result.getString("name"));
        doctor.setPatronymic(result.getString("patronymic"));
        doctor.setSurname(result.getString("surname"));
        doctor.setDateOfBirth(result.getDate("date_of_birth"));
        return doctor;
    }    
}
