/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.prodes.hospital.db.builder;

import java.sql.ResultSet;
import java.sql.SQLException;
import xyz.prodes.hospital.entity.Treatment;

/**
 *
 * @author АРТЁМ
 */
public class TreatmentBuilder extends AbstractBuilder<Treatment>{
    public TreatmentBuilder(ResultSet result) {
        super(result);
    } 

    @Override
    public Treatment getObject() throws SQLException {
        Treatment treatment = new Treatment();
        treatment.setId(result.getInt("id"));
        treatment.setComplaint(result.getString("complaint"));
        treatment.setDiagnosis(result.getString("diagnosis"));
        treatment.setStartDate(result.getDate("start_date"));
        treatment.setEndDate(result.getDate("end_date"));
        treatment.setDoctorId(result.getInt("doctor_id"));
        treatment.setPatientId(result.getInt("patient_id"));
        return treatment;
    }
}
