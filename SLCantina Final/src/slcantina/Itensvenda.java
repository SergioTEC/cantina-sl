/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slcantina;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Senai
 */
@Entity
@Table(name = "ITENSVENDA", catalog = "", schema = "CANTINASL")
@NamedQueries({
    @NamedQuery(name = "Itensvenda.findAll", query = "SELECT i FROM Itensvenda i")
    , @NamedQuery(name = "Itensvenda.findByCodigo", query = "SELECT i FROM Itensvenda i WHERE i.codigo = :codigo")
    , @NamedQuery(name = "Itensvenda.findByCodigovenda", query = "SELECT i FROM Itensvenda i WHERE i.codigovenda = :codigovenda")
    , @NamedQuery(name = "Itensvenda.findByCodigoproduto", query = "SELECT i FROM Itensvenda i WHERE i.codigoproduto = :codigoproduto")
    , @NamedQuery(name = "Itensvenda.findByQuantidade", query = "SELECT i FROM Itensvenda i WHERE i.quantidade = :quantidade")})
public class Itensvenda implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODIGO")
    private Long codigo;
    @Basic(optional = false)
    @Column(name = "CODIGOVENDA")
    private long codigovenda;
    @Basic(optional = false)
    @Column(name = "CODIGOPRODUTO")
    private long codigoproduto;
    @Basic(optional = false)
    @Column(name = "QUANTIDADE")
    private short quantidade;

    public Itensvenda() {
    }

    public Itensvenda(Long codigo) {
        this.codigo = codigo;
    }

    public Itensvenda(Long codigo, long codigovenda, long codigoproduto, short quantidade) {
        this.codigo = codigo;
        this.codigovenda = codigovenda;
        this.codigoproduto = codigoproduto;
        this.quantidade = quantidade;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        Long oldCodigo = this.codigo;
        this.codigo = codigo;
        changeSupport.firePropertyChange("codigo", oldCodigo, codigo);
    }

    public long getCodigovenda() {
        return codigovenda;
    }

    public void setCodigovenda(long codigovenda) {
        long oldCodigovenda = this.codigovenda;
        this.codigovenda = codigovenda;
        changeSupport.firePropertyChange("codigovenda", oldCodigovenda, codigovenda);
    }

    public long getCodigoproduto() {
        return codigoproduto;
    }

    public void setCodigoproduto(long codigoproduto) {
        long oldCodigoproduto = this.codigoproduto;
        this.codigoproduto = codigoproduto;
        changeSupport.firePropertyChange("codigoproduto", oldCodigoproduto, codigoproduto);
    }

    public short getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(short quantidade) {
        short oldQuantidade = this.quantidade;
        this.quantidade = quantidade;
        changeSupport.firePropertyChange("quantidade", oldQuantidade, quantidade);
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
        if (!(object instanceof Itensvenda)) {
            return false;
        }
        Itensvenda other = (Itensvenda) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "slcantina.Itensvenda[ codigo=" + codigo + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
