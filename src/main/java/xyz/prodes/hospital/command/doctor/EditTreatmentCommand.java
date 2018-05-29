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
public class EditTreatmentCommand implements Command{
    private TreatmentDAO treatmentDao;
    private PrescriptionDAO prescriptionDao;
    private DrugDAO drugDao;
    
    public EditTreatmentCommand(TreatmentDAO treatmentDao, PrescriptionDAO prescriptionDao, DrugDAO drugDao){
        this.treatmentDao = treatmentDao;
        this.prescriptionDao = prescriptionDao;
        this.drugDao = drugDao;
    }

        
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String complaint = request.getParameter("complaint");
        String diagnosis = request.getParameter("diagnosis");
        Integer treatmentId = Integer.valueOf(request.getParameter("treatmentId"));
        updateTreatment(treatmentId, complaint, diagnosis);
        updatePrescriptions(request, treatmentId);
        
        Treatment treatment = treatmentDao.findById(treatmentId);
        request.setAttribute(HospitalConstants.PARAM_NAME_TREATMENT, treatment);
        List<Prescription> prescriptions = prescriptionDao.findByTreatmentId(treatmentId);
        request.setAttribute(HospitalConstants.PARAM_NAME_PRESCRIPTIONS, prescriptions);
        List<Drug> drugs = drugDao.findAll();
        request.setAttribute(HospitalConstants.PARAM_NAME_DRUGS, drugs);
        Integer patientId = Integer.valueOf(request.getParameter(HospitalConstants.PARAM_NAME_PATIENT_ID));
        request.setAttribute(HospitalConstants.PARAM_NAME_PATIENT_ID, patientId);
        
        ConfigurationManager manger = new ConfigurationManager();
        String page = manger.getProperty(ConfigurationManager.ConfigEnum.SHOW_TREATMENT_DETAILS_PAGE);
        return page;
    }

    private void updateTreatment(Integer treatmentId, String complaint, String diagnosis) throws Exception{
        Treatment treatment = new Treatment();
        treatment.setId(treatmentId);
        treatment.setComplaint(complaint);
        treatment.setDiagnosis(diagnosis);
        treatmentDao.updateTreatment(treatment);
    }

    private void updatePrescriptions(HttpServletRequest request, Integer treatmentId) throws Exception {
        int drugCount = Integer.parseInt(request.getParameter("drugCount"));
        while (drugCount >= 0) {
            String stringDrugCount = String.valueOf(drugCount);
            Prescription prescription = new Prescription();
            String stringPrescriptionId = request.getParameter("prescriptionId" + stringDrugCount);
            Integer prescriptionId;
            if (stringPrescriptionId == null) {
                prescriptionId = null;
            } else {
                prescriptionId = Integer.valueOf(stringPrescriptionId);
            }
            prescription.setId(prescriptionId);
            String drug = request.getParameter("drug" + stringDrugCount);
            prescription.setDrugId(Integer.valueOf(drug));
            String dosage = request.getParameter("dosage" + stringDrugCount);
            prescription.setDosage(Integer.valueOf(dosage));
            String comment = request.getParameter("comment" + stringDrugCount);
            prescription.setComment(comment);
            String count = request.getParameter("count" + stringDrugCount);
            prescription.setCount(Integer.valueOf(count));
            prescription.setTreatmentId(treatmentId);
            if (prescriptionId != null) {
                prescriptionDao.update(prescription);
            } else{
                prescriptionDao.save(prescription);
            }
            drugCount--;
        }
    }
}
