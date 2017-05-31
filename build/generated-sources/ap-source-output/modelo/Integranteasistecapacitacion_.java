package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Capacitacion;
import modelo.Integrante;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-27T13:22:49")
@StaticMetamodel(Integranteasistecapacitacion.class)
public class Integranteasistecapacitacion_ { 

    public static volatile SingularAttribute<Integranteasistecapacitacion, String> estado;
    public static volatile SingularAttribute<Integranteasistecapacitacion, Integrante> cedulaIntegrante;
    public static volatile SingularAttribute<Integranteasistecapacitacion, Integer> idAsiste;
    public static volatile SingularAttribute<Integranteasistecapacitacion, Capacitacion> idCapacitacion;

}