package com.campotech.sgsoft.model.entity;

import java.math.BigInteger;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Lote {

	//ATRIBUTOS
	private Integer lId;
	private String lNome;
	private String lNomeOrigem;
	private String lNomeDestino;
	private Date lDataInicio; //Calendar
	private Time lHoraInicio; //Calendar
	private Integer lTempoTerreiro;
	private Integer lEstado;
	private Integer lEtapa;
	private String lTempoSecagem; //Calendar
	private String lTempoDescanso; //Calendar
	private Integer Secador_sId;
	private Integer Receita_rId;
	private Date lHoraInicioDescanso;
	private String lBebida;
	private String lRenda;
	private String lTulhaDestino;
	private String lVariedade;
	
	
	//CONSTRUTORES
	public Lote() {
		super();
		this.lId = null;
		this.lNome = null;
		this.lNomeOrigem = null;
		this.lNomeDestino = null;
		this.lDataInicio = null;
		this.lHoraInicio = null;
		this.lTempoTerreiro = null;
		this.lEstado = null;
		this.lEtapa = null;
		this.lTempoSecagem = null;
		this.lTempoDescanso = null;
		this.Secador_sId = null;
		this.Receita_rId = null;
		this.lHoraInicioDescanso = null;
		this.lBebida = null;
		this.lRenda = null;
		this.lTulhaDestino = null;
		this.lVariedade = null;
		
	}

	public Lote(Integer lId, String lNome, String lNomeOrigem,
			String lNomeDestino, Date lDataInicio, Time lHoraInicio, //Calendar, Calendar
			Integer lTempoTerreiro, Integer lEstado, Integer lEtapa, String lTempoSecagem, //Calendar
			String lTempoDescanso, Integer secador_sId, Integer receita_rId, Date lHoraInicioDescanso, String lBebida, String lRenda, //Calendar
			String lTulhaDestino, String lVariedade) {
		
		super();
		this.lId = lId;
		this.lNome = lNome;
		this.lNomeOrigem = lNomeOrigem;
		this.lNomeDestino = lNomeDestino;
		this.lDataInicio = lDataInicio;
		this.lHoraInicio = lHoraInicio;
		this.lTempoTerreiro = lTempoTerreiro;
		this.lEstado = lEstado;
		this.lEtapa = lEtapa;
		this.lTempoSecagem = lTempoSecagem;
		this.lTempoDescanso = lTempoDescanso;
		this.Secador_sId = secador_sId;
		this.Receita_rId = receita_rId;
		this.lHoraInicioDescanso = lHoraInicioDescanso;
		this.lBebida = lBebida;
		this.lRenda = lRenda;
		this.lTulhaDestino = lTulhaDestino;
		this.lVariedade = lVariedade;
		
	}
	
	
	//GETTERS E SETTERS
	public Integer getlId() {
		return lId;
	}
	
	public void setlId(Integer lId) {
		this.lId = lId;
	}
	
	public String getlNome() {
		return lNome;
	}
	
	public void setlNome(String lNome) {
		this.lNome = lNome;
	}
	
	public String getlNomeOrigem() {
		return lNomeOrigem;
	}
	
	public void setlNomeOrigem(String lNomeOrigem) {
		this.lNomeOrigem = lNomeOrigem;
	}
	
	public String getlNomeDestino() {
		return lNomeDestino;
	}
	
	public void setlNomeDestino(String lNomeDestino) {
		this.lNomeDestino = lNomeDestino;
	}
	
	public Date getlDataInicio() {
		return lDataInicio;
	}
	
	//public void setlDataInicio(Calendar lDataInicio) {
	public void setlDataInicio(java.sql.Date lDataInicio) {
		this.lDataInicio = lDataInicio;
	}
	
	//public Calendar getlHoraInicio() {
	public Time getlHoraInicio() {
		return lHoraInicio;
	}
	
	public void setlHoraInicio(Time lHoraInicio) { //Calendar
		this.lHoraInicio = lHoraInicio;
	}
	
	public Integer getlTempoTerreiro() {
		return lTempoTerreiro;
	}
	
	public void setlTempoTerreiro(Integer lTempoTerreiro) {
		this.lTempoTerreiro = lTempoTerreiro;
	}
	
	public Integer getlEstado() {
		return lEstado;
	}
	
	public void setlEstado(Integer lEstado) {
		this.lEstado = lEstado;
	}
	
	public Integer getlEtapa() {
		return lEtapa;
	}
	
	public void setlEtapa(Integer lEtapa) {
		this.lEtapa = lEtapa;
	}
	
	//public Calendar getlTempoSecagem() {
	public String getlTempoSecagem() {
		return lTempoSecagem;
	}
	
	public void setlTempoSecagem(String lTempoSecagem) { //Calendar
		this.lTempoSecagem = lTempoSecagem;
	}
	
	//public Calendar getlTempoDescanso() {
	public String getlTempoDescanso() {
		return lTempoDescanso;
	}
	
	public void setlTempoDescanso(String lTempoDescanso) { //Calendar
		this.lTempoDescanso = lTempoDescanso;
	}
	
	public Integer getSecador_sId() {
		return Secador_sId;
	}
	
	public Integer getReceita_rId() {
		return Receita_rId;
	}
	
	public void setSecador_sId(Integer secador_sId) {
		Secador_sId = secador_sId;
	}
	
	public void setReceita_rId(Integer receita_rId) {
		Receita_rId = receita_rId;
	}
	
	public Date getlHoraInicioDescanso() {
		return lHoraInicioDescanso;
	}
	
	public void setlHoraInicioDescanso(Date lHoraInicioDescanso) {
		//SimpleDateFormat timeStampFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		this.lHoraInicioDescanso = lHoraInicioDescanso;
	}
	
	public String getlBebida() {
		return lBebida;
	}
	
	public void setlBebida(String lBebida) {
		this.lBebida = lBebida;
	}

	public String getlRenda() {
		return lRenda;
	}
	
	public void setlRenda(String lRenda) {
		this.lRenda = lRenda;
	}
	
	public String getlTulhaDestino() {
		return lTulhaDestino;
	}
	
	public void setlTulhaDestino(String lTulhaDestino) {
		this.lTulhaDestino = lTulhaDestino;
	}
	
	public String getlVariedade() {
		return lVariedade;
	}
	
	public void setlVariedade(String lVariedade) {
		this.lVariedade = lVariedade;
	}
	
	
	//TO STRING
	@Override
	public String toString() {
		return "Lote [lId=" + lId + ", lNome=" + lNome + ", lNomeOrigem="
				+ lNomeOrigem + ", lNomeDestino=" + lNomeDestino
				+ ", lDataInicio=" + lDataInicio + ", lHoraInicio="
				+ lHoraInicio + ", lTempoTerreiro=" + lTempoTerreiro
				+ ", lEstado=" + lEstado + ", lEtapa=" + lEtapa + ", lTempoSecagem=" + lTempoSecagem
				+ ", lTempoDescanso=" + lTempoDescanso + ", Secador_sId="
				+ Secador_sId + ", Receita_rId=" + Receita_rId + ", lHoraInicioDescanso=" + lHoraInicioDescanso
				+ ", lBebida=" + lBebida + ", lRenda=" + lRenda + ", lTulhaDestino=" + lTulhaDestino
				+ ", lVariedade=" + lVariedade + "]";
	}
	
}
