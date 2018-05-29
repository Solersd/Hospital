/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.prodes.hospital.command.patient;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import xyz.prodes.hospital.command.Command;
import xyz.prodes.hospital.constants.HospitalConstants;
import xyz.prodes.hospital.db.dao.DrugDAO;
import xyz.prodes.hospital.db.dao.PrescriptionDAO;
import xyz.prodes.hospital.db.dao.TreatmentDAO;
import xyz.prodes.hospital.entity.Drug;
import xyz.prodes.hospital.entity.Prescription;
import xyz.prodes.hospital.entity.Treatment;
import xyz.prodes.hospital.manager.ConfigurationManager;

/**
 *
 * @author АРТЁМ
 */
public class ShowTreatmentDetailsForPatientCommand implements Command{
    private TreatmentDAO treatmentDao;
    private PrescriptionDAO prescriptionDao;
    private DrugDAO drugDao;
    
    public ShowTreatmentDetailsForPatientCommand(TreatmentDAO treatmentDao, PrescriptionDAO prescriptionDao, DrugDAO drugDao){
        this.prescriptionDao = prescriptionDao;
        this.treatmentDao = treatmentDao;
        this.drugDao = drugDao;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Integer treatmentId = Integer.valueOf(request.getParameter(HospitalConstants.PARAM_NAME_TREATMENT_ID));
        Treatment treatment = treatmentDao.findById(treatmentId);
        request.setAttribute(HospitalConstants.PARAM_NAME_TREATMENT, treatment);
        List<Prescription> prescriptions = prescriptionDao.findByTreatmentId(treatmentId);
        request.setAttribute(HospitalConstants.PARAM_NAME_PRESCRIPTIONS, prescriptions);
        List<Drug> drugs = drugDao.findAll();
        request.setAttribute(HospitalConstants.PARAM_NAME_DRUGS, drugs);
        ConfigurationManager manager = new ConfigurationManager();
        String page = manager.getProperty(ConfigurationManager.ConfigEnum.SHOW_TREATMENT_DETAILS_FOR_PATIENT_PAGE_PATH);
        return page;
    }
}
