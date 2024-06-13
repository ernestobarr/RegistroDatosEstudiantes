package com.mycompany.registroacadweb.jpa;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.Size;

@Entity
@NamedQueries({
    @NamedQuery(name = "Credencial.findAll", query = "SELECT c FROM Credencial c"),
    @NamedQuery(name = "Credencial.findByIdcredencial", query = "SELECT c FROM Credencial c WHERE c.idcredencial = :idcredencial"),
    @NamedQuery(name = "Credencial.findByUsername", query = "SELECT c FROM Credencial c WHERE c.username = :username"),
    @NamedQuery(name = "Credencial.findByPassword", query = "SELECT c FROM Credencial c WHERE c.password = :password"),
    @NamedQuery(name = "Credencial.findByActivo", query = "SELECT c FROM Credencial c WHERE c.activo = :activo"),
    @NamedQuery(name = "Credencial.findByIdestudiante", query = "SELECT c FROM Credencial c WHERE c.idestudiante = :idestudiante")})

public class Credencial implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idcredencial;
    @Size(max = 255)
    private String username;
    @Size(max = 255)
    private String password;
    private Boolean activo;
    private Integer idestudiante;
    @JoinColumns({
        @JoinColumn(name = "idcredencial", referencedColumnName = "idestudiante", insertable = false, updatable = false),
        @JoinColumn(name = "idcredencial", referencedColumnName = "idestudiante", insertable = false, updatable = false)})
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private Estudiante estudiante;

    public Credencial() {
    }

    public Credencial(String username, String password, Boolean activo) {
        this.username = username;
        this.password = password;
        this.activo = activo;
    }
    
    public Credencial(Integer idcredencial) {
        this.idcredencial = idcredencial;
    }

    public Integer getIdcredencial() {
        return idcredencial;
    }

    public void setIdcredencial(Integer idcredencial) {
        this.idcredencial = idcredencial;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Integer getIdestudiante() {
        return idestudiante;
    }

    public void setIdestudiante(Integer idestudiante) {
        this.idestudiante = idestudiante;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcredencial != null ? idcredencial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Credencial)) {
            return false;
        }
        Credencial other = (Credencial) object;
        if ((this.idcredencial == null && other.idcredencial != null) || (this.idcredencial != null && !this.idcredencial.equals(other.idcredencial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Credencial{" + "idcredencial=" + idcredencial + ", username=" + username + ", password=" + password + ", activo=" + activo + ", idestudiante=" + idestudiante + '}';
    }
       
}