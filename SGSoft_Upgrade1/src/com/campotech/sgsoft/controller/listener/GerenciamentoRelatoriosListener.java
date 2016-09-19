package com.campotech.sgsoft.controller.listener;

public interface GerenciamentoRelatoriosListener {
	
	public void btnColetarAmostrasRelatorioClicked();
	public void btnExportarPDFClicked();
	public void btnExportarXLSClicked();
	
	public void rdbtnBuscaTudoClicked();
	public void rdbtnBuscaLoteClicked();
	public void rdbtnBuscaSecadorClicked();
	
	public void rdbtnBuscaDiaClicked();
	public void rdbtnBuscaSemanaClicked();
	public void rdbtnBuscaMesClicked();
	public void rdbtnBuscaPeriodoClicked();
	
	public void btnPesquisarAmostrasClicked();
	
	public void txfLoteClicked();
	
	public void cmbbxSecadorMouseEntered();
	
}
