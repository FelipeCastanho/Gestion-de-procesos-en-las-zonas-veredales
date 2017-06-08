package logica;

import java.util.List;
import modelo.Zonaveredal;
import persistencia.ZonaveredalJpaController;

public class ZonaveredalLogica {
    ZonaveredalJpaController zonaveredalDAO;
    public ZonaveredalLogica(){
        zonaveredalDAO = new ZonaveredalJpaController();
    }
    public Zonaveredal buscarZonaVeredal(int id){
        return zonaveredalDAO.findZonaveredal(id);
    }
    public List<Zonaveredal> buscarZonaVerdal(){
        return zonaveredalDAO.findZonaveredalEntities();
    }
    public Zonaveredal buscarZonaVeredalPorNombre(String nombre){
        List<Zonaveredal> zonasVeredales = buscarZonaVerdal();
        for(int i = 0; i < zonasVeredales.size(); i++){
            if(zonasVeredales.get(i).getNombreZonaVeredal().equals(nombre)) return zonasVeredales.get(i);
        }
        return null;
    }
}
