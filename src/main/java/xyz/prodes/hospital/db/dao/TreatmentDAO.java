/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.prodes.hospital.db.dao;

import java.text.SimpleDateFormat;
import java.util.List;
import xyz.prodes.hospital.db.builder.factory.BuilderFactory;
import xyz.prodes.hospital.entity.Treatment;

/**
 *
 * @author АРТЁМ
 */
public class TreatmentDAO extends AbstractDAO<Treatment>{
    private static final String FIND_BY_ID = "SELECT * FROM treatments WHERE id = ?";
    private static final String FIND_BY_PATIENT_ID = "SELECT * FROM treatments WHERE patient_id = ?";
    private static final String INSERT_INTO_TREATMENTS = 
            "INSERT INTO treatments(complaint, diagnosis, start_date, doctor_id, patient_id)"
            + "VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_TREATMENT = "UPDATE treatments SET complaint = ?, diagnosis = ? WHERE id = ?";
    private static final String UPDATE_END_DATE = "UPDATE treatments SET end_date = ? WHERE id = ?";
    private static final String REOPEN = "UPDATE treatments SET end_date = null WHERE id = ?";
    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd");
    
    public Integer save(Treatment treatment) throws Exception{
        String startDate = FORMATTER.format(treatment.getStartDate());
        return super.save(INSERT_INTO_TREATMENTS, 
                treatment.getComplaint(),
                treatment.getDiagnosis(), 
                startDate, 
                treatment.getDoctorId(),
                treatment.getPatientId());
    }
    
    
    public void updateTreatment(Treatment treatment) throws Exception{
        super.update(UPDATE_TREATMENT, 
                treatment.getComplaint(),
                treatment.getDiagnosis(),
                treatment.getId());
    }
    
    public void updateEndDate(Treatment treatment) throws Exception{
        String endDate = FORMATTER.format(treatment.getEndDate());
        super.update(UPDATE_END_DATE, 
                endDate,
                treatment.getId());
    }
    
    public void reopen(Treatment treatment) throws Exception{
        super.update(REOPEN, treatment.getId());
    }
    
    public List<Treatment> findByPatientId(Integer patientId) throws Exception{
        return super.daoHelper.getObjects(FIND_BY_PATIENT_ID, BuilderFactory.BuilderEnum.TREATMENT, patientId);
    }
    
    public Treatment findById(Integer id) throws Exception{
        return super.daoHelper.getObject(FIND_BY_ID, BuilderFactory.BuilderEnum.TREATMENT, id);
    }
}
