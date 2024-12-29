/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.EEspindola.SuperDigito.DAO;

import com.EEspindola.SuperDigito.ML.Historial;
import com.EEspindola.SuperDigito.ML.Result;
import com.EEspindola.SuperDigito.ML.SuperDigito;

/**
 *
 * @author Eduardo
 */
public interface HistorialDAO {
    
    public Result GetAll();
    public Result GetByNumero(int numero);
    public Result Add(SuperDigito superDigitio);
    public Result Update(Historial historial);
    public Result DeleteById(int idHistorial);
    public Result DeleteAll();
    
}
