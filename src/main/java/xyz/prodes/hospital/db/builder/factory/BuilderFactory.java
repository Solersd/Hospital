/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.prodes.hospital.db.builder.factory;

import java.sql.ResultSet;
import xyz.prodes.hospital.db.builder.AbstractBuilder;
import xyz.prodes.hospital.db.builder.DoctorBuilder;
import xyz.prodes.hospital.db.builder.DrugBuilder;
import xyz.prodes.hospital.db.builder.PatientBuilder;
import xyz.prodes.hospital.db.builder.PrescriptionBuilder;
import xyz.prodes.hospital.db.builder.TreatmentBuilder;

/**
 *
 * @author АРТЁМ
 */
public class BuilderFactory {

    public enum BuilderEnum{
        DOCTOR, PATIENT, TREATMENT, PRESCRIPTION, DRUG;
    }

    public static AbstractBuilder getClassFromFactory(BuilderEnum type, ResultSet result){
        switch(type){
            case PATIENT:
                return new PatientBuilder(result);                
            case DOCTOR:
                return new DoctorBuilder(result);
            case TREATMENT:
                return new TreatmentBuilder(result);
            case PRESCRIPTION:
                return new PrescriptionBuilder(result);
            case DRUG:
                return new DrugBuilder(result);
            default:
                throw new IllegalArgumentException();   
        }
    }    
}
