/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.prodes.hospital.db.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import xyz.prodes.hospital.exception.HospitalException;
import xyz.prodes.hospital.manager.ConfigurationManager;
import xyz.prodes.hospital.manager.ConfigurationManager.ConfigEnum;

/**
 *
 * @author АРТЁМ
 */
public class ConnectionCreator {
    private String dbUrl;
    private Properties properties;

    public ConnectionCreator() throws Exception{
        ConfigurationManager confManager = new ConfigurationManager();
        String driver = confManager.getProperty(ConfigEnum.DATABASE_DRIVER_NAME);
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            throw new HospitalException(HospitalException.ErrorEnum.DB_ERROR);
        }
        dbUrl = confManager.getProperty(ConfigEnum.DATABASE_URL);
        properties = new Properties();
        properties.put("useUnicode", "true");
        properties.put("characterEncoding", "utf-8");
    }

    public Connection getConnection() throws Exception{
        try {
            Connection connection = (Connection) DriverManager.getConnection(dbUrl, properties);
            return connection;
        } catch (SQLException ex) {
            throw new HospitalException(HospitalException.ErrorEnum.DB_ERROR);
        }
    }    
}
