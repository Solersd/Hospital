/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.prodes.hospital.db.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import xyz.prodes.hospital.db.builder.factory.BuilderFactory;
import xyz.prodes.hospital.entity.user.Patient;

/**
 *
 * @author АРТЁМ
 */
public class PatientDAO extends AbstractDAO<Patient>{
    private static final String GET_BY_LOGIN_AND_PASSWORD = "SELECT * FROM patients, doctors WHERE patients.login = ? AND patients.password = MD5(?)"
            + " AND patients.doctor_id = doctors.id";
    private static final String PATIENTS_TABLE_NAME = "patients";
    private static final String UPDATE_PATIENT = 
            "UPDATE patients SET name = ?, patronymic = ?, surname = ?, date_of_birth = ? WHERE id = ?";
    private static final String FIND_BY_ID = 
            "SELECT * FROM patients, doctors WHERE patients.id = ? AND patients.doctor_id = doctors.id";
    private static final String FIND_ALL = "SELECT * FROM patients, doctors WHERE patients.doctor_id = doctors.id";
    private static final String AND_NAME = " AND patients.name = ?";
    private static final String AND_PATRONYMIC = " AND patients.patronymic = ?";
    private static final String AND_SURNAME = " AND patients.surname = ?";
    private static final String AND_DATE_OF_BIRTH = " AND patients.date_of_birth = ?";
    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd");
    
    public enum Parameters{
        NAME, PATRONYMIC, SURNAME, DATE_OF_BIRTH
    }
    
    public Patient findByLoginAndPassword(String login, String password) throws Exception{
        Patient result = daoHelper.getObject(GET_BY_LOGIN_AND_PASSWORD, BuilderFactory.BuilderEnum.PATIENT, login, password);
        return result;
    }
    
    public Patient getById(Integer id) throws Exception{
        return super.findById(FIND_BY_ID, BuilderFactory.BuilderEnum.PATIENT, id);
    } 
    
    public List<Patient> findAll() throws Exception{
        return super.findAll(FIND_ALL, BuilderFactory.BuilderEnum.PATIENT);
    }
    
    public List<Patient> findByPageParameters(Map<Parameters, String> pageParameters) throws Exception{
        List<Object> parameters = new ArrayList<>();
        StringBuilder query = new StringBuilder();
        query.append(FIND_ALL);
        if (pageParameters.containsKey(Parameters.NAME)) {
            query.append(AND_NAME);
            parameters.add(pageParameters.get(Parameters.NAME));
        }
        if (pageParameters.containsKey(Parameters.PATRONYMIC)) {
            query.append(AND_PATRONYMIC);
            parameters.add(pageParameters.get(Parameters.PATRONYMIC));
        }
        if (pageParameters.containsKey(Parameters.SURNAME)) {
            query.append(AND_SURNAME);
            parameters.add(pageParameters.get(Parameters.SURNAME));
        }
        if (pageParameters.containsKey(Parameters.DATE_OF_BIRTH)) {
            query.append(AND_DATE_OF_BIRTH);
            parameters.add(pageParameters.get(Parameters.DATE_OF_BIRTH));
        }
        return super.daoHelper.getObjects(query.toString(), BuilderFactory.BuilderEnum.PATIENT, parameters.toArray());
    }
    
    public void update(Patient patient) throws Exception{
        String dateOfBirth = FORMATTER.format(patient.getDateOfBirth());
        super.update(UPDATE_PATIENT,
                patient.getName(),
                patient.getPatronymic(),
                patient.getSurname(),
                dateOfBirth,
                patient.getId());
    }
    
    public void delete(Integer id) throws Exception{
        super.delete(PATIENTS_TABLE_NAME, id);
    }
}
