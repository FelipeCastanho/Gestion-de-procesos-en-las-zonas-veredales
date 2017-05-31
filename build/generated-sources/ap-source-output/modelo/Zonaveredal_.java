package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Autoridadingresazonaveredal;
import modelo.Integrante;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-27T13:22:49")
@StaticMetamodel(Zonaveredal.class)
public class Zonaveredal_ { 

    public static volatile SingularAttribute<Zonaveredal, String> nombreZonaVeredal;
    public static volatile SingularAttribute<Zonaveredal, String> ubicacionZonaVeredal;
    public static volatile ListAttribute<Zonaveredal, Integrante> integranteList;
    public static volatile SingularAttribute<Zonaveredal, Integer> idZonaVeredal;
    public static volatile SingularAttribute<Zonaveredal, Integer> cantidadMaximaResidentes;
    public static volatile ListAttribute<Zonaveredal, Autoridadingresazonaveredal> autoridadingresazonaveredalList;

}