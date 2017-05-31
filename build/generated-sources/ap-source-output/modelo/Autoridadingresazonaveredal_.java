package modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Autoridad;
import modelo.Zonaveredal;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-27T13:22:49")
@StaticMetamodel(Autoridadingresazonaveredal.class)
public class Autoridadingresazonaveredal_ { 

    public static volatile SingularAttribute<Autoridadingresazonaveredal, Date> fechaIngreso;
    public static volatile SingularAttribute<Autoridadingresazonaveredal, Integer> idIngreso;
    public static volatile SingularAttribute<Autoridadingresazonaveredal, Date> fechaSalida;
    public static volatile SingularAttribute<Autoridadingresazonaveredal, Zonaveredal> idZonaVeredal;
    public static volatile SingularAttribute<Autoridadingresazonaveredal, String> motivoIngreso;
    public static volatile SingularAttribute<Autoridadingresazonaveredal, Autoridad> idAutoridad;

}