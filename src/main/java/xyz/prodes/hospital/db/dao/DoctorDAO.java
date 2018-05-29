/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.prodes.hospital.db.dao;

import java.util.List;
import xyz.prodes.hospital.db.builder.factory.BuilderFactory;
import xyz.prodes.hospital.entity.user.Doctor;

/**
 *
 * @author АРТЁМ
 */
public class DoctorDAO extends AbstractDAO<Doctor>{
    private static final String GET_BY_LOGIN_AND_PASSWORD = "SELECT * FROM doctors WHERE login = ? AND password = MD5(?)";
    private final String DOCTORS_TABLE_NAME = "doctors";
    private static final String FIND_BY_ID = "SELECT * FROM doctors WHERE id = '?'";
    private static final String FIND_ALL = "SELECT * FROM doctors";
    
    public Doctor findByLoginAndPassword(String login, String password) throws Exception{
        Doctor result = daoHelper.getObject(GET_BY_LOGIN_AND_PASSWORD, BuilderFactory.BuilderEnum.DOCTOR, login, password);
        return result;
    }
    
    public Doctor getById(Integer id) throws Exception{
        return super.findById(FIND_BY_ID, BuilderFactory.BuilderEnum.DOCTOR, id);
    } 
    
    public List<Doctor> findAll() throws Exception{
        return super.findAll(FIND_ALL, BuilderFactory.BuilderEnum.DOCTOR);
    }
    
    public void delete(Integer id) throws Exception{
        super.delete(DOCTORS_TABLE_NAME, id);
    }
}
