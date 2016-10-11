package com.campotech.sgsoft.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.campotech.sgsoft.controller.AcessoDB;
import com.campotech.sgsoft.model.entity.Lote;
import com.campotech.sgsoft.model.entity.Receita;

public class LoteDAO {


	//DESIGN PATTERN: SINGLETON
	//Referência: http://pt.wikipedia.org/wiki/Singleton
	//A IDEIA É TER APENAS UMA INSTÂNCIA PARA DAO, EVITANDO ACESSOS MÚLTIPLOS À DB
	
	
	//ATRIBUTO ESTÁTICO QUE CONTERÁ A INSTÂNCIA DO SINGLETON.
	private static LoteDAO INSTANCE = new LoteDAO();
	
	
	//ATRIBUTOS PRIVADOS DE USO DO CONNECTOR POSTGRES
	private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
	    
    
    //CONSTRUTOR PRIVADO. SUPRIME O CONSTRUTOR PÚBLICO QUE VEM POR PADRÃO [UTILIZADO PARA IMPLEMENTAR O SINGLETON]
    private LoteDAO() {
	
	}
    
    
    public static LoteDAO getInstance() {
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
  
    
    private final String SELECT_LOTE = "SELECT lid, lnome, lnomeorigem, lnomedestino, ldatainicio, lhorainicio, ltempoterreiro, lestado, letapa, ltemposecagem, ltempodescanso, secador_sid1, receita_rid, lhorainiciodescanso FROM lote WHERE lnome LIKE ?";
    private final String SELECT_ALL_NOME = "SELECT lnome FROM lote ORDER BY lnome ASC";
    private final String SELECT_LASTLOTENOME_BYSECADORID = "SELECT lnome FROM lote WHERE secador_sid1 = ? ORDER BY lid ASC";
    private final String SELECT_ALL_NOME_DESCANSANDO = "SELECT lnome FROM lote WHERE lestado = 2 ORDER BY lnome ASC"; //lestado = 2 => descansando
    private final String SELECT_LOTE_NOME = "SELECT lnome FROM lote WHERE lnome LIKE ?";
    private final String SELECT_LOTE_RECEITAID = "SELECT receita_rid FROM lote WHERE lnome LIKE ?";
    private final String SELECT_RECEITAID = "SELECT receita_rid FROM lote WHERE secador_sid1 = ?";
    
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		/* 
		 * FUNÇÕES DE CHAMADA DAS QUERIES
		 */
    
    
    	//SELECT_LOTE
    	public Lote selectLote(String lNome) {
    		
    		Lote lote = null;
    	
    		try {
    			connection = AcessoDB.conectar();
    			preparedStatement = connection.prepareStatement(SELECT_LOTE);
    			preparedStatement.setString(1, lNome);
    			resultSet = preparedStatement.executeQuery();
    		
    			while(resultSet.next()) {
    			  
    				lote = new Lote();
    				lote.setlId(resultSet.getInt("lid"));
    				lote.setlNome(resultSet.getString("lnome"));
    				lote.setlNomeOrigem(resultSet.getString("lnomeorigem"));
    				lote.setlNomeDestino(resultSet.getString("lnomedestino"));
    				lote.setlDataInicio(resultSet.getDate("ldatainicio"));
    				lote.setlHoraInicio(resultSet.getTime("lhorainicio"));
    				lote.setlTempoTerreiro(resultSet.getInt("ltempoterreiro"));
    				lote.setlEstado(resultSet.getInt("lestado"));
    				lote.setlEtapa(resultSet.getInt("letapa"));
    				lote.setlTempoSecagem(resultSet.getString("ltemposecagem"));
    				lote.setlTempoDescanso(resultSet.getString("ltempodescanso"));
    				lote.setSecador_sId(resultSet.getInt("secador_sid1"));
    				lote.setReceita_rId(resultSet.getInt("receita_rid"));
    				lote.setlHoraInicioDescanso(resultSet.getTimestamp("lhorainiciodescanso"));
    				
    		  }
    	  } catch (Exception e) {
    		  System.out.println("Erro: " + e.getMessage());
    		  e.printStackTrace();
    	  } finally {
    		  System.out.println("Disconnected from PostgreSQL!!");
    		  AcessoDB.desconectar(connection, preparedStatement, resultSet);
    	  }
    	  return lote;
    }
    
	    
	      //SELECT_ALL_NOME
		  public ArrayList<String> selectAllNome() {
			  ArrayList<String> lotesNome = new ArrayList<String>();
		      String loteNome = null;
		      
		      try {
		    	  connection = AcessoDB.conectar();
		    	  preparedStatement = connection.prepareStatement(SELECT_ALL_NOME);
		      	  resultSet = preparedStatement.executeQuery();
		     
		      	  while(resultSet.next()) {
		      		  
		      		  loteNome = resultSet.getString("lnome");
		      		  lotesNome.add(loteNome);
		      		  
		      		}
		      } catch (Exception e) {
		    	  
		      		System.out.println("Erro: " + e.getMessage());
		      		e.printStackTrace();
		      		
		      	} finally {
		      		
		      		System.out.println("Disconnected from PostgreSQL!!");
		      		AcessoDB.desconectar(connection, preparedStatement, resultSet);
		      		
		      	}
		      
		      	System.out.println(lotesNome);
		      	return lotesNome;
		      	
		  }   
		  
		  
		  //SELECT_LASTLOTENOME_BYSECADORID
		  public String selectLastLoteNomeBySecadorId(int secadorId) {
		      
			  String loteNome = null;
		      
		      try {
		    	  connection = AcessoDB.conectar();
		    	  preparedStatement = connection.prepareStatement(SELECT_LASTLOTENOME_BYSECADORID);
		    	  preparedStatement.setInt(1, secadorId);
		      	  resultSet = preparedStatement.executeQuery();
		     
		      	  while(resultSet.next()) {
		      		  
		      		  loteNome = resultSet.getString("lnome");
		      		  
		      		}
		      } catch (Exception e) {
		    	  
		      		System.out.println("Erro: " + e.getMessage());
		      		e.printStackTrace();
		      		
		      	} finally {
		      		
		      		System.out.println("Disconnected from PostgreSQL!!");
		      		AcessoDB.desconectar(connection, preparedStatement, resultSet);
		      		
		      	}
		      
		      	return loteNome;
		      	
		  }
		  
		  
		//SELECT_ALL_NOME
		public ArrayList<String> selectAllNomeDescansando() {
			ArrayList<String> lotesNome = new ArrayList<String>();
		    String loteNome = null;
		      
		    try {
		    	connection = AcessoDB.conectar();
		    	preparedStatement = connection.prepareStatement(SELECT_ALL_NOME_DESCANSANDO);
		      	resultSet = preparedStatement.executeQuery();
		     
		      	while(resultSet.next()) {
		      		  
		      		loteNome = resultSet.getString("lnome");
		      		lotesNome.add(loteNome);
		      		  
		      }
		   } catch (Exception e) {
		    	  
			   System.out.println("Erro: " + e.getMessage());
			   e.printStackTrace();
		      		
		   } finally {
		      		
			   System.out.println("Disconnected from PostgreSQL!!");
			   AcessoDB.desconectar(connection, preparedStatement, resultSet);
		      		
		   }
		      
		   System.out.println(lotesNome);
		   return lotesNome;
		      	
		}   
		
		
		//SELECT_LOTE_NOME
		  public String selectLoteNome(String loteNome) {
		      
			  String loteNomeResultado = null;
		      
		      try {
		    	  connection = AcessoDB.conectar();
		    	  preparedStatement = connection.prepareStatement(SELECT_LOTE_NOME);
		    	  preparedStatement.setString(1, loteNome);
		      	  resultSet = preparedStatement.executeQuery();
		     
		      	  while(resultSet.next()) {
		      		  
		      		  loteNomeResultado = resultSet.getString("lnome");
		      		  
		      		}
		      } catch (Exception e) {
		    	  
		      		System.out.println("Erro: " + e.getMessage());
		      		e.printStackTrace();
		      		
		      	} finally {
		      		
		      		System.out.println("Disconnected from PostgreSQL!!");
		      		AcessoDB.desconectar(connection, preparedStatement, resultSet);
		      		
		      	}
		      
		      	return loteNomeResultado;
		      	
		  }
		
		  
		  //SELECT_LOTE_RECEITAID
		  public int selectReceitaId(String loteNome) {
		      
			  int receitaId = -1;
		      
		      try {
		    	  connection = AcessoDB.conectar();
		    	  preparedStatement = connection.prepareStatement(SELECT_LOTE_RECEITAID);
		    	  preparedStatement.setString(1, loteNome);
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
		  
		  
		  //SELECT_LOTE_RECEITAID
		  public int selectReceitaIDBySecadorId1(int secador_sId) {
		      
			  int receitaId = -1;
		      
		      try {
		    	  connection = AcessoDB.conectar();
		    	  preparedStatement = connection.prepareStatement(SELECT_RECEITAID);
		    	  preparedStatement.setInt(1, secador_sId);
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
		  
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%    
//************************************************************************************************************************
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%	    
			
			  
		  	/* 
	    	 * INSERTS - TIPO DE INSERÇÃO: "LOTE"
	     	 */
			        
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			        
	    	/* 
	    	 * QUERIES CONSTANTES PARA MANIPULAÇÃO DA DB
	    	 */    
			      
			      
	    private final String INSERT_INICIA = "INSERT INTO lote(lnome, lnomeorigem, lnomedestino, ldatainicio, lhorainicio, ltempoterreiro, lestado, letapa, ltemposecagem, ltempodescanso, secador_sid1, receita_rid) VALUES(?, ?, ?, to_date(?, 'YYYY-MM-dd'), to_timestamp(?, 'hh24:mi:ss'), ?, ?, ?, (?)::INTERVAL, (?)::INTERVAL, ?, ?)";
			      
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	    //INSERT INTO lote(lnome, lnomeorigem, lnomedestino, ldatainicio, lhorainicio, ltempoterreiro, lestado, letapa, ltemposecagem, ltempodescanso,
	    		//secador_sid1)
	    		//VALUES ('loteZ', 'origemZ', 'destinoZ', '2015-12-15', '09:24:00', 24, 0, 1, '00:00:00', '00:00:00', 1);
	    
	    
		  	/* 
		  	 * FUNÇÕES DE CHAMADA DAS QUERIES
		  	 */
			      
			         
	    //INSERT
	    public void insert(String lNome, String lNomeOrigem, String lNomeDestino, String lDataInicio, String lHoraInicio, int lTempoTerreiro, int lEstado, int lEtapa, String lTempoSecagem, String lTempoDescanso, int secador_sid1, int receita_rid) {
		    	
	    	try {
	    		connection = AcessoDB.conectar();
	    		preparedStatement = connection.prepareStatement(INSERT_INICIA);
	    		preparedStatement.setString(1, lNome);
	    		preparedStatement.setString(2, lNomeOrigem);
	    		preparedStatement.setString(3, lNomeDestino);
	    		preparedStatement.setString(4, lDataInicio);
	    		preparedStatement.setString(5, lHoraInicio);
	    		preparedStatement.setInt(6, lTempoTerreiro);
	    		preparedStatement.setInt(7, lEstado);
	    		preparedStatement.setInt(8, lEtapa);
	    		preparedStatement.setString(9, lTempoSecagem);
	    		preparedStatement.setString(10, lTempoDescanso);
	    		preparedStatement.setInt(11, secador_sid1);
	    		preparedStatement.setInt(12, receita_rid);
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
	    		 * UPDATES - TIPO DE ATUALIZAÇÃO: "LOTE"
	    		 */
	      
	  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	      
	    		/* 
	    		 * QUERIES CONSTANTES PARA MANIPULAÇÃO DA DB
	    		 */    
	    
	      private final String UPDATE_DESCANSANDO = "UPDATE lote SET lestado = 2, letapa = ?, ltemposecagem = (?)::INTERVAL, lhorainiciodescanso = now()::TIMESTAMP WHERE lnome LIKE ?";
	      private final String UPDATE_REINICIA = "UPDATE lote SET lestado = 1, letapa = ?, ltemposecagem = (?)::INTERVAL, ltempodescanso = (?)::INTERVAL, secador_sid1 = ?, lhorainiciodescanso = NULL WHERE lnome LIKE ?";
	      private final String UPDATE_CONCLUIRFINALIZAPARADO = "UPDATE lote SET lestado = 0, letapa = ?, ltemposecagem = (?)::INTERVAL, ltempodescanso = (?)::INTERVAL, lbebida = ?, lrenda = ?, ltulhadestino = ?, lvariedade = ? WHERE lnome LIKE ?";
	      private final String UPDATE_STATUSFINALIZADO = "UPDATE lote SET lestado = 0, secador_sid1 = NULL, lhorainiciodescanso = NULL WHERE lnome LIKE ?";
	      private final String UPDATE_SETSECADORNULL = "UPDATE lote SET secador_sid1 = NULL WHERE lnome LIKE ?";
	      
	    
	  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	  		/* 
	  		 * FUNÇÕES DE CHAMADA DAS QUERIES
	  		 */    
	    
	    
	      //UPDATE_STATUSFINALIZADO
	      public void updateStatusFinalizado(String lNome) {
	    	
	      	try {
	      		connection = AcessoDB.conectar();
	      		preparedStatement = connection.prepareStatement(UPDATE_STATUSFINALIZADO);
	      		preparedStatement.setString(1, lNome);
	      		preparedStatement.executeUpdate();
	    		
	    		
	      	} catch (Exception e) {
	      		System.out.println("Erro: " + e.getMessage());
	      		e.printStackTrace();
	      	} finally {
	      		System.out.println("Disconnected from PostgreSQL!!");
	      		AcessoDB.desconectar(connection, preparedStatement, resultSet);
	      	}
	      	
	      }
	      
	      
	  	  //UPDATE_FINALIZAPARADO
	      public void updateConcluirFinalizaParado(int lEtapa, String lTempoSecagem, String lTempoDescanso, String lBebida, String lRenda, String lTulhaDestino, String lVariedade, String lNome) {
	    	
	      	try {
	      		connection = AcessoDB.conectar();
	      		preparedStatement = connection.prepareStatement(UPDATE_CONCLUIRFINALIZAPARADO);
	      		preparedStatement.setInt(1, lEtapa);
	      		preparedStatement.setString(2, lTempoSecagem);
	      		preparedStatement.setString(3, lTempoDescanso);
	      		preparedStatement.setString(4, lBebida);
	      		preparedStatement.setString(5, lRenda);
	      		preparedStatement.setString(6, lTulhaDestino);
	      		preparedStatement.setString(7, lVariedade);
	      		preparedStatement.setString(8, lNome);
	      		preparedStatement.executeUpdate();
	    		
	    		
	      	} catch (Exception e) {
	      		System.out.println("Erro: " + e.getMessage());
	      		e.printStackTrace();
	      	} finally {
	      		System.out.println("Disconnected from PostgreSQL!!");
	      		AcessoDB.desconectar(connection, preparedStatement, resultSet);
	      	}
	      	
	      }	  
	      
	      
	      //UPDATE_DESCANSO
	      public void updateDescanso(int lEtapa, String lTempoSecagem, String lNome) {
	    	
	      	try {
	      		connection = AcessoDB.conectar();
	      		preparedStatement = connection.prepareStatement(UPDATE_DESCANSANDO);
	      		preparedStatement.setInt(1, lEtapa);
	      		preparedStatement.setString(2, lTempoSecagem);
	      		preparedStatement.setString(3, lNome);
	      		preparedStatement.executeUpdate();
	    		
	    		
	      	} catch (Exception e) {
	      		System.out.println("Erro: " + e.getMessage());
	      		e.printStackTrace();
	      	} finally {
	      		System.out.println("Disconnected from PostgreSQL!!");
	      		AcessoDB.desconectar(connection, preparedStatement, resultSet);
	      	}
	      	
	      }	 
	      
	      
	      //UPDATE_REINICIA
	      public void updateReinicio(int lEtapa, String lTempoSecagem, String lTempoDescanso, int secador_sid1, String lNome) {
	    	
	      	try {
	      		connection = AcessoDB.conectar();
	      		preparedStatement = connection.prepareStatement(UPDATE_REINICIA);
	      		preparedStatement.setInt(1, lEtapa);
	      		preparedStatement.setString(2, lTempoSecagem);
	      		preparedStatement.setString(3, lTempoDescanso);
	      		preparedStatement.setInt(4, secador_sid1);
	      		preparedStatement.setString(5, lNome);
	      		preparedStatement.executeUpdate();
	    		
	    		
	      	} catch (Exception e) {
	      		System.out.println("Erro: " + e.getMessage());
	      		e.printStackTrace();
	      	} finally {
	      		System.out.println("Disconnected from PostgreSQL!!");
	      		AcessoDB.desconectar(connection, preparedStatement, resultSet);
	      	}
	      	
	      }
	      
	      
	      //UPDATE_SETSECADORNULL
	      public void updateSetSecadorNull(String lNome) {
	    	
	      	try {
	      		connection = AcessoDB.conectar();
	      		preparedStatement = connection.prepareStatement(UPDATE_SETSECADORNULL);
	      		preparedStatement.setString(1, lNome);
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
	        	 * DELETES - TIPO DE EXCLUSÃO: "LOTE"
	        	 */
	        
	    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	        
	        	/* 
	        	 * QUERIES CONSTANTES PARA MANIPULAÇÃO DA DB
	        	 */    
	      
	      
	        private final String DELETE = "DELETE FROM lote WHERE lnome LIKE ?";
	      
	      
	    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	    		/* 
	    		 * FUNÇÕES DE CHAMADA DAS QUERIES
	    		 */    
	      
	      
	        //DELETE
	        public void delete(String lNome) {
	      	
	    		try {
	    			connection = AcessoDB.conectar();
	    			preparedStatement = connection.prepareStatement(DELETE);
	    			preparedStatement.setString(1, lNome);
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
