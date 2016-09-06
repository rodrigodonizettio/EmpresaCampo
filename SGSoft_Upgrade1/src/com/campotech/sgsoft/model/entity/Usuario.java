package com.campotech.sgsoft.model.entity;

import java.math.BigInteger;

public class Usuario {

	//ATRIBUTOS
	private Integer uId;
	private String uNome;
	private String uSenha;
	private boolean uNivel;
	
	
	//CONSTRUTORES
	public Usuario() {
		super();
		this.uId = null;
		this.uNome = null;
		this.uSenha = null;
		this.uNivel = true; //False = Operador ; True = Administrador 
	}
	
	public Usuario(Integer uId, String uNome, String uSenha, boolean uNivel) {
		super();
		this.uId = uId;
		this.uNome = uNome;
		this.uSenha = uSenha;
		this.uNivel = uNivel;
	}
	
	
	//GETTERS E SETTERS
	public Integer getuId() {
		return uId;
	}
	
	public void setuId(Integer uId) {
		this.uId = uId;
	}
	
	public String getuNome() {
		return uNome;
	}
	
	public void setuNome(String uNome) {
		this.uNome = uNome;
	}
	
	public String getuSenha() {
		return uSenha;
	}
	
	public void setuSenha(String uSenha) {
		this.uSenha = uSenha;
	}
	
	public boolean getuNivel() {
		return uNivel;
	}
	
	public void setuNivel(boolean uNivel) {
		this.uNivel = uNivel;
	}
	
		
	//TO STRING
	@Override
	public String toString() {
		return "Usuario [uId=" + uId + ", uNome=" + uNome + ", uSenha="
				+ uSenha + ", uNivel=" + uNivel + "]";
	}
}
