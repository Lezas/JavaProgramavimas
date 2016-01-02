/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratorinis7;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Vartotojas
 */
@Entity
@Table(name = "TAXUSER")
@NamedQueries({
    @NamedQuery(name = "Taxuser.findAll", query = "SELECT t FROM Taxuser t"),
    @NamedQuery(name = "Taxuser.findById", query = "SELECT t FROM Taxuser t WHERE t.id = :id"),
    @NamedQuery(name = "Taxuser.findByFirstname", query = "SELECT t FROM Taxuser t WHERE t.firstname = :firstname"),
    @NamedQuery(name = "Taxuser.findBySurname", query = "SELECT t FROM Taxuser t WHERE t.surname = :surname"),
    @NamedQuery(name = "Taxuser.findByEmail", query = "SELECT t FROM Taxuser t WHERE t.email = :email")})
public class Taxuser implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "FIRSTNAME")
    private String firstname;
    @Column(name = "SURNAME")
    private String surname;
    @Column(name = "EMAIL")
    private String email;
    @OneToMany(mappedBy = "taxuser")
    private Collection<Expenses> expensesCollection;

    public Taxuser() {
    }

    public Taxuser(Integer id) {
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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        String oldFirstname = this.firstname;
        this.firstname = firstname;
        changeSupport.firePropertyChange("firstname", oldFirstname, firstname);
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        String oldSurname = this.surname;
        this.surname = surname;
        changeSupport.firePropertyChange("surname", oldSurname, surname);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        String oldEmail = this.email;
        this.email = email;
        changeSupport.firePropertyChange("email", oldEmail, email);
    }

    public Collection<Expenses> getExpensesCollection() {
        return expensesCollection;
    }

    public void setExpensesCollection(Collection<Expenses> expensesCollection) {
        this.expensesCollection = expensesCollection;
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
        if (!(object instanceof Taxuser)) {
            return false;
        }
        Taxuser other = (Taxuser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.firstname + " " + this.surname;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
