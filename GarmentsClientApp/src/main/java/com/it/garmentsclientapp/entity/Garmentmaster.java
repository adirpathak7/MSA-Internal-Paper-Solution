/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.it.garmentsclientapp.entity;

import jakarta.persistence.Entity;
import java.io.Serializable;

/**
 *
 * @author Aditya Pathak R
 */
//@Entity
public class Garmentmaster implements Serializable {

    private Integer garmentId;
    private String garmentName;
    private String size;
    private String category;
    private String description;
    private double price;
    private String stock;

    public Garmentmaster() {

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

}
