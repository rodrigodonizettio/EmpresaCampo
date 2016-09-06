package com.campotech.sgsoft.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Dialog.ModalExclusionType;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class GerenciamentoRedeFrame extends JFrame {

	
	//ATRIBUTOS PERTENCENTES AO FRAME
	private JPanel contentPane;
	private JLabel lblBorderRedeSetup;
	private JLabel lblBorderRedeConnect;
	private JLabel lblPortaCOM;
	private JLabel lblPortaCOMValor;
	private JLabel lblSoftwareReset;
	private JLabel lblSoftwareResetLED;
	private JLabel lblPasswordF;
	private JLabel lblPasswordFLED;
	private JLabel lblPasswordE;
	private JLabel lblPasswordELED;
	private JLabel lblBaudRate;
	private JLabel lblBaudRateLED;
	private JLabel lblCanal;
	private JLabel lblCanalLED;
	private JLabel lblPreferredId;
	private JLabel lblPreferredIdLED;
	private JLabel lblPinosSaida;
	private JLabel lblPinosSaidaLED;
	private JLabel lblLEDs;
	private JLabel lblLEDsLED;
	private JLabel lblIntervalo;
	private JLabel lblIntervaloLED;
	private JLabel lblPisca;
	private JLabel lblPiscaLED;
	private JLabel lblDisableSR;
	private JLabel lblDisableSRLED;
	private JLabel lblFindNetwork;
	private JLabel lblFindNetworkLED;
	private JLabel lblJoinNetwork;
	private JLabel lblJoinNetworkLED;
	
	

	//ATRIBUTOS DE IMAGENS 
	URL urlIconCampotech = GerenciamentoRelatoriosFrame.class.getResource("images/icon-campotech.png");
	
	
	public GerenciamentoRedeFrame() {
		
		setTitle("SGSoft - Tela Gerenciamento de Rede");
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 380, 345);
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
		contentPane.setLayout(null);
		
		
		lblBorderRedeSetup = new JLabel();
		lblBorderRedeSetup.setBounds(5, 5, 363, 230);
		lblBorderRedeSetup.setBorder(new TitledBorder(null, "Inicialização do Terminal de Usuário", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		contentPane.add(lblBorderRedeSetup);
		
		
			lblPortaCOM = new JLabel();
			lblPortaCOM.setText("Terminal de Usuário localizado na Porta:");
			lblPortaCOM.setBounds(40, 20, 230, 30);
			lblBorderRedeSetup.add(lblPortaCOM);
			
			lblPortaCOMValor = new JLabel();
			lblPortaCOMValor.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblPortaCOMValor.setBounds(270, 20, 60, 30);
			lblBorderRedeSetup.add(lblPortaCOMValor);
			
			
			//LEFT SIDE
			lblSoftwareReset = new JLabel();
			lblSoftwareReset.setText("Software Reset");
			lblSoftwareReset.setBounds(40, 42, 90, 30);
			lblBorderRedeSetup.add(lblSoftwareReset);
			
			lblSoftwareResetLED = new JLabel();
			lblSoftwareResetLED.setForeground(Color.RED);
			lblSoftwareResetLED.setFont(new Font("Tahoma", Font.BOLD, 35));
			lblSoftwareResetLED.setText("•");
			//lblSoftwareResetLED.setEnabled(false);
			lblSoftwareResetLED.setBounds(10, 40, 30, 30);
			lblBorderRedeSetup.add(lblSoftwareResetLED);
			
			lblPasswordF = new JLabel();
			lblPasswordF.setText("Password-F");
			lblPasswordF.setBounds(40, 72, 70, 30);
			lblBorderRedeSetup.add(lblPasswordF);
			
			lblPasswordFLED = new JLabel();
			lblPasswordFLED.setForeground(Color.RED);
			lblPasswordFLED.setFont(new Font("Tahoma", Font.BOLD, 35));
			lblPasswordFLED.setText("•");
			//lblPasswordFLED.setEnabled(false);
			lblPasswordFLED.setBounds(10, 70, 30, 30);
			lblBorderRedeSetup.add(lblPasswordFLED);
		
			lblPasswordE = new JLabel();
			lblPasswordE.setText("Password-E");
			lblPasswordE.setBounds(40, 102, 70, 30);
			lblBorderRedeSetup.add(lblPasswordE);
			
			lblPasswordELED = new JLabel();
			lblPasswordELED.setForeground(Color.RED);
			lblPasswordELED.setFont(new Font("Tahoma", Font.BOLD, 35));
			lblPasswordELED.setText("•");
			//lblPasswordELED.setEnabled(false);
			lblPasswordELED.setBounds(10, 100, 30, 30);
			lblBorderRedeSetup.add(lblPasswordELED);
			
			lblBaudRate = new JLabel();
			lblBaudRate.setText("Baud Rate");
			lblBaudRate.setBounds(40, 132, 70, 30);
			lblBorderRedeSetup.add(lblBaudRate);
			
			lblBaudRateLED = new JLabel();
			lblBaudRateLED.setForeground(Color.RED);
			lblBaudRateLED.setFont(new Font("Tahoma", Font.BOLD, 35));
			lblBaudRateLED.setText("•");
			//lblBaudRateLED.setEnabled(false);
			lblBaudRateLED.setBounds(10, 130, 30, 30);
			lblBorderRedeSetup.add(lblBaudRateLED);
			
			lblCanal = new JLabel();
			lblCanal.setText("Canal");
			lblCanal.setBounds(40, 162, 70, 30);
			lblBorderRedeSetup.add(lblCanal);
			
			lblCanalLED = new JLabel();
			lblCanalLED.setForeground(Color.RED);
			lblCanalLED.setFont(new Font("Tahoma", Font.BOLD, 35));
			lblCanalLED.setText("•");
			//lblCanalLED.setEnabled(false);
			lblCanalLED.setBounds(10, 160, 30, 30);
			lblBorderRedeSetup.add(lblCanalLED);
			
			lblPreferredId = new JLabel();
			lblPreferredId.setText("Preferred-ID");
			lblPreferredId.setBounds(40, 192, 70, 30);
			lblBorderRedeSetup.add(lblPreferredId);
			
			lblPreferredIdLED = new JLabel();
			lblPreferredIdLED.setForeground(Color.RED);
			lblPreferredIdLED.setFont(new Font("Tahoma", Font.BOLD, 35));
			lblPreferredIdLED.setText("•");
			//lblPreferredIdLED.setEnabled(false);
			lblPreferredIdLED.setBounds(10, 190, 30, 30);
			lblBorderRedeSetup.add(lblPreferredIdLED);
			
			
			//RIGHT SIDE
			lblPinosSaida = new JLabel();
			lblPinosSaida.setText("Saídas");
			lblPinosSaida.setBounds(230, 42, 70, 30);
			lblBorderRedeSetup.add(lblPinosSaida);
			
			lblPinosSaidaLED = new JLabel();
			lblPinosSaidaLED.setForeground(Color.RED);
			lblPinosSaidaLED.setFont(new Font("Tahoma", Font.BOLD, 35));
			lblPinosSaidaLED.setText("•");
			//lblPinosSaidaLED.setEnabled(false);
			lblPinosSaidaLED.setBounds(200, 40, 30, 30);
			lblBorderRedeSetup.add(lblPinosSaidaLED);
			
			lblLEDs = new JLabel();
			lblLEDs.setText("LEDs");
			lblLEDs.setBounds(230, 72, 70, 30);
			lblBorderRedeSetup.add(lblLEDs);
			
			lblLEDsLED = new JLabel();
			lblLEDsLED.setForeground(Color.RED);
			lblLEDsLED.setFont(new Font("Tahoma", Font.BOLD, 35));
			lblLEDsLED.setText("•");
			//lblLEDsLED.setEnabled(false);
			lblLEDsLED.setBounds(200, 70, 30, 30);
			lblBorderRedeSetup.add(lblLEDsLED);
			
			lblIntervalo = new JLabel();
			lblIntervalo.setText("Intervalo de Tempo");
			lblIntervalo.setBounds(230, 102, 120, 30);
			lblBorderRedeSetup.add(lblIntervalo);
			
			lblIntervaloLED = new JLabel();
			lblIntervaloLED.setForeground(Color.RED);
			lblIntervaloLED.setFont(new Font("Tahoma", Font.BOLD, 35));
			lblIntervaloLED.setText("•");
			//lblIntervaloLED.setEnabled(false);
			lblIntervaloLED.setBounds(200, 100, 30, 30);
			lblBorderRedeSetup.add(lblIntervaloLED);
			
			lblPisca = new JLabel();
			lblPisca.setText("Blink");
			lblPisca.setBounds(230, 132, 120, 30);
			lblBorderRedeSetup.add(lblPisca);
			
			lblPiscaLED = new JLabel();
			lblPiscaLED.setForeground(Color.RED);
			lblPiscaLED.setFont(new Font("Tahoma", Font.BOLD, 35));
			lblPiscaLED.setText("•");
			//lblPiscaLED.setEnabled(false);
			lblPiscaLED.setBounds(200, 130, 30, 30);
			lblBorderRedeSetup.add(lblPiscaLED);
			
			lblDisableSR = new JLabel();
			lblDisableSR.setText("Disable SR");
			lblDisableSR.setBounds(230, 162, 120, 30);
			lblBorderRedeSetup.add(lblDisableSR);
			
			lblDisableSRLED = new JLabel();
			lblDisableSRLED.setForeground(Color.RED);
			lblDisableSRLED.setFont(new Font("Tahoma", Font.BOLD, 35));
			lblDisableSRLED.setText("•");
			//lblDisableSRLED.setEnabled(false);
			lblDisableSRLED.setBounds(200, 160, 30, 30);
			lblBorderRedeSetup.add(lblDisableSRLED);
			
			
		lblBorderRedeConnect = new JLabel();
		lblBorderRedeConnect.setBounds(5, 240, 363, 70);
		lblBorderRedeConnect.setBorder(new TitledBorder(null, "Conexão com Roteador", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		contentPane.add(lblBorderRedeConnect);
			
			lblFindNetwork = new JLabel();
			lblFindNetwork.setText("Canal & Preferred-ID");
			lblFindNetwork.setBounds(40, 22, 120, 30);
			lblBorderRedeConnect.add(lblFindNetwork);
			
			lblFindNetworkLED = new JLabel();
			lblFindNetworkLED.setForeground(Color.RED);
			lblFindNetworkLED.setFont(new Font("Tahoma", Font.BOLD, 35));
			lblFindNetworkLED.setText("•");
			//lblFindNetworkLED.setEnabled(false);
			lblFindNetworkLED.setBounds(10, 20, 30, 30);
			lblBorderRedeConnect.add(lblFindNetworkLED);
			
			lblJoinNetwork = new JLabel();
			lblJoinNetwork.setText("Join Network");
			lblJoinNetwork.setBounds(230, 22, 70, 30);
			lblBorderRedeConnect.add(lblJoinNetwork);
			
			lblJoinNetworkLED = new JLabel();
			lblJoinNetworkLED.setForeground(Color.RED);
			lblJoinNetworkLED.setFont(new Font("Tahoma", Font.BOLD, 35));
			lblJoinNetworkLED.setText("•");
			//lblJoinNetworkLED.setEnabled(false);
			lblJoinNetworkLED.setBounds(200, 20, 30, 30);
			lblBorderRedeConnect.add(lblJoinNetworkLED);
			
	}
	
	
	//GETTERS
	//TODO - GETTERS
	public String getLblPortaCOMValorText() {
		
		return lblPortaCOMValor.getText();
		
	}
	
	
	
	//SETTERS
	//TODO - SETTERS
	public void setLblPortaCOMValorText(String portNumber) {
		
		lblPortaCOMValor.setText(portNumber);
		
	}
	
	/*
	public void setLblSoftwareResetLEDEnable(boolean enabled) {
		
		lblSoftwareResetLED.setEnabled(enabled);
		
	}
	
	public void setLblPasswordFLEDEnable(boolean enabled) {
		
		lblPasswordFLED.setEnabled(enabled);
		
	}
	
	public void setLblPasswordELEDEnable(boolean enabled) {
		
		lblPasswordELED.setEnabled(enabled);
		
	}

	public void setLblBaudRateLEDEnable(boolean enabled) {
		
		lblBaudRateLED.setEnabled(enabled);
		
	}
	
	public void setLblCanalLEDEnable(boolean enabled) {
		
		lblCanalLED.setEnabled(enabled);
		
	}
	
	public void setLblPreferredIdLEDEnable(boolean enabled) {
		
		lblPreferredIdLED.setEnabled(enabled);
		
	}

	public void setLblPinosSaidaLEDEnable(boolean enabled) {
		
		lblPinosSaidaLED.setEnabled(enabled);
		
	}

	public void setLblLEDsLEDEnable(boolean enabled) {
		
		lblLEDsLED.setEnabled(enabled);
		
	}
	
	public void setLblIntervaloLEDEnable(boolean enabled) {
		
		lblIntervaloLED.setEnabled(enabled);
		
	}
	
	public void setLblPiscaLEDEnable(boolean enabled) {
		
		lblPiscaLED.setEnabled(enabled);
		
	}
	
	public void setLblDisableSRLEDEnable(boolean enabled) {
		
		lblDisableSRLED.setEnabled(enabled);
		
	}
	
	public void setLblFindNetworkLEDEnable(boolean enabled) {
		
		lblFindNetworkLED.setEnabled(enabled);
		
	}
	*/
	
	public void setLblSoftwareResetLEDForegroundColor(Color color) {
		
		lblSoftwareResetLED.setForeground(color);
		
	}
	
	public void setLblPasswordFLEDForegroundColor(Color color) {
		
		lblPasswordFLED.setForeground(color);
		
	}
	
	public void setLblPasswordELEDForegroundColor(Color color) {
		
		lblPasswordELED.setForeground(color);
		
	}
	
	public void setLblBaudRateLEDForegroundColor(Color color) {
		
		lblBaudRateLED.setForeground(color);
		
	}
	
	public void setLblCanalLEDForegroundColor(Color color) {
		
		lblCanalLED.setForeground(color);
		
	}
	
	public void setLblPreferredIdLEDForegroundColor(Color color) {
		
		lblPreferredIdLED.setForeground(color);
		
	}

	public void setLblPinosSaidaLEDForegroundColor(Color color) {
		
		lblPinosSaidaLED.setForeground(color);
		
	}

	public void setLblLEDsLEDForegroundColor(Color color) {
		
		lblLEDsLED.setForeground(color);
		
	}
	
	public void setLblIntervaloLEDForegroundColor(Color color) {
		
		lblIntervaloLED.setForeground(color);
		
	}
	
	public void setLblPiscaLEDForegroundColor(Color color) {
		
		lblPiscaLED.setForeground(color);
		
	}
	
	public void setLblDisableSRLEDForegroundColor(Color color) {
		
		lblDisableSRLED.setForeground(color);
		
	}

	public void setLblFindNetworkLEDForegroundColor(Color color) {
		
		lblFindNetworkLED.setForeground(color);
		
	}
	
	public void setLblJoinNetworkLEDForegroundColor(Color color) {
		
		lblJoinNetworkLED.setForeground(color);
		
	}
	
}
