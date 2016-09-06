package com.campotech.sgsoft.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JSplitPane;
import javax.swing.KeyStroke;
import javax.swing.SpringLayout;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.UIManager;

import java.awt.Color;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import com.campotech.sgsoft.controller.listener.LoginListener;
import com.campotech.sgsoft.model.Constants;
import com.campotech.sgsoft.model.dao.UsuarioDAO;
import com.campotech.sgsoft.model.entity.Usuario;

import java.awt.Component;

import javax.swing.JPasswordField;

import java.awt.Dialog.ModalExclusionType;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import javax.swing.ImageIcon;

import java.awt.event.KeyAdapter;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class LoginFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	
	//ATRIBUTOS PERTENCENTES AO FRAME
	private JPanel contentPane;
	private JLabel lblUsuario;
	private JTextField txfUsuario;
	private JLabel lblSenha;
	private JTextField txpSenha;
	private JLabel lblUsuarioSenhaIncorreto;
	private JButton btnEntrar;
	private JLabel lblLogoCampotech;

	
	
	//ATRIBUTOS PERTENCENTES AO LISTENER
	private LoginListener loginListener;
	
	
	//ATRIBUTOS DE IMAGENS 
	URL urlIconCampotech = GerenciamentoRelatoriosFrame.class.getResource("images/icon-campotech.png");
	URL urlLogoCampotech = GerenciamentoRelatoriosFrame.class.getResource("images/logo-campotech.png");
	
	
	/**
	 * Create the frame.
	 */
	//CONSTRUTOR
	public LoginFrame() {
		setTitle("SGSoft - Tela de Autenticação");
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 336, 330);
		setResizable(false);
		//<CRIANDO ICONE PARA APLICAÇÃO
		setIconImage(Toolkit.getDefaultToolkit().getImage(urlIconCampotech).getScaledInstance(32, 32, java.awt.Image.SCALE_SMOOTH));
		//CRIANDO ICONE PARA APLICAÇÃO>
		//<POSICIONANDO FRAME NO CENTRO DA TELA DO COMPUTADOR
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();		
		this.setLocation(screenSize.width/2 - this.getSize().width/2, screenSize.height/2 - this.getSize().height/2);
		//POSICIONANDO FRAME NO CENTRO DA TELA DO COMPUTADOR>
		contentPane = new JPanel();		
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//<CRIANDO LABEL PARA IMAGEM
		ImageIcon logoCampotech = new ImageIcon(urlLogoCampotech);
		//CRIANDO LABEL PARA IMAGEM>
		lblLogoCampotech = new JLabel(logoCampotech);
		lblLogoCampotech.setBounds(0, 5, 331, 99);
		contentPane.add(lblLogoCampotech);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				txfUsuario.requestFocus();
			}
			@Override
			public void windowClosed(WindowEvent arg0) {
				System.out.println("LOGIN CLOSED!");
			}
			@Override
			public void windowClosing(WindowEvent arg0) {
				loginListener.windowClosingActionPerformed();
			}
		});
		
		lblUsuario = new JLabel("Usuário:");
		lblUsuario.setBounds(50, 119, 50, 20);
		contentPane.add(lblUsuario);
		
		txfUsuario = new JTextField();
		txfUsuario.setBounds(50, 140, 225, 30);
		txfUsuario.setDocument(new JTextFieldCharacterLimit(30));
		contentPane.add(txfUsuario);
		
		txfUsuario.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					loginListener.enterButtonPressed();
				}
			}
		});
		
		lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(50, 173, 40, 20);
		contentPane.add(lblSenha);
				
		
		txpSenha = new JPasswordField();
		txpSenha.setBounds(50, 195, 225, 30);
		txpSenha.setDocument(new JTextFieldCharacterLimit(30));
		((JPasswordField) txpSenha).setEchoChar('•');
		txpSenha.setColumns(10);
		contentPane.add(txpSenha);		
		txpSenha.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					loginListener.enterButtonPressed();
				}
			}
		});
		
		lblUsuarioSenhaIncorreto = new JLabel();
		lblUsuarioSenhaIncorreto.setBounds(42, 225, 245, 30);
		lblUsuarioSenhaIncorreto.setForeground(Color.RED);
		lblUsuarioSenhaIncorreto.setText("Usuário/Senha incorretos. Tente novamente.");
		lblUsuarioSenhaIncorreto.setVisible(false);
		contentPane.add(lblUsuarioSenhaIncorreto);
		
		btnEntrar = new JButton();
		btnEntrar.setBounds(130, 260, 65, 30);
		btnEntrar.setText("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent actionEvent) {
				loginListener.btnEntrarClicked();
			}
		});
		
		btnEntrar.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					loginListener.enterButtonPressed();
				}
			}
		});
		
		contentPane.add(btnEntrar);
		
	} //CONSTRUTOR

///////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////
	
	//GETTERS - CAPTURAM DADOS DIGITADOS NOS JTEXTFIELD: USUÁRIO E SENHA
	public String getTxfUsuario() {
		return txfUsuario.getText();
	}

	public String getTxpSenha() {
		return txpSenha.getText();
	}
	
	//SETTERS - DEIXAM O LABEL DE ERRO DE USUÁRIO/SENHA VISÍVEL/INVISÍVEL AO USUÁRIO
	public void setLblUsuarioSenhaIncorretoVisible(Boolean visible) {
		lblUsuarioSenhaIncorreto.setVisible(visible);
	}	
	
	
	//FAZ A LINKAGEM ENTRE O RUNNER E A VIEW [LOGIN] POR INTERMÉDIO DO LISTENER [LOGIN]
	public void addLoginListener(LoginListener loginListener) {
		this.loginListener = loginListener;
	}
	
}
