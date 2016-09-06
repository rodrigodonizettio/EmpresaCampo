package com.campotech.sgsoft.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.Dialog.ModalExclusionType;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.SpringLayout;
import javax.swing.JLabel;

import com.campotech.sgsoft.controller.Runner;
import com.campotech.sgsoft.controller.listener.LoginListener;
import com.campotech.sgsoft.controller.listener.SobreListener;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.net.URI;
import java.net.URL;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SobreFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	
	//ATRIBUTOS PERTENCENTES AO FRAME
	private JPanel contentPane;
	private JLabel lblLogoCampotech;
	private JLabel txlDesenvolvido;
	private JLabel txlNomeEmpresa;
	private JLabel txlSac;
	private JLabel txlEmail;
	private JLabel txlSite;
	private JLabel txlWww;
	private JLabel txlVersao;
	private String uriWww = "www.campotech.com";
	private String uriEmail = "mailto:suporte@campotech.com?subject=Suporte";
	private JLabel lblNomeProjetista;
	
	
	//ATRIBUTOS PERTENCENTES AO LISTENER
	private SobreListener sobreListener;

	
	//ATRIBUTOS DE IMAGENS
	URL urlIconCampotech = GerenciamentoRelatoriosFrame.class.getResource("images/icon-campotech.png");
	URL urlLogoCampotech = GerenciamentoRelatoriosFrame.class.getResource("images/logo-campotech.png");
	
	
	//CONSTRUTOR
	public SobreFrame() {
		setTitle("SGSoft - Tela Sobre");
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 340, 268);
		setResizable(false);
		//<CRIANDO ICONE PARA APLICAÇÃO
		setIconImage(Toolkit.getDefaultToolkit().getImage(urlIconCampotech).getScaledInstance(32, 32, java.awt.Image.SCALE_SMOOTH));
		//CRIANDO ICONE PARA APLICAÇÃO>
		setBackground(Color.WHITE);
		//<POSICIONANDO FRAME NO CENTRO DA TELA DO COMPUTADOR
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();		
		this.setLocation(screenSize.width/2 - this.getSize().width/2, screenSize.height/2 - this.getSize().height/2);
		//POSICIONANDO FRAME NO CENTRO DA TELA DO COMPUTADOR>
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);		
		//<CRIANDO LABEL PARA IMAGEM
		ImageIcon logoCampotech = new ImageIcon(urlLogoCampotech);
		//CRIANDO LABEL PARA IMAGEM>
		lblLogoCampotech = new JLabel(logoCampotech);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblLogoCampotech, 0, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblLogoCampotech, -10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblLogoCampotech, 105, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblLogoCampotech, 335, SpringLayout.WEST, contentPane);
		contentPane.add(lblLogoCampotech);
		
		txlDesenvolvido = new JLabel("Desenvolvido por:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, txlDesenvolvido, 111, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, txlDesenvolvido, 0, SpringLayout.WEST, contentPane);
		contentPane.add(txlDesenvolvido);
		
		lblNomeProjetista = new JLabel("Projetista: Rodrigo Donizetti de Oliveira");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNomeProjetista, 0, SpringLayout.WEST, contentPane);
		contentPane.add(lblNomeProjetista);
		
		txlNomeEmpresa = new JLabel("High-Z Vale Ind\u00FAstria Eletr\u00F4nica LTDA");
		sl_contentPane.putConstraint(SpringLayout.NORTH, txlNomeEmpresa, 3, SpringLayout.SOUTH, txlDesenvolvido);
		sl_contentPane.putConstraint(SpringLayout.WEST, txlNomeEmpresa, 0, SpringLayout.WEST, contentPane);
		txlNomeEmpresa.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(txlNomeEmpresa);
		
		txlSac = new JLabel("SAC:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, txlSac, 20, SpringLayout.SOUTH, txlNomeEmpresa);
		sl_contentPane.putConstraint(SpringLayout.WEST, txlSac, 0, SpringLayout.WEST, contentPane);
		contentPane.add(txlSac);
		
		txlEmail = new JLabel();		
		txlEmail.setForeground(Color.BLUE);
		txlEmail.setText("suporte@campotech.com");
		sl_contentPane.putConstraint(SpringLayout.NORTH, txlEmail, 0, SpringLayout.NORTH, txlSac);
		sl_contentPane.putConstraint(SpringLayout.WEST, txlEmail, 6, SpringLayout.EAST, txlSac);
		sl_contentPane.putConstraint(SpringLayout.EAST, txlEmail, 152, SpringLayout.EAST, txlSac);		
		txlEmail.setFont(new Font("Tahoma", Font.ITALIC, 12));
		
		txlEmail.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				sobreListener.lblEmailClicked(URI.create(uriEmail));
			}
			
		});
		
		contentPane.add(txlEmail);
		
		txlSite = new JLabel("Site:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, txlSite, 2, SpringLayout.SOUTH, txlSac);
		sl_contentPane.putConstraint(SpringLayout.WEST, txlSite, 0, SpringLayout.WEST, contentPane);
		contentPane.add(txlSite);
		
		txlWww = new JLabel();
		txlWww.setText("www.campotech.com");
		txlWww.setForeground(Color.BLUE);
		sl_contentPane.putConstraint(SpringLayout.EAST, txlWww, 129, SpringLayout.WEST, txlEmail);
		sl_contentPane.putConstraint(SpringLayout.NORTH, txlWww, 0, SpringLayout.NORTH, txlSite);
		sl_contentPane.putConstraint(SpringLayout.WEST, txlWww, 0, SpringLayout.WEST, txlEmail);
		txlWww.setFont(new Font("Tahoma", Font.ITALIC, 12));
		
		txlWww.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				sobreListener.lblSiteClicked(URI.create(uriWww));
			}
			
		});
		
		contentPane.add(txlWww);
		
		txlVersao = new JLabel("v3.8");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNomeProjetista, 0, SpringLayout.NORTH, txlVersao);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, txlVersao, 0, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, txlVersao, 0, SpringLayout.EAST, contentPane);
		contentPane.add(txlVersao);
		
	}
	
	//FAZ A LINKAGEM ENTRE O RUNNER E A VIEW [LOGIN] POR INTERMÉDIO DO LISTENER [LOGIN]
	public void addSobreListener(SobreListener sobreListener) {
		this.sobreListener = sobreListener;
	}
	
	
	
	
	/*
	*CORPO DOS MÉTODOS UTILIZADOS NESTA CLASSE
	*/
}
