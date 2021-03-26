package it.polito.tdp.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.corsi.model.Corso;

public class CorsoDAO {

	public List<Corso> corsiPerPeriodo(Integer periodo){
		String sql = "select * "
				+ "from corso "
				+ "where pd = ?";
		List<Corso> result = new ArrayList<Corso>();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, periodo);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Corso c = new Corso(rs.getString("codins"), rs.getInt("crediti"), 
						rs.getString("nome"), rs.getInt("pd"));
				result.add(c);
			}
			
			rs.close();
			st.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return result;
		
	}
	
	public Map<Corso,Integer> iscrittiPerCorsoPerPeriodo(Integer periodo){
		String sql = "SELECT c.codins, c.nome, c.crediti, c.pd, COUNT(*) AS tot "
				+"FROM corso AS c, iscrizione AS i "
				+"WHERE c.codins = i.codins AND c.pd = ? "
				+"GROUP BY c.codins, c.nome, c.crediti, c.pd";
		Map<Corso,Integer> result = new HashMap<Corso,Integer>();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, periodo);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Corso c = new Corso(rs.getString("codins"), rs.getInt("crediti"), 
						rs.getString("nome"), rs.getInt("pd"));
				Integer i = rs.getInt("tot");
				result.put(c, i);
			}
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		return result;
	}
	
	public List<String> getCodiciCorsi(){
		List<String> result = new ArrayList<String>();
		String sql = "SELECT distinct corso.codins "
				+"from corso ";
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				String s = rs.getString("codins");
				result.add(s);
			}
		}catch(SQLException e) {
			throw new RuntimeException();
		}
		
		return result;
	}
	
}
