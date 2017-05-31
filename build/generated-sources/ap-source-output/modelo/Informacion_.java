package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Integrante;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-27T13:22:49")
@StaticMetamodel(Informacion.class)
public class Informacion_ { 

    public static volatile SingularAttribute<Informacion, Integrante> cedulaIntegrante;
    public static volatile SingularAttribute<Informacion, String> ubicacionInformacion;
    public static volatile SingularAttribute<Informacion, Integer> idInformacion;
    public static volatile SingularAttribute<Informacion, String> estadoInformacion;
    public static volatile SingularAttribute<Informacion, String> descripcionInformacion;
    public static volatile SingularAttribute<Informacion, Integer> cantidadInformacion;

}