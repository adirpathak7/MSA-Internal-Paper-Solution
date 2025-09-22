/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.it.garmentsclientapp.bean;

import com.it.garmentsclientapp.entity.Garmentmaster;
import com.it.garmentsclientapp.garments.GarmentsService;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.ws.rs.core.GenericType;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author Aditya Pathak R
 */
@Named(value = "garments")
@SessionScoped
public class GarmentsReportBean implements Serializable {

    @Inject
    GarmentsService garmentsService;

    private List<Garmentmaster> listOfGarment;
    private String selectedCategory;
    private String selectedPriceRange;

    public GarmentsReportBean() {
    }

    public String getHello() {
        return garmentsService.get().readEntity(String.class);
    }

    public List<Garmentmaster> getListOfGarment() {
        if (listOfGarment == null) {
            listOfGarment = garmentsService.getAllGarments()
                    .readEntity(new GenericType<List<Garmentmaster>>() {
                    });
//            System.out.println("listOfGarment:- " + listOfGarment);
        }
        return listOfGarment;
    }

    public void findByCategory() {
        if (selectedCategory != null) {
            listOfGarment = garmentsService.findGarmentsByByCategory(selectedCategory)
                    .readEntity(new GenericType<List<Garmentmaster>>() {
                    });
        }
    }

    public void findByPriceRange(BigInteger min, BigInteger max) {
        listOfGarment = garmentsService.findGarmentsByPrice(min, max)
                .readEntity(new GenericType<List<Garmentmaster>>() {
                });
//        System.out.println("the listOfGarment in findByPriceRange:- " + listOfGarment);
    }

    public void filterPrice() {
        if (selectedPriceRange != null && !selectedPriceRange.isEmpty()) {
            String[] p = selectedPriceRange.split("-");
            BigInteger min = new BigInteger(p[0]);
            BigInteger max = new BigInteger(p[1]);
//            System.out.println("min and max:- " + min + " " + max);
            findByPriceRange(min, max);
        }
    }

    public void setListOfGarment(List<Garmentmaster> listOfGarment) {
        this.listOfGarment = listOfGarment;
    }

    public String getSelectedCategory() {
        return selectedCategory;
    }

    public void setSelectedCategory(String selectedCategory) {
        this.selectedCategory = selectedCategory;
    }

    public String getSelectedPriceRange() {
        return selectedPriceRange;
    }

    public void setSelectedPriceRange(String selectedPriceRange) {
        this.selectedPriceRange = selectedPriceRange;
    }

}
