package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Integrante;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-27T13:22:49")
@StaticMetamodel(Pena.class)
public class Pena_ { 

    public static volatile SingularAttribute<Pena, Integer> idPena;
    public static volatile SingularAttribute<Pena, Integrante> cedulaIntegrante;
    public static volatile SingularAttribute<Pena, String> modalidadPena;
    public static volatile SingularAttribute<Pena, Integer> anosPena;
    public static volatile SingularAttribute<Pena, Integer> mesesPena;

}