package com.campotech.sgsoft.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.campotech.sgsoft.controller.AcessoDB;
import com.campotech.sgsoft.model.entity.Receita;
import com.campotech.sgsoft.model.entity.Usuario;

public class ReceitaDAO {

		//DESIGN PATTERN: SINGLETON
		//Referência: http://pt.wikipedia.org/wiki/Singleton
		//A IDEIA É TER APENAS UMA INSTÂNCIA PARA DAO, EVITANDO ACESSOS MÚLTIPLOS À DB
		
		
		//ATRIBUTO ESTÁTICO QUE CONTERÁ A INSTÂNCIA DO SINGLETON.
		private static ReceitaDAO INSTANCE = new ReceitaDAO();
		
		
		//ATRIBUTOS PRIVADOS DE USO DO CONNECTOR POSTGRES
		private Connection connection;
	    private PreparedStatement preparedStatement;
	    private ResultSet resultSet;
		    
	    
	    //CONSTRUTOR PRIVADO. SUPRIME O CONSTRUTOR PÚBLICO QUE VEM POR PADRÃO [UTILIZADO PARA IMPLEMENTAR O SINGLETON]
	    private ReceitaDAO() {
		
		}
	    
	    
	    public static ReceitaDAO getInstance() {
	    	return INSTANCE;
	    }
	    
	    
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	        
      	/* 
      	 * SELECTS - TIPO DE BUSCA: "ALL [TELA DE GERENCIAMENTO DE RECEITAS]"
       	 */
	        
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	        
      	/* 
      	 * QUERIES CONSTANTES PARA MANIPULAÇÃO DA DB
      	 */    
	      
	  
	  private final String SELECT_RECEITA = "SELECT rid, rnome, rnumeroetapas, rtemperaturamaximaentrada1, rtemperaturamaximaentrada2, rtemperaturamaximaentrada3, rtemperaturamaximamassa1, rtemperaturamaximamassa2, rtemperaturamaximamassa3, rtempoetapa1, rtempoetapa2, rtempoetapa3, rtipocontroleetapa1, rtipocontroleetapa2, rtipocontroleetapa3 FROM receita WHERE rnome LIKE ?";
      private final String SELECT_ALL_NOME = "SELECT rnome FROM receita ORDER BY rnome ASC";
      private final String SELECT_NOME = "SELECT rnome FROM receita WHERE rnome LIKE ?";
      private final String SELECT_LASTRECEITANOME_BYSECADORID = "SELECT rnome FROM receita WHERE secador_sid2 = ? ORDER BY rid ASC";
      private final String SELECT_RECEITANOME_BYID = "SELECT rnome FROM receita WHERE rid = ?";
      private final String SELECT_RECEITAID = "SELECT rid FROM receita WHERE rnome LIKE ?";
	  private final String SELECT_RECEITABYID = "SELECT rid, rnome, rnumeroetapas, rtemperaturamaximaentrada1, rtemperaturamaximaentrada2, rtemperaturamaximaentrada3, rtemperaturamaximamassa1, rtemperaturamaximamassa2, rtemperaturamaximamassa3, rtempoetapa1, rtempoetapa2, rtempoetapa3, rtipocontroleetapa1, rtipocontroleetapa2, rtipocontroleetapa3 FROM receita WHERE rid = ?";
	  ///
	  private final String SELECT_RECEITAPRIMEIROID = "SELECT rid FROM receita WHERE rid > 0";
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    	/* 
    	 * FUNÇÕES DE CHAMADA DAS QUERIES
    	 */
	      
	  
	//SELECT_RECEITA
      public int selectReceitaPrimeiroId() {
    	  
    	  Receita receita = null;
      	  int receitaId = -1;
    	  
    	  try {
    		  connection = AcessoDB.conectar();
    		  preparedStatement = connection.prepareStatement(SELECT_RECEITAPRIMEIROID);
      		  resultSet = preparedStatement.executeQuery();
      		
      		  if(resultSet.next()) {
      			  
      			  receita = new Receita();
      			  receita.setrId(resultSet.getInt("rid"));
      			  receitaId = receita.getrId();
      			  
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
	  
	  
   	  //SELECT_RECEITA
      public Receita selectReceita(String rNome) {
    	  Receita receita = null;
      	
    	  try {
    		  connection = AcessoDB.conectar();
    		  preparedStatement = connection.prepareStatement(SELECT_RECEITA);
      		  preparedStatement.setString(1, rNome);
      		  resultSet = preparedStatement.executeQuery();
      		
      		  while(resultSet.next()) {
      			  
      			  receita = new Receita();
      			  receita.setrId(resultSet.getInt("rid"));
      			  receita.setrNome(resultSet.getString("rnome"));
      			  receita.setrNumeroEtapas(resultSet.getInt("rnumeroetapas"));
      			  receita.setrTemperaturaMaximaEntrada1(resultSet.getInt("rtemperaturamaximaentrada1"));
      			  receita.setrTemperaturaMaximaEntrada2(resultSet.getInt("rtemperaturamaximaentrada2"));
      			  receita.setrTemperaturaMaximaEntrada3(resultSet.getInt("rtemperaturamaximaentrada3"));
      			  receita.setrTemperaturaMaximaMassa1(resultSet.getInt("rtemperaturamaximamassa1"));
      			  receita.setrTemperaturaMaximaMassa2(resultSet.getInt("rtemperaturamaximamassa2"));
      			  receita.setrTemperaturaMaximaMassa3(resultSet.getInt("rtemperaturamaximamassa3"));
      			  receita.setrTempoEtapa1(resultSet.getInt("rtempoetapa1"));
      			  receita.setrTempoEtapa2(resultSet.getInt("rtempoetapa2"));
      			  receita.setrTempoEtapa3(resultSet.getInt("rtempoetapa3"));
      			  receita.setrTipoControleEtapa1(resultSet.getBoolean("rtipocontroleetapa1"));
      			  receita.setrTipoControleEtapa2(resultSet.getBoolean("rtipocontroleetapa2"));
      			  receita.setrTipoControleEtapa3(resultSet.getBoolean("rtipocontroleetapa3"));
      			  
      		  }
      	  } catch (Exception e) {
      		  System.out.println("Erro: " + e.getMessage());
      		  e.printStackTrace();
      	  } finally {
      		  System.out.println("Disconnected from PostgreSQL!!");
      		  AcessoDB.desconectar(connection, preparedStatement, resultSet);
      	  }
      	  return receita;
      }
	  
	  
      //SELECT_RECEITABYID
      public Receita selectReceitaById(int rid) {
    	  Receita receita = null;
      	
    	  try {
    		  connection = AcessoDB.conectar();
    		  preparedStatement = connection.prepareStatement(SELECT_RECEITABYID);
      		  preparedStatement.setInt(1, rid);
      		  resultSet = preparedStatement.executeQuery();
      		
      		  while(resultSet.next()) {
      			  
      			  receita = new Receita();
      			  receita.setrId(resultSet.getInt("rid"));
      			  receita.setrNome(resultSet.getString("rnome"));
      			  receita.setrNumeroEtapas(resultSet.getInt("rnumeroetapas"));
      			  receita.setrTemperaturaMaximaEntrada1(resultSet.getInt("rtemperaturamaximaentrada1"));
      			  receita.setrTemperaturaMaximaEntrada2(resultSet.getInt("rtemperaturamaximaentrada2"));
      			  receita.setrTemperaturaMaximaEntrada3(resultSet.getInt("rtemperaturamaximaentrada3"));
      			  receita.setrTemperaturaMaximaMassa1(resultSet.getInt("rtemperaturamaximamassa1"));
      			  receita.setrTemperaturaMaximaMassa2(resultSet.getInt("rtemperaturamaximamassa2"));
      			  receita.setrTemperaturaMaximaMassa3(resultSet.getInt("rtemperaturamaximamassa3"));
      			  receita.setrTempoEtapa1(resultSet.getInt("rtempoetapa1"));
      			  receita.setrTempoEtapa2(resultSet.getInt("rtempoetapa2"));
      			  receita.setrTempoEtapa3(resultSet.getInt("rtempoetapa3"));
      			  receita.setrTipoControleEtapa1(resultSet.getBoolean("rtipocontroleetapa1"));
      			  receita.setrTipoControleEtapa2(resultSet.getBoolean("rtipocontroleetapa2"));
      			  receita.setrTipoControleEtapa3(resultSet.getBoolean("rtipocontroleetapa3"));
      			  
      		  }
      	  } catch (Exception e) {
      		  System.out.println("Erro: " + e.getMessage());
      		  e.printStackTrace();
      	  } finally {
      		  System.out.println("Disconnected from PostgreSQL!!");
      		  AcessoDB.desconectar(connection, preparedStatement, resultSet);
      	  }
      	  return receita;
      }
      
      
	  //SELECT_ALL_NOME
	  public ArrayList<String> selectAllNome() {
		  ArrayList<String> receitasNome = new ArrayList<String>();
	      String receitaNome = null;
	      
	      try {
	    	  connection = AcessoDB.conectar();
	    	  preparedStatement = connection.prepareStatement(SELECT_ALL_NOME);
	      	  resultSet = preparedStatement.executeQuery();
	     
	      	  while(resultSet.next()) {
	      		  
	      		  receitaNome = resultSet.getString("rnome");
	      		  receitasNome.add(receitaNome);
	      		  
	      		}
	      } catch (Exception e) {
	    	  
	      		System.out.println("Erro: " + e.getMessage());
	      		e.printStackTrace();
	      		
	      	} finally {
	      		
	      		System.out.println("Disconnected from PostgreSQL!!");
	      		AcessoDB.desconectar(connection, preparedStatement, resultSet);
	      		
	      	}
	      
	      	System.out.println(receitasNome);
	      	return receitasNome;
	      	
	  }
	  
	  
	  //SELECT_NOME
	  public String selectNome(String rNome) {
	      String receitaNome = null;
	      
	      try {
	    	  connection = AcessoDB.conectar();
	    	  preparedStatement = connection.prepareStatement(SELECT_NOME);
	    	  preparedStatement.setString(1, rNome);
	      	  resultSet = preparedStatement.executeQuery();
	     
	      	  while(resultSet.next()) {
	      		  
	      		  receitaNome = resultSet.getString("rnome");
	      		  
	      		}
	      } catch (Exception e) {
	    	  
	      		System.out.println("Erro: " + e.getMessage());
	      		e.printStackTrace();
	      		
	      	} finally {
	      		
	      		System.out.println("Disconnected from PostgreSQL!!");
	      		AcessoDB.desconectar(connection, preparedStatement, resultSet);
	      		
	      	}
	      
	      	return receitaNome;
	      	
	  }
	   
	     
	  //SELECT_LASTRECEITANOME_BYSECADORID
	  public String selectLastReceitaNomeBySecadorId(int secadorId) {
	      
		  String receitaNome = null;
	      
	      try {
	    	  connection = AcessoDB.conectar();
	    	  preparedStatement = connection.prepareStatement(SELECT_LASTRECEITANOME_BYSECADORID);
	    	  preparedStatement.setInt(1, secadorId);
	      	  resultSet = preparedStatement.executeQuery();
	     
	      	  while(resultSet.next()) {
	      		  
	      		  receitaNome = resultSet.getString("rnome");
	      		  
	      		}
	      } catch (Exception e) {
	    	  
	      		System.out.println("Erro: " + e.getMessage());
	      		e.printStackTrace();
	      		
	      	} finally {
	      		
	      		System.out.println("Disconnected from PostgreSQL!!");
	      		AcessoDB.desconectar(connection, preparedStatement, resultSet);
	      		
	      	}
	      
	      	return receitaNome;
	      	
	  }
	  
	  
	  //SELECT_RECEITANOME_BYID
	  public String selectReceitaNomeById(int rid) {
	      String receitaNome = null;
	      
	      try {
	    	  connection = AcessoDB.conectar();
	    	  preparedStatement = connection.prepareStatement(SELECT_RECEITANOME_BYID);
	    	  preparedStatement.setInt(1, rid);
	      	  resultSet = preparedStatement.executeQuery();
	     
	      	  while(resultSet.next()) {
	      		  
	      		  receitaNome = resultSet.getString("rnome");
	      		  
	      		}
	      } catch (Exception e) {
	    	  
	      		System.out.println("Erro: " + e.getMessage());
	      		e.printStackTrace();
	      		
	      	} finally {
	      		
	      		System.out.println("Disconnected from PostgreSQL!!");
	      		AcessoDB.desconectar(connection, preparedStatement, resultSet);
	      		
	      	}
	      
	      	return receitaNome;
	      	
	  }
	  
	  
	  //SELECT_RECEITANOME_BYID
	  public int selectReceitaId(String rnome) {
	      int receitaId = 0;
	      
	      try {
	    	  connection = AcessoDB.conectar();
	    	  preparedStatement = connection.prepareStatement(SELECT_RECEITAID);
	    	  preparedStatement.setString(1, rnome);
	      	  resultSet = preparedStatement.executeQuery();
	     
	      	  while(resultSet.next()) {
	      		  
	      		  receitaId = resultSet.getInt("rid");
	      		  
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
	  
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%    
//************************************************************************************************************************
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%	    
	
	  
	  	/* 
    	 * INSERTS - TIPO DE INSERÇÃO: "RECEITA"
     	 */
	        
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	        
    	/* 
    	 * QUERIES CONSTANTES PARA MANIPULAÇÃO DA DB
    	 */    
	      
	      
    private final String INSERT = "INSERT INTO receita(rnome, rnumeroetapas, rtemperaturamaximaentrada1, rtemperaturamaximaentrada2, rtemperaturamaximaentrada3, rtemperaturamaximamassa1, rtemperaturamaximamassa2, rtemperaturamaximamassa3, rtempoetapa1, rtempoetapa2, rtempoetapa3, rtipocontroleetapa1, rtipocontroleetapa2, rtipocontroleetapa3) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	      
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	  	/* 
	  	 * FUNÇÕES DE CHAMADA DAS QUERIES
	  	 */
	      
	         
    //INSERT
    public void insert(String rNome, int rNumeroEtapas, int rTemperaturaMaximaEntrada1, int rTemperaturaMaximaEntrada2, int rTemperaturaMaximaEntrada3, int rTemperaturaMaximaMassa1, int rTemperaturaMaximaMassa2, int rTemperaturaMaximaMassa3, int rTempoEtapa1, int rTempoEtapa2, int rTempoEtapa3, boolean rTipoControleEtapa1, boolean rTipoControleEtapa2, boolean rTipoControleEtapa3) {
    	
    	try {
    		connection = AcessoDB.conectar();
    		preparedStatement = connection.prepareStatement(INSERT);
    		preparedStatement.setString(1, rNome);
    		preparedStatement.setInt(2, rNumeroEtapas);
    		preparedStatement.setInt(3, rTemperaturaMaximaEntrada1);
    		preparedStatement.setInt(4, rTemperaturaMaximaEntrada2);
    		preparedStatement.setInt(5, rTemperaturaMaximaEntrada3);
    		preparedStatement.setInt(6, rTemperaturaMaximaMassa1);
    		preparedStatement.setInt(7, rTemperaturaMaximaMassa2);
    		preparedStatement.setInt(8, rTemperaturaMaximaMassa3);
    		preparedStatement.setInt(9, rTempoEtapa1);
    		preparedStatement.setInt(10, rTempoEtapa2);
    		preparedStatement.setInt(11, rTempoEtapa3);
    		preparedStatement.setBoolean(12, rTipoControleEtapa1);
    		preparedStatement.setBoolean(13, rTipoControleEtapa2);
    		preparedStatement.setBoolean(14, rTipoControleEtapa3);
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
	   
	      
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%    
//************************************************************************************************************************
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%	  
    
  		/* 
  		 * UPDATES - TIPO DE ATUALIZAÇÃO: "RECEITA"
  		 */
    
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
  		/* 
  		 * QUERIES CONSTANTES PARA MANIPULAÇÃO DA DB
  		 */    
  
  
    private final String UPDATE = "UPDATE receita SET rnumeroetapas = ?, rtemperaturamaximaentrada1 = ?, rtemperaturamaximaentrada2 = ?, rtemperaturamaximaentrada3 = ?, rtemperaturamaximamassa1 = ?, rtemperaturamaximamassa2 = ?, rtemperaturamaximamassa3 = ?, rtempoetapa1 = ?, rtempoetapa2 = ?, rtempoetapa3 = ?, rtipocontroleetapa1 = ?, rtipocontroleetapa2 = ?, rtipocontroleetapa3 = ? WHERE rnome LIKE ?";
    private final String UPDATE_SECADORID = "UPDATE receita SET secador_sid2 = ? WHERE rnome LIKE ?";
    
  
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		/* 
		 * FUNÇÕES DE CHAMADA DAS QUERIES
		 */    
  
  
	//UPDATE
    public void update(int rNumeroEtapas, int rTemperaturaMaximaEntrada1, int rTemperaturaMaximaEntrada2, int rTemperaturaMaximaEntrada3, int rTemperaturaMaximaMassa1, int rTemperaturaMaximaMassa2, int rTemperaturaMaximaMassa3, int rTempoEtapa1, int rTempoEtapa2, int rTempoEtapa3, boolean rTipoControleEtapa1, boolean rTipoControleEtapa2, boolean rTipoControleEtapa3, String rNome) {
  	
    	try {
    		connection = AcessoDB.conectar();
    		preparedStatement = connection.prepareStatement(UPDATE);
    		preparedStatement.setInt(1, rNumeroEtapas);
    		preparedStatement.setInt(2, rTemperaturaMaximaEntrada1);
    		preparedStatement.setInt(3, rTemperaturaMaximaEntrada2);
    		preparedStatement.setInt(4, rTemperaturaMaximaEntrada3);
    		preparedStatement.setInt(5, rTemperaturaMaximaMassa1);
    		preparedStatement.setInt(6, rTemperaturaMaximaMassa2);
    		preparedStatement.setInt(7, rTemperaturaMaximaMassa3);
    		preparedStatement.setInt(8, rTempoEtapa1);
    		preparedStatement.setInt(9, rTempoEtapa2);
    		preparedStatement.setInt(10, rTempoEtapa3);
    		preparedStatement.setBoolean(11, rTipoControleEtapa1);
    		preparedStatement.setBoolean(12, rTipoControleEtapa2);
    		preparedStatement.setBoolean(13, rTipoControleEtapa3);
    		preparedStatement.setString(14, rNome);
    		preparedStatement.executeUpdate();
  		
  		
    	} catch (Exception e) {
    		System.out.println("Erro: " + e.getMessage());
    		e.printStackTrace();
    	} finally {
    		System.out.println("Disconnected from PostgreSQL!!");
    		AcessoDB.desconectar(connection, preparedStatement, resultSet);
    	}
    }   
  
  
    //UPDATE_SECADORID
    public void updateSecadorId(int secador_sid2, String rNome) {
  	
    	try {
    		connection = AcessoDB.conectar();
    		preparedStatement = connection.prepareStatement(UPDATE_SECADORID);
    		preparedStatement.setInt(1, secador_sid2);
    		preparedStatement.setString(2, rNome);
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
    
    	/* 
    	 * DELETES - TIPO DE EXCLUSÃO: "RECEITA"
    	 */
    
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    	/* 
    	 * QUERIES CONSTANTES PARA MANIPULAÇÃO DA DB
    	 */    
  
  
    private final String DELETE = "DELETE FROM receita WHERE rnome LIKE ?";
  
  
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		/* 
		 * FUNÇÕES DE CHAMADA DAS QUERIES
		 */    
  
  
    //DELETE
    public void delete(String rNome) {
  	
		try {
			connection = AcessoDB.conectar();
			preparedStatement = connection.prepareStatement(DELETE);
			preparedStatement.setString(1, rNome);
			preparedStatement.executeUpdate();
	  		
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
			e.printStackTrace();
		} finally {
			System.out.println("Disconnected from PostgreSQL!!");
			AcessoDB.desconectar(connection, preparedStatement, resultSet);
		}
    }      
    
}