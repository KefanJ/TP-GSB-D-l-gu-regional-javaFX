/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gsb.rv.dr;

import fr.gsb.rv.technique.Mois;
import fr.gsb.rv.dr.entities.RapportVisite;
import fr.gsb.rv.dr.entities.Visiteur;
import fr.gsb.rv.dr.modeles.ModeleGsbRv;
import fr.gsb.rv.technique.ConnexionException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;



/**
 *
 * @author etudiant
 */

    
     public class PanneauRapports extends Pane {
    
    private ComboBox<Visiteur> cbVisiteurs = new ComboBox<Visiteur>();
    private ComboBox<String> cbMois = new ComboBox<String>();
    private ComboBox<Integer> cbAnnee = new ComboBox<Integer>();
    
    TableView <RapportVisite> tabRapportVisite = new TableView <RapportVisite>();


    public PanneauRapports() {
         super() ;
        
        this.setStyle("-fx-background-color: white");
        
        VBox root = new VBox() ;
        
        root.getChildren().add( new Label("") ) ;
        
        this.getChildren().add( root ) ;
        
        GridPane gp = new GridPane();

       // Changer le nom des comBox
       cbVisiteurs.setPromptText("Visiteur");
       cbMois.setPromptText("Mois");
       cbAnnee.setPromptText("Année");

        // Création du bouton de validation
        
        Button valider = new Button("Valider");
        
       valider.setOnAction( (ActionEvent event)  -> {
            try {
                  if( cbVisiteurs.getValue() != null && cbMois.getValue() != null && cbAnnee.getValue() != null){
                      rafraichir();                    
                  } else {
                        Alert alert = new Alert (Alert.AlertType.ERROR);
                        alert.setTitle("ECHEC DE LA VALIDATION");
                        alert.setHeaderText("La selection est incomplète");
                        alert.setContentText("");
                        alert.showAndWait();
                  }
            } catch (Exception ex) {
                Logger.getLogger(PanneauRapports.class.getName()).log(Level.SEVERE, null, ex);
            }
           
            }
        ) ;       

 
        // Récup les visiteurs
         try {
              List<Visiteur> visiteur = ModeleGsbRv.getVisiteurs() ;
              cbVisiteurs.getItems().addAll(visiteur);
         } catch (ConnexionException ex) {
               Logger.getLogger(PanneauRapports.class.getName()).log(Level.SEVERE, null, ex);
        };  
        
        //Recup les mois
        try {
            for (Mois unMois : Mois.values()){
                cbMois.getItems().add(unMois.toString());
            }
            
        }
        catch (Exception ex) {
               Logger.getLogger(PanneauRapports.class.getName()).log(Level.SEVERE, null, ex);
        };  
        
        // Recup les années
        
        try {
       LocalDate aujourdhui = LocalDate.now();
       int anneeCourante = aujourdhui.getYear();
       cbAnnee.getItems().addAll(anneeCourante,anneeCourante - 1, anneeCourante -2, anneeCourante -3, anneeCourante - 4);
       }
       catch (Exception ex) {
               Logger.getLogger(PanneauRapports.class.getName()).log(Level.SEVERE, null, ex);
        }; 
        
         // Création des colonnes de la tb
        TableColumn<RapportVisite, Integer> colNumero = new TableColumn<RapportVisite,Integer>("Numéro"); 
        TableColumn<RapportVisite, String> colNomPraticien = new TableColumn<RapportVisite,String>("Nom praticien"); 
        TableColumn<RapportVisite, String> colVillePraticien = new TableColumn<RapportVisite,String>("Ville"); 
        TableColumn<RapportVisite, LocalDate> colVisite = new TableColumn<RapportVisite,LocalDate>("Visite"); 
        TableColumn<RapportVisite, LocalDate> colRedaction = new TableColumn<RapportVisite,LocalDate>("Rédaction"); 

        
        tabRapportVisite.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        colNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));

        
        colNomPraticien.setCellValueFactory(
             new Callback<CellDataFeatures<RapportVisite,String>,ObservableValue<String>>(){
            
                 @Override
             public ObservableValue<String> call(CellDataFeatures<RapportVisite, String> param) {
                 //System.out.println(  ) :
                 String nom = param.getValue().getLePraticien().getNom();
                return new SimpleStringProperty(nom);
             }
        }
  );
        
         colVisite.setCellFactory(
               colonne -> {   
             return new TableCell <RapportVisite,LocalDate>() {
                 @Override
                 protected void updateItem(LocalDate item, boolean empty){
                     super.updateItem(item, empty);
                     
                     if(empty){
                         setText("");
                     }
                     else {
                         DateTimeFormatter formateur = DateTimeFormatter.ofPattern("dd/MM/uuuu");
                         setText(item.format(formateur)) ;
                         //setText( "Date" ) ;
                     }
                 }
             };
                   
        } 
               
);
         
          tabRapportVisite.setRowFactory(

                ligne -> {

                    return new TableRow<RapportVisite>(){

                        @Override

                        protected void updateItem(RapportVisite item, boolean empty){

                            super.updateItem(item, empty);

                            if(item != null ){

                                if( item.isLu() ){

                                    setStyle("-fx-background-color: gold");

                                }

                                else{

                                    setStyle("-fx-background-color: cyan");

                                }

                            }

                        }

                    };

                }

); 
          
          
     
          
          tabRapportVisite.setOnMouseClicked(
                (MouseEvent event) -> {
                    if( event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2 ){
                        int indiceRapport = tabRapportVisite.getSelectionModel().getSelectedIndex();
                        Visiteur leVisiteur = (Visiteur) cbVisiteurs.getSelectionModel().getSelectedItem();
                        RapportVisite rv = tabRapportVisite.getItems().get(indiceRapport);
                        
                        System.out.println( "Sélection : " + rv ) ;
                        
                        try {
                            ModeleGsbRv.setRapportVisiteLu(leVisiteur.getMatricule(), rv.getNumero());
                            
                            this.rafraichir();
                            
                            
                            VueRapport vueRapport = new VueRapport(rv);
                            vueRapport.showAndWait();
                            
                        } catch (ConnexionException ex) {
                            Logger.getLogger(PanneauRapports.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
);
        
        
        colVisite.setCellValueFactory(new PropertyValueFactory<>("dateVisite"));
        colRedaction.setCellValueFactory(new PropertyValueFactory<>("dateRedaction"));

        
       tabRapportVisite.getColumns().addAll(colNumero,colNomPraticien,colVillePraticien,colVisite,colRedaction);
       
       gp.add(cbVisiteurs,0,0);
       gp.add(cbMois,1,0);
       gp.add(cbAnnee,2,0);
       gp.add(valider,0,1);
       root.getChildren().add(gp);
       root.getChildren().add(tabRapportVisite);
      
       
    
                   
        } 
               
      
                   
         
               
       
        

        
      

                            

                        

                    

                

        
       
    
      
    
     public void rafraichir() throws ConnexionException{
         //System.out.println(cbVisiteurs.getValue()+ " " + cbMois.getValue()+ " " + cbAnnee.getValue()); 
            Visiteur leVisiteur;
            leVisiteur = (Visiteur) cbVisiteurs.getSelectionModel().getSelectedItem();
            int mois = cbMois.getSelectionModel().getSelectedIndex()+1;
            int annee = (int) cbAnnee.getSelectionModel().getSelectedItem();
            
            System.out.println( "" + leVisiteur.getMatricule() + "," + mois + "," + annee ) ;
            
            List<RapportVisite> rapportVisite = ModeleGsbRv.getRapportsVisite(leVisiteur.getMatricule(),mois,annee);
            ObservableList<RapportVisite> obListe = FXCollections.observableArrayList(rapportVisite);
            
           tabRapportVisite.setItems(obListe);
            
        }
         
}
    

