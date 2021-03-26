package it.polito.tdp.corsi.model;

import java.util.List;
import java.util.Map;

import it.polito.tdp.db.CorsoDAO;
import it.polito.tdp.db.StudenteDAO;

public class Model {
	
	private CorsoDAO corsoDAO;
	private StudenteDAO studenteDAO;
	
	public Model() {
		this.corsoDAO = new CorsoDAO();
		this.studenteDAO = new StudenteDAO();
	}
	
	public List<Corso> corsiPerPeriodo(Integer periodo){
		return this.corsoDAO.corsiPerPeriodo(periodo);
	}
	
	public Map<Corso,Integer> iscrittiPerCorsoPerPeriodo(Integer periodo){
		return this.corsoDAO.iscrittiPerCorsoPerPeriodo(periodo);
	}
	
	public List<Studente> getStudentiByCorso(String codice){
		return this.studenteDAO.getStudentiByCorso(codice);
	}
	
	public List<String> getCodiciCorsi(){
		return this.corsoDAO.getCodiciCorsi();
	}
	
	public Map<String,Integer> getDivisioneByCorso(String codice){
		return this.studenteDAO.getDivisioneByCorso(codice);
	}
}
