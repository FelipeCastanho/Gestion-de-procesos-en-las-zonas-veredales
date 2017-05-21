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
@Table(name = "informacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Informacion.findAll", query = "SELECT i FROM Informacion i")
    , @NamedQuery(name = "Informacion.findByIdInformacion", query = "SELECT i FROM Informacion i WHERE i.idInformacion = :idInformacion")
    , @NamedQuery(name = "Informacion.findByDescripcionInformacion", query = "SELECT i FROM Informacion i WHERE i.descripcionInformacion = :descripcionInformacion")
    , @NamedQuery(name = "Informacion.findByUbicacionInformacion", query = "SELECT i FROM Informacion i WHERE i.ubicacionInformacion = :ubicacionInformacion")
    , @NamedQuery(name = "Informacion.findByCantidadInformacion", query = "SELECT i FROM Informacion i WHERE i.cantidadInformacion = :cantidadInformacion")
    , @NamedQuery(name = "Informacion.findByEstadoInformacion", query = "SELECT i FROM Informacion i WHERE i.estadoInformacion = :estadoInformacion")})
public class Informacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idInformacion")
    private Integer idInformacion;
    @Column(name = "descripcionInformacion")
    private String descripcionInformacion;
    @Column(name = "ubicacionInformacion")
    private String ubicacionInformacion;
    @Column(name = "cantidadInformacion")
    private Integer cantidadInformacion;
    @Column(name = "estadoInformacion")
    private String estadoInformacion;
    @JoinColumn(name = "cedulaIntegrante", referencedColumnName = "cedulaIntegrante")
    @ManyToOne
    private Integrante cedulaIntegrante;

    public Informacion() {
    }

    public Informacion(Integer idInformacion) {
        this.idInformacion = idInformacion;
    }

    public Integer getIdInformacion() {
        return idInformacion;
    }

    public void setIdInformacion(Integer idInformacion) {
        this.idInformacion = idInformacion;
    }

    public String getDescripcionInformacion() {
        return descripcionInformacion;
    }

    public void setDescripcionInformacion(String descripcionInformacion) {
        this.descripcionInformacion = descripcionInformacion;
    }

    public String getUbicacionInformacion() {
        return ubicacionInformacion;
    }

    public void setUbicacionInformacion(String ubicacionInformacion) {
        this.ubicacionInformacion = ubicacionInformacion;
    }

    public Integer getCantidadInformacion() {
        return cantidadInformacion;
    }

    public void setCantidadInformacion(Integer cantidadInformacion) {
        this.cantidadInformacion = cantidadInformacion;
    }

    public String getEstadoInformacion() {
        return estadoInformacion;
    }

    public void setEstadoInformacion(String estadoInformacion) {
        this.estadoInformacion = estadoInformacion;
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
        hash += (idInformacion != null ? idInformacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Informacion)) {
            return false;
        }
        Informacion other = (Informacion) object;
        if ((this.idInformacion == null && other.idInformacion != null) || (this.idInformacion != null && !this.idInformacion.equals(other.idInformacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Informacion[ idInformacion=" + idInformacion + " ]";
    }
    
}
