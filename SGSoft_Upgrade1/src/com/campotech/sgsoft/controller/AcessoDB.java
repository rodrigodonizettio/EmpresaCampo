package com.campotech.sgsoft.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.campotech.sgsoft.model.Constants;

public class AcessoDB {

	//Método de Conexão à DB - Postgresql
	public static Connection conectar() throws Exception {
		
		Class.forName(Constants.JDBC_DRIVER);
		
		Connection conexao = DriverManager.getConnection(
				Constants.JDBC_URL + Constants.JDBC_DATABASE,
				Constants.JDBC_USER, 
				Constants.JDBC_PASSWORD);
		
		if(conexao != null) {
			System.out.println("Connected to PostgreSQL!!");
		} else {
			System.out.println("Failed to connect to PostgreSQL!!");
		}
		return conexao;
	}
	

	//Método de Desconexão à DB - Postgresql
	public static void desconectar(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
		
		try {
			if(resultSet != null) {
				resultSet.close();
			}
			if(preparedStatement != null) {
				preparedStatement.close();
			}
			if(connection != null) {
				connection.close();
			}
			System.out.println("Disconnected from PostgreSQL!!");	
		} catch (Exception excecao) {
			System.out.println("Falha ao Desconectar do Banco de Dados. Exceção Gerada: " + excecao.getClass());
			excecao.printStackTrace();
		}
	}
	
}
