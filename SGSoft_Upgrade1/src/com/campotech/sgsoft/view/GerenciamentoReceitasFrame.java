package com.campotech.sgsoft.view;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.Dialog.ModalExclusionType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import java.awt.Color;
import java.net.URL;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;

import com.campotech.sgsoft.controller.listener.GerenciamentoReceitasListener;
import com.campotech.sgsoft.controller.listener.GerenciamentoUsuariosListener;
import java.awt.Font;

public class GerenciamentoReceitasFrame extends JFrame {

		
	//ATRIBUTOS PERTENCENTES AO FRAME
	private JPanel contentPane;
	private JLabel lblConfiguracaoEtapas;
	private JLabel lblReceita;
	private JTextField txfReceitaNome;
	private JLabel lblEtapa1;
	private JCheckBox chkbxEtapa1;
	private JRadioButton rdbtnTemperaturaMaximaEntrada1;
	private JSpinner spnTemperaturaMaximaEntrada1;
	private JLabel lblTemperaturaMaximaEntrada1GrauCelsius;
	private JRadioButton rdbtnTemperaturaMaximaMassa1;
	private JSpinner spnTemperaturaMaximaMassa1;
	private JLabel lblTemperaturaMaximaMassa1GrauCelsius;
	private ButtonGroup rdgrpTipoControle1;
	private JLabel lblTempo1;
	private JSpinner spnTempo1;
	private JLabel lblTempo1Horas;
	///
	private JLabel lblEtapa2;
	private JCheckBox chkbxEtapa2;
	private JRadioButton rdbtnTemperaturaMaximaEntrada2;
	private JSpinner spnTemperaturaMaximaEntrada2;
	private JLabel lblTemperaturaMaximaEntrada2GrauCelsius;
	private JRadioButton rdbtnTemperaturaMaximaMassa2;
	private JSpinner spnTemperaturaMaximaMassa2;
	private JLabel lblTemperaturaMaximaMassa2GrauCelsius;
	private ButtonGroup rdgrpTipoControle2;
	private JLabel lblTempo2;
	private JSpinner spnTempo2;
	private JLabel lblTempo2Horas;
	///
	private JLabel lblEtapa3;
	private JCheckBox chkbxEtapa3;
	private JRadioButton rdbtnTemperaturaMaximaEntrada3;
	private JSpinner spnTemperaturaMaximaEntrada3;
	private JLabel lblTemperaturaMaximaEntrada3GrauCelsius;
	private JRadioButton rdbtnTemperaturaMaximaMassa3;
	private JSpinner spnTemperaturaMaximaMassa3;
	private JLabel lblTemperaturaMaximaMassa3GrauCelsius;
	private ButtonGroup rdgrpTipoControle3;
	private JLabel lblTempo3;
	private JSpinner spnTempo3;
	private JLabel lblTempo3Horas;
	///
	private JButton btnCriarReceita;
	private JButton btnEditarReceita;
	private JLabel lblReceitasCriadas;
	private JComboBox cmbbxReceitasCriadas;
	private DefaultListModel dftListModel;
	private JList lstReceitas;
	private JScrollPane jspLstReceitas;
	private JButton btnCriar;
	private JButton btnEditar;
	private JButton btnExcluir;
	
	
	//ATRIBUTOS PERTENCENTES AO LISTENER
	GerenciamentoReceitasListener gerenciamentoReceitasListener;
	
	
	//ATRIBUTOS DE IMAGENS 
	URL urlIconCampotech = GerenciamentoReceitasFrame.class.getResource("images/icon-campotech.png");
	URL urlIconBtnCriar = GerenciamentoUsuariosFrame.class.getResource("images/icon-createColor-36px.png");
	URL urlIconBtnEditar = GerenciamentoUsuariosFrame.class.getResource("images/icon-editColor-36px.png");
	//URL urlIconBtnExcluir = GerenciamentoReceitasFrame.class.getResource("images/icon-excludeSquareColor-36px.png");
	URL urlIconBtnExcluir = GerenciamentoReceitasFrame.class.getResource("images/icon-trashColor-36px.png");

	
	/**
	 * Create the frame.
	 */
	public GerenciamentoReceitasFrame() {
		setTitle("Tela - Gerenciamento de Receitas");
		//<CRIANDO ICONE PARA APLICAÇÃO
		setIconImage(Toolkit.getDefaultToolkit().getImage(urlIconCampotech).getScaledInstance(32, 32, java.awt.Image.SCALE_SMOOTH));
		//CRIANDO ICONE PARA APLICAÇÃO>
		setBounds(100, 100, 700, 570);
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
		
		
		//[LEFT] CRIAÇÃO DE RECEITAS		
		lblConfiguracaoEtapas = new JLabel();
		lblConfiguracaoEtapas.setBounds(5, 5, 350, 530);
		lblConfiguracaoEtapas.setVisible(false);
		lblConfiguracaoEtapas.setBorder(new TitledBorder(null, "Configuração - Etapas de Secagem", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		contentPane.add(lblConfiguracaoEtapas);
		
			lblReceita = new JLabel();
			lblReceita.setBounds(15, 25, 90, 20);
			lblReceita.setText("Nome - Receita:");
			lblConfiguracaoEtapas.add(lblReceita);
			
			txfReceitaNome = new JTextField();
			txfReceitaNome.setBounds(107, 21, 225, 30);
			txfReceitaNome.setDocument(new JTextFieldCharacterLimit(30));
			lblConfiguracaoEtapas.add(txfReceitaNome);
		
			lblEtapa1 = new JLabel();
			lblEtapa1.setBounds(37, 60, 273, 130);
			lblEtapa1.setBorder(new TitledBorder(null, null, TitledBorder.CENTER, TitledBorder.TOP, null, null));
			lblConfiguracaoEtapas.add(lblEtapa1);
			
				chkbxEtapa1 = new JCheckBox();
				chkbxEtapa1.setBounds(100, 5, 65, 20);
				chkbxEtapa1.setSelected(true);
				chkbxEtapa1.setEnabled(false);
				chkbxEtapa1.setText("Etapa-1");
				lblEtapa1.add(chkbxEtapa1);
				
				rdbtnTemperaturaMaximaEntrada1 = new JRadioButton();
				rdbtnTemperaturaMaximaEntrada1.setBounds(30, 30, 160, 20);
				rdbtnTemperaturaMaximaEntrada1.setSelected(true);
				rdbtnTemperaturaMaximaEntrada1.setText("Temperatura - Entrada:");
				
				lblEtapa1.add(rdbtnTemperaturaMaximaEntrada1);
				
				spnTemperaturaMaximaEntrada1 = new JSpinner();
				spnTemperaturaMaximaEntrada1.setBounds(184, 28, 55, 25);
				spnTemperaturaMaximaEntrada1.setModel(new SpinnerNumberModel(45, 1, 120, 1));
				lblEtapa1.add(spnTemperaturaMaximaEntrada1);
				
				lblTemperaturaMaximaEntrada1GrauCelsius = new JLabel();
				lblTemperaturaMaximaEntrada1GrauCelsius.setFont(new Font("Tahoma", Font.PLAIN, 12));
				lblTemperaturaMaximaEntrada1GrauCelsius.setBounds(240, 24, 30, 30);
				lblTemperaturaMaximaEntrada1GrauCelsius.setText("[°C]");
				lblEtapa1.add(lblTemperaturaMaximaEntrada1GrauCelsius);
				
				rdbtnTemperaturaMaximaMassa1 = new JRadioButton();
				rdbtnTemperaturaMaximaMassa1.setBounds(30, 60, 160, 20);
				rdbtnTemperaturaMaximaMassa1.setText(" Temperatura - Massa:");
				lblEtapa1.add(rdbtnTemperaturaMaximaMassa1);
				
				rdgrpTipoControle1 = new ButtonGroup();
				rdgrpTipoControle1.add(rdbtnTemperaturaMaximaEntrada1);
				rdgrpTipoControle1.add(rdbtnTemperaturaMaximaMassa1);
				
				spnTemperaturaMaximaMassa1 = new JSpinner();
				spnTemperaturaMaximaMassa1.setBounds(184, 58, 55, 25);
				spnTemperaturaMaximaMassa1.setModel(new SpinnerNumberModel(45, 1, 120, 1));
				lblEtapa1.add(spnTemperaturaMaximaMassa1);
				
				lblTemperaturaMaximaMassa1GrauCelsius = new JLabel();
				lblTemperaturaMaximaMassa1GrauCelsius.setFont(new Font("Tahoma", Font.PLAIN, 12));
				lblTemperaturaMaximaMassa1GrauCelsius.setBounds(240, 54, 30, 30);
				lblTemperaturaMaximaMassa1GrauCelsius.setText("[°C]");
				lblEtapa1.add(lblTemperaturaMaximaMassa1GrauCelsius);
				
				lblTempo1 = new JLabel();
				lblTempo1.setBounds(125, 91, 50, 20);
				lblTempo1.setHorizontalAlignment(SwingConstants.RIGHT);
				lblTempo1.setText("Tempo:");
				lblEtapa1.add(lblTempo1);
				
				spnTempo1 = new JSpinner();
				spnTempo1.setBounds(184, 90, 55, 25);
				spnTempo1.setModel(new SpinnerNumberModel(24, 1, 72, 1));
				lblEtapa1.add(spnTempo1);
				
				lblTempo1Horas = new JLabel();
				lblTempo1Horas.setFont(new Font("Tahoma", Font.PLAIN, 12));
				lblTempo1Horas.setBounds(240, 87, 30, 30);
				lblTempo1Horas.setText("[h]");
				lblEtapa1.add(lblTempo1Horas);
				
			lblEtapa2 = new JLabel();
			lblEtapa2.setBounds(37, 205, 273, 130);
			lblEtapa2.setBorder(new TitledBorder(null, null, TitledBorder.CENTER, TitledBorder.TOP, null, null));
			lblConfiguracaoEtapas.add(lblEtapa2);
			
				chkbxEtapa2 = new JCheckBox();				
				chkbxEtapa2.setBounds(100, 5, 65, 20);
				chkbxEtapa2.setText("Etapa-2");
				
				chkbxEtapa2.addMouseListener(new MouseAdapter() {
					
					@Override
					public void mouseClicked(MouseEvent e) {
						
						//@11-10-2016
						///
						if(chkbxEtapa2.isSelected()) {
						
							lblTemperaturaMaximaEntrada2GrauCelsius.setEnabled(true);
							lblTemperaturaMaximaMassa2GrauCelsius.setEnabled(true);
							lblTempo2Horas.setEnabled(true);
							
						} else {
							
							lblTemperaturaMaximaEntrada2GrauCelsius.setEnabled(false);
							lblTemperaturaMaximaMassa2GrauCelsius.setEnabled(false);
							lblTempo2Horas.setEnabled(false);
							///
							lblTemperaturaMaximaEntrada3GrauCelsius.setEnabled(false);
							lblTemperaturaMaximaMassa3GrauCelsius.setEnabled(false);
							lblTempo3Horas.setEnabled(false);
							
						}						
						///
						gerenciamentoReceitasListener.chkbxEtapa2Clicked();
						
						
					}
					
				});
				
				lblEtapa2.add(chkbxEtapa2);
				
				rdbtnTemperaturaMaximaEntrada2 = new JRadioButton();
				rdbtnTemperaturaMaximaEntrada2.setBounds(30, 30, 160, 20);
				rdbtnTemperaturaMaximaEntrada2.setSelected(true);
				rdbtnTemperaturaMaximaEntrada2.setEnabled(false);
				rdbtnTemperaturaMaximaEntrada2.setText("Temperatura - Entrada:");
				lblEtapa2.add(rdbtnTemperaturaMaximaEntrada2);
				
				spnTemperaturaMaximaEntrada2 = new JSpinner();
				spnTemperaturaMaximaEntrada2.setBounds(184, 28, 55, 25);
				spnTemperaturaMaximaEntrada2.setEnabled(false);
				spnTemperaturaMaximaEntrada2.setModel(new SpinnerNumberModel(45, 1, 120, 1));
				lblEtapa2.add(spnTemperaturaMaximaEntrada2);
				
				lblTemperaturaMaximaEntrada2GrauCelsius = new JLabel();
				lblTemperaturaMaximaEntrada2GrauCelsius.setFont(new Font("Tahoma", Font.PLAIN, 12));
				lblTemperaturaMaximaEntrada2GrauCelsius.setBounds(240, 24, 30, 30);
				lblTemperaturaMaximaEntrada2GrauCelsius.setText("[°C]");
				lblTemperaturaMaximaEntrada2GrauCelsius.setEnabled(false);
				lblEtapa2.add(lblTemperaturaMaximaEntrada2GrauCelsius);
				
				rdbtnTemperaturaMaximaMassa2 = new JRadioButton();
				rdbtnTemperaturaMaximaMassa2.setBounds(30, 60, 160, 20);
				rdbtnTemperaturaMaximaMassa2.setEnabled(false);
				rdbtnTemperaturaMaximaMassa2.setText(" Temperatura - Massa:");
				lblEtapa2.add(rdbtnTemperaturaMaximaMassa2);
				
				rdgrpTipoControle2 = new ButtonGroup();
				rdgrpTipoControle2.add(rdbtnTemperaturaMaximaEntrada2);
				rdgrpTipoControle2.add(rdbtnTemperaturaMaximaMassa2);
				
				spnTemperaturaMaximaMassa2 = new JSpinner();
				spnTemperaturaMaximaMassa2.setBounds(184, 58, 55, 25);
				spnTemperaturaMaximaMassa2.setEnabled(false);
				spnTemperaturaMaximaMassa2.setModel(new SpinnerNumberModel(45, 1, 120, 1));
				lblEtapa2.add(spnTemperaturaMaximaMassa2);
				
				lblTemperaturaMaximaMassa2GrauCelsius = new JLabel();
				lblTemperaturaMaximaMassa2GrauCelsius.setFont(new Font("Tahoma", Font.PLAIN, 12));
				lblTemperaturaMaximaMassa2GrauCelsius.setBounds(240, 54, 30, 30);
				lblTemperaturaMaximaMassa2GrauCelsius.setText("[°C]");
				lblTemperaturaMaximaMassa2GrauCelsius.setEnabled(false);
				lblEtapa2.add(lblTemperaturaMaximaMassa2GrauCelsius);
				
				lblTempo2 = new JLabel();
				lblTempo2.setBounds(125, 91, 50, 20);
				lblTempo2.setEnabled(false);
				lblTempo2.setHorizontalAlignment(SwingConstants.RIGHT);
				lblTempo2.setText("Tempo:");
				lblEtapa2.add(lblTempo2);
				
				spnTempo2 = new JSpinner();
				spnTempo2.setBounds(184, 90, 55, 25);
				spnTempo2.setEnabled(false);
				spnTempo2.setModel(new SpinnerNumberModel(24, 1, 72, 1));
				lblEtapa2.add(spnTempo2);
				
				lblTempo2Horas = new JLabel();
				lblTempo2Horas.setFont(new Font("Tahoma", Font.PLAIN, 12));
				lblTempo2Horas.setBounds(240, 87, 30, 30);
				lblTempo2Horas.setText("[h]");
				lblTempo2Horas.setEnabled(false);
				lblEtapa2.add(lblTempo2Horas);
				
			lblEtapa3 = new JLabel();
			lblEtapa3.setBounds(37, 350, 273, 130);
			lblEtapa3.setEnabled(false);
			lblEtapa3.setBorder(new TitledBorder(null, null, TitledBorder.CENTER, TitledBorder.TOP, null, null));
			lblConfiguracaoEtapas.add(lblEtapa3);
			
				chkbxEtapa3 = new JCheckBox();
				chkbxEtapa3.setBounds(100, 5, 65, 20);
				chkbxEtapa3.setEnabled(false);
				chkbxEtapa3.setText("Etapa-3");
				
				chkbxEtapa3.addMouseListener(new MouseAdapter() {
					
					@Override
					public void mouseClicked(MouseEvent arg0) {
						
						//@11-10-2016
						///
						if(chkbxEtapa3.isSelected()) {
						
							lblTemperaturaMaximaEntrada3GrauCelsius.setEnabled(true);
							lblTemperaturaMaximaMassa3GrauCelsius.setEnabled(true);
							lblTempo3Horas.setEnabled(true);
							
						} else {
							
							lblTemperaturaMaximaEntrada3GrauCelsius.setEnabled(false);
							lblTemperaturaMaximaMassa3GrauCelsius.setEnabled(false);
							lblTempo3Horas.setEnabled(false);
							
						}						
						///
						gerenciamentoReceitasListener.chkbxEtapa3Clicked();
						
					}
					
				});
				
				lblEtapa3.add(chkbxEtapa3);
				
				rdbtnTemperaturaMaximaEntrada3 = new JRadioButton();
				rdbtnTemperaturaMaximaEntrada3.setBounds(30, 30, 160, 20);
				rdbtnTemperaturaMaximaEntrada3.setSelected(true);
				rdbtnTemperaturaMaximaEntrada3.setEnabled(false);
				rdbtnTemperaturaMaximaEntrada3.setText("Temperatura - Entrada:");
				lblEtapa3.add(rdbtnTemperaturaMaximaEntrada3);
				
				spnTemperaturaMaximaEntrada3 = new JSpinner();
				spnTemperaturaMaximaEntrada3.setBounds(184, 28, 55, 25);
				spnTemperaturaMaximaEntrada3.setEnabled(false);
				spnTemperaturaMaximaEntrada3.setModel(new SpinnerNumberModel(45, 1, 120, 1));
				lblEtapa3.add(spnTemperaturaMaximaEntrada3);
				
				lblTemperaturaMaximaEntrada3GrauCelsius = new JLabel();
				lblTemperaturaMaximaEntrada3GrauCelsius.setFont(new Font("Tahoma", Font.PLAIN, 12));
				lblTemperaturaMaximaEntrada3GrauCelsius.setBounds(240, 24, 30, 30);
				lblTemperaturaMaximaEntrada3GrauCelsius.setText("[°C]");
				lblTemperaturaMaximaEntrada3GrauCelsius.setEnabled(false);
				lblEtapa3.add(lblTemperaturaMaximaEntrada3GrauCelsius);
				
				rdbtnTemperaturaMaximaMassa3 = new JRadioButton();
				rdbtnTemperaturaMaximaMassa3.setBounds(30, 60, 160, 20);
				rdbtnTemperaturaMaximaMassa3.setEnabled(false);
				rdbtnTemperaturaMaximaMassa3.setText(" Temperatura - Massa:");
				lblEtapa3.add(rdbtnTemperaturaMaximaMassa3);
				
				rdgrpTipoControle3 = new ButtonGroup();
				rdgrpTipoControle3.add(rdbtnTemperaturaMaximaEntrada3);
				rdgrpTipoControle3.add(rdbtnTemperaturaMaximaMassa3);
				
				spnTemperaturaMaximaMassa3 = new JSpinner();
				spnTemperaturaMaximaMassa3.setBounds(184, 58, 55, 25);
				spnTemperaturaMaximaMassa3.setEnabled(false);
				spnTemperaturaMaximaMassa3.setModel(new SpinnerNumberModel(45, 1, 120, 1));
				lblEtapa3.add(spnTemperaturaMaximaMassa3);
				
				lblTemperaturaMaximaMassa3GrauCelsius = new JLabel();
				lblTemperaturaMaximaMassa3GrauCelsius.setFont(new Font("Tahoma", Font.PLAIN, 12));
				lblTemperaturaMaximaMassa3GrauCelsius.setBounds(240, 54, 30, 30);
				lblTemperaturaMaximaMassa3GrauCelsius.setText("[°C]");
				lblTemperaturaMaximaMassa3GrauCelsius.setEnabled(false);
				lblEtapa3.add(lblTemperaturaMaximaMassa3GrauCelsius);
				
				lblTempo3 = new JLabel();
				lblTempo3.setBounds(125, 91, 50, 20);
				lblTempo3.setEnabled(false);
				lblTempo3.setHorizontalAlignment(SwingConstants.RIGHT);
				lblTempo3.setText("Tempo:");
				lblEtapa3.add(lblTempo3);
				
				spnTempo3 = new JSpinner();
				spnTempo3.setBounds(184, 90, 55, 25);
				spnTempo3.setEnabled(false);
				spnTempo3.setModel(new SpinnerNumberModel(24, 1, 72, 1));
				lblEtapa3.add(spnTempo3);
				
				lblTempo3Horas = new JLabel();
				lblTempo3Horas.setFont(new Font("Tahoma", Font.PLAIN, 12));
				lblTempo3Horas.setBounds(240, 87, 30, 30);
				lblTempo3Horas.setText("[h]");
				lblTempo3Horas.setEnabled(false);
				lblEtapa3.add(lblTempo3Horas);
				
			btnCriarReceita = new JButton();
			btnCriarReceita.setBounds(115, 487, 115, 30);
			btnCriarReceita.setVisible(false);
			btnCriarReceita.setText("Criar Receita");
			
			btnCriarReceita.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent arg0) {
					
					gerenciamentoReceitasListener.btnCriarReceitaClicked();
				}
				
			});
			
			lblConfiguracaoEtapas.add(btnCriarReceita);
			
			btnEditarReceita = new JButton();
			btnEditarReceita.setBounds(115, 487, 115, 30);
			btnEditarReceita.setVisible(false);
			btnEditarReceita.setText("Editar Receita");
			
			btnEditarReceita.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent arg0) {
					
					gerenciamentoReceitasListener.btnEditarReceitaClicked();
				}
				
			});
			
			lblConfiguracaoEtapas.add(btnEditarReceita);
			
			
		//[RIGHT] RECEITAS CRIADAS
			/*
		lblReceitasCriadas = new JLabel();
		lblReceitasCriadas.setBounds(360, 5, 275, 530);
		lblReceitasCriadas.setBorder(new TitledBorder(null, "Receitas Criadas", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		contentPane.add(lblReceitasCriadas);
		*/
		
		/*
		cmbbxReceitasCriadas = new JComboBox();
		cmbbxReceitasCriadas.setBounds(15, 20, 245, 30);
		cmbbxReceitasCriadas.addItem("123456789012345678901234567890");
		cmbbxReceitasCriadas.addItem("Teste1");
		cmbbxReceitasCriadas.addItem("Teste2");
		lblReceitasCriadas.add(cmbbxReceitasCriadas);
		*/
		
		//[LEFT] LIST BOX - USUÁRIOS
		dftListModel = new DefaultListModel();
		lstReceitas = new JList(dftListModel);
		lstReceitas.setLayoutOrientation(JList.VERTICAL);
		lstReceitas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lstReceitas.setBackground(Color.WHITE);
				
		jspLstReceitas = new JScrollPane(lstReceitas);
		jspLstReceitas.setBounds(355, 5, 290, 530);
		jspLstReceitas.setBorder(new TitledBorder(null, "Receitas Cadastradas", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		
		dftListModel.addElement("POSICIONE O MOUSE AQUI");
		dftListModel.addElement("PARA VER A LISTA DE RECEITAS");
		dftListModel.addElement("CADASTRADAS");
		
		jspLstReceitas.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				gerenciamentoReceitasListener.jspLstReceitasMouseEntered();
				
			}
			
		});
		
		contentPane.add(jspLstReceitas);
		
		
		btnCriar = new JButton();		
		btnCriar.setToolTipText("Criar Receita");
		btnCriar.setIcon(new ImageIcon(urlIconBtnCriar));
		btnCriar.setBounds(650, 10, 36, 36);
		
		btnCriar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				gerenciamentoReceitasListener.btnCriarClicked();
				
			}
			
		});
		
		contentPane.add(btnCriar);
		
		btnEditar = new JButton();		
		btnEditar.setToolTipText("Editar Receita");
		btnEditar.setIcon(new ImageIcon(urlIconBtnEditar));
		btnEditar.setBounds(650, 255, 36, 36);
		
		btnEditar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				gerenciamentoReceitasListener.btnEditarClicked();
				
			}
			
		});
		
		contentPane.add(btnEditar);
		
		btnExcluir = new JButton();
		btnExcluir.setToolTipText("Excluir Receita");
		btnExcluir.setIcon(new ImageIcon(urlIconBtnExcluir)); //"images/icon-editBW-36px.png"
		btnExcluir.setBounds(650, 492, 36, 36);
		
		btnExcluir.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				gerenciamentoReceitasListener.btnExcluirClicked();
				
			}
			
		});
		
		contentPane.add(btnExcluir);
		
	}
	
	
	//TODO - GETTERS
	public int getLstReceitasSelectedIndex(String receitaNome) {
		return lstReceitas.getSelectedIndex();
	}
	
	public String getLstReceitasSelectedValue() {
		return (String)lstReceitas.getSelectedValue();
	}
	
	public boolean getChkbxEtapa2IsSelected() {
		return chkbxEtapa2.isSelected();
	}
	
	public boolean getChkbxEtapa3IsSelected() {
		return chkbxEtapa3.isSelected();
	}
	
	public int getSpnTemperaturaMaximaEntrada1Value() {
		return (int) spnTemperaturaMaximaEntrada1.getValue();
	}
	
	public int getSpnTemperaturaMaximaMassa1Value() {
		return (int) spnTemperaturaMaximaMassa1.getValue();
	}
	
	public int getSpnTempo1Value() {
		return (int) spnTempo1.getValue();
	}
	
	public int getSpnTemperaturaMaximaEntrada2Value() {
		return (int) spnTemperaturaMaximaEntrada2.getValue();
	}
	
	public int getSpnTemperaturaMaximaMassa2Value() {
		return (int) spnTemperaturaMaximaMassa2.getValue();
	}
	
	public int getSpnTempo2Value() {
		return (int) spnTempo2.getValue();
	}
	
	public int getSpnTemperaturaMaximaEntrada3Value() {
		return (int) spnTemperaturaMaximaEntrada3.getValue();
	}
	
	public int getSpnTemperaturaMaximaMassa3Value() {
		return (int) spnTemperaturaMaximaMassa3.getValue();
	}
	
	public int getSpnTempo3Value() {
		return (int) spnTempo3.getValue();
	}
	
	public boolean getRdbtnTemperaturaMaximaEntrada1IsSelected() {
		return rdbtnTemperaturaMaximaEntrada1.isSelected();
	}
	
	public boolean getRdbtnTemperaturaMaximaEntrada2IsSelected() {
		return rdbtnTemperaturaMaximaEntrada2.isSelected();
	}
	
	public boolean getRdbtnTemperaturaMaximaEntrada3IsSelected() {
		return rdbtnTemperaturaMaximaEntrada3.isSelected();
	}
	
	public boolean getRdbtnTemperaturaMaximaMassa1IsSelected() {
		return rdbtnTemperaturaMaximaMassa1.isSelected();
	}
	
	public boolean getRdbtnTemperaturaMaximaMassa2IsSelected() {
		return rdbtnTemperaturaMaximaMassa2.isSelected();
	}
	
	public boolean getRdbtnTemperaturaMaximaMassa3IsSelected() {
		return rdbtnTemperaturaMaximaMassa3.isSelected();
	}
	
	public String getTxfReceitaNomeText() {
		return txfReceitaNome.getText();
	}
	
	//TODO - SETTERS
	public void setDftListModelRemoveAllElements() {
		dftListModel.removeAllElements();
	}
	
	public void setDftListModelAddElement(String receitaNome) {
		dftListModel.addElement(receitaNome);
	}
	
	public void setLstReceitasSelectedIndex(int indiceReceita) {
		lstReceitas.setSelectedIndex(indiceReceita);
	}
	
	public void setBtnCriarReceitaVisible(boolean visible) {
		btnCriarReceita.setVisible(visible);
	}
	
	public void setBtnEditarReceitaVisible(boolean visible) {
		btnEditarReceita.setVisible(visible);
	}
	
	public void setLblConfiguracaoEtapasVisible(boolean visible) {
		lblConfiguracaoEtapas.setVisible(visible);
	}
	
	public void setTxfReceitaNomeText(String receitaNome) {
		txfReceitaNome.setText(receitaNome);
	}
	
	public void setTxfReceitaNomeEnabled(boolean enabled) {
		txfReceitaNome.setEnabled(enabled);
	}
	
	public void setSpnTemperaturaMaximaEntrada1Value (int temperatura) {
		spnTemperaturaMaximaEntrada1.setValue(temperatura);
	}
	
	public void setSpnTemperaturaMaximaMassa1Value (int temperatura) {
		spnTemperaturaMaximaMassa1.setValue(temperatura);
	}
	
	public void setSpnTempo1Value (int tempoHoras) {
		spnTempo1.setValue(tempoHoras);
	}
	
	public void setSpnTemperaturaMaximaEntrada2Value (int temperatura) {
		spnTemperaturaMaximaEntrada2.setValue(temperatura);
	}
	
	public void setSpnTemperaturaMaximaMassa2Value (int temperatura) {
		spnTemperaturaMaximaMassa2.setValue(temperatura);
	}
	
	public void setSpnTempo2Value (int tempoHoras) {
		spnTempo2.setValue(tempoHoras);
	}
	
	public void setSpnTemperaturaMaximaEntrada3Value (int temperatura) {
		spnTemperaturaMaximaEntrada3.setValue(temperatura);
	}
	
	public void setSpnTemperaturaMaximaMassa3Value (int temperatura) {
		spnTemperaturaMaximaMassa3.setValue(temperatura);
	}
	
	public void setSpnTempo3Value (int tempoHoras) {
		spnTempo3.setValue(tempoHoras);
	}
	
	public void setRdbtnTemperaturaMaximaEntrada1Selected(boolean selected) {
		rdbtnTemperaturaMaximaEntrada1.setSelected(selected);
	}
	
	public void setRdbtnTemperaturaMaximaMassa1Selected(boolean selected) {
		rdbtnTemperaturaMaximaMassa1.setSelected(selected);
	}
	
	public void setRdbtnTemperaturaMaximaEntrada2Selected(boolean selected) {
		rdbtnTemperaturaMaximaEntrada2.setSelected(selected);
	}
	
	public void setRdbtnTemperaturaMaximaMassa2Selected(boolean selected) {
		rdbtnTemperaturaMaximaMassa2.setSelected(selected);
	}
	
	public void setRdbtnTemperaturaMaximaEntrada3Selected(boolean selected) {
		rdbtnTemperaturaMaximaEntrada3.setSelected(selected);
	}
	
	public void setRdbtnTemperaturaMaximaMassa3Selected(boolean selected) {
		rdbtnTemperaturaMaximaMassa3.setSelected(selected);
	}
	
	public void setRdbtnTemperaturaMaximaEntrada1Enabled(boolean enabled) {
		rdbtnTemperaturaMaximaEntrada1.setEnabled(enabled);
	}
	
	public void setRdbtnTemperaturaMaximaMassa1Enabled(boolean enabled) {
		rdbtnTemperaturaMaximaMassa1.setEnabled(enabled);
	}
	
	public void setRdbtnTemperaturaMaximaEntrada2Enabled(boolean enabled) {
		rdbtnTemperaturaMaximaEntrada2.setEnabled(enabled);
	}
	
	public void setRdbtnTemperaturaMaximaMassa2Enabled(boolean enabled) {
		rdbtnTemperaturaMaximaMassa2.setEnabled(enabled);
	}
	
	public void setRdbtnTemperaturaMaximaEntrada3Enabled(boolean enabled) {
		rdbtnTemperaturaMaximaEntrada3.setEnabled(enabled);
	}
	
	public void setRdbtnTemperaturaMaximaMassa3Enabled(boolean enabled) {
		rdbtnTemperaturaMaximaMassa3.setEnabled(enabled);
	}
	
	public void setSpnTemperaturaMaximaEntrada1Enabled(boolean enabled) {
		spnTemperaturaMaximaEntrada1.setEnabled(enabled);
	}
	
	public void setSpnTemperaturaMaximaMassa1Enabled(boolean enabled) {
		spnTemperaturaMaximaMassa1.setEnabled(enabled);
	}
	
	public void setSpnTemperaturaMaximaEntrada2Enabled(boolean enabled) {
		spnTemperaturaMaximaEntrada2.setEnabled(enabled);
	}
	
	public void setSpnTemperaturaMaximaMassa2Enabled(boolean enabled) {
		spnTemperaturaMaximaMassa2.setEnabled(enabled);
	}
	
	public void setSpnTemperaturaMaximaEntrada3Enabled(boolean enabled) {
		spnTemperaturaMaximaEntrada3.setEnabled(enabled);
	}
	
	public void setSpnTemperaturaMaximaMassa3Enabled(boolean enabled) {
		spnTemperaturaMaximaMassa3.setEnabled(enabled);
	}
	
	public void setLblTempo1Enabled(boolean enabled) {
		lblTempo1.setEnabled(enabled);
	}
	
	public void setSpnTempo1Enabled(boolean enabled) {
		spnTempo1.setEnabled(enabled);
	}
	
	public void setLblTempo2Enabled(boolean enabled) {
		lblTempo2.setEnabled(enabled);
	}
	
	public void setSpnTempo2Enabled(boolean enabled) {
		spnTempo2.setEnabled(enabled);
	}
	
	public void setLblTempo3Enabled(boolean enabled) {
		lblTempo3.setEnabled(enabled);
	}
	
	public void setSpnTempo3Enabled(boolean enabled) {
		spnTempo3.setEnabled(enabled);
	}
	
	public void setChkbxEtapa1Visible(boolean visible) {
		chkbxEtapa1.setVisible(visible);
	}
	
	public void setChkbxEtapa1Enabled(boolean enabled) {
		chkbxEtapa1.setEnabled(enabled);
	}
	
	public void setChkbxEtapa2Enabled(boolean enabled) {
		chkbxEtapa2.setEnabled(enabled);
	}
	
	public void setChkbxEtapa3Enabled(boolean enabled) {
		chkbxEtapa3.setEnabled(enabled);
	}
	
	public void setChkbxEtapa1Selected(boolean selected) {
		chkbxEtapa1.setSelected(selected);
	}
	
	public void setChkbxEtapa2Selected(boolean selected) {
		chkbxEtapa2.setSelected(selected);
	}
	
	public void setChkbxEtapa3Selected(boolean selected) {
		chkbxEtapa3.setSelected(selected);
	}
	
	public void setLblReceitaVisible(boolean visible) {
		lblReceita.setVisible(visible);
	}
	
	public void setTxfReceitaNomeVisible(boolean visible) {
		txfReceitaNome.setVisible(visible);
	}
	
	public void setLblEtapa1Visible(boolean visible) {
		lblEtapa1.setVisible(visible);
	}
	
	public void setLblEtapa2Visible(boolean visible) {
		lblEtapa2.setVisible(visible);
	}
	
	public void setLblEtapa2Enabled(boolean enabled) {
		lblEtapa2.setEnabled(enabled);
	}
	
	public void setLblEtapa3Visible(boolean visible) {
		lblEtapa3.setVisible(visible);
	}
	
	public void setLblEtapa3Enabled(boolean enabled) {
		lblEtapa3.setEnabled(enabled);
	}
	
	
	//FAZ A LINKAGEM ENTRE O RUNNER E A VIEW [GERENCIAMENTORECEITAS] POR INTERMÉDIO DO LISTENER [GERENCIAMENTORECEITAS]
	public void addGerenciamentoReceitasListener(GerenciamentoReceitasListener gerenciamentoReceitasListener) {
		this.gerenciamentoReceitasListener = gerenciamentoReceitasListener;
	}
	
}
