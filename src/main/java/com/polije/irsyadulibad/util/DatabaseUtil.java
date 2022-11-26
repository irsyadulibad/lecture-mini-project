/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polije.irsyadulibad.util;

import com.mysql.cj.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ibad
 */
public class DatabaseUtil {
    private static Connection conn;
    
    private static String dbhost = "localhost",
            dbdata = "pplmahasiswa",
            dbuser = "root",
            dbpass = "123";
    
    private static Integer dbport = 3306;
    
    static {
        String jdbcurl = "jdbc:mysql://"+ dbhost +":"+ dbport +"/"+ dbdata;
        
        try {
            Driver driver = new Driver();
            DriverManager.registerDriver(driver);
            
            conn = DriverManager.getConnection(jdbcurl, dbuser, dbpass);
        } catch (SQLException e) {}
    }
    
    public static Connection getConnection() {
        return conn;
    }
}
