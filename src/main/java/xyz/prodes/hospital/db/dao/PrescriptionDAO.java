/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.prodes.hospital.db.dao;

import java.util.List;
import xyz.prodes.hospital.db.builder.factory.BuilderFactory;
import xyz.prodes.hospital.entity.Prescription;

/**
 *
 * @author АРТЁМ
 */
public class PrescriptionDAO extends AbstractDAO<Prescription>{
    private static final String INSERT_INTO_PRESCRIPTION = 
            "INSERT INTO prescriptions(drug_id, dosage, comment, count, treatment_id)"
            + "VALUES (?, ?, ?, ?, ?)";
    private static final String FIND_BY_TREATMENT_ID = "SELECT * FROM drugs, prescriptions "
            + "WHERE drugs.id = prescriptions.drug_id AND treatment_id = ?";
    private static final String UPDATE_PRESCRIPTION = "UPDATE prescriptions SET drug_id = ?, dosage = ?, comment = ?, count = ? WHERE id = ?";
    
    public Integer save(Prescription prescription) throws Exception{
        return super.save(INSERT_INTO_PRESCRIPTION,
                prescription.getDrugId(),
                prescription.getDosage(),
                prescription.getComment(),
                prescription.getCount(),
                prescription.getTreatmentId());
    }    
    
    public List<Prescription> findByTreatmentId(Integer treatmentId) throws Exception{
        return super.daoHelper.getObjects(FIND_BY_TREATMENT_ID, BuilderFactory.BuilderEnum.PRESCRIPTION, treatmentId);
    }
    
    public void update(Prescription prescription) throws Exception{
        super.update(UPDATE_PRESCRIPTION,
                prescription.getDrugId(),
                prescription.getDosage(),
                prescription.getComment(),
                prescription.getCount(),
                prescription.getId());
    }
}
