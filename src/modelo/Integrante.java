/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jennifer
 */
@Entity
@Table(name = "integrante")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Integrante.findAll", query = "SELECT i FROM Integrante i")
    , @NamedQuery(name = "Integrante.findByCedulaIntegrante", query = "SELECT i FROM Integrante i WHERE i.cedulaIntegrante = :cedulaIntegrante")
    , @NamedQuery(name = "Integrante.findByNombreIntegrante", query = "SELECT i FROM Integrante i WHERE i.nombreIntegrante = :nombreIntegrante")
    , @NamedQuery(name = "Integrante.findByApellidosIntegrante", query = "SELECT i FROM Integrante i WHERE i.apellidosIntegrante = :apellidosIntegrante")
    , @NamedQuery(name = "Integrante.findByAlturaIntegrante", query = "SELECT i FROM Integrante i WHERE i.alturaIntegrante = :alturaIntegrante")
    , @NamedQuery(name = "Integrante.findByFechaNacimientoIntegrante", query = "SELECT i FROM Integrante i WHERE i.fechaNacimientoIntegrante = :fechaNacimientoIntegrante")
    , @NamedQuery(name = "Integrante.findByLugarNacimientoIntegrante", query = "SELECT i FROM Integrante i WHERE i.lugarNacimientoIntegrante = :lugarNacimientoIntegrante")
    , @NamedQuery(name = "Integrante.findBySexoIntegrante", query = "SELECT i FROM Integrante i WHERE i.sexoIntegrante = :sexoIntegrante")
    , @NamedQuery(name = "Integrante.findByTipoSangreIntegrante", query = "SELECT i FROM Integrante i WHERE i.tipoSangreIntegrante = :tipoSangreIntegrante")})
public class Integrante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cedulaIntegrante")
    private Integer cedulaIntegrante;
    @Column(name = "nombreIntegrante")
    private String nombreIntegrante;
    @Column(name = "apellidosIntegrante")
    private String apellidosIntegrante;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "alturaIntegrante")
    private Double alturaIntegrante;
    @Column(name = "fechaNacimientoIntegrante")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimientoIntegrante;
    @Column(name = "lugarNacimientoIntegrante")
    private String lugarNacimientoIntegrante;
    @Column(name = "sexoIntegrante")
    private String sexoIntegrante;
    @Column(name = "tipoSangreIntegrante")
    private String tipoSangreIntegrante;
    @OneToMany(mappedBy = "cedulaIntegrante")
    private List<Integranteasistecapacitacion> integranteasistecapacitacionList;
    @OneToMany(mappedBy = "cedulaIntegrante")
    private List<Informacion> informacionList;
    @JoinColumn(name = "idZonaVeredal", referencedColumnName = "idZonaVeredal")
    @ManyToOne
    private Zonaveredal idZonaVeredal;
    @OneToMany(mappedBy = "cedulaIntegrante")
    private List<Permisosalida> permisosalidaList;
    @OneToMany(mappedBy = "cedulaIntegrante")
    private List<Arma> armaList;
    @OneToMany(mappedBy = "cedulaIntegrante")
    private List<Pena> penaList;

    public Integrante() {
    }

    public Integrante(Integer cedulaIntegrante) {
        this.cedulaIntegrante = cedulaIntegrante;
    }

    public Integer getCedulaIntegrante() {
        return cedulaIntegrante;
    }

    public void setCedulaIntegrante(Integer cedulaIntegrante) {
        this.cedulaIntegrante = cedulaIntegrante;
    }

    public String getNombreIntegrante() {
        return nombreIntegrante;
    }

    public void setNombreIntegrante(String nombreIntegrante) {
        this.nombreIntegrante = nombreIntegrante;
    }

    public String getApellidosIntegrante() {
        return apellidosIntegrante;
    }

    public void setApellidosIntegrante(String apellidosIntegrante) {
        this.apellidosIntegrante = apellidosIntegrante;
    }

    public Double getAlturaIntegrante() {
        return alturaIntegrante;
    }

    public void setAlturaIntegrante(Double alturaIntegrante) {
        this.alturaIntegrante = alturaIntegrante;
    }

    public Date getFechaNacimientoIntegrante() {
        return fechaNacimientoIntegrante;
    }

    public void setFechaNacimientoIntegrante(Date fechaNacimientoIntegrante) {
        this.fechaNacimientoIntegrante = fechaNacimientoIntegrante;
    }

    public String getLugarNacimientoIntegrante() {
        return lugarNacimientoIntegrante;
    }

    public void setLugarNacimientoIntegrante(String lugarNacimientoIntegrante) {
        this.lugarNacimientoIntegrante = lugarNacimientoIntegrante;
    }

    public String getSexoIntegrante() {
        return sexoIntegrante;
    }

    public void setSexoIntegrante(String sexoIntegrante) {
        this.sexoIntegrante = sexoIntegrante;
    }

    public String getTipoSangreIntegrante() {
        return tipoSangreIntegrante;
    }

    public void setTipoSangreIntegrante(String tipoSangreIntegrante) {
        this.tipoSangreIntegrante = tipoSangreIntegrante;
    }

    @XmlTransient
    public List<Integranteasistecapacitacion> getIntegranteasistecapacitacionList() {
        return integranteasistecapacitacionList;
    }

    public void setIntegranteasistecapacitacionList(List<Integranteasistecapacitacion> integranteasistecapacitacionList) {
        this.integranteasistecapacitacionList = integranteasistecapacitacionList;
    }

    @XmlTransient
    public List<Informacion> getInformacionList() {
        return informacionList;
    }

    public void setInformacionList(List<Informacion> informacionList) {
        this.informacionList = informacionList;
    }

    public Zonaveredal getIdZonaVeredal() {
        return idZonaVeredal;
    }

    public void setIdZonaVeredal(Zonaveredal idZonaVeredal) {
        this.idZonaVeredal = idZonaVeredal;
    }

    @XmlTransient
    public List<Permisosalida> getPermisosalidaList() {
        return permisosalidaList;
    }

    public void setPermisosalidaList(List<Permisosalida> permisosalidaList) {
        this.permisosalidaList = permisosalidaList;
    }

    @XmlTransient
    public List<Arma> getArmaList() {
        return armaList;
    }

    public void setArmaList(List<Arma> armaList) {
        this.armaList = armaList;
    }

    @XmlTransient
    public List<Pena> getPenaList() {
        return penaList;
    }

    public void setPenaList(List<Pena> penaList) {
        this.penaList = penaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cedulaIntegrante != null ? cedulaIntegrante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Integrante)) {
            return false;
        }
        Integrante other = (Integrante) object;
        if ((this.cedulaIntegrante == null && other.cedulaIntegrante != null) || (this.cedulaIntegrante != null && !this.cedulaIntegrante.equals(other.cedulaIntegrante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Integrante[ cedulaIntegrante=" + cedulaIntegrante + " ]";
    }
    
}
