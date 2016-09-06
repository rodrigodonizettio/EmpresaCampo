package com.campotech.sgsoft.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import java.awt.Component;
import java.awt.Dialog.ModalExclusionType;
import java.net.URL;

import javax.swing.SwingConstants;

import com.campotech.sgsoft.controller.listener.GerenciamentoRelatoriosListener;
import com.campotech.sgsoft.controller.listener.GerenciamentoUsuariosListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GerenciamentoUsuariosFrame extends JFrame {

	
	//ATRIBUTOS PERTENCENTES AO FRAME
	private JPanel contentPane;
	private JLabel lblUsuario;
	private JTextField txfUsuario;
	private JLabel lblSenha;
	private JPasswordField txpSenha;
	private JLabel lblConfirmacaoSenha;
	private JPasswordField txpConfirmacaoSenha;
	private JLabel lblSenhaAntiga;
	private JPasswordField txpSenhaAntiga;
	private JLabel lblPerfil;
	private JRadioButton rdbtnAdministrador;
	private JRadioButton rdbtnOperador;
	private JButton btnCriarUsuario;
	private JButton btnEditarUsuario;
	private JScrollPane jspLstUsuarios;
	private JList lstUsuarios;
	private JButton btnCriar;
	private JButton btnEditar;
	private JButton btnExcluir;
	private DefaultListModel dftListModel;
	private ButtonGroup rdgrpPerfil;
	
	
	//ATRIBUTOS PERTENCENTES AO LISTENER
	private GerenciamentoUsuariosListener gerenciamentoUsuariosListener;

	
	//ATRIBUTOS DE IMAGENS 
	URL urlIconCampotech = GerenciamentoUsuariosFrame.class.getResource("images/icon-campotech.png");
	URL urlIconBtnCriar = GerenciamentoUsuariosFrame.class.getResource("images/icon-createColor-36px.png");
	URL urlIconBtnEditar = GerenciamentoUsuariosFrame.class.getResource("images/icon-editColor-36px.png");
	//URL urlIconBtnExcluir = GerenciamentoReceitasFrame.class.getResource("images/icon-eraser-36px.png");
	URL urlIconBtnExcluir = GerenciamentoReceitasFrame.class.getResource("images/icon-trashColor-36px.png");
	
	
	/**
	 * Create the frame.
	 */
	public GerenciamentoUsuariosFrame() {
		setTitle("Tela - Gerenciamento de Usuários");
		//<CRIANDO ICONE PARA APLICAÇÃO
		setIconImage(Toolkit.getDefaultToolkit().getImage(urlIconCampotech).getScaledInstance(32, 32, java.awt.Image.SCALE_SMOOTH));
		//CRIANDO ICONE PARA APLICAÇÃO>
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setBounds(100, 100, 720, 220);
		//<POSICIONANDO FRAME NO CENTRO DA TELA DO COMPUTADOR
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();		
		this.setLocation(screenSize.width/2 - this.getSize().width/2, screenSize.height/2 - this.getSize().height/2);
		//POSICIONANDO FRAME NO CENTRO DA TELA DO COMPUTADOR>
		setBackground(Color.WHITE);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		//[LEFT] TEXT BOX - COMPONENTS
		lblUsuario = new JLabel();
		lblUsuario.setBounds(83, 5, 50, 30);
		lblUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsuario.setText("Usuário:");
		lblUsuario.setVisible(false);
		contentPane.add(lblUsuario);
		
			txfUsuario = new JTextField();
			txfUsuario.setBounds(135, 5, 225, 30);
			txfUsuario.setDocument(new JTextFieldCharacterLimit(30));
			txfUsuario.setVisible(false);			
			contentPane.add(txfUsuario);
		
		lblSenha = new JLabel();
		lblSenha.setBounds(83, 35, 50, 30);
		lblSenha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSenha.setText("Senha:");
		lblSenha.setVisible(false);
		contentPane.add(lblSenha);
		
			txpSenha = new JPasswordField();
			txpSenha.setBounds(135, 35, 225, 30);
			((JPasswordField) txpSenha).setEchoChar('•');
			txpSenha.setDocument(new JTextFieldCharacterLimit(30));
			txpSenha.setVisible(false);
			contentPane.add(txpSenha);
			
		lblConfirmacaoSenha = new JLabel();
		lblConfirmacaoSenha.setBounds(3, 65, 130, 30);
		lblConfirmacaoSenha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblConfirmacaoSenha.setText("Confirmação de Senha:");
		lblConfirmacaoSenha.setVisible(false);
		contentPane.add(lblConfirmacaoSenha);
		
			txpConfirmacaoSenha = new JPasswordField();
			txpConfirmacaoSenha.setBounds(135, 65, 225, 30);
			((JPasswordField) txpConfirmacaoSenha).setEchoChar('•');
			txpConfirmacaoSenha.setDocument(new JTextFieldCharacterLimit(30));
			txpConfirmacaoSenha.setVisible(false);
			contentPane.add(txpConfirmacaoSenha);
			
		lblPerfil = new JLabel();
		lblPerfil.setBounds(52, 95, 80, 30);
		lblPerfil.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPerfil.setText("Tipo de Conta:");
		lblPerfil.setVisible(false);
		contentPane.add(lblPerfil);
			
			rdbtnAdministrador = new JRadioButton();
			rdbtnAdministrador.setBounds(135, 95, 100, 30);
			rdbtnAdministrador.setText("Administrador");
			rdbtnAdministrador.setVisible(false);
			contentPane.add(rdbtnAdministrador);
			
			rdbtnOperador = new JRadioButton();
			rdbtnOperador.setBounds(283, 95, 100, 30);
			rdbtnOperador.setText("Operador");
			rdbtnOperador.setVisible(false);
			contentPane.add(rdbtnOperador);
			
			rdgrpPerfil = new ButtonGroup();
			rdgrpPerfil.add(rdbtnAdministrador);
			rdgrpPerfil.add(rdbtnOperador);
			
			
		//LABEL E BOTÃO [SENHA ANTIGA - PARA CONFIRMAÇÃO DE AUTENTICIDADE DO USUÁRIO AO EDITAR A SI PRÓPRIO/OUTROS]
		lblSenhaAntiga = new JLabel();
		lblSenhaAntiga.setBounds(3, 125, 130, 30);
		lblSenhaAntiga.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSenhaAntiga.setText("Senha Antiga:");
		lblSenhaAntiga.setVisible(false);
		contentPane.add(lblSenhaAntiga);
		
			txpSenhaAntiga = new JPasswordField();
			txpSenhaAntiga.setBounds(135, 125, 225, 30);
			((JPasswordField) txpSenhaAntiga).setEchoChar('•');
			txpSenhaAntiga.setDocument(new JTextFieldCharacterLimit(30));
			txpSenhaAntiga.setVisible(false);
			contentPane.add(txpSenhaAntiga);
			
			
		//BOTÃO CRIAR USUÁRIO [1/2]	
		btnCriarUsuario = new JButton();
		btnCriarUsuario.setBounds(110, 138, 150, 30);
		btnCriarUsuario.setText("Criar Usuário");
		
		btnCriarUsuario.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				gerenciamentoUsuariosListener.btnCriarUsuarioClicked();
			}
		});
		
		btnCriarUsuario.setVisible(false);
		contentPane.add(btnCriarUsuario);
		
		
		//BOTÃO EDITAR USUÁRIO [2/2]
		btnEditarUsuario = new JButton();
		btnEditarUsuario.setBounds(110, 158, 150, 30);
		btnEditarUsuario.setText("Editar Usuário");
		
		btnEditarUsuario.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				gerenciamentoUsuariosListener.btnEditarUsuarioClicked();
			}
		});
		
		btnEditarUsuario.setVisible(false);
		contentPane.add(btnEditarUsuario);
				
		
		//[LEFT] LIST BOX - USUÁRIOS
		dftListModel = new DefaultListModel();
		lstUsuarios = new JList(dftListModel);
		lstUsuarios.setLayoutOrientation(JList.VERTICAL);
		lstUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lstUsuarios.setBackground(Color.WHITE);
		
		jspLstUsuarios = new JScrollPane(lstUsuarios);
		jspLstUsuarios.setBounds(380, 5, 270, 170);
		jspLstUsuarios.setBorder(new TitledBorder(null, "Usuários Cadastrados", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		
		dftListModel.addElement("POSICIONE O MOUSE AQUI");
		dftListModel.addElement("PARA VER A LISTA DE USUÁRIOS");
		dftListModel.addElement("CADASTRADOS");
		
		
		jspLstUsuarios.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				gerenciamentoUsuariosListener.jspLstUsuariosMouseEntered();
				
			}
			
		});
		
		contentPane.add(jspLstUsuarios);			
		
		
		//[RIGHT] CRIAR, EDITAR E EXCLUIR - BUTTONS
		btnCriar = new JButton();		
		btnCriar.setToolTipText("Criar Usuário");
		btnCriar.setIcon(new ImageIcon(urlIconBtnCriar));
		btnCriar.setBounds(658, 10, 36, 36);
		btnCriar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				gerenciamentoUsuariosListener.btnCriarClicked();
				
			}
			
		});
		
		contentPane.add(btnCriar);
		
		btnEditar = new JButton();
		btnEditar.setToolTipText("Editar Usuário");
		btnEditar.setIcon(new ImageIcon(urlIconBtnEditar)); //"images/icon-editBW-36px.png"
		btnEditar.setBounds(658, 72, 36, 36);
		
		btnEditar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				gerenciamentoUsuariosListener.btnEditarClicked();
				
			}
			
		});
		
		contentPane.add(btnEditar);
		
		btnExcluir = new JButton();
		btnExcluir.setToolTipText("Excluir Usuário");
		btnExcluir.setIcon(new ImageIcon(urlIconBtnExcluir)); //"images/icon-excludeBW-36px.png"
		btnExcluir.setBounds(658, 132, 36, 36);
		
		btnExcluir.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				gerenciamentoUsuariosListener.btnExcluirClicked();
				
			}
			
		});
		
		contentPane.add(btnExcluir);
		
	}
	
	
	//TODO - GETTERS
	public String getTxfUsuarioText() {
		return txfUsuario.getText();
	}
	
	public String getTxpSenhaText() {
		return String.valueOf(txpSenha.getPassword());
	}
	
	public String getTxpConfirmacaoSenhaText() {
		return String.valueOf(txpConfirmacaoSenha.getPassword());
	}
	
	public String getTxpSenhaAntigaText() {
		return String.valueOf(txpSenhaAntiga.getPassword());
	}
	
	public int getRdgrpPerfilIsSelected() {
		if(rdbtnOperador.isSelected()) {
			return 0;
		} else if(rdbtnAdministrador.isSelected()) {
			return 1;
		} else return -1;
	}
	
	public String getLstUsuariosSelectedValue() {
		return (String)lstUsuarios.getSelectedValue();
	}
	
	public int getLstUsuariosSelectedIndex(String usuarioNome) {
		return lstUsuarios.getSelectedIndex();
	}
	
	
	//TODO - SETTERS
	public void setDftListModelRemoveAllElements() {
		dftListModel.removeAllElements();
	}
	
	public void setDftListModelAddElement(String usuarioNome) {
		dftListModel.addElement(usuarioNome);
	}
	
	public void setLstUsuariosSelectedIndex(int indiceUsuario) {
		lstUsuarios.setSelectedIndex(indiceUsuario);
	}
	
	public void setLblUsuarioVisible(boolean visible) {
		lblUsuario.setVisible(visible);
	}
	
	public void setTxfUsuarioVisible(boolean visible) {
		txfUsuario.setVisible(visible);
	}
	
	public void setTxfUsuarioEnabled(boolean enabled) {
		txfUsuario.setBackground(Color.WHITE);
		txfUsuario.setEnabled(enabled);
	}
	
	public void setLblSenhaVisible(boolean visible) {
		lblSenha.setVisible(visible);
	}
	
	public void setTxpSenhaVisible(boolean visible) {
		txpSenha.setVisible(visible);
	}
	
	public void setLblConfirmacaoSenhaVisible(boolean visible) {
		lblConfirmacaoSenha.setVisible(visible);
	}
	
	public void setTxpConfirmacaoSenhaVisible(boolean visible) {
		txpConfirmacaoSenha.setVisible(visible);
	}
	
	public void setLblPerfilVisible(boolean visible) {
		lblPerfil.setVisible(visible);
	}
	
	public void setRdbtnAdministradorVisible(boolean visible) {
		rdbtnAdministrador.setVisible(visible);
	}
	
	public void setRdbtnOperadorVisible(boolean visible) {
		rdbtnOperador.setVisible(visible);
	}
	
	public void setLblSenhaAntigaVisible(boolean visible) {
		lblSenhaAntiga.setVisible(visible);
	}
	
	public void setTxpSenhaAntigaVisible(boolean visible) {
		txpSenhaAntiga.setVisible(visible);
	}
	
	public void setBtnCriarUsuarioVisible(boolean visible) {
		btnCriarUsuario.setVisible(visible);
	}
	
	public void setBtnEditarUsuarioVisible(boolean visible) {
		btnEditarUsuario.setVisible(visible);
	}
	
	public void setTxfUsuarioText(String usuarioNome) {
		txfUsuario.setText(usuarioNome);
	}
	
	public void setTxpSenhaText(String usuarioSenha) {
		txpSenha.setText(usuarioSenha);
	}
	
	public void setTxpConfirmacaoSenhaText(String usuarioConfirmacaoSenha) {
		txpConfirmacaoSenha.setText(usuarioConfirmacaoSenha);
	}
	
	public void setRdbtnAdministradorSelected(boolean selected) {
		rdbtnAdministrador.setSelected(selected);
	}
	
	public void setRdbtnOperadorSelected(boolean selected) {
		rdbtnOperador.setSelected(selected);
	}
	
	public void setBtnCriarEnabled(boolean enabled) {
		btnCriar.setEnabled(enabled);
	}
	
	public void setBtnExcluirEnabled(boolean enabled) {
		btnExcluir.setEnabled(enabled);
	}
	
	
	//FAZ A LINKAGEM ENTRE O RUNNER E A VIEW [GERENCIAMENTOUSUÁRIOS] POR INTERMÉDIO DO LISTENER [GERENCIAMENTOUSUÁRIOS]
	public void addGerenciamentoUsuariosListener(GerenciamentoUsuariosListener gerenciamentoUsuariosListener) {
		this.gerenciamentoUsuariosListener = gerenciamentoUsuariosListener;
	}

}
