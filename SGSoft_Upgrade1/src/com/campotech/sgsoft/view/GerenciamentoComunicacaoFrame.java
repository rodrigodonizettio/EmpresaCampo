package com.campotech.sgsoft.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.Dialog.ModalExclusionType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.campotech.sgsoft.controller.listener.GerenciamentoComunicacaoListener;
import com.campotech.sgsoft.controller.listener.LoginListener;

import java.awt.Color;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GerenciamentoComunicacaoFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	//ATRIBUTOS PERTENCENTES AO FRAME
	private JPanel contentPane;
	private JLabel lblPortaCOM;
	private JComboBox cmbbxPortaCOM;
	private JButton btnOk;
	
	
	//ATRIBUTOS PERTENCENTES AO LISTENER
	private GerenciamentoComunicacaoListener gerenciamentoComunicacaoListener;

	
	//ATRIBUTOS DE IMAGENS 
	URL urlIconCampotech = GerenciamentoRelatoriosFrame.class.getResource("images/icon-campotech.png");
	
	
	/**
	 * Create the frame.
	 */
	public GerenciamentoComunicacaoFrame() {
		setTitle("Tela - Gerenciamento de Comunicação");
		//<CRIANDO ICONE PARA APLICAÇÃO
		setIconImage(Toolkit.getDefaultToolkit().getImage(urlIconCampotech).getScaledInstance(32, 32, java.awt.Image.SCALE_SMOOTH));
		//CRIANDO ICONE PARA APLICAÇÃO>
		setBounds(100, 100, 380, 105);
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
		
		lblPortaCOM = new JLabel();
		lblPortaCOM.setBounds(115, 10, 65, 20);
		lblPortaCOM.setText("Porta COM:");
		contentPane.add(lblPortaCOM);
		
		cmbbxPortaCOM = new JComboBox();		
		cmbbxPortaCOM.setBounds(180, 5, 85, 30);
		
		cmbbxPortaCOM.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				
				gerenciamentoComunicacaoListener.cmbbxPortaCOMMouseEntered();			
				
			}
			
		});
		
		cmbbxPortaCOM.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					gerenciamentoComunicacaoListener.enterButtonPressed();
				}
			}
		});
		
		contentPane.add(cmbbxPortaCOM);
		
		btnOk = new JButton();
		btnOk.setBounds(165, 40, 45, 30);
		btnOk.setText("OK");
		btnOk.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent actionEvent) {
				gerenciamentoComunicacaoListener.btnOkClicked();
			}
		});
		
		btnOk.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					gerenciamentoComunicacaoListener.enterButtonPressed();
				}
			}
		});
		contentPane.add(btnOk);
		


	}

///////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////
	
	
	//GETTERS
	public String getCmbbxPortaCOM() {
		return (String)cmbbxPortaCOM.getSelectedItem();
	}
	
	
	//SETTERS
	public void setCmbbxPortaCOMAddItem(String portaCOM) {
		cmbbxPortaCOM.addItem(portaCOM);
	}
	
	public void setCmbbxPortaCOMRemoveAll() {
		cmbbxPortaCOM.removeAllItems();
	}
	
	
	//EFETUA O FOCO NO COMBOBOX - PORTA COM
	public void cmbbxPortaCOMRequestFocus() {
		cmbbxPortaCOM.requestFocus();
	}
	
	
	
	//FAZ A LINKAGEM ENTRE O RUNNER E A VIEW [LOGIN] POR INTERMÉDIO DO LISTENER [LOGIN]
	public void addGerenciamentoComunicacaoListener(GerenciamentoComunicacaoListener gerenciamentoComunicacaoListener) {
		this.gerenciamentoComunicacaoListener = gerenciamentoComunicacaoListener;
	}
	
	
}
