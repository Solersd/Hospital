/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.prodes.hospital.command.doctor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import xyz.prodes.hospital.command.Command;
import xyz.prodes.hospital.constants.HospitalConstants;
import xyz.prodes.hospital.db.dao.PatientDAO;
import xyz.prodes.hospital.entity.user.Patient;
import xyz.prodes.hospital.manager.ConfigurationManager;
import xyz.prodes.hospital.manager.ConfigurationManager.ConfigEnum;

/**
 *
 * @author АРТЁМ
 */
public class EditPatientCommand implements Command{
    private PatientDAO patientDao;

    public EditPatientCommand(PatientDAO patientDao){
        this.patientDao = patientDao;
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String name = request.getParameter("name");
        String patronymic = request.getParameter("patronymic");
        String surname = request.getParameter("surname");
        String dateOfBirth = request.getParameter("dateOfBirth");        
        if (name != null && patronymic != null && surname != null && dateOfBirth != null) {
            Patient patient = new Patient();
            Integer patientId = Integer.valueOf(request.getParameter("patientId"));
            patient.setId(patientId);
            patient.setName(name);
            patient.setPatronymic(patronymic);
            patient.setSurname(surname);
            SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
            Date date = formatter.parse(dateOfBirth);
            patient.setDateOfBirth(date);
            patientDao.update(patient);
        }
        List<Patient> patients = patientDao.findAll();
        request.setAttribute(HospitalConstants.PARAM_NAME_PATIENTS, patients);
        ConfigurationManager manager = new ConfigurationManager();
        String page = manager.getProperty(ConfigEnum.MAIN_DOCTOR_PAGE_PATH);
        return page;
    }
}
