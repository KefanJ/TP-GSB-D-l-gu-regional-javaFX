/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gsb.rv.dr;

import fr.gsb.rv.dr.entities.Praticien;
import fr.gsb.rv.dr.modeles.ModeleGsbRv;
import fr.gsb.rv.dr.utilitaires.ComparateurCoefNotoriete;
import fr.gsb.rv.dr.utilitaires.ComparateurConfiance;
import fr.gsb.rv.dr.utilitaires.ComparateurDateVisite;
import fr.gsb.rv.technique.ConnexionException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


/**
 *
 * @author etudiant
 */
public class PanneauPraticiens extends Pane {
    
    private static int CRITERE_COEF_CONFIANCE = 1;
    private static int CRITERE_COEF_NOTORIETE = 2;
    private static int CRITERE_DATE_VISITE = 3;
    
    
    private  int critereTri = CRITERE_COEF_CONFIANCE ;
            
    private RadioButton rdoConfiance = new RadioButton("Confiance");
    private RadioButton rdoNotoriete = new RadioButton("Notoriété"); 
    private RadioButton rdoDateVisite = new RadioButton("Date Visite"); 
    
    TableView<Praticien> tPraticiens =  new TableView<Praticien>();
  
    
  
   
    
    
    
    
   
    public void Raffraichir() {
        try{
        List<Praticien> praticiens = ModeleGsbRv.getPraticiensHesitants();
        
        
        ObservableList<Praticien> op = FXCollections.observableArrayList(praticiens);
        
        if(critereTri == CRITERE_COEF_CONFIANCE){
        
            Collections.sort(op, new ComparateurConfiance());

        
        }
        
        else if(critereTri == CRITERE_COEF_NOTORIETE){
        
            //Collections.sort(op, new ComparateurCoefNotoriete().reversed());
            Collections.sort(op, new ComparateurCoefNotoriete());
            Collections.reverse(op);
        
        }
        
        else if(critereTri == CRITERE_DATE_VISITE){
        
          Collections.sort(op, new ComparateurDateVisite().reversed());
        
        }
        
        tPraticiens.setItems(op);
 
    
    } 
    catch (ConnexionException ex) {
            Logger.getLogger(Appli.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
       
    }
    
    
    
    
    public void CritèreTri(Integer critereTri){
        
     critereTri = this.critereTri;
        
    
   rdoConfiance.setSelected(true);
   
  
    }
    
     
    public PanneauPraticiens (){
        
        super();
        
       this.setStyle("-fx-background-color:white");
       VBox vPraticiens = new VBox();
       vPraticiens.getChildren().add(new Label("Sélectionner un critère de tri :"));
       vPraticiens.setStyle("-fx-font-weight: bold");
       this.getChildren().add(vPraticiens);
       
       
       
       
      vPraticiens.setSpacing(10);
      vPraticiens.setPadding(new Insets(10));
      
      
      GridPane gpPraticiens = new GridPane();
      gpPraticiens.setHgap(10);
      gpPraticiens.setVgap(10);
      
      
      ToggleGroup tlgPraticien = new ToggleGroup();
      rdoConfiance.setToggleGroup(tlgPraticien);
      rdoNotoriete.setToggleGroup(tlgPraticien);
      rdoDateVisite.setToggleGroup(tlgPraticien);
      
      rdoConfiance.setSelected(true);
      
     HBox hbPraticien = new HBox(20,rdoConfiance,rdoNotoriete,rdoDateVisite);
     
     
  
      
     gpPraticiens.getChildren().add(hbPraticien);
    
      
      
      TableColumn<Praticien,Integer> numCol = new TableColumn<Praticien, Integer>("Numéro");
      TableColumn<Praticien,String>prenomCol = new TableColumn<Praticien, String>("Prénom");
      TableColumn<Praticien,String> nomCol = new TableColumn<Praticien, String>("Nom");
      TableColumn<Praticien,String> villeCol = new TableColumn<Praticien, String>("Ville");
      
       numCol.setCellValueFactory(new PropertyValueFactory<>("numero"));
       prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
       nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
       villeCol.setCellValueFactory(new PropertyValueFactory<>("ville"));
      
      
      
      tPraticiens.getColumns().addAll(numCol,prenomCol,nomCol,villeCol);
      tPraticiens.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    
      
      vPraticiens.getChildren().add(gpPraticiens);
      vPraticiens.getChildren().add(tPraticiens);
      
      rdoConfiance.setOnAction( (ActionEvent event) -> {
          
          critereTri = CRITERE_COEF_CONFIANCE;
          this.Raffraichir();
      }
               );
      
       rdoNotoriete.setOnAction( (ActionEvent event) -> {
          
          critereTri = CRITERE_COEF_NOTORIETE;
          this.Raffraichir();
      }
               );  
       
       
       
        rdoDateVisite.setOnAction( (ActionEvent event) -> {
          
          critereTri = CRITERE_DATE_VISITE;
          this.Raffraichir();
      }
               );
       
       
    }
    
    
    
    
    
    
    
    
    
    }
   
   
        
    
   
    
   
     
    

