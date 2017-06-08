/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jennifer
 */
@Entity
@Table(name = "zonaveredal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Zonaveredal.findAll", query = "SELECT z FROM Zonaveredal z")
    , @NamedQuery(name = "Zonaveredal.findByIdZonaVeredal", query = "SELECT z FROM Zonaveredal z WHERE z.idZonaVeredal = :idZonaVeredal")
    , @NamedQuery(name = "Zonaveredal.findByNombreZonaVeredal", query = "SELECT z FROM Zonaveredal z WHERE z.nombreZonaVeredal = :nombreZonaVeredal")
    , @NamedQuery(name = "Zonaveredal.findByUbicacionZonaVeredal", query = "SELECT z FROM Zonaveredal z WHERE z.ubicacionZonaVeredal = :ubicacionZonaVeredal")
    , @NamedQuery(name = "Zonaveredal.findByCantidadMaximaResidentes", query = "SELECT z FROM Zonaveredal z WHERE z.cantidadMaximaResidentes = :cantidadMaximaResidentes")})
public class Zonaveredal implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idZonaVeredal")
    private Integer idZonaVeredal;
    @Column(name = "nombreZonaVeredal")
    private String nombreZonaVeredal;
    @Column(name = "ubicacionZonaVeredal")
    private String ubicacionZonaVeredal;
    @Column(name = "cantidadMaximaResidentes")
    private Integer cantidadMaximaResidentes;
    @OneToMany(mappedBy = "idZonaVeredal")
    private List<Integrante> integranteList;
    @OneToMany(mappedBy = "idZonaVeredal")
    private List<Autoridadingresazonaveredal> autoridadingresazonaveredalList;

    public Zonaveredal() {
    }

    public Zonaveredal(Integer idZonaVeredal) {
        this.idZonaVeredal = idZonaVeredal;
    }

    public Integer getIdZonaVeredal() {
        return idZonaVeredal;
    }

    public void setIdZonaVeredal(Integer idZonaVeredal) {
        Integer oldIdZonaVeredal = this.idZonaVeredal;
        this.idZonaVeredal = idZonaVeredal;
        changeSupport.firePropertyChange("idZonaVeredal", oldIdZonaVeredal, idZonaVeredal);
    }

    public String getNombreZonaVeredal() {
        return nombreZonaVeredal;
    }

    public void setNombreZonaVeredal(String nombreZonaVeredal) {
        String oldNombreZonaVeredal = this.nombreZonaVeredal;
        this.nombreZonaVeredal = nombreZonaVeredal;
        changeSupport.firePropertyChange("nombreZonaVeredal", oldNombreZonaVeredal, nombreZonaVeredal);
    }

    public String getUbicacionZonaVeredal() {
        return ubicacionZonaVeredal;
    }

    public void setUbicacionZonaVeredal(String ubicacionZonaVeredal) {
        String oldUbicacionZonaVeredal = this.ubicacionZonaVeredal;
        this.ubicacionZonaVeredal = ubicacionZonaVeredal;
        changeSupport.firePropertyChange("ubicacionZonaVeredal", oldUbicacionZonaVeredal, ubicacionZonaVeredal);
    }

    public Integer getCantidadMaximaResidentes() {
        return cantidadMaximaResidentes;
    }

    public void setCantidadMaximaResidentes(Integer cantidadMaximaResidentes) {
        Integer oldCantidadMaximaResidentes = this.cantidadMaximaResidentes;
        this.cantidadMaximaResidentes = cantidadMaximaResidentes;
        changeSupport.firePropertyChange("cantidadMaximaResidentes", oldCantidadMaximaResidentes, cantidadMaximaResidentes);
    }

    @XmlTransient
    public List<Integrante> getIntegranteList() {
        return integranteList;
    }

    public void setIntegranteList(List<Integrante> integranteList) {
        this.integranteList = integranteList;
    }

    @XmlTransient
    public List<Autoridadingresazonaveredal> getAutoridadingresazonaveredalList() {
        return autoridadingresazonaveredalList;
    }

    public void setAutoridadingresazonaveredalList(List<Autoridadingresazonaveredal> autoridadingresazonaveredalList) {
        this.autoridadingresazonaveredalList = autoridadingresazonaveredalList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idZonaVeredal != null ? idZonaVeredal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Zonaveredal)) {
            return false;
        }
        Zonaveredal other = (Zonaveredal) object;
        if ((this.idZonaVeredal == null && other.idZonaVeredal != null) || (this.idZonaVeredal != null && !this.idZonaVeredal.equals(other.idZonaVeredal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Zonaveredal[ idZonaVeredal=" + idZonaVeredal + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
