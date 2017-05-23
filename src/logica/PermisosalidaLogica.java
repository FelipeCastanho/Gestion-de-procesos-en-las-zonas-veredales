/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Permisosalida;
import persistencia.PermisosalidaJpaController;

/**
 *
 * @author Jhon
 */
public class PermisosalidaLogica {
    PermisosalidaJpaController PermisosalidaDAO;
     
    public PermisosalidaLogica(){
       PermisosalidaDAO = new PermisosalidaJpaController();
    }
    
    public List<Permisosalida> listarPermisosalida(){
       return PermisosalidaDAO.findPermisosalidaEntities();
    }
    
    public Permisosalida BuscarPermisosalida(Integer IDPermisosalida){ 
        return PermisosalidaDAO.findPermisosalida(IDPermisosalida);
    }
    
    public void ModificarPena(Permisosalida permiso_salida){
        try {
            PermisosalidaDAO.edit(permiso_salida);
        } catch (Exception ex) {
            Logger.getLogger(PermisosalidaLogica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}
