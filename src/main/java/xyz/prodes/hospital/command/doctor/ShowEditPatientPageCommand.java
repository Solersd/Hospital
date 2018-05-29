/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.prodes.hospital.command.doctor;

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
public class ShowEditPatientPageCommand implements Command {

    private PatientDAO patientDao;

    public ShowEditPatientPageCommand(PatientDAO patientDao) {
        this.patientDao = patientDao;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ConfigurationManager manager = new ConfigurationManager();
        String page = manager.getProperty(ConfigurationManager.ConfigEnum.SHOW_EDIT_PATIENT_PAGE);
        Integer id = Integer.parseInt(request.getParameter(HospitalConstants.PARAM_NAME_PATIENT_ID));
        Patient patient = patientDao.getById(id);
        request.setAttribute(HospitalConstants.PARAM_NAME_PATIENT, patient);
        return page;
    }
}
