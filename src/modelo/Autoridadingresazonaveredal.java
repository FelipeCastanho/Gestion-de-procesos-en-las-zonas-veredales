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
@Table(name = "autoridadingresazonaveredal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Autoridadingresazonaveredal.findAll", query = "SELECT a FROM Autoridadingresazonaveredal a")
    , @NamedQuery(name = "Autoridadingresazonaveredal.findByIdIngreso", query = "SELECT a FROM Autoridadingresazonaveredal a WHERE a.idIngreso = :idIngreso")
    , @NamedQuery(name = "Autoridadingresazonaveredal.findByFechaIngreso", query = "SELECT a FROM Autoridadingresazonaveredal a WHERE a.fechaIngreso = :fechaIngreso")
    , @NamedQuery(name = "Autoridadingresazonaveredal.findByFechaSalida", query = "SELECT a FROM Autoridadingresazonaveredal a WHERE a.fechaSalida = :fechaSalida")
    , @NamedQuery(name = "Autoridadingresazonaveredal.findByMotivoIngreso", query = "SELECT a FROM Autoridadingresazonaveredal a WHERE a.motivoIngreso = :motivoIngreso")})
public class Autoridadingresazonaveredal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idIngreso")
    private Integer idIngreso;
    @Column(name = "fechaIngreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;
    @Column(name = "fechaSalida")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSalida;
    @Column(name = "motivoIngreso")
    private String motivoIngreso;
    @JoinColumn(name = "idZonaVeredal", referencedColumnName = "idZonaVeredal")
    @ManyToOne
    private Zonaveredal idZonaVeredal;
    @JoinColumn(name = "idAutoridad", referencedColumnName = "idAutoridad")
    @ManyToOne
    private Autoridad idAutoridad;

    public Autoridadingresazonaveredal() {
    }

    public Autoridadingresazonaveredal(Integer idIngreso) {
        this.idIngreso = idIngreso;
    }

    public Integer getIdIngreso() {
        return idIngreso;
    }

    public void setIdIngreso(Integer idIngreso) {
        this.idIngreso = idIngreso;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getMotivoIngreso() {
        return motivoIngreso;
    }

    public void setMotivoIngreso(String motivoIngreso) {
        this.motivoIngreso = motivoIngreso;
    }

    public Zonaveredal getIdZonaVeredal() {
        return idZonaVeredal;
    }

    public void setIdZonaVeredal(Zonaveredal idZonaVeredal) {
        this.idZonaVeredal = idZonaVeredal;
    }

    public Autoridad getIdAutoridad() {
        return idAutoridad;
    }

    public void setIdAutoridad(Autoridad idAutoridad) {
        this.idAutoridad = idAutoridad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idIngreso != null ? idIngreso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Autoridadingresazonaveredal)) {
            return false;
        }
        Autoridadingresazonaveredal other = (Autoridadingresazonaveredal) object;
        if ((this.idIngreso == null && other.idIngreso != null) || (this.idIngreso != null && !this.idIngreso.equals(other.idIngreso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Autoridadingresazonaveredal[ idIngreso=" + idIngreso + " ]";
    }
    
}
