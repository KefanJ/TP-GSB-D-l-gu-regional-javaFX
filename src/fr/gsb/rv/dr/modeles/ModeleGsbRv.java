package fr.gsb.rv.dr.modeles;


import fr.gsb.rv.dr.entities.Praticien;
import fr.gsb.rv.dr.entities.RapportVisite;
import fr.gsb.rv.dr.entities.Visiteur;
import fr.gsb.rv.technique.ConnexionBD;
import fr.gsb.rv.technique.ConnexionException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ModeleGsbRv {
    
    public static Visiteur seConnecter( String matricule , String mdp ) throws ConnexionException{
        
        Connection connexion = ConnexionBD.getConnexion() ;
        
        String requete = "select vis_nom,vis_prenom \n"
                + "from Travailler t1 \n"
                + " inner join Visiteur v \n" 
                + " on v.vis_matricule = t1.vis_matricule \n "
                + " where v.vis_matricule = ? \n"
                + " and v.vis_mdp = ? \n"
                + " and t1.jjmmaa = ( \n "
                + " select max(t2.jjmmaa) \n "
                + " from Travailler t2 \n "
                + " where t1.vis_matricule = t2.vis_matricule) \n "
                + " and t1.tra_role ='Délégué' \n";
        
        
        try {
            PreparedStatement requetePreparee = (PreparedStatement) connexion.prepareStatement( requete ) ;
            requetePreparee.setString(1, matricule);
            requetePreparee.setString(2, mdp);
            System.out.println( requetePreparee.toString() ) ;
            ResultSet resultat = requetePreparee.executeQuery() ;
            if( resultat.next() ){
                Visiteur visiteur = new Visiteur() ;
                visiteur.setMatricule( matricule );
                visiteur.setNom( resultat.getString( "vis_nom" ) ) ;
                visiteur.setPrenom((resultat.getString("vis_prenom"))); 
                
                requetePreparee.close() ;
                return visiteur ;
            }
            else {
                return null ;
            }
        }
        catch( Exception e ){
            return null ;
        } 
        
       } 
    
    public static List<Praticien> getPraticiensHesitants()  throws ConnexionException {
        
      Connection connexion = ConnexionBD.getConnexion() ;
    
      String requete = "select RapportVisite.pra_num,pra_prenom,pra_nom,pra_ville,pra_coefnotoriete,rap_date_visite, rap_coef_confiance "
                     + "from RapportVisite "
                     + "inner join Praticien "
                     + "on RapportVisite.pra_num = Praticien.pra_num "
                     + "where rap_coef_confiance < 5 "
                     + "and rap_date_visite = ( "
                     + "select MAX(rap_date_visite) "
                     + "from RapportVisite "
                     + "where RapportVisite.pra_num = Praticien.pra_num)";
             
      
      
      
      
      try {
            PreparedStatement requetePreparee = (PreparedStatement) connexion.prepareStatement(requete);
             
            System.out.println( requetePreparee.toString() ) ;
            
            List<Praticien> praticiens = new ArrayList<Praticien>();
            
            ResultSet resultat = requetePreparee.executeQuery() ;
            
           
            
            while (resultat.next() ){
                
                    Praticien praticien = new Praticien();
                    praticien.setNumero(resultat.getInt("pra_num") );
                    praticien.setPrenom(resultat.getString("pra_prenom"));
                    praticien.setNom(resultat.getString("pra_nom"));
                    praticien.setVille(resultat.getString("pra_ville"));
                    praticien.setCoefNotoriete(resultat.getFloat("pra_coefnotoriete"));
                    praticien.setDateDerniereVisite(resultat.getDate("rap_date_visite").toLocalDate());
                    praticien.setDernierCoefConfiance(resultat.getInt("rap_coef_confiance"));
                    
                    praticiens.add(praticien);
            
             
            
            }
            requetePreparee.close();
            return praticiens;
            
      }
      
      
      
     
        catch( Exception e ){
            System.out.println("Problème de connexion à la base de donnée");
            return null ;
        } 
      
      
      
      
    }
    
     public static List<Visiteur> getVisiteurs() throws ConnexionException {
       
        Connection connexion = ConnexionBD.getConnexion() ;

        String requete ="select vis_matricule, vis_nom, vis_prenom" +
                        " from Visiteur;";

        try {
            PreparedStatement requetePreparee = (PreparedStatement) connexion.prepareStatement( requete ) ;
    
           
            System.out.println( requetePreparee.toString() ) ;
           
            List<Visiteur> visiteurs = new ArrayList<Visiteur>() ;
           
            ResultSet resultat = requetePreparee.executeQuery() ;
                      
          
           
            while ( resultat.next() ){
                Visiteur visiteur = new Visiteur() ;
                visiteur.setMatricule( resultat.getString( "vis_matricule" ) );
                visiteur.setNom( resultat.getString("vis_nom") );
                visiteur.setPrenom( resultat.getString("vis_prenom") );
                      
                visiteurs.add(visiteur);
            }
            requetePreparee.close() ;
            return visiteurs ;
        }
       
        catch( Exception e ){
            System.out.println( "Probleme de connexion à la  Base de donnée" ) ;
            return null ;    
        }
    
}
     
     
     public static List<RapportVisite> getRapportsVisite(String matricule, int mois, int annee) throws ConnexionException {
       
        Connection connexion = ConnexionBD.getConnexion() ;

        String requete = "select * \n" +
                            "from RapportVisite r\n" +
                            "inner join Praticien p\n"+
                            "on r.pra_num = p.pra_num\n"+                
                            "where vis_matricule = ? \n" +
                            "and month(r.rap_date_visite) = ? \n" +
                            "and year(r.rap_date_visite) = ? \n";
                

        try {
            PreparedStatement requetePreparee = (PreparedStatement) connexion.prepareStatement( requete ) ;
    
            requetePreparee.setString( 1 , matricule );
            requetePreparee.setInt(2 , mois );
            requetePreparee.setInt( 3 , annee );
           
            System.out.println( requetePreparee.toString() ) ;
           
            List<RapportVisite> rapportsVisite = new ArrayList<RapportVisite>() ;
           
            ResultSet resultat = requetePreparee.executeQuery() ;
                      
            //System.out.println( "1" ) ;
           
            while ( resultat.next() ){
                RapportVisite rapportVisite = new RapportVisite() ;
                rapportVisite.setNumero( resultat.getInt( "rap_num" ) );
                rapportVisite.setCoefConfiance( resultat.getInt("rap_coef_confiance") );
                rapportVisite.setDateVisite(resultat.getDate("rap_date_visite").toLocalDate());
                rapportVisite.setDateRedaction(resultat.getDate("rap_date_redaction").toLocalDate());
                rapportVisite.setBilan( resultat.getString( "rap_bilan" ) );
                rapportVisite.setMotif( resultat.getString( "rap_motif" ) );
                rapportVisite.setLu( resultat.getBoolean( "rap_lu" ) );
                
                
                Praticien praticien = new Praticien() ;
                praticien.setNumero( resultat.getInt( "pra_num" ) );
                praticien.setNom( resultat.getString("pra_nom") );
                praticien.setVille( resultat.getString("pra_ville") );
                praticien.setCoefNotoriete( resultat.getFloat("pra_coefnotoriete"));
                //praticien.setDateDerniereVisite( resultat.getDate("rap_date_visite").toLocalDate());
                //praticien.setDernierCoefConfiance( resultat.getInt("rap_coef_confiance") );
                
                rapportVisite.setLePraticien( praticien );
                      
                rapportsVisite.add(rapportVisite);
                
                System.out.println( "-> " + rapportVisite ) ;
                
            }
            requetePreparee.close() ;
            return rapportsVisite ;
        }
       
        catch( Exception e ){
            System.out.println( "Problème de connexion à la  Base de donnée" ) ;
            return null ;    
        }
    
}
     
     public static void setRapportVisiteLu(String matricule, int numRapport) {
       
        

        String requete ="update RapportVisite" +
                        " set rap_lu = 1" + 
                        " where rap_num = ?" +
                        " and vis_matricule = ?" ;

        try {
            Connection connexion = ConnexionBD.getConnexion() ;
            System.out.println( "1 " + requete ) ;
            PreparedStatement requetePreparee = (PreparedStatement) connexion.prepareStatement( requete ) ;
            System.out.println( "2 " + requetePreparee.toString()) ;
            requetePreparee.setInt( 1 , numRapport );
            System.out.println( "3 " + requetePreparee.toString()) ;
            requetePreparee.setString( 2 , matricule );
            System.out.println( "4 " + requetePreparee.toString()) ;
           
            System.out.println( requetePreparee.toString() ) ;
           
            requetePreparee.executeUpdate() ;
          
                      
            requetePreparee.close() ;
        }
       
        catch( Exception e ){
            System.out.println( "Problème connexion à la Base de donnée" ) ;
        }
    
}
    
     
    }
