package logica;

import java.util.ArrayList;
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
    public ArrayList<Zonaveredal> buscarZonaVerdal(){
        return (ArrayList<Zonaveredal>) zonaveredalDAO.findZonaveredalEntities();
    }
}
