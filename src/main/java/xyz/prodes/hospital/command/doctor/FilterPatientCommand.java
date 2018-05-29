/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.prodes.hospital.command.doctor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import xyz.prodes.hospital.command.Command;
import xyz.prodes.hospital.constants.HospitalConstants;
import xyz.prodes.hospital.db.dao.PatientDAO;
import xyz.prodes.hospital.db.dao.PatientDAO.Parameters;
import xyz.prodes.hospital.entity.user.Patient;
import xyz.prodes.hospital.manager.ConfigurationManager;

/**
 *
 * @author АРТЁМ
 */
public class FilterPatientCommand implements Command{
    private PatientDAO patientDao;

    public FilterPatientCommand(PatientDAO patientDao) {
        this.patientDao = patientDao;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String page = null;
        ConfigurationManager manager = new ConfigurationManager();
        String name = (String) request.getParameter("name");
        String patronymic = (String) request.getParameter("patronymic");
        String surname = (String) request.getParameter("surname");
        String dateOfBirth = (String) request.getParameter("dateOfBirth");
        List<Patient> patients = null;
        Map<Parameters, String> pageParameters = new HashMap<>();
        if (!name.isEmpty()) {
            pageParameters.put(Parameters.NAME, name);
        }
        if (!patronymic.isEmpty()) {
            pageParameters.put(Parameters.PATRONYMIC, patronymic);
        }
        if (!surname.isEmpty()) {
            pageParameters.put(Parameters.SURNAME, surname);
        }
        if (!dateOfBirth.isEmpty()) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
            Date date = formatter.parse(dateOfBirth);
            formatter = new SimpleDateFormat("yyyy-MM-dd");
            String formatDate = formatter.format(date);
            pageParameters.put(Parameters.DATE_OF_BIRTH, formatDate);
        }
        patients = patientDao.findByPageParameters(pageParameters);
        request.setAttribute(HospitalConstants.PARAM_NAME_PATIENTS, patients);
        page = manager.getProperty(ConfigurationManager.ConfigEnum.MAIN_DOCTOR_PAGE_PATH);
        return page;
    }
}
