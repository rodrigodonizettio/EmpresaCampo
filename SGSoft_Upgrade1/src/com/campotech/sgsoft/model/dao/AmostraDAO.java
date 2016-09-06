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

public class AmostraDAO {

	//DESIGN PATTERN: SINGLETON
	//Referência: http://pt.wikipedia.org/wiki/Singleton
	//A IDEIA É TER APENAS UMA INSTÂNCIA PARA DAO, EVITANDO ACESSOS MÚLTIPLOS À DB 
	
	
	//ATRIBUTO ESTÁTICO QUE CONTERÁ A INSTÂNCIA DO SINGLETON.
	private static AmostraDAO INSTANCE = new AmostraDAO();
	
	
	//ATRIBUTOS PRIVADOS DE USO DO CONNECTOR POSTGRES
	private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    
    
    //CONSTRUTOR PRIVADO. SUPRIME O CONSTRUTOR PÚBLICO QUE VEM POR PADRÃO [UTILIZADO PARA IMPLEMENTAR O SINGLETON]
    private AmostraDAO() {
    	
    }
    
    
    public static AmostraDAO getInstance() {
    	return INSTANCE;
    }
    
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    
    /* 
     * SELECTS - TIPO DE BUSCA: "TUDO"
     */
    
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    /* 
     * QUERIES CONSTANTES PARA MANIPULAÇÃO DA DB
     */
    
    
    private final String SELECT_TUDO = "SELECT secador_sid, lote_lnome, atemperaturaentrada, atemperaturamassa, aumidade, to_char(adata, 'DD/MM/YYYY'), ahora, atulhadestino, arenda, abebida, avariedade, atipocontrole, asetpoint, ausuarionome FROM amostra ORDER BY adata ASC";
    private final String SELECT_TUDO_DIA = "SELECT secador_sid, lote_lnome, atemperaturaentrada, atemperaturamassa, aumidade, to_char(adata, 'DD/MM/YYYY'), ahora, atulhadestino, arenda, abebida, avariedade, atipocontrole, asetpoint, ausuarionome FROM amostra WHERE CAST(adata AS CHARACTER VARYING) = ? ORDER BY adata ASC";
    private final String SELECT_TUDO_DIA1 = "SELECT secador_sid, lote_lnome, atemperaturaentrada, atemperaturamassa, aumidade, to_char(adata, 'DD/MM/YYYY'), ahora, atulhadestino, arenda, abebida, avariedade, atipocontrole, asetpoint, ausuarionome FROM amostra WHERE adata = NOW()::DATE ORDER BY adata ASC";
    //SELECT FROM DATE THE DATE OF THE CURRENT-DAY ON THE OS [Operational System] CLOCK
    /*
    SELECT secador_sid, lote_lnome, atemperaturaentrada, atemperaturamassa, aumidade,
    adata, ahora, atulhadestino, arenda, abebida, avariedade, atipocontrole
    FROM amostra
    WHERE adata = NOW()::DATE
    */
    
    private final String SELECT_TUDO_SEMANA = "SELECT secador_sid, lote_lnome, atemperaturaentrada, atemperaturamassa, aumidade, to_char(adata, 'DD/MM/YYYY'), ahora, atulhadestino, arenda, abebida, avariedade, atipocontrole, asetpoint, ausuarionome FROM amostra WHERE adata BETWEEN NOW()::DATE-EXTRACT(DOW FROM NOW())::INTEGER AND NOW()::DATE-EXTRACT(DOW FROM NOW())::INTEGER+6  ORDER BY adata ASC";
    //SELECT FROM DATE THE ENTIRE DATE OF THE FIRST DAY OF THE WEEK. THE INTEGER CAST THE FIRST DAY OF THE WEEK. INTEGER+6 = LAST DAY OF THE WEEK
    /*
    SELECT secador_sid, lote_lnome, atemperaturaentrada, atemperaturamassa, aumidade,
	adata, ahora, atulhadestino, arenda, abebida, avariedade, atipocontrole
	FROM amostra
	WHERE adata
	BETWEEN NOW()::DATE-EXTRACT(DOW FROM NOW())::INTEGER AND NOW()::DATE-EXTRACT(DOW FROM NOW())::INTEGER+6
    */
    
    private final String SELECT_TUDO_MES = "SELECT secador_sid, lote_lnome, atemperaturaentrada, atemperaturamassa, aumidade, to_char(adata, 'DD/MM/YYYY'), ahora, atulhadestino, arenda, abebida, avariedade, atipocontrole, asetpoint, ausuarionome FROM amostra WHERE EXTRACT(MONTH FROM date_trunc('MONTH', adata)) = (SELECT EXTRACT(MONTH FROM date_trunc('MONTH', now())::DATE)) ORDER BY adata ASC";
    //SELECT FROM DATE THE MONTH OF THE CURRENT-MONTH ON THE OS [Operational System] CLOCK
    /*
    SELECT secador_sid, lote_lnome, atemperaturaentrada, atemperaturamassa, aumidade, adata, ahora, atulhadestino,
    arenda, abebida, avariedade, atipocontrole
    FROM amostra
    WHERE EXTRACT(MONTH FROM date_trunc('MONTH', adata)) = (SELECT EXTRACT(MONTH FROM date_trunc('MONTH', now())::DATE))
    */
    
    private final String SELECT_TUDO_PERIODO = "SELECT secador_sid, lote_lnome, atemperaturaentrada, atemperaturamassa, aumidade, to_char(adata, 'DD/MM/YYYY'), ahora, atulhadestino, arenda, abebida, avariedade, atipocontrole, asetpoint, ausuarionome FROM amostra WHERE adata >= ?::DATE AND adata <= ?::DATE ORDER BY adata ASC";
        
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    /* 
     * FUNÇÕES DE CHAMADA DAS QUERIES
     */
    
    
    //SELECT_TUDO
    public TableModel selectTudo() {
    	//ArrayList<Amostra> amostras = new ArrayList<Amostra>();
    	//Amostra amostra = null;
    	TableModel tblModel = null;
    	try {
    		connection = AcessoDB.conectar();
    		preparedStatement = connection.prepareStatement(SELECT_TUDO);
    		resultSet = preparedStatement.executeQuery();
    		tblModel = DbUtils.resultSetToTableModel(resultSet);
    		/*
    		while(resultSet.next()) {
    			amostra = new Amostra();  			
    			amostra.setSecador_sId(resultSet.getInt("secador_sid"));
    			amostra.setLote_lNome(resultSet.getString("lote_lnome"));
    			amostra.setaTemperaturaEntrada(resultSet.getInt("atemperaturaentrada"));
    			amostra.setaTemperaturaMassa(resultSet.getInt("atemperaturamassa"));
    			amostra.setaUmidade(resultSet.getInt("aumidade"));
    			amostra.setaData(resultSet.getDate("adata"));
    			amostra.setaHora(resultSet.getTime("ahora"));
    			amostra.setaTulhaDestino(resultSet.getString("atulhadestino"));
    			amostra.setaRenda(resultSet.getInt("arenda"));
    			amostra.setaBebida(resultSet.getString("abebida"));
    			amostra.setaVariedade(resultSet.getString("avariedade"));
    			amostra.setaTipoControle(resultSet.getBoolean("atipocontrole"));
    			amostras.add(amostra);
    		}
    		*/
    	} catch (Exception e) {
    		System.out.println("Erro: " + e.getMessage());
    		e.printStackTrace();
    	} finally {
    		System.out.println("Disconnected from PostgreSQL!!");
    		AcessoDB.desconectar(connection, preparedStatement, resultSet);
    	}
    	return tblModel;
    }
    
    
    //SELECT_TUDO_DIA
    public TableModel selectTudoDia(String dataDia) {
    	//ArrayList<Amostra> amostras = new ArrayList<Amostra>();
    	//Amostra amostra = null;
    	TableModel tblModel = null;
    	try {
    		connection = AcessoDB.conectar();
    		preparedStatement = connection.prepareStatement(SELECT_TUDO_DIA);
    		preparedStatement.setString(1, dataDia);
    		System.out.println(preparedStatement);
    		resultSet = preparedStatement.executeQuery();
    		tblModel = DbUtils.resultSetToTableModel(resultSet);
    		/*
    		while(resultSet.next()) {
    			amostra = new Amostra();  			
    			amostra.setSecador_sId(resultSet.getInt("secador_sid"));
    			amostra.setLote_lNome(resultSet.getString("lote_lnome"));
    			amostra.setaTemperaturaEntrada(resultSet.getInt("atemperaturaentrada"));
    			amostra.setaTemperaturaMassa(resultSet.getInt("atemperaturamassa"));
    			amostra.setaUmidade(resultSet.getInt("aumidade"));
    			amostra.setaData(resultSet.getDate("adata"));
    			amostra.setaHora(resultSet.getTime("ahora"));
    			amostra.setaTulhaDestino(resultSet.getString("atulhadestino"));
    			amostra.setaRenda(resultSet.getInt("arenda"));
    			amostra.setaBebida(resultSet.getString("abebida"));
    			amostra.setaVariedade(resultSet.getString("avariedade"));
    			amostra.setaTipoControle(resultSet.getBoolean("atipocontrole"));
    			amostras.add(amostra);
    		}
    		*/
    	} catch (Exception e) {
    		System.out.println("Erro: " + e.getMessage());
    		e.printStackTrace();
    	} finally {
    		System.out.println("Disconnected from PostgreSQL!!");
    		AcessoDB.desconectar(connection, preparedStatement, resultSet);
    	}
    	return tblModel;
    }
    
    
    //SELECT_TUDO_DIA1
    public TableModel selectTudoDia1() {
    	//ArrayList<Amostra> amostras = new ArrayList<Amostra>();
    	//Amostra amostra = null;
    	TableModel tblModel = null;
    	try {
    		connection = AcessoDB.conectar();
    		preparedStatement = connection.prepareStatement(SELECT_TUDO_DIA1);
    		System.out.println(preparedStatement);
    		resultSet = preparedStatement.executeQuery();
    		tblModel = DbUtils.resultSetToTableModel(resultSet);
    		/*
    		while(resultSet.next()) {
    			amostra = new Amostra();  			
    			amostra.setSecador_sId(resultSet.getInt("secador_sid"));
    			amostra.setLote_lNome(resultSet.getString("lote_lnome"));
    			amostra.setaTemperaturaEntrada(resultSet.getInt("atemperaturaentrada"));
    			amostra.setaTemperaturaMassa(resultSet.getInt("atemperaturamassa"));
    			amostra.setaUmidade(resultSet.getInt("aumidade"));
    			amostra.setaData(resultSet.getDate("adata"));
    			amostra.setaHora(resultSet.getTime("ahora"));
    			amostra.setaTulhaDestino(resultSet.getString("atulhadestino"));
    			amostra.setaRenda(resultSet.getInt("arenda"));
    			amostra.setaBebida(resultSet.getString("abebida"));
    			amostra.setaVariedade(resultSet.getString("avariedade"));
    			amostra.setaTipoControle(resultSet.getBoolean("atipocontrole"));
    			amostras.add(amostra);
    		}
    		*/
    	} catch (Exception e) {
    		System.out.println("Erro: " + e.getMessage());
    		e.printStackTrace();
    	} finally {
    		System.out.println("Disconnected from PostgreSQL!!");
    		AcessoDB.desconectar(connection, preparedStatement, resultSet);
    	}
    	return tblModel;
    }
    
    
    //SELECT_TUDO_SEMANA
    public TableModel selectTudoSemana() {
    	//ArrayList<Amostra> amostras = new ArrayList<Amostra>();
    	//Amostra amostra = null;
    	TableModel tblModel = null;
    	try {
    		connection = AcessoDB.conectar();
    		preparedStatement = connection.prepareStatement(SELECT_TUDO_SEMANA);
    		System.out.println(preparedStatement);
    		resultSet = preparedStatement.executeQuery();
    		tblModel = DbUtils.resultSetToTableModel(resultSet);
    		/*
    		while(resultSet.next()) {
    			amostra = new Amostra();  			
    			amostra.setSecador_sId(resultSet.getInt("secador_sid"));
    			amostra.setLote_lNome(resultSet.getString("lote_lnome"));
    			amostra.setaTemperaturaEntrada(resultSet.getInt("atemperaturaentrada"));
    			amostra.setaTemperaturaMassa(resultSet.getInt("atemperaturamassa"));
    			amostra.setaUmidade(resultSet.getInt("aumidade"));
    			amostra.setaData(resultSet.getDate("adata"));
    			amostra.setaHora(resultSet.getTime("ahora"));
    			amostra.setaTulhaDestino(resultSet.getString("atulhadestino"));
    			amostra.setaRenda(resultSet.getInt("arenda"));
    			amostra.setaBebida(resultSet.getString("abebida"));
    			amostra.setaVariedade(resultSet.getString("avariedade"));
    			amostra.setaTipoControle(resultSet.getBoolean("atipocontrole"));
    			amostras.add(amostra);
    		}
    		*/
    	} catch (Exception e) {
    		System.out.println("Erro: " + e.getMessage());
    		e.printStackTrace();
    	} finally {
    		System.out.println("Disconnected from PostgreSQL!!");
    		AcessoDB.desconectar(connection, preparedStatement, resultSet);
    	}
    	return tblModel;
    }
    
    
    //SELECT_TUDO_MES
    public TableModel selectTudoMes() {
    	//ArrayList<Amostra> amostras = new ArrayList<Amostra>();
    	//Amostra amostra = null;
    	TableModel tblModel = null;
    	try {
    		connection = AcessoDB.conectar();
    		preparedStatement = connection.prepareStatement(SELECT_TUDO_MES);
    		System.out.println(preparedStatement);
    		resultSet = preparedStatement.executeQuery();
    		tblModel = DbUtils.resultSetToTableModel(resultSet);
    		/*
    		while(resultSet.next()) {
    			amostra = new Amostra();  			
    			amostra.setSecador_sId(resultSet.getInt("secador_sid"));
    			amostra.setLote_lNome(resultSet.getString("lote_lnome"));
    			amostra.setaTemperaturaEntrada(resultSet.getInt("atemperaturaentrada"));
    			amostra.setaTemperaturaMassa(resultSet.getInt("atemperaturamassa"));
    			amostra.setaUmidade(resultSet.getInt("aumidade"));
    			amostra.setaData(resultSet.getDate("adata"));
    			amostra.setaHora(resultSet.getTime("ahora"));
    			amostra.setaTulhaDestino(resultSet.getString("atulhadestino"));
    			amostra.setaRenda(resultSet.getInt("arenda"));
    			amostra.setaBebida(resultSet.getString("abebida"));
    			amostra.setaVariedade(resultSet.getString("avariedade"));
    			amostra.setaTipoControle(resultSet.getBoolean("atipocontrole"));
    			amostras.add(amostra);
    		}
    		*/
    	} catch (Exception e) {
    		System.out.println("Erro: " + e.getMessage());
    		e.printStackTrace();
    	} finally {
    		System.out.println("Disconnected from PostgreSQL!!");
    		AcessoDB.desconectar(connection, preparedStatement, resultSet);
    	}
    	return tblModel;
    }
    
    
    //SELECT_TUDO_PERÍODO
    public TableModel selectTudoPeriodo(String dataInicio, String dataFim) {
    	//ArrayList<Amostra> amostras = new ArrayList<Amostra>();
    	//Amostra amostra = null;
    	TableModel tblModel = null;
    	try {
    		connection = AcessoDB.conectar();
    		preparedStatement = connection.prepareStatement(SELECT_TUDO_PERIODO);
    		preparedStatement.setString(1, dataInicio);
    		preparedStatement.setString(2, dataFim);
    		System.out.println(preparedStatement);
    		resultSet = preparedStatement.executeQuery();
    		tblModel = DbUtils.resultSetToTableModel(resultSet);
    		/*
    		while(resultSet.next()) {
    			amostra = new Amostra();  			
    			amostra.setSecador_sId(resultSet.getInt("secador_sid"));
    			amostra.setLote_lNome(resultSet.getString("lote_lnome"));
    			amostra.setaTemperaturaEntrada(resultSet.getInt("atemperaturaentrada"));
    			amostra.setaTemperaturaMassa(resultSet.getInt("atemperaturamassa"));
    			amostra.setaUmidade(resultSet.getInt("aumidade"));
    			amostra.setaData(resultSet.getDate("adata"));
    			amostra.setaHora(resultSet.getTime("ahora"));
    			amostra.setaTulhaDestino(resultSet.getString("atulhadestino"));
    			amostra.setaRenda(resultSet.getInt("arenda"));
    			amostra.setaBebida(resultSet.getString("abebida"));
    			amostra.setaVariedade(resultSet.getString("avariedade"));
    			amostra.setaTipoControle(resultSet.getBoolean("atipocontrole"));
    			amostras.add(amostra);
    		}
    		*/
    	} catch (Exception e) {
    		System.out.println("Erro: " + e.getMessage());
    		e.printStackTrace();
    	} finally {
    		System.out.println("Disconnected from PostgreSQL!!");
    		AcessoDB.desconectar(connection, preparedStatement, resultSet);
    	}
    	return tblModel;
    }
    
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%    
//************************************************************************************************************************
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    
    /* 
     * SELECTS - TIPO DE BUSCA: "LOTE"
     */
    
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    /* 
     * QUERIES CONSTANTES PARA MANIPULAÇÃO DA DB
     */
    
    
    private final String SELECT_LOTE = "SELECT secador_sid, lote_lnome, atemperaturaentrada, atemperaturamassa, aumidade, to_char(adata, 'DD/MM/YYYY'), ahora, atulhadestino, arenda, abebida, avariedade, atipocontrole, asetpoint, ausuarionome FROM amostra WHERE lote_lnome LIKE ?";
    private final String SELECT_LOTE_DIA = "SELECT secador_sid, lote_lnome, atemperaturaentrada, atemperaturamassa, aumidade, to_char(adata, 'DD/MM/YYYY'), ahora, atulhadestino, arenda, abebida, avariedade, atipocontrole, asetpoint, ausuarionome FROM amostra WHERE ((adata = NOW()::DATE) AND (lote_lnome LIKE ?))";
    private final String SELECT_LOTE_SEMANA = "SELECT secador_sid, lote_lnome, atemperaturaentrada, atemperaturamassa, aumidade, to_char(adata, 'DD/MM/YYYY'), ahora, atulhadestino, arenda, abebida, avariedade, atipocontrole, asetpoint, ausuarionome FROM amostra WHERE ((adata BETWEEN NOW()::DATE-EXTRACT(DOW FROM NOW())::INTEGER AND NOW()::DATE-EXTRACT(DOW FROM NOW())::INTEGER+6) AND (lote_lnome LIKE ?))";
    private final String SELECT_LOTE_MES = "SELECT secador_sid, lote_lnome, atemperaturaentrada, atemperaturamassa, aumidade, to_char(adata, 'DD/MM/YYYY'), ahora, atulhadestino, arenda, abebida, avariedade, atipocontrole, asetpoint, ausuarionome FROM amostra WHERE ((EXTRACT(MONTH FROM date_trunc('MONTH', adata)) = (SELECT EXTRACT(MONTH FROM date_trunc('MONTH', now())::DATE))) AND (lote_lnome LIKE ?))";
    private final String SELECT_LOTE_PERIODO = "SELECT secador_sid, lote_lnome, atemperaturaentrada, atemperaturamassa, aumidade, to_char(adata, 'DD/MM/YYYY'), ahora, atulhadestino, arenda, abebida, avariedade, atipocontrole, asetpoint, ausuarionome FROM amostra WHERE ((adata >= ?::DATE AND adata <= ?::DATE) AND (lote_lnome LIKE ?))";
    
    
    
    
    
    
    //SELECT_LOTE
    public TableModel selectLote(String loteNome) {
    	//ArrayList<Amostra> amostras = new ArrayList<Amostra>();
    	//Amostra amostra = null;
    	TableModel tblModel = null;
    	try {
    		connection = AcessoDB.conectar();
    		preparedStatement = connection.prepareStatement(SELECT_LOTE);
    		preparedStatement.setString(1, "%" + loteNome + "%");
    		resultSet = preparedStatement.executeQuery();
    		tblModel = DbUtils.resultSetToTableModel(resultSet);
    		/*
    		while(resultSet.next()) {
    			amostra = new Amostra();  			
    			amostra.setSecador_sId(resultSet.getInt("secador_sid"));
    			amostra.setLote_lNome(resultSet.getString("lote_lnome"));
    			amostra.setaTemperaturaEntrada(resultSet.getInt("atemperaturaentrada"));
    			amostra.setaTemperaturaMassa(resultSet.getInt("atemperaturamassa"));
    			amostra.setaUmidade(resultSet.getInt("aumidade"));
    			amostra.setaData(resultSet.getDate("adata"));
    			amostra.setaHora(resultSet.getTime("ahora"));
    			amostra.setaTulhaDestino(resultSet.getString("atulhadestino"));
    			amostra.setaRenda(resultSet.getInt("arenda"));
    			amostra.setaBebida(resultSet.getString("abebida"));
    			amostra.setaVariedade(resultSet.getString("avariedade"));
    			amostra.setaTipoControle(resultSet.getBoolean("atipocontrole"));
    			amostras.add(amostra);
    		}
    		*/
    	} catch (Exception e) {
    		System.out.println("Erro: " + e.getMessage());
    		e.printStackTrace();
    	} finally {
    		System.out.println("Disconnected from PostgreSQL!!");
    		AcessoDB.desconectar(connection, preparedStatement, resultSet);
    	}
    	return tblModel;
    }
    
    
    //SELECT_LOTE_DIA
    public TableModel selectLoteDia(String loteNome) {
    	//ArrayList<Amostra> amostras = new ArrayList<Amostra>();
    	//Amostra amostra = null;
    	TableModel tblModel = null;
    	try {
    		connection = AcessoDB.conectar();
    		preparedStatement = connection.prepareStatement(SELECT_LOTE_DIA);
    		preparedStatement.setString(1, "%" + loteNome + "%");
    		resultSet = preparedStatement.executeQuery();
    		tblModel = DbUtils.resultSetToTableModel(resultSet);
    		/*
    		while(resultSet.next()) {
    			amostra = new Amostra();  			
    			amostra.setSecador_sId(resultSet.getInt("secador_sid"));
    			amostra.setLote_lNome(resultSet.getString("lote_lnome"));
    			amostra.setaTemperaturaEntrada(resultSet.getInt("atemperaturaentrada"));
    			amostra.setaTemperaturaMassa(resultSet.getInt("atemperaturamassa"));
    			amostra.setaUmidade(resultSet.getInt("aumidade"));
    			amostra.setaData(resultSet.getDate("adata"));
    			amostra.setaHora(resultSet.getTime("ahora"));
    			amostra.setaTulhaDestino(resultSet.getString("atulhadestino"));
    			amostra.setaRenda(resultSet.getInt("arenda"));
    			amostra.setaBebida(resultSet.getString("abebida"));
    			amostra.setaVariedade(resultSet.getString("avariedade"));
    			amostra.setaTipoControle(resultSet.getBoolean("atipocontrole"));
    			amostras.add(amostra);
    		}
    		*/
    	} catch (Exception e) {
    		System.out.println("Erro: " + e.getMessage());
    		e.printStackTrace();
    	} finally {
    		System.out.println("Disconnected from PostgreSQL!!");
    		AcessoDB.desconectar(connection, preparedStatement, resultSet);
    	}
    	return tblModel;
    }
    
    
    //SELECT_LOTE_SEMANA
    public TableModel selectLoteSemana(String loteNome) {
    	//ArrayList<Amostra> amostras = new ArrayList<Amostra>();
    	//Amostra amostra = null;
    	TableModel tblModel = null;
    	try {
    		connection = AcessoDB.conectar();
    		preparedStatement = connection.prepareStatement(SELECT_LOTE_SEMANA);
    		preparedStatement.setString(1, "%" + loteNome + "%");
    		resultSet = preparedStatement.executeQuery();
    		tblModel = DbUtils.resultSetToTableModel(resultSet);
    		/*
    		while(resultSet.next()) {
    			amostra = new Amostra();  			
    			amostra.setSecador_sId(resultSet.getInt("secador_sid"));
    			amostra.setLote_lNome(resultSet.getString("lote_lnome"));
    			amostra.setaTemperaturaEntrada(resultSet.getInt("atemperaturaentrada"));
    			amostra.setaTemperaturaMassa(resultSet.getInt("atemperaturamassa"));
    			amostra.setaUmidade(resultSet.getInt("aumidade"));
    			amostra.setaData(resultSet.getDate("adata"));
    			amostra.setaHora(resultSet.getTime("ahora"));
    			amostra.setaTulhaDestino(resultSet.getString("atulhadestino"));
    			amostra.setaRenda(resultSet.getInt("arenda"));
    			amostra.setaBebida(resultSet.getString("abebida"));
    			amostra.setaVariedade(resultSet.getString("avariedade"));
    			amostra.setaTipoControle(resultSet.getBoolean("atipocontrole"));
    			amostras.add(amostra);
    		}
    		*/
    	} catch (Exception e) {
    		System.out.println("Erro: " + e.getMessage());
    		e.printStackTrace();
    	} finally {
    		System.out.println("Disconnected from PostgreSQL!!");
    		AcessoDB.desconectar(connection, preparedStatement, resultSet);
    	}
    	return tblModel;
    }
    
    
    //SELECT_LOTE_MÊS
    public TableModel selectLoteMes(String loteNome) {
    	//ArrayList<Amostra> amostras = new ArrayList<Amostra>();
    	//Amostra amostra = null;
    	TableModel tblModel = null;
    	try {
    		connection = AcessoDB.conectar();
    		preparedStatement = connection.prepareStatement(SELECT_LOTE_MES);
    		preparedStatement.setString(1, "%" + loteNome + "%");
    		resultSet = preparedStatement.executeQuery();
    		tblModel = DbUtils.resultSetToTableModel(resultSet);
    		/*
    		while(resultSet.next()) {
    			amostra = new Amostra();  			
    			amostra.setSecador_sId(resultSet.getInt("secador_sid"));
    			amostra.setLote_lNome(resultSet.getString("lote_lnome"));
    			amostra.setaTemperaturaEntrada(resultSet.getInt("atemperaturaentrada"));
    			amostra.setaTemperaturaMassa(resultSet.getInt("atemperaturamassa"));
    			amostra.setaUmidade(resultSet.getInt("aumidade"));
    			amostra.setaData(resultSet.getDate("adata"));
    			amostra.setaHora(resultSet.getTime("ahora"));
    			amostra.setaTulhaDestino(resultSet.getString("atulhadestino"));
    			amostra.setaRenda(resultSet.getInt("arenda"));
    			amostra.setaBebida(resultSet.getString("abebida"));
    			amostra.setaVariedade(resultSet.getString("avariedade"));
    			amostra.setaTipoControle(resultSet.getBoolean("atipocontrole"));
    			amostras.add(amostra);
    		}
    		*/
    	} catch (Exception e) {
    		System.out.println("Erro: " + e.getMessage());
    		e.printStackTrace();
    	} finally {
    		System.out.println("Disconnected from PostgreSQL!!");
    		AcessoDB.desconectar(connection, preparedStatement, resultSet);
    	}
    	return tblModel;
    }
    
    
    //SELECT_LOTE_PERÍODO
    public TableModel selectLotePeriodo(String loteNome, String dataInicio, String dataFim) {
    	//ArrayList<Amostra> amostras = new ArrayList<Amostra>();
    	//Amostra amostra = null;
    	TableModel tblModel = null;
    	try {
    		connection = AcessoDB.conectar();
    		preparedStatement = connection.prepareStatement(SELECT_LOTE_PERIODO);
    		preparedStatement.setString(1, dataInicio);
    		preparedStatement.setString(2, dataFim);
    		preparedStatement.setString(3, "%" + loteNome + "%");
    		System.out.println(preparedStatement);
    		resultSet = preparedStatement.executeQuery();
    		tblModel = DbUtils.resultSetToTableModel(resultSet);
    		/*
    		while(resultSet.next()) {
    			amostra = new Amostra();  			
    			amostra.setSecador_sId(resultSet.getInt("secador_sid"));
    			amostra.setLote_lNome(resultSet.getString("lote_lnome"));
    			amostra.setaTemperaturaEntrada(resultSet.getInt("atemperaturaentrada"));
    			amostra.setaTemperaturaMassa(resultSet.getInt("atemperaturamassa"));
    			amostra.setaUmidade(resultSet.getInt("aumidade"));
    			amostra.setaData(resultSet.getDate("adata"));
    			amostra.setaHora(resultSet.getTime("ahora"));
    			amostra.setaTulhaDestino(resultSet.getString("atulhadestino"));
    			amostra.setaRenda(resultSet.getInt("arenda"));
    			amostra.setaBebida(resultSet.getString("abebida"));
    			amostra.setaVariedade(resultSet.getString("avariedade"));
    			amostra.setaTipoControle(resultSet.getBoolean("atipocontrole"));
    			amostras.add(amostra);
    		}
    		*/
    	} catch (Exception e) {
    		System.out.println("Erro: " + e.getMessage());
    		e.printStackTrace();
    	} finally {
    		System.out.println("Disconnected from PostgreSQL!!");
    		AcessoDB.desconectar(connection, preparedStatement, resultSet);
    	}
    	return tblModel;
    }
    

//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%    
//************************************************************************************************************************
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
      
      /* 
       * SELECTS - TIPO DE BUSCA: "SECADOR"
       */
      
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
      
      /* 
       * QUERIES CONSTANTES PARA MANIPULAÇÃO DA DB
       */
      
      
      private final String SELECT_SECADOR = "SELECT secador_sid, lote_lnome, atemperaturaentrada, atemperaturamassa, aumidade, to_char(adata, 'DD/MM/YYYY'), ahora, atulhadestino, arenda, abebida, avariedade, atipocontrole, asetpoint, ausuarionome FROM amostra WHERE secador_sid = ?::SMALLINT";
      private final String SELECT_SECADOR_DIA = "SELECT secador_sid, lote_lnome, atemperaturaentrada, atemperaturamassa, aumidade, to_char(adata, 'DD/MM/YYYY'), ahora, atulhadestino, arenda, abebida, avariedade, atipocontrole, asetpoint, ausuarionome FROM amostra WHERE ((adata = NOW()::DATE) AND (secador_sid = ?::SMALLINT))";
      private final String SELECT_SECADOR_SEMANA = "SELECT secador_sid, lote_lnome, atemperaturaentrada, atemperaturamassa, aumidade, to_char(adata, 'DD/MM/YYYY'), ahora, atulhadestino, arenda, abebida, avariedade, atipocontrole, asetpoint, ausuarionome FROM amostra WHERE ((adata BETWEEN NOW()::DATE-EXTRACT(DOW FROM NOW())::INTEGER AND NOW()::DATE-EXTRACT(DOW FROM NOW())::INTEGER+6) AND (secador_sid = ?::SMALLINT))";
      private final String SELECT_SECADOR_MES = "SELECT secador_sid, lote_lnome, atemperaturaentrada, atemperaturamassa, aumidade, to_char(adata, 'DD/MM/YYYY'), ahora, atulhadestino, arenda, abebida, avariedade, atipocontrole, asetpoint, ausuarionome FROM amostra WHERE ((EXTRACT(MONTH FROM date_trunc('MONTH', adata)) = (SELECT EXTRACT(MONTH FROM date_trunc('MONTH', now())::DATE))) AND (secador_sid = ?::SMALLINT))";
      private final String SELECT_SECADOR_PERIODO = "SELECT secador_sid, lote_lnome, atemperaturaentrada, atemperaturamassa, aumidade, to_char(adata, 'DD/MM/YYYY'), ahora, atulhadestino, arenda, abebida, avariedade, atipocontrole, asetpoint, ausuarionome FROM amostra WHERE ((adata >= ?::DATE AND adata <= ?::DATE) AND (secador_sid = ?::SMALLINT))";
      
      
      //SELECT_SECADOR
      public TableModel selectSecador(String secadorId) {
      	//ArrayList<Amostra> amostras = new ArrayList<Amostra>();
      	//Amostra amostra = null;
      	TableModel tblModel = null;
      	try {
      		connection = AcessoDB.conectar();
      		preparedStatement = connection.prepareStatement(SELECT_SECADOR);
      		preparedStatement.setString(1, secadorId);
      		resultSet = preparedStatement.executeQuery();
      		tblModel = DbUtils.resultSetToTableModel(resultSet);
      		/*
      		while(resultSet.next()) {
      			amostra = new Amostra();  			
      			amostra.setSecador_sId(resultSet.getInt("secador_sid"));
      			amostra.setLote_lNome(resultSet.getString("lote_lnome"));
      			amostra.setaTemperaturaEntrada(resultSet.getInt("atemperaturaentrada"));
      			amostra.setaTemperaturaMassa(resultSet.getInt("atemperaturamassa"));
      			amostra.setaUmidade(resultSet.getInt("aumidade"));
      			amostra.setaData(resultSet.getDate("adata"));
      			amostra.setaHora(resultSet.getTime("ahora"));
      			amostra.setaTulhaDestino(resultSet.getString("atulhadestino"));
      			amostra.setaRenda(resultSet.getInt("arenda"));
      			amostra.setaBebida(resultSet.getString("abebida"));
      			amostra.setaVariedade(resultSet.getString("avariedade"));
      			amostra.setaTipoControle(resultSet.getBoolean("atipocontrole"));
      			amostras.add(amostra);
      		}
      		*/
      	} catch (Exception e) {
      		System.out.println("Erro: " + e.getMessage());
      		e.printStackTrace();
      	} finally {
      		System.out.println("Disconnected from PostgreSQL!!");
      		AcessoDB.desconectar(connection, preparedStatement, resultSet);
      	}
      	return tblModel;
      }

      
      //SELECT_SECADOR_DIA
      public TableModel selectSecadorDia(String secadorId) {
      	//ArrayList<Amostra> amostras = new ArrayList<Amostra>();
      	//Amostra amostra = null;
      	TableModel tblModel = null;
      	try {
      		connection = AcessoDB.conectar();
      		preparedStatement = connection.prepareStatement(SELECT_SECADOR_DIA);
      		preparedStatement.setString(1, secadorId);
      		resultSet = preparedStatement.executeQuery();
      		tblModel = DbUtils.resultSetToTableModel(resultSet);
      		/*
      		while(resultSet.next()) {
      			amostra = new Amostra();  			
      			amostra.setSecador_sId(resultSet.getInt("secador_sid"));
      			amostra.setLote_lNome(resultSet.getString("lote_lnome"));
      			amostra.setaTemperaturaEntrada(resultSet.getInt("atemperaturaentrada"));
      			amostra.setaTemperaturaMassa(resultSet.getInt("atemperaturamassa"));
      			amostra.setaUmidade(resultSet.getInt("aumidade"));
      			amostra.setaData(resultSet.getDate("adata"));
      			amostra.setaHora(resultSet.getTime("ahora"));
      			amostra.setaTulhaDestino(resultSet.getString("atulhadestino"));
      			amostra.setaRenda(resultSet.getInt("arenda"));
      			amostra.setaBebida(resultSet.getString("abebida"));
      			amostra.setaVariedade(resultSet.getString("avariedade"));
      			amostra.setaTipoControle(resultSet.getBoolean("atipocontrole"));
      			amostras.add(amostra);
      		}
      		*/
      	} catch (Exception e) {
      		System.out.println("Erro: " + e.getMessage());
      		e.printStackTrace();
      	} finally {
      		System.out.println("Disconnected from PostgreSQL!!");
      		AcessoDB.desconectar(connection, preparedStatement, resultSet);
      	}
      	return tblModel;
      }
      
      
      //SELECT_SECADOR_SEMANA
      public TableModel selectSecadorSemana(String secadorId) {
      	//ArrayList<Amostra> amostras = new ArrayList<Amostra>();
      	//Amostra amostra = null;
      	TableModel tblModel = null;
      	try {
      		connection = AcessoDB.conectar();
      		preparedStatement = connection.prepareStatement(SELECT_SECADOR_SEMANA);
      		preparedStatement.setString(1, secadorId);
      		resultSet = preparedStatement.executeQuery();
      		tblModel = DbUtils.resultSetToTableModel(resultSet);
      		/*
      		while(resultSet.next()) {
      			amostra = new Amostra();  			
      			amostra.setSecador_sId(resultSet.getInt("secador_sid"));
      			amostra.setLote_lNome(resultSet.getString("lote_lnome"));
      			amostra.setaTemperaturaEntrada(resultSet.getInt("atemperaturaentrada"));
      			amostra.setaTemperaturaMassa(resultSet.getInt("atemperaturamassa"));
      			amostra.setaUmidade(resultSet.getInt("aumidade"));
      			amostra.setaData(resultSet.getDate("adata"));
      			amostra.setaHora(resultSet.getTime("ahora"));
      			amostra.setaTulhaDestino(resultSet.getString("atulhadestino"));
      			amostra.setaRenda(resultSet.getInt("arenda"));
      			amostra.setaBebida(resultSet.getString("abebida"));
      			amostra.setaVariedade(resultSet.getString("avariedade"));
      			amostra.setaTipoControle(resultSet.getBoolean("atipocontrole"));
      			amostras.add(amostra);
      		}
      		*/
      	} catch (Exception e) {
      		System.out.println("Erro: " + e.getMessage());
      		e.printStackTrace();
      	} finally {
      		System.out.println("Disconnected from PostgreSQL!!");
      		AcessoDB.desconectar(connection, preparedStatement, resultSet);
      	}
      	return tblModel;
      }
      
      
      //SELECT_SECADOR_MÊS
      public TableModel selectSecadorMes(String secadorId) {
      	//ArrayList<Amostra> amostras = new ArrayList<Amostra>();
      	//Amostra amostra = null;
      	TableModel tblModel = null;
      	try {
      		connection = AcessoDB.conectar();
      		preparedStatement = connection.prepareStatement(SELECT_SECADOR_MES);
      		preparedStatement.setString(1, secadorId);
      		resultSet = preparedStatement.executeQuery();
      		tblModel = DbUtils.resultSetToTableModel(resultSet);
      		/*
      		while(resultSet.next()) {
      			amostra = new Amostra();  			
      			amostra.setSecador_sId(resultSet.getInt("secador_sid"));
      			amostra.setLote_lNome(resultSet.getString("lote_lnome"));
      			amostra.setaTemperaturaEntrada(resultSet.getInt("atemperaturaentrada"));
      			amostra.setaTemperaturaMassa(resultSet.getInt("atemperaturamassa"));
      			amostra.setaUmidade(resultSet.getInt("aumidade"));
      			amostra.setaData(resultSet.getDate("adata"));
      			amostra.setaHora(resultSet.getTime("ahora"));
      			amostra.setaTulhaDestino(resultSet.getString("atulhadestino"));
      			amostra.setaRenda(resultSet.getInt("arenda"));
      			amostra.setaBebida(resultSet.getString("abebida"));
      			amostra.setaVariedade(resultSet.getString("avariedade"));
      			amostra.setaTipoControle(resultSet.getBoolean("atipocontrole"));
      			amostras.add(amostra);
      		}
      		*/
      	} catch (Exception e) {
      		System.out.println("Erro: " + e.getMessage());
      		e.printStackTrace();
      	} finally {
      		System.out.println("Disconnected from PostgreSQL!!");
      		AcessoDB.desconectar(connection, preparedStatement, resultSet);
      	}
      	return tblModel;
      }
      
      
      //SELECT_SECADOR_PERÍODO
      public TableModel selectSecadorPeriodo(String secadorId, String dataInicio, String dataFim) {
      	//ArrayList<Amostra> amostras = new ArrayList<Amostra>();
      	//Amostra amostra = null;
      	TableModel tblModel = null;
      	try {
      		connection = AcessoDB.conectar();
      		preparedStatement = connection.prepareStatement(SELECT_SECADOR_PERIODO);
      		preparedStatement.setString(1, dataInicio);
      		preparedStatement.setString(2, dataFim);
      		preparedStatement.setString(3, secadorId);
      		System.out.println(preparedStatement);
      		resultSet = preparedStatement.executeQuery();
      		tblModel = DbUtils.resultSetToTableModel(resultSet);
      		/*
      		while(resultSet.next()) {
      			amostra = new Amostra();  			
      			amostra.setSecador_sId(resultSet.getInt("secador_sid"));
      			amostra.setLote_lNome(resultSet.getString("lote_lnome"));
      			amostra.setaTemperaturaEntrada(resultSet.getInt("atemperaturaentrada"));
      			amostra.setaTemperaturaMassa(resultSet.getInt("atemperaturamassa"));
      			amostra.setaUmidade(resultSet.getInt("aumidade"));
      			amostra.setaData(resultSet.getDate("adata"));
      			amostra.setaHora(resultSet.getTime("ahora"));
      			amostra.setaTulhaDestino(resultSet.getString("atulhadestino"));
      			amostra.setaRenda(resultSet.getInt("arenda"));
      			amostra.setaBebida(resultSet.getString("abebida"));
      			amostra.setaVariedade(resultSet.getString("avariedade"));
      			amostra.setaTipoControle(resultSet.getBoolean("atipocontrole"));
      			amostras.add(amostra);
      		}
      		*/
      	} catch (Exception e) {
      		System.out.println("Erro: " + e.getMessage());
      		e.printStackTrace();
      	} finally {
      		System.out.println("Disconnected from PostgreSQL!!");
      		AcessoDB.desconectar(connection, preparedStatement, resultSet);
      	}
      	return tblModel;
      }
      
  
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%    
//************************************************************************************************************************
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%	    
			
			  
		  	/* 
	    	 * INSERTS - TIPO DE INSERÇÃO: "AMOSTRA"
	     	 */
			        
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			        
	    	/* 
	    	 * QUERIES CONSTANTES PARA MANIPULAÇÃO DA DB
	    	 */    
			      
			      
      	//BKP!
      	//private final String INSERT = "SET \"synchronous_commit\" = off;INSERT INTO amostra(secador_sid, lote_lnome, atemperaturaentrada, atemperaturamassa, aumidade, adata, ahora, atulhadestino, arenda, abebida, avariedade, atipocontrole) VALUES (?, ?, ?, ?, ?, to_date(?, 'YYYY-MM-dd'), to_timestamp(?, 'hh24:mi:ss'), ?, ?, ?, ?, ?)";
      	private final String INSERT = "SET \"synchronous_commit\" = off;INSERT INTO amostra(secador_sid, lote_lnome, atemperaturaentrada, atemperaturamassa, aumidade, adata, ahora, atulhadestino, arenda, abebida, avariedade, atipocontrole, asetpoint, ausuarionome) VALUES (?, ?, ?, ?, ?, to_date(?, 'YYYY-MM-dd'), to_timestamp(?, 'hh24:mi:ss'), ?, ?, ?, ?, ?, ?, ?)";
      	//BKP!
		//private final String INSERT_INCOMPLETO = "INSERT INTO amostra(secador_sid, lote_lnome, atemperaturaentrada, atemperaturamassa, aumidade, adata, ahora, atulhadestino, arenda, abebida, avariedade, atipocontrole) VALUES (?, 'Aguardando Finalizacao', ?, ?, ?, to_date(?, 'YYYY-MM-dd'), to_timestamp(?, 'hh24:mi:ss'), 'Aguardando Finalizacao', 0, 'Aguardando Finalizacao', 'Aguardando Finalizacao', ?)";
      	private final String INSERT_INCOMPLETO = "SET \"synchronous_commit\" = off;INSERT INTO amostra(secador_sid, lote_lnome, atemperaturaentrada, atemperaturamassa, aumidade, adata, ahora, atulhadestino, arenda, abebida, avariedade, atipocontrole) VALUES (?, 'Aguardando Finalizacao', ?, ?, ?, to_date(?, 'YYYY-MM-dd'), to_timestamp(?, 'hh24:mi:ss'), 'Aguardando Finalizacao', 0, 'Aguardando Finalizacao', 'Aguardando Finalizacao', ?)";
	    
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////      
  
	    
	    //INSERT
	    public void insertCompleto(int secador_sid, String lote_lnome, int aTemperaturaEntrada, int aTemperaturaMassa, int aUmidade, String aData, String aHora, String aTulhaDestino, String aRenda, String aBebida, String aVariedade, String aTipoControle, int aSetPoint, String aUsuarioNome) {
		    	
	    	try {
	    		
	    		connection = AcessoDB.conectar();
	    		preparedStatement = connection.prepareStatement(INSERT);
	    		preparedStatement.setInt(1, secador_sid);
	    		preparedStatement.setString(2, lote_lnome);
	    		preparedStatement.setInt(3, aTemperaturaEntrada);
	    		preparedStatement.setInt(4, aTemperaturaMassa);
	    		preparedStatement.setInt(5, aUmidade);
	    		preparedStatement.setString(6, aData);
	    		preparedStatement.setString(7, aHora);
	    		preparedStatement.setString(8, aTulhaDestino);
	    		preparedStatement.setString(9, aRenda);
	    		preparedStatement.setString(10, aBebida);
	    		preparedStatement.setString(11, aVariedade);
	    		preparedStatement.setString(12, aTipoControle);
	    		///
	    		preparedStatement.setInt(13, aSetPoint);
	    		preparedStatement.setString(14, aUsuarioNome);
	    		///
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
	    
	    
	    //INSERT_INCOMPLETO [PARA COLETAR AMOSTRAS NA TELA: GERENCIAMENTO RELATÓRIOS FRAME]
	    public void insertIncompleto(int secador_sid, int aTemperaturaEntrada, int aTemperaturaMassa, int aUmidade, String aData, String aHora, String aTipoControle) {
		    	
	    	try {
	    		
	    		connection = AcessoDB.conectar();
	    		preparedStatement = connection.prepareStatement(INSERT_INCOMPLETO);
	    		preparedStatement.setInt(1, secador_sid);
	    		preparedStatement.setInt(2, aTemperaturaEntrada);
	    		preparedStatement.setInt(3, aTemperaturaMassa);
	    		preparedStatement.setInt(4, aUmidade);
	    		preparedStatement.setString(5, aData);
	    		preparedStatement.setString(6, aHora);
	    		preparedStatement.setString(7, aTipoControle);
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
	    
}
