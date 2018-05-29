/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.prodes.hospital.db.dao;

import java.util.List;
import xyz.prodes.hospital.db.builder.factory.BuilderFactory;
import xyz.prodes.hospital.db.dao.helper.DAOHelper;

/**
 *
 * @author АРТЁМ
 */
public abstract class AbstractDAO<T> {
    
    protected DAOHelper<T> daoHelper = new DAOHelper<>();
    
    protected T findById(String request, BuilderFactory.BuilderEnum builder, Integer id) throws Exception{
        return (T) daoHelper.getObject(request, builder, id);        
    }
    
    protected List<T> findAll(String request, BuilderFactory.BuilderEnum builder, Object... parameters) throws Exception{
        return (List<T>) daoHelper.getObjects(request, builder, parameters);
    }
    
    protected Integer save(String request, Object... parameters) throws Exception{
        return daoHelper.putObject(request, parameters);
    }
    
    protected void update(String request, Object... parameters) throws Exception{
        daoHelper.putObject(request, parameters);
    }
    
    protected void delete(String tableName, Integer id) throws Exception{
        daoHelper.putObject("DELETE FROM " + tableName + " WHERE id = ?", id);
    }
    
    protected List<T> fibdByName(String request, BuilderFactory.BuilderEnum builder, Object... parameters) throws Exception{
        return daoHelper.getObjects(request, builder, parameters);
    }
}
