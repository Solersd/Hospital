/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.prodes.hospital.command.doctor;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import xyz.prodes.hospital.command.Command;
import xyz.prodes.hospital.constants.HospitalConstants;
import xyz.prodes.hospital.db.dao.PrescriptionDAO;
import xyz.prodes.hospital.db.dao.TreatmentDAO;
import xyz.prodes.hospital.entity.Prescription;
import xyz.prodes.hospital.entity.Treatment;
import xyz.prodes.hospital.entity.user.Doctor;
import xyz.prodes.hospital.manager.ConfigurationManager;

/**
 *
 * @author АРТЁМ
 */
public class NewTreatmentCommand implements Command{
    private TreatmentDAO treatmentDao;
    private PrescriptionDAO prescriptionDao;
    
    public NewTreatmentCommand(TreatmentDAO treatmentDao, PrescriptionDAO prescriptionDao){
        this.treatmentDao = treatmentDao;
        this.prescriptionDao = prescriptionDao;
    }

        
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Integer patientId = Integer.valueOf(request.getParameter(HospitalConstants.PARAM_NAME_PATIENT_ID));
        String complaint = request.getParameter("complaint");
        String diagnosis = request.getParameter("diagnosis");
        if (complaint != null && diagnosis != null) {
            HttpSession session = request.getSession();
            Doctor doctor = (Doctor) session.getAttribute(HospitalConstants.PARAM_NAME_USER);
            Integer treatmentId = createTreatment(complaint, diagnosis, doctor, patientId);
            createPrescriptions(request, treatmentId);
        }
        request.setAttribute(HospitalConstants.PARAM_NAME_PATIENT_ID, patientId);
        List<Treatment> treatments = treatmentDao.findByPatientId(patientId);
        request.setAttribute(HospitalConstants.PARAM_NAME_TREATMENTS, treatments);
        ConfigurationManager manager = new ConfigurationManager();
        String page = manager.getProperty(ConfigurationManager.ConfigEnum.SHOW_TREATMENTS_PAGE);
        return page;
    }

    private Integer createTreatment(String complaint, String diagnosis, Doctor doctor, Integer patientId) throws NumberFormatException, Exception {
        Treatment treatment = new Treatment();
        treatment.setComplaint(complaint);
        treatment.setDiagnosis(diagnosis);
        treatment.setStartDate(new Date());
        Integer doctorId = doctor.getId();
        treatment.setDoctorId(doctorId);
        treatment.setPatientId(patientId);
        Integer treatmentId = treatmentDao.save(treatment);
        return treatmentId;
    }

    private void createPrescriptions(HttpServletRequest request, Integer treatmentId) throws NumberFormatException, Exception {
        int drugCount = Integer.parseInt(request.getParameter("drugCount"));
        while (drugCount >= 0) {
            String stringDrugCount = String.valueOf(drugCount);
            Prescription prescription = new Prescription();
            String drug = request.getParameter("drug" + stringDrugCount);
            prescription.setDrugId(Integer.valueOf(drug));
            String dosage = request.getParameter("dosage" + stringDrugCount);
            prescription.setDosage(Integer.valueOf(dosage));
            String comment = request.getParameter("comment" + stringDrugCount);
            prescription.setComment(comment);
            String count = request.getParameter("count" + stringDrugCount);
            prescription.setCount(Integer.valueOf(count));
            prescription.setTreatmentId(treatmentId);
            prescriptionDao.save(prescription);
            drugCount--;
        }
    }
}
