/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slcantina;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author Senai
 */
@Entity
@Table(name = "AGENDA", catalog = "", schema = "CANTINASL")
@NamedQueries({
    @NamedQuery(name = "Agenda.findAll", query = "SELECT a FROM Agenda a")
    , @NamedQuery(name = "Agenda.findByCodigo", query = "SELECT a FROM Agenda a WHERE a.codigo = :codigo")
    , @NamedQuery(name = "Agenda.findByCpfcliente", query = "SELECT a FROM Agenda a WHERE a.cpfcliente = :cpfcliente")
    , @NamedQuery(name = "Agenda.findByDataagenda", query = "SELECT a FROM Agenda a WHERE a.dataagenda = :dataagenda")
    , @NamedQuery(name = "Agenda.findByDescricao", query = "SELECT a FROM Agenda a WHERE a.descricao = :descricao")})
public class Agenda implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @Column(name = "CPFCLIENTE")
    private long cpfcliente;
    @Column(name = "DATAAGENDA")
    @Temporal(TemporalType.DATE)
    private Date dataagenda;
    @Column(name = "DESCRICAO")
    private String descricao;

    public Agenda() {
    }

    public Agenda(Long codigo) {
        this.codigo = codigo;
    }

    public Agenda(Long codigo, long cpfcliente) {
        this.codigo = codigo;
        this.cpfcliente = cpfcliente;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        Long oldCodigo = this.codigo;
        this.codigo = codigo;
        changeSupport.firePropertyChange("codigo", oldCodigo, codigo);
    }

    public long getCpfcliente() {
        return cpfcliente;
    }

    public void setCpfcliente(long cpfcliente) {
        long oldCpfcliente = this.cpfcliente;
        this.cpfcliente = cpfcliente;
        changeSupport.firePropertyChange("cpfcliente", oldCpfcliente, cpfcliente);
    }

    public Date getDataagenda() {
        return dataagenda;
    }

    public void setDataagenda(Date dataagenda) {
        Date oldDataagenda = this.dataagenda;
        this.dataagenda = dataagenda;
        changeSupport.firePropertyChange("dataagenda", oldDataagenda, dataagenda);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        String oldDescricao = this.descricao;
        this.descricao = descricao;
        changeSupport.firePropertyChange("descricao", oldDescricao, descricao);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Agenda)) {
            return false;
        }
        Agenda other = (Agenda) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "slcantina.Agenda[ codigo=" + codigo + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
