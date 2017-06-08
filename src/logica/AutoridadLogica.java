package logica;

import java.util.ArrayList;
import modelo.Autoridad;
import persistencia.AutoridadJpaController;

public class AutoridadLogica {
    AutoridadJpaController autoridadDAO;
    public AutoridadLogica(){
        autoridadDAO = new AutoridadJpaController();
    }
    public Autoridad buscarAutoridad(int id){
        return autoridadDAO.findAutoridad(id);
    }
    public ArrayList<Autoridad> buscarAutoridades(){
        return (ArrayList<Autoridad>) autoridadDAO.findAutoridadEntities();
    }
    public void registrarAutoridad(Autoridad autoridad) throws Exception{
        autoridad.getNombreAutoridad().charAt(0);
        autoridadDAO.create(autoridad);
    }
}