/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polije.irsyadulibad.service;

import com.polije.irsyadulibad.data.LoggedMahasiswa;
import com.polije.irsyadulibad.util.DatabaseUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ibad
 */
public class AuthService {
    private static Connection conn = DatabaseUtil.getConnection();
    
    public static boolean login(String nim, String password) {
        String sql = "SELECT * FROM akun WHERE nim = ? AND password = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nim);
            stmt.setString(2, password);
            
            ResultSet result = stmt.executeQuery();
            
            if(!result.next()) return false;
            LoggedMahasiswa.setNim(result.getString("nim"));
            
            return true;
        } catch(SQLException e) {}
        
        return false;
    }
    
    public static boolean register(String nim, String name, String phone, String password) {
        String sqlMhs = "INSERT INTO mahasiswa SET nim = ?, nama = ?, telp = ?";
        String sqlAkun = "INSERT INTO akun SET nim = ?, password = ?";
        
        try(PreparedStatement stmt = conn.prepareStatement(sqlMhs);
            PreparedStatement stmt2 = conn.prepareStatement(sqlAkun);
        ) {
            stmt.setString(1, nim);
            stmt.setString(2, name);
            stmt.setString(3, phone);
            
            stmt2.setString(1, nim);
            stmt2.setString(2, password);
            
            stmt.executeUpdate();
            stmt2.executeUpdate();
            
            return true;
        } catch(SQLException e) {}
        
        return false;
    }
}
