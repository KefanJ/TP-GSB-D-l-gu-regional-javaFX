/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gsb.rv.technique;

import fr.gsb.rv.dr.entities.Visiteur;

/**
 *
 * @author etudiant
 */
public class Session {
     private static Session session = null ;
    Visiteur leVisiteur;
    
    
    
    public Session(Visiteur leVisiteur){
        this.leVisiteur = leVisiteur;
        
    }
    
    public static  void ouvrir(Visiteur leVisiteur){
        
        Session.session = new Session(leVisiteur);
    }
    
    
    public static void fermer () {
        Session.session = null;
        
    }
    
    public static  Session getSession(){
        return session;
        
    }
    
    
    public Visiteur getLeVisiteur(){
        return leVisiteur;
    }
    
    
    public boolean estOuverte(){
        
        
        
        if(session == null){
           return false;
            
        }
        else{
            return true;
        }
       
        
      
        
    }
    
}
