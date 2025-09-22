/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.it.garmentsapp.bean;

import com.it.garmentsapp.entity.Garmentmaster;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author Aditya Pathak R
 */
@Stateless
public class GarmentsBean {

    @PersistenceContext(unitName = "mypu")
    EntityManager em;

    public List<Garmentmaster> getAllGarments() {
        List<Garmentmaster> list = em.createNamedQuery("Garmentmaster.findAll").getResultList();
        return list;
    }

    public List<Garmentmaster> findGarmentsByByCategory(String category) {
        List<Garmentmaster> list = em.createNamedQuery("Garmentmaster.findByCategory")
                .setParameter("category", category)
                .getResultList();
        return list;
    }

    public List<Garmentmaster> findGarmentsByPrice(BigInteger min, BigInteger max) {
        List<Garmentmaster> list = em.createQuery("SELECT g FROM Garmentmaster g WHERE g.price BETWEEN :min AND :max", Garmentmaster.class)
                .setParameter("min", min).setParameter("max", max)
                .getResultList();
        return list;
    }
}
