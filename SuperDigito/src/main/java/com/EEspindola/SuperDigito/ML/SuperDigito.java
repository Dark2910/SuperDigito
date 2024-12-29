/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.EEspindola.SuperDigito.ML;

/**
 *
 * @author Eduardo
 */
public class SuperDigito {
    
    private String Numero;
    private String Resultado;

    public String getNumero() {
        return Numero;
    }
    public void setNumero(String Numero) {
        this.Numero = Numero;
        this.Resultado = CalcularResultado(Numero);
    }

    public String getResultado() {
        return Resultado;
    }

    public String CalcularResultado(String numero) {

        if (numero.length() == 1) {
            return numero;
        }

        int dividendo = Integer.parseInt(numero);
        int divisor = 10;

        int residuo = 0;

        while (dividendo > 0) {
            residuo += Math.floorMod(dividendo, divisor);
            dividendo = (int) Math.floor(dividendo / divisor);
        }

        return CalcularResultado(residuo + "");
    }
    
    
}
