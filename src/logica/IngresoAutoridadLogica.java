package logica;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import modelo.Autoridad;
import modelo.Autoridadingresazonaveredal;
import persistencia.AutoridadingresazonaveredalJpaController;

public class IngresoAutoridadLogica {
    AutoridadingresazonaveredalJpaController ingresoAutoridadDAO;
    
    public  IngresoAutoridadLogica(){
        ingresoAutoridadDAO = new AutoridadingresazonaveredalJpaController();
    }
    public void registrarIngresoAutoridad(Autoridadingresazonaveredal ingreso) throws Exception{
        //if(ingreso.getIdAutoridad().getIdAutoridad() == null)
        Calendar calendar = Calendar.getInstance();
        Date fecha = calendar.getTime();
        ingreso.setFechaIngreso(fecha);
        ingresoAutoridadDAO.create(ingreso);
    }
    public void registrarSalidaAutoridad(Autoridad autoridad) throws Exception{
        Autoridadingresazonaveredal salida = buscarIngresoPorAutoridad(autoridad);
        if(salida == null) throw new Exception("No se encontro el ingreso en la base de datos");
        else{
            Calendar calendar = Calendar.getInstance();
            Date fecha = calendar.getTime();
            salida.setFechaIngreso(fecha);
            salida.setFechaSalida(fecha);
            ingresoAutoridadDAO.edit(salida);
        }
    }
    public List<Autoridadingresazonaveredal> buscarIngresoAutoridad(){
        return  ingresoAutoridadDAO.findAutoridadingresazonaveredalEntities();
    }
    public Autoridadingresazonaveredal buscarIngresoAutoridad(int id){
        return ingresoAutoridadDAO.findAutoridadingresazonaveredal(id);
    }
    public int buscarSiguienteId(){
        List<Autoridadingresazonaveredal> ingresos = buscarIngresoAutoridad();
        return ingresos.size()+1;
    }

    private Autoridadingresazonaveredal buscarIngresoPorAutoridad(Autoridad autoridad) {
        List<Autoridadingresazonaveredal> ingresos = buscarIngresoAutoridad();
        for (int i = 0; i < ingresos.size(); i++) {
            if(ingresos.get(i).getIdAutoridad().equals(autoridad) && ingresos.get(i).getFechaSalida() == null) return ingresos.get(i);
        }
        return null;
    }

    public List<Autoridadingresazonaveredal> buscarPorFecha(Date fechaIngreso, Date fechaSalida) {
        List<Autoridadingresazonaveredal> ingresos = buscarIngresoAutoridad();
        List<Autoridadingresazonaveredal> respuesta = new ArrayList<Autoridadingresazonaveredal>();
        for (int i = 0; i < ingresos.size(); i++) {
            if(ingresos.get(i).getFechaIngreso().getTime() >= fechaIngreso.getTime() &&
                    ingresos.get(i).getFechaSalida().getTime() <= fechaSalida.getTime()){
                respuesta.add(ingresos.get(i));
            }
        }
        return respuesta;
    }
}
