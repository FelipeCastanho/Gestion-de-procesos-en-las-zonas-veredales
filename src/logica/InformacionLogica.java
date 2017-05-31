/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.ArrayList;
import java.util.List;
import modelo.Informacion;
import modelo.Integrante;
import persistencia.InformacionJpaController;

/**
 *
 * @author Konita
 */
public class InformacionLogica {
    InformacionJpaController informacionDAO;
    
    
    public InformacionLogica(){
        informacionDAO = new InformacionJpaController();
    }
    
    public List<Informacion> buscarInformacion(Integrante integrante){
        List<Informacion> infoEncontrada = new ArrayList<>();
        List<Informacion> informaciones = informacionDAO.findInformacionEntities();
        for (int i = 0; i < informaciones.size(); i++) {
            if(informaciones.get(i).getCedulaIntegrante().equals(integrante)){
                infoEncontrada.add(informaciones.get(i));
            }
        }
        return infoEncontrada;
    }
    
    public List<Informacion> buscarInformacion(String ubicacion){
        List<Informacion> infoEncontrada = new ArrayList<>();
        List<Informacion> informaciones = informacionDAO.findInformacionEntities();
        for (int i = 0; i < informaciones.size(); i++) {
            if(informaciones.get(i).getUbicacionInformacion().equals(ubicacion)){
                infoEncontrada.add(informaciones.get(i));
            }
        }
        return infoEncontrada;
    }
    
    public void registrarInformacion(Informacion info){
        informacionDAO.create(info);
    }
    
    public void modificarInformacion(Informacion info) throws Exception{
        informacionDAO.edit(info);
    }
    
}
