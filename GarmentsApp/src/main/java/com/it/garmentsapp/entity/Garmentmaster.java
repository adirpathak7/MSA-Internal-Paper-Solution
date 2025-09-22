/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.it.garmentsapp.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigInteger;

/**
 *
 * @author Aditya Pathak R
 */
@Entity
@Table(name = "garmentmaster")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Garmentmaster.findAll", query = "SELECT g FROM Garmentmaster g"),
    @NamedQuery(name = "Garmentmaster.findByGarmentId", query = "SELECT g FROM Garmentmaster g WHERE g.garmentId = :garmentId"),
    @NamedQuery(name = "Garmentmaster.findByGarmentName", query = "SELECT g FROM Garmentmaster g WHERE g.garmentName = :garmentName"),
    @NamedQuery(name = "Garmentmaster.findBySize", query = "SELECT g FROM Garmentmaster g WHERE g.size = :size"),
    @NamedQuery(name = "Garmentmaster.findByCategory", query = "SELECT g FROM Garmentmaster g WHERE g.category = :category"),
    @NamedQuery(name = "Garmentmaster.findByDescription", query = "SELECT g FROM Garmentmaster g WHERE g.description = :description"),
    @NamedQuery(name = "Garmentmaster.findByPrice", query = "SELECT g FROM Garmentmaster g WHERE g.price = :price"),
    @NamedQuery(name = "Garmentmaster.findByStock", query = "SELECT g FROM Garmentmaster g WHERE g.stock = :stock")})
public class Garmentmaster implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "garmentId")
    private Integer garmentId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "garmentName")
    private String garmentName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "size")
    private String size;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "category")
    private String category;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private BigInteger price;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "stock")
    private String stock;

    public Garmentmaster() {
    }

    public Garmentmaster(Integer garmentId) {
        this.garmentId = garmentId;
    }

    public Garmentmaster(Integer garmentId, String garmentName, String size, String category, String description, BigInteger price, String stock) {
        this.garmentId = garmentId;
        this.garmentName = garmentName;
        this.size = size;
        this.category = category;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    public Integer getGarmentId() {
        return garmentId;
    }

    public void setGarmentId(Integer garmentId) {
        this.garmentId = garmentId;
    }

    public String getGarmentName() {
        return garmentName;
    }

    public void setGarmentName(String garmentName) {
        this.garmentName = garmentName;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigInteger getPrice() {
        return price;
    }

    public void setPrice(BigInteger price) {
        this.price = price;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (garmentId != null ? garmentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Garmentmaster)) {
            return false;
        }
        Garmentmaster other = (Garmentmaster) object;
        if ((this.garmentId == null && other.garmentId != null) || (this.garmentId != null && !this.garmentId.equals(other.garmentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.it.garmentsapp.entity.Garmentmaster[ garmentId=" + garmentId + " ]";
    }
    
}
