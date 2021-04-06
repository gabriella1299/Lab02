package it.polito.tdp.alien;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private AlienDictionary model;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtInserisci;

    @FXML
    private TextArea txtRisultato;

    @FXML
    void doClear(ActionEvent event) {
    	txtRisultato.clear();
    	txtInserisci.clear();
    	model.resetDictionary();
    }
    
    @FXML
    void doTranslate(ActionEvent event) {

		txtRisultato.clear();
		String riga = txtInserisci.getText().toLowerCase();

		// Controllo sull'input
		if (riga == null || riga.length() == 0) {
			txtRisultato.setText("Inserire una o due parole.");
			return;
		}

		StringTokenizer st = new StringTokenizer(riga, " ");

		// Controllo su String Tokenizer (superfluo)
		if (!st.hasMoreElements()) {
			txtRisultato.setText("Inserire una o due parole.");
			return;
		}

		// Estraggo la prima parola
		String alienWord = st.nextToken();

		if (st.hasMoreTokens()) {
			// Devo inserire nel dizionario

			// Estraggo la seconda parola
			String translation = st.nextToken();

			if (!alienWord.matches("[a-zA-Z]*") || !translation.matches("[a-zA-Z]*")) {
				txtRisultato.setText("Inserire solo caratteri alfabetici.");
				return;
			}

			// Aggiungo la parola aliena e traduzione nel dizionario
			model.addWord(alienWord, translation);

			txtRisultato.setText("La parola: " + alienWord + " traduzione: " + translation + " è stata inserita.");

		} else {

			// Controllo che non ci siano caratteri non ammessi
			if (!alienWord.matches("[a-zA-Z?]*")) {
				txtRisultato.setText("Inserire solo caratteri alfabetici.");
				return;
			}

			String translation; //non la traduciamo subito perche' se c'e' un puntp interrogativo abbiamo una wildcar

			if (alienWord.matches("[a-zA-Z?]*") && !alienWord.matches("[a-zA-Z]*")) {

				// Traduzione con WildCard
				translation = model.translateWordWildCard(alienWord);

			} else {

				// Traduzione classica
				translation = model.translateWord(alienWord);
			}

			if (translation != null) {
				txtRisultato.setText(translation);
			} else {
				txtRisultato.setText("La parola cercata non esiste nel dizionario.");
			}
		}

    }
   /* @FXML
    void doTranslate(ActionEvent event) {
    	txtRisultato.clear();
    	String par=txtInserisci.getText().toLowerCase();
    	if(par=="") { //if (riga == null || riga.length() == 0)
    		this.txtRisultato.setText("Inserire una parola!");
    		return;
    	}
    	if(par.matches(".*[0-9].*")) { //if(!par.matches("[a-zA-Z]]")
    		this.txtRisultato.setText("Inserire solo caratteri alfabetici!");
    		return;
    	}
    	if(par.contains(" ")) {
    		String[] array=par.split(" "); //StringTokenizer st=new StringTokenizer(riga, " ");
    		model.addWord(array[0],model.generaListaTraduzioni(array));
        	this.txtRisultato.setText("Parola e sue traduzioni aggiunte al dizionario!");
    	}
    	else {
    		try {
    			model.translateWord(par);
    			this.txtRisultato.setText("Le traduzioni della parola "+par+ " sono: "+model.ricercaWord(par).toString());
    		
    		}catch(NullPointerException e) {
    			this.txtRisultato.setText("Parola inesistente!");
    		}
    	}
    	txtInserisci.clear();
    }
    */
    
    public void setModel(AlienDictionary model) {
    	this.model=model;
    }
    
    @FXML
    void initialize() {
        assert txtInserisci != null : "fx:id=\"txtInserisci\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";

    }
}
