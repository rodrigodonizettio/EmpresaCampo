package com.campotech.sgsoft.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JButton;
import javax.swing.UIManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Dialog.ModalExclusionType;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JSlider;
import javax.swing.JPanel;

import java.awt.GridLayout;

import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import com.campotech.sgsoft.controller.Runner;
import com.campotech.sgsoft.controller.listener.LoginListener;
import com.campotech.sgsoft.controller.listener.MainListener;
import com.campotech.sgsoft.model.entity.Secador;

import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.TimerTask;
import java.awt.event.WindowAdapter;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;


public class MainFrame extends JFrame {

	
	//VARIÁVEIS PARA CONTROLE DE REDIMENSIONAMENTO DO FRAME NA TELA
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int frameWidth = 0;
	private int frameHeight = 0;
	
	
	//VARIÁVEIS DE REDIMENSIONAMENTO DO FRAME SECADOR
	private int jIntFrameHeight;
	private int jIntFrameWidth;
	
	
	//ATRIBUTOS PERTENCENTES AO FRAME
	private JScrollPane scrollPane;
	private JPanel panel;
	private SecadorFrame secadorFrame1;
	private SecadorFrame secadorFrame2;
	private SecadorFrame secadorFrame3;
	private SecadorFrame secadorFrame4;
	private SecadorFrame secadorFrame5;
	private SecadorFrame secadorFrame6;
	private SecadorFrame secadorFrame7;
	private SecadorFrame secadorFrame8;
	private SecadorFrame secadorFrame9;
	private SecadorFrame secadorFrame10;
	private SecadorFrame secadorFrame11;
	private SecadorFrame secadorFrame12;
	private SecadorFrame secadorFrame13;
	private SecadorFrame secadorFrame14;
	private SecadorFrame secadorFrame15;
	private SecadorFrame secadorFrame16;
	private SecadorFrame secadorFrame17;
	private SecadorFrame secadorFrame18;
	private SecadorFrame secadorFrame19;
	private SecadorFrame secadorFrame20;
	private SecadorFrame secadorFrame21;
	private SecadorFrame secadorFrame22;
	private SecadorFrame secadorFrame23;
	private SecadorFrame secadorFrame24;
	private SecadorFrame secadorFrame25;
	private SecadorFrame secadorFrame26;
	private SecadorFrame secadorFrame27;
	private SecadorFrame secadorFrame28;
	private SecadorFrame secadorFrame29;
	private SecadorFrame secadorFrame30;
	private JMenuBar menuBar;
	private JMenu mnUsuarios;
	private JMenu mnReceitas;
	private JMenu mnRelatorios;
	private JMenu mnSobre;
	private JMenu mnComunicacao;
	
	
	//ATRIBUTOS PERTENCENTES AO LISTENER
	private MainListener mainListener;
	
	
	//ATRIBUTOS DE IMAGENS 
	URL urlIconCampotech = GerenciamentoRelatoriosFrame.class.getResource("images/icon-campotech.png");
	
/*				
					mainFrame.addWindowStateListener(new WindowStateListener() {
						
						@Override
						public void windowStateChanged(WindowEvent arg0) {
							
							if((arg0.getOldState() == Frame.MAXIMIZED_BOTH) && (arg0.getNewState() == Frame.ICONIFIED)) {
								System.out.println("Maximize -> Iconified");
							}
																
							if((arg0.getOldState() == Frame.ICONIFIED) && (arg0.getNewState() == Frame.NORMAL)) {
								System.out.println("Iconified -> Normal");
							}
							
							if((arg0.getOldState() == Frame.MAXIMIZED_BOTH) && (arg0.getNewState() == Frame.NORMAL)) {
								System.out.println("Maximize -> Normal");
							}
							
							if((arg0.getOldState() == Frame.NORMAL) && (arg0.getNewState() == Frame.MAXIMIZED_BOTH)) {
								System.out.println("Normal -> Maximized");
							}
							
							//6: Iconified -> Maximize
							if(arg0.getNewState() == 6) {
								System.out.println("Iconified -> Maximized");
							}
						
							//7: Maximize -> Iconified
							if(arg0.getNewState() == 7) {
								System.out.println("Maximized -> Iconified");
							}					
							
						}
					});
*/

	
	//CONSTRUTOR
	public MainFrame() {
		
		setExtendedState(Frame.MAXIMIZED_VERT);
		setTitle("SGSoft - Tela Principal [CampoTECH - Tecnologia a Serviço do Produtor]");
		
		//<POSICIONANDO FRAME NO CENTRO DA TELA DO COMPUTADOR
		if(screenSize.width <= 1366) {
			
			setBounds(0, 0, 800, 600);
			frameWidth = this.getSize().width;
			frameHeight = this.getSize().height;
			this.setLocation(screenSize.width/2 - frameWidth/2, screenSize.height/2 - frameHeight/2);
			
		} else {
			
			setBounds(0, 0, 1382, 830);
			frameWidth = this.getSize().width;
			frameHeight = this.getSize().height;
			this.setLocation(screenSize.width/2 - frameWidth/2, screenSize.height/2 - frameHeight/2);
			
		}
		
		System.out.println("SCREENSIZE - WIDTH: " + screenSize.width);
		System.out.println("SCREENSIZE - HEIGHT: " + screenSize.height);
		System.out.println("THIS - WIDTH: " + frameWidth);
		System.out.println("THIS - HEIGHT: " + frameHeight);
		//getContentPane().setBorder(new EmptyBorder(5, 5, 5, 5));
		//setResizable(false);
		

///////////////////////////////////
///////////////////////////////////		
		addWindowStateListener(new WindowStateListener() {
			
			@Override
			public void windowStateChanged(WindowEvent arg0) {
				
				/*
				if((arg0.getOldState() == Frame.MAXIMIZED_BOTH) && (arg0.getNewState() == Frame.ICONIFIED)) {
					System.out.println("Maximize -> Iconified");
				}
													
				if((arg0.getOldState() == Frame.ICONIFIED) && (arg0.getNewState() == Frame.NORMAL)) {
					System.out.println("Iconified -> Normal");
				}
				
				if((arg0.getOldState() == Frame.MAXIMIZED_BOTH) && (arg0.getNewState() == Frame.NORMAL)) {
					System.out.println("Maximize -> Normal");
				}
				*/
				/*
				if((arg0.getOldState() == Frame.NORMAL) && (arg0.getNewState() == Frame.MAXIMIZED_BOTH)) {
					System.out.println("Normal -> Maximized");
					setLocation(screenSize.width/2 - getSize().width/2, screenSize.height/2 - getSize().height/2);
				}
				*/
				/*
				//6: Iconified -> Maximize
				if(arg0.getNewState() == 6) {
					System.out.println("Iconified -> Maximized");
				}
				
				//7: Maximize -> Iconified
				if(arg0.getNewState() == 7) {
					System.out.println("Maximized -> Iconified");
				}					
				*/
				
			}
		});
		
		
///////////////////////////////////		
///////////////////////////////////				
		
		
		addComponentListener(new ComponentAdapter() {
			
			@Override
			public void componentResized(ComponentEvent arg0) {
			
				frameWidth = getWidth();
				frameHeight = getHeight();
				
				System.out.println("***** THIS - WIDTH: " + getWidth());
				System.out.println("***** THIS - HEIGHT: " + getHeight());
				
				if((screenSize.width <= 1366) && (screenSize.height <= 768)) {
					
					//setBounds(0, 0, frameWidth, frameHeight);
					//setLocation(screenSize.width/2 - frameWidth/2, screenSize.height/2 - frameHeight/2);
					
				} else if((screenSize.width > 1366) && (screenSize.height > 768)) {
					
					if(frameWidth > 1382) {
						
						setBounds((int) getLocation().getX(), (int) getLocation().getY(), 1382, frameHeight);
						
					} else if(frameHeight > 850) {
						
						setBounds((int) getLocation().getX(), (int) getLocation().getY(), frameWidth, 850);
						
					}
					
				} 
				
				/*
				else if((screenSize.width <= 1366) && (screenSize.height > 768)) {
					
					if(frameWidth > 1382) {
						
						setBounds((int) getLocation().getX(), (int) getLocation().getY(), 1382, frameHeight);
						
					} else if(frameHeight > 1035) {
						
						setBounds((int) getLocation().getX(), (int) getLocation().getY(), frameWidth, 1035);
						
					}
					
				}
				*/
				/*
				if(getWidth() < 1382) {
					
					setBounds(0, 0, getWidth(), getHeight());
					setLocation(screenSize.width/2 - getSize().width/2, screenSize.height/2 - getSize().height/2);
					
				} else {
					
					setBounds(0, 0, 1382, getHeight());
					setLocation(screenSize.width/2 - getSize().width/2, screenSize.height/2 - getSize().height/2);
					
				}
				*/
				super.componentResized(arg0);
				
				
				
			}
			
		});
		
		
		
		//NÃO PERMITE QUE A TELA RECEBA FOCO AUTOMÁTICO AO TER QUALQUER DE SEUS COMPONENTES ATUALIZADOS
		setAutoRequestFocus(true);
		//setRootPaneCheckingEnabled(false);
		//setFocusableWindowState(false);
		//setFocusTraversalKeysEnabled(false);
		//setFocusCycleRoot(false);
		
		
		
		//NÃO PERMITE QUE A TELA RECEBA FOCO AUTOMÁTICO AO TER QUALQUER DE SEUS COMPONENTES ATUALIZADOS
		//E PREJUDICA FUNCIONALIDADE DE MINIMIZE/RESTORE FROM TASKBAR APPLICATION ICON
		//setFocusableWindowState(false);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBackground(Color.WHITE);
		setForeground(Color.WHITE);
		//<CRIANDO ICONE PARA APLICAÇÃO
		setIconImage(Toolkit.getDefaultToolkit().getImage(urlIconCampotech).getScaledInstance(32, 32, java.awt.Image.SCALE_SMOOTH));
		//CRIANDO ICONE PARA APLICAÇÃO>
		
		addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent arg0) {
				
				mainListener.windowClosingActionPerformed();
				
			}
			
		});	
		
		jIntFrameHeight = 375;
		jIntFrameWidth = 450;
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setForeground(Color.WHITE);
		//scrollPane.getHorizontalScrollBar().setUnitIncrement(20);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		getContentPane().add(scrollPane, gbc_scrollPane);
		
		panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setBackground(Color.WHITE);
		GridBagLayout gbl_panel = new GridBagLayout();
		//gbl_panel.columnWidths = new int[]{jIntFrameWidth, jIntFrameWidth, jIntFrameWidth, jIntFrameWidth, jIntFrameWidth, jIntFrameWidth};
		//gbl_panel.rowHeights = new int[]{jIntFrameHeight, jIntFrameHeight, jIntFrameHeight, jIntFrameHeight, jIntFrameHeight};
		
		//gbl_panel.columnWidths = new int[]{jIntFrameWidth, jIntFrameWidth, jIntFrameWidth, jIntFrameWidth, jIntFrameWidth,
				//jIntFrameWidth, jIntFrameWidth, jIntFrameWidth, jIntFrameWidth, jIntFrameWidth};
		//gbl_panel.rowHeights = new int[]{jIntFrameHeight, jIntFrameHeight, jIntFrameHeight};
		
		gbl_panel.columnWidths = new int[]{jIntFrameWidth, jIntFrameWidth, jIntFrameWidth};
		gbl_panel.rowHeights = new int[]{jIntFrameHeight, jIntFrameHeight, jIntFrameHeight, jIntFrameHeight, jIntFrameHeight,
				jIntFrameHeight, jIntFrameHeight, jIntFrameHeight, jIntFrameHeight, jIntFrameHeight};
		
		gbl_panel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		

		//INTERNAL FRAMES
		secadorFrame1 = new SecadorFrame();
		GridBagConstraints gbcInternalFrame1 = new GridBagConstraints();
		secadorFrame1.setBounds(0, 0, 420, 350);
		gbcInternalFrame1.fill = GridBagConstraints.BOTH;
		//Insets(int top, int left, int bottom, int right) 
		gbcInternalFrame1.insets = new Insets(5, 1, 5, 5);
		gbcInternalFrame1.gridx = 0;
		gbcInternalFrame1.gridy = 0;
		panel.add(secadorFrame1, gbcInternalFrame1);		
		secadorFrame1.setVisible(false);
		
		secadorFrame2 = new SecadorFrame();
		GridBagConstraints gbcInternalFrame2 = new GridBagConstraints();
		secadorFrame2.setBounds(0, 0, 420, 350);
		gbcInternalFrame2.fill = GridBagConstraints.BOTH;
		gbcInternalFrame2.insets = new Insets(5, 1, 5, 5);
		gbcInternalFrame2.gridx = 1;
		gbcInternalFrame2.gridy = 0;
		panel.add(secadorFrame2, gbcInternalFrame2);
		secadorFrame2.setVisible(false);
		
		secadorFrame3 = new SecadorFrame();
		GridBagConstraints gbcInternalFrame3 = new GridBagConstraints();
		secadorFrame3.setBounds(0, 0, 420, 350);
		gbcInternalFrame3.fill = GridBagConstraints.BOTH;
		gbcInternalFrame3.insets = new Insets(5, 1, 5, 5);
		gbcInternalFrame3.gridx = 2;
		gbcInternalFrame3.gridy = 0;
		panel.add(secadorFrame3, gbcInternalFrame3);
		secadorFrame3.setVisible(false);
		
		secadorFrame4 = new SecadorFrame();
		GridBagConstraints gbcInternalFrame4 = new GridBagConstraints();
		secadorFrame4.setBounds(0, 0, 420, 350);
		gbcInternalFrame4.fill = GridBagConstraints.BOTH;
		gbcInternalFrame4.insets = new Insets(5, 1, 5, 5);
		gbcInternalFrame4.gridx = 0;
		gbcInternalFrame4.gridy = 1;
		panel.add(secadorFrame4, gbcInternalFrame4);
		secadorFrame4.setVisible(false);
		
		secadorFrame5 = new SecadorFrame();
		GridBagConstraints gbcInternalFrame5 = new GridBagConstraints();
		secadorFrame5.setBounds(0, 0, 420, 350);
		gbcInternalFrame5.fill = GridBagConstraints.BOTH;
		gbcInternalFrame5.insets = new Insets(5, 1, 5, 5);
		gbcInternalFrame5.gridx = 1;
		gbcInternalFrame5.gridy = 1;
		panel.add(secadorFrame5, gbcInternalFrame5);
		secadorFrame5.setVisible(false);
		
		secadorFrame6 = new SecadorFrame();
		GridBagConstraints gbcInternalFrame6 = new GridBagConstraints();
		secadorFrame6.setBounds(0, 0, 420, 350);
		gbcInternalFrame6.fill = GridBagConstraints.BOTH;
		gbcInternalFrame6.insets = new Insets(5, 1, 5, 5);
		gbcInternalFrame6.gridx = 2;
		gbcInternalFrame6.gridy = 1;
		panel.add(secadorFrame6, gbcInternalFrame6);
		secadorFrame6.setVisible(false);
///////////////////////////////////////////////////////////////////////
		
		secadorFrame7 = new SecadorFrame();
		GridBagConstraints gbcInternalFrame7 = new GridBagConstraints();
		secadorFrame7.setBounds(0, 0, 420, 350);
		gbcInternalFrame7.fill = GridBagConstraints.BOTH;
		gbcInternalFrame7.insets = new Insets(5, 1, 5, 5);
		gbcInternalFrame7.gridx = 0;
		gbcInternalFrame7.gridy = 2;
		panel.add(secadorFrame7, gbcInternalFrame7);
		secadorFrame7.setVisible(false);
		
		secadorFrame8 = new SecadorFrame();
		GridBagConstraints gbcInternalFrame8 = new GridBagConstraints();
		secadorFrame8.setBounds(0, 0, 420, 350);
		gbcInternalFrame8.fill = GridBagConstraints.BOTH;
		gbcInternalFrame8.insets = new Insets(5, 1, 5, 5);
		gbcInternalFrame8.gridx = 1;
		gbcInternalFrame8.gridy = 2;
		panel.add(secadorFrame8, gbcInternalFrame8);
		secadorFrame8.setVisible(false);
		
		secadorFrame9 = new SecadorFrame();
		GridBagConstraints gbcInternalFrame9 = new GridBagConstraints();
		secadorFrame9.setBounds(0, 0, 420, 350);
		gbcInternalFrame9.fill = GridBagConstraints.BOTH;
		gbcInternalFrame9.insets = new Insets(5, 1, 5, 5);
		gbcInternalFrame9.gridx = 2;
		gbcInternalFrame9.gridy = 2;
		panel.add(secadorFrame9, gbcInternalFrame9);
		secadorFrame9.setVisible(false);
		
		secadorFrame10 = new SecadorFrame();
		GridBagConstraints gbcInternalFrame10 = new GridBagConstraints();
		secadorFrame10.setBounds(0, 0, 420, 350);
		gbcInternalFrame10.fill = GridBagConstraints.BOTH;
		gbcInternalFrame10.insets = new Insets(5, 1, 5, 5);
		gbcInternalFrame10.gridx = 0;
		gbcInternalFrame10.gridy = 3;
		panel.add(secadorFrame10, gbcInternalFrame10);
		secadorFrame10.setVisible(false);
		
		secadorFrame11 = new SecadorFrame();
		GridBagConstraints gbcInternalFrame11 = new GridBagConstraints();
		secadorFrame11.setBounds(0, 0, 420, 350);
		gbcInternalFrame11.fill = GridBagConstraints.BOTH;
		gbcInternalFrame11.insets = new Insets(5, 1, 5, 5);
		gbcInternalFrame11.gridx = 1;
		gbcInternalFrame11.gridy = 3;
		panel.add(secadorFrame11, gbcInternalFrame11);
		secadorFrame11.setVisible(false);
		
		secadorFrame12 = new SecadorFrame();
		GridBagConstraints gbcInternalFrame12 = new GridBagConstraints();
		secadorFrame12.setBounds(0, 0, 420, 350);
		gbcInternalFrame12.fill = GridBagConstraints.BOTH;
		gbcInternalFrame12.insets = new Insets(5, 1, 5, 5);
		gbcInternalFrame12.gridx = 2;
		gbcInternalFrame12.gridy = 3;
		panel.add(secadorFrame12, gbcInternalFrame12);
		secadorFrame12.setVisible(false);
///////////////////////////////////////////////////////////////////////
		
		secadorFrame13 = new SecadorFrame();
		GridBagConstraints gbcInternalFrame13 = new GridBagConstraints();
		secadorFrame13.setBounds(0, 0, 420, 350);
		gbcInternalFrame13.fill = GridBagConstraints.BOTH;
		gbcInternalFrame13.insets = new Insets(5, 1, 5, 5);
		gbcInternalFrame13.gridx = 0;
		gbcInternalFrame13.gridy = 4;
		panel.add(secadorFrame13, gbcInternalFrame13);
		secadorFrame13.setVisible(false);
		
		secadorFrame14 = new SecadorFrame();
		GridBagConstraints gbcInternalFrame14 = new GridBagConstraints();
		secadorFrame14.setBounds(0, 0, 420, 350);
		gbcInternalFrame14.fill = GridBagConstraints.BOTH;
//BKP!		
//		gbcInternalFrame14.insets = new Insets(1, 1, 5, 5);
		gbcInternalFrame14.insets = new Insets(5, 1, 5, 5);
		gbcInternalFrame14.gridx = 1;
		gbcInternalFrame14.gridy = 4;
		panel.add(secadorFrame14, gbcInternalFrame14);
		secadorFrame14.setVisible(false);
		
		secadorFrame15 = new SecadorFrame();
		GridBagConstraints gbcInternalFrame15 = new GridBagConstraints();
		secadorFrame15.setBounds(0, 0, 420, 350);
		gbcInternalFrame15.fill = GridBagConstraints.BOTH;
		gbcInternalFrame15.insets = new Insets(5, 1, 5, 5);
		gbcInternalFrame15.gridx = 2;
		gbcInternalFrame15.gridy = 4;
		panel.add(secadorFrame15, gbcInternalFrame15);
		secadorFrame15.setVisible(false);
		
		secadorFrame16 = new SecadorFrame();
		GridBagConstraints gbcInternalFrame16 = new GridBagConstraints();
		secadorFrame16.setBounds(0, 0, 420, 350);
		gbcInternalFrame16.fill = GridBagConstraints.BOTH;
		gbcInternalFrame16.insets = new Insets(5, 1, 5, 5);
		gbcInternalFrame16.gridx = 0;
		gbcInternalFrame16.gridy = 5;
		panel.add(secadorFrame16, gbcInternalFrame16);
		secadorFrame16.setVisible(false);
		
		secadorFrame17 = new SecadorFrame();
		GridBagConstraints gbcInternalFrame17 = new GridBagConstraints();
		secadorFrame17.setBounds(0, 0, 420, 350);
		gbcInternalFrame17.fill = GridBagConstraints.BOTH;
		gbcInternalFrame17.insets = new Insets(5, 1, 5, 5);
		gbcInternalFrame17.gridx = 1;
		gbcInternalFrame17.gridy = 5;
		panel.add(secadorFrame17, gbcInternalFrame17);
		secadorFrame17.setVisible(false);
		
		secadorFrame18 = new SecadorFrame();
		GridBagConstraints gbcInternalFrame18 = new GridBagConstraints();
		secadorFrame18.setBounds(0, 0, 420, 350);
		gbcInternalFrame18.fill = GridBagConstraints.BOTH;
		gbcInternalFrame18.insets = new Insets(5, 1, 5, 5);
		gbcInternalFrame18.gridx = 2;
		gbcInternalFrame18.gridy = 5;
		panel.add(secadorFrame18, gbcInternalFrame18);
		secadorFrame18.setVisible(false);
///////////////////////////////////////////////////////////////////////
		
		secadorFrame19 = new SecadorFrame();
		GridBagConstraints gbcInternalFrame19 = new GridBagConstraints();
		secadorFrame19.setBounds(0, 0, 420, 350);
		gbcInternalFrame19.fill = GridBagConstraints.BOTH;
		gbcInternalFrame19.insets = new Insets(5, 1, 5, 5);
		gbcInternalFrame19.gridx = 0;
		gbcInternalFrame19.gridy = 6;
		panel.add(secadorFrame19, gbcInternalFrame19);
		secadorFrame19.setVisible(false);
		
		secadorFrame20 = new SecadorFrame();
		GridBagConstraints gbcInternalFrame20 = new GridBagConstraints();
		secadorFrame20.setBounds(0, 0, 420, 350);
		gbcInternalFrame20.fill = GridBagConstraints.BOTH;
		gbcInternalFrame20.insets = new Insets(5, 1, 5, 5);
		gbcInternalFrame20.gridx = 1;
		gbcInternalFrame20.gridy = 6;
		panel.add(secadorFrame20, gbcInternalFrame20);
		secadorFrame20.setVisible(false);
		
		secadorFrame21 = new SecadorFrame();
		GridBagConstraints gbcInternalFrame21 = new GridBagConstraints();
		secadorFrame21.setBounds(0, 0, 420, 350);
		gbcInternalFrame21.fill = GridBagConstraints.BOTH;
		gbcInternalFrame21.insets = new Insets(5, 1, 5, 5);
		gbcInternalFrame21.gridx = 2;
		gbcInternalFrame21.gridy = 6;
		panel.add(secadorFrame21, gbcInternalFrame21);
		secadorFrame21.setVisible(false);
		
		secadorFrame22 = new SecadorFrame();
		GridBagConstraints gbcInternalFrame22 = new GridBagConstraints();
		secadorFrame22.setBounds(0, 0, 420, 350);
		gbcInternalFrame22.fill = GridBagConstraints.BOTH;
		gbcInternalFrame22.insets = new Insets(5, 1, 5, 5);
		gbcInternalFrame22.gridx = 0;
		gbcInternalFrame22.gridy = 7;
		panel.add(secadorFrame22, gbcInternalFrame22);
		secadorFrame22.setVisible(false);
		
		secadorFrame23 = new SecadorFrame();
		GridBagConstraints gbcInternalFrame23 = new GridBagConstraints();
		secadorFrame23.setBounds(0, 0, 420, 350);
		gbcInternalFrame23.fill = GridBagConstraints.BOTH;
		gbcInternalFrame23.insets = new Insets(5, 1, 5, 5);
		gbcInternalFrame23.gridx = 1;
		gbcInternalFrame23.gridy = 7;
		panel.add(secadorFrame23, gbcInternalFrame23);
		secadorFrame23.setVisible(false);
		
		secadorFrame24 = new SecadorFrame();
		GridBagConstraints gbcInternalFrame24 = new GridBagConstraints();
		secadorFrame24.setBounds(0, 0, 420, 350);
		gbcInternalFrame24.fill = GridBagConstraints.BOTH;
		gbcInternalFrame24.insets = new Insets(5, 1, 5, 5);
		gbcInternalFrame24.gridx = 2;
		gbcInternalFrame24.gridy = 7;
		panel.add(secadorFrame24, gbcInternalFrame24);
		secadorFrame24.setVisible(false);
///////////////////////////////////////////////////////////////////////
		
		secadorFrame25 = new SecadorFrame();
		GridBagConstraints gbcInternalFrame25 = new GridBagConstraints();
		secadorFrame25.setBounds(0, 0, 420, 350);
		gbcInternalFrame25.fill = GridBagConstraints.BOTH;
		gbcInternalFrame25.insets = new Insets(5, 1, 5, 5);
		gbcInternalFrame25.gridx = 0;
		gbcInternalFrame25.gridy = 8;
		panel.add(secadorFrame25, gbcInternalFrame25);
		secadorFrame25.setVisible(false);
		
		secadorFrame26 = new SecadorFrame();
		GridBagConstraints gbcInternalFrame26 = new GridBagConstraints();
		secadorFrame26.setBounds(0, 0, 420, 350);
		gbcInternalFrame26.fill = GridBagConstraints.BOTH;
		gbcInternalFrame26.insets = new Insets(5, 1, 5, 5);
		gbcInternalFrame26.gridx = 1;
		gbcInternalFrame26.gridy = 8;
		panel.add(secadorFrame26, gbcInternalFrame26);
		secadorFrame26.setVisible(false);
		
		secadorFrame27 = new SecadorFrame();
		GridBagConstraints gbcInternalFrame27 = new GridBagConstraints();
		secadorFrame27.setBounds(0, 0, 420, 350);
		gbcInternalFrame27.fill = GridBagConstraints.BOTH;
		gbcInternalFrame27.insets = new Insets(5, 1, 5, 5);
		gbcInternalFrame27.gridx = 2;
		gbcInternalFrame27.gridy = 8;
		panel.add(secadorFrame27, gbcInternalFrame27);
		secadorFrame27.setVisible(false);
		
		secadorFrame28 = new SecadorFrame();
		GridBagConstraints gbcInternalFrame28 = new GridBagConstraints();
		secadorFrame28.setBounds(0, 0, 420, 350);
		gbcInternalFrame28.fill = GridBagConstraints.BOTH;
		gbcInternalFrame28.insets = new Insets(5, 1, 5, 5);
		gbcInternalFrame28.gridx = 0;
		gbcInternalFrame28.gridy = 9;
		panel.add(secadorFrame28, gbcInternalFrame28);
		secadorFrame28.setVisible(false);
		
		secadorFrame29 = new SecadorFrame();
		GridBagConstraints gbcInternalFrame29 = new GridBagConstraints();
		secadorFrame29.setBounds(0, 0, 420, 350);
		gbcInternalFrame29.fill = GridBagConstraints.BOTH;
		gbcInternalFrame29.insets = new Insets(5, 1, 5, 5);
		gbcInternalFrame29.gridx = 1;
		gbcInternalFrame29.gridy = 9;
		panel.add(secadorFrame29, gbcInternalFrame29);
		secadorFrame29.setVisible(false);
		
		secadorFrame30 = new SecadorFrame();
		GridBagConstraints gbcInternalFrame30 = new GridBagConstraints();
		secadorFrame30.setBounds(0, 0, 420, 350);
		gbcInternalFrame30.fill = GridBagConstraints.BOTH;
		gbcInternalFrame30.insets = new Insets(5, 1, 5, 5);
		gbcInternalFrame30.gridx = 2;
		gbcInternalFrame30.gridy = 9;
		panel.add(secadorFrame30, gbcInternalFrame30);
		secadorFrame30.setVisible(false);
///////////////////////////////////////////////////////////////////////
		
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnUsuarios = new JMenu("Usuários");
		mnUsuarios.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				mainListener.menuUsuariosClicked();
				
			}
			
		});
		
		menuBar.add(mnUsuarios);
		
		mnReceitas = new JMenu("Receitas");
		mnReceitas.addMouseListener(new MouseAdapter() {
					
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				mainListener.menuReceitasClicked();
				
			}
			
		});
		
		menuBar.add(mnReceitas);
		
		mnRelatorios = new JMenu("Relatórios");
		mnRelatorios.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				mainListener.menuRelatoriosClicked();
				
			}
			
		});
		
		menuBar.add(mnRelatorios);
		
		/*
		mnComunicacao = new JMenu("Comunicação");
		mnComunicacao.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				mainListener.menuComunicacaoClicked();
			}
		});
		menuBar.add(mnComunicacao);
		*/
		
		mnSobre = new JMenu("Sobre");
		mnSobre.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				mainListener.menuSobreClicked();
				
			}
			
		});
		
		menuBar.add(mnSobre);
		
//////////////////////////////////////////////////////////
		
	}
	
	
///////////////////////////////////////////////////////
///////////////////////////////////////////////////////
///////////////////////////////////////////////////////
	//GETTERS
	//TODO - GETTERS
	
	public SecadorFrame getSecadorFrame1Object() {
		
		return secadorFrame1;
		
	}
	
	public SecadorFrame getSecadorFrame2Object() {
		
		return secadorFrame2;
		
	}
	
	public SecadorFrame getSecadorFrame3Object() {
		
		return secadorFrame3;
		
	}
	
	public SecadorFrame getSecadorFrame4Object() {
		
		return secadorFrame4;
		
	}
	
	public SecadorFrame getSecadorFrame5Object() {
		
		return secadorFrame5;
		
	}
	
	public SecadorFrame getSecadorFrame6Object() {
		
		return secadorFrame6;
		
	}
	
	public SecadorFrame getSecadorFrame7Object() {
		
		return secadorFrame7;
		
	}
	
	public SecadorFrame getSecadorFrame8Object() {
		
		return secadorFrame8;
		
	}
	
	public SecadorFrame getSecadorFrame9Object() {
		
		return secadorFrame9;
		
	}

	public SecadorFrame getSecadorFrame10Object() {
		
		return secadorFrame10;
		
	}
	
	public SecadorFrame getSecadorFrame11Object() {
		
		return (SecadorFrame) secadorFrame11;
		
	}

	public SecadorFrame getSecadorFrame12Object() {
	
		return secadorFrame12;
		
	}

	public SecadorFrame getSecadorFrame13Object() {
	
		return secadorFrame13;
	
	}

	public SecadorFrame getSecadorFrame14Object() {
	
		return secadorFrame14;
	
	}
	
	public SecadorFrame getSecadorFrame15Object() {
		
		return secadorFrame15;
		
	}

	public SecadorFrame getSecadorFrame16Object() {
		
		return secadorFrame16;
		
	}
	
	public SecadorFrame getSecadorFrame17Object() {
		
		return secadorFrame17;
		
	}
	
	public SecadorFrame getSecadorFrame18Object() {
		
		return secadorFrame18;
		
	}
	
	public SecadorFrame getSecadorFrame19Object() {
		
		return secadorFrame19;
		
	}
	
	public SecadorFrame getSecadorFrame20Object() {
		
		return secadorFrame20;
		
	}
	
	public SecadorFrame getSecadorFrame21Object() {
		
		return secadorFrame21;
		
	}
	
	public SecadorFrame getSecadorFrame22Object() {
		
		return secadorFrame22;
		
	}
	
	public SecadorFrame getSecadorFrame23Object() {
		
		return secadorFrame23;
		
	}
	
	public SecadorFrame getSecadorFrame24Object() {
		
		return secadorFrame24;
		
	}
	
	public SecadorFrame getSecadorFrame25Object() {
		
		return secadorFrame25;
		
	}
	
	public SecadorFrame getSecadorFrame26Object() {
		
		return secadorFrame26;
		
	}
	
	public SecadorFrame getSecadorFrame27Object() {
		
		return secadorFrame27;
		
	}
	
	public SecadorFrame getSecadorFrame28Object() {
		
		return secadorFrame28;
		
	}
	
	public SecadorFrame getSecadorFrame29Object() {
		
		return secadorFrame29;
		
	}
	
	public SecadorFrame getSecadorFrame30Object() {
		
		return secadorFrame30;
		
	}
	
///////////////////////////////////////////////////////
///////////////////////////////////////////////////////
///////////////////////////////////////////////////////	
	//SETTERS
	//TODO - SETTERS

	
	public void setInternalFrame1Visible(boolean visible) {
		
		secadorFrame1.setVisible(visible);
		
	}
	
	public void setInternalFrame2Visible(boolean visible) {
		
		secadorFrame2.setVisible(visible);
		
	}
	
	public void setInternalFrame3Visible(boolean visible) {
		
		secadorFrame3.setVisible(visible);
		
	}
	
	public void setInternalFrame4Visible(boolean visible) {
		
		secadorFrame4.setVisible(visible);
		
	}
	
	public void setInternalFrame5Visible(boolean visible) {
		
		secadorFrame5.setVisible(visible);
		
	}
	
	public void setInternalFrame6Visible(boolean visible) {
		
		secadorFrame6.setVisible(visible);
		
	}
	
	public void setInternalFrame7Visible(boolean visible) {
		
		secadorFrame7.setVisible(visible);
		
	}
	
	public void setInternalFrame8Visible(boolean visible) {
		
		secadorFrame8.setVisible(visible);
		
	}
	
	public void setInternalFrame9Visible(boolean visible) {
		
		secadorFrame9.setVisible(visible);
		
	}
	
	public void setInternalFrame10Visible(boolean visible) {
		
		secadorFrame10.setVisible(visible);
		
	}
	
	public void setInternalFrame11Visible(boolean visible) {
		
		secadorFrame11.setVisible(visible);
		
	}
	
	public void setInternalFrame12Visible(boolean visible) {
		
		secadorFrame12.setVisible(visible);
		
	}
	
	public void setInternalFrame13Visible(boolean visible) {
		
		secadorFrame13.setVisible(visible);
		
	}
	
	public void setInternalFrame14Visible(boolean visible) {
		
		secadorFrame14.setVisible(visible);
		
	}
	
	public void setInternalFrame15Visible(boolean visible) {
		
		secadorFrame15.setVisible(visible);
		
	}
	
	public void setInternalFrame16Visible(boolean visible) {
		
		secadorFrame16.setVisible(visible);
		
	}
	
	public void setInternalFrame17Visible(boolean visible) {
		
		secadorFrame17.setVisible(visible);
		
	}
	
	public void setInternalFrame18Visible(boolean visible) {
		
		secadorFrame18.setVisible(visible);
		
	}
	
	public void setInternalFrame19Visible(boolean visible) {
		
		secadorFrame19.setVisible(visible);
		
	}
	
	public void setInternalFrame20Visible(boolean visible) {
		
		secadorFrame20.setVisible(visible);
		
	}
	
	public void setInternalFrame21Visible(boolean visible) {
		
		secadorFrame21.setVisible(visible);
		
	}
	
	public void setInternalFrame22Visible(boolean visible) {
		
		secadorFrame22.setVisible(visible);
		
	}
	
	public void setInternalFrame23Visible(boolean visible) {
		
		secadorFrame23.setVisible(visible);
		
	}
	
	public void setInternalFrame24Visible(boolean visible) {
		
		secadorFrame24.setVisible(visible);
		
	}
	
	public void setInternalFrame25Visible(boolean visible) {
		
		secadorFrame25.setVisible(visible);
		
	}
	
	public void setInternalFrame26Visible(boolean visible) {
		
		secadorFrame26.setVisible(visible);
		
	}
	
	public void setInternalFrame27Visible(boolean visible) {
		
		secadorFrame27.setVisible(visible);
		
	}
	
	public void setInternalFrame28Visible(boolean visible) {
		
		secadorFrame28.setVisible(visible);
		
	}
	
	public void setInternalFrame29Visible(boolean visible) {
		
		secadorFrame29.setVisible(visible);
		
	}
	
	public void setInternalFrame30Visible(boolean visible) {
		
		secadorFrame30.setVisible(visible);
		
	}
	
	public void setInternalFrame1Title(int numeroSecador) {
		
		this.secadorFrame1.setTitle("Secador - " + Integer.toString(numeroSecador));
		
	}
	
	public void setInternalFrame2Title(int numeroSecador) {
		
		this.secadorFrame2.setTitle("Secador - " + Integer.toString(numeroSecador));
		
	}
	
	public void setInternalFrame3Title(int numeroSecador) {
		
		this.secadorFrame3.setTitle("Secador - " + Integer.toString(numeroSecador));
		
	}
	
	public void setInternalFrame4Title(int numeroSecador) {
		
		this.secadorFrame4.setTitle("Secador - " + Integer.toString(numeroSecador));
		
	}
	
	public void setInternalFrame5Title(int numeroSecador) {
		
		this.secadorFrame5.setTitle("Secador - " + Integer.toString(numeroSecador));
		
	}
	
	public void setInternalFrame6Title(int numeroSecador) {
		
		this.secadorFrame6.setTitle("Secador - " + Integer.toString(numeroSecador));
		
	}
	
	public void setInternalFrame7Title(int numeroSecador) {
		
		this.secadorFrame7.setTitle("Secador - " + Integer.toString(numeroSecador));
		
	}
	
	public void setInternalFrame8Title(int numeroSecador) {
		
		this.secadorFrame8.setTitle("Secador - " + Integer.toString(numeroSecador));
		
	}
	
	public void setInternalFrame9Title(int numeroSecador) {
		
		this.secadorFrame9.setTitle("Secador - " + Integer.toString(numeroSecador));
		
	}
	
	public void setInternalFrame10Title(int numeroSecador) {
		
		this.secadorFrame10.setTitle("Secador - " + Integer.toString(numeroSecador));
		
	}
	
	public void setInternalFrame11Title(int numeroSecador) {
		
		this.secadorFrame11.setTitle("Secador - " + Integer.toString(numeroSecador));
		
	}
	
	public void setInternalFrame12Title(int numeroSecador) {
		
		this.secadorFrame12.setTitle("Secador - " + Integer.toString(numeroSecador));
		
	}
	
	public void setInternalFrame13Title(int numeroSecador) {
		
		this.secadorFrame13.setTitle("Secador - " + Integer.toString(numeroSecador));
		
	}
	
	public void setInternalFrame14Title(int numeroSecador) {
		
		this.secadorFrame14.setTitle("Secador - " + Integer.toString(numeroSecador));
		
	}
	
	public void setInternalFrame15Title(int numeroSecador) {
		
		this.secadorFrame15.setTitle("Secador - " + Integer.toString(numeroSecador));
		
	}
	
	public void setInternalFrame16Title(int numeroSecador) {
		
		this.secadorFrame16.setTitle("Secador - " + Integer.toString(numeroSecador));
		
	}
	
	public void setInternalFrame17Title(int numeroSecador) {
		
		this.secadorFrame17.setTitle("Secador - " + Integer.toString(numeroSecador));
		
	}
	
	public void setInternalFrame18Title(int numeroSecador) {
		
		this.secadorFrame18.setTitle("Secador - " + Integer.toString(numeroSecador));
		
	}
	
	public void setInternalFrame19Title(int numeroSecador) {
		
		this.secadorFrame19.setTitle("Secador - " + Integer.toString(numeroSecador));
		
	}
	
	public void setInternalFrame20Title(int numeroSecador) {
		
		this.secadorFrame20.setTitle("Secador - " + Integer.toString(numeroSecador));
		
	}
	
	public void setInternalFrame21Title(int numeroSecador) {
		
		this.secadorFrame21.setTitle("Secador - " + Integer.toString(numeroSecador));
		
	}
	
	public void setInternalFrame22Title(int numeroSecador) {
		
		this.secadorFrame22.setTitle("Secador - " + Integer.toString(numeroSecador));
		
	}
	
	public void setInternalFrame23Title(int numeroSecador) {
		
		this.secadorFrame23.setTitle("Secador - " + Integer.toString(numeroSecador));
		
	}
	
	public void setInternalFrame24Title(int numeroSecador) {
		
		this.secadorFrame24.setTitle("Secador - " + Integer.toString(numeroSecador));
		
	}
	
	public void setInternalFrame25Title(int numeroSecador) {
		
		this.secadorFrame25.setTitle("Secador - " + Integer.toString(numeroSecador));
		
	}
	
	public void setInternalFrame26Title(int numeroSecador) {
		
		this.secadorFrame26.setTitle("Secador - " + Integer.toString(numeroSecador));
		
	}
	
	public void setInternalFrame27Title(int numeroSecador) {
		
		this.secadorFrame27.setTitle("Secador - " + Integer.toString(numeroSecador));
		
	}
	
	public void setInternalFrame28Title(int numeroSecador) {
		
		this.secadorFrame28.setTitle("Secador - " + Integer.toString(numeroSecador));
		
	}
	
	public void setInternalFrame29Title(int numeroSecador) {
		
		this.secadorFrame29.setTitle("Secador - " + Integer.toString(numeroSecador));
		
	}
	
	public void setInternalFrame30Title(int numeroSecador) {
		
		this.secadorFrame30.setTitle("Secador - " + Integer.toString(numeroSecador));
		
	}
	
	public void setMnReceitasEnabled(boolean enabled) {
		
		mnReceitas.setEnabled(enabled);
		
	}
	
///////////////////////////////////////////////////////
///////////////////////////////////////////////////////
///////////////////////////////////////////////////////
	
	//FAZ A LINKAGEM ENTRE O RUNNER E A VIEW [MAINFRAME] POR INTERMÉDIO DO LISTENER [MAINLISTENER]
	//TODO - LISTENER LINKER
	public void addMainListener(MainListener mainListener) {
		this.mainListener = mainListener;
	}
}
