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
@Table(name = "arma")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Arma.findAll", query = "SELECT a FROM Arma a")
    , @NamedQuery(name = "Arma.findByIdArma", query = "SELECT a FROM Arma a WHERE a.idArma = :idArma")
    , @NamedQuery(name = "Arma.findByMatriculaArma", query = "SELECT a FROM Arma a WHERE a.matriculaArma = :matriculaArma")
    , @NamedQuery(name = "Arma.findByEstadoArma", query = "SELECT a FROM Arma a WHERE a.estadoArma = :estadoArma")
    , @NamedQuery(name = "Arma.findByCalibreArma", query = "SELECT a FROM Arma a WHERE a.calibreArma = :calibreArma")
    , @NamedQuery(name = "Arma.findByCantidadArma", query = "SELECT a FROM Arma a WHERE a.cantidadArma = :cantidadArma")
    , @NamedQuery(name = "Arma.findByFrenteArma", query = "SELECT a FROM Arma a WHERE a.frenteArma = :frenteArma")})
public class Arma implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idArma")
    private Integer idArma;
    @Column(name = "matriculaArma")
    private String matriculaArma;
    @Column(name = "estadoArma")
    private String estadoArma;
    @Column(name = "calibreArma")
    private String calibreArma;
    @Column(name = "cantidadArma")
    private Integer cantidadArma;
    @Column(name = "frenteArma")
    private String frenteArma;
    @JoinColumn(name = "cedulaIntegrante", referencedColumnName = "cedulaIntegrante")
    @ManyToOne
    private Integrante cedulaIntegrante;

    public Arma() {
    }

    public Arma(Integer idArma) {
        this.idArma = idArma;
    }

    public Integer getIdArma() {
        return idArma;
    }

    public void setIdArma(Integer idArma) {
        this.idArma = idArma;
    }

    public String getMatriculaArma() {
        return matriculaArma;
    }

    public void setMatriculaArma(String matriculaArma) {
        this.matriculaArma = matriculaArma;
    }

    public String getEstadoArma() {
        return estadoArma;
    }

    public void setEstadoArma(String estadoArma) {
        this.estadoArma = estadoArma;
    }

    public String getCalibreArma() {
        return calibreArma;
    }

    public void setCalibreArma(String calibreArma) {
        this.calibreArma = calibreArma;
    }

    public Integer getCantidadArma() {
        return cantidadArma;
    }

    public void setCantidadArma(Integer cantidadArma) {
        this.cantidadArma = cantidadArma;
    }

    public String getFrenteArma() {
        return frenteArma;
    }

    public void setFrenteArma(String frenteArma) {
        this.frenteArma = frenteArma;
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
        hash += (idArma != null ? idArma.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Arma)) {
            return false;
        }
        Arma other = (Arma) object;
        if ((this.idArma == null && other.idArma != null) || (this.idArma != null && !this.idArma.equals(other.idArma))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Arma[ idArma=" + idArma + " ]";
    }
    
}
