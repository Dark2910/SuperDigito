/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.EEspindola.SuperDigito.ML;

/**
 *
 * @author Eduardo
 */
public class Historial {
    
    private int IdHistorial;
    private String Numero;
    private String Resultado;
    private String Fecha;
    
    
    public int getIdHistorial(){
        return this.IdHistorial;
    }
    public void setIdHistorial(int idHistorial){
        this.IdHistorial = idHistorial;
    }
    
    public String getNumero(){
        return this.Numero;
    }
    public void setNumero(String numero){
        this.Numero = numero;
    }
    
    public String getResultado(){
        return this.Resultado;
    }
    public void setResultado(String resultado){
        this.Resultado = resultado;
    }
    
    public String getFecha(){
        return this.Fecha;
    }
    public void setFecha(String fecha){
        this.Fecha = fecha;
    }
    
}
