/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jennifer
 */
@Entity
@Table(name = "permisosalida")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Permisosalida.findAll", query = "SELECT p FROM Permisosalida p")
    , @NamedQuery(name = "Permisosalida.findByIdSalida", query = "SELECT p FROM Permisosalida p WHERE p.idSalida = :idSalida")
    , @NamedQuery(name = "Permisosalida.findByMotivoSalida", query = "SELECT p FROM Permisosalida p WHERE p.motivoSalida = :motivoSalida")
    , @NamedQuery(name = "Permisosalida.findByFechaSalida", query = "SELECT p FROM Permisosalida p WHERE p.fechaSalida = :fechaSalida")
    , @NamedQuery(name = "Permisosalida.findByFechaRetornoSalida", query = "SELECT p FROM Permisosalida p WHERE p.fechaRetornoSalida = :fechaRetornoSalida")
    , @NamedQuery(name = "Permisosalida.findByEstadoSalida", query = "SELECT p FROM Permisosalida p WHERE p.estadoSalida = :estadoSalida")})
public class Permisosalida implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idSalida")
    private Integer idSalida;
    @Column(name = "motivoSalida")
    private String motivoSalida;
    @Column(name = "fechaSalida")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSalida;
    @Column(name = "fechaRetornoSalida")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRetornoSalida;
    @Column(name = "estadoSalida")
    private String estadoSalida;
    @JoinColumn(name = "cedulaIntegrante", referencedColumnName = "cedulaIntegrante")
    @ManyToOne
    private Integrante cedulaIntegrante;

    public Permisosalida() {
    }

    public Permisosalida(Integer idSalida) {
        this.idSalida = idSalida;
    }

    public Integer getIdSalida() {
        return idSalida;
    }

    public void setIdSalida(Integer idSalida) {
        this.idSalida = idSalida;
    }

    public String getMotivoSalida() {
        return motivoSalida;
    }

    public void setMotivoSalida(String motivoSalida) {
        this.motivoSalida = motivoSalida;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Date getFechaRetornoSalida() {
        return fechaRetornoSalida;
    }

    public void setFechaRetornoSalida(Date fechaRetornoSalida) {
        this.fechaRetornoSalida = fechaRetornoSalida;
    }

    public String getEstadoSalida() {
        return estadoSalida;
    }

    public void setEstadoSalida(String estadoSalida) {
        this.estadoSalida = estadoSalida;
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
        hash += (idSalida != null ? idSalida.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Permisosalida)) {
            return false;
        }
        Permisosalida other = (Permisosalida) object;
        if ((this.idSalida == null && other.idSalida != null) || (this.idSalida != null && !this.idSalida.equals(other.idSalida))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Permisosalida[ idSalida=" + idSalida + " ]";
    }
    
}
