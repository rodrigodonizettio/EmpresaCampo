package com.campotech.sgsoft.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.TableModel;

import net.proteanit.sql.DbUtils;

import com.campotech.sgsoft.controller.AcessoDB;
import com.campotech.sgsoft.model.entity.Amostra;
import com.campotech.sgsoft.model.entity.Usuario;
import com.campotech.sgsoft.view.GerenciamentoRelatoriosFrame;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

public class SpEUnDAO {

	//DESIGN PATTERN: SINGLETON
	//Referência: http://pt.wikipedia.org/wiki/Singleton
	//A IDEIA É TER APENAS UMA INSTÂNCIA PARA DAO, EVITANDO ACESSOS MÚLTIPLOS À DB 
	
	
	//ATRIBUTO ESTÁTICO QUE CONTERÁ A INSTÂNCIA DO SINGLETON.
	private static SpEUnDAO INSTANCE = new SpEUnDAO();
	
	
	//ATRIBUTOS PRIVADOS DE USO DO CONNECTOR POSTGRES
	private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    
    
    //CONSTRUTOR PRIVADO. SUPRIME O CONSTRUTOR PÚBLICO QUE VEM POR PADRÃO [UTILIZADO PARA IMPLEMENTAR O SINGLETON]
    private SpEUnDAO() {
    	
    }
    
    
    public static SpEUnDAO getInstance() {
    	return INSTANCE;
    }
    
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%	    
			
			  
		  	/* 
	    	 * INSERTS - TIPO DE INSERÇÃO: "SP E NOME-USUÁRIO"
	     	 */
			        
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			        
	    	/* 
	    	 * QUERIES CONSTANTES PARA MANIPULAÇÃO DA DB
	    	 */    
			      
      	//private final String INSERT = "INSERT INTO speun(speunsecadorid, speundatahora, speunusuarionome, speunsetpoint) VALUES (?, ?, ?, ?, ?, to_date(?, 'YYYY-MM-dd'), to_timestamp(?, 'hh24:mi:ss'), ?, ?, ?, ?, ?)";
    	private final String INSERT = "INSERT INTO speun(speunsecadorid, speundatahora, speunusuarionome, speunsetpoint) VALUES (?, ?::timestamp, ?, ?)";
     	private final String INSERT_SPDATA_RECEITA = "INSERT INTO speun(speunsecadorid, speundatahora, speunusuarionome, speunsetpoint) VALUES (?, ?::TIMESTAMP + ?::INTERVAL, ?, ?)";
//    	private final String INSERT_SPDATA_RECEITA = "INSERT INTO speun(speunsecadorid, speundatahora, speunusuarionome, speunsetpoint) VALUES (?, ?::timestamp, ?, ?)";
      	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////      
  
	    
	    //INSERT
	    public void insert(int speunSecadorId, String speunDataHora, String speunUsuarioNome, int speunSetPoint) {
		    	
	    	try {
	    		
	    		connection = AcessoDB.conectar();
	    		preparedStatement = connection.prepareStatement(INSERT);
	    		preparedStatement.setInt(1, speunSecadorId);
	    		preparedStatement.setString(2, speunDataHora);
	    		preparedStatement.setString(3, speunUsuarioNome);
	    		preparedStatement.setInt(4, speunSetPoint);
		    	preparedStatement.executeUpdate();
		    	System.out.println(preparedStatement);
		    		
		   	} catch (Exception e) {
		   		System.out.println("Erro: " + e.getMessage());
		   		e.printStackTrace();
		   	} finally {
		   		System.out.println("Disconnected from PostgreSQL!!");
		   		AcessoDB.desconectar(connection, preparedStatement, resultSet);
		   	}
	    }
	
	    
	    //INSERT_SPDATA_RECEITA
	    public void insertSpDataReceita(int speunSecadorId, String speunDataHora, String speunInterval, String speunUsuarioNome, int speunSetPoint) {
		    	
	    	try {
	    		
	    		connection = AcessoDB.conectar();
	    		preparedStatement = connection.prepareStatement(INSERT_SPDATA_RECEITA);
	    		preparedStatement.setInt(1, speunSecadorId);
	    		preparedStatement.setString(2, speunDataHora);
	    		preparedStatement.setString(3, speunInterval);
	    		preparedStatement.setString(4, speunUsuarioNome);
	    		preparedStatement.setInt(5, speunSetPoint);
	    		System.out.println(preparedStatement);
		    	preparedStatement.executeUpdate();
		    		
		   	} catch (Exception e) {
		   		System.out.println("Erro: " + e.getMessage());
		   		e.printStackTrace();
		   	} finally {
		   		System.out.println("Disconnected from PostgreSQL!!");
		   		AcessoDB.desconectar(connection, preparedStatement, resultSet);
		   	}
	    }
	    
	    
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
//------------------------------------------------------------------------------------------------------------------------
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	    
	    
	    /* 
    	 * SELECTS - TIPO DE SELEÇÃO: "SP E NOME-USUÁRIO"
     	 */
		        
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		        
    	/* 
    	 * QUERIES CONSTANTES PARA MANIPULAÇÃO DA DB
    	 */    
		      
	private final String SELECT_SETPOINT_USUARIONOME = "SELECT speunsecadorid, speundatahora, speunusuarionome, speunsetpoint FROM speun WHERE speunsecadorid = ? AND speundatahora < ?::timestamp ORDER BY speundatahora DESC";
  	
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  


    //SELECT_SETPOINT
	public int selectSetPoint(int speunSecadorID, String speunDataHora) {
    	
    	int setPoint = -1;
    	
    	try {
    		connection = AcessoDB.conectar();
    		preparedStatement = connection.prepareStatement(SELECT_SETPOINT_USUARIONOME);
    		preparedStatement.setInt(1, speunSecadorID);
    		preparedStatement.setString(2, speunDataHora);
    		resultSet = preparedStatement.executeQuery();
    		
    		if(resultSet.next()) setPoint = resultSet.getInt("speunsetpoint");
    		
//    		while(resultSet.next()) {
//    			setPoint = resultSet.getBoolean("univel");
//    		}
    		
    	} catch (Exception e) {
    		System.out.println("Erro: " + e.getMessage());
    		e.printStackTrace();
    	} finally {
    		System.out.println("Disconnected from PostgreSQL!!");
    		AcessoDB.desconectar(connection, preparedStatement, resultSet);
    	}
    	return setPoint;
    }
	    
	
	//SELECT_USUARIONOME
	public String selectUsuarioNome(int speunSecadorID, String speunDataHora) {
    	
    	String usuarioNome = new String();
    	
    	try {
    		connection = AcessoDB.conectar();
    		preparedStatement = connection.prepareStatement(SELECT_SETPOINT_USUARIONOME);
    		preparedStatement.setInt(1, speunSecadorID);
    		preparedStatement.setString(2, speunDataHora);
    		resultSet = preparedStatement.executeQuery();
    		
    		if(resultSet.next()) usuarioNome = resultSet.getString("speunusuarionome");
    		
//	    		while(resultSet.next()) {
//	    			setPoint = resultSet.getBoolean("univel");
//	    		}
    		
    	} catch (Exception e) {
    		System.out.println("Erro: " + e.getMessage());
    		e.printStackTrace();
    	} finally {
    		System.out.println("Disconnected from PostgreSQL!!");
    		AcessoDB.desconectar(connection, preparedStatement, resultSet);
    	}
    	return usuarioNome;
    }
	
}
