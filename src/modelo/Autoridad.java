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
@Table(name = "autoridad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Autoridad.findAll", query = "SELECT a FROM Autoridad a")
    , @NamedQuery(name = "Autoridad.findByIdAutoridad", query = "SELECT a FROM Autoridad a WHERE a.idAutoridad = :idAutoridad")
    , @NamedQuery(name = "Autoridad.findByNombreAutoridad", query = "SELECT a FROM Autoridad a WHERE a.nombreAutoridad = :nombreAutoridad")})
public class Autoridad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idAutoridad")
    private Integer idAutoridad;
    @Column(name = "nombreAutoridad")
    private String nombreAutoridad;
    @OneToMany(mappedBy = "idAutoridad")
    private List<Autoridadingresazonaveredal> autoridadingresazonaveredalList;

    public Autoridad() {
    }

    public Autoridad(Integer idAutoridad) {
        this.idAutoridad = idAutoridad;
    }

    public Integer getIdAutoridad() {
        return idAutoridad;
    }

    public void setIdAutoridad(Integer idAutoridad) {
        this.idAutoridad = idAutoridad;
    }

    public String getNombreAutoridad() {
        return nombreAutoridad;
    }

    public void setNombreAutoridad(String nombreAutoridad) {
        this.nombreAutoridad = nombreAutoridad;
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
        hash += (idAutoridad != null ? idAutoridad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Autoridad)) {
            return false;
        }
        Autoridad other = (Autoridad) object;
        if ((this.idAutoridad == null && other.idAutoridad != null) || (this.idAutoridad != null && !this.idAutoridad.equals(other.idAutoridad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Autoridad[ idAutoridad=" + idAutoridad + " ]";
    }
    
}
