/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parkinglot;

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
 * @author Tomas Šiukščius
 */
@Entity
@Table(name = "LESSOR")
@NamedQueries({
    @NamedQuery(name = "Lessor.findAll", query = "SELECT l FROM Lessor l"),
    @NamedQuery(name = "Lessor.findById", query = "SELECT l FROM Lessor l WHERE l.id = :id"),
    @NamedQuery(name = "Lessor.findByName", query = "SELECT l FROM Lessor l WHERE l.name = :name"),
    @NamedQuery(name = "Lessor.findByPlateNumber", query = "SELECT l FROM Lessor l WHERE l.plateNumber = :plateNumber"),
    @NamedQuery(name = "Lessor.findByTel", query = "SELECT l FROM Lessor l WHERE l.tel = :tel"),
    @NamedQuery(name = "Lessor.findByEmail", query = "SELECT l FROM Lessor l WHERE l.email = :email")})
public class Lessor implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "PLATE_NUMBER")
    private String plateNumber;
    @Column(name = "TEL")
    private String tel;
    @Column(name = "EMAIL")
    private String email;
    @OneToMany(mappedBy = "lessor")
    private Collection<ParkingLot_1> parkingLotCollection;

    public Lessor() {
    }

    public Lessor(Integer id) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        String oldName = this.name;
        this.name = name;
        changeSupport.firePropertyChange("name", oldName, name);
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        String oldPlateNumber = this.plateNumber;
        this.plateNumber = plateNumber;
        changeSupport.firePropertyChange("plateNumber", oldPlateNumber, plateNumber);
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        String oldTel = this.tel;
        this.tel = tel;
        changeSupport.firePropertyChange("tel", oldTel, tel);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        String oldEmail = this.email;
        this.email = email;
        changeSupport.firePropertyChange("email", oldEmail, email);
    }

    public Collection<ParkingLot_1> getParkingLotCollection() {
        return parkingLotCollection;
    }

    public void setParkingLotCollection(Collection<ParkingLot_1> parkingLotCollection) {
        this.parkingLotCollection = parkingLotCollection;
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
        if (!(object instanceof Lessor)) {
            return false;
        }
        Lessor other = (Lessor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.name + ", " + this.plateNumber;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
