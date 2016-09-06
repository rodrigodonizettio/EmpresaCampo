package com.campotech.sgsoft.view;

import java.awt.Checkbox;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.text.DefaultFormatter;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;
import javax.swing.ImageIcon;

import com.campotech.sgsoft.controller.listener.GerenciamentoAjusteListener;
import com.campotech.sgsoft.controller.listener.MainListener;
import com.campotech.sgsoft.controller.listener.SecadorFrameListener;
import com.campotech.sgsoft.model.Constants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class SecadorFrame extends JInternalFrame {

	//ATRIBUTOS PERTENCENTES AO FRAME
	private JLabel lblTemperaturaEntrada;
	private JLabel lblTemperaturaMassa;
	private JLabel lblLote;
	private JLabel lblLoteNome;
	private JLabel lblLED;
	private JLabel lblEtapa1;
	private JLabel lblEtapa2;
	private JLabel lblEtapa3;
	private JLabel lblTempoRestanteTitulo;
	private JLabel lblTempoRestante;
	
	
	private JLabel lblTempoExtraTitulo;
	private JSpinner spnTempoExtra;
	private JLabel lblTempoExtraMinuto;
	private JLabel lblTempoExtraHora;
	private JButton btnAtualizarTempoExtra;
	
	private JLabel lblReceitaPane;
	private JLabel lblReceitaTitulo;
	private JLabel lblReceita;
	private JLabel lblDataEntradaTitulo;
	private JLabel lblDataEntrada;
	private JLabel lblHoraEntradaTitulo;
	private JLabel lblHoraEntrada;
	private JLabel lblControlePane;
	private JRadioButton rdbtnEntrada;
	private JRadioButton rdbtnMassa;
	private ButtonGroup rdgrpBtnControle;
	private JLabel lblTemperatura;
	private JSpinner spnTemperatura;
	private JButton btnAtualizarControle;
	private JLabel lblProtecaoPane;
	private JRadioButton rdbtnSensorDeMovimento;
	private JButton btnAtualizarProtecao;
	private JButton btnSecagem;
	private JButton btnAjuste;	
	private JLabel lblProtecaoIcon;
	
	
	//ATRIBUTOS PERTENCES AO LISTENER
	private SecadorFrameListener secadorFrameListener;
	private MainListener mainListener;
	
	
	//ATRIBUTOS DE IMAGENS 
	URL urlIconAjuste = GerenciamentoSecagemFrame.class.getResource("images/icon-configuracao-36px.png");
	URL urlIconProtecao = GerenciamentoSecagemFrame.class.getResource("images/icon-warning-red-triangle-36px.png");
	
	//ATRIBUTO PERTENCENTE AO RADIO-BUTTON SENSOR DE MOVIMENTO
	private boolean rdbtnSensorDeMovimentoOldState = false;
	
	
	//CONSTRUTOR
	public SecadorFrame() {
		
		getContentPane().setBackground(Color.WHITE);
		
		//GETCONTENT - PANE
		setBounds(100, 100, 455, 400);
		getContentPane().setLayout(null);
		
		
		//TEMPERATURA ENTRADA - PANE
		lblTemperaturaEntrada = new JLabel();
		lblTemperaturaEntrada.setBounds(39, 5, 190, 60);
		lblTemperaturaEntrada.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblTemperaturaEntrada.setHorizontalAlignment(SwingConstants.CENTER);
		//lblTemperaturaEntrada.setForeground(Color.RED);
		//lblTemperaturaEntrada.setText("47 [°C]");
		lblTemperaturaEntrada.setBorder(new TitledBorder(null, "Temperatura - Entrada", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		getContentPane().add(lblTemperaturaEntrada);
		
		
		//TEMPERATURA MASSA - PANE
		lblTemperaturaMassa = new JLabel();
		lblTemperaturaMassa.setBounds(227, 5, 205, 60);
		lblTemperaturaMassa.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblTemperaturaMassa.setHorizontalAlignment(SwingConstants.CENTER);
		//lblTemperaturaMassa.setForeground(Color.GREEN);
		//lblTemperaturaMassa.setText("42 [°C]");
		lblTemperaturaMassa.setBorder(new TitledBorder(null, "Temperatura - Massa", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		getContentPane().add(lblTemperaturaMassa);
		
		
		//LOTE - PANE
		lblLote = new JLabel();
		lblLote.setBounds(227, 67, 205, 140);
		lblLote.setBorder(new TitledBorder(null, "Lote no Secador", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		getContentPane().add(lblLote);
			
			lblLoteNome = new JLabel();
			lblLoteNome.setHorizontalAlignment(SwingConstants.CENTER);
			lblLoteNome.setBounds(10, 20, 165, 20);
			lblLoteNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
			//lblLoteNumero.setText("Lote-Nenhum (ESCOLHER)");
			lblLote.add(lblLoteNome);
			
			lblLED = new JLabel();
			lblLED.setBounds(175, 13, 25, 30);
			lblLED.setFont(new Font("Tahoma", Font.BOLD, 35));
			lblLED.setForeground(Color.GREEN);
			lblLED.setText("•");
			lblLote.add(lblLED);
			
			lblEtapa1 = new JLabel();
			lblEtapa1.setBounds(10, 45, 50, 20);
			lblEtapa1.setFont(new Font("Tahoma", Font.ITALIC, 14));
			lblEtapa1.setText("Etapa-1");
			lblLote.add(lblEtapa1);
			
			lblEtapa2 = new JLabel();
			lblEtapa2.setBounds(77, 45, 50, 20);
			lblEtapa2.setFont(new Font("Tahoma", Font.ITALIC, 14));
			//lblEtapa2.setForeground(Color.GREEN);
			lblEtapa2.setText("Etapa-2");
			lblLote.add(lblEtapa2);
			
			lblEtapa3 = new JLabel();
			lblEtapa3.setBounds(145, 45, 50, 20);
			lblEtapa3.setFont(new Font("Tahoma", Font.ITALIC, 14));
			//lblEtapa3.setForeground(Color.GRAY);
			lblEtapa3.setText("Etapa-3");
			lblLote.add(lblEtapa3);
			
			lblTempoRestanteTitulo = new JLabel();
			lblTempoRestanteTitulo.setBounds(30, 65, 110, 20);
			lblTempoRestanteTitulo.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblTempoRestanteTitulo.setText("Tempo Restante:");
			lblLote.add(lblTempoRestanteTitulo);
			
				lblTempoRestante = new JLabel();
				lblTempoRestante.setBounds(137, 65, 60, 20);
				lblTempoRestante.setText("HH:MM");
				lblLote.add(lblTempoRestante);
			
			lblTempoExtraTitulo = new JLabel();
			lblTempoExtraTitulo.setBounds(10, 95, 60, 20);
			lblTempoExtraTitulo.setText("Adicionar:");
			lblLote.add(lblTempoExtraTitulo);	
					
				spnTempoExtra = new JSpinner();
				spnTempoExtra.setBounds(65, 92, 55, 25);
				//Configurando Spinner para Aceitar Somente Números
				((DefaultFormatter)((JSpinner.DefaultEditor)spnTempoExtra.getEditor()).getTextField().getFormatter()).setAllowsInvalid(false);
				//((JSpinner.DefaultEditor)spnTempoExtra.getEditor()).getTextField().setEditable(false);
				//JSpinner.DefaultEditor editor = (JSpinner.DefaultEditor) spnTempoExtra.getEditor();
				//editor.getTextField().setEnabled(false);
				//editor.getTextField().setEditable(false);
				
				//((JFormattedTextField)((JSpinner.DefaultEditor)spnTempoExtra.getEditor()).getTextField()).setEditable(false);
				spnTempoExtra.setModel(new SpinnerNumberModel(0, 0, 300, 10));
				
				spnTempoExtra.addChangeListener(new ChangeListener() {
					
					public void stateChanged(ChangeEvent arg0) {
						
						secadorFrameListener.spnTempoExtraClicked();
						
					}
				});
				
				lblLote.add(spnTempoExtra);
			
				lblTempoExtraMinuto = new JLabel();
				lblTempoExtraMinuto.setBounds(123, 93, 35, 20);
				lblTempoExtraMinuto.setText("[min]");
				lblLote.add(lblTempoExtraMinuto);
				
				lblTempoExtraHora = new JLabel();
				lblTempoExtraHora.setVisible(false);
				lblTempoExtraHora.setBounds(68, 115, 60, 20);
				lblLote.add(lblTempoExtraHora);
				
				btnAtualizarTempoExtra = new JButton();
				btnAtualizarTempoExtra.setBounds(152, 90, 45, 27);
				btnAtualizarTempoExtra.setEnabled(false);
				btnAtualizarTempoExtra.setText("OK");
				
				btnAtualizarTempoExtra.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
					
						secadorFrameListener.btnAtualizarTempoExtraClicked();
						
					}
					
				});
				
				lblLote.add(btnAtualizarTempoExtra);
				
			
		//RECEITA - PANE
		lblReceitaPane = new JLabel();
		lblReceitaPane.setBounds(2, 70, 225, 135);
		lblReceitaPane.setBorder(new TitledBorder(null, null, TitledBorder.CENTER, TitledBorder.TOP, null, null));
		getContentPane().add(lblReceitaPane);
		
			lblReceitaTitulo = new JLabel();
			lblReceitaTitulo.setBounds(90, 5, 50, 20);
			lblReceitaTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblReceitaTitulo.setText("Receita");
			lblReceitaPane.add(lblReceitaTitulo);
			
				lblReceita = new JLabel();
				lblReceita.setHorizontalAlignment(SwingConstants.CENTER);
				lblReceita.setBounds(10, 25, 210, 20);
				//lblReceita.setText("123456789012345678901234567890");
				lblReceitaPane.add(lblReceita);
			
			lblDataEntradaTitulo = new JLabel();
			lblDataEntradaTitulo.setBounds(60, 45, 120, 20);
			lblDataEntradaTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblDataEntradaTitulo.setText("Data de Entrada");
			lblReceitaPane.add(lblDataEntradaTitulo);
			
				lblDataEntrada = new JLabel();
				lblDataEntrada.setHorizontalAlignment(SwingConstants.CENTER);
				lblDataEntrada.setBounds(10, 65, 210, 20);
				//lblDataEntrada.setText("DD/MM/AAAA");
				lblReceitaPane.add(lblDataEntrada);
			
			lblHoraEntradaTitulo = new JLabel();
			lblHoraEntradaTitulo.setBounds(60, 85, 120, 20);
			lblHoraEntradaTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblHoraEntradaTitulo.setText("Hora de Entrada");
			lblReceitaPane.add(lblHoraEntradaTitulo);
			
				lblHoraEntrada = new JLabel();
				lblHoraEntrada.setHorizontalAlignment(SwingConstants.CENTER);
				lblHoraEntrada.setBounds(10, 105, 210, 20);
				//lblHoraEntrada.setText("HH:MM:SS");
				lblReceitaPane.add(lblHoraEntrada);
			
			
			
			//CONTROLE - PANE
			lblControlePane = new JLabel();
			lblControlePane.setBorder(new TitledBorder(null, "Controle", TitledBorder.CENTER, TitledBorder.TOP, null, null));
			lblControlePane.setBounds(227, 210, 205, 100);
			getContentPane().add(lblControlePane);
			
				rdbtnEntrada = new JRadioButton();
				rdbtnEntrada.setBounds(10, 15, 65, 20);
				rdbtnEntrada.setText("Entrada");
				lblControlePane.add(rdbtnEntrada);
				
				rdbtnMassa = new JRadioButton();
				rdbtnMassa.setBounds(135, 15, 60, 20);
				//btnMassa.setBounds(105, 15, 60, 20);
				rdbtnMassa.setText("Massa");
				lblControlePane.add(rdbtnMassa);
				
				rdgrpBtnControle = new ButtonGroup();
				rdgrpBtnControle.add(rdbtnEntrada);
				rdgrpBtnControle.add(rdbtnMassa);
				
				lblTemperatura = new JLabel();
				lblTemperatura.setBounds(30, 40, 100, 20);
				lblTemperatura.setText("Temperatura [°C]:");
				lblControlePane.add(lblTemperatura);
				
				spnTemperatura = new JSpinner();
				spnTemperatura.setBounds(128, 38, 55, 25);
				//Configurando Spinner para Aceitar Somente Números
				((DefaultFormatter)((JSpinner.DefaultEditor)spnTemperatura.getEditor()).getTextField().getFormatter()).setAllowsInvalid(false);
				spnTemperatura.setModel(new SpinnerNumberModel(1, 1, 120, 1));				
				lblControlePane.add(spnTemperatura);
				
				btnAtualizarControle = new JButton();				
				btnAtualizarControle.setBounds(65, 65, 75, 27);
				btnAtualizarControle.setText("Atualizar");
				btnAtualizarControle.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
					
						secadorFrameListener.btnAtualizarControleClicked();
						
					}
					
				});
				
				lblControlePane.add(btnAtualizarControle);
			
			//PROTEÇÃO - PANE
			lblProtecaoPane = new JLabel();
			lblProtecaoPane.setBorder(new TitledBorder(null, "Proteção", TitledBorder.CENTER, TitledBorder.TOP, null, null));
			lblProtecaoPane.setBounds(1, 210, 229, 100);
			getContentPane().add(lblProtecaoPane);
			
				rdbtnSensorDeMovimento = new JRadioButton();
				rdbtnSensorDeMovimento.setBackground(Color.WHITE);
				rdbtnSensorDeMovimento.setBounds(43, 32, 145, 20);
				rdbtnSensorDeMovimento.setText("Sensor de Movimento");
				lblProtecaoPane.add(rdbtnSensorDeMovimento);

				btnAtualizarProtecao = new JButton();
				btnAtualizarProtecao.setBounds(75, 65, 75, 27);
				btnAtualizarProtecao.setText("Atualizar");
				btnAtualizarProtecao.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent arg0) {
					
						secadorFrameListener.btnAtualizarProtecaoClicked();
						
					}
					
				});
				
				lblProtecaoPane.add(btnAtualizarProtecao);
			
				lblProtecaoIcon = new JLabel();
				lblProtecaoIcon.setIcon(new ImageIcon(urlIconProtecao));
				lblProtecaoIcon.setBounds(10, 50, 40, 40);
				lblProtecaoIcon.setToolTipText("Pane Mecânica detectada!");
				lblProtecaoIcon.setVisible(false);
				lblProtecaoPane.add(lblProtecaoIcon);
				
				
		//BOTÕES - SECAGEM E AJUSTE
		btnSecagem = new JButton();
		btnSecagem.setBounds(180, 305, 90, 27);
		btnSecagem.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSecagem.setText("Secagem");
		btnSecagem.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				secadorFrameListener.btnSecagemClicked();

			}
			
		});
		
		getContentPane().add(btnSecagem);
		
		btnAjuste = new JButton();
		btnAjuste.setIcon(new ImageIcon(urlIconAjuste));
		btnAjuste.setBounds(2, 16, 36, 36);
		btnAjuste.setToolTipText("Número de Secador e Sincronia de Relógio");
		btnAjuste.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				secadorFrameListener.btnAjusteClicked();

			}
			
		});
		
		getContentPane().add(btnAjuste);
		
	}
	
	
////////////////////////////////////////////
////////////////////////////////////////////
////////////////////////////////////////////	
	//TODO - GETTERS
	
	public boolean getRdbtnEntradaSelected() {
		
		return rdbtnEntrada.isSelected();
		
	}
	
	public boolean getRdbtnMassaSelected() {
		
		return rdbtnMassa.isSelected();
		
	}
	
	public int getSpnTemperaturaValue() {
		
		return (Integer) spnTemperatura.getValue();
		
	}
	
	public int getSpnTempoExtraValue() {
		
		return (Integer) spnTempoExtra.getValue();
		
	}
	
	public String getLblReceitaText() {
		
		return lblReceita.getText();
		
	}
	
	public boolean getRdbtnSensorDeMovimentoSelected() {
		
		return rdbtnSensorDeMovimento.isSelected();
		
	}
	
	public String getLblLoteNomeText() {
		
		return lblLoteNome.getText();
		
	}
	
////////////////////////////////////////////
////////////////////////////////////////////
////////////////////////////////////////////
	//TODO - SETTERS
	
	public void setLblProtecaoIconVisible(boolean visible) {
		
		lblProtecaoIcon.setVisible(visible);
		
	}
	
	public void setLblTemperaturaEntradaText(String temperaturaEntradaMedida) {
		
		if(temperaturaEntradaMedida.equals("0")) lblTemperaturaEntrada.setText("Erro A/C"); 
		else lblTemperaturaEntrada.setText(temperaturaEntradaMedida + " [°C]");
		
	}
	
	public void setLblTemperaturaMassaText(String temperaturaMassaMedida) {
		
		if(temperaturaMassaMedida.equals("0")) lblTemperaturaMassa.setText("Erro B/D");
		else lblTemperaturaMassa.setText(temperaturaMassaMedida + " [°C]");
		
	}
	
	public void setSpnTemperaturaValue(String setPointMedido) {
		
		if((Integer.parseInt(setPointMedido) >= 1) && (Integer.parseInt(setPointMedido) <= 120)) {
			
			spnTemperatura.setValue(Integer.parseInt(setPointMedido));
			
		} //else spnTemperatura.setValue(120); //-1
		
	}
	
	public void setRdbtnEntradaOuMassaSelected(String tipoControleMedido) {
	
		//FALSE = CONTROLE PELO SENSOR DE ENTRADA
		//TRUE = CONTROLE PELO SENSOR DE MASSA
		if(tipoControleMedido.equals("0")) rdbtnEntrada.setSelected(true);
		else if(tipoControleMedido.equals("1")) rdbtnMassa.setSelected(true);
		
	}
	
	public void setRdbtnSensorDeMovimentoSelected(String sensorDeMovimentoMedido) {
		
		if(sensorDeMovimentoMedido.equals("0")) rdbtnSensorDeMovimento.setSelected(false);
		else if(sensorDeMovimentoMedido.equals("1")) rdbtnSensorDeMovimento.setSelected(true);		
		
	}
	
	public void setLblTempoRestanteForegroundColor(Color color) {
		
		lblTempoRestante.setForeground(color);
		
	}
	
	public void setLblTempoRestanteText(String tempoRestanteMedido) {
		
		lblTempoRestante.setText(tempoRestanteMedido);
		
	}
	
	public void setLblTemperaturaEntradaForegroundColor(Color color) {
		
		lblTemperaturaEntrada.setForeground(color);
		
	}
	
	public void setLblTemperaturaMassaForegroundColor(Color color) {
		
		lblTemperaturaMassa.setForeground(color);
		
	}
	
	public void setBtnAjusteEnabled(boolean enabled) {
		
		btnAjuste.setEnabled(enabled);
		
	}
	
	public void setRdbtnSensorDeMovimentoEnabled(boolean enabled) {
		
		rdbtnSensorDeMovimento.setEnabled(enabled);
		
	}
	
	public void setBtnAtualizarProtecaoEnabled(boolean enabled) {
		
		btnAtualizarProtecao.setEnabled(enabled);
		
	}
	
	public void setRdbtnEntradaEnabled(boolean enabled) {
		
		rdbtnEntrada.setEnabled(enabled);
		
	}

	public void setRdbtnMassaEnabled(boolean enabled) {
		
		rdbtnMassa.setEnabled(enabled);
		
	}
	
	public void setSpnTemperaturaEnabled(boolean enabled) {
		
		spnTemperatura.setEnabled(enabled);
		
	}
	
	public void setBtnAtualizarControleEnabled(boolean enabled) {
		
		btnAtualizarControle.setEnabled(enabled);
		
	}
	
	public void setBtnSecagemEnabled(boolean enabled) {
		
		btnSecagem.setEnabled(enabled);
		
	}
	
	public void setLblTemperaturaEnabled(boolean enabled) {
		
		lblTemperatura.setEnabled(enabled);
		
	}
	
	public void setLblProtecaoPaneEnabled(boolean enabled) {
		
		lblProtecaoPane.setEnabled(enabled);
		
	}
	
	public void setLblControlePaneEnabled(boolean enabled) {
		
		lblControlePane.setEnabled(enabled);
		
	}
	
	public void setLblReceitaText(String receitaNome) {
		
		lblReceita.setText(receitaNome);
		
	}
	
	public void setLblReceitaForegroundColor(Color color) {
		
		lblReceita.setForeground(color);
		
	}
	
	public void setLblDataEntradaText(String dataEntrada) {
		
		lblDataEntrada.setText(dataEntrada);
		
	}
	
	public void setLblDataEntradaForegroundColor(Color color) {
		
		lblDataEntrada.setForeground(color);
		
	}
	
	public void setLblHoraEntradaText(String horaEntrada) {
		
		lblHoraEntrada.setText(horaEntrada);
		
	}
	
	public void setLblHoraEntradaForegroundColor(Color color) {
		
		lblHoraEntrada.setForeground(color);
		
	}
	
	public void setLblLoteNomeText(String loteNome) {
		
		lblLoteNome.setText(loteNome);
		
	}
	
	public void setLblLoteNomeForegroundColor(Color color) {
		
		lblLoteNome.setForeground(color);
		
	}
	
	public void setLblLEDForegroundColor(Color color) {
		
		lblLED.setForeground(color);
		
	}
	
	public void setLblLEDToolTipText(String toolTipText) {
		
		lblLED.setToolTipText(toolTipText);
		
	}
	
	public void setLblEtapa1Enabled(boolean enabled) {
		
		lblEtapa1.setEnabled(enabled);
		
	}
	
	public void setLblEtapa2Enabled(boolean enabled) {
		
		lblEtapa2.setEnabled(enabled);
		
	}

	public void setLblEtapa3Enabled(boolean enabled) {
	
		lblEtapa3.setEnabled(enabled);
	
	}
	
	public void setLblEtapa1ForegroundColor(Color color) {
		
		lblEtapa1.setForeground(color);
		
	}
	
	public void setLblEtapa2ForegroundColor(Color color) {
	
		lblEtapa2.setForeground(color);
	
	}
	
	public void setLblEtapa3ForegroundColor(Color color) {
	
		lblEtapa3.setForeground(color);
	
	}
	
	public void setLblTempoExtraHoraVisible(boolean visible) {
		
		lblTempoExtraHora.setVisible(visible);
		
	}
	
	public void setLblTempoExtraHoraText(String text) {
		
		lblTempoExtraHora.setText(text);
	
	}
	
	public void setSpnTempoExtraValue(int value) {
	
		spnTempoExtra.setValue(value);
		
	}
	
	public void setLblTempoExtraTituloEnabled(boolean enabled) {
		
		lblTempoExtraTitulo.setEnabled(enabled);
		
	}
	
	public void setSpnTempoExtraEnabled(boolean enabled) {
		
		spnTempoExtra.setEnabled(enabled);
		
	}
	
	public void setLblTempoExtraMinutoEnabled(boolean enabled) {
		
		lblTempoExtraMinuto.setEnabled(enabled);
		
	}
	
	public void setBtnAtualizarTempoExtraEnabled(boolean enabled) {
		
		btnAtualizarTempoExtra.setEnabled(enabled);
		
	}
	
	public void setRdgrpBtnControle(boolean selected) {
		
		if(!selected) {
			
			rdgrpBtnControle.clearSelection();
			
		}
		
		
	}
	
	
////////////////////////////////////////////
////////////////////////////////////////////
////////////////////////////////////////////

	
	//FAZ A LINKAGEM ENTRE O RUNNER E A VIEW [SECADORFRAME] POR INTERMÉDIO DO LISTENER [SECADORFRAME]
	public void addSecadorFrameListener(SecadorFrameListener secadorFrameListener) {
		this.secadorFrameListener = secadorFrameListener;
	}
	
}
