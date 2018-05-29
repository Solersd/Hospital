/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.prodes.hospital.db.builder;

import java.sql.ResultSet;
import java.sql.SQLException;
import xyz.prodes.hospital.entity.Drug;

/**
 *
 * @author АРТЁМ
 */
public class DrugBuilder extends AbstractBuilder<Drug>{
    
    public DrugBuilder(ResultSet result) {
        super(result);
    } 

    @Override
    public Drug getObject() throws SQLException {
        Drug drug = new Drug();
        drug.setId(result.getInt("id"));
        drug.setDrugName(result.getString("drug_name"));
        return drug;
    }
}
