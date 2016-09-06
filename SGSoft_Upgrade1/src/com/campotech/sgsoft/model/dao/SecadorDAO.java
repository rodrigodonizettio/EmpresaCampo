package com.campotech.sgsoft.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.campotech.sgsoft.controller.AcessoDB;
import com.campotech.sgsoft.model.entity.Receita;
import com.campotech.sgsoft.model.entity.Secador;

public class SecadorDAO {

			//DESIGN PATTERN: SINGLETON
			//Refer�ncia: http://pt.wikipedia.org/wiki/Singleton
			//A IDEIA � TER APENAS UMA INST�NCIA PARA DAO, EVITANDO ACESSOS M�LTIPLOS � DB
			
			
			//ATRIBUTO EST�TICO QUE CONTER� A INST�NCIA DO SINGLETON.
			private static SecadorDAO INSTANCE = new SecadorDAO();
			
			
			//ATRIBUTOS PRIVADOS DE USO DO CONNECTOR POSTGRES
			private Connection connection;
		    private PreparedStatement preparedStatement;
		    private ResultSet resultSet;
			    
		    
		    //CONSTRUTOR PRIVADO. SUPRIME O CONSTRUTOR P�BLICO QUE VEM POR PADR�O [UTILIZADO PARA IMPLEMENTAR O SINGLETON]
		    private SecadorDAO() {
			
			}
		    
		    
		    public static SecadorDAO getInstance() {
		    	return INSTANCE;
		    }
		    
		    
	//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
		        
	      	/* 
	      	 * SELECTS - TIPO DE BUSCA: "ALL [TELA DE GERENCIAMENTO DE SECAGEM]"
	       	 */
		        
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		        
	      	/* 
	      	 * QUERIES CONSTANTES PARA MANIPULA��O DA DB
	      	 */    
		      
		  
		  private final String SELECT_SECADOR = "SELECT sid, ssaidacilindro, ssaidaventilador, ssaidaauxiliar FROM secador WHERE sid = ?";
		  private final String SELECT_RECEITA_ID = "SELECT receita_rid FROM secador WHERE sid = ?";
	      private final String SELECT_RECEITA_ATRELADA_SECADOR = "SELECT sid FROM secador WHERE receita_rid = ?";
		  
	          
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	    	/* 
	    	 * FUN��ES DE CHAMADA DAS QUERIES
	    	 */
		    
		  
		  //SELECT_SECADOR
	      public Secador selectSecador(int sid) {
	    	  Secador secador = null;
	      	
	    	  try {
	    		  connection = AcessoDB.conectar();
	    		  preparedStatement = connection.prepareStatement(SELECT_SECADOR);
	      		  preparedStatement.setInt(1, sid);
	      		  resultSet = preparedStatement.executeQuery();
	      		
	      		  while(resultSet.next()) {
	      			  
	      			  secador = new Secador();
	      			  secador.setsId(resultSet.getInt("sid"));
	      			  secador.setsSaidaCilindro(resultSet.getBoolean("ssaidacilindro"));
	      			  secador.setsSaidaVentilador(resultSet.getBoolean("ssaidaventilador"));
	      			  secador.setsSaidaAuxiliar(resultSet.getBoolean("ssaidaauxiliar"));
	      			  
	      		  }
	      	  } catch (Exception e) {
	      		  System.out.println("Erro: " + e.getMessage());
	      		  e.printStackTrace();
	      	  } finally {
	      		  System.out.println("Disconnected from PostgreSQL!!");
	      		  AcessoDB.desconectar(connection, preparedStatement, resultSet);
	      	  }
	      	  return secador;
	      }
		  
		         
		  //SELECT_RECEITA_ID
		  public int selectReceitaId(int sid) {
		      int receitaId = 0;
		      
		      try {
		    	  connection = AcessoDB.conectar();
		    	  preparedStatement = connection.prepareStatement(SELECT_RECEITA_ID);
		    	  preparedStatement.setInt(1, sid);
		      	  resultSet = preparedStatement.executeQuery();
		     
		      	  while(resultSet.next()) {
		      		  
		      		  receitaId = resultSet.getInt("receita_rid");
		      		  
		      		}
		      } catch (Exception e) {
		    	  
		      		System.out.println("Erro: " + e.getMessage());
		      		e.printStackTrace();
		      		
		      	} finally {
		      		
		      		System.out.println("Disconnected from PostgreSQL!!");
		      		AcessoDB.desconectar(connection, preparedStatement, resultSet);
		      		
		      	}
		      
		      	return receitaId;
		      	
		  }
		  
		  
		  //SELECT_RECEITA_ID
		  public int selectReceitaAtreladaSecador(int receita_rid) {
		      int sid = 0;
		      
		      try {
		    	  connection = AcessoDB.conectar();
		    	  preparedStatement = connection.prepareStatement(SELECT_RECEITA_ATRELADA_SECADOR);
		    	  preparedStatement.setInt(1, receita_rid);
		      	  resultSet = preparedStatement.executeQuery();
		     
		      	  while(resultSet.next()) {
		      		  
		      		  sid = resultSet.getInt("sid");
		      		  
		      		}
		      } catch (Exception e) {
		    	  
		      		System.out.println("Erro: " + e.getMessage());
		      		e.printStackTrace();
		      		
		      	} finally {
		      		
		      		System.out.println("Disconnected from PostgreSQL!!");
		      		AcessoDB.desconectar(connection, preparedStatement, resultSet);
		      		
		      	}
		      
		      	return sid;
		      	
		  }
		  
		  
		//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
		//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
		//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%    
		//************************************************************************************************************************
		//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
		//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
		//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%	
		  
		  
		  /* 
	  		 * UPDATES - TIPO DE ATUALIZA��O: "SECADOR"
	  		 */
	    
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	    
	  		/* 
	  		 * QUERIES CONSTANTES PARA MANIPULA��O DA DB
	  		 */    
	  
	  
	    private final String UPDATE_RECEITAID = "UPDATE secador SET receita_rid = ? WHERE sid = ?";
	    private final String UPDATE_SAIDASERECEITA = "UPDATE secador SET ssaidacilindro = ?, ssaidaventilador = ?, ssaidaauxiliar = ?, receita_rid = ? WHERE sid = ?";
	    private final String UPDATE_RECEITANULL = "UPDATE secador SET receita_rid = NULL WHERE sid = ?";
	  
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

			/* 
			 * FUN��ES DE CHAMADA DAS QUERIES
			 */    
	  
	  
	  
	    //UPDATE_RECEITAID
	    public void updateReceitaId(int receita_rid, int sid) {
	  	
	    	try {
	    		connection = AcessoDB.conectar();
	    		preparedStatement = connection.prepareStatement(UPDATE_RECEITAID);
	    		preparedStatement.setInt(1, receita_rid);
	    		preparedStatement.setInt(2, sid);
	    		preparedStatement.executeUpdate();
	  		
	    	} catch (Exception e) {
	    		System.out.println("Erro: " + e.getMessage());
	    		e.printStackTrace();
	    	} finally {
	    		System.out.println("Disconnected from PostgreSQL!!");
	    		AcessoDB.desconectar(connection, preparedStatement, resultSet);
	    	}
	    }
	    
	    
	    //UPDATE_SAIDAS
	    public void updateSaidasEReceita(boolean ssaidacilindro, boolean ssaidaventilador, boolean ssaidaauxiliar, int receita_rid, int sid) {
	  	
	    	try {
	    		connection = AcessoDB.conectar();
	    		preparedStatement = connection.prepareStatement(UPDATE_SAIDASERECEITA);
	    		preparedStatement.setBoolean(1, ssaidacilindro);
	    		preparedStatement.setBoolean(2, ssaidaventilador);
	    		preparedStatement.setBoolean(3, ssaidaauxiliar);
//	    		if(receita_rid == -99) preparedStatement.setInt(4, (Integer) null);
//	    		else preparedStatement.setInt(4, receita_rid);
	    		preparedStatement.setInt(4, receita_rid);
	    		preparedStatement.setInt(5, sid);
	    		preparedStatement.executeUpdate();
	  		
	    	} catch (Exception e) {
	    		System.out.println("Erro: " + e.getMessage());
	    		e.printStackTrace();
	    	} finally {
	    		System.out.println("AOOO FERRO!!");
	    		System.out.println("Disconnected from PostgreSQL!!");
	    		AcessoDB.desconectar(connection, preparedStatement, resultSet);
	    	}
	    }
	    
	    
	    //UPDATE_RECEITANULL
	    public void updateReceitaNull(int sid) {
	  	
	    	try {
	    		connection = AcessoDB.conectar();
	    		preparedStatement = connection.prepareStatement(UPDATE_RECEITANULL);
	    		preparedStatement.setInt(1, sid);
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
	//************************************************************************************************************************
	//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	
	
}
