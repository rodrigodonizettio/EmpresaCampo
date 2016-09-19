package com.campotech.sgsoft.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Dialog.ModalExclusionType;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.Painter;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.UIDefaults;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.campotech.sgsoft.controller.listener.GerenciamentoSecagemListener;
import com.campotech.sgsoft.controller.listener.GerenciamentoUsuariosListener;

import java.awt.Color;
import java.io.File;
import java.net.URL;
import java.rmi.server.UID;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.PaintEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.Component;

import javax.swing.SwingConstants;

import org.eclipse.wb.swing.FocusTraversalOnArray;

public class GerenciamentoSecagemFrame extends JFrame {
	
//	while(flagGerenciamentoSecagemFrameColetarAmostras) {
//		
//		zigBeeGerenciamentoSecagemFrameColetarAmostras();
//		
//	}
	
	//ATRIBUTOS PERTENCENTES AO FRAME
	private JPanel contentPane;
	private JLabel lblSecadorELote;
	private JLabel lblBorderOuter;
	private JLabel lblBorderLote;
	private JLabel lblLoteSecador;
	private JComboBox cmbbxLoteSecador;
	private JButton btnOkCmbbxLoteSecador;
	private JLabel lblLoteOrigem;
	private JTextField txfLoteOrigem;
	private JLabel lblLoteDestino;
	private JTextField txfLoteDestino;
	private JCheckBox chkbxNovoLote;
	private JTextField txfNovoLote;
	private JLabel lblBorderReceita;
	private JLabel lblReceita;
	private JComboBox cmbbxReceita;
	private JButton btnEditarReceita;
	private JLabel lblData;
	private JTextField txfData;
	private JLabel lblHora;
	private JTextField txfHora;
	private JCheckBox chkbxTempoTerreiro;
	private JSpinner spnTempoTerreiro;
	private JLabel lblHoras;
	private JLabel lblBorderSaidas;
//	private JLabel lblLEDCilindro;
//	private JLabel lblBorderCilindro;
//	private JRadioButton rdbtnCilindroLiga;
//	private JRadioButton rdbtnCilindroDesliga;
//	private JLabel lblLEDVentilador;
//	private JLabel lblBorderVentilador;
//	private JRadioButton rdbtnVentiladorLiga;
//	private JRadioButton rdbtnVentiladorDesliga;
//	private JLabel lblLEDAuxiliar;
//	private JLabel lblBorderAuxiliar;
//	private JRadioButton rdbtnAuxiliarLiga;
//	private JRadioButton rdbtnAuxiliarDesliga;
	private JLabel lblTempoDescanso;
	private JLabel lblTempoDescansoValor;
	private JLabel lblTempoSecagem;
	private JLabel lblTempoSecagemValor;
	///
	private JButton btnColetarAmostras;
	///
	private JLabel lblTempoTotal;
	private JLabel lblTempoTotalValor;
	private JLabel lblBebida;
	private JTextField txfBebida;
	private JLabel lblRenda;
	private JTextField txfRenda;
	private JLabel lblTulhaDestino;
	private JTextField txfTulhaDestino;
	private JLabel lblVariedade;
	private JTextField txfVariedade;
	private JProgressBar prgrssbrColetaAmostra;
	private JButton btnIniciar;
	private JButton btnDescansar;
	private JButton btnReiniciar;
	private JButton btnFinalizar;
	private ButtonGroup rdgrpCilindro;
	private ButtonGroup rdgrpVentilador;
	private ButtonGroup rdgrpAuxiliar;
//	private JButton btnAtualizar;
	private JButton btnAcessarMotores;
//	private JLabel lblSecador;
	
	///
//	URL urlIconSecador = GerenciamentoSecagemFrame.class.getResource("images/secadorCD.png");
//	private Icon icon = new ImageIcon(urlIconSecador);
//	private ImageIcon imgIcon = new ImageIcon(urlIconSecador);
	//BufferedImage buffImg = ImageIO.read(new File(GerenciamentoSecagemFrame.class.getResource("images/secadorCD.png")));
	
	//ATRIBUTOS PERTENCENTES AO LISTENER
	private GerenciamentoSecagemListener gerenciamentoSecagemListener;
	
	
	//ATRIBUTOS DE IMAGENS
	URL urlIconCampotech = GerenciamentoRelatoriosFrame.class.getResource("images/icon-campotech.png");	
	
	
	//ATRIBUTOS PERTENCENTES ÀS CONVERSÕES DE FORMATO
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	private SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
	
	
	//ATRIBUTOS GLOBAIS
	private String secadorNumero = null;
	private String loteNome = null;
	
	
	//CORES CUSTOMIZADAS
	Color VERDE_ESCURO = new Color(0, 193, 0); //0, 128, 0 => VERDE ESCURÃO
	
	
	public GerenciamentoSecagemFrame() {
		
		setTitle("Tela - Gerenciamento de Secagem");
		//<CRIANDO ICONE PARA APLICAÇÃO
		setIconImage(Toolkit.getDefaultToolkit().getImage(urlIconCampotech).getScaledInstance(32, 32, java.awt.Image.SCALE_SMOOTH));
		//CRIANDO ICONE PARA APLICAÇÃO>
		setBounds(100, 100, 800, 600);
		//<POSICIONANDO FRAME NO CENTRO DA TELA DO COMPUTADOR
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(screenSize.width/2 - this.getSize().width/2, screenSize.height/2 - this.getSize().height/2);
		//POSICIONANDO FRAME NO CENTRO DA TELA DO COMPUTADOR>
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		//setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent arg0) {
				
				gerenciamentoSecagemListener.windowClosingActionPerformed();
				
			}
			
		});
		
		
		lblSecadorELote = new JLabel();
		lblSecadorELote.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSecadorELote.setBounds(290, 5, 420, 20);
		//lblSecadorELote.setText("Secador-XX (Lote-123456789012345678901234567890)");
		contentPane.add(lblSecadorELote);
		
		
			//[BORDER] CONTENT PANE
			lblBorderOuter = new JLabel();
			lblBorderOuter.setBounds(5, 30, 785, 540);
			lblBorderOuter.setBorder(new TitledBorder(null, null, TitledBorder.CENTER, TitledBorder.TOP, null, null));
			contentPane.add(lblBorderOuter);
			
				
				//[BORDER] LOTE
				lblBorderLote = new JLabel();
				lblBorderLote.setBounds(10, 10, 765, 82);
				lblBorderLote.setBorder(new TitledBorder(null, null, TitledBorder.CENTER, TitledBorder.TOP, null, null));
				lblBorderOuter.add(lblBorderLote);
		
					//[LINHA-1] LOTES: SECADOR/ORIGEM/DESTINO
					lblLoteSecador = new JLabel();
					lblLoteSecador.setBounds(15, 15, 115, 20);
					lblLoteSecador.setText("Lotes Descansando:");
					lblBorderLote.add(lblLoteSecador);
				
					cmbbxLoteSecador = new JComboBox();
					cmbbxLoteSecador.setBounds(130, 10, 245, 30);
					cmbbxLoteSecador.addItem("PASSE O MOUSE AQUI PARA LISTAR");
					
					cmbbxLoteSecador.addMouseListener(new MouseAdapter() {
						
						@Override
						public void mouseEntered(MouseEvent arg0) {
							
							//SE NOVO LOTE ESTÁ DESMARCADO, ENTÃO DEVE EFETUAR A ATUALIZAÇÃO DA LISTA DE LOTES CADASTRADOS NA DB
							if(!chkbxNovoLote.isSelected()) {
								
								gerenciamentoSecagemListener.cmbbxLoteSecadorMouseEntered();
								
							}
							
							
						}
						
					});
					
					lblBorderLote.add(cmbbxLoteSecador);
					
					btnOkCmbbxLoteSecador = new JButton();
					btnOkCmbbxLoteSecador.setBounds(380, 10, 50, 30);
					btnOkCmbbxLoteSecador.setText("OK");
					
					btnOkCmbbxLoteSecador.addActionListener(new ActionListener() {
						
						public void actionPerformed(ActionEvent arg0) {
							
							gerenciamentoSecagemListener.btnOkCmbbxLoteSecadorClicked();
							
						}
						
					});
					
					lblBorderLote.add(btnOkCmbbxLoteSecador);
					//CONTINUAR: INSERIR BOTÃO OK PARA DEFINIR LOTE SELECIONADO NO COMBOBOX
					
					lblLoteOrigem = new JLabel();
					lblLoteOrigem.setBounds(435, 15, 95, 20);
					lblLoteOrigem.setText("Lote de Origem:");
					lblBorderLote.add(lblLoteOrigem);
				
					txfLoteOrigem = new JTextField();
					txfLoteOrigem.setBounds(525, 10, 225, 30);
					txfLoteOrigem.setEnabled(false);
					txfLoteOrigem.setDocument(new JTextFieldCharacterLimit(30));
					lblBorderLote.add(txfLoteOrigem);
					
					lblLoteDestino = new JLabel();
					lblLoteDestino.setBounds(433, 45, 95, 20);
					lblLoteDestino.setText("Lote de Destino:");
					lblBorderLote.add(lblLoteDestino);
				
					txfLoteDestino = new JTextField();
					txfLoteDestino.setBounds(525, 40, 225, 30);
					txfLoteDestino.setEnabled(false);
					txfLoteDestino.setDocument(new JTextFieldCharacterLimit(30));
					lblBorderLote.add(txfLoteDestino);

					//[LINHA-2] NOVO LOTE
					chkbxNovoLote = new JCheckBox();
					chkbxNovoLote.setBounds(13, 45, 150, 20);
					chkbxNovoLote.setText("Novo Lote no Secador:");
					
					chkbxNovoLote.addActionListener(new ActionListener() {
						
						public void actionPerformed(ActionEvent arg0) {
						
							gerenciamentoSecagemListener.chkbxNovoLoteClicked();
							
						}
						
					});
					
					lblBorderLote.add(chkbxNovoLote);
					
					txfNovoLote = new JTextField();
					txfNovoLote.setBounds(161, 40, 225, 30);
					txfNovoLote.setEnabled(false);
					txfNovoLote.setDocument(new JTextFieldCharacterLimit(30));
					lblBorderLote.add(txfNovoLote);
					
					
				//[BORDER] RECEITA
				lblBorderReceita = new JLabel();
				lblBorderReceita.setBounds(10, 100, 765, 82);
				lblBorderReceita.setBorder(new TitledBorder(null, null, TitledBorder.CENTER, TitledBorder.TOP, null, null));
				lblBorderOuter.add(lblBorderReceita);	
				
					//[LINHA-1] RECEITA
					lblReceita = new JLabel();
					lblReceita.setBounds(15, 15, 50, 20);
					lblReceita.setText("Receita:");
					lblBorderReceita.add(lblReceita);
					
					cmbbxReceita = new JComboBox();
					cmbbxReceita.setBounds(62, 10, 245, 30);
					cmbbxReceita.addItem("PASSE O MOUSE AQUI PARA LISTAR");
					
					cmbbxReceita.addMouseListener(new MouseAdapter() {
						
						@Override
						public void mouseEntered(MouseEvent arg0) {
							
							gerenciamentoSecagemListener.cmbbxReceitaMouseEntered();
							
						}
						
					});
					
					lblBorderReceita.add(cmbbxReceita);
					
					btnEditarReceita = new JButton();
					btnEditarReceita.setVisible(false);
					btnEditarReceita.setBounds(315, 10, 105, 30);
					btnEditarReceita.setText("Editar Receita");
					
					btnEditarReceita.addActionListener(new ActionListener() {
						
						public void actionPerformed(ActionEvent arg0) {
						
							gerenciamentoSecagemListener.btnEditarReceitaClicked();
							
						}
						
					});
					
					lblBorderReceita.add(btnEditarReceita);
					
					lblData = new JLabel();
					lblData.setBounds(586, 15, 80, 20);
					lblData.setText("Data de Início:");
					lblBorderReceita.add(lblData);
					
					txfData = new JTextField();
					txfData.setToolTipText("Data preenchida pelo Sistema!");
					txfData.setBounds(665, 10, 85, 30);
					txfData.setEnabled(false);
					txfData.setDocument(new JTextFieldCharacterLimit(10));
					lblBorderReceita.add(txfData);
					
					lblHora = new JLabel();
					lblHora.setBounds(586, 45, 80, 20);
					lblHora.setText("Hora de Início:");
					lblBorderReceita.add(lblHora);
					
					txfHora = new JTextField();
					txfHora.setToolTipText("Hora preenchida pelo Sistema!");
					txfHora.setBounds(665, 40, 85, 30);
					txfHora.setEnabled(false);
					txfHora.setDocument(new JTextFieldCharacterLimit(8));
					lblBorderReceita.add(txfHora);
					
					//[LINHA-2] NOVO LOTE
					chkbxTempoTerreiro = new JCheckBox();
					chkbxTempoTerreiro.setBounds(13, 45, 170, 20);
					chkbxTempoTerreiro.setEnabled(false);
					chkbxTempoTerreiro.setText("Tempo no Terreiro [horas]:");
					
					chkbxTempoTerreiro.addActionListener(new ActionListener() {
						
						public void actionPerformed(ActionEvent arg0) {
						
							gerenciamentoSecagemListener.chkbxTempoTerreiroClicked();
							
						}
						
					});
					
					lblBorderReceita.add(chkbxTempoTerreiro);
					
					spnTempoTerreiro = new JSpinner();
					spnTempoTerreiro.setBounds(185, 43, 55, 25);
					spnTempoTerreiro.setEnabled(false);
					spnTempoTerreiro.setModel(new SpinnerNumberModel(0, 0, 999, 1));
					lblBorderReceita.add(spnTempoTerreiro);
					
					
				//[BORDER] SAÍDAS
				lblBorderSaidas = new JLabel();
				lblBorderSaidas.setBounds(10, 190, 765, 170);
				lblBorderSaidas.setBorder(new TitledBorder(null, "Motores", TitledBorder.CENTER, TitledBorder.TOP, null, null));
				lblBorderOuter.add(lblBorderSaidas);
						
				//[LABEL] TEMPOS - DESCANSO/SECAGEM/OPERAÇÃO
				lblTempoDescanso = new JLabel();
				lblTempoDescanso.setBounds(15, 450, 180, 30);
				lblTempoDescanso.setFont(new Font("Tahoma", Font.BOLD, 15));
				lblTempoDescanso.setText("Tempo em Descanso:");
				lblBorderOuter.add(lblTempoDescanso);
				
				lblTempoDescansoValor = new JLabel();
				lblTempoDescansoValor.setBounds(205, 450, 250, 30);
				//lblTempoDescansoValor.setForeground(Color.BLUE);
				lblTempoDescansoValor.setFont(new Font("Tahoma", Font.BOLD, 15));
				//lblTempoDescansoValor.setText("DD [d] HH [h] MM [m] SS [s] ");
				lblBorderOuter.add(lblTempoDescansoValor);
				
				lblTempoSecagem = new JLabel();
				lblTempoSecagem.setBounds(15, 410, 180, 30);
				lblTempoSecagem.setFont(new Font("Tahoma", Font.BOLD, 15));
				lblTempoSecagem.setText("Tempo em Secagem:");
				lblBorderOuter.add(lblTempoSecagem);
				
				lblTempoSecagemValor = new JLabel();
				lblTempoSecagemValor.setBounds(205, 410, 250, 30);
				//lblTempoSecagemValor.setForeground(Color.ORANGE);
				lblTempoSecagemValor.setFont(new Font("Tahoma", Font.BOLD, 15));
				//lblTempoSecagemValor.setText("DD [d] HH [h] MM [m] SS [s] ");
				lblBorderOuter.add(lblTempoSecagemValor);
				
				btnColetarAmostras = new JButton();
				btnColetarAmostras.setBounds(10, 485, 90, 45);
				btnColetarAmostras.setText("<html><p>&nbsp;&nbsp;Coletar</p><p>Amostras</p></html>");
				
				btnColetarAmostras.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent arg0) {
						
						gerenciamentoSecagemListener.btnColetarAmostrasClicked();
						
					}
					
				});
				
				lblBorderOuter.add(btnColetarAmostras);
				
				
				lblTempoTotal = new JLabel();
				lblTempoTotal.setBounds(15, 370, 200, 30);
				lblTempoTotal.setFont(new Font("Tahoma", Font.BOLD, 14));
				lblTempoTotal.setText("Tempo Total de Operação:");
				lblBorderOuter.add(lblTempoTotal);
				
				lblTempoTotalValor = new JLabel();
				lblTempoTotalValor.setBounds(205, 370, 250, 30);
				//lblTempoTotalValor.setForeground(Color.GREEN);
				lblTempoTotalValor.setFont(new Font("Tahoma", Font.BOLD, 15));
				//lblTempoTotalValor.setText("DD [d] HH [h] MM [m] SS [s] ");
				lblBorderOuter.add(lblTempoTotalValor);
				
				
				//[LABEL] DADOS DE FINALIZAÇÃO DE SECAGEM - BEBIDA/RENDA/TULHA DE DESTINO
				lblBebida = new JLabel();
				lblBebida.setBounds(480, 375, 45, 20);
				lblBebida.setVisible(false);
				lblBebida.setHorizontalAlignment(SwingConstants.RIGHT);
				lblBebida.setText("Bebida:");
				lblBorderOuter.add(lblBebida);
				
				txfBebida = new JTextField();
				txfBebida.setBounds(525, 370, 225, 30);
				txfBebida.setVisible(false);
				txfBebida.setDocument(new JTextFieldCharacterLimit(30));
				lblBorderOuter.add(txfBebida);
				
				lblRenda = new JLabel();
				lblRenda.setBounds(462, 405, 60, 20);
				lblRenda.setVisible(false);
				lblRenda.setHorizontalAlignment(SwingConstants.RIGHT);
				lblRenda.setText("Renda [%]:");
				lblBorderOuter.add(lblRenda);
				
				txfRenda = new JTextField();
				txfRenda.setBounds(525, 400, 225, 30);
				txfRenda.setVisible(false);
				txfRenda.setDocument(new JTextFieldCharacterLimit(30));
				lblBorderOuter.add(txfRenda);
				
				lblTulhaDestino = new JLabel();
				lblTulhaDestino.setBounds(425, 435, 100, 20);
				lblTulhaDestino.setVisible(false);
				lblTulhaDestino.setHorizontalAlignment(SwingConstants.RIGHT);
				lblTulhaDestino.setText("Tulha de Destino:");
				lblBorderOuter.add(lblTulhaDestino);
				
				txfTulhaDestino = new JTextField();
				txfTulhaDestino.setBounds(525, 430, 225, 30);
				txfTulhaDestino.setVisible(false);
				txfTulhaDestino.setDocument(new JTextFieldCharacterLimit(30));
				lblBorderOuter.add(txfTulhaDestino);
				
				lblVariedade = new JLabel();
				lblVariedade.setBounds(425, 465, 100, 20);
				lblVariedade.setVisible(false);
				lblVariedade.setHorizontalAlignment(SwingConstants.RIGHT);
				lblVariedade.setText("Variedade:");
				lblBorderOuter.add(lblVariedade);
				
				txfVariedade = new JTextField();
				txfVariedade.setBounds(525, 460, 225, 30);
				txfVariedade.setVisible(false);
				txfVariedade.setDocument(new JTextFieldCharacterLimit(30));
				lblBorderOuter.add(txfVariedade);
				
				
				//CONTINUAR: ADEQUAR PROGRESSBAR À COLETA DE AMOSTRAS
				
				
				
				//UIDefaults uiDefaults = new UIDefaults();
				//uiDefaults.put("ProgressBar.Background", Color.BLUE);
				//uiDefaults.put("ProgressBar.Foreground", Color.RED);
				
				
				prgrssbrColetaAmostra = new JProgressBar();
				prgrssbrColetaAmostra.setBounds(110, 510, 300, 20);
				prgrssbrColetaAmostra.setVisible(false);
				lblBorderOuter.add(prgrssbrColetaAmostra);
				
				
				prgrssbrColetaAmostra.setStringPainted(true);
				prgrssbrColetaAmostra.setMinimum(0);
				prgrssbrColetaAmostra.setMaximum(100);
				
				
				btnIniciar = new JButton();
				btnIniciar.setBounds(480, 495, 90, 30);
				btnIniciar.setEnabled(false);
				btnIniciar.setText("Iniciar");
				
				btnIniciar.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent arg0) {
					
						gerenciamentoSecagemListener.btnIniciarClicked();
						
					}
					
				});
				
				lblBorderOuter.add(btnIniciar);
				
				btnDescansar = new JButton();
				btnDescansar.setBounds(580, 495, 90, 30);
				btnDescansar.setEnabled(false);
				btnDescansar.setText("Descansar");
				
				btnDescansar.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						
						gerenciamentoSecagemListener.btnDescansarClicked();
						
					}
					
				});
				
				lblBorderOuter.add(btnDescansar);
				
				btnReiniciar = new JButton();
				btnReiniciar.setBounds(580, 495, 90, 30);
				btnReiniciar.setVisible(false);
				btnReiniciar.setEnabled(false);
				btnReiniciar.setText("Reiniciar");
				
				btnReiniciar.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						
						gerenciamentoSecagemListener.btnReiniciarClicked();
						
					}
					
				});
				
				lblBorderOuter.add(btnReiniciar);
				
				btnFinalizar = new JButton();
				btnFinalizar.setBounds(680, 495, 90, 30);
				btnFinalizar.setEnabled(false);
				btnFinalizar.setText("Finalizar");
				
				btnFinalizar.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent arg0) {
						
						gerenciamentoSecagemListener.btnFinalizarClicked();
						
					}
					
				});
				
				lblBorderOuter.add(btnFinalizar);
						
								//[BORDER] CILINDRO
//						lblLEDCilindro = new JLabel();
//						lblLEDCilindro.setBounds(53, 25, 25, 30);
//						lblLEDCilindro.setFont(new Font("Tahoma", Font.BOLD, 35));
//						lblLEDCilindro.setForeground(Color.RED);
//						lblLEDCilindro.setText("•");
//						lblLEDCilindro.setEnabled(false);
//						lblBorderSaidas.add(lblLEDCilindro);
								
//						lblBorderCilindro = new JLabel();
//						lblBorderCilindro.setBounds(15, 55, 100, 67);
//						lblBorderCilindro.setBorder(new TitledBorder(null, "Cilindro", TitledBorder.CENTER, TitledBorder.TOP, null, null));
//						lblBorderCilindro.setEnabled(false);
//						lblBorderSaidas.add(lblBorderCilindro);
								
//							rdbtnCilindroLiga = new JRadioButton();
//							rdbtnCilindroLiga.setForeground(VERDE_ESCURO);
//							rdbtnCilindroLiga.setBounds(15, 15, 50, 20);
//							rdbtnCilindroLiga.setText("Liga");
//							rdbtnCilindroLiga.setEnabled(false);
//							lblBorderCilindro.add(rdbtnCilindroLiga);
									
//							rdbtnCilindroDesliga = new JRadioButton();
//							rdbtnCilindroDesliga.setForeground(Color.RED);
//							rdbtnCilindroDesliga.setBounds(15, 35, 65, 20);
//							rdbtnCilindroDesliga.setText("Desliga");
//							rdbtnCilindroDesliga.setEnabled(false);
//							lblBorderCilindro.add(rdbtnCilindroDesliga);
									
//							rdgrpCilindro = new ButtonGroup();
//							rdgrpCilindro.add(rdbtnCilindroLiga);
//							rdgrpCilindro.add(rdbtnCilindroDesliga);
									
									
								//[BORDER] VENTILADOR
//						lblLEDVentilador = new JLabel();
//						lblLEDVentilador.setBounds(372, 25, 25, 30);
//						lblLEDVentilador.setFont(new Font("Tahoma", Font.BOLD, 35));
//						lblLEDVentilador.setForeground(Color.RED);
//						lblLEDVentilador.setText("•");
//						lblLEDVentilador.setEnabled(false);
//						lblBorderSaidas.add(lblLEDVentilador);
								
//						lblBorderVentilador = new JLabel();
//						lblBorderVentilador.setBounds(333, 55, 100, 67);
//						lblBorderVentilador.setBorder(new TitledBorder(null, "Ventilador", TitledBorder.CENTER, TitledBorder.TOP, null, null));
//						lblBorderVentilador.setEnabled(false);
//						lblBorderSaidas.add(lblBorderVentilador);
								
//							rdbtnVentiladorLiga = new JRadioButton();
//							rdbtnVentiladorLiga.setForeground(VERDE_ESCURO);
//							rdbtnVentiladorLiga.setBounds(15, 15, 50, 20);
//							rdbtnVentiladorLiga.setText("Liga");
//							rdbtnVentiladorLiga.setEnabled(false);
//							lblBorderVentilador.add(rdbtnVentiladorLiga);
									
//							rdbtnVentiladorDesliga = new JRadioButton();
//							rdbtnVentiladorDesliga.setForeground(Color.RED);
//							rdbtnVentiladorDesliga.setBounds(15, 35, 65, 20);
//							rdbtnVentiladorDesliga.setText("Desliga");
//							rdbtnVentiladorDesliga.setEnabled(false);
//							lblBorderVentilador.add(rdbtnVentiladorDesliga);
									
//							rdgrpVentilador = new ButtonGroup();
//							rdgrpVentilador.add(rdbtnVentiladorLiga);
//							rdgrpVentilador.add(rdbtnVentiladorDesliga);
									
								
								//[BORDER] AUXILIAR
//						lblLEDAuxiliar = new JLabel();
//						lblLEDAuxiliar.setBounds(689, 25, 25, 30);
//						lblLEDAuxiliar.setFont(new Font("Tahoma", Font.BOLD, 35));
//						lblLEDAuxiliar.setForeground(Color.RED);
//						lblLEDAuxiliar.setText("•");
//						lblLEDAuxiliar.setEnabled(false);
//						lblBorderSaidas.add(lblLEDAuxiliar);
								
//						lblBorderAuxiliar = new JLabel();
//						lblBorderAuxiliar.setBounds(650, 55, 100, 67);
//						lblBorderAuxiliar.setBorder(new TitledBorder(null, "Auxiliar", TitledBorder.CENTER, TitledBorder.TOP, null, null));
//						lblBorderAuxiliar.setEnabled(false);
//						lblBorderSaidas.add(lblBorderAuxiliar);
								
//							rdbtnAuxiliarLiga = new JRadioButton();
//							rdbtnAuxiliarLiga.setForeground(VERDE_ESCURO);
//							rdbtnAuxiliarLiga.setBounds(15, 15, 50, 20);
//							rdbtnAuxiliarLiga.setText("Liga");
//							rdbtnAuxiliarLiga.setEnabled(false);
//							lblBorderAuxiliar.add(rdbtnAuxiliarLiga);
									
//							rdbtnAuxiliarDesliga = new JRadioButton();
//							rdbtnAuxiliarDesliga.setForeground(Color.RED);
//							rdbtnAuxiliarDesliga.setBounds(15, 35, 65, 20);
//							rdbtnAuxiliarDesliga.setText("Desliga");
//							rdbtnAuxiliarDesliga.setEnabled(false);
//							lblBorderAuxiliar.add(rdbtnAuxiliarDesliga);
									
//							rdgrpAuxiliar = new ButtonGroup();
//							rdgrpAuxiliar.add(rdbtnAuxiliarLiga);
//							rdgrpAuxiliar.add(rdbtnAuxiliarDesliga);
						
//						btnAtualizar = new JButton();
//						btnAtualizar.setFont(new Font("Tahoma", Font.PLAIN, 20));
//						btnAtualizar.setBounds(324, 126, 120, 35);
//						btnAtualizar.setEnabled(false);
//						btnAtualizar.setText("Atualizar");
//						btnAtualizar.addActionListener(new ActionListener() {
//							
//							public void actionPerformed(ActionEvent arg0) {
//							
//								gerenciamentoSecagemListener.btnAtualizarClicked();
//								
//							}
//							
//						});
//						
//						lblBorderSaidas.add(btnAtualizar);	
						
						btnAcessarMotores = new JButton();
						btnAcessarMotores.setFont(new Font("Tahoma", Font.PLAIN, 20));
						btnAcessarMotores.setBounds(290, 65, 180, 35);
						btnAcessarMotores.setEnabled(false);
						btnAcessarMotores.setText("Acessar Motores");
						btnAcessarMotores.addActionListener(new ActionListener() {
							
							public void actionPerformed(ActionEvent arg0) {
								
								gerenciamentoSecagemListener.btnAcessarMotoresClicked();
								
							}
							
						});
						
						lblBorderSaidas.add(btnAcessarMotores);
				
//				lblNewLabel = new JLabel();
//				lblNewLabel.setBounds(452, 36, 332, 534);
//				lblNewLabel.setIcon(imgIcon);
////				lblNewLabel.setIcon(Toolkit.getDefaultToolkit().getImage(urlIconSecador).getScaledInstance(300, 400, java.awt.Image.SCALE_SMOOTH));
//				contentPane.add(lblNewLabel);
				
//				try {
//					BufferedImage buffImg = null;
////					buffImg = ImageIO.read(new File("secadorCD.png"));
//					buffImg = ImageIO.read(urlIconSecador);
//					Image tmp = buffImg.getScaledInstance(lblNewLabel.getWidth(), lblNewLabel.getHeight(), Image.SCALE_SMOOTH);
//					BufferedImage rebuffImg = new BufferedImage(lblNewLabel.getWidth(), lblNewLabel.getHeight(), BufferedImage.TYPE_INT_ARGB);
//					
//					Graphics2D g2d = rebuffImg.createGraphics();
//					g2d.drawImage(tmp, 0, 0, null);
//					g2d.dispose();
//					
//					imgIcon = new ImageIcon(rebuffImg);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
				
				
				
				//TAB-ORDER
//				setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txfNovoLote, txfLoteOrigem, txfLoteDestino, cmbbxReceita, btnEditarReceita, txfData, txfHora, chkbxTempoTerreiro, spnTempoTerreiro, rdbtnCilindroLiga, rdbtnCilindroDesliga, rdbtnVentiladorLiga, rdbtnVentiladorDesliga, rdbtnAuxiliarLiga, rdbtnAuxiliarDesliga, btnAtualizar, txfBebida, txfRenda, txfTulhaDestino, txfVariedade, prgrssbrColetaAmostra, btnIniciar, btnDescansar, btnReiniciar, btnFinalizar}));
				setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txfNovoLote, txfLoteOrigem, txfLoteDestino, cmbbxReceita, btnEditarReceita, txfData, txfHora, chkbxTempoTerreiro, spnTempoTerreiro, btnAcessarMotores, txfBebida, txfRenda, txfTulhaDestino, txfVariedade, prgrssbrColetaAmostra, btnIniciar, btnDescansar, btnReiniciar, btnFinalizar}));
				
				
	}

///////////////////////////////////////////////////
///////////////////////////////////////////////////
///////////////////////////////////////////////////
	
	//TODO: GETTERS
	public boolean getChkbxNovoLoteIsSelected() {
		
		return chkbxNovoLote.isSelected();
		
	}
	
	public boolean getChkbxTempoTerreiroIsSelected() {
		
		return chkbxTempoTerreiro.isSelected();
		
	}
	
	public String getTxfBebidaText() {
		
		return txfBebida.getText();
		
	}
	
	public String getTxfRendaText() {
		
		return txfRenda.getText();
		
	}
	
	public String getTxfTulhaDestinoText() {
		
		return txfTulhaDestino.getText();
		
	}
	
	public String getTxfVariedadeText() {
		
		return txfVariedade.getText();
		
	}
	
	public String getCmbbxLoteSecadorSelectedItem() {
		
		return (String)cmbbxLoteSecador.getSelectedItem();
		
	}
	
	public String getTxfNovoLoteText() {
		
		return txfNovoLote.getText();
		
	}
	
	public String getTxfLoteOrigemText() {
		
		return txfLoteOrigem.getText();
		
	}
	
	public String getTxfLoteDestinoText() {
		
		return txfLoteDestino.getText();
		
	}
	
	public String getCmbbxReceitaSelectedItem() {
		
		return cmbbxReceita.getSelectedItem().toString();
		
	}
	
	public String getTxfDataText() {
		
		return txfData.getText();
		
	}
	
	public String getTxfHoraText() {
		
		return txfHora.getText();
		
	}
	
	public int getSpnTempoTerreiroValue() {
		
		return (int) spnTempoTerreiro.getValue();
		
	}
	
//	public boolean getRdbtnCilindroLigaSelected() {
//		
//		return rdbtnCilindroLiga.isSelected();
//		
//	}
	
//	public boolean getRdbtnCilindroDesligaSelected() {
//		
//		return rdbtnCilindroDesliga.isSelected();
//		
//	}
	
//	public boolean getRdbtnVentiladorLigaSelected() {
//		
//		return rdbtnVentiladorLiga.isSelected();
//		
//	}
	
//	public boolean getRdbtnVentiladorDesligaSelected() {
//		
//		return rdbtnVentiladorDesliga.isSelected();
//		
//	}
	
//	public boolean getRdbtnAuxiliarLigaSelected() {
//		
//		return rdbtnAuxiliarLiga.isSelected();
//		
//	}
	
//	public boolean getRdbtnAuxiliarDesligaSelected() {
//		
//		return rdbtnAuxiliarDesliga.isSelected();
//		
//	}
	
	public String getLblTempoSecagemValorText() {
		
		return lblTempoSecagemValor.getText();
		
	}
	
	public String getLblTempoDescansoValorText() {
		
		return lblTempoDescansoValor.getText();
		
	}
	
	public String getLblTempoTotalValorText() {
		
		return lblTempoTotalValor.getText();
		
	}
	
	public String getLblSecadorELoteText() {
		
		return lblSecadorELote.getText();
		
	}
	
	public String getBtnFinalizarText() {
		
		return btnFinalizar.getText();
		
	}
	
///////////////////////////////////////////////////////////	
///////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////
	
	
	//TODO: SETTERS
	public void setPrgrssbrColetaAmostraVisible(boolean visible) {
		
		prgrssbrColetaAmostra.setVisible(visible);
		
	}
	
	
	public void setPrgrssbrColetaAmostraValue(int value) {
		
		prgrssbrColetaAmostra.setValue(value);
		
	}
	
	public void setLblSecadorELoteText(String secadorNumero, String loteNome) {
		
		lblSecadorELote.setText(secadorNumero + " :: Lote: ( " + loteNome + " )");
				
	}
	
	public String getSaidasValue() {
	
		boolean flagCilindroEnabled = false;
		boolean flagVentiladorEnabled = false;
		boolean flagAuxiliarEnabled = false;
		String saidas = null;
		
		//CILINDRO
//		if(rdbtnCilindroLiga.isSelected()) {
//			
//			//lblLEDCilindro.setForeground(Color.GREEN);
//			flagCilindroEnabled = true;
//			
//		} //else //lblLEDCilindro.setForeground(Color.RED);
		
		
		//VENTILADOR
//		if(rdbtnVentiladorLiga.isSelected()) {
//			
//			//lblLEDVentilador.setForeground(Color.GREEN);
//			flagVentiladorEnabled = true;
//
//		} //else //lblLEDVentilador.setForeground(Color.RED);
		
		
		//AUXILIAR
//		if(rdbtnAuxiliarLiga.isSelected()) {
//			
//			//lblLEDAuxiliar.setForeground(Color.GREEN);
//			flagAuxiliarEnabled = true;
//
//		} //else //lblLEDAuxiliar.setForeground(Color.RED);
		
		
		//CILINDRO = VENTILADOR = AUXILIAR = OFF :: [CILINDRO-VENTILADOR-AUXILIAR] = [0-0-0]
		if(!flagCilindroEnabled && !flagVentiladorEnabled && !flagAuxiliarEnabled) {
			
			saidas = "00";
			
			
		  //CILINDRO = VENTILADOR = OFF :: AUXILIAR = ON :: [CILINDRO-VENTILADOR-AUXILIAR] = [0-0-1]
		} else if (!flagCilindroEnabled && !flagVentiladorEnabled && flagAuxiliarEnabled) {
			
			saidas = "04";
			
			
		  //CILINDRO = AUXILIAR = OFF :: VENTILADOR = ON :: [CILINDRO-VENTILADOR-AUXILIAR] = [0-1-0]	
		} else if (!flagCilindroEnabled && flagVentiladorEnabled && !flagAuxiliarEnabled) {
			
			saidas = "02";
			
			
		  //CILINDRO = OFF :: VENTILADOR = AUXILIAR = ON :: [CILINDRO-VENTILADOR-AUXILIAR] = [0-1-1]	
		} else if (!flagCilindroEnabled && flagVentiladorEnabled && flagAuxiliarEnabled) {
			
			saidas = "06";
			
			
		  //CILINDRO = ON :: VENTILADOR = AUXILIAR = OFF :: [CILINDRO-VENTILADOR-AUXILIAR] = [1-0-0]
		} else if (flagCilindroEnabled && !flagVentiladorEnabled && !flagAuxiliarEnabled) {
			
			saidas = "01";
			
			
		  //CILINDRO = AUXILIAR = ON :: VENTILADOR = OFF :: [CILINDRO-VENTILADOR-AUXILIAR] = [1-0-1]
		} else if (flagCilindroEnabled && !flagVentiladorEnabled && flagAuxiliarEnabled) {
			
			saidas = "05";
			
			
		  //CILINDRO = VENTILADOR = ON :: AUXILIAR = OFF :: [CILINDRO-VENTILADOR-AUXILIAR] = [1-1-0]	
		} else if (flagCilindroEnabled && flagVentiladorEnabled && !flagAuxiliarEnabled) {
			
			saidas = "03";
			
			
		  //CILINDRO = VENTILADOR = AUXILIAR = ON :: [CILINDRO-VENTILADOR-AUXILIAR] = [1-1-1]
		} else if (flagCilindroEnabled && flagVentiladorEnabled && flagAuxiliarEnabled) {
			
			saidas = "07";
			
		}
		
		return saidas;
		
	}
	
	public void setTxfLoteOrigemEnabled(boolean enabled) {
		
		txfLoteOrigem.setEnabled(enabled);
		
	}
	
	public void setTxfLoteOrigemText(String text) {
		
		txfLoteOrigem.setText(text);
		
	}
	
	public void setTxfLoteDestinoEnabled(boolean enabled) {
		
		txfLoteDestino.setEnabled(enabled);
		
	}
	
	public void setTxfLoteDestinoText(String text) {
		
		txfLoteDestino.setText(text);
		
	}
	
	public void setBtnOkCmbbxLoteSecadorEnabled(boolean enabled) {
		
		btnOkCmbbxLoteSecador.setEnabled(enabled);
		
	}
	
	public void setTxfNovoLoteEnabled(boolean enabled) {
		
		txfNovoLote.setEnabled(enabled);
		
	}
	
	public void setTxfNovoLoteText(String text) {
		
		txfNovoLote.setText(text);
		
	}
	
	public void setCmbbxLoteSecadorEnabled(boolean enabled) {
		
		cmbbxLoteSecador.setEnabled(enabled);
		
	}
	
	public void setCmbbxReceitaEnabled(boolean enabled) {
		
		cmbbxReceita.setEnabled(enabled);
		
	}
	
	public void setSpnTempoTerreiroEnabled(boolean enabled) {
		
		spnTempoTerreiro.setEnabled(enabled);
		
	}
	
	public void setSpnTempoTerreiroValue(int valor) {
		
		spnTempoTerreiro.setValue(valor);
		
	}
	
	public void setTxfDataSistemaText() {
		Date data = new Date();
		txfData.setText(dateFormat.format(data));
	}
	
	public void setTxfHoraSistemaText() {
		Date hora = new Date();
		txfHora.setText(hourFormat.format(hora));
	}
	
	public void setTxfDataInicioEnabled(boolean enabled) {
		
		txfData.setEnabled(enabled);
		
	}
	
	public void setTxfDataInicioText(String text) {
		
		txfData.setText(text);
	}
	
	public void setTxfDataInicioTextFormatted(Date dataInicio) {
		
		txfData.setText(dateFormat.format(dataInicio));
	}
	
	public void setTxfHoraInicioEnabled(boolean enabled) {
		
		txfHora.setEnabled(enabled);
		
	}
	
	public void setTxfHoraInicioText(String text) {
		
		txfHora.setText(text);
	}
	
	public void setTxfHoraInicioTextFormatted(Time horaInicio) {
		
		txfHora.setText(hourFormat.format(horaInicio));
	}
	
//	public void setRdbtnCilindroLigaSelected(boolean selected) {
//		
//		rdbtnCilindroLiga.setSelected(selected);
//		
//	}
	
//	public void setRdbtnCilindroDesligaSelected(boolean selected) {
//		
//		rdbtnCilindroDesliga.setSelected(selected);
//		
//	}
	
//	public void setRdbtnVentiladorLigaSelected(boolean selected) {
//		
//		rdbtnVentiladorLiga.setSelected(selected);
//		
//	}
	
//	public void setRdbtnVentiladorDesligaSelected(boolean selected) {
//		
//		rdbtnVentiladorDesliga.setSelected(selected);
//		
//	}
	
//	public void setRdbtnAuxiliarLigaSelected(boolean selected) {
//		
//		rdbtnAuxiliarLiga.setSelected(selected);
//		
//	}
	
//	public void setRdbtnAuxiliarDesligaSelected(boolean selected) {
//		
//		rdbtnAuxiliarDesliga.setSelected(selected);
//		
//	}
	
//	public void setRdbtnCilindroLigaEnabled(boolean enabled) {
//		
//		rdbtnCilindroLiga.setEnabled(enabled);
//		
//	}
	
//	public void setRdbtnCilindroDesligaEnabled(boolean enabled) {
//		
//		rdbtnCilindroDesliga.setEnabled(enabled);
//		
//	}
	
//	public void setRdbtnVentiladorLigaEnabled(boolean enabled) {
//		
//		rdbtnVentiladorLiga.setEnabled(enabled);
//		
//	}
	
//	public void setRdbtnVentiladorDesligaEnabled(boolean enabled) {
//		
//		rdbtnVentiladorDesliga.setEnabled(enabled);
//		
//	}
	
//	public void setRdbtnAuxiliarLigaEnabled(boolean enabled) {
//		
//		rdbtnAuxiliarLiga.setEnabled(enabled);
//		
//	}
	
//	public void setRdbtnAuxiliarDesligaEnabled(boolean enabled) {
//		
//		rdbtnAuxiliarDesliga.setEnabled(enabled);
//		
//	}
	
	public void setBtnDescansarEnabled(boolean selected) {
		
		btnDescansar.setEnabled(selected);
		
	}
	
	public void setBtnDescansarVisible(boolean visible) {
		
		btnDescansar.setVisible(visible);
		
	}
	
	public void setBtnReiniciarEnabled(boolean enabled) {
		
		btnReiniciar.setEnabled(enabled);
		
	}
	
	public void setBtnReiniciarVisible(boolean visible) {
		
		btnReiniciar.setVisible(visible);
		
	}
	
	public void setBtnDescansarText(String text) {
		
		btnDescansar.setText(text);
		
	}
	
	public void setBtnFinalizarVisible(boolean visible) {
		
		btnFinalizar.setVisible(visible);
		
	}
	
	public void setBtnFinalizarEnabled(boolean enabled) {
		
		btnFinalizar.setEnabled(enabled);
		
	}
	
	public void setBtnFinalizarSelected(boolean selected) {
		
		btnFinalizar.setSelected(selected);
		
	}
	
	public void setLblBebidaVisible(boolean visible) {
		
		lblBebida.setVisible(visible);
		
	}
	
	public void setTxfBebidaVisible(boolean visible) {
		
		txfBebida.setVisible(visible);
		
	}
	
	public void setTxfBebidaText(String text) {
		
		txfBebida.setText(text);
		
	}
	
	public void setLblRendaVisible(boolean visible) {
		
		lblRenda.setVisible(visible);
		
	}
	
	public void setTxfRendaVisible(boolean visible) {
		
		txfRenda.setVisible(visible);
		
	}
	
	public void setTxfRendaText(String text) {
		
		txfRenda.setText(text);
		
	}
	
	public void setLblTulhaDestinoVisible(boolean visible) {
		
		lblTulhaDestino.setVisible(visible);
		
	}
	
	public void setTxfTulhaDestinoVisible(boolean visible) {
		
		txfTulhaDestino.setVisible(visible);
		
	}
	
	public void setLblVariedadeVisible(boolean visible) {
		
		lblVariedade.setVisible(visible);
		
	}
	
	public void setTxfVariedadeVisible(boolean visible) {
		
		txfVariedade.setVisible(visible);
		
	}
	
	public void setTxfTulhaDestinoText(String text) {
		
		txfTulhaDestino.setText(text);
		
	}
	
	public void setTxfVariedadeText(String text) {
		
		txfVariedade.setText(text);
		
	}
	
	public void setChkbxNovoLoteEnabled(boolean enabled) {
		
		chkbxNovoLote.setEnabled(enabled);
		
	}
	
	public void setChkbxNovoLoteSelected(boolean selected) {
		
		chkbxNovoLote.setSelected(selected);
		
	}
	
	public void setChkbxTempoTerreiroEnabled(boolean enabled) {
		
		chkbxTempoTerreiro.setEnabled(enabled);
		
	}
	
	public void setChkbxTempoTerreiroSelected(boolean selected) {
		
		chkbxTempoTerreiro.setSelected(selected);
		
	}
	
	public void setBtnEditarReceitaEnabled(boolean enabled) {
		
		btnEditarReceita.setEnabled(enabled);
		
	}
	
//	public void setBtnAtualizarEnabled(boolean enabled) {
//		
//		btnAtualizar.setEnabled(enabled);
//		
//	}
	
	public void setBtnIniciarVisible(boolean visible) {
		
		btnIniciar.setVisible(visible);
		
	}
	
	public void setBtnIniciarEnabled(boolean enabled) {
		
		btnIniciar.setEnabled(enabled);
		
	}
	
	public void setBtnFinalizarText(String text) {
		
		btnFinalizar.setText(text);
		
	}
	
	public void setLblTempoTotalValorVisible(boolean visible) {
		
		lblTempoTotalValor.setVisible(visible);
		
	}
	
	public void setLblTempoTotalValorEnabled(boolean enabled) {
		
		lblTempoTotalValor.setEnabled(enabled);
		
	}
	
	public void setLblTempoSecagemValorVisible(boolean visible) {
		
		lblTempoSecagemValor.setVisible(visible);
		
	}
	
	public void setLblTempoSecagemValorEnabled(boolean enabled) {
		
		lblTempoSecagemValor.setEnabled(enabled);
		
	}
	
	public void setLblTempoDescansoValorVisible(boolean visible) {
		
		lblTempoDescansoValor.setVisible(visible);
		
	}
	
	public void setLblTempoDescansoValorEnabled(boolean enabled) {
		
		lblTempoDescansoValor.setEnabled(enabled);
		
	}
	
	public void setLblTempoTotalValorText(String tempo) {
		
		lblTempoTotalValor.setText(tempo);
		
	}
	
	public void setLblTempoSecagemValorText(String tempo) {
		
		//lblTempoSecagemValor.setText(hourFormat.format(tempo));
		lblTempoSecagemValor.setText(tempo);
		
	}
	
	public void setLblTempoDescansoValorText(String tempo) {
		
		//lblTempoDescansoValor.setText(hourFormat.format(tempo));
		lblTempoDescansoValor.setText(tempo);
		
	}
	
//	public void setLblLEDCilindroEnabled(boolean enabled) {
//		
//		lblLEDCilindro.setEnabled(enabled);
//		
//	}
	
//	public void setLblLEDCilindroColor(Color color) {
//		
//		lblLEDCilindro.setForeground(color);
//		
//	}
	
//	public void setLblLEDVentiladorEnabled(boolean enabled) {
//		
//		lblLEDVentilador.setEnabled(enabled);
//		
//	}
	
//	public void setLblLEDVentiladorColor(Color color) {
//		
//		lblLEDVentilador.setForeground(color);
//		
//	}
	
//	public void setLblLEDAuxiliarEnabled(boolean enabled) {
//		
//		lblLEDAuxiliar.setEnabled(enabled);
//		
//	}
	
//	public void setLblLEDAuxiliarColor(Color color) {
//		
//		lblLEDAuxiliar.setForeground(color);
//		
//	}
	
	public void setCmbbxLoteSecadorRemoveAllItems() {
		
		cmbbxLoteSecador.removeAllItems();
		
	}
	
	public void setCmbbxLoteSecadorAddItem(String nomeLote) {
		
		cmbbxLoteSecador.addItem(nomeLote);
		
	}
	
	public void setCmbbxReceitaRemoveAllItems() {
		
		cmbbxReceita.removeAllItems();
		
	}
	
	public void setCmbbxReceitaAddItem(String nomeReceita) {
		
		cmbbxReceita.addItem(nomeReceita);
		
	}
	
//	public void setLblBorderCilindroEnabled(boolean enabled) {
//		
//		lblBorderCilindro.setEnabled(enabled);
//		
//	}
	
//	public void setLblBorderVentiladorEnabled(boolean enabled) {
//		
//		lblBorderVentilador.setEnabled(enabled);
//		
//	}
	
//	public void setLblBorderAuxiliarEnabled(boolean enabled) {
//		
//		lblBorderAuxiliar.setEnabled(enabled);
//		
//	}
	
	public void setRdgrpCilindroClearSelection() {
		
		rdgrpCilindro.clearSelection();
		
	}
	
	public void setRdgrpVentiladorClearSelection() {
		
		rdgrpVentilador.clearSelection();
		
	}
	
	public void setRdgrpAuxiliarClearSelection() {
		
		rdgrpAuxiliar.clearSelection();
		
	}

	public void setSaidasValue() {
		
		
		//CILINDRO
//		if(rdbtnCilindroLiga.isSelected()) {
//			
//			lblLEDCilindro.setForeground(Color.GREEN);
//			
//		} else lblLEDCilindro.setForeground(Color.RED);
		
		
		//VENTILADOR
//		if(rdbtnVentiladorLiga.isSelected()) {
//			
//			lblLEDVentilador.setForeground(Color.GREEN);
//
//		} else lblLEDVentilador.setForeground(Color.RED);
		
		
		//AUXILIAR
//		if(rdbtnAuxiliarLiga.isSelected()) {
//			
//			lblLEDAuxiliar.setForeground(Color.GREEN);
//
//		} else lblLEDAuxiliar.setForeground(Color.RED);
		
	}
	
	public void setBtnAcessarMotoresEnabled(boolean enabled) {
		
		btnAcessarMotores.setEnabled(enabled);
		
	}
	
	public void setBtnAcessarMotoresText(String text) {
		
		btnAcessarMotores.setText(text);
		
	}
	
	public void setBtnColetarAmostrasEnabled(boolean enabled) {
		
		btnColetarAmostras.setEnabled(enabled);
		
	}
	
	public void setBtnColetarAmostrasText(String text) {
		
		btnColetarAmostras.setText(text);
		
	}
	
//////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////
	
	
	//FAZ A LINKAGEM ENTRE O RUNNER E A VIEW [GERENCIAMENTOSECAGEM] POR INTERMÉDIO DO LISTENER [GERENCIAMENTOSECAGEM]
	public void addGerenciamentoSecagemListener(GerenciamentoSecagemListener gerenciamentoSecagemListener) {
		this.gerenciamentoSecagemListener = gerenciamentoSecagemListener;
	}
}
