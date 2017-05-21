/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jennifer
 */
@Entity
@Table(name = "integranteasistecapacitacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Integranteasistecapacitacion.findAll", query = "SELECT i FROM Integranteasistecapacitacion i")
    , @NamedQuery(name = "Integranteasistecapacitacion.findByIdAsiste", query = "SELECT i FROM Integranteasistecapacitacion i WHERE i.idAsiste = :idAsiste")
    , @NamedQuery(name = "Integranteasistecapacitacion.findByEstado", query = "SELECT i FROM Integranteasistecapacitacion i WHERE i.estado = :estado")})
public class Integranteasistecapacitacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAsiste")
    private Integer idAsiste;
    @Column(name = "estado")
    private String estado;
    @JoinColumn(name = "idCapacitacion", referencedColumnName = "idCapacitacion")
    @ManyToOne
    private Capacitacion idCapacitacion;
    @JoinColumn(name = "cedulaIntegrante", referencedColumnName = "cedulaIntegrante")
    @ManyToOne
    private Integrante cedulaIntegrante;

    public Integranteasistecapacitacion() {
    }

    public Integranteasistecapacitacion(Integer idAsiste) {
        this.idAsiste = idAsiste;
    }

    public Integer getIdAsiste() {
        return idAsiste;
    }

    public void setIdAsiste(Integer idAsiste) {
        this.idAsiste = idAsiste;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Capacitacion getIdCapacitacion() {
        return idCapacitacion;
    }

    public void setIdCapacitacion(Capacitacion idCapacitacion) {
        this.idCapacitacion = idCapacitacion;
    }

    public Integrante getCedulaIntegrante() {
        return cedulaIntegrante;
    }

    public void setCedulaIntegrante(Integrante cedulaIntegrante) {
        this.cedulaIntegrante = cedulaIntegrante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAsiste != null ? idAsiste.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Integranteasistecapacitacion)) {
            return false;
        }
        Integranteasistecapacitacion other = (Integranteasistecapacitacion) object;
        if ((this.idAsiste == null && other.idAsiste != null) || (this.idAsiste != null && !this.idAsiste.equals(other.idAsiste))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Integranteasistecapacitacion[ idAsiste=" + idAsiste + " ]";
    }
    
}
