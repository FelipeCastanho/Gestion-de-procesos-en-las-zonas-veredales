/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.List;
import modelo.Integrante;
import persistencia.IntegranteJpaController;

/**
 *
 * @author Konita
 */
public class IntegranteLogica {
    IntegranteJpaController integranteDAO;
    
    public IntegranteLogica(){
        integranteDAO = new IntegranteJpaController();
    }
    
    public Integrante buscarIntegrante(int cedula){
        return integranteDAO.findIntegrante(cedula);
    }
    
    public List<Integrante> buscarIntgrantes(){
        return integranteDAO.findIntegranteEntities();
    }
    
}
