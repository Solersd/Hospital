/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.prodes.hospital.db.builder;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author АРТЁМ
 */
public abstract class AbstractBuilder<T> {
    
    protected ResultSet result;
    
    public AbstractBuilder(ResultSet result) {
        this.result = result;
    }

    public abstract T getObject() throws SQLException;
}
