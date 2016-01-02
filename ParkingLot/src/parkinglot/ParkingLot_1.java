/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parkinglot;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
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
import javax.persistence.Transient;

/**
 *
 * @author Tomas Šiukščius
 */
@Entity
@Table(name = "PARKING_LOT")
@NamedQueries({
    @NamedQuery(name = "ParkingLot_1.findAll", query = "SELECT p FROM ParkingLot_1 p"),
    @NamedQuery(name = "ParkingLot_1.findById", query = "SELECT p FROM ParkingLot_1 p WHERE p.id = :id"),
    @NamedQuery(name = "ParkingLot_1.findByNum", query = "SELECT p FROM ParkingLot_1 p WHERE p.num = :num"),
    @NamedQuery(name = "ParkingLot_1.findByFloor", query = "SELECT p FROM ParkingLot_1 p WHERE p.floor = :floor"),
    @NamedQuery(name = "ParkingLot_1.findByDescription", query = "SELECT p FROM ParkingLot_1 p WHERE p.description = :description")})
public class ParkingLot_1 implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NUM")
    private Integer num;
    @Column(name = "FLOOR")
    private Integer floor;
    @Column(name = "DESCRIPTION")
    private String description;
    @JoinColumn(name = "LESSOR", referencedColumnName = "ID")
    @ManyToOne
    private Lessor lessor;

    public ParkingLot_1() {
    }

    public ParkingLot_1(Integer id) {
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

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        Integer oldNum = this.num;
        this.num = num;
        changeSupport.firePropertyChange("num", oldNum, num);
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        Integer oldFloor = this.floor;
        this.floor = floor;
        changeSupport.firePropertyChange("floor", oldFloor, floor);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        String oldDescription = this.description;
        this.description = description;
        changeSupport.firePropertyChange("description", oldDescription, description);
    }

    public Lessor getLessor() {
        return lessor;
    }

    public void setLessor(Lessor lessor) {
        Lessor oldLessor = this.lessor;
        this.lessor = lessor;
        changeSupport.firePropertyChange("lessor", oldLessor, lessor);
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
        if (!(object instanceof ParkingLot_1)) {
            return false;
        }
        ParkingLot_1 other = (ParkingLot_1) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.num + ", floor: " + this.floor;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
