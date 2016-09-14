package com.campotech.sgsoft.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.Dialog.ModalExclusionType;
import java.net.URL;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import com.campotech.sgsoft.controller.listener.GerenciamentoComunicacaoListener;
import com.campotech.sgsoft.controller.listener.GerenciamentoMotoresListener;
import com.itextpdf.text.io.TempFileCache;
import com.itextpdf.text.log.SysoCounter;
import com.jgoodies.forms.builder.ButtonStackBuilder;

import javax.swing.UIManager;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class GerenciamentoMotoresFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public static boolean flagGerenciamentoSecagemFrameSetStatusMotores = false; //FLAG PARA ENVIO DE CONFIGURAÇÃO AOS MOTORES
	public static String comandoBA = new String();
	
	//ATRIBUTOS PERTENCENTES AO FRAME
	private JPanel contentPane;
	//%%%%%%%%%%
	//%%%%%%%%%%
	//%%%%%%%%%%
	//MODOS-FUNCIONAMENTO
	private JLabel lblModoFuncionamento;
	private JRadioButton rdbtnModoManual;
	private JRadioButton rdbtnModoIntermitente;
	private JRadioButton rdbtnModoContinuo;
	private ButtonGroup rdgrpModoFuncionamento;
	//%%%%%%%%%%
	//%%%%%%%%%%
	//%%%%%%%%%%
	//CILINDRO
	private JLabel lblCilindro;
	//CILINDRO-MANUAL
	private JLabel lblBorderCilindroManual;
	private JRadioButton rdbtnModoManualCilindroLigado;
	private JRadioButton rdbtnModoManualCilindroDesligado;
	private ButtonGroup rdgrpModoManualCilindro;
	//CILINDRO-INTERMITENTE
	private JLabel lblBorderCilindroIntermitente;
	private JLabel lblModoIntermitenteCilindroLigadoHoras;
	private JLabel lblModoIntermitenteCilindroLigado;
	private JSpinner spnModoIntermitenteCilindroLigado;
	private JLabel lblModoIntermitenteCilindroLigadoMinutos;
	private JLabel lblModoIntermitenteCilindroDesligadoHoras;
	private JLabel lblModoIntermitenteCilindroDesligado;
	private JSpinner spnModoIntermitenteCilindroDesligado;
	private JLabel lblModoIntermitenteCilindroDesligadoMinutos;
	//CILINDRO-CONTÍNUO
	private JLabel lblBorderCilindroContinuo;
	private JLabel lblModoContinuoCilindroLigado;
	private JFormattedTextField txfModoContinuoCilindroLigado;
	private JLabel lblModoContinuoCilindroDesligado;
	private JFormattedTextField txfModoContinuoCilindroDesligado;
	//%%%%%%%%%%
	//%%%%%%%%%%
	//%%%%%%%%%%
	//VENTILADOR
	private JLabel lblVentilador;
	//VENTILADOR-MANUAL
	private JLabel lblBorderVentiladorManual;
	private JRadioButton rdbtnModoManualVentiladorLigado;
	private JRadioButton rdbtnModoManualVentiladorDesligado;
	private ButtonGroup rdgrpModoManualVentilador;
	//VENTILADOR-INTERMITENTE
	private JLabel lblBorderVentiladorIntermitente;
	private JLabel lblModoIntermitenteVentiladorLigadoHoras;
	private JLabel lblModoIntermitenteVentiladorLigado;
	private JSpinner spnModoIntermitenteVentiladorLigado;
	private JLabel lblModoIntermitenteVentiladorLigadoMinutos;
	private JLabel lblModoIntermitenteVentiladorDesligadoHoras;
	private JLabel lblModoIntermitenteVentiladorDesligado;
	private JSpinner spnModoIntermitenteVentiladorDesligado;
	private JLabel lblModoIntermitenteVentiladorDesligadoMinutos;
	//VENTILADOR-CONTÍNUO
	private JLabel lblBorderVentiladorContinuo;
	private JLabel lblModoContinuoVentiladorLigado;
	private JFormattedTextField txfModoContinuoVentiladorLigado;
	private JLabel lblModoContinuoVentiladorDesligado;
	private JFormattedTextField txfModoContinuoVentiladorDesligado;
	//%%%%%%%%%%
	//%%%%%%%%%%
	//%%%%%%%%%%
	//VENTILADOR
	private JLabel lblAuxiliar;
	//VENTILADOR-MANUAL
	private JLabel lblBorderAuxiliarManual;
	private JRadioButton rdbtnModoManualAuxiliarLigado;
	private JRadioButton rdbtnModoManualAuxiliarDesligado;
	private ButtonGroup rdgrpModoManualAuxiliar;
	//%%%%%%%%%%
	//%%%%%%%%%%
	//%%%%%%%%%%
	private JButton btnSalvar;
	//%%%%%%%%%%
	//%%%%%%%%%%
	//%%%%%%%%%%
	//TESTE!
//	private URL urlSecador = GerenciamentoMotoresFrame.class.getResource("images/secadorTudoEstatico.png");
	private URL urlSecador = GerenciamentoMotoresFrame.class.getResource("images/secador_full1.gif");
//	private URL urlSecador = GerenciamentoMotoresFrame.class.getResource("images/ventilador_full.gif");
	private Icon iconSecador = new ImageIcon(); 
	private JLabel lblSecador;
	

	//ATRIBUTOS PERTENCENTES AO LISTENER
	GerenciamentoMotoresListener gerenciamentoMotoresListener;
	
	
	//ATRIBUTOS DE IMAGENS
	URL urlIconCampotech = GerenciamentoRelatoriosFrame.class.getResource("images/icon-campotech.png");
	
	
	//CORES CUSTOMIZADAS
	Color VERDE_ESCURO = new Color(0, 193, 0); //0, 128, 0 => VERDE ESCURÃO
	
		
	/**
	 * Create the frame.
	 */
	public GerenciamentoMotoresFrame() {
		addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosed(WindowEvent arg0) {
				
				gerenciamentoMotoresListener.windowClosed();
				
			}
			
		});
		
		setTitle("Tela - Gerenciamento de Motores");
		//<CRIANDO ICONE PARA APLICAÇÃO
		setIconImage(Toolkit.getDefaultToolkit().getImage(urlIconCampotech).getScaledInstance(32, 32, java.awt.Image.SCALE_SMOOTH));
		//CRIANDO ICONE PARA APLICAÇÃO>
		setBounds(100, 100, 800, 600);
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
				
		
		//MODO-FUNCIONAMENTO
		lblModoFuncionamento = new JLabel();
		lblModoFuncionamento.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblModoFuncionamento.setBounds(10, 0, 160, 30);
		lblModoFuncionamento.setText("Modo de Funcionamento");
		contentPane.add(lblModoFuncionamento);
		
		
			//RDBTN-MODO-MANUAL
			rdbtnModoManual = new JRadioButton();
			rdbtnModoManual.setBounds(45,30, 70, 30);
			rdbtnModoManual.setText("Manual");
			rdbtnModoManual.setSelected(true);
			rdbtnModoManual.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					
					setRdbtnModoManualSelected(true);
					
				}
				
			});
			contentPane.add(rdbtnModoManual);
			
			
			//RDBTN-MODO-INTERMITENTE
			rdbtnModoIntermitente = new JRadioButton();
			rdbtnModoIntermitente.setBounds(45, 60, 90, 30);
			rdbtnModoIntermitente.setText("Intermitente");
			rdbtnModoIntermitente.setSelected(false);
			rdbtnModoIntermitente.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					
					setRdbtnModoIntermitenteSelected(true);
					
				}
				
			});
			contentPane.add(rdbtnModoIntermitente);
				
			
			//RDBTN-MODO-CONTÍNUO
			rdbtnModoContinuo = new JRadioButton();			
			rdbtnModoContinuo.setBounds(45, 90, 90, 30);
			rdbtnModoContinuo.setText("Contínuo");
			rdbtnModoContinuo.setSelected(false);
			rdbtnModoContinuo.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					
					setRdbtnModoContinuoSelected(true);
					
				}
				
			});
			contentPane.add(rdbtnModoContinuo);
					
			
			//RADIO-GROUP-MODO-FUNCIONAMENTO
			rdgrpModoFuncionamento = new ButtonGroup();
			rdgrpModoFuncionamento.add(rdbtnModoManual);
			rdgrpModoFuncionamento.add(rdbtnModoIntermitente);
			rdgrpModoFuncionamento.add(rdbtnModoContinuo);
			
		
		//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
		//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
		//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
		
		
		//CILINDRO
		lblCilindro = new JLabel();
		lblCilindro.setBounds(5, 150, 204, 290);
		lblCilindro.setBackground(Color.BLACK);
		//TIP:
		//TitledBorder(Border border, String title, int titleJustification, int titlePosition, Font titleFont, Color titleColor)  
		lblCilindro.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Cilindro", TitledBorder.CENTER, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 16), new Color(0, 0, 0)));
		contentPane.add(lblCilindro);
	
			
			//CILINDRO-MANUAL-BORDER
			lblBorderCilindroManual = new JLabel();
			lblBorderCilindroManual.setBounds(2, 20, 200, 72);
			lblBorderCilindroManual.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Manual", TitledBorder.CENTER, TitledBorder.BELOW_TOP, null, new Color(0, 0, 0)));
			lblCilindro.add(lblBorderCilindroManual);
		
			
				//RDBTN-MODO-MANUAL-CILINDRO-LIGADO
				rdbtnModoManualCilindroLigado = new JRadioButton();
				rdbtnModoManualCilindroLigado.setBounds(15, 30, 60, 30);
				rdbtnModoManualCilindroLigado.setForeground(VERDE_ESCURO);
				rdbtnModoManualCilindroLigado.setText("Ligado");
//				rdbtnModoManualCilindroLigado.setSelected(true);
				lblBorderCilindroManual.add(rdbtnModoManualCilindroLigado);
		
		
				//RDBTN-MODO-MANUAL-CILINDRO-DESLIGADO
				rdbtnModoManualCilindroDesligado = new JRadioButton();
				rdbtnModoManualCilindroDesligado.setBounds(103, 30, 80, 30);
				rdbtnModoManualCilindroDesligado.setForeground(Color.RED);
				rdbtnModoManualCilindroDesligado.setText("Desligado");
				rdbtnModoManualCilindroDesligado.setSelected(true);
				lblBorderCilindroManual.add(rdbtnModoManualCilindroDesligado);
		
		
				//RADIO-GROUP-CILINDRO
				rdgrpModoManualCilindro = new ButtonGroup();
				rdgrpModoManualCilindro.add(rdbtnModoManualCilindroLigado);
				rdgrpModoManualCilindro.add(rdbtnModoManualCilindroDesligado);
				
				
			//CILINDRO-INTERMITENTE-BORDER
			lblBorderCilindroIntermitente = new JLabel();
			lblBorderCilindroIntermitente.setBounds(2, 81, 200, 114);
			lblBorderCilindroIntermitente.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Intermitente", TitledBorder.CENTER, TitledBorder.BELOW_TOP, null, new Color(0, 0, 0)));
			lblBorderCilindroIntermitente.setEnabled(false);
			lblCilindro.add(lblBorderCilindroIntermitente);
		
			
				//LBL-MODO-INTERMITENTE-CILINDRO-LIGADO-HORAS
				lblModoIntermitenteCilindroLigadoHoras = new JLabel();
				lblModoIntermitenteCilindroLigadoHoras.setBounds(17, 20, 55, 30);
				lblModoIntermitenteCilindroLigadoHoras.setText("");
				lblModoIntermitenteCilindroLigadoHoras.setVisible(false);
				lblBorderCilindroIntermitente.add(lblModoIntermitenteCilindroLigadoHoras);
			
				
				//LBL-MODO-INTERMITENTE-CILINDRO-LIGADO
				lblModoIntermitenteCilindroLigado = new JLabel();
				lblModoIntermitenteCilindroLigado.setBounds(20, 35, 55, 30);
				lblModoIntermitenteCilindroLigado.setForeground(VERDE_ESCURO);
				lblModoIntermitenteCilindroLigado.setText("Ligado");
				lblBorderCilindroIntermitente.add(lblModoIntermitenteCilindroLigado);
				
				
				//SPN-MODO-INTERMITENTE-CILINDRO-LIGADO
				spnModoIntermitenteCilindroLigado = new JSpinner();
				spnModoIntermitenteCilindroLigado.setBounds(73, 35, 55, 30);
				spnModoIntermitenteCilindroLigado.setModel(new SpinnerNumberModel(1, 1, 360, 1)); //VALUE, MIN, MAX, STEP
				spnModoIntermitenteCilindroLigado.setEnabled(false);
				spnModoIntermitenteCilindroLigado.setValue(1);
				spnModoIntermitenteCilindroLigado.addChangeListener(new ChangeListener() {
					
					public void stateChanged(ChangeEvent arg0) {
						
						int horas = 0;
						int minutos = 0;
						
						if((int) spnModoIntermitenteCilindroLigado.getValue() > 59) {
							
							horas = (int) spnModoIntermitenteCilindroLigado.getValue() / 60;
							minutos = (int) spnModoIntermitenteCilindroLigado.getValue() % 60;
							lblModoIntermitenteCilindroLigadoHoras.setText(String.valueOf(horas) + "h" + String.valueOf(minutos) + "m");
							lblModoIntermitenteCilindroLigadoHoras.setVisible(true);
							
						} else lblModoIntermitenteCilindroLigadoHoras.setVisible(false);
						
					}
					
				});
				lblBorderCilindroIntermitente.add(spnModoIntermitenteCilindroLigado);
				
				
				//LBL-MODO-INTERMITENTE-CILINDRO-LIGADO-MINUTOS
				lblModoIntermitenteCilindroLigadoMinutos = new JLabel();
				lblModoIntermitenteCilindroLigadoMinutos.setBounds(133, 35, 55, 30);
				lblModoIntermitenteCilindroLigadoMinutos.setText("[minutos]");
				lblBorderCilindroIntermitente.add(lblModoIntermitenteCilindroLigadoMinutos);
					
				/////////////////////////////////////////////////////////////
				
				
				//LBL-MODO-INTERMITENTE-CILINDRO-DESLIGADO-HORAS
				lblModoIntermitenteCilindroDesligadoHoras = new JLabel();
				lblModoIntermitenteCilindroDesligadoHoras.setBounds(17, 80, 55, 30);
				lblModoIntermitenteCilindroDesligadoHoras.setText("");
				lblModoIntermitenteCilindroDesligadoHoras.setVisible(false);
				lblBorderCilindroIntermitente.add(lblModoIntermitenteCilindroDesligadoHoras);
				
				
				//LBL-MODO-INTERMITENTE-CILINDRO-DESLIGADO
				lblModoIntermitenteCilindroDesligado = new JLabel();
				lblModoIntermitenteCilindroDesligado.setBounds(13, 65, 65, 30);
				lblModoIntermitenteCilindroDesligado.setForeground(Color.RED);
				lblModoIntermitenteCilindroDesligado.setText("Desligado");
				lblBorderCilindroIntermitente.add(lblModoIntermitenteCilindroDesligado);
				
				
				//SPN-MODO-INTERMITENTE-CILINDRO-DESLIGADO
				spnModoIntermitenteCilindroDesligado = new JSpinner();
				spnModoIntermitenteCilindroDesligado.setBounds(73, 65, 55, 30);
				spnModoIntermitenteCilindroDesligado.setModel(new SpinnerNumberModel(1, 1, 360, 1)); //VALUE, MIN, MAX, STEP
				spnModoIntermitenteCilindroDesligado.setEnabled(false);
				spnModoIntermitenteCilindroDesligado.setValue(1);
				spnModoIntermitenteCilindroDesligado.addChangeListener(new ChangeListener() {
					
					public void stateChanged(ChangeEvent arg0) {
						
						int horas = 0;
						int minutos = 0;
						
						if((int) spnModoIntermitenteCilindroDesligado.getValue() > 59) {
							
							horas = (int) spnModoIntermitenteCilindroDesligado.getValue() / 60;
							minutos = (int) spnModoIntermitenteCilindroDesligado.getValue() % 60;
							lblModoIntermitenteCilindroDesligadoHoras.setText(String.valueOf(horas) + "h" + String.valueOf(minutos) + "m");
							lblModoIntermitenteCilindroDesligadoHoras.setVisible(true);
							
						} else lblModoIntermitenteCilindroDesligadoHoras.setVisible(false);
						
					}
					
				});
				lblBorderCilindroIntermitente.add(spnModoIntermitenteCilindroDesligado);
				
				
				//LBL-MODO-INTERMITENTE-CILINDRO-DESLIGADO-MINUTOS
				lblModoIntermitenteCilindroDesligadoMinutos = new JLabel();
				lblModoIntermitenteCilindroDesligadoMinutos.setBounds(133, 65, 55, 30);
				lblModoIntermitenteCilindroDesligadoMinutos.setText("[minutos]");
				lblBorderCilindroIntermitente.add(lblModoIntermitenteCilindroDesligadoMinutos);
				
				
			//CILINDRO-CONTÍNUO-BORDER
			lblBorderCilindroContinuo = new JLabel();
			lblBorderCilindroContinuo.setBounds(2, 184, 200, 104);
			lblBorderCilindroContinuo.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Contínuo", TitledBorder.CENTER, TitledBorder.BELOW_TOP, null, new Color(0, 0, 0)));
			lblBorderCilindroContinuo.setEnabled(false);
			lblCilindro.add(lblBorderCilindroContinuo);		
		
		
				//LBL-MODO-CONTÍNUO-CILINDRO-LIGADO
				lblModoContinuoCilindroLigado = new JLabel();
				lblModoContinuoCilindroLigado.setBounds(44, 33, 55, 30);
				lblModoContinuoCilindroLigado.setForeground(VERDE_ESCURO);
				lblModoContinuoCilindroLigado.setText("Ligar às");
				lblBorderCilindroContinuo.add(lblModoContinuoCilindroLigado);
			
			
				//TXF-MODO-CONTÍNUO-CILINDRO-LIGADO
				txfModoContinuoCilindroLigado = new JFormattedTextField(createFormatter("##h##m"));
				txfModoContinuoCilindroLigado.setBounds(100, 33, 60, 30);
				txfModoContinuoCilindroLigado.setEnabled(false);
				txfModoContinuoCilindroLigado.setText("0000");
				txfModoContinuoCilindroLigado.addMouseMotionListener(new MouseMotionAdapter() {
					
					@Override
					public void mouseDragged(MouseEvent arg0) {
						
						txfModoContinuoCilindroLigado.setCaretPosition(0);
						
					}
					
				});
				txfModoContinuoCilindroLigado.addMouseListener(new MouseAdapter() {
					
					@Override
					public void mouseClicked(MouseEvent arg0) {
						
						txfModoContinuoCilindroLigado.setCaretPosition(0);
						
					}
					
				});
				txfModoContinuoCilindroLigado.addFocusListener(new FocusAdapter() {
					
					@Override
					public void focusLost(FocusEvent arg0) {
						
						if(txfModoContinuoCilindroLigado.getText().contains("")) txfModoContinuoCilindroLigado.setText(txfModoContinuoCilindroLigado.getText().replaceAll(" ", "0"));
						
							int hora = Integer.parseInt(txfModoContinuoCilindroLigado.getText().substring(0, 2));
							int minuto = Integer.parseInt(txfModoContinuoCilindroLigado.getText().substring(3, 5));
							if(hora > 23) txfModoContinuoCilindroLigado.setText("23" + txfModoContinuoCilindroLigado.getText().substring(3, 5));
							if(minuto > 59) txfModoContinuoCilindroLigado.setText(txfModoContinuoCilindroLigado.getText().substring(0, 2) + "59");
						
						}
					
					@Override
					public void focusGained(FocusEvent e) {
							
						int hora = Integer.parseInt(txfModoContinuoCilindroLigado.getText().substring(0, 2));
						int minuto = Integer.parseInt(txfModoContinuoCilindroLigado.getText().substring(3, 5));
						System.out.println("HORA: " + hora);
						System.out.println("MINUTO: " + minuto);
						if(hora > 23) txfModoContinuoCilindroLigado.setText("23" + txfModoContinuoCilindroLigado.getText().substring(3, 5));
						if(minuto > 59) txfModoContinuoCilindroLigado.setText(txfModoContinuoCilindroLigado.getText().substring(0, 2) + "59");
						txfModoContinuoCilindroLigado.setCaretPosition(0);
						
					}
					
				});
				lblBorderCilindroContinuo.add(txfModoContinuoCilindroLigado);
			
				
				//LBL-MODO-CONTÍNUO-CILINDRO-DESLIGADO
				lblModoContinuoCilindroDesligado = new JLabel();
				lblModoContinuoCilindroDesligado.setBounds(35, 60, 65, 30);
				lblModoContinuoCilindroDesligado.setForeground(Color.RED);
				lblModoContinuoCilindroDesligado.setText("Desligar às");
				lblBorderCilindroContinuo.add(lblModoContinuoCilindroDesligado);
		
				
				//TXF-MODO-CONTÍNUO-VENTILADOR-DESLIGADO
				txfModoContinuoCilindroDesligado = new JFormattedTextField(createFormatter("##h##m"));
				txfModoContinuoCilindroDesligado.setBounds(100, 60, 60, 30);
				txfModoContinuoCilindroDesligado.setEnabled(false);
				txfModoContinuoCilindroDesligado.setText("0000");
				txfModoContinuoCilindroDesligado.addMouseMotionListener(new MouseMotionAdapter() {
					
					@Override
					public void mouseDragged(MouseEvent arg0) {
						
						txfModoContinuoCilindroDesligado.setCaretPosition(0);
						
					}
					
				});
				txfModoContinuoCilindroDesligado.addMouseListener(new MouseAdapter() {
					
					@Override
					public void mouseClicked(MouseEvent arg0) {
						
						txfModoContinuoCilindroDesligado.setCaretPosition(0);
						
					}
					
				});
				txfModoContinuoCilindroDesligado.addFocusListener(new FocusAdapter() {
					
					@Override
					public void focusLost(FocusEvent arg0) {
						
						if(txfModoContinuoCilindroDesligado.getText().contains("")) txfModoContinuoCilindroDesligado.setText(txfModoContinuoCilindroDesligado.getText().replaceAll(" ", "0"));
						
							int hora = Integer.parseInt(txfModoContinuoCilindroDesligado.getText().substring(0, 2));
							int minuto = Integer.parseInt(txfModoContinuoCilindroDesligado.getText().substring(3, 5));
							if(hora > 23) txfModoContinuoCilindroDesligado.setText("23" + txfModoContinuoCilindroDesligado.getText().substring(3, 5));
							if(minuto > 59) txfModoContinuoCilindroDesligado.setText(txfModoContinuoCilindroDesligado.getText().substring(0, 2) + "59");
						
						}
					
					@Override
					public void focusGained(FocusEvent e) {
							
						int hora = Integer.parseInt(txfModoContinuoCilindroDesligado.getText().substring(0, 2));
						int minuto = Integer.parseInt(txfModoContinuoCilindroDesligado.getText().substring(3, 5));
						System.out.println("HORA: " + hora);
						System.out.println("MINUTO: " + minuto);
						if(hora > 23) txfModoContinuoCilindroDesligado.setText("23" + txfModoContinuoCilindroDesligado.getText().substring(3, 5));
						if(minuto > 59) txfModoContinuoCilindroDesligado.setText(txfModoContinuoCilindroDesligado.getText().substring(0, 2) + "59");
						txfModoContinuoCilindroDesligado.setCaretPosition(0);
						
					}
					
				});
				lblBorderCilindroContinuo.add(txfModoContinuoCilindroDesligado);
				
				
		//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
		//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
		//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
		
		
		//VENTILADOR
		lblVentilador = new JLabel();
		lblVentilador.setBounds(585, 62, 204, 290);
		lblVentilador.setBackground(Color.BLACK);
		//TIP:
		//TitledBorder(Border border, String title, int titleJustification, int titlePosition, Font titleFont, Color titleColor)  
		lblVentilador.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Ventilador", TitledBorder.CENTER, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 16), new Color(0, 0, 0)));
		contentPane.add(lblVentilador);
	
			
			//VENTILADOR-MANUAL-BORDER
			lblBorderVentiladorManual = new JLabel();
			lblBorderVentiladorManual.setBounds(2, 20, 200, 72);
			lblBorderVentiladorManual.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Manual", TitledBorder.CENTER, TitledBorder.BELOW_TOP, null, new Color(0, 0, 0)));
			lblVentilador.add(lblBorderVentiladorManual);
		
			
				//RDBTN-MODO-MANUAL-VENTILADOR-LIGADO
				rdbtnModoManualVentiladorLigado = new JRadioButton();
				rdbtnModoManualVentiladorLigado.setBounds(15, 30, 60, 30);
				rdbtnModoManualVentiladorLigado.setForeground(VERDE_ESCURO);
				rdbtnModoManualVentiladorLigado.setText("Ligado");
//				rdbtnModoManualVentiladorLigado.setSelected(true);
				lblBorderVentiladorManual.add(rdbtnModoManualVentiladorLigado);
		
		
				//RDBTN-MODO-MANUAL-VENTILADOR-DESLIGADO
				rdbtnModoManualVentiladorDesligado = new JRadioButton();
				rdbtnModoManualVentiladorDesligado.setBounds(103, 30, 80, 30);
				rdbtnModoManualVentiladorDesligado.setForeground(Color.RED);
				rdbtnModoManualVentiladorDesligado.setText("Desligado");
				rdbtnModoManualVentiladorDesligado.setSelected(true);
				lblBorderVentiladorManual.add(rdbtnModoManualVentiladorDesligado);
		
				
				//RADIO-GROUP-VENTILADOR
				rdgrpModoManualVentilador = new ButtonGroup();
				rdgrpModoManualVentilador.add(rdbtnModoManualVentiladorLigado);
				rdgrpModoManualVentilador.add(rdbtnModoManualVentiladorDesligado);
		
				
			//VENTILADOR-INTERMITENTE-BORDER
			lblBorderVentiladorIntermitente = new JLabel();
			lblBorderVentiladorIntermitente.setBounds(2, 81, 200, 114); //590
			lblBorderVentiladorIntermitente.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Intermitente", TitledBorder.CENTER, TitledBorder.BELOW_TOP, null, new Color(0, 0, 0)));
			lblBorderVentiladorIntermitente.setEnabled(false);
			lblVentilador.add(lblBorderVentiladorIntermitente);
		
				
				//LBL-MODO-INTERMITENTE-VENTILADOR-LIGADO-HORAS
				lblModoIntermitenteVentiladorLigadoHoras = new JLabel();
				lblModoIntermitenteVentiladorLigadoHoras.setBounds(17, 20, 55, 30);
				lblModoIntermitenteVentiladorLigadoHoras.setText("xxhxxm");
				lblModoIntermitenteVentiladorLigadoHoras.setVisible(false);
				lblBorderVentiladorIntermitente.add(lblModoIntermitenteVentiladorLigadoHoras);
			
				
				//LBL-MODO-INTERMITENTE-VENTILADOR-LIGADO
				lblModoIntermitenteVentiladorLigado = new JLabel();
				lblModoIntermitenteVentiladorLigado.setBounds(20, 35, 55, 30);
				lblModoIntermitenteVentiladorLigado.setForeground(VERDE_ESCURO);
				lblModoIntermitenteVentiladorLigado.setText("Ligado");
				lblBorderVentiladorIntermitente.add(lblModoIntermitenteVentiladorLigado);
				
				
				//SPN-MODO-INTERMITENTE-VENTILADOR-LIGADO
				spnModoIntermitenteVentiladorLigado = new JSpinner();
				spnModoIntermitenteVentiladorLigado.setBounds(73, 35, 55, 30);
				spnModoIntermitenteVentiladorLigado.setModel(new SpinnerNumberModel(1, 1, 360, 1)); //VALUE, MIN, MAX, STEP
				spnModoIntermitenteVentiladorLigado.setEnabled(false);
				spnModoIntermitenteVentiladorLigado.setValue(1);
				spnModoIntermitenteVentiladorLigado.addChangeListener(new ChangeListener() {
					
					public void stateChanged(ChangeEvent arg0) {
						
						int horas = 0;
						int minutos = 0;
						
						if((int) spnModoIntermitenteVentiladorLigado.getValue() > 59) {
							
							horas = (int) spnModoIntermitenteVentiladorLigado.getValue() / 60;
							minutos = (int) spnModoIntermitenteVentiladorLigado.getValue() % 60;
							lblModoIntermitenteVentiladorLigadoHoras.setText(String.valueOf(horas) + "h" + String.valueOf(minutos) + "m");
							lblModoIntermitenteVentiladorLigadoHoras.setVisible(true);
							
						} else lblModoIntermitenteVentiladorLigadoHoras.setVisible(false);
						
					}
					
				});
				lblBorderVentiladorIntermitente.add(spnModoIntermitenteVentiladorLigado);
				
				
				//LBL-MODO-INTERMITENTE-VENTILADOR-LIGADO-MINUTOS
				lblModoIntermitenteVentiladorLigadoMinutos = new JLabel();
				lblModoIntermitenteVentiladorLigadoMinutos.setBounds(133, 35, 55, 30);
				lblModoIntermitenteVentiladorLigadoMinutos.setText("[minutos]");
				lblBorderVentiladorIntermitente.add(lblModoIntermitenteVentiladorLigadoMinutos);
					
				/////////////////////////////////////////////////////////////
				
				
				//LBL-MODO-INTERMITENTE-VENTILADOR-DESLIGADO-HORAS
				lblModoIntermitenteVentiladorDesligadoHoras = new JLabel();
				lblModoIntermitenteVentiladorDesligadoHoras.setBounds(17, 80, 55, 30);
				lblModoIntermitenteVentiladorDesligadoHoras.setText("xxhxxm");
				lblModoIntermitenteVentiladorDesligadoHoras.setVisible(false);
				lblBorderVentiladorIntermitente.add(lblModoIntermitenteVentiladorDesligadoHoras);
				
				
				//LBL-MODO-INTERMITENTE-VENTILADOR-DESLIGADO
				lblModoIntermitenteVentiladorDesligado = new JLabel();
				lblModoIntermitenteVentiladorDesligado.setBounds(13, 65, 65, 30);
				lblModoIntermitenteVentiladorDesligado.setForeground(Color.RED);
				lblModoIntermitenteVentiladorDesligado.setText("Desligado");
				lblBorderVentiladorIntermitente.add(lblModoIntermitenteVentiladorDesligado);
				
				//SPN-MODO-INTERMITENTE-VENTILADOR-DESLIGADO
				spnModoIntermitenteVentiladorDesligado = new JSpinner();
				spnModoIntermitenteVentiladorDesligado.setBounds(73, 65, 55, 30);
				spnModoIntermitenteVentiladorDesligado.setModel(new SpinnerNumberModel(1, 1, 360, 1)); //VALUE, MIN, MAX, STEP
				spnModoIntermitenteVentiladorDesligado.setEnabled(false);
				spnModoIntermitenteVentiladorDesligado.setValue(1);
				spnModoIntermitenteVentiladorDesligado.addChangeListener(new ChangeListener() {
					
					public void stateChanged(ChangeEvent arg0) {
						
						int horas = 0;
						int minutos = 0;
						
						if((int) spnModoIntermitenteVentiladorDesligado.getValue() > 59) {
							
							horas = (int) spnModoIntermitenteVentiladorDesligado.getValue() / 60;
							minutos = (int) spnModoIntermitenteVentiladorDesligado.getValue() % 60;
							lblModoIntermitenteVentiladorDesligadoHoras.setText(String.valueOf(horas) + "h" + String.valueOf(minutos) + "m");
							lblModoIntermitenteVentiladorDesligadoHoras.setVisible(true);
							
						} else lblModoIntermitenteVentiladorDesligadoHoras.setVisible(false);
						
					}
					
				});
				lblBorderVentiladorIntermitente.add(spnModoIntermitenteVentiladorDesligado);
				
				
				//LBL-MODO-INTERMITENTE-VENTILADOR-DESLIGADO-MINUTOS
				lblModoIntermitenteVentiladorDesligadoMinutos = new JLabel();
				lblModoIntermitenteVentiladorDesligadoMinutos.setBounds(133, 65, 55, 30);
				lblModoIntermitenteVentiladorDesligadoMinutos.setText("[minutos]");
				lblBorderVentiladorIntermitente.add(lblModoIntermitenteVentiladorDesligadoMinutos);
				
				
			//VENTILADOR-CONTÍNUO-BORDER
			lblBorderVentiladorContinuo = new JLabel();
			lblBorderVentiladorContinuo.setBounds(2, 184, 200, 104);
			lblBorderVentiladorContinuo.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Contínuo", TitledBorder.CENTER, TitledBorder.BELOW_TOP, null, new Color(0, 0, 0)));
			lblBorderVentiladorContinuo.setEnabled(false);
			lblVentilador.add(lblBorderVentiladorContinuo);		
		
		
				//LBL-MODO-CONTÍNUO-VENTILADOR-LIGADO
				lblModoContinuoVentiladorLigado = new JLabel();
				lblModoContinuoVentiladorLigado.setBounds(44, 33, 55, 30);
				lblModoContinuoVentiladorLigado.setForeground(VERDE_ESCURO);
				lblModoContinuoVentiladorLigado.setText("Ligar às");
				lblBorderVentiladorContinuo.add(lblModoContinuoVentiladorLigado);
			
			
				//TXF-MODO-CONTÍNUO-VENTILADOR-LIGADO
				txfModoContinuoVentiladorLigado = new JFormattedTextField(createFormatter("##h##m"));
				txfModoContinuoVentiladorLigado.setBounds(100, 33, 60, 30);
				txfModoContinuoVentiladorLigado.setEnabled(false);
				txfModoContinuoVentiladorLigado.setText("0000");
				txfModoContinuoVentiladorLigado.addMouseMotionListener(new MouseMotionAdapter() {
					
					@Override
					public void mouseDragged(MouseEvent arg0) {
						
						txfModoContinuoVentiladorLigado.setCaretPosition(0);
						
					}
					
				});
				txfModoContinuoVentiladorLigado.addMouseListener(new MouseAdapter() {
					
					@Override
					public void mouseClicked(MouseEvent arg0) {
						
						txfModoContinuoVentiladorLigado.setCaretPosition(0);
						
					}
					
				});
				txfModoContinuoVentiladorLigado.addFocusListener(new FocusAdapter() {
					
					@Override
					public void focusLost(FocusEvent arg0) {
						
						if(txfModoContinuoVentiladorLigado.getText().contains("")) txfModoContinuoVentiladorLigado.setText(txfModoContinuoVentiladorLigado.getText().replaceAll(" ", "0"));
						
							int hora = Integer.parseInt(txfModoContinuoVentiladorLigado.getText().substring(0, 2));
							int minuto = Integer.parseInt(txfModoContinuoVentiladorLigado.getText().substring(3, 5));
							if(hora > 23) txfModoContinuoVentiladorLigado.setText("23" + txfModoContinuoVentiladorLigado.getText().substring(3, 5));
							if(minuto > 59) txfModoContinuoVentiladorLigado.setText(txfModoContinuoVentiladorLigado.getText().substring(0, 2) + "59");
						
						}
					
					@Override
					public void focusGained(FocusEvent e) {
							
						int hora = Integer.parseInt(txfModoContinuoVentiladorLigado.getText().substring(0, 2));
						int minuto = Integer.parseInt(txfModoContinuoVentiladorLigado.getText().substring(3, 5));
						System.out.println("HORA: " + hora);
						System.out.println("MINUTO: " + minuto);
						if(hora > 23) txfModoContinuoVentiladorLigado.setText("23" + txfModoContinuoVentiladorLigado.getText().substring(3, 5));
						if(minuto > 59) txfModoContinuoVentiladorLigado.setText(txfModoContinuoVentiladorLigado.getText().substring(0, 2) + "59");
						txfModoContinuoVentiladorLigado.setCaretPosition(0);
						
					}
					
				});
				lblBorderVentiladorContinuo.add(txfModoContinuoVentiladorLigado);
			
				
				//LBL-MODO-CONTÍNUO-VENTILADOR-DESLIGADO
				lblModoContinuoVentiladorDesligado = new JLabel();
				lblModoContinuoVentiladorDesligado.setBounds(35, 60, 65, 30);
				lblModoContinuoVentiladorDesligado.setForeground(Color.RED);
				lblModoContinuoVentiladorDesligado.setText("Desligar às");
				lblBorderVentiladorContinuo.add(lblModoContinuoVentiladorDesligado);
		
				
				//TXF-MODO-CONTÍNUO-VENTILADOR-DESLIGADO
				txfModoContinuoVentiladorDesligado = new JFormattedTextField(createFormatter("##h##m"));
				txfModoContinuoVentiladorDesligado.setBounds(100, 60, 60, 30);
				txfModoContinuoVentiladorDesligado.setEnabled(false);
				txfModoContinuoVentiladorDesligado.setText("0000");
				txfModoContinuoVentiladorDesligado.addMouseMotionListener(new MouseMotionAdapter() {
					
					@Override
					public void mouseDragged(MouseEvent arg0) {
						
						txfModoContinuoVentiladorDesligado.setCaretPosition(0);
						
					}
					
				});
				txfModoContinuoVentiladorDesligado.addMouseListener(new MouseAdapter() {
					
					@Override
					public void mouseClicked(MouseEvent arg0) {
						
						txfModoContinuoVentiladorDesligado.setCaretPosition(0);
						
					}
					
				});
				txfModoContinuoVentiladorDesligado.addFocusListener(new FocusAdapter() {
					
					@Override
					public void focusLost(FocusEvent arg0) {
						
						if(txfModoContinuoVentiladorDesligado.getText().contains("")) txfModoContinuoVentiladorDesligado.setText(txfModoContinuoVentiladorDesligado.getText().replaceAll(" ", "0"));
						
							int hora = Integer.parseInt(txfModoContinuoVentiladorDesligado.getText().substring(0, 2));
							int minuto = Integer.parseInt(txfModoContinuoVentiladorDesligado.getText().substring(3, 5));
							if(hora > 23) txfModoContinuoVentiladorDesligado.setText("23" + txfModoContinuoVentiladorDesligado.getText().substring(3, 5));
							if(minuto > 59) txfModoContinuoVentiladorDesligado.setText(txfModoContinuoVentiladorDesligado.getText().substring(0, 2) + "59");
						
						}
					
					@Override
					public void focusGained(FocusEvent e) {
							
						int hora = Integer.parseInt(txfModoContinuoVentiladorDesligado.getText().substring(0, 2));
						int minuto = Integer.parseInt(txfModoContinuoVentiladorDesligado.getText().substring(3, 5));
						System.out.println("HORA: " + hora);
						System.out.println("MINUTO: " + minuto);
						if(hora > 23) txfModoContinuoVentiladorDesligado.setText("23" + txfModoContinuoVentiladorDesligado.getText().substring(3, 5));
						if(minuto > 59) txfModoContinuoVentiladorDesligado.setText(txfModoContinuoVentiladorDesligado.getText().substring(0, 2) + "59");
						txfModoContinuoVentiladorDesligado.setCaretPosition(0);
						
					}
					
				});
				lblBorderVentiladorContinuo.add(txfModoContinuoVentiladorDesligado);
		
		
		//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
		//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
		//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
			
			
		//AUXILIAR
		lblAuxiliar = new JLabel();
		lblAuxiliar.setBounds(585, 400, 204, 94);
		lblAuxiliar.setBackground(Color.BLACK);
		//TIP:
		//TitledBorder(Border border, String title, int titleJustification, int titlePosition, Font titleFont, Color titleColor)  
		lblAuxiliar.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Auxiliar", TitledBorder.CENTER, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 16), new Color(0, 0, 0)));
		contentPane.add(lblAuxiliar);	
				
			
			//AUXILIAR-MANUAL-BORDER
			lblBorderAuxiliarManual = new JLabel();
			lblBorderAuxiliarManual.setBounds(2, 20, 200, 72);
			lblBorderAuxiliarManual.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Manual", TitledBorder.CENTER, TitledBorder.BELOW_TOP, null, new Color(0, 0, 0)));
			lblAuxiliar.add(lblBorderAuxiliarManual);
		
			
				//RDBTN-MODO-MANUAL-AUXILIAR-LIGADO
				rdbtnModoManualAuxiliarLigado = new JRadioButton();
				rdbtnModoManualAuxiliarLigado.setBounds(15, 30, 60, 30);
				rdbtnModoManualAuxiliarLigado.setForeground(VERDE_ESCURO);
				rdbtnModoManualAuxiliarLigado.setText("Ligado");
//				rdbtnModoManualAuxiliarLigado.setSelected(true);
				lblBorderAuxiliarManual.add(rdbtnModoManualAuxiliarLigado);
		
		
				//RDBTN-MODO-MANUAL-AUXILIAR-DESLIGADO
				rdbtnModoManualAuxiliarDesligado = new JRadioButton();
				rdbtnModoManualAuxiliarDesligado.setBounds(103, 30, 80, 30);
				rdbtnModoManualAuxiliarDesligado.setForeground(Color.RED);
				rdbtnModoManualAuxiliarDesligado.setText("Desligado");
				rdbtnModoManualAuxiliarDesligado.setSelected(true);
				lblBorderAuxiliarManual.add(rdbtnModoManualAuxiliarDesligado);
		
				
				//RADIO-GROUP-AUXILIAR
				rdgrpModoManualAuxiliar= new ButtonGroup();
				rdgrpModoManualAuxiliar.add(rdbtnModoManualAuxiliarLigado);
				rdgrpModoManualAuxiliar.add(rdbtnModoManualAuxiliarDesligado);
				
				
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
				//TESTE!
				lblSecador = new JLabel();
				lblSecador.setBounds(235, 0, 320, 600);
				iconSecador = new ImageIcon(urlSecador);
				lblSecador.setIcon(iconSecador);
				contentPane.add(lblSecador);
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
		
		
		
		//BTN-SALVAR
		btnSalvar = new JButton();		
		btnSalvar.setBounds(715, 523, 70, 40);
		btnSalvar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSalvar.setText("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				//PREPARANDO VIEW DO FRAME
				//*****************************************
				btnSalvar.setEnabled(false);
				//*****************************************
				
				//SE O MODO É MANUAL/INTERMITENTE, NÃO TEM PROBLEMA DE COLISÃO DE HORÁRIO
				if(!rdbtnModoContinuo.isSelected()) getComandoBA();
				else {
					
					String txfCilindroTimeON = txfModoContinuoCilindroLigado.getText().substring(0, 2) + txfModoContinuoCilindroLigado.getText().substring(3, 5);
					String txfCilindroTimeOFF = txfModoContinuoCilindroDesligado.getText().substring(0, 2) + txfModoContinuoCilindroDesligado.getText().substring(3, 5);
					String txfVentiladorTimeON = txfModoContinuoVentiladorLigado.getText().substring(0, 2) + txfModoContinuoVentiladorLigado.getText().substring(3, 5);
					String txfVentiladorTimeOFF = txfModoContinuoVentiladorDesligado.getText().substring(0, 2) + txfModoContinuoVentiladorDesligado.getText().substring(3, 5);
					
					if(txfCilindroTimeON.equals(txfCilindroTimeOFF)) {
						
						JOptionPane.showMessageDialog(null, "Horário de Ligado e Desligado do Cilindro são os mesmos.\n Altere para horários diferentes e tente novamente.", "[Cilindro] Alerta - Horários Iguais", JOptionPane.WARNING_MESSAGE);
						
						//PREPARANDO VIEW DO FRAME
						//*****************************************
						btnSalvar.setEnabled(true);
						//*****************************************
						
					} else if(txfVentiladorTimeON.equals(txfVentiladorTimeOFF)) {
						
						JOptionPane.showMessageDialog(null, "Horário de Ligado e Desligado do Ventilador são os mesmos.\n Altere para horários diferentes e tente novamente.", "[Ventilador] Alerta - Horários Iguais", JOptionPane.WARNING_MESSAGE);
						
						//PREPARANDO VIEW DO FRAME
						//*****************************************
						btnSalvar.setEnabled(true);
						//*****************************************
						
					} else getComandoBA();
					
				}
				
			}
			
		});
		btnSalvar.setEnabled(true);
		contentPane.add(btnSalvar);
		
		
		//TAB-ORDER
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{rdbtnModoManual, rdbtnModoIntermitente, rdbtnModoContinuo, rdbtnModoManualCilindroLigado, rdbtnModoManualCilindroDesligado, spnModoIntermitenteCilindroLigado, spnModoIntermitenteCilindroDesligado, txfModoContinuoCilindroLigado, txfModoContinuoCilindroLigado, rdbtnModoManualVentiladorLigado, rdbtnModoManualVentiladorDesligado, spnModoIntermitenteVentiladorLigado, spnModoIntermitenteVentiladorDesligado, txfModoContinuoVentiladorLigado, txfModoContinuoVentiladorLigado, rdbtnModoManualAuxiliarLigado, rdbtnModoManualAuxiliarDesligado, btnSalvar}));
		
	}
	
	
///////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////
	
	
	//GETTERS	
	public void getComandoBA() {
		
		//FORMATO
		//AA-BA-B1-B2-B3-B4-B5-B6-B7-B8-B9-CHKSUM-5
		String comandoBAHex = "BA";
		String comandoBABinary = "10111010"; //BA
		
		
		//******
		//BYTE-1
		//******
		String dadoByte1Binary = new String();
		String dadoByte1Hex = new String();
		//BYTE-1 [BITs-7-6-5: ModoOperação-Sensor2-Sensor1 - Utilizados apenas na Leitura]
		dadoByte1Binary = "000";
		//BYTE-1 [BITs-4-3-2: AuxiliarStart-VentiladorStart-CilindroStart - Utilizados nos Modos Intermitente e Contínuo]
		if(!rdbtnModoManual.isSelected()) {
			
			//BYTE-1 [BIT-4: AuxliarSituaçãoInicialON/OFF]
			if(rdbtnModoManualAuxiliarLigado.isSelected()) dadoByte1Binary += "1";
			else dadoByte1Binary += "0";
			//BYTE-1 [BIT-3: VentiladorSituaçãoInicialON/OFF]
			if(rdbtnModoManualVentiladorLigado.isSelected()) dadoByte1Binary += "1";
			else dadoByte1Binary += "0";
			//BYTE-1 [BIT-2: CilindroSituaçãoInicialON/OFF]
			if(rdbtnModoManualCilindroLigado.isSelected()) dadoByte1Binary += "1";
			else dadoByte1Binary += "0";
			
		} else dadoByte1Binary += "000"; //STATUS DAS SAÍDAS SERÃO CONFIGURADOS NO BYTE-2 [BITs-2-1-0]
		
		
		//BYTE-1 [BIT-1-0: ModoFundionamentoManual/Intermitente/Contínuo]
		if(rdbtnModoManual.isSelected()) dadoByte1Binary += "01";
		else if(rdbtnModoIntermitente.isSelected()) dadoByte1Binary += "10";
		else dadoByte1Binary += "11";
		
		System.out.println("BYTE-1-BIN: " + dadoByte1Binary);
		dadoByte1Hex = Integer.toString(Integer.parseInt(dadoByte1Binary, 2), 16).toUpperCase();
		if(dadoByte1Hex.length() < 2) dadoByte1Hex = "0" + dadoByte1Hex;
		System.out.println("BYTE-1-HEX: " + dadoByte1Hex);
		
		
		//////////////////////////////////////////////////
		//////////////////////////////////////////////////
		System.out.println("======");
		
		
		//********
		//BYTE-2-3
		//********
		String dadoByte2Binary = new String();
		String dadoByte2Hex = new String();
		String dadoByte3Binary = new String();
		String dadoByte3Hex = new String();
		StringBuilder stringBuilderByte2Byte3 = new StringBuilder();
		
		//BYTE-2 [BITs-7-6-5-4-3 (MANUAL) ou BITs-7-6-5-4 (INTERMITENTE/CONTÍNUO): NãoUtilizados .:. 0s]
		if(rdbtnModoManual.isSelected()) {
		
			stringBuilderByte2Byte3.insert(0, "00000");
			
			//BYTE-2 [BITs-2-1-0: Aux/Ventilador/Cilindro ou BITs-3-2-1-0: D[MSB]-D-D-D]
			//BYTE-3 [BITs-7..0: D-D-D-D-D-D-D-D[LSB]]
			if(rdbtnModoManualAuxiliarLigado.isSelected()) stringBuilderByte2Byte3.append("1");
			else stringBuilderByte2Byte3.append("0");
			
			if(rdbtnModoManualVentiladorLigado.isSelected()) stringBuilderByte2Byte3.append("1");
			else stringBuilderByte2Byte3.append("0");
			
			if(rdbtnModoManualCilindroLigado.isSelected()) stringBuilderByte2Byte3.append("1");
			else stringBuilderByte2Byte3.append("0");
			
			while(stringBuilderByte2Byte3.length() < 16) stringBuilderByte2Byte3.append("0");
			
			
		//INTERMITENTE [ATRIBUI VALOR BINÁRIO DO TEMPO-ON DA SAÍDA CILINDRO]
		} else if(rdbtnModoIntermitente.isSelected()) {
			
			stringBuilderByte2Byte3.append(Integer.toBinaryString((int) spnModoIntermitenteCilindroLigado.getValue()));			
			
			while(stringBuilderByte2Byte3.length() < 16) stringBuilderByte2Byte3.insert(0, "0"); 
			
		//CONTÍNUO [ATRIBUI VALOR BINÁRIO DO TEMPO-ON DA SAÍDA CILINDRO]	
		} else if(rdbtnModoContinuo.isSelected()) {
			
			int minutosCilindroLigado = 0;
			minutosCilindroLigado = (Integer.parseInt(txfModoContinuoCilindroLigado.getText().substring(0, 2)) * 60) + Integer.parseInt(txfModoContinuoCilindroLigado.getText().substring(3, 5));
			String minutosCilindroLigadoBinary = new String();
			minutosCilindroLigadoBinary = Integer.toString(minutosCilindroLigado, 2);
			while(minutosCilindroLigadoBinary.length() < 16) minutosCilindroLigadoBinary = "0" + minutosCilindroLigadoBinary;
			stringBuilderByte2Byte3.append(minutosCilindroLigadoBinary);
			
		}
		dadoByte2Binary = stringBuilderByte2Byte3.substring(0, 8);
		dadoByte2Hex = Integer.toString(Integer.parseInt(dadoByte2Binary, 2), 16).toUpperCase();
		if(dadoByte2Hex.length() < 2) dadoByte2Hex = "0" + dadoByte2Hex;
		System.out.println("BYTE-2-BIN: " + dadoByte2Binary);
		System.out.println("BYTE-2-HEX: " + dadoByte2Hex);
		
		System.out.println("======");
		
		dadoByte3Binary = stringBuilderByte2Byte3.substring(8);
		dadoByte3Hex = Integer.toString(Integer.parseInt(dadoByte3Binary, 2), 16).toUpperCase();
		if(dadoByte3Hex.length() < 2) dadoByte3Hex = "0" + dadoByte3Hex;
		System.out.println("BYTE-3-BIN: " + dadoByte3Binary);
		System.out.println("BYTE-3-HEX: " + dadoByte3Hex);
		
		
		//////////////////////////////////////////////////
		//////////////////////////////////////////////////
		System.out.println("======");
		
		
		//********
		//BYTE-4-5
		//********
		String dadoByte4Binary = new String();
		String dadoByte4Hex = new String();
		String dadoByte5Binary = new String();
		String dadoByte5Hex = new String();
		StringBuilder stringBuilderByte4Byte5 = new StringBuilder();
		
		//BYTES-4-5 [BITs-7-6-5-4-3-2-1-0 (MANUAL): NãoUtilizados .:. 0s]
		if(rdbtnModoManual.isSelected()) {
		
			stringBuilderByte4Byte5.append("0000000000000000");			
			
		//INTERMITENTE [ATRIBUI VALOR BINÁRIO DO TEMPO-OFF DA SAÍDA CILINDRO]
		} else if(rdbtnModoIntermitente.isSelected()) {
			
			stringBuilderByte4Byte5.append(Integer.toBinaryString((int) spnModoIntermitenteCilindroDesligado.getValue()));			
			
			while(stringBuilderByte4Byte5.length() < 16) stringBuilderByte4Byte5.insert(0, "0"); 
			
		//CONTÍNUO [ATRIBUI VALOR BINÁRIO DO TEMPO-OFF DA SAÍDA CILINDRO]	
		} else if(rdbtnModoContinuo.isSelected()) {
			
			int minutosCilindroDesligado = 0;
			minutosCilindroDesligado = (Integer.parseInt(txfModoContinuoCilindroDesligado.getText().substring(0, 2)) * 60) + Integer.parseInt(txfModoContinuoCilindroDesligado.getText().substring(3, 5));
			String minutosCilindroDesligadoBinary = new String();
			minutosCilindroDesligadoBinary = Integer.toString(minutosCilindroDesligado, 2);
			while(minutosCilindroDesligadoBinary.length() < 16) minutosCilindroDesligadoBinary = "0" + minutosCilindroDesligadoBinary;
			stringBuilderByte4Byte5.append(minutosCilindroDesligadoBinary);
			
		}
		dadoByte4Binary = stringBuilderByte4Byte5.substring(0, 8);
		dadoByte4Hex = Integer.toString(Integer.parseInt(dadoByte4Binary, 2), 16).toUpperCase();
		if(dadoByte4Hex.length() < 2) dadoByte4Hex = "0" + dadoByte4Hex;
		System.out.println("BYTE-4-BIN: " + dadoByte4Binary);
		System.out.println("BYTE-4-HEX: " + dadoByte4Hex);
		
		System.out.println("======");
		
		dadoByte5Binary = stringBuilderByte4Byte5.substring(8);
		dadoByte5Hex = Integer.toString(Integer.parseInt(dadoByte5Binary, 2), 16).toUpperCase();
		if(dadoByte5Hex.length() < 2) dadoByte5Hex = "0" + dadoByte5Hex;
		System.out.println("BYTE-5-BIN: " + dadoByte5Binary);
		System.out.println("BYTE-5-HEX: " + dadoByte5Hex);		
	
		
		//////////////////////////////////////////////////
		//////////////////////////////////////////////////
		System.out.println("======");
		
		
		//********
		//BYTE-6-7
		//********
		String dadoByte6Binary = new String();
		String dadoByte6Hex = new String();
		String dadoByte7Binary = new String();
		String dadoByte7Hex = new String();
		StringBuilder stringBuilderByte6Byte7 = new StringBuilder();
		
		//BYTES-6-7 [BITs-7-6-5-4-3-2-1-0 (MANUAL): NãoUtilizados .:. 0s]
		if(rdbtnModoManual.isSelected()) {
		
		stringBuilderByte6Byte7.append("0000000000000000");			
		
		//INTERMITENTE [ATRIBUI VALOR BINÁRIO DO TEMPO-ON DA SAÍDA VENTILADOR]
		} else if(rdbtnModoIntermitente.isSelected()) {
		
		stringBuilderByte6Byte7.append(Integer.toBinaryString((int) spnModoIntermitenteVentiladorLigado.getValue()));			
		
		while(stringBuilderByte6Byte7.length() < 16) stringBuilderByte6Byte7.insert(0, "0"); 
		
		//CONTÍNUO [ATRIBUI VALOR BINÁRIO DO TEMPO-ON DA SAÍDA VENTILADOR]	
		} else if(rdbtnModoContinuo.isSelected()) {
		
		int minutosVentiladorLigado = 0;
		minutosVentiladorLigado = (Integer.parseInt(txfModoContinuoVentiladorLigado.getText().substring(0, 2)) * 60) + Integer.parseInt(txfModoContinuoVentiladorLigado.getText().substring(3, 5));
		String minutosVentiladorLigadoBinary = new String();
		minutosVentiladorLigadoBinary = Integer.toString(minutosVentiladorLigado, 2);
		while(minutosVentiladorLigadoBinary.length() < 16) minutosVentiladorLigadoBinary = "0" + minutosVentiladorLigadoBinary;
		stringBuilderByte6Byte7.append(minutosVentiladorLigadoBinary);
		
		}
		dadoByte6Binary = stringBuilderByte6Byte7.substring(0, 8);
		dadoByte6Hex = Integer.toString(Integer.parseInt(dadoByte6Binary, 2), 16).toUpperCase();
		if(dadoByte6Hex.length() < 2) dadoByte6Hex = "0" + dadoByte6Hex;
		System.out.println("BYTE-6-BIN: " + dadoByte6Binary);
		System.out.println("BYTE-6-HEX: " + dadoByte6Hex);
		
		System.out.println("======");
		
		dadoByte7Binary = stringBuilderByte6Byte7.substring(8);
		dadoByte7Hex = Integer.toString(Integer.parseInt(dadoByte7Binary, 2), 16).toUpperCase();
		if(dadoByte7Hex.length() < 2) dadoByte7Hex = "0" + dadoByte7Hex;
		System.out.println("BYTE-7-BIN: " + dadoByte7Binary);
		System.out.println("BYTE-7-HEX: " + dadoByte7Hex);
		
		
		//////////////////////////////////////////////////
		//////////////////////////////////////////////////
		System.out.println("======");
		
		
		//********
		//BYTE-8-9
		//********
		String dadoByte8Binary = new String();
		String dadoByte8Hex = new String();
		String dadoByte9Binary = new String();
		String dadoByte9Hex = new String();
		StringBuilder stringBuilderByte8Byte9 = new StringBuilder();
		
		//BYTES-8-9 [BITs-7-6-5-4-3-2-1-0 (MANUAL): NãoUtilizados .:. 0s]
		if(rdbtnModoManual.isSelected()) {
		
		stringBuilderByte8Byte9.append("0000000000000000");			
		
		//INTERMITENTE [ATRIBUI VALOR BINÁRIO DO TEMPO-OFF DA SAÍDA VENTILADOR]
		} else if(rdbtnModoIntermitente.isSelected()) {
		
		stringBuilderByte8Byte9.append(Integer.toBinaryString((int) spnModoIntermitenteVentiladorDesligado.getValue()));			
		while(stringBuilderByte8Byte9.length() < 16) stringBuilderByte8Byte9.insert(0, "0"); 
		
		//CONTÍNUO [ATRIBUI VALOR BINÁRIO DO TEMPO-ON DA SAÍDA VENTILADOR]	
		} else if(rdbtnModoContinuo.isSelected()) {
		
		int minutosVentiladorDesligado = 0;
		minutosVentiladorDesligado = (Integer.parseInt(txfModoContinuoVentiladorDesligado.getText().substring(0, 2)) * 60) + Integer.parseInt(txfModoContinuoVentiladorDesligado.getText().substring(3, 5));
		String minutosVentiladorDesligadoBinary = new String();
		minutosVentiladorDesligadoBinary = Integer.toString(minutosVentiladorDesligado, 2);
		while(minutosVentiladorDesligadoBinary.length() < 16) minutosVentiladorDesligadoBinary = "0" + minutosVentiladorDesligadoBinary;
		stringBuilderByte8Byte9.append(minutosVentiladorDesligadoBinary);
		
		}
		dadoByte8Binary = stringBuilderByte8Byte9.substring(0, 8);
		dadoByte8Hex = Integer.toString(Integer.parseInt(dadoByte8Binary, 2), 16).toUpperCase();
		if(dadoByte8Hex.length() < 2) dadoByte8Hex = "0" + dadoByte8Hex;
		System.out.println("BYTE-8-BIN: " + dadoByte8Binary);
		System.out.println("BYTE-8-HEX: " + dadoByte8Hex);
		
		System.out.println("======");
		
		dadoByte9Binary = stringBuilderByte8Byte9.substring(8);
		dadoByte9Hex = Integer.toString(Integer.parseInt(dadoByte9Binary, 2), 16).toUpperCase();
		if(dadoByte9Hex.length() < 2) dadoByte9Hex = "0" + dadoByte9Hex;
		System.out.println("BYTE-9-BIN: " + dadoByte9Binary);
		System.out.println("BYTE-9-HEX: " + dadoByte9Hex);
		
		
		//////////////////////////////////////////////////
		//////////////////////////////////////////////////
		System.out.println("======");
	
		
		//*********
		//CHECK-SUM
		//*********
		
		//***
		//XOR
		//***
		StringBuilder checkSum1 = new StringBuilder();
		checkSum1.setLength(0);
		for(int i = 0 ; i < 8; i++) {
			
			checkSum1.append(((comandoBABinary.charAt(i)) ^ (dadoByte1Binary.charAt(i))));

		}
		String aux = checkSum1.toString();
		checkSum1.setLength(0);
		for(int i = 0 ; i < 8; i++) {
			
			checkSum1.append(((aux.charAt(i)) ^ (dadoByte2Binary.charAt(i))));
			
		}
		aux = checkSum1.toString();
		checkSum1.setLength(0);
		for(int i = 0 ; i < 8; i++) {
			
			checkSum1.append(((aux.charAt(i)) ^ (dadoByte3Binary.charAt(i))));
			
		}
		aux = checkSum1.toString();
		checkSum1.setLength(0);
		for(int i = 0 ; i < 8; i++) {
			
			checkSum1.append(((aux.charAt(i)) ^ (dadoByte4Binary.charAt(i))));
			
		}
		aux = checkSum1.toString();
		checkSum1.setLength(0);
		for(int i = 0 ; i < 8; i++) {
			
			checkSum1.append(((aux.charAt(i)) ^ (dadoByte5Binary.charAt(i))));
			
		}
		aux = checkSum1.toString();
		checkSum1.setLength(0);
		for(int i = 0 ; i < 8; i++) {
			
			checkSum1.append(((aux.charAt(i)) ^ (dadoByte6Binary.charAt(i))));
			
		}
		aux = checkSum1.toString();
		checkSum1.setLength(0);
		for(int i = 0 ; i < 8; i++) {
			
			checkSum1.append(((aux.charAt(i)) ^ (dadoByte7Binary.charAt(i))));
			
		}
		aux = checkSum1.toString();
		checkSum1.setLength(0);
		for(int i = 0 ; i < 8; i++) {
			
			checkSum1.append(((aux.charAt(i)) ^ (dadoByte8Binary.charAt(i))));
			
		}
		aux = checkSum1.toString();
		checkSum1.setLength(0);
		for(int i = 0 ; i < 8; i++) {
			
			checkSum1.append(((aux.charAt(i)) ^ (dadoByte9Binary.charAt(i))));
			
		}
		aux = checkSum1.toString();
		System.out.println("XOR-RESULT-BINARY: " + aux);
		System.out.println("XOR-RESULT-HEX: " + Integer.toString(Integer.parseInt(aux, 2), 16).toUpperCase());
		//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
		
		//%%%
		//NOT
		//%%%
		aux = checkSum1.toString().replaceAll("0", "X").replaceAll("1", "0").replaceAll("X", "1");
		System.out.println("NOT-RESULT: " + aux);
		//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
		
		int xorNotToDecimal = Integer.parseInt(aux, 2); 
		String xorNotToHexadecimal = Integer.toString(xorNotToDecimal, 16);
		
		if(xorNotToHexadecimal.length() < 2) {
			
			xorNotToHexadecimal = "0" + xorNotToHexadecimal;
			
		}
		System.out.println("XOR-NOT-HEX-RESULT: " + xorNotToHexadecimal);
		checkSum1.setLength(0);
		checkSum1.append(xorNotToHexadecimal.toUpperCase());
		System.out.println("CHECK-SUM: " + checkSum1.toString());
		//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
		
		
		
		
		System.out.println("%%%%%%%%");
		
		String comandoCompleto = "AA" + comandoBAHex + dadoByte1Hex + dadoByte2Hex + dadoByte3Hex + dadoByte4Hex +
				dadoByte5Hex + dadoByte6Hex + dadoByte7Hex + dadoByte8Hex + dadoByte9Hex + checkSum1 + "5";  
		
		comandoCompleto += "\r\n5";
		
		System.out.println("COMANDO COMPLETO: " + comandoCompleto);
		flagGerenciamentoSecagemFrameSetStatusMotores = true;
		comandoBA = comandoCompleto;
		
	}
	
	
	//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	
	
	//SETTERS	
	public void setBtnSalvarEnabled(boolean enabled) {
		
		btnSalvar.setEnabled(enabled);
		
	}
	
	public void setRdbtnModoManualSelected(boolean selected) {
		
		rdbtnModoManual.setSelected(selected);
		///
		//MANUAL
		lblBorderCilindroManual.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Manual", TitledBorder.CENTER, TitledBorder.BELOW_TOP, null, new Color(0, 0, 0)));
		lblBorderVentiladorManual.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Manual", TitledBorder.CENTER, TitledBorder.BELOW_TOP, null, new Color(0, 0, 0)));
		rdbtnModoManualCilindroDesligado.setSelected(true);
		rdbtnModoManualVentiladorDesligado.setSelected(true);
		rdbtnModoManualAuxiliarDesligado.setSelected(true);
		///
		rdbtnModoManualCilindroLigado.setEnabled(true);
		rdbtnModoManualCilindroDesligado.setEnabled(true);
		rdbtnModoManualVentiladorLigado.setEnabled(true);
		rdbtnModoManualVentiladorDesligado.setEnabled(true);
		rdbtnModoManualAuxiliarLigado.setEnabled(true);
		rdbtnModoManualAuxiliarDesligado.setEnabled(true);
		
		//INTERMITENTE
		lblBorderCilindroIntermitente.setEnabled(false);
		spnModoIntermitenteCilindroLigado.setEnabled(false);
		spnModoIntermitenteCilindroLigado.setValue(1);
		spnModoIntermitenteCilindroDesligado.setEnabled(false);
		spnModoIntermitenteCilindroDesligado.setValue(1);
		lblBorderVentiladorIntermitente.setEnabled(false);
		spnModoIntermitenteVentiladorLigado.setEnabled(false);
		spnModoIntermitenteVentiladorLigado.setValue(1);
		spnModoIntermitenteVentiladorDesligado.setEnabled(false);
		spnModoIntermitenteVentiladorDesligado.setValue(1);
		
		//CONTÍNUO
		lblBorderCilindroContinuo.setEnabled(false);
		txfModoContinuoCilindroLigado.setEnabled(false);
		txfModoContinuoCilindroLigado.setText("0000");
		txfModoContinuoCilindroDesligado.setEnabled(false);
		txfModoContinuoCilindroDesligado.setText("0000");
		lblBorderVentiladorContinuo.setEnabled(false);
		txfModoContinuoVentiladorLigado.setEnabled(false);
		txfModoContinuoVentiladorLigado.setText("0000");
		txfModoContinuoVentiladorDesligado.setEnabled(false);
		txfModoContinuoVentiladorDesligado.setText("0000");
		
	}
	
	
	public void setRdbtnModoIntermitenteSelected(boolean selected) {
		
		rdbtnModoIntermitente.setSelected(selected);
		///
		//MANUAL
		lblBorderCilindroManual.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Iniciar", TitledBorder.CENTER, TitledBorder.BELOW_TOP, null, new Color(0, 0, 0)));
		lblBorderVentiladorManual.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Iniciar", TitledBorder.CENTER, TitledBorder.BELOW_TOP, null, new Color(0, 0, 0)));
		rdbtnModoManualCilindroDesligado.setSelected(true);
		rdbtnModoManualVentiladorDesligado.setSelected(true);
		rdbtnModoManualAuxiliarDesligado.setSelected(true);
		///
		rdbtnModoManualCilindroLigado.setEnabled(true);
		rdbtnModoManualCilindroDesligado.setEnabled(true);
		rdbtnModoManualVentiladorLigado.setEnabled(true);
		rdbtnModoManualVentiladorDesligado.setEnabled(true);
		rdbtnModoManualAuxiliarLigado.setEnabled(true);
		rdbtnModoManualAuxiliarDesligado.setEnabled(true);
		
		//INTERMITENTE
		lblBorderCilindroIntermitente.setEnabled(true);
		spnModoIntermitenteCilindroLigado.setEnabled(true);
		spnModoIntermitenteCilindroLigado.setValue(1);
		spnModoIntermitenteCilindroDesligado.setEnabled(true);
		spnModoIntermitenteCilindroDesligado.setValue(1);
		lblBorderVentiladorIntermitente.setEnabled(true);
		spnModoIntermitenteVentiladorLigado.setEnabled(true);
		spnModoIntermitenteVentiladorLigado.setValue(1);
		spnModoIntermitenteVentiladorDesligado.setEnabled(true);
		spnModoIntermitenteVentiladorDesligado.setValue(1);
		
		//CONTÍNUO
		lblBorderCilindroContinuo.setEnabled(false);
		txfModoContinuoCilindroLigado.setEnabled(false);
		txfModoContinuoCilindroLigado.setText("0000");
		txfModoContinuoCilindroDesligado.setEnabled(false);
		txfModoContinuoCilindroDesligado.setText("0000");
		lblBorderVentiladorContinuo.setEnabled(false);
		txfModoContinuoVentiladorLigado.setEnabled(false);
		txfModoContinuoVentiladorLigado.setText("0000");
		txfModoContinuoVentiladorDesligado.setEnabled(false);
		txfModoContinuoVentiladorDesligado.setText("0000");
				
	}
	
	public void setRdbtnModoContinuoSelected(boolean selected) {
		
		rdbtnModoContinuo.setSelected(selected);
		///
		//MANUAL
		lblBorderCilindroManual.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Estado Atual", TitledBorder.CENTER, TitledBorder.BELOW_TOP, null, new Color(0, 0, 0)));
		lblBorderVentiladorManual.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Estado Atual", TitledBorder.CENTER, TitledBorder.BELOW_TOP, null, new Color(0, 0, 0)));
		rdbtnModoManualCilindroDesligado.setSelected(true);
		rdbtnModoManualVentiladorDesligado.setSelected(true);
		rdbtnModoManualAuxiliarDesligado.setSelected(true);
		///
		rdbtnModoManualCilindroLigado.setEnabled(false);
		rdbtnModoManualCilindroDesligado.setEnabled(false);
		rdbtnModoManualVentiladorLigado.setEnabled(false);
		rdbtnModoManualVentiladorDesligado.setEnabled(false);
		
		//INTERMITENTE
		lblBorderCilindroIntermitente.setEnabled(false);
		spnModoIntermitenteCilindroLigado.setEnabled(false);
		spnModoIntermitenteCilindroLigado.setValue(1);
		spnModoIntermitenteCilindroDesligado.setEnabled(false);
		spnModoIntermitenteCilindroDesligado.setValue(1);
		lblBorderVentiladorIntermitente.setEnabled(false);
		spnModoIntermitenteVentiladorLigado.setEnabled(false);
		spnModoIntermitenteVentiladorLigado.setValue(1);
		spnModoIntermitenteVentiladorDesligado.setEnabled(false);
		spnModoIntermitenteVentiladorDesligado.setValue(1);
		
		//CONTÍNUO
		lblBorderCilindroContinuo.setEnabled(true);
		txfModoContinuoCilindroLigado.setEnabled(true);
		txfModoContinuoCilindroLigado.setText("0000");
		txfModoContinuoCilindroDesligado.setEnabled(true);
		txfModoContinuoCilindroDesligado.setText("0000");
		lblBorderVentiladorContinuo.setEnabled(true);
		txfModoContinuoVentiladorLigado.setEnabled(true);
		txfModoContinuoVentiladorLigado.setText("0000");
		txfModoContinuoVentiladorDesligado.setEnabled(true);
		txfModoContinuoVentiladorDesligado.setText("0000");
		
	}
	
	public void setRdbtnModoManualCilindroLigadoEnabled(boolean enabled) {
		
		if(enabled) rdbtnModoManualCilindroLigado.setEnabled(true);
		else rdbtnModoManualCilindroLigado.setEnabled(false);
		
	}
	
	public void setRdbtnModoManualCilindroDesligadoEnabled(boolean enabled) {
		
		if(enabled) rdbtnModoManualCilindroDesligado.setEnabled(true);
		else rdbtnModoManualCilindroDesligado.setEnabled(false);
		
	}
	
	public void setRdbtnModoManualVentiladorLigadoEnabled(boolean enabled) {
		
		if(enabled) rdbtnModoManualVentiladorLigado.setEnabled(true);
		else rdbtnModoManualVentiladorLigado.setEnabled(false);
		
	}
	
	public void setRdbtnModoManualVentiladorDesligadoEnabled(boolean enabled) {
		
		if(enabled) rdbtnModoManualVentiladorDesligado.setEnabled(true);
		else rdbtnModoManualVentiladorDesligado.setEnabled(false);
		
	}
	
	public void setRdbtnModoManualAuxiliarLigadoEnabled(boolean enabled) {
		
		if(enabled) rdbtnModoManualAuxiliarLigado.setEnabled(true);
		else rdbtnModoManualAuxiliarLigado.setEnabled(false);
		
	}
	
	public void setRdbtnModoManualAuxiliarDesligadoEnabled(boolean enabled) {
		
		if(enabled) rdbtnModoManualAuxiliarDesligado.setEnabled(true);
		else rdbtnModoManualAuxiliarDesligado.setEnabled(false);
		
	}
	
	public void setRdbtnModoManualCilindroLigadoSelected(boolean selected) {
		
		if(selected) rdbtnModoManualCilindroLigado.setSelected(true);
		else rdbtnModoManualCilindroDesligado.setSelected(true);
		
	}
	
	public void setRdbtnModoManualVentiladorLigadoSelected(boolean selected) {
		
		if(selected) rdbtnModoManualVentiladorLigado.setSelected(true);
		else rdbtnModoManualVentiladorDesligado.setSelected(true);
		
	}
	
	public void setRdbtnModoManualAuxiliarLigadoSelected(boolean selected) {
		
		if(selected) rdbtnModoManualAuxiliarLigado.setSelected(true);
		else rdbtnModoManualAuxiliarDesligado.setSelected(true);
		
	}
	
	public void setSpnModoIntermitenteCilindroLigadoValue(int value) {
		
		if(value <= 0) spnModoIntermitenteCilindroLigado.setValue(1); //MODO-INTERMITENTE, MENOR VALOR POSSÍVEL É 1 [minuto]
		else spnModoIntermitenteCilindroLigado.setValue(value);
		
	}
	
	public void setSpnModoIntermitenteCilindroDesligadoValue(int value) {
		
		if(value <= 0) spnModoIntermitenteCilindroDesligado.setValue(1); //MODO-INTERMITENTE, MENOR VALOR POSSÍVEL É 1 [minuto]
		else spnModoIntermitenteCilindroDesligado.setValue(value);
		
	}
	
	public void setSpnModoIntermitenteVentiladorLigadoValue(int value) {
		
		if(value <= 0) spnModoIntermitenteVentiladorLigado.setValue(1); //MODO-INTERMITENTE, MENOR VALOR POSSÍVEL É 1 [minuto]
		else spnModoIntermitenteVentiladorLigado.setValue(value);
		
	}
	
	public void setSpnModoIntermitenteVentiladorDesligadoValue(int value) {
		
		if(value <= 0) spnModoIntermitenteVentiladorDesligado.setValue(1); //MODO-INTERMITENTE, MENOR VALOR POSSÍVEL É 1 [minuto]
		else spnModoIntermitenteVentiladorDesligado.setValue(value);
		
	}
	
	public void setTxfModoContinuoCilindroLigadoText(String text) {
		
		txfModoContinuoCilindroLigado.setText(text);
		
	}
	
	public void setTxfModoContinuoCilindroDesligadoText(String text) {
		
		txfModoContinuoCilindroDesligado.setText(text);
		
	}
	
	public void setTxfModoContinuoVentiladorLigadoText(String text) {
		
		txfModoContinuoVentiladorLigado.setText(text);
		
	}
	
	public void setTxfModoContinuoVentiladorDesligadoText(String text) {
		
		txfModoContinuoVentiladorDesligado.setText(text);
		
	}
	
	
	//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
		
	
	//FAZ A LINKAGEM ENTRE O RUNNER E A VIEW [MOTORES] POR INTERMÉDIO DO LISTENER [MOTORES]
	public void addGerenciamentoMotoresListener(GerenciamentoMotoresListener gerenciamentoMotoresListener) {
		
		this.gerenciamentoMotoresListener = gerenciamentoMotoresListener;
		
	}
	
	
	//FAZ A CRIAÇÃO DO FORMATO XXhYYm PARA CUSTOMIZAÇÃO DO FORMATTED-TEXT-FIELD
	//[PARA USO NO MODO TEMPORIZADO CONTÍNUO]
	public MaskFormatter createFormatter(String formatoHoraMinuto) {
		
		MaskFormatter maskFormatter = null;
		try {
			
			maskFormatter = new MaskFormatter(formatoHoraMinuto);
			
		} catch (Exception e) {

			e.printStackTrace();
			
		}
		return maskFormatter;
		
	}
	
	
}
