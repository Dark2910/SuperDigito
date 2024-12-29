/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.EEspindola.SuperDigito.Controller;

import com.EEspindola.SuperDigito.DAO.HistorialDAOImplementation;
import com.EEspindola.SuperDigito.ML.Historial;
import com.EEspindola.SuperDigito.ML.Result;
import com.EEspindola.SuperDigito.ML.SuperDigito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Eduardo
 */

@Controller
@RequestMapping("/SuperDigito")
public class HistorialController {
    
    @Autowired
    HistorialDAOImplementation historialDAOImplementation;
    
    
    
    @GetMapping("/Calcular")
    public String GetAll(Model model){
        
        SuperDigito superDigito = new SuperDigito();
        model.addAttribute("SuperDigito", superDigito);
        
        Result result = historialDAOImplementation.GetAll();
        model.addAttribute("Historial", result.object);
        
        return("HistorialGetAll");
    }
    
    
    
    @PostMapping("/Calcular")
    public String GetAll(Model model, @ModelAttribute SuperDigito superDigito){
        
        int numero = Integer.parseInt(superDigito.getNumero());
        
        Result resultGetByNumero = historialDAOImplementation.GetByNumero(numero);
        
        Historial historial = (Historial)resultGetByNumero.object;
        
        if(resultGetByNumero.isCorrect){
            historialDAOImplementation.Update(historial);
        } else {
            historialDAOImplementation.Add(superDigito);
        }
        
        
        model.addAttribute("SuperDigito", superDigito);
        
        Result resultGetAll = historialDAOImplementation.GetAll();
        model.addAttribute("Historial", resultGetAll.object);


        return("HistorialGetAll");
    }
    
    
    
    @GetMapping("/Delete/{idHistorial}")
    public String Delete(@PathVariable int idHistorial){
        
        if(idHistorial == 0){
            historialDAOImplementation.DeleteAll();
        } else {
            historialDAOImplementation.DeleteById(idHistorial);
        }
        
        return("redirect:/SuperDigito/Calcular");
    }
    
}
