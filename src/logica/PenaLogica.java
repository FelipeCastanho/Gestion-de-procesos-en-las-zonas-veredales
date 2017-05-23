/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.List;
import modelo.Pena;
import persistencia.PenaJpaController;

/**
 *
 * @author Jhon
 */
public class PenaLogica {
    PenaJpaController PenaDAO;
    
    public PenaLogica(){
       PenaDAO = new PenaJpaController();
    }
    
    public List<Pena> listarPenas(){
        return PenaDAO.findPenaEntities();
    }
    
    public Pena BuscarPena(Integer IDPena){ 
        return PenaDAO.findPena(IDPena);
    }
    
}
