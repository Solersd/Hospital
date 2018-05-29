/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.prodes.hospital.command.doctor;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import xyz.prodes.hospital.command.Command;
import xyz.prodes.hospital.constants.HospitalConstants;
import xyz.prodes.hospital.db.dao.PatientDAO;
import xyz.prodes.hospital.entity.user.Patient;
import xyz.prodes.hospital.manager.ConfigurationManager;

/**
 *
 * @author АРТЁМ
 */
public class ShowDoctorMainPageCommand implements Command {

    private PatientDAO patientDao;

    public ShowDoctorMainPageCommand(PatientDAO patientDao) {
        this.patientDao = patientDao;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String page = null;
        ConfigurationManager manager = new ConfigurationManager();
        List<Patient> patients = patientDao.findAll();
        request.setAttribute(HospitalConstants.PARAM_NAME_PATIENTS, patients);
        page = manager.getProperty(ConfigurationManager.ConfigEnum.MAIN_DOCTOR_PAGE_PATH);
        return page;
    }
    
}
