/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratorinis7;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.Transient;

/**
 *
 * @author Vartotojas
 */
@Entity
@Table(name = "EXPENSES")
@NamedQueries({
    @NamedQuery(name = "Expenses.findAll", query = "SELECT e FROM Expenses e"),
    @NamedQuery(name = "Expenses.findById", query = "SELECT e FROM Expenses e WHERE e.id = :id"),
    @NamedQuery(name = "Expenses.findByTitle", query = "SELECT e FROM Expenses e WHERE e.title = :title"),
    @NamedQuery(name = "Expenses.findByDescription", query = "SELECT e FROM Expenses e WHERE e.description = :description"),
    @NamedQuery(name = "Expenses.findByDatetime", query = "SELECT e FROM Expenses e WHERE e.datetime = :datetime")})
public class Expenses implements Serializable {
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "MONEY")
    private BigDecimal money;
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datetime;
    @JoinColumn(name = "TAXUSER", referencedColumnName = "ID")
    @ManyToOne
    private Taxuser taxuser;

    public Expenses() {
    }

    public Expenses(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        Integer oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        String oldTitle = this.title;
        this.title = title;
        changeSupport.firePropertyChange("title", oldTitle, title);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        String oldDescription = this.description;
        this.description = description;
        changeSupport.firePropertyChange("description", oldDescription, description);
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        Date oldDatetime = this.datetime;
        this.datetime = datetime;
        changeSupport.firePropertyChange("datetime", oldDatetime, datetime);
    }

    public Taxuser getTaxuser() {
        return taxuser;
    }

    public void setTaxuser(Taxuser taxuser) {
        Taxuser oldTaxuser = this.taxuser;
        this.taxuser = taxuser;
        changeSupport.firePropertyChange("taxuser", oldTaxuser, taxuser);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Expenses)) {
            return false;
        }
        Expenses other = (Expenses) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "laboratorinis7.Expenses[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        BigDecimal oldMoney = this.money;
        this.money = money;
        changeSupport.firePropertyChange("money", oldMoney, money);
    }
    
}
