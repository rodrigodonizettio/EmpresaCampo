package com.campotech.sgsoft.COMPort;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.text.DateFormatter;

import org.joda.time.Hours;

import com.campotech.sgsoft.controller.listener.TwoWaySerialCommListener;
import com.campotech.sgsoft.view.GerenciamentoRelatoriosFrame;


public class TwoWaySerialComm {
	
	
	//ATRIBUTOS PERTENCENTES À CLASSE
    OutputStream outputStream;
    InputStream inputStream;
    SerialPort serialPort;
    String portName;
    public static String dataFromSerial;
    public static String dataMultipleLinesFromSerial;
    public static boolean flagPortaEncontrada = false;
    public static boolean flagBuscaProximaPorta = false;
    
    private ArrayList<String> listaDeDados = new ArrayList<String>();
    
    //CONSTANTES PERTENCENTES À CONEXÃO
    final static int TIME_OUT = 2000; //m[s]
    static int BAUD_RATE = 57600; //[bps]
    
    
    //ÍCONES DO JOPTIONPANE
	private URL urlSuccessIcon = GerenciamentoRelatoriosFrame.class.getResource("images/successIcon-JOptionPane-72px.png");
	private ImageIcon successIcon = new ImageIcon(urlSuccessIcon);
    
	
    //ATRIBUTOS PERTENCENTES AO LISTENER
    TwoWaySerialCommListener twoWaySerialCommListener;
        
        
    //CONSTRUTOR
    public TwoWaySerialComm() {
    	
    	super();
                
    }

    
//////////////////////////////////////////////////////
//////////////////////////////////////////////////////
//////////////////////////////////////////////////////
    

    public void connect(String portName) throws Exception, NullPointerException, PortInUseException {
    	
    	CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
        if (portIdentifier.isCurrentlyOwned()) {
        	
            System.out.println("Error: Port is currently in use");
            //JOptionPane.showMessageDialog(null, "Existe outro dispositivo utilizando a Porta COM do Terminal de Usuário.\nLibere a Porta e tente novamente.", "Falha - Porta COM em Uso", JOptionPane.ERROR_MESSAGE);
            this.close();
            
        } else {
        	
            CommPort commPort = portIdentifier.open(this.getClass().getName(), TIME_OUT); //NOME DE REFERÊNCIA DA CLASSE QUE ESTÁ INICIALIZADO A COM, TIMEOUT

            if (commPort instanceof SerialPort) {
            	
                serialPort = (SerialPort) commPort;
                serialPort.setSerialPortParams(BAUD_RATE, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
                   
                inputStream = serialPort.getInputStream();
                outputStream = serialPort.getOutputStream();
//
                //Thread threadSerialComm = new Thread(new SerialWriter(outputStream));
                //threadSerialComm.start();
//                
                (new Thread(new SerialWriter(outputStream))).start();
                
                serialPort.addEventListener(new SerialReader(inputStream));
                serialPort.notifyOnDataAvailable(true);

            } else {
            	
                System.out.println("Error: Only serial ports are handled by this example.");
                
            }
        }
        
            System.out.println("Connected to " + portName);
            writeToCOM("\r\n");
            
    }
        
//////////////////////////////////////////////////////
//////////////////////////////////////////////////////
//////////////////////////////////////////////////////

    
    //FECHA A COMUNICAÇÃO SERIAL QUE ESTAVA ESTABELECIDA
    public void close() {
		try {
			outputStream.close();
			inputStream.close();
			//serialPort.removeEventListener();
			//serialPort.close();
		} catch (IOException e) {
			System.out.println("Não foi possível fechar porta COM.");
					
		}
	}
    
    
//////////////////////////////////////////////////////
//////////////////////////////////////////////////////
//////////////////////////////////////////////////////
    
    
    public static String[] listAvailablePorts() {
    	
        Vector localVector = new Vector();
        try {
        	
        	Enumeration localEnumeration = CommPortIdentifier.getPortIdentifiers();
            while (localEnumeration.hasMoreElements()) {
            	
                CommPortIdentifier localCommPortIdentifier = (CommPortIdentifier) localEnumeration.nextElement();
                
                if (localCommPortIdentifier.getPortType() == 1) {
                	
                    String str = localCommPortIdentifier.getName();
                    System.out.println("PORTA: " + str);
                    localVector.addElement(str);
                    
                }
            }
        } catch (UnsatisfiedLinkError localUnsatisfiedLinkError) {
        	
                    localUnsatisfiedLinkError.printStackTrace();
                    
        } catch (Exception localException) {
        	
                    localException.printStackTrace();
                    
        }
        
        String[] arrayOfString = new String[localVector.size()];
        localVector.copyInto(arrayOfString);
        return arrayOfString;
        
    }
 

//////////////////////////////////////////////////////
//////////////////////////////////////////////////////
//////////////////////////////////////////////////////        

    public String discoverUserTerminalPort() throws NullPointerException, PortInUseException {
    	
        portName = null;
        
        try {
        	
        	Enumeration localEnumeration = CommPortIdentifier.getPortIdentifiers();
            
        	while (localEnumeration.hasMoreElements() && !flagPortaEncontrada) { 
        		
        		boolean flagBaudRate = false;
        		
        		flagPortaEncontrada = false;
        		flagBuscaProximaPorta = false;
                CommPortIdentifier localCommPortIdentifier = (CommPortIdentifier) localEnumeration.nextElement();
                
                //1 = Serial Port Type
                if (localCommPortIdentifier.getPortType() == 1) {
                	
                    portName = localCommPortIdentifier.getName();
                                        
                    try { 
                    	
                    	this.connect(portName); //this
                        int cont = 0;
                        
                        while(!flagPortaEncontrada && !flagBuscaProximaPorta) {
							
							if(cont < 3) {
								
								Thread.sleep(50);
								this.writeToCOM("ATI\r\n");
								Thread.sleep(120);
								
							} else {
								
								//TESTE! PARA ORIGINAL: Remover comentários do Else e apagar o flagBuscaProximaPorta
								flagBuscaProximaPorta = true;
								
								//BKP: LINHAS ABAIXO [DESCOMENTAR PARA ORIGINAL]
								//this.close();
								//JOptionPane.showMessageDialog(null, "Terminal de Usuário não encontrado.", "Falha - Terminal de Usuário Não Encontrado", JOptionPane.ERROR_MESSAGE);
								//System.exit(0);
								
							}
							
							System.out.println("LISTA: " + this.readFromCOMList());
							
							for (String dado : this.readFromCOMList()) {
								
								if(dado.contains("Telegesis")) {
								
									System.out.println("ACHOU!");
									flagPortaEncontrada = true;
									break;
									
								}
								
							}
							cont++;
							
							
							//System.out.println("Software Reset: " + twoWaySerialComm.readFromCOM());
							//System.exit(0);
							//System.out.println("Software Reset: " + twoWaySerialComm.readMultipleLinesFromCOM());
							//if(twoWaySerialComm.readFromCOMString().contains("OK")) {
							//if(twoWaySerialComm.readMultipleLinesFromCOM().contains("OK")) {
								
								//JOptionPane.showMessageDialog(this.loginFrame, "Terminal de Usuário RESETADO!", "Sucesso - Software Reset", JOptionPane.INFORMATION_MESSAGE, successIcon);
								//gerenciamentoRedeFrame.setLblSoftwareResetLEDEnable(true);
								//gerenciamentoRedeFrame.setLblSoftwareResetLEDForegroundColor(Color.GREEN);
								//flagSoftwareReset = true;
								
							//} //else gerenciamentoRedeFrame.setLblSoftwareResetLEDEnable(false);
							
							
						}
                        
                        
                        
                        /*
                        while(cont < 3 && !flagPortaEncontrada) {
                        	
                            writeToCOM("ATI\r\n");
                            Thread.sleep(10);
                                        		
                            if(dataFromSerial.contains("Telegesis")) {
                            	
                                System.out.println("[ Tentativa: " + cont + " ]");
                                System.out.println("Porta Encontrada! A Porta " + portName + " é um TU");
                                System.out.println("Encerrando busca por Portas COM");
                                            		
                                System.out.println("IN: " + dataFromSerial);
                                
                                flagPortaEncontrada = true;
                                
                                dataFromSerial = null;
                                listaDeDados.clear();
                                
                                break;

                            } else {
                            	
                            	cont++;
                                System.out.println("[ Tentativa: " + cont + " ]");
                                System.out.println("A Porta " + portName + " NÃO é um TU");
                                            		
                                System.out.println("IN: " + dataFromSerial);
                                            		
                                flagPortaEncontrada = false;
                                
                                dataFromSerial = null;
                                listaDeDados.clear();
                                
                                
                            }
                                        		
                        }
                        */
                    } catch (PortInUseException e) {
                    	
                    	e.printStackTrace();
                    	System.out.println("Port-In-Use-Exception @ Try#2-DISCOVERUSERTERMINALPORT");
                    	JOptionPane.showMessageDialog(null, "Existe outro dispositivo utilizando a Porta COM do Terminal de Usuário.\nLibere a Porta e tente novamente.", "Falha - Porta COM em Uso", JOptionPane.ERROR_MESSAGE);
                    	//this.close();
                    	System.exit(0);
                    	
                    } catch (NullPointerException e) {
                    	
                    	e.printStackTrace();
                    	System.out.println("Null-Pointer-Exception @ Try#2-DISCOVERUSERTERMINALPORT");
                    	//JOptionPane.showMessageDialog(null, "NULL POINTER EXCEPTION.", "Falha - Porta COM", JOptionPane.ERROR_MESSAGE);
                    	this.close();
                    	discoverUserTerminalPort();
                    	//System.exit(0);
                    	
                    }                   
                                        
                }
                        
        	}
        	
        } catch (UnsatisfiedLinkError e) {
        	
            e.printStackTrace();
                        
        } catch (Exception e) {
        	
            e.printStackTrace();
            
        }
        
        if(flagPortaEncontrada) {
                	
        	return portName;
                	
        } else {
        	
        	//discoverUserTerminalPort();
        	//JOptionPane.showMessageDialog(null, "Terminal de Usuário não encontrado. Conecte-o e tente novamente", "Falha - Terminal de Usuário não conectado", JOptionPane.ERROR_MESSAGE);
        	//System.exit(0);
        	return "Nenhuma";
        	
        }
                
    }        

        
//////////////////////////////////////////////////////
//////////////////////////////////////////////////////
//////////////////////////////////////////////////////
        
        
    public class SerialReader implements SerialPortEventListener {
    	
    	private InputStream inputStream;
        private byte[] buffer  = new byte[1024];
                
        
        public SerialReader(InputStream in) {
        	
            this.inputStream = in;
            
        }

        public void serialEvent(SerialPortEvent arg0) {
        	
            if(arg0.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
                		
            	int data;
            	//dataMultipleLinesFromSerial = null;
            	
            	try {
            		
                    int len = 0;
                    while ((data = inputStream.read()) > -1) {
                    	
                        if (data == '\n') { // '\r'
                        	
                        	break;
                        
                        }
                        
                        buffer[len++] = (byte) data;
                        
                    }
                     	//System.out.println(new String(buffer, 0, len));
                    	dataFromSerial = new String(buffer, 0, len);
                    	
                    	listaDeDados.add(dataFromSerial);
                    	
                    	/*
                    	for (String string : _list) {
							System.out.println("DADO-" + cont + string);
							cont++;
						}
                    	*/
                    	
                    	/*
                    	while(_list.size() > 0) {
                    		System.out.println("TAMANHO: " + _list.size());
                    		System.out.println("DADO-" + cont + ": " + _list.remove(0));
                    		//System.out.println("TAMANHO: " + _list.size());
                    		
                    	}
                    	*/
                    	//dataMultipleLinesFromSerial += new String(buffer, 0, len);  
                    	
                } catch (IOException e) {
                	
                    e.printStackTrace();
                    System.exit(-1);
                    
                }
                        
            }
                	
        }

    }
        
        
//////////////////////////////////////////////////////
//////////////////////////////////////////////////////
//////////////////////////////////////////////////////
        
        
    public static class SerialWriter implements Runnable {
    	
    	OutputStream    out;
    	
    	
    	public SerialWriter(OutputStream out) {
    		
                        this.out = out;
        }
    	
    	
    	public void run() {
    		
    		try {
    			
    			int c = 0;
    			
    			while ((c = System.in.read()) > -1) {
    				
    				this.out.write(c);
    			
    			}
            
    		} catch (IOException e) {
    			
    			e.printStackTrace();
    			System.exit(-1);
                        
    		}
    		
        }
    	
    }
        
        
//////////////////////////////////////////////////////
//////////////////////////////////////////////////////
//////////////////////////////////////////////////////


    public void writeToCOM(String str) {
    	
    	listaDeDados.clear();
    	dataFromSerial = null;
    	
    	try {
    		
    		this.outputStream.write(str.getBytes());
    		
    	} catch (IOException e) {
    		
    		e.printStackTrace();
    		
    	}
    	
    }
    
        
//////////////////////////////////////////////////////
//////////////////////////////////////////////////////
//////////////////////////////////////////////////////

    public ArrayList<String> readFromCOMList() {
    	
//    	return dataFromSerial;
/*
    	if(_list.size() > 0)
    	{
    		return _list.remove(0);
    	}
    	else
    	{
    		return "";
    	}
*/    	
    	
    	return listaDeDados;
    			
    }
 

    public String readFromCOMString() {
    	
    	return dataFromSerial;
    	
    }
    
    
    public String readMultipleLinesFromCOM() {
    	
    	String data = dataMultipleLinesFromSerial;
    	dataMultipleLinesFromSerial = null;
    	return data;
    	
    }
    
//////////////////////////////////////////////////////
//////////////////////////////////////////////////////
//////////////////////////////////////////////////////    

    public String getPortName() {

    	return portName;

    }

//////////////////////////////////////////////////////
//////////////////////////////////////////////////////
//////////////////////////////////////////////////////
    
    //TESTE APENAS
    /*
    public static void main ( String[] args )
    {
        try
        {
            //(new TwoWaySerialComm()).connect("COM3");
        	TwoWaySerialComm twsc = new TwoWaySerialComm();
        	twsc.connect("COM3");        	
        }
        catch ( Exception e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    */
    
    //FAZ A LINKAGEM ENTRE O RUNNER E A PORTA SERIAL POR INTERMÉDIO DO LISTENER [TWOWAYSERIALCOMMLISTENER]
  	public void addTwoWaySerialCommListener(TwoWaySerialCommListener twoWaySerialCommListener) {
  		
  		this.twoWaySerialCommListener = twoWaySerialCommListener;
  		
  	}        
    
}