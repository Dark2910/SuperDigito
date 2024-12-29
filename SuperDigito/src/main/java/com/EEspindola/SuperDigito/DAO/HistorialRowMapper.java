/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.EEspindola.SuperDigito.DAO;

import com.EEspindola.SuperDigito.ML.Historial;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Eduardo
 */
public class HistorialRowMapper implements RowMapper<Historial> {

    @Override
    public Historial mapRow(ResultSet rs, int rowNum) throws SQLException {
        Historial historial = new Historial();
        
        historial.setIdHistorial( rs.getInt("IdHistorial") );
        historial.setNumero( rs.getString("Numero") );
        historial.setResultado( rs.getString("Resultado") );
        historial.setFecha( rs.getString("Fecha") );
        
        return historial;
    }
}
