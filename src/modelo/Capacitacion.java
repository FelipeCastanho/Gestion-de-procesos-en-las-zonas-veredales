/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jennifer
 */
@Entity
@Table(name = "capacitacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Capacitacion.findAll", query = "SELECT c FROM Capacitacion c")
    , @NamedQuery(name = "Capacitacion.findByIdCapacitacion", query = "SELECT c FROM Capacitacion c WHERE c.idCapacitacion = :idCapacitacion")
    , @NamedQuery(name = "Capacitacion.findByNombreCapacitacion", query = "SELECT c FROM Capacitacion c WHERE c.nombreCapacitacion = :nombreCapacitacion")
    , @NamedQuery(name = "Capacitacion.findByNumeroIntegrantesCapacitacion", query = "SELECT c FROM Capacitacion c WHERE c.numeroIntegrantesCapacitacion = :numeroIntegrantesCapacitacion")
    , @NamedQuery(name = "Capacitacion.findByNombreEncargadoCapacitacion", query = "SELECT c FROM Capacitacion c WHERE c.nombreEncargadoCapacitacion = :nombreEncargadoCapacitacion")
    , @NamedQuery(name = "Capacitacion.findByJornadaCapacitacion", query = "SELECT c FROM Capacitacion c WHERE c.jornadaCapacitacion = :jornadaCapacitacion")})
public class Capacitacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCapacitacion")
    private Integer idCapacitacion;
    @Column(name = "nombreCapacitacion")
    private String nombreCapacitacion;
    @Column(name = "numeroIntegrantesCapacitacion")
    private Integer numeroIntegrantesCapacitacion;
    @Column(name = "nombreEncargadoCapacitacion")
    private String nombreEncargadoCapacitacion;
    @Column(name = "jornadaCapacitacion")
    private String jornadaCapacitacion;
    @OneToMany(mappedBy = "idCapacitacion")
    private List<Integranteasistecapacitacion> integranteasistecapacitacionList;

    public Capacitacion() {
    }

    public Capacitacion(Integer idCapacitacion) {
        this.idCapacitacion = idCapacitacion;
    }

    public Integer getIdCapacitacion() {
        return idCapacitacion;
    }

    public void setIdCapacitacion(Integer idCapacitacion) {
        this.idCapacitacion = idCapacitacion;
    }

    public String getNombreCapacitacion() {
        return nombreCapacitacion;
    }

    public void setNombreCapacitacion(String nombreCapacitacion) {
        this.nombreCapacitacion = nombreCapacitacion;
    }

    public Integer getNumeroIntegrantesCapacitacion() {
        return numeroIntegrantesCapacitacion;
    }

    public void setNumeroIntegrantesCapacitacion(Integer numeroIntegrantesCapacitacion) {
        this.numeroIntegrantesCapacitacion = numeroIntegrantesCapacitacion;
    }

    public String getNombreEncargadoCapacitacion() {
        return nombreEncargadoCapacitacion;
    }

    public void setNombreEncargadoCapacitacion(String nombreEncargadoCapacitacion) {
        this.nombreEncargadoCapacitacion = nombreEncargadoCapacitacion;
    }

    public String getJornadaCapacitacion() {
        return jornadaCapacitacion;
    }

    public void setJornadaCapacitacion(String jornadaCapacitacion) {
        this.jornadaCapacitacion = jornadaCapacitacion;
    }

    @XmlTransient
    public List<Integranteasistecapacitacion> getIntegranteasistecapacitacionList() {
        return integranteasistecapacitacionList;
    }

    public void setIntegranteasistecapacitacionList(List<Integranteasistecapacitacion> integranteasistecapacitacionList) {
        this.integranteasistecapacitacionList = integranteasistecapacitacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCapacitacion != null ? idCapacitacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Capacitacion)) {
            return false;
        }
        Capacitacion other = (Capacitacion) object;
        if ((this.idCapacitacion == null && other.idCapacitacion != null) || (this.idCapacitacion != null && !this.idCapacitacion.equals(other.idCapacitacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Capacitacion[ idCapacitacion=" + idCapacitacion + " ]";
    }
    
}
