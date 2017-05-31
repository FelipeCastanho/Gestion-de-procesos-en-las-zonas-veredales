package modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Arma;
import modelo.Informacion;
import modelo.Integranteasistecapacitacion;
import modelo.Pena;
import modelo.Permisosalida;
import modelo.Zonaveredal;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-27T13:22:49")
@StaticMetamodel(Integrante.class)
public class Integrante_ { 

    public static volatile SingularAttribute<Integrante, String> lugarNacimientoIntegrante;
    public static volatile SingularAttribute<Integrante, Integer> cedulaIntegrante;
    public static volatile SingularAttribute<Integrante, String> nombreIntegrante;
    public static volatile SingularAttribute<Integrante, String> tipoSangreIntegrante;
    public static volatile SingularAttribute<Integrante, Zonaveredal> idZonaVeredal;
    public static volatile ListAttribute<Integrante, Informacion> informacionList;
    public static volatile SingularAttribute<Integrante, String> apellidosIntegrante;
    public static volatile ListAttribute<Integrante, Pena> penaList;
    public static volatile SingularAttribute<Integrante, Date> fechaNacimientoIntegrante;
    public static volatile ListAttribute<Integrante, Arma> armaList;
    public static volatile ListAttribute<Integrante, Integranteasistecapacitacion> integranteasistecapacitacionList;
    public static volatile SingularAttribute<Integrante, Double> alturaIntegrante;
    public static volatile ListAttribute<Integrante, Permisosalida> permisosalidaList;
    public static volatile SingularAttribute<Integrante, String> sexoIntegrante;

}