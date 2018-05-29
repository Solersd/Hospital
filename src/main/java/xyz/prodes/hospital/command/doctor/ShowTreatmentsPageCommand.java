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
import xyz.prodes.hospital.db.dao.TreatmentDAO;
import xyz.prodes.hospital.entity.Treatment;
import xyz.prodes.hospital.manager.ConfigurationManager;

/**
 *
 * @author АРТЁМ
 */
public class ShowTreatmentsPageCommand implements Command{
    private TreatmentDAO treatmentDao;
    
    public ShowTreatmentsPageCommand(TreatmentDAO treatmentDao){
        this.treatmentDao = treatmentDao;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ConfigurationManager manager = new ConfigurationManager();
        String page = manager.getProperty(ConfigurationManager.ConfigEnum.SHOW_TREATMENTS_PAGE);
        Integer patientId = Integer.valueOf(request.getParameter(HospitalConstants.PARAM_NAME_PATIENT_ID));
        List<Treatment> treatments = treatmentDao.findByPatientId(patientId);
        request.setAttribute(HospitalConstants.PARAM_NAME_TREATMENTS, treatments);
        request.setAttribute(HospitalConstants.PARAM_NAME_PATIENT_ID, patientId);
        return page;
    }
}
