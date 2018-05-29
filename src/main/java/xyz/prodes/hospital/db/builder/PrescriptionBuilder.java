/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.prodes.hospital.db.builder;

import java.sql.ResultSet;
import java.sql.SQLException;
import xyz.prodes.hospital.entity.Drug;
import xyz.prodes.hospital.entity.Prescription;

/**
 *
 * @author АРТЁМ
 */
public class PrescriptionBuilder extends AbstractBuilder<Prescription>{
    public PrescriptionBuilder(ResultSet result) {
        super(result);
    } 

    @Override
    public Prescription getObject() throws SQLException {
        Prescription prescription = new Prescription();
        prescription.setId(result.getInt("prescriptions.id"));
        prescription.setDrugId(result.getInt("prescriptions.drug_id"));
        prescription.setDosage(result.getInt("prescriptions.dosage"));
        prescription.setComment(result.getString("prescriptions.comment"));
        prescription.setCount(result.getInt("prescriptions.count"));
        Drug drug = new Drug();
        drug.setId(result.getInt("drugs.id"));
        drug.setDrugName(result.getString("drugs.drug_name"));
        prescription.setDrug(drug);
        return prescription;
    }    
}
