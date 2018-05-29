/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.prodes.hospital.command.patient;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import xyz.prodes.hospital.command.Command;
import xyz.prodes.hospital.constants.HospitalConstants;
import xyz.prodes.hospital.db.dao.TreatmentDAO;
import xyz.prodes.hospital.entity.Treatment;
import xyz.prodes.hospital.entity.user.Patient;
import xyz.prodes.hospital.manager.ConfigurationManager;

/**
 *
 * @author АРТЁМ
 */
public class ShowPatientMainPageCommand implements Command{
    private TreatmentDAO TreatmentDao;
    
    public ShowPatientMainPageCommand(TreatmentDAO treatmentDao){
        this.TreatmentDao = treatmentDao;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Patient patient = (Patient) session.getAttribute(HospitalConstants.PARAM_NAME_USER);
        Integer patientId = patient.getId();
        List<Treatment> treatments = TreatmentDao.findByPatientId(patientId);
        request.setAttribute(HospitalConstants.PARAM_NAME_TREATMENTS, treatments);
        ConfigurationManager manager = new ConfigurationManager();
        String page = manager.getProperty(ConfigurationManager.ConfigEnum.MAIN_PATIENT_PAGE_PATH);
        return page;
    }
    
}
