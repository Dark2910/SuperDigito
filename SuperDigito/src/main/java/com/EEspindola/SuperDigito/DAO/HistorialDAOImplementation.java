/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.EEspindola.SuperDigito.DAO;

import com.EEspindola.SuperDigito.ML.Historial;
import com.EEspindola.SuperDigito.ML.Result;
import com.EEspindola.SuperDigito.ML.SuperDigito;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Eduardo
 */

@Repository
public class HistorialDAOImplementation implements HistorialDAO{
    
    JdbcTemplate jdbcTemplate;
    
    @Autowired
    public HistorialDAOImplementation(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Result GetAll() {
        Result result = new Result();
        String query = "{CALL HistorialGetAll(?)}";
        
        try {
            List<Historial> historial = jdbcTemplate.execute(query, (CallableStatementCallback<List<Historial>>) callableStatementCallback -> {
                
                callableStatementCallback.registerOutParameter("pCursor", Types.REF_CURSOR);
                
                callableStatementCallback.execute();
                
                ResultSet rs = (ResultSet)callableStatementCallback.getObject("pCursor");
                
                HistorialRowMapper historialRowMapper = new HistorialRowMapper();
                
                List<Historial> historialList = new ArrayList<Historial>();
                
                while(rs.next()){
                    Historial historialRecuperado = historialRowMapper.mapRow(rs, rs.getRow());
                    historialList.add(historialRecuperado);
                }
            
                return historialList;
            });
            
            if(historial != null){
                result.isCorrect = true;
                result.object = historial;
            } else {
                result.isCorrect = false;
                result.errorMessage="No se encontraron registros.";
                
            }
            
        } catch (Exception e) {
            result.isCorrect = false;
            result.exception = e;
            result.errorMessage = e.getMessage();
        }
        
        return result;
    }

    @Override
    public Result GetByNumero(int numero) {
        Result result = new Result();
        String query = "{CALL HistorialGetByNumero(?, ?)}";
        
        try {
            Historial historial = jdbcTemplate.execute(query, (CallableStatementCallback<Historial>) callableStatementCallback -> {
            
                callableStatementCallback.registerOutParameter("pCursor", Types.REF_CURSOR);
                callableStatementCallback.setString("pNumero", Integer.toString(numero));
                
                callableStatementCallback.execute();
                
                ResultSet rs = (ResultSet)callableStatementCallback.getObject("pCursor");
                
                HistorialRowMapper historialRowMapper = new HistorialRowMapper();
                
                rs.next();
                return historialRowMapper.mapRow(rs, rs.getRow());
            });
            
            if(historial != null) {
                result.isCorrect = true;
                result.object = historial;
            } else {
                result.isCorrect = false;
                result.errorMessage = "No se encontro el registro.";
            }
            
        } catch (Exception e) {
              result.isCorrect = false;
              result.exception = e;
              result.errorMessage = e.getMessage();
        }
        
        return result;
    }

    @Override
    public Result Add(SuperDigito superDigito) {
        Result result = new Result();
        String query = "{CALL HistorialAdd(?, ?)}";
        
        try {
            int rowAffected = jdbcTemplate.execute(query, ( CallableStatementCallback<Integer>) callableStatementCallback -> {
                
                callableStatementCallback.setString("pNumero", superDigito.getNumero());
                callableStatementCallback.setString("pResultado", superDigito.getResultado());
                
                callableStatementCallback.execute();
                
                return callableStatementCallback.getUpdateCount();
            });
            
            if(rowAffected != 0){
                result.isCorrect = true;
            } else {
                result.isCorrect = false;
                result.errorMessage= "Error al agregar.";
            }
            
        } catch (Exception e) {
            result.isCorrect = false;
            result.exception = e;
            result.errorMessage = e.getMessage();
        }
        
        return result;
    }

    @Override
    public Result Update(Historial historial) {
        Result result = new Result();
        String query = "{CALL HistorialUpdate(?)}";
        
        try {
            int rowAffected = jdbcTemplate.execute(query, ( CallableStatementCallback<Integer>)  callableStatementCallback -> {
                
                callableStatementCallback.setInt("pIdHistorial", historial.getIdHistorial());
                
                callableStatementCallback.execute();
                
                return callableStatementCallback.getUpdateCount();
            });
            
            
            if(rowAffected != 0){
                result.isCorrect= false;
            } else {
                result.isCorrect= false;
                result.errorMessage= "Error al actualizar.";
            }
            
        } catch (Exception e) {
            result.isCorrect= false;
            result.exception= e;
            result.errorMessage= e.getMessage();
        }
        
        return result;
    }

    @Override
    public Result DeleteById(int idHistorial) {
        Result result = new Result();
        String query = "{CALL HistorialDeleteById(?)}";
        
        try {
            int rowAffected = jdbcTemplate.execute(query, (CallableStatementCallback<Integer>) callableStatementCallback -> {
                
                callableStatementCallback.setInt("pIdHistorial", idHistorial);
                
                callableStatementCallback.execute();
                
                return callableStatementCallback.getUpdateCount();
            });
            
            if (rowAffected != 0) {
                result.isCorrect = false;
            } else {
                result.isCorrect = false;
                result.errorMessage = "Error al eliminar.";
            }
            
        } catch (Exception e) {
            result.isCorrect= false;
            result.exception= e;
            result.errorMessage= e.getMessage();
        }

        return result;
    }

    @Override
    public Result DeleteAll() {
        Result result = new Result();
        String query = "{CALL HistorialDeleteAll()}";
        
        try {
            int rowAffected = jdbcTemplate.execute(query, (CallableStatementCallback<Integer>) callableStatementCallback -> {

                callableStatementCallback.execute();

                return callableStatementCallback.getUpdateCount();
            });

            if (rowAffected != 0) {
                result.isCorrect = false;
            } else {
                result.isCorrect = false;
                result.errorMessage = "Error al eliminar.";
            }
            
        } catch (Exception e) {
            result.isCorrect= false;
            result.exception= e;
            result.errorMessage= e.getMessage();
            
        }
        
        return result;
    }
    
}
