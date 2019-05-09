/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gsb.rv.dr.entities;

import java.time.LocalDate;

/**
 *
 * @author etudiant
 */
public class Praticien {
 
    private int numero;
    private String nom;
    private String prenom;
    private String ville;
    private double coefNotoriete;
    private LocalDate dateDerniereVisite;
    private int  dernierCoefConfiance;
    
    public Praticien(int unNumero, String unNom, String unPrenom, String unVille, double coefNotoriete, LocalDate unDateDerniereVisite, int unDernierCoefConfiance ) {
    
        unNumero = this.numero;
        unPrenom = this.prenom;
        unNom = this.nom;
        unVille = this.ville;
        coefNotoriete = this.coefNotoriete;
        unDateDerniereVisite = this.dateDerniereVisite;
        unDernierCoefConfiance = this.dernierCoefConfiance;
        
        
        
    }

    public Praticien() {
        
    }

    /**
     * @return the numero
     */
    public int getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the ville
     */
    public String getVille() {
        return ville;
    }

    /**
     * @param ville the ville to set
     */
    public void setVille(String ville) {
        this.ville = ville;
    }

    /**
     * @return the coefNotoriete
     */
    public double getCoefNotoriete() {
        return coefNotoriete;
    }

    /**
     * @param coefNotoriete the coefNotoriete to set
     */
    public void setCoefNotoriete(double coefNotoriete) {
        this.coefNotoriete = coefNotoriete;
    }

    /**
     * @return the dateDerniereVisite
     */
    public LocalDate getDateDerniereVisite() {
        return dateDerniereVisite;
    }

    /**
     * @param dateDerniereVisite the dateDerniereVisite to set
     */
    public void setDateDerniereVisite(LocalDate dateDerniereVisite) {
        this.dateDerniereVisite = dateDerniereVisite;
    }

    /**
     * @return the dernierCoefConfiance
     */
    public int getDernierCoefConfiance() {
        return dernierCoefConfiance;
    }

    /**
     * @param dernierCoefConfiance the dernierCoefConfiance to set
     */
    public void setDernierCoefConfiance(int dernierCoefConfiance) {
        this.dernierCoefConfiance = dernierCoefConfiance;
    }
    
    
    public String toString(){
    
        return this.numero +" "+ this.prenom +" "+  this.nom +" "+  this.ville+" "+ this.coefNotoriete +" "+ this.dateDerniereVisite+" "+ this.dernierCoefConfiance;
    }

    /**
     * @return the prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * @param prenom the prenom to set
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    
}
