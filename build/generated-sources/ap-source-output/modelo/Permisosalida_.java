package modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Integrante;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-27T13:22:49")
@StaticMetamodel(Permisosalida.class)
public class Permisosalida_ { 

    public static volatile SingularAttribute<Permisosalida, String> estadoSalida;
    public static volatile SingularAttribute<Permisosalida, Integrante> cedulaIntegrante;
    public static volatile SingularAttribute<Permisosalida, Integer> idSalida;
    public static volatile SingularAttribute<Permisosalida, String> motivoSalida;
    public static volatile SingularAttribute<Permisosalida, Date> fechaSalida;
    public static volatile SingularAttribute<Permisosalida, Date> fechaRetornoSalida;

}