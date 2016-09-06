package com.campotech.sgsoft.model.entity;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;

public class Amostra {

	//ATRIBUTOS
	private Integer aId;
	private String aNome;
	private Integer aTemperaturaEntrada;
	private Integer aTemperaturaMassa;
	private Date aData;
	//private Calendar aData;
	private Time aHora;
	//private Calendar aHora;
	private Integer aRenda;
	private String aBebida;
	private String aVariedade;
	private Integer aUmidade;
	private boolean aTipoControle;
	private String aTulhaDestino;
	private Integer Secador_sId;
	private Integer Lote_lId;
	private String Lote_lNome;
	
	
	//CONSTRUTORES
	public Amostra() {
		super();
		this.aId = null;
		this.aNome = null;
		this.aTemperaturaEntrada = null;
		this.aTemperaturaMassa = null;
		this.aData = null;
		this.aHora = null;
		this.aRenda = null;
		this.aBebida = null;
		this.aVariedade = null;
		this.aUmidade = null;
		this.aTipoControle = false; //False = Entrada ; True = Massa
		this.aTulhaDestino = null;
		this.Secador_sId = null;
		this.Lote_lId = null;
		this.Lote_lNome = null;
	}


	public Amostra(Integer aId, String aNome, Integer aTemperaturaEntrada,
			Integer aTemperaturaMassa, Date aData, Time aHora,
			Integer aRenda, String aBebida, String aVariedade,
			Integer aUmidade, boolean aTipoControle, String aTulhaDestino,
			Integer secador_sId, Integer lote_lId, String lote_lNome) {
		super();
		this.aId = aId;
		this.aNome = aNome;
		this.aTemperaturaEntrada = aTemperaturaEntrada;
		this.aTemperaturaMassa = aTemperaturaMassa;
		this.aData = aData;
		this.aHora = aHora;
		this.aRenda = aRenda;
		this.aBebida = aBebida;
		this.aVariedade = aVariedade;
		this.aUmidade = aUmidade;
		this.aTipoControle = aTipoControle;
		this.aTulhaDestino = aTulhaDestino;
		this.Secador_sId = secador_sId;
		this.Lote_lId = lote_lId;
		this.Lote_lNome = lote_lNome;
	}


	//GETTERS E SETTERS
	public Integer getaId() {
		return aId;
	}

	public void setaId(Integer aId) {
		this.aId = aId;
	}
	
	public String getaNome() {
		return aNome;
	}

	public void setaNome(String aNome) {
		this.aNome = aNome;
	}

	public Integer getaTemperaturaEntrada() {
		return aTemperaturaEntrada;
	}

	public void setaTemperaturaEntrada(Integer aTemperaturaEntrada) {
		this.aTemperaturaEntrada = aTemperaturaEntrada;
	}

	public Integer getaTemperaturaMassa() {
		return aTemperaturaMassa;
	}

	public void setaTemperaturaMassa(Integer aTemperaturaMassa) {
		this.aTemperaturaMassa = aTemperaturaMassa;
	}

	public Date getaData() {
		return aData;
	}

	public void setaData(Date aData) {
		this.aData = aData;
	}

	public Time getaHora() {
		return aHora;
	}

	public void setaHora(Time aHora) {
		this.aHora = aHora;
	}

	public Integer getaRenda() {
		return aRenda;
	}

	public void setaRenda(Integer aRenda) {
		this.aRenda = aRenda;
	}

	public String getaBebida() {
		return aBebida;
	}

	public void setaBebida(String aBebida) {
		this.aBebida = aBebida;
	}

	public String getaVariedade() {
		return aVariedade;
	}

	public void setaVariedade(String aVariedade) {
		this.aVariedade = aVariedade;
	}

	public Integer getaUmidade() {
		return aUmidade;
	}

	public void setaUmidade(Integer aUmidade) {
		this.aUmidade = aUmidade;
	}

	public boolean isaTipoControle() {
		return aTipoControle;
	}

	public void setaTipoControle(boolean aTipoControle) {
		this.aTipoControle = aTipoControle;
	}

	public String getaTulhaDestino() {
		return aTulhaDestino;
	}

	public void setaTulhaDestino(String aTulhaDestino) {
		this.aTulhaDestino = aTulhaDestino;
	}

	public Integer getSecador_sId() {
		return Secador_sId;
	}

	public void setSecador_sId(Integer secador_sId) {
		Secador_sId = secador_sId;
	}

	public Integer getLote_lId() {
		return Lote_lId;
	}

	public void setLote_lId(Integer lote_lId) {
		Lote_lId = lote_lId;
	}

	public String getLote_lNome() {
		return Lote_lNome;
	}

	public void setLote_lNome(String lote_lNome) {
		this.Lote_lNome = lote_lNome;
	}
	
	//TO STRING
	@Override
	public String toString() {
		return "Amostra [aId=" + aId + ", aTemperaturaEntrada="
				+ aTemperaturaEntrada + ", aTemperaturaMassa="
				+ aTemperaturaMassa + ", aData=" + aData + ", aHora=" + aHora
				+ ", aRenda=" + aRenda + ", aBebida=" + aBebida
				+ ", aVariedade=" + aVariedade + ", aUmidade=" + aUmidade
				+ ", aTipoControle=" + aTipoControle + ", aTulhaDestino="
				+ aTulhaDestino + ", Secador_sId=" + Secador_sId
				+ ", Lote_lId=" + Lote_lId + "]";
	}			
}
