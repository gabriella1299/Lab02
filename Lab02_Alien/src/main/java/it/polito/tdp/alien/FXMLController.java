package it.polito.tdp.alien;

import java.net.URL;
import java.util.ResourceBundle;

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
    	String par=txtInserisci.getText().toLowerCase();
    	if(par.matches(".*[0-9].*")) {
    		this.txtRisultato.setText("Inserire solo caratteri alfabetici!");
    		return;
    	}
    	if(par.contains(" ")) {
    		String[] array=par.split(" ");
    		if(array.length>2)
    			this.txtRisultato.setText("Inserire al massimo due parole separate da uno spazio!");
    		else {
    			model.addWord(array[0],array[1]);
        		this.txtRisultato.setText("Parola e sua traduzione aggiunte al dizionario!");
    		}
    	}
    	else {
    		try {
    			
    			model.translateWord(par);
    			this.txtRisultato.setText("La traduzione della parola "+par+ " e': "+model.ricercaWord(par).getTranslation());
    		
    		}catch(NullPointerException e) {
    			this.txtRisultato.setText("Parola inesistente!");
    		}
    	}
    	txtInserisci.clear();
    }
    
    public void setModel(AlienDictionary model) {
    	this.model=model;
    }
    
    @FXML
    void initialize() {
        assert txtInserisci != null : "fx:id=\"txtInserisci\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";

    }
}
