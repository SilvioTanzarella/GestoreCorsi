package it.polito.tdp.corsi;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import it.polito.tdp.corsi.model.Corso;
import it.polito.tdp.corsi.model.Model;
import it.polito.tdp.corsi.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtPeriodo"
    private TextField txtPeriodo; // Value injected by FXMLLoader

    @FXML // fx:id="txtCorso"
    private TextField txtCorso; // Value injected by FXMLLoader

    @FXML // fx:id="btnCorsiPerPeriodo"
    private Button btnCorsiPerPeriodo; // Value injected by FXMLLoader

    @FXML // fx:id="btnNumeroStudenti"
    private Button btnNumeroStudenti; // Value injected by FXMLLoader

    @FXML // fx:id="btnStudenti"
    private Button btnStudenti; // Value injected by FXMLLoader

    @FXML // fx:id="btnDivisioneStudenti"
    private Button btnDivisioneStudenti; // Value injected by FXMLLoader

    @FXML // fx:id="txtRisultato"
    private TextArea txtRisultato; // Value injected by FXMLLoader

    @FXML
    void corsiPerPeriodo(ActionEvent event) {
    	this.txtRisultato.clear();
    	int periodo;
    	try{
    		periodo  = Integer.parseInt(this.txtPeriodo.getText());
    	}catch(NumberFormatException e) {
    		this.txtRisultato.setText("Inserire 1 o 2");
    		return;
    	}
    	
    	if(periodo!=1 && periodo!=2) {
    		this.txtRisultato.setText("Inserire 1 o 2");
    		return;
    	}

    	
    	List<Corso> risultato = this.model.corsiPerPeriodo(periodo);
    	
    	for(Corso c: risultato) {
    		this.txtRisultato.appendText(c.toString()+"\n");
    	}
    	
    	this.txtPeriodo.clear();
    	
    	
    }

    @FXML
    void numeroStudenti(ActionEvent event) {
    	this.txtRisultato.clear();
    	int periodo;
    	try{
    		periodo  = Integer.parseInt(this.txtPeriodo.getText());
    	}catch(NumberFormatException e) {
    		this.txtRisultato.setText("Inserire 1 o 2");
    		return;
    	}
    	
    	if(periodo!=1 && periodo!=2) {
    		this.txtRisultato.setText("Inserire 1 o 2");
    		return;
    	}
    	
    	Map<Corso,Integer> risultato = this.model.iscrittiPerCorsoPerPeriodo(periodo);
    	for(Corso c: risultato.keySet()) {
    		this.txtRisultato.appendText(c.toString()+", iscritti: "+risultato.get(c)+"\n");
    	}
    	
    }

    @FXML
    void stampaDivisione(ActionEvent event) {
    	this.txtRisultato.clear();
    	String codice = this.txtCorso.getText();
    	if(!this.model.getCodiciCorsi().contains(codice)) {
    		this.txtRisultato.setText("Il codice del corso inserito non è presente nel database");
    		return;
    	}
    	Map<String,Integer> result = this.model.getDivisioneByCorso(codice);
    	for(String s: result.keySet()) {
    		this.txtRisultato.appendText(s+" "+result.get(s)+"\n");
    	}
    }

    @FXML
    void stampaStudenti(ActionEvent event) {

    	this.txtRisultato.clear();
    	String codice = this.txtCorso.getText();
    	if(!this.model.getCodiciCorsi().contains(codice)) {
    		this.txtRisultato.setText("Il codice del corso inserito non è presente nel database");
    		return;
    	}
    	
    	List<Studente> result = this.model.getStudentiByCorso(codice);
    	for(Studente s: result) {
    		this.txtRisultato.appendText(s.toString()+"\n");
    	}
    	
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtPeriodo != null : "fx:id=\"txtPeriodo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCorso != null : "fx:id=\"txtCorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCorsiPerPeriodo != null : "fx:id=\"btnCorsiPerPeriodo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnNumeroStudenti != null : "fx:id=\"btnNumeroStudenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnStudenti != null : "fx:id=\"btnStudenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnDivisioneStudenti != null : "fx:id=\"btnDivisioneStudenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
    
    
}