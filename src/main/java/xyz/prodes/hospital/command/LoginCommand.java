/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.prodes.hospital.command;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import xyz.prodes.hospital.constants.HospitalConstants;
import xyz.prodes.hospital.constants.Role;
import xyz.prodes.hospital.db.dao.DoctorDAO;
import xyz.prodes.hospital.db.dao.PatientDAO;
import xyz.prodes.hospital.db.dao.TreatmentDAO;
import xyz.prodes.hospital.entity.Treatment;
import xyz.prodes.hospital.entity.user.Doctor;
import xyz.prodes.hospital.entity.user.Patient;
import xyz.prodes.hospital.exception.HospitalException;
import xyz.prodes.hospital.exception.HospitalException.ErrorEnum;
import xyz.prodes.hospital.manager.ConfigurationManager;

/**
 *
 * @author АРТЁМ
 */
public class LoginCommand implements Command{
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String page = null;
        ConfigurationManager manager = new ConfigurationManager();
        String login = request.getParameter(HospitalConstants.PARAM_NAME_LOGIN);
        String password = request.getParameter(HospitalConstants.PARAM_NAME_PASSWORD);
        HttpSession session = request.getSession();
        DoctorDAO doctorDao = new DoctorDAO();
        Doctor doctor = doctorDao.findByLoginAndPassword(login, password);
        if (doctor != null) {
            session.setAttribute(HospitalConstants.PARAM_NAME_USER, doctor);
            session.setAttribute(HospitalConstants.PARAM_NAME_ROLE, Role.UserRole.DOCTOR);
            PatientDAO patientDao = new PatientDAO();
            List<Patient> patients = patientDao.findAll();
            request.setAttribute(HospitalConstants.PARAM_NAME_PATIENTS, patients);
            page = manager.getProperty(ConfigurationManager.ConfigEnum.MAIN_DOCTOR_PAGE_PATH);
            return page;
        }
        PatientDAO patientDao = new PatientDAO();
        Patient patient = patientDao.findByLoginAndPassword(login, password);
        if (patient != null) {
            session.setAttribute(HospitalConstants.PARAM_NAME_USER, patient);
            session.setAttribute(HospitalConstants.PARAM_NAME_ROLE, Role.UserRole.PATIENT);
            Integer patientId = patient.getId();
            TreatmentDAO treatmentDao = new TreatmentDAO();
            List<Treatment> treatments = treatmentDao.findByPatientId(patientId);
            request.setAttribute(HospitalConstants.PARAM_NAME_TREATMENTS, treatments);
            page = manager.getProperty(ConfigurationManager.ConfigEnum.MAIN_PATIENT_PAGE_PATH);
            return page;
        }
        throw new HospitalException(ErrorEnum.LOGIN_ERROR);
    }  
}
