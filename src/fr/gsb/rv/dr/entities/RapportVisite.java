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
public class RapportVisite {
  
    private int numero;
    private LocalDate dateVisite;
    private LocalDate dateRedaction;
    private String bilan;
    private String motif;
    private int coefConfiance;
    private boolean lu;
    
    private Visiteur leVisiteur ;
    private Praticien lePraticien ;

    
    public RapportVisite(){}
    
    public RapportVisite(int unNumero, LocalDate uneDateVisite,LocalDate uneDateRedaction,String unBilan,String unMotif,int unCoefConfiance, boolean unLu){
        this.numero = unNumero;
        this.dateVisite = uneDateVisite;
        this.dateRedaction = uneDateRedaction;
        this.bilan = unBilan;
        this.motif = unMotif;
        this.coefConfiance = unCoefConfiance;
        this.lu = unLu;
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
     * @return the dateVisite
     */
    public LocalDate getDateVisite() {
        return dateVisite;
    }

    /**
     * @param dateVisite the dateVisite to set
     */
    public void setDateVisite(LocalDate dateVisite) {
        this.dateVisite = dateVisite;
    }

    /**
     * @return the dateRedaction
     */
    public LocalDate getDateRedaction() {
        return dateRedaction;
    }

    /**
     * @param dateRedaction the dateRedaction to set
     */
    public void setDateRedaction(LocalDate dateRedaction) {
        this.dateRedaction = dateRedaction;
    }

    /**
     * @return the bilan
     */
    public String getBilan() {
        return bilan;
    }

    /**
     * @param bilan the bilan to set
     */
    public void setBilan(String bilan) {
        this.bilan = bilan;
    }

    /**
     * @return the motif
     */
    public String getMotif() {
        return motif;
    }

    /**
     * @param motif the motif to set
     */
    public void setMotif(String motif) {
        this.motif = motif;
    }

    /**
     * @return the coefConfiance
     */
    public int getCoefConfiance() {
        return coefConfiance;
    }

    /**
     * @param coefConfiance the coefConfiance to set
     */
    public void setCoefConfiance(int coefConfiance) {
        this.coefConfiance = coefConfiance;
    }

    /**
     * @return the lu
     */
    public boolean isLu() {
        return lu;
    }

    /**
     * @param lu the lu to set
     */
    public void setLu(boolean lu) {
        this.lu = lu;
    }
    
    public String toString() {
        return "RapportVisite{" + "numero=" + numero + ", coefConfiance=" + coefConfiance + ", dateVisite=" + dateVisite + ", dateRedaction=" + dateRedaction + ", bilan=" + bilan + ", motif=" + motif + ", lu=" + lu + ", leVisiteur=" + getLeVisiteur() + ", lePraticien=" + getLePraticien() + '}';
}

    /**
     * @param leVisiteur the leVisiteur to set
     */
    public void setLeVisiteur(Visiteur leVisiteur) {
        this.leVisiteur = leVisiteur;
    }

    /**
     * @param lePraticien the lePraticien to set
     */
    public void setLePraticien(Praticien lePraticien) {
        this.lePraticien = lePraticien;
    }

    /**
     * @return the leVisiteur
     */
    public Visiteur getLeVisiteur() {
        return leVisiteur;
    }

    /**
     * @return the lePraticien
     */
    public Praticien getLePraticien() {
        return lePraticien;
    }
}
