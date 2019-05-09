/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gsb.rv.dr;

import fr.gsb.rv.dr.entities.Visiteur;
import fr.gsb.rv.dr.modeles.ModeleGsbRv;
import fr.gsb.rv.technique.ConnexionBD;
import fr.gsb.rv.technique.ConnexionException;
import fr.gsb.rv.technique.Session;
import java.awt.Color;
import java.sql.Connection;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Pair;
import javafx.scene.layout.Pane; 
import java.awt.Panel;
import java.util.List;
import fr.gsb.rv.dr.entities.Praticien;
import fr.gsb.rv.dr.entities.RapportVisite;
import fr.gsb.rv.dr.modeles.ModeleGsbRv;
import fr.gsb.rv.dr.utilitaires.ComparateurConfiance;
import java.util.Collections;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
/**
 *
 * @author etudiant
 */
public class Appli extends Application {
    
    private  PanneauAccueil vueAcceuil = new PanneauAccueil();
    private  PanneauPraticiens vuePraticiens = new PanneauPraticiens(); 
    private  PanneauRapports vueRapports = new PanneauRapports();
    private StackPane p ;
    
    @Override
    public void start(Stage primaryStage) throws ConnexionException {
     
        
        
        BorderPane root = new BorderPane();
        
        
        MenuBar barreMenus = new MenuBar();
        Menu menuFichier = new Menu("Fichier");
     
        
        barreMenus.getMenus().add(menuFichier);
        Menu rapports = new Menu("Rapports");
        rapports.setDisable(true);
        barreMenus.getMenus().add(rapports);
       
        Menu practicien = new Menu("Practiciens");
        practicien.setDisable(true);
        barreMenus.getMenus().add(practicien);
        
        root.setTop(barreMenus);
        
        MenuItem itemSeConnecter = new MenuItem("Se connecter");
        menuFichier.getItems().add(itemSeConnecter);
        MenuItem itemSeDeconnecter = new MenuItem("Se déconnecter");
        itemSeDeconnecter.setDisable(true);
        menuFichier.getItems().add(itemSeDeconnecter);
        
        
        MenuItem itemQuitter = new MenuItem("Quitter");
        itemQuitter.setAccelerator(new KeyCodeCombination(KeyCode.X, KeyCombination.CONTROL_DOWN));
        menuFichier.getItems().add(itemQuitter);
        
       
        
        
                
        MenuItem itemConsulter = new MenuItem("Consulter");
        
        rapports.getItems().add(itemConsulter);
        
        MenuItem itemHesitant = new MenuItem("Hésitant");
        practicien.getItems().add(itemHesitant);
        
        
                  
          
        
          
          
        
        
       
           
        
        itemSeConnecter.setOnAction(
             new EventHandler<ActionEvent>(){
                 @Override
                 public void handle(ActionEvent event){
                     
                      vueAcceuil.toFront();
                     //Visiteur unVisiteur = new Visiteur();
                     //unVisiteur.setMatricule("OB001");
                     //unVisiteur.setPrenom("Oumayma");
                     //unVisiteur.setNom("Bellili");
                     //Session.ouvrir(unVisiteur);
                     //System.out.println(unVisiteur.getPrenom()+ " " + unVisiteur.getNom());
                     VueConnexion vue = new VueConnexion();
                     Optional<Pair<String, String>> reponse = vue.showAndWait();
                     
                     if(reponse.isPresent()){
                         
                         String matricule = reponse.get().getKey();
                         String mdp = reponse.get().getValue();
                         try {
                             Visiteur v = ModeleGsbRv.seConnecter(matricule,mdp);
                             if( v != null ){
                                 Session.ouvrir(v);
                                 System.out.println( v.getNom()+" "+v.getPrenom());
                                 
                                 menuFichier.setDisable(false);
                     itemSeConnecter.setDisable(true);
                     itemSeDeconnecter.setDisable(false);
                     itemQuitter.setDisable(false);
                     rapports.setDisable(false);
                     practicien.setDisable(false);
                            primaryStage.setTitle(Session.getSession().getLeVisiteur().getNom()+ " GSB-RV-DR");
                             }
                             
                             
                         } catch (ConnexionException ex) {
                             Logger.getLogger(Appli.class.getName()).log(Level.SEVERE, null, ex);
                         }
                         
                     }else{
                         System.out.println("Erreur d'authentification");
                     }                     

                     
                     
                 }
                 
             });
          
        itemSeDeconnecter.setOnAction(
             new EventHandler<ActionEvent>(){
                 @Override
                 public void handle(ActionEvent event){
                     
                     menuFichier.setDisable(false);
                     itemSeConnecter.setDisable(false);
                     itemSeDeconnecter.setDisable(true);
                     itemQuitter.setDisable(false);
                     rapports.setDisable(true);
                     practicien.setDisable(true);
                     //System.out.println(Session.getSession().getLeVisiteur().getPrenom()+" "+Session.getSession().getLeVisiteur().getNom());

                     
                     
                 }
                 
             });
        
        ButtonType btnOui = new ButtonType("Oui");
                     ButtonType btnNon = new ButtonType("Non");
                     Alert alertQuitter = new Alert(Alert.AlertType.CONFIRMATION);
                     alertQuitter.setTitle("Quittter");
                     alertQuitter.setHeaderText("Demande de confirmation");
                     alertQuitter.setContentText("Voulez-vous quitter l'application ?");
                    
                     alertQuitter.getButtonTypes().setAll(btnOui,btnNon);
                
                     
        
        itemQuitter.setOnAction(
             new EventHandler<ActionEvent>(){
                 @Override
                 public void handle(ActionEvent event){
                     
                     Optional<ButtonType> reponse = alertQuitter.showAndWait();
                     if(reponse.get() == btnOui){
                      Platform.exit();
                     }else{
                     alertQuitter.close();
                     }
                     
                     
                     
                 }
                 
             });
        
        itemConsulter.setOnAction(
             new EventHandler<ActionEvent>(){
                 @Override
                 public void handle(ActionEvent event){
                 
                     vueRapports.toFront();
                     //System.out.println(Session.getSession().getLeVisiteur().getPrenom()+" "+Session.getSession().getLeVisiteur().getNom());
                     

                     
                     
                 }
                 
             });
        
        itemHesitant.setOnAction(
             new EventHandler<ActionEvent>(){
                 @Override
                 public void handle(ActionEvent event){
                    
                       vuePraticiens.toFront();
                     //System.out.println(Session.getSession().getLeVisiteur().getPrenom()+" "+Session.getSession().getLeVisiteur().getNom());

                     
                     
                 }
                 
             });
        
        Scene scene = new Scene(root, 500, 500);
        
 
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
        StackPane st = new StackPane();
        st.getChildren().addAll(vueAcceuil,vueRapports,vuePraticiens);
        vueAcceuil.toFront();
        root.setCenter(st);
        
           List<Praticien> praticiens = ModeleGsbRv.getPraticiensHesitants() ;
            for (Praticien unPraticien : praticiens ){
            System.out.println(unPraticien);
} 
        
        
    }
    


    
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ConnexionException {
        
        
        try {
            
            ConnexionBD c1 = new ConnexionBD();
            System.out.println(c1 + "\n\n");
            
            
            //System.out.println( "\n\n1------" );
            //
            System.out.println( ModeleGsbRv.seConnecter("c14" , "azerty") ) ;
            
            
            //System.out.println( "\n\n2------" );
            //System.out.println( ModeleGsbRv.seConnecter("c14" , "azertysdfqsdfqsdfqsdfqs") ) ;
            
            //System.out.println( "\n\n3------" );
            //System.out.println( ModeleGsbRv.seConnecter("c99992929299299" , "dsfdsfdfdfdf") ) ;
           
           
        } catch (ConnexionException ex) {
            Logger.getLogger(Appli.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
                List<Praticien> praticiens = ModeleGsbRv.getPraticiensHesitants();
              //  for(Praticien unPraticien : praticiens){
              //  System.out.println(unPraticien);
              //  }
                
                
                
                
              Collections.reverse(praticiens);
                for(Praticien unPraticien : praticiens){
                System.out.println(unPraticien);
                }
                
                //test de la methode getVisiteurs de la classe ModelGsbRv
        List<Visiteur> visiteurs = ModeleGsbRv.getVisiteurs() ;
       
        for( Visiteur unVisiteur : visiteurs) {
            System.out.println(unVisiteur);
        }
        //test de la methode getRapportsVisite de la classe ModelGsbRv
        List<RapportVisite> rapportsVisiteLu = ModeleGsbRv.getRapportsVisite("c14", 3, 2018) ;
       
        for( RapportVisite unRapportVisite : rapportsVisiteLu) {
            System.out.println(unRapportVisite);
        }
       
       
        
         ModeleGsbRv.setRapportVisiteLu("c14", 1);
       
        for( RapportVisite unRapportVisite : rapportsVisiteLu) {
            System.out.println(unRapportVisite);
        }
        
        
                
                
       launch(args); 
        
        
        
       
    }
    
}
