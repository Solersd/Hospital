/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.prodes.hospital.db.dao;

import java.util.List;
import xyz.prodes.hospital.db.builder.factory.BuilderFactory;
import xyz.prodes.hospital.entity.Drug;

/**
 *
 * @author АРТЁМ
 */
public class DrugDAO extends AbstractDAO<Drug>{    
    private static final String FIND_ALL = "SELECT * FROM drugs";
    private static final String FIND_BY_NAME = "SELECT * FROM drugs WHERE drug_name = ?";
    
    public List<Drug> findAll() throws Exception{
        return super.findAll(FIND_ALL, BuilderFactory.BuilderEnum.DRUG);
    }
    
    public List<Drug> findByName(String name) throws Exception{
        return super.fibdByName(FIND_BY_NAME, BuilderFactory.BuilderEnum.DRUG, name);
    }
}
