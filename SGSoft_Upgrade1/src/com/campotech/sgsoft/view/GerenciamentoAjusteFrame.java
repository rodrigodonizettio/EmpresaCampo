package com.campotech.sgsoft.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.Dialog.ModalExclusionType;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.campotech.sgsoft.controller.listener.GerenciamentoAjusteListener;
import com.campotech.sgsoft.controller.listener.GerenciamentoRelatoriosListener;
import com.campotech.sgsoft.controller.listener.SobreListener;

import java.awt.Color;
import java.awt.Font;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Component;
import javax.swing.SwingConstants;

public class GerenciamentoAjusteFrame extends JFrame {

	//ATRIBUTOS PERTENCENTES AO FRAME
	private JPanel contentPane;
	private JLabel lblSecador;
	private JLabel lblAjustes;
	private JLabel lblNovoNumeroSecador;
	private JComboBox cmbbxNumeroSecador;
	private JLabel lblDataSistema;
	private JLabel lblValorDataSistema;
	private JLabel lblHoraSistema;
	private JLabel lblValorHoraSistema;
	private JButton btnSincronizar;
	private JLabel lblSincronizar;
	
	
	//ATRIBUTOS PERTENCENTES AO LISTENER
	private GerenciamentoAjusteListener gerenciamentoAjusteListener;
	
	
	//ATRIBUTOS DE IMAGENS 
	URL urlIconCampotech = GerenciamentoRelatoriosFrame.class.getResource("images/icon-campotech.png");
	
	
	//ATRIBUTOS PERTENCENTES ÀS CONVERSÕES DE FORMATO
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	private SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
	
	
	//ATRIBUTOS DE CAPTURA DO OBJETO QUE CRIOU ESTE FRAME
	private int numeroSecadorAtual = 0;
	
	
	//CONSTRUTOR
	public GerenciamentoAjusteFrame() {
		
		setTitle("Tela - Gerenciamento de Ajuste");
		//<CRIANDO ICONE PARA APLICAÇÃO
		setIconImage(Toolkit.getDefaultToolkit().getImage(urlIconCampotech).getScaledInstance(32, 32, java.awt.Image.SCALE_SMOOTH));
		//CRIANDO ICONE PARA APLICAÇÃO>
		setBounds(100, 100, 335, 230);
		//<POSICIONANDO FRAME NO CENTRO DA TELA DO COMPUTADOR
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(screenSize.width/2 - this.getSize().width/2, screenSize.height/2 - this.getSize().height/2);
		//POSICIONANDO FRAME NO CENTRO DA TELA DO COMPUTADOR>
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				cmbbxNumeroSecador.requestFocus();
			}
		});
		
		
		lblSecador = new JLabel();
		lblSecador.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSecador.setBounds(125, 5, 90, 20);
		contentPane.add(lblSecador);
		
			lblAjustes = new JLabel();
			lblAjustes.setBounds(5, 35, 318, 165);
			lblAjustes.setBorder(new TitledBorder(null, "Ajustes", TitledBorder.CENTER, TitledBorder.TOP, null, null));
			contentPane.add(lblAjustes);
			
			lblNovoNumeroSecador = new JLabel();
			lblNovoNumeroSecador.setBounds(70, 20, 150, 20);
			lblNovoNumeroSecador.setText("Novo Número do Secador:");
			lblAjustes.add(lblNovoNumeroSecador);
			
			cmbbxNumeroSecador = new JComboBox();			
			cmbbxNumeroSecador.setBounds(220, 15, 50, 30);
			cmbbxNumeroSecador.addKeyListener(new KeyAdapter() {
				
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode() == KeyEvent.VK_ENTER) {
						gerenciamentoAjusteListener.enterButtonPressed();
					}
				}
			});
			
			cmbbxNumeroSecador.addMouseListener(new MouseAdapter() {
				
				@Override
				public void mouseEntered(MouseEvent arg0) {
					
					gerenciamentoAjusteListener.cmbbxNumeroSecadorMouseEntered();
					
				}
			});
			
			lblAjustes.add(cmbbxNumeroSecador);
			
			lblDataSistema = new JLabel();
			lblDataSistema.setBounds(15, 60, 95, 20);
			lblDataSistema.setText("Data do Sistema:");
			lblAjustes.add(lblDataSistema);
			
				lblValorDataSistema = new JLabel();
				lblValorDataSistema.setBounds(115, 60, 95, 20);
				lblValorDataSistema.setForeground(Color.BLUE);
				lblValorDataSistema.setText("DD/MM/AAAA");
				lblAjustes.add(lblValorDataSistema);
			
			lblHoraSistema = new JLabel();
			lblHoraSistema.setBounds(15, 80, 100, 20);
			lblHoraSistema.setText("Hora do Sistema:");
			lblAjustes.add(lblHoraSistema);
			
				lblValorHoraSistema = new JLabel();
				lblValorHoraSistema.setBounds(115, 80, 95, 20);
				lblValorHoraSistema.setForeground(Color.BLUE);
				lblValorHoraSistema.setText("HH:MM:SS");
				lblAjustes.add(lblValorHoraSistema);
			
			btnSincronizar = new JButton();
			btnSincronizar.setBounds(80, 105, 150, 30);
			btnSincronizar.setText("Sincronizar Dados");
			
			btnSincronizar.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent arg0) {
					
					gerenciamentoAjusteListener.btnSincronizarClicked();
					
				}
				
			});
			
			btnSincronizar.addKeyListener(new KeyAdapter() {
				
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode() == KeyEvent.VK_ENTER) {
						gerenciamentoAjusteListener.enterButtonPressed();
					}
				}
			});
			
			lblAjustes.add(btnSincronizar);
			
			lblSincronizar = new JLabel();
			lblSincronizar.setHorizontalAlignment(SwingConstants.CENTER);
			lblSincronizar.setBounds(64, 135, 180, 20);
			lblSincronizar.setVisible(false);
			lblSincronizar.setForeground(Color.RED);
			lblAjustes.add(lblSincronizar);
			
	}
	
////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////
//GETTERS
//TODO - GETTERS
////////////////////////////////////////////////////////////////////	
	
	public int getCmbbxNumeroSecadorSelectedItem() {
		
		return Integer.parseInt((String)cmbbxNumeroSecador.getSelectedItem());
		
	}
	
	public String getLblSecadorText() {
		
		return lblSecador.getText();
		
	}
	
	public String getLblValorDataSistemaText() {
		
		return lblValorDataSistema.getText();
		
	}
	
	public String getLblValorHoraSistemaText() {
		
		return lblValorHoraSistema.getText();
		
	}
	
////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////
//SETTERS
//TODO - SETTERS
////////////////////////////////////////////////////////////////////
	
	public void setLblSecadorText(String numeroSecadorTexto) {
		
		lblSecador.setText(numeroSecadorTexto);
		
		if(numeroSecadorTexto.equals("Secador - 1")) {
			numeroSecadorAtual = 1;
		} else if(numeroSecadorTexto.equals("Secador - 2")) {
			numeroSecadorAtual = 2;
		} else if(numeroSecadorTexto.equals("Secador - 3")) {
			numeroSecadorAtual = 3;
		} else if(numeroSecadorTexto.equals("Secador - 4")) {
			numeroSecadorAtual = 4;
		} else if(numeroSecadorTexto.equals("Secador - 5")) {
			numeroSecadorAtual = 5;
		} else if(numeroSecadorTexto.equals("Secador - 6")) {
			numeroSecadorAtual = 6;
		} else if(numeroSecadorTexto.equals("Secador - 7")) {
			numeroSecadorAtual = 7;
		} else if(numeroSecadorTexto.equals("Secador - 8")) {
			numeroSecadorAtual = 8;
		} else if(numeroSecadorTexto.equals("Secador - 9")) {
			numeroSecadorAtual = 9;
		} else if(numeroSecadorTexto.equals("Secador - 10")) {
			numeroSecadorAtual = 10;
		} else if(numeroSecadorTexto.equals("Secador - 11")) {
			numeroSecadorAtual = 11;
		} else if(numeroSecadorTexto.equals("Secador - 12")) {
			numeroSecadorAtual = 12;
		} else if(numeroSecadorTexto.equals("Secador - 13")) {
			numeroSecadorAtual = 13;
		} else if(numeroSecadorTexto.equals("Secador - 14")) {
			numeroSecadorAtual = 14;
		} else if(numeroSecadorTexto.equals("Secador - 15")) {
			numeroSecadorAtual = 15;
		} else if(numeroSecadorTexto.equals("Secador - 16")) {
			numeroSecadorAtual = 16;
		} else if(numeroSecadorTexto.equals("Secador - 17")) {
			numeroSecadorAtual = 17;
		} else if(numeroSecadorTexto.equals("Secador - 18")) {
			numeroSecadorAtual = 18;
		} else if(numeroSecadorTexto.equals("Secador - 19")) {
			numeroSecadorAtual = 19;
		} else if(numeroSecadorTexto.equals("Secador - 20")) {
			numeroSecadorAtual = 20;
		} else if(numeroSecadorTexto.equals("Secador - 21")) {
			numeroSecadorAtual = 21;
		} else if(numeroSecadorTexto.equals("Secador - 22")) {
			numeroSecadorAtual = 22;
		} else if(numeroSecadorTexto.equals("Secador - 23")) {
			numeroSecadorAtual = 23;
		} else if(numeroSecadorTexto.equals("Secador - 24")) {
			numeroSecadorAtual = 24;
		} else if(numeroSecadorTexto.equals("Secador - 25")) {
			numeroSecadorAtual = 25;
		} else if(numeroSecadorTexto.equals("Secador - 26")) {
			numeroSecadorAtual = 26;
		} else if(numeroSecadorTexto.equals("Secador - 27")) {
			numeroSecadorAtual = 27;
		} else if(numeroSecadorTexto.equals("Secador - 28")) {
			numeroSecadorAtual = 28;
		} else if(numeroSecadorTexto.equals("Secador - 29")) {
			numeroSecadorAtual = 29;
		} else if(numeroSecadorTexto.equals("Secador - 30")) {
			numeroSecadorAtual = 30;
		}
		
	}
	
	public void setLblValorDataSistemaText() {
		Date data = new Date();
		lblValorDataSistema.setText(dateFormat.format(data));
	}
	
	public void setLblValorHoraSistemaText() {
		Date hora = new Date();
		lblValorHoraSistema.setText(hourFormat.format(hora));
	}
	
	public void setCmbbxNumeroSecadorRemoveAllItems() {
		
		cmbbxNumeroSecador.removeAllItems();
		
	}
	
	public void setCmbbxNumeroSecadorAddItem(String i) {
		
		cmbbxNumeroSecador.addItem(i);
		
	}
	
	public void setCmbbxNumeroSecadorEnabled(boolean enabled) {
		
		cmbbxNumeroSecador.setEnabled(enabled);
		
	}
	
	public void setBtnSincronizarEnabled(boolean enabled) {
		
		btnSincronizar.setEnabled(enabled);
		
	}
	
	public void setLblSincronizarVisible(boolean visible) {
		
		lblSincronizar.setVisible(visible);
		
	}
	
	public void setLblSincronizarText(String text) {
		
		lblSincronizar.setText(text);
		
	}
	
////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////
	
	
	//FAZ A LINKAGEM ENTRE O RUNNER E A VIEW [GERENCIAMENTORELATORIOS] POR INTERMÉDIO DO LISTENER [GERENCIAMENTORELATORIOS]
	public void addGerenciamentoAjusteListener(GerenciamentoAjusteListener gerenciamentoAjusteListener) {
		this.gerenciamentoAjusteListener = gerenciamentoAjusteListener;
	}

}
