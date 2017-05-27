package logica;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import modelo.Autoridadingresazonaveredal;
import persistencia.AutoridadingresazonaveredalJpaController;

public class IngresoAutoridadLogica {
    AutoridadingresazonaveredalJpaController ingresoAutoridadDAO;
    
    public  IngresoAutoridadLogica(){
        ingresoAutoridadDAO = new AutoridadingresazonaveredalJpaController();
    }
    public void registrarIngresoAutoridad(Autoridadingresazonaveredal ingreso) throws Exception{
        Calendar calendar = Calendar.getInstance();
        Date fecha = new Date(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));
        ingreso.setFechaIngreso(fecha);
        ingresoAutoridadDAO.create(ingreso);
    }
    public void registrarSalidaAutoridad(Autoridadingresazonaveredal salida) throws Exception{
        Calendar calendar = Calendar.getInstance();
        Date fecha = new Date(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));
        salida.setFechaSalida(fecha);
        ingresoAutoridadDAO.edit(salida);
    }
    public ArrayList<Autoridadingresazonaveredal> buscarIngresoAutoridad(){
        return (ArrayList<Autoridadingresazonaveredal>) ingresoAutoridadDAO.findAutoridadingresazonaveredalEntities();
    }
    public Autoridadingresazonaveredal buscarIngresoAutoridad(int id){
        return ingresoAutoridadDAO.findAutoridadingresazonaveredal(id);
    }
}
