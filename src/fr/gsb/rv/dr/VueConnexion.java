/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gsb.rv.dr;

import javafx.scene.control.Button;
import static javafx.scene.control.ButtonBar.ButtonData.CANCEL_CLOSE;
import static javafx.scene.control.ButtonBar.ButtonData.OK_DONE;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.Pair;

/**
 *
 * @author etudiant
 */
public class VueConnexion extends Dialog<Pair<String,String>>{
    public VueConnexion(){
        super();
        setTitle("Authentification");
        setHeaderText("Saisir vos données de connexion.");
        Label lMatricule = new Label("Matricule :");
        Label lMdp = new Label("Mot de passe :");
        TextField tfMatricule = new TextField("");
        HBox hMatricule = new HBox(10);
        hMatricule.getChildren().addAll(lMatricule,tfMatricule);
        PasswordField pfMdp = new PasswordField();
        HBox hMdp = new HBox(10);
        hMdp.getChildren().addAll(lMdp,pfMdp);
        VBox vB1 = new VBox(15);
        vB1.getChildren().addAll(hMatricule,hMdp);
        getDialogPane().setContent(vB1);
        
        ButtonType btnOk = new ButtonType("Ok");
        ButtonType btnCancel = new ButtonType("Cancel");
        this.getDialogPane().getButtonTypes().addAll(btnOk,btnCancel);
        
        setResultConverter(
                new Callback<ButtonType, Pair<String,String>>(){
                    
                      @Override
        
                    public Pair<String,String> call(ButtonType typeBouton){
                        if( typeBouton == btnOk){
                            String matricule = tfMatricule.getText() ;
                            String mdp = pfMdp.getText();
                            
                            
                            return new Pair<String,String>(matricule, mdp) ;
                            
                        }
                        return null;
                        
                    }
        
                }
        ) ;
        
        
    
        
        
    }
    
}
