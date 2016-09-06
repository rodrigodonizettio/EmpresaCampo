package com.campotech.sgsoft.model.entity;

import java.math.BigInteger;

public class Receita {

	//ATRIBUTOS
	private Integer rId;
	private String rNome;
	private Integer rNumeroEtapas;
	private Integer rTemperaturaMaximaEntrada1;
	private Integer rTemperaturaMaximaEntrada2;
	private Integer rTemperaturaMaximaEntrada3;
	private Integer rTemperaturaMaximaMassa1;
	private Integer rTemperaturaMaximaMassa2;
	private Integer rTemperaturaMaximaMassa3;
	private Integer rTempoEtapa1;
	private Integer rTempoEtapa2;
	private Integer rTempoEtapa3;
	private boolean rTipoControleEtapa1;
	private boolean rTipoControleEtapa2;
	private boolean rTipoControleEtapa3;
	
	
	//CONSTRUTORES
	public Receita() {
		super();
		this.rId = null;
		this.rNome = null;
		this.rNumeroEtapas = null;
		this.rTemperaturaMaximaEntrada1 = null;
		this.rTemperaturaMaximaEntrada2 = null;
		this.rTemperaturaMaximaEntrada3 = null;
		this.rTemperaturaMaximaMassa1 = null;
		this.rTemperaturaMaximaMassa2 = null;
		this.rTemperaturaMaximaMassa3 = null;
		this.rTempoEtapa1 = null;
		this.rTempoEtapa2 = null;
		this.rTempoEtapa3 = null;
		this.rTipoControleEtapa1 = false;
		this.rTipoControleEtapa2 = false;
		this.rTipoControleEtapa3 = false;
	}
	
	public Receita(Integer rId, String rNome, Integer rNumeroEtapas,
			Integer rTemperaturaMaximaEntrada1,
			Integer rTemperaturaMaximaEntrada2,
			Integer rTemperaturaMaximaEntrada3,
			Integer rTemperaturaMaximaMassa1, Integer rTemperaturaMaximaMassa2,
			Integer rTemperaturaMaximaMassa3, Integer rTempoEtapa1,
			Integer rTempoEtapa2, Integer rTempoEtapa3, boolean rTipoControleEtapa1, boolean rTipoControleEtapa2, boolean rTipoControleEtapa3) {
		
		super();
		this.rId = rId;
		this.rNome = rNome;
		this.rNumeroEtapas = rNumeroEtapas;
		this.rTemperaturaMaximaEntrada1 = rTemperaturaMaximaEntrada1;
		this.rTemperaturaMaximaEntrada2 = rTemperaturaMaximaEntrada2;
		this.rTemperaturaMaximaEntrada3 = rTemperaturaMaximaEntrada3;
		this.rTemperaturaMaximaMassa1 = rTemperaturaMaximaMassa1;
		this.rTemperaturaMaximaMassa2 = rTemperaturaMaximaMassa2;
		this.rTemperaturaMaximaMassa3 = rTemperaturaMaximaMassa3;
		this.rTempoEtapa1 = rTempoEtapa1;
		this.rTempoEtapa2 = rTempoEtapa2;
		this.rTempoEtapa3 = rTempoEtapa3;
		this.rTipoControleEtapa1 = rTipoControleEtapa1;
		this.rTipoControleEtapa2 = rTipoControleEtapa2;
		this.rTipoControleEtapa3 = rTipoControleEtapa3;
	}
	
	
	//GETTERS E SETTERS
	public Integer getrId() {
		return rId;
	}
	
	public void setrId(Integer rId) {
		this.rId = rId;
	}
	
	public String getrNome() {
		return rNome;
	}
	
	public void setrNome(String rNome) {
		this.rNome = rNome;
	}
	
	public Integer getrNumeroEtapas() {
		return rNumeroEtapas;
	}
	
	public void setrNumeroEtapas(Integer rNumeroEtapas) {
		this.rNumeroEtapas = rNumeroEtapas;
	}
	
	public Integer getrTemperaturaMaximaEntrada1() {
		return rTemperaturaMaximaEntrada1;
	}
	
	public void setrTemperaturaMaximaEntrada1(Integer rTemperaturaMaximaEntrada1) {
		this.rTemperaturaMaximaEntrada1 = rTemperaturaMaximaEntrada1;
	}
	
	public Integer getrTemperaturaMaximaEntrada2() {
		return rTemperaturaMaximaEntrada2;
	}
	
	public void setrTemperaturaMaximaEntrada2(Integer rTemperaturaMaximaEntrada2) {
		this.rTemperaturaMaximaEntrada2 = rTemperaturaMaximaEntrada2;
	}
	
	public Integer getrTemperaturaMaximaEntrada3() {
		return rTemperaturaMaximaEntrada3;
	}
	
	public void setrTemperaturaMaximaEntrada3(Integer rTemperaturaMaximaEntrada3) {
		this.rTemperaturaMaximaEntrada3 = rTemperaturaMaximaEntrada3;
	}
	
	public Integer getrTemperaturaMaximaMassa1() {
		return rTemperaturaMaximaMassa1;
	}
	
	public void setrTemperaturaMaximaMassa1(Integer rTemperaturaMaximaMassa1) {
		this.rTemperaturaMaximaMassa1 = rTemperaturaMaximaMassa1;
	}
	
	public Integer getrTemperaturaMaximaMassa2() {
		return rTemperaturaMaximaMassa2;
	}
	
	public void setrTemperaturaMaximaMassa2(Integer rTemperaturaMaximaMassa2) {
		this.rTemperaturaMaximaMassa2 = rTemperaturaMaximaMassa2;
	}
	
	public Integer getrTemperaturaMaximaMassa3() {
		return rTemperaturaMaximaMassa3;
	}
	
	public void setrTemperaturaMaximaMassa3(Integer rTemperaturaMaximaMassa3) {
		this.rTemperaturaMaximaMassa3 = rTemperaturaMaximaMassa3;
	}
	
	public Integer getrTempoEtapa1() {
		return rTempoEtapa1;
	}
	
	public void setrTempoEtapa1(Integer rTempoEtapa1) {
		this.rTempoEtapa1 = rTempoEtapa1;
	}
	
	public Integer getrTempoEtapa2() {
		return rTempoEtapa2;
	}
	
	public void setrTempoEtapa2(Integer rTempoEtapa2) {
		this.rTempoEtapa2 = rTempoEtapa2;
	}
	
	public Integer getrTempoEtapa3() {
		return rTempoEtapa3;
	}
	
	public void setrTempoEtapa3(Integer rTempoEtapa3) {
		this.rTempoEtapa3 = rTempoEtapa3;
	}
	
	public boolean getrTipoControleEtapa1() {
		return rTipoControleEtapa1;
	}
	
	public void setrTipoControleEtapa1(boolean rTipoControleEtapa1) {
		this.rTipoControleEtapa1 = rTipoControleEtapa1;
	}
	
	public boolean getrTipoControleEtapa2() {
		return rTipoControleEtapa2;
	}
	
	public void setrTipoControleEtapa2(boolean rTipoControleEtapa2) {
		this.rTipoControleEtapa2 = rTipoControleEtapa2;
	}
		
	public boolean getrTipoControleEtapa3() {
		return rTipoControleEtapa3;
	}
	
	public void setrTipoControleEtapa3(boolean rTipoControleEtapa3) {
		this.rTipoControleEtapa3 = rTipoControleEtapa3;
	}
	
	//TO STRING
	@Override
	public String toString() {
		return "Receita [rId=" + rId + ", rNome=" + rNome + ", rNumeroEtapas="
				+ rNumeroEtapas + ", rTemperaturaMaximaEntrada1="
				+ rTemperaturaMaximaEntrada1 + ", rTemperaturaMaximaEntrada2="
				+ rTemperaturaMaximaEntrada2 + ", rTemperaturaMaximaEntrada3="
				+ rTemperaturaMaximaEntrada3 + ", rTemperaturaMaximaMassa1="
				+ rTemperaturaMaximaMassa1 + ", rTemperaturaMaximaMassa2="
				+ rTemperaturaMaximaMassa2 + ", rTemperaturaMaximaMassa3="
				+ rTemperaturaMaximaMassa3 + ", rTempoEtapa1=" + rTempoEtapa1
				+ ", rTempoEtapa2=" + rTempoEtapa2 + ", rTempoEtapa3="
				+ rTempoEtapa3 + ", rTipoControleEtapa1=" + rTipoControleEtapa1 + ", rTipoControleEtapa2=" + rTipoControleEtapa2 + ", rTipoControleEtapa3=" + rTipoControleEtapa3 + "]";
	}	
	
}
