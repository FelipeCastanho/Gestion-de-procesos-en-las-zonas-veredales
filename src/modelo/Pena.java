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
@Table(name = "pena")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pena.findAll", query = "SELECT p FROM Pena p")
    , @NamedQuery(name = "Pena.findByIdPena", query = "SELECT p FROM Pena p WHERE p.idPena = :idPena")
    , @NamedQuery(name = "Pena.findByModalidadPena", query = "SELECT p FROM Pena p WHERE p.modalidadPena = :modalidadPena")
    , @NamedQuery(name = "Pena.findByAnosPena", query = "SELECT p FROM Pena p WHERE p.anosPena = :anosPena")
    , @NamedQuery(name = "Pena.findByMesesPena", query = "SELECT p FROM Pena p WHERE p.mesesPena = :mesesPena")})
public class Pena implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPena")
    private Integer idPena;
    @Column(name = "modalidadPena")
    private String modalidadPena;
    @Column(name = "anosPena")
    private Integer anosPena;
    @Column(name = "mesesPena")
    private Integer mesesPena;
    @JoinColumn(name = "cedulaIntegrante", referencedColumnName = "cedulaIntegrante")
    @ManyToOne
    private Integrante cedulaIntegrante;

    public Pena() {
    }

    public Pena(Integer idPena) {
        this.idPena = idPena;
    }

    public Integer getIdPena() {
        return idPena;
    }

    public void setIdPena(Integer idPena) {
        this.idPena = idPena;
    }

    public String getModalidadPena() {
        return modalidadPena;
    }

    public void setModalidadPena(String modalidadPena) {
        this.modalidadPena = modalidadPena;
    }

    public Integer getAnosPena() {
        return anosPena;
    }

    public void setAnosPena(Integer anosPena) {
        this.anosPena = anosPena;
    }

    public Integer getMesesPena() {
        return mesesPena;
    }

    public void setMesesPena(Integer mesesPena) {
        this.mesesPena = mesesPena;
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
        hash += (idPena != null ? idPena.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pena)) {
            return false;
        }
        Pena other = (Pena) object;
        if ((this.idPena == null && other.idPena != null) || (this.idPena != null && !this.idPena.equals(other.idPena))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Pena[ idPena=" + idPena + " ]";
    }
    
}
