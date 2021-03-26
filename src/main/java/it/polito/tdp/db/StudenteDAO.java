package it.polito.tdp.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.corsi.model.Studente;

public class StudenteDAO {

	public List<Studente> getStudentiByCorso(String codice){
		String sql = "SELECT s.matricola, s.cognome, s.nome, s.CDS "
				+"FROM studente AS s, iscrizione AS i "
				+"WHERE s.matricola = i.matricola AND i.codins = '"+codice+"' "
				+"GROUP BY s.matricola, s.cognome, s.nome, s.CDS "
				+"ORDER BY s.cognome";
		List<Studente> result = new ArrayList<Studente>(); 
		try {
		Connection conn = DBConnect.getConnection();
		PreparedStatement st = conn.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		
		while(rs.next()) {
			Studente s = new Studente(rs.getInt("matricola"),rs.getString("cognome"),rs.getString("nome"),rs.getString("CDS"));
			result.add(s);
		}
		rs.close();
		st.close();
		conn.close();
		}catch(SQLException e) {
			throw new RuntimeException("Database error");
		}
		return result;
	}
	
	public Map<String,Integer> getDivisioneByCorso(String codice){
		String sql = "SELECT COUNT(*) AS tot, studente.CDS "
				+"FROM studente, iscrizione, corso "
				+"WHERE studente.matricola = iscrizione.matricola AND iscrizione.codins = corso.codins AND corso.codins = '"+codice+"' "
				+"GROUP BY studente.CDS";
		Map<String,Integer> result = new HashMap<String,Integer>();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				String s = rs.getString("CDS");
				Integer i = rs.getInt("tot");
				result.put(s, i);
			}
		}catch(SQLException e) {
			throw new RuntimeException("Database error");
		}
		return result;
		
	}
	
}
