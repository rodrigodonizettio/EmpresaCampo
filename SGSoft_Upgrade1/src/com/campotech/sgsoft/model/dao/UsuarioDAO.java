package com.campotech.sgsoft.model.dao;

import java.awt.List;
//import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.campotech.sgsoft.controller.AcessoDB;
import com.campotech.sgsoft.model.entity.*;

public class UsuarioDAO {

	//DESIGN PATTERN: SINGLETON
	//Refer�ncia: http://pt.wikipedia.org/wiki/Singleton
	//A IDEIA � TER APENAS UMA INST�NCIA PARA DAO, EVITANDO ACESSOS M�LTIPLOS � DB
	
	
	//ATRIBUTO EST�TICO QUE CONTER� A INST�NCIA DO SINGLETON.
	private static UsuarioDAO INSTANCE = new UsuarioDAO();
	
	
	//ATRIBUTOS PRIVADOS DE USO DO CONNECTOR POSTGRES
	private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
	    
    
    //CONSTRUTOR PRIVADO. SUPRIME O CONSTRUTOR P�BLICO QUE VEM POR PADR�O [UTILIZADO PARA IMPLEMENTAR O SINGLETON]
    private UsuarioDAO() {
    	
    }
    
    
    public static UsuarioDAO getInstance() {
    	return INSTANCE;
    }
    
    
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
      
      	/* 
      	 * SELECTS - TIPO DE BUSCA: "LOGIN [TELA DE LOGIN] E ALL [TELA DE GERENCIAMENTO DE USU�RIOS]"
      	 */
      
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
      
      	/* 
      	 * QUERIES CONSTANTES PARA MANIPULA��O DA DB
      	 */    
    
    
    private final String SELECT_LOGIN = "SELECT * FROM usuario WHERE unome LIKE ?";
    private final String SELECT_ALL_NOME = "SELECT unome FROM usuario WHERE uid > 1 ORDER BY unome ASC";
    
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    	/* 
    	 * FUN��ES DE CHAMADA DAS QUERIES
    	 */
    
       
    //SELECT_LOGIN
    public Usuario selectLogin(String uNome) {
    	Usuario usuario = null;
    	
    	try {
    		connection = AcessoDB.conectar();
    		preparedStatement = connection.prepareStatement(SELECT_LOGIN);
    		preparedStatement.setString(1, uNome);
    		resultSet = preparedStatement.executeQuery();
    		
    		while(resultSet.next()) {
    			usuario = new Usuario();
    			usuario.setuId(resultSet.getInt("uid"));
    			usuario.setuNome(resultSet.getString("unome"));
    			usuario.setuSenha(resultSet.getString("usenha"));
    			usuario.setuNivel(resultSet.getBoolean("univel"));
    		}
    	} catch (Exception e) {
    		System.out.println("Erro: " + e.getMessage());
    		e.printStackTrace();
    	} finally {
    		System.out.println("Disconnected from PostgreSQL!!");
    		AcessoDB.desconectar(connection, preparedStatement, resultSet);
    	}
    	return usuario;
    }
    
    
    //SELECT_NOME
    public String selectNome(String uNome) {
    	
    	String usuarioNome = "";
    	
    	try {
    		connection = AcessoDB.conectar();
    		preparedStatement = connection.prepareStatement(SELECT_LOGIN);
    		preparedStatement.setString(1, uNome);
    		resultSet = preparedStatement.executeQuery();
    		
    		while(resultSet.next()) {
    			usuarioNome = resultSet.getString("unome");
    		}
    	} catch (Exception e) {
    		System.out.println("Erro: " + e.getMessage());
    		e.printStackTrace();
    	} finally {
    		System.out.println("Disconnected from PostgreSQL!!");
    		AcessoDB.desconectar(connection, preparedStatement, resultSet);
    	}
    	return usuarioNome;
    }
    
    
    //SELECT_SENHA
    public String selectSenha(String uNome) {
    	
    	String usuarioSenha = "";
    	
    	try {
    		connection = AcessoDB.conectar();
    		preparedStatement = connection.prepareStatement(SELECT_LOGIN);
    		preparedStatement.setString(1, uNome);
    		resultSet = preparedStatement.executeQuery();
    		
    		while(resultSet.next()) {
    			usuarioSenha = resultSet.getString("usenha");
    		}
    	} catch (Exception e) {
    		System.out.println("Erro: " + e.getMessage());
    		e.printStackTrace();
    	} finally {
    		System.out.println("Disconnected from PostgreSQL!!");
    		AcessoDB.desconectar(connection, preparedStatement, resultSet);
    	}
    	return usuarioSenha;
    }
    
    
    //SELECT_PERFIL
    public boolean selectPerfil(String uNome) {
    	
    	boolean usuarioPerfil = false;
    	
    	try {
    		connection = AcessoDB.conectar();
    		preparedStatement = connection.prepareStatement(SELECT_LOGIN);
    		preparedStatement.setString(1, uNome);
    		resultSet = preparedStatement.executeQuery();
    		
    		while(resultSet.next()) {
    			usuarioPerfil = resultSet.getBoolean("univel");
    		}
    	} catch (Exception e) {
    		System.out.println("Erro: " + e.getMessage());
    		e.printStackTrace();
    	} finally {
    		System.out.println("Disconnected from PostgreSQL!!");
    		AcessoDB.desconectar(connection, preparedStatement, resultSet);
    	}
    	return usuarioPerfil;
    }
    
    
    //SELECT_ALL
    public ArrayList<String> selectAllNome() {
    	ArrayList<String> usuariosNome = new ArrayList<String>();
    	String usuarioNome = null;
    	try {
    		connection = AcessoDB.conectar();
    		preparedStatement = connection.prepareStatement(SELECT_ALL_NOME);
    		resultSet = preparedStatement.executeQuery();
    		
    		while(resultSet.next()) {
    			usuarioNome = resultSet.getString("unome");
    			usuariosNome.add(usuarioNome);
    		}
    	} catch (Exception e) {
    		System.out.println("Erro: " + e.getMessage());
    		e.printStackTrace();
    	} finally {
    		System.out.println("Disconnected from PostgreSQL!!");
    		AcessoDB.desconectar(connection, preparedStatement, resultSet);
    	}
    	System.out.println(usuariosNome);
    	return usuariosNome;
    }
 
    
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%    
//************************************************************************************************************************
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
      
    	/* 
    	 * INSERTS - TIPO DE INSER��O: "USU�RIO"
    	 */
      
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
      
    	/* 
    	 * QUERIES CONSTANTES PARA MANIPULA��O DA DB
    	 */    
    
    
    private final String INSERT = "INSERT INTO usuario(unome, usenha, univel) VALUES(?, ?, ?)";
    
    
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    	/* 
    	 * FUN��ES DE CHAMADA DAS QUERIES
    	 */    
    
    
    //INSERT
    public void insert(String uNome, String uSenha, boolean uNivel) {
    	
    	try {
    		connection = AcessoDB.conectar();
    		preparedStatement = connection.prepareStatement(INSERT);
    		preparedStatement.setString(1, uNome);
    		preparedStatement.setString(2, uSenha);
    		preparedStatement.setBoolean(3, uNivel);
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
      	 * UPDATES - TIPO DE ATUALIZA��O: "USU�RIO"
      	 */
        
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
      	/* 
      	 * QUERIES CONSTANTES PARA MANIPULA��O DA DB
      	 */    
      
      
      private final String UPDATE_USUARIO = "UPDATE usuario SET usenha = ?, univel = ? WHERE unome LIKE ?";
      
      
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

      	/* 
      	 * FUN��ES DE CHAMADA DAS QUERIES
      	 */    
      
      
      //UPDATE_USU�RIO
      public void updateUsuario(String uNome, String uSenha, boolean uNivel) {
      	
      	try {
      		connection = AcessoDB.conectar();
      		preparedStatement = connection.prepareStatement(UPDATE_USUARIO);
      		preparedStatement.setString(1, uSenha);
      		preparedStatement.setBoolean(2, uNivel);
      		preparedStatement.setString(3, uNome);
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
         * DELETES - TIPO DE EXCLUS�O: "USU�RIO"
         */
            
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            
         /* 
          * QUERIES CONSTANTES PARA MANIPULA��O DA DB
          */    
          
          
      private final String DELETE_USUARIO = "DELETE FROM usuario WHERE unome LIKE ?";
          
          
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

         /* 
          * FUN��ES DE CHAMADA DAS QUERIES
          */    
          
          
      //DELETE_USU�RIO
      public void deleteUsuario(String uNome) {
          	
       	try {
       		connection = AcessoDB.conectar();
       		preparedStatement = connection.prepareStatement(DELETE_USUARIO);
       		preparedStatement.setString(1, uNome);
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