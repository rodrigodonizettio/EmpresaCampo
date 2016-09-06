package com.campotech.sgsoft.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Dialog.ModalExclusionType;
import java.awt.color.CMMException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.net.URI;
import java.net.URL;
import java.sql.*;

import javax.swing.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.campotech.sgsoft.controller.AcessoDB;
import com.campotech.sgsoft.controller.listener.GerenciamentoRelatoriosListener;
import com.campotech.sgsoft.controller.listener.LoginListener;
import com.campotech.sgsoft.model.dao.AmostraDAO;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.TextField;
import com.itextpdf.text.pdf.fonts.otf.TableHeader;
import com.toedter.calendar.JDateChooser;

import javax.swing.JTable;
import javax.swing.JScrollPane;

import net.proteanit.sql.DbUtils;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ScrollPaneConstants;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Font;

public class GerenciamentoRelatoriosFrame extends JFrame {

	
	//ATRIBUTOS PERTENCENTES AO FRAME
	private JPanel contentPane;
	private JLabel lblAmostrasColetar;
	private JLabel lblSecador;
	private JComboBox cmbbxSecador;
	private JButton btnColetarAmostras; 
	
	private JLabel lblAmostrasPesquisar;
	private JLabel lblPesquisa;
	private JRadioButton rdbtnTudo;
	private JRadioButton rdbtnLote;
	private JRadioButton rdbtnSecador;
	private ButtonGroup rdgrpTipoPesquisa;
	private JComboBox cmbbxSecador1;
	private JTextField txfLote;
	
	private JLabel lblIntervaloTempo;
	private JRadioButton rdbtnDia;
	private JRadioButton rdbtnSemana;
	private JRadioButton rdbtnMes;
	private JRadioButton rdbtnPeriodo;
	private JDateChooser dtchsrPeriodoInicio;
	private ButtonGroup rdgrpIntervaloTempo;
	private JLabel lblA;
	private JDateChooser dtchsrPeriodoFim;
	private JButton btnPesquisarAmostras; 
	
	private JLabel lblAmostrasSalvar;
	private JButton btnPDF;
	private JButton btnXLS;
	
	private JTable tblAmostras;
	private JScrollPane scrllPnAmostras;
	
	private boolean rdbtnDiaOldState = false;
	private boolean rdbtnSemanaOldState = false;
	private boolean rdbtnMesOldState = false;
	private boolean rdbtnPeriodoOldState = false;
	
	
	//ATRIBUTOS PERTENCENTES AO LISTENER
	private GerenciamentoRelatoriosListener gerenciamentoRelatoriosListener;
	
	
	//ATRIBUTOS PERTENCENTES À DB
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	
	//ATRIBUTOS PERTENCENTES ÀS CONVERSÕES DE FORMATO
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	
	//ATRIBUTOS DE IMAGENS 
	URL urlIconCampotech = GerenciamentoRelatoriosFrame.class.getResource("images/icon-campotech.png");
	URL urlIconBtnSalvarPDF = GerenciamentoRelatoriosFrame.class.getResource("images/icon-exportPDFColor-36px.png");
	URL urlIconBtnSalvarXLS = GerenciamentoRelatoriosFrame.class.getResource("images/icon-exportXLSColor-36px.png");
		
	
	/**
	 * Create the frame.
	 */
	public GerenciamentoRelatoriosFrame() {
		
		try {
			connection = AcessoDB.conectar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		setTitle("Tela - Gerenciamento de Relatórios");
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
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				cmbbxSecador.requestFocus();
			}
			@Override
			public void windowClosed(WindowEvent arg0) {
				
				try {
					AcessoDB.desconectar(connection, preparedStatement, resultSet);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		//[LEFT] COLETA DE AMOSTRAS E FILTRO DE PESQUISA
		lblAmostrasColetar = new JLabel();
		lblAmostrasColetar.setBorder(new TitledBorder(null, "Amostras - Coletar", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		lblAmostrasColetar.setBounds(5, 5, 185, 103);
		lblAmostrasColetar.setVisible(false);
		contentPane.add(lblAmostrasColetar);
		
			lblSecador = new JLabel();
			lblSecador.setBounds(42, 25, 50, 20);
			lblSecador.setText("Secador:");
			lblSecador.setVisible(false);
			lblAmostrasColetar.add(lblSecador);
		
			cmbbxSecador = new JComboBox();
			cmbbxSecador.setBounds(94, 20, 50, 30);
			cmbbxSecador.setVisible(false);
			cmbbxSecador.addMouseListener(new MouseAdapter() {
				
				@Override
				public void mouseEntered(MouseEvent arg0) {
					
					System.out.println("CLICK!");
					gerenciamentoRelatoriosListener.cmbbxSecadorMouseEntered();
					
				}
				
			});
			
			lblAmostrasColetar.add(cmbbxSecador);
			
			btnColetarAmostras = new JButton();
			btnColetarAmostras.setBounds(30, 60, 125, 30);
			btnColetarAmostras.setText("Coletar Amostras");
			btnColetarAmostras.setVisible(false);
			btnColetarAmostras.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent arg0) {
					
					gerenciamentoRelatoriosListener.btnColetarAmostrasClicked();
					
				}
				
			});
			lblAmostrasColetar.add(btnColetarAmostras);
			
				
		lblAmostrasPesquisar = new JLabel();
		lblAmostrasPesquisar.setBorder(new TitledBorder(null, "Amostras - Pesquisar", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		//lblAmostrasPesquisar.setBounds(5, 110, 185, 385);
		lblAmostrasPesquisar.setBounds(5, 5, 185, 490);
		contentPane.add(lblAmostrasPesquisar);
		

			lblPesquisa = new JLabel();
			lblPesquisa.setBorder(new TitledBorder(null, "Tipo de Busca", TitledBorder.CENTER, TitledBorder.TOP, null, null));
			lblPesquisa.setBounds(10, 20, 165, 170);
			lblAmostrasPesquisar.add(lblPesquisa);
			
				rdbtnTudo = new JRadioButton();
				rdbtnTudo.setBounds(15, 30, 50, 20);
				rdbtnTudo.setText("Tudo");				
				rdbtnTudo.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						
						gerenciamentoRelatoriosListener.rdbtnBuscaTudoClicked();
						
					}
				});
				
				lblPesquisa.add(rdbtnTudo);
			
				rdbtnLote = new JRadioButton();
				rdbtnLote.setBounds(15, 60, 50, 20);
				rdbtnLote.setText("Lote");				
				rdbtnLote.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						
						gerenciamentoRelatoriosListener.rdbtnBuscaLoteClicked();
						
					}
					
				});
				
				lblPesquisa.add(rdbtnLote);
				
				rdbtnSecador = new JRadioButton();
				rdbtnSecador.setBounds(15, 90, 70, 20);
				rdbtnSecador.setText("Secador");
				rdbtnSecador.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						
						gerenciamentoRelatoriosListener.rdbtnBuscaSecadorClicked();
						
					}
					
				});
				
				lblPesquisa.add(rdbtnSecador);
			
				rdgrpTipoPesquisa = new ButtonGroup();
				rdgrpTipoPesquisa.add(rdbtnTudo);
				rdgrpTipoPesquisa.add(rdbtnLote);
				rdgrpTipoPesquisa.add(rdbtnSecador);
					
					
					txfLote = new JTextField();
					txfLote.setBounds(8, 120, 150, 30);
					txfLote.setVisible(false);
					txfLote.setDocument(new JTextFieldCharacterLimit(30));
					txfLote.addFocusListener(new FocusAdapter() {
						
						@Override
						public void focusGained(FocusEvent arg0) {
							gerenciamentoRelatoriosListener.txfLoteClicked();
						}
					});
					lblPesquisa.add(txfLote);
					
				
					cmbbxSecador1 = new JComboBox();
					cmbbxSecador1.setBounds(86, 85, 50, 30);
					cmbbxSecador1.setVisible(false);
					for(int i = 1; i <= 30 ; i++) {
						cmbbxSecador1.addItem(Integer.toString(i));
					}
					lblPesquisa.add(cmbbxSecador1);
				
				
			lblIntervaloTempo = new JLabel();
			lblIntervaloTempo.setBorder(new TitledBorder(null, "Intervalo de Tempo", TitledBorder.CENTER, TitledBorder.TOP, null, null));
			lblIntervaloTempo.setBounds(10, 200, 165, 280);
			lblAmostrasPesquisar.add(lblIntervaloTempo);
			
				rdbtnDia = new JRadioButton();
				rdbtnDia.setBounds(10, 30, 50, 20);
				rdbtnDia.setText("Hoje");
				rdbtnDia.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						
						gerenciamentoRelatoriosListener.rdbtnBuscaDiaClicked();
						
					}
					
				});
				
				lblIntervaloTempo.add(rdbtnDia);
				
				rdbtnSemana = new JRadioButton();
				rdbtnSemana.setBounds(10, 60, 105, 20);
				rdbtnSemana.setText("Nesta Semana");
				rdbtnSemana.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
					
						gerenciamentoRelatoriosListener.rdbtnBuscaSemanaClicked();
						
					}
					
				});
				
				lblIntervaloTempo.add(rdbtnSemana);
				
				rdbtnMes = new JRadioButton();
				rdbtnMes.setBounds(10, 90, 85, 20);
				rdbtnMes.setText("Neste Mês");
				rdbtnMes.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						
						gerenciamentoRelatoriosListener.rdbtnBuscaMesClicked();
						
					}
					
				});

				lblIntervaloTempo.add(rdbtnMes);
				
				rdbtnPeriodo = new JRadioButton();
				rdbtnPeriodo.setBounds(10, 120, 105, 20);
				rdbtnPeriodo.setText("No Período de:");
				rdbtnPeriodo.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						
						gerenciamentoRelatoriosListener.rdbtnBuscaPeriodoClicked();
						
					}
					
				});
				
				lblIntervaloTempo.add(rdbtnPeriodo);
				
				rdgrpIntervaloTempo = new ButtonGroup();
				rdgrpIntervaloTempo.add(rdbtnDia);
				rdgrpIntervaloTempo.add(rdbtnSemana);
				rdgrpIntervaloTempo.add(rdbtnMes);
				rdgrpIntervaloTempo.add(rdbtnPeriodo);
				
				dtchsrPeriodoInicio = new JDateChooser();
				dtchsrPeriodoInicio.setBounds(20, 145, 125, 30);
				dtchsrPeriodoInicio.setEnabled(false);
				lblIntervaloTempo.add(dtchsrPeriodoInicio);
				
				lblA = new JLabel();
				lblA.setBounds(80, 173, 10, 20);
				lblA.setEnabled(false);
				lblA.setText("à");
				lblIntervaloTempo.add(lblA);
				
				dtchsrPeriodoFim = new JDateChooser();
				dtchsrPeriodoFim.setBounds(20, 195, 125, 30);
				dtchsrPeriodoFim.setEnabled(false);
				lblIntervaloTempo.add(dtchsrPeriodoFim);
		
			btnPesquisarAmostras = new JButton();
			btnPesquisarAmostras.setBounds(20, 430, 145, 30);
			btnPesquisarAmostras.setText("Pesquisar Amostras");
			btnPesquisarAmostras.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent arg0) {
					
					gerenciamentoRelatoriosListener.btnPesquisarAmostrasClicked();
					
					//SE "NENHUM" RADIO BUTTON SELECIONADO
					if((!getRdbtnTudoIsSelected()) && (!getRdbtnLoteIsSelected()) && (!getRdbtnSecadorIsSelected())) {
						JOptionPane.showMessageDialog(null, "Selecione algum TIPO DE BUSCA e tente novamente.", "Atenção - Busca Cancelada", JOptionPane.WARNING_MESSAGE);
					} else {
						
						//APENAS ESTÉTICA DESTA VIEW - MUDANÇA DE LARGURA/NOME DAS COLUNAS DA TABLE 
						tblAmostras.getColumnModel().getColumn(0).setPreferredWidth(60);
						tblAmostras.getColumnModel().getColumn(1).setPreferredWidth(225);
						tblAmostras.getColumnModel().getColumn(2).setPreferredWidth(155);
						tblAmostras.getColumnModel().getColumn(3).setPreferredWidth(150);
						tblAmostras.getColumnModel().getColumn(4).setPreferredWidth(85);
						tblAmostras.getColumnModel().getColumn(5).setPreferredWidth(85);
						tblAmostras.getColumnModel().getColumn(6).setPreferredWidth(70);
						tblAmostras.getColumnModel().getColumn(7).setPreferredWidth(225);
						tblAmostras.getColumnModel().getColumn(8).setPreferredWidth(70);
						tblAmostras.getColumnModel().getColumn(9).setPreferredWidth(225);
						tblAmostras.getColumnModel().getColumn(10).setPreferredWidth(225);
						tblAmostras.getColumnModel().getColumn(11).setPreferredWidth(150);
						tblAmostras.getColumnModel().getColumn(12).setPreferredWidth(70);
						tblAmostras.getColumnModel().getColumn(13).setPreferredWidth(225);
						
						tblAmostras.getColumnModel().getColumn(0).setHeaderValue("Secador");
						tblAmostras.getColumnModel().getColumn(1).setHeaderValue("Lote");
						tblAmostras.getColumnModel().getColumn(2).setHeaderValue("Temperatura Entrada [°C]");
						tblAmostras.getColumnModel().getColumn(3).setHeaderValue("Temepratura Massa [°C]");
						tblAmostras.getColumnModel().getColumn(4).setHeaderValue("Umidade [%]");
						tblAmostras.getColumnModel().getColumn(5).setHeaderValue("Data");
						tblAmostras.getColumnModel().getColumn(6).setHeaderValue("Hora");
						tblAmostras.getColumnModel().getColumn(7).setHeaderValue("Tulha Destino");
						tblAmostras.getColumnModel().getColumn(8).setHeaderValue("Renda [%]");
						tblAmostras.getColumnModel().getColumn(9).setHeaderValue("Bebida");
						tblAmostras.getColumnModel().getColumn(10).setHeaderValue("Variedade");
						tblAmostras.getColumnModel().getColumn(11).setHeaderValue("Controle de Temperatura");
						tblAmostras.getColumnModel().getColumn(12).setHeaderValue("SP");
						tblAmostras.getColumnModel().getColumn(13).setHeaderValue("Usuário no Sistema");
						
					}	
				}
			});
			
			lblAmostrasPesquisar.add(btnPesquisarAmostras);
			
			
		lblAmostrasSalvar = new JLabel();
		lblAmostrasSalvar.setBorder(new TitledBorder(null, "Amostras - Salvar", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		lblAmostrasSalvar.setBounds(5, 495, 185, 70);
		contentPane.add(lblAmostrasSalvar);
			
			btnPDF = new JButton();
			btnPDF.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(urlIconBtnSalvarPDF)));
			btnPDF.setBounds(30, 20, 36, 36);
			btnPDF.setToolTipText("Salvar como PDF");
			btnPDF.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent arg0) {
					
					gerenciamentoRelatoriosListener.btnExportarPDFClicked();
					
				}
				
			});

			lblAmostrasSalvar.add(btnPDF);
			
			btnXLS = new JButton();
			btnXLS.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(urlIconBtnSalvarXLS)));
			btnXLS.setBounds(115, 20, 36, 36);
			btnXLS.setToolTipText("Salvar como EXCEL");
			btnXLS.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent arg0) {
					
					gerenciamentoRelatoriosListener.btnExportarXLSClicked();
					
				}
				
			});

			lblAmostrasSalvar.add(btnXLS);
			
			scrllPnAmostras = new JScrollPane(tblAmostras, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			scrllPnAmostras.setBounds(200, 10, 585, 550);
			contentPane.add(scrllPnAmostras);
			
			tblAmostras = new JTable();
			tblAmostras.setEnabled(false);
			tblAmostras.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			scrllPnAmostras.setViewportView(tblAmostras);
			
			DefaultTableModel dftTblModel;
			//tblAmostras.getColumnModel().getColumn(0).setHeaderValue("Teste");
			
		
	} //CONSTRUTOR

///////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////
	
//////////////////////////
//GETTERS ////////////////
//////////////////////////	
	
	//GETTERS - CAPTURAM DADOS ESCOLHIDOS NOS JCOMBOBOX [SECADOR COLETA/SECADOR PESQUISA] E DATACHOOSER [PERÍODO INÍCIO/FIM]
	public int getCmbbxSecadorSelectedItem() {
		System.out.println(cmbbxSecador.getSelectedItem());
		return (int) cmbbxSecador.getSelectedItem();
	}

	public String getCmbbxSecador1SelectedItem() {
		return (String)cmbbxSecador1.getSelectedItem();
	}

	public String getDtchsrPeriodoInicio() {
		String dataInicio = null;
		if(dtchsrPeriodoInicio.getDate() != null) {
			dataInicio = dateFormat.format(dtchsrPeriodoInicio.getDate());
		} else return null;
		return dataInicio;
	}

	public String getDtchsrPeriodoFim() {
		String dataFim = null;
		if(dtchsrPeriodoFim.getDate() != null) {
			dataFim = dateFormat.format(dtchsrPeriodoFim.getDate());
		} else return null;
		return dataFim;		
	}

	public boolean getRdbtnTudoIsSelected() {
		return rdbtnTudo.isSelected();
	}
	
	public boolean getRdbtnLoteIsSelected() {
		return rdbtnLote.isSelected();
	}
	
	public boolean getRdbtnSecadorIsSelected() {
		return rdbtnSecador.isSelected();
	}
	
	public boolean getRdbtnDiaIsSelected() {
		return rdbtnDia.isSelected();
	}
	
	public boolean getRdbtnSemanaIsSelected() {
		return rdbtnSemana.isSelected();
	}
	
	public boolean getRdbtnMesIsSelected() {
		return rdbtnMes.isSelected();
	}
	
	public boolean getRdbtnPeriodoIsSelected() {
		return rdbtnPeriodo.isSelected();
	}
	
	public String getTxfLoteText() {
		return txfLote.getText();
	}
	
	public int getTblAmostrasRowCount() {
		return tblAmostras.getRowCount();
	}
	
	public int getTblAmostrasColumnCount() {
		return tblAmostras.getColumnCount();
	}
	
	public String getTblAmostrasColumnName(int numeroDaColuna) {
		return tblAmostras.getColumnName(numeroDaColuna);
	}
	
	public String getTblAmostrasGetValueAt(int indiceLinha, int indiceColuna) {
		return String.valueOf(tblAmostras.getModel().getValueAt(indiceLinha, indiceColuna));
	}
	
	public TableModel getTblAmostrasTableModel() {
		return tblAmostras.getModel();
	}

	
//////////////////////////	
// SETTERS ///////////////
//////////////////////////
	
	
	public void setBtnPesquisarAmostrasEnabled(boolean enabled) {
		btnPesquisarAmostras.setEnabled(enabled);
	}
	
	public void setDtchsrPeriodoInicioDate() {
		dtchsrPeriodoInicio.setDate(null);
	}
	
	public void setDtchsrPeriodoFimDate() {
		dtchsrPeriodoFim.setDate(null);
	}
	
	public void setCmbbxSecadorEnabled(boolean enabled) {
		cmbbxSecador.setEnabled(enabled);
	}
	
	public void setCmbbxSecador1Enabled(boolean enabled) {
		cmbbxSecador1.setEnabled(enabled);
	}
	
	public void setBtnColetarAmostrasEnabled(boolean enabled) {
		btnColetarAmostras.setEnabled(enabled);
	}
	
	public void setRdbtnTudoEnabled(boolean enabled) {
		rdbtnTudo.setEnabled(enabled);
	}
	
	public void setRdbtnLoteEnabled(boolean enabled) {
		rdbtnLote.setEnabled(enabled);
	}
	
	public void setRdbtnSecadorEnabled(boolean enabled) {
		rdbtnSecador.setEnabled(enabled);
	}
	
	public void setTxfLoteEnabled(boolean enabled) {
		txfLote.setEnabled(enabled);
	}
	
	public void setRdbtnDiaEnabled(boolean enabled) {
		rdbtnDia.setEnabled(enabled);
	}
	
	public void setRdbtnSemanaEnabled(boolean enabled) {
		rdbtnSemana.setEnabled(enabled);
	}
	
	public void setRdbtnMesEnabled(boolean enabled) {
		rdbtnMes.setEnabled(enabled);
	}
	
	public void setRdbtnPeriodoEnabled(boolean enabled) {
		rdbtnPeriodo.setEnabled(enabled);
	}
	
	public void setDtchsPeriodoInicioEnabled(boolean enabled) {
		dtchsrPeriodoInicio.setEnabled(enabled);
	}
	
	public void setDtchsPeriodoFimEnabled(boolean enabled) {
		dtchsrPeriodoFim.setEnabled(enabled);
	}
	
	public void setBtnPDFEnabled(boolean enabled) {
		btnPDF.setEnabled(enabled);
	}
	
	public void setBtnXLSEnabled(boolean enabled) {
		btnXLS.setEnabled(enabled);
	}
	
	
	
	
	
	
	
	
	public void setCmbbxSecador1Visible(boolean visible) {
		cmbbxSecador1.setVisible(visible);
	}
	
	public void setTxfLoteVisible(boolean visible) {
		txfLote.setVisible(visible);
	}
	
	public void setTxfLoteEmptyText() {
		txfLote.setFont(new Font("Tahoma", Font.ITALIC, 11));
		txfLote.setForeground(Color.LIGHT_GRAY);
		txfLote.setText("Digite a Palavra Chave");
	}
	
	public void setTxfLoteText() {
		txfLote.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txfLote.setForeground(Color.BLACK);
		txfLote.setText(null);
	}
	
	public void setDtchsrPeriodoEnabled(boolean enabled) {
		dtchsrPeriodoInicio.setEnabled(enabled);
		lblA.setEnabled(enabled);
		dtchsrPeriodoFim.setEnabled(enabled);
	}
	
	public void setTblAmostrasTblModel(TableModel tblModel) {
		tblAmostras.setModel(tblModel);
	}
	
	public void setRdbtnDiaSetSelected() {
		if(!rdbtnDiaOldState) {
			rdbtnDia.setSelected(true);
			setDtchsrPeriodoEnabled(false);
			rdbtnDiaOldState = true;
			
			//SE UM RADIOBUTTON DIFERENTE É CLICADO, OS DEMAIS SÃO DESMARCADOS, ENTÃO DEVEM RECEBER FLAG = FALSE
			rdbtnSemanaOldState = false;
			rdbtnMesOldState = false;
			rdbtnPeriodoOldState = false;
		} else {
			rdgrpIntervaloTempo.clearSelection();
			setDtchsrPeriodoEnabled(false);
			rdbtnDiaOldState = false;
		}
	}
	
	public void setRdbtnSemanaSetSelected() {
		if(!rdbtnSemanaOldState) {
			rdbtnSemana.setSelected(true);
			setDtchsrPeriodoEnabled(false);
			rdbtnSemanaOldState = true;
			
			//SE UM RADIOBUTTON DIFERENTE É CLICADO, OS DEMAIS SÃO DESMARCADOS, ENTÃO DEVEM RECEBER FLAG = FALSE
			rdbtnDiaOldState = false;
			rdbtnMesOldState = false;
			rdbtnPeriodoOldState = false;
		} else {
			rdgrpIntervaloTempo.clearSelection();
			setDtchsrPeriodoEnabled(false);
			rdbtnSemanaOldState = false;
		}
	}
	
	public void setRdbtnMesSetSelected() {
		if(!rdbtnMesOldState) {
			rdbtnMes.setSelected(true);
			setDtchsrPeriodoEnabled(false);
			rdbtnMesOldState = true;
			
			//SE UM RADIOBUTTON DIFERENTE É CLICADO, OS DEMAIS SÃO DESMARCADOS, ENTÃO DEVEM RECEBER FLAG = FALSE
			rdbtnDiaOldState = false;
			rdbtnSemanaOldState = false;
			rdbtnPeriodoOldState = false;
		} else {
			rdgrpIntervaloTempo.clearSelection();
			setDtchsrPeriodoEnabled(false);
			rdbtnMesOldState = false;
		}
	}
	
	public void setRdbtnPeriodoSetSelected() {
		if(!rdbtnPeriodoOldState) {
			rdbtnPeriodo.setSelected(true);
			setDtchsrPeriodoEnabled(true);
			rdbtnPeriodoOldState = true;
			
			//SE UM RADIOBUTTON DIFERENTE É CLICADO, OS DEMAIS SÃO DESMARCADOS, ENTÃO DEVEM RECEBER FLAG = FALSE
			rdbtnDiaOldState = false;
			rdbtnSemanaOldState = false;
			rdbtnMesOldState = false;
		} else {
			rdgrpIntervaloTempo.clearSelection();
			setDtchsrPeriodoEnabled(false);
			rdbtnPeriodoOldState = false;
			
		}
	}
	
	public void setCmbbxSecadorRemoveAllItems() {
		
		cmbbxSecador.removeAllItems();
		
	}
	
	public void setCmbbxSecadorAddItem(int numeroSecador) {
		
		cmbbxSecador.addItem(numeroSecador);
		
	}
	
	

///////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////
	
	
	//FAZ A LINKAGEM ENTRE O RUNNER E A VIEW [GERENCIAMENTORELATORIOS] POR INTERMÉDIO DO LISTENER [GERENCIAMENTORELATORIOS]
	public void addGerenciamentoRelatoriosListener(GerenciamentoRelatoriosListener gerenciamentoRelatoriosListener) {
		this.gerenciamentoRelatoriosListener = gerenciamentoRelatoriosListener;
	}
		
}
