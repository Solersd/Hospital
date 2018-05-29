/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.prodes.hospital.db.dao.helper;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import xyz.prodes.hospital.db.builder.AbstractBuilder;
import xyz.prodes.hospital.db.builder.factory.BuilderFactory;
import xyz.prodes.hospital.db.builder.factory.BuilderFactory.BuilderEnum;
import xyz.prodes.hospital.db.connection.ConnectionCreator;
import xyz.prodes.hospital.exception.HospitalException;

/**
 *
 * @author АРТЁМ
 */

public class DAOHelper<T> {
    
    public DAOHelper() {
    }
    public T getObject(String query, BuilderEnum builderType, Object... parameters) throws Exception {
        List<T> objects = getObjects(query, builderType, parameters);
        if (objects.isEmpty()) {
            return null;
        }
        return objects.get(0);
    }
    public List<T> getObjects(String query, BuilderEnum builderType, Object... parameters) throws Exception {
        List<T> objects = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        try {
            try {
                connection = new ConnectionCreator().getConnection();
                try {
                    preparedStatement = (PreparedStatement) connection.prepareStatement(query);
                    if (parameters.length > 0) {
                        setParameters(preparedStatement, parameters);
                    }
                    try {
                        result = preparedStatement.executeQuery();
                        objects = new ArrayList<>();
                        while (result.next()){
                            AbstractBuilder builder = BuilderFactory.getClassFromFactory(builderType, result);
                            objects.add((T) builder.getObject());
                        }
                    } finally {
                        if (result != null) {
                            result.close();
                        }
                    }
                } finally {
                    if (preparedStatement != null) {
                        preparedStatement.close();
                    }
                }

            } finally {
                if (connection != null) {
                    connection.close();
                }
            }
        } catch (SQLException ex) {
            throw new HospitalException(HospitalException.ErrorEnum.DB_ERROR);
        }
        return objects;
    }

    public Integer putObject(String request, Object... parameters) throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Integer elementId = null;
        try {
            try {
                connection =  new ConnectionCreator().getConnection();
                try {
                    preparedStatement = (PreparedStatement) connection.prepareStatement(request, PreparedStatement.RETURN_GENERATED_KEYS);
                    if (parameters.length > 0) {
                        setParameters(preparedStatement, parameters);
                    }
                    preparedStatement.executeUpdate();
                    ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        elementId = generatedKeys.getInt(1);
                    }
                } finally {
                    if (preparedStatement != null) {
                        preparedStatement.close();
                    }
                }
            } finally {
                if (connection != null) {
                    connection.close();
                }
            }
        } catch (SQLException ex) {
            throw new HospitalException(HospitalException.ErrorEnum.DB_ERROR);
        }
        return elementId;
    }

    private void setParameters(PreparedStatement stmt, Object... parameters) throws SQLException {
        int i = 1;
        for (Object param : parameters) {
            stmt.setObject(i++, param);
        }
    }    
}
