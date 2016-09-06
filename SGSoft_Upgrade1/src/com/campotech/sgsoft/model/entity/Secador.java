package com.campotech.sgsoft.model.entity;

import java.math.BigInteger;

public class Secador {

	//ATRIBUTOS
	private Integer sId;
	private boolean sSaidaCilindro;
	private boolean sSaidaVentilador;
	private boolean sSaidaAuxiliar;
	
	
	//CONTRUTORES
	public Secador() {
		super();
		this.sId = null;
		this.sSaidaCilindro = false; //False = Desacionada; True = Acionada
		this.sSaidaVentilador = false; //False = Desacionada; True = Acionada
		this.sSaidaAuxiliar = false; //False = Desacionada; True = Acionada
	}

	public Secador(Integer sId, boolean sSaidaCilindro,
			boolean sSaidaVentilador, boolean sSaidaAuxiliar) {
		super();
		this.sId = sId;
		this.sSaidaCilindro = sSaidaCilindro;
		this.sSaidaVentilador = sSaidaVentilador;
		this.sSaidaAuxiliar = sSaidaAuxiliar;
	}
	
	
	//GETTERS E SETTERS
	public Integer getsId() {
		return sId;
	}
	
	public void setsId(Integer sId) {
		this.sId = sId;
	}
	
	public boolean getSSaidaCilindro() {
		return sSaidaCilindro;
	}
	
	public void setsSaidaCilindro(boolean sSaidaCilindro) {
		this.sSaidaCilindro = sSaidaCilindro;
	}
	
	public boolean getSSaidaVentilador() {
		return sSaidaVentilador;
	}
	
	public void setsSaidaVentilador(boolean sSaidaVentilador) {
		this.sSaidaVentilador = sSaidaVentilador;
	}
	
	public boolean getSSaidaAuxiliar() {
		return sSaidaAuxiliar;
	}
	
	public void setsSaidaAuxiliar(boolean sSaidaAuxiliar) {
		this.sSaidaAuxiliar = sSaidaAuxiliar;
	}

		
	//TO STRING
	@Override
	public String toString() {
		return "Secador [sId=" + sId + ", sSaidaCilindro=" + sSaidaCilindro
				+ ", sSaidaVentilador=" + sSaidaVentilador
				+ ", sSaidaAuxiliar=" + sSaidaAuxiliar + "]";
	}
		
}
