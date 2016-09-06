package com.campotech.sgsoft.COMPort;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Vector;

import com.itextpdf.text.log.SysoCounter;

/**
 * This version of the TwoWaySerialComm example makes use of the SerialPortEventListener to avoid polling.
 * 
 */
public class Test
{
        OutputStream    out;
        public static String dataFromSerial;

        public Test()
        {
                super();
        }

        void connect(String portName) throws Exception
        {
                CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
                if (portIdentifier.isCurrentlyOwned())
                {
                        System.out.println("Error: Port is currently in use");
                }
                else
                {
                        CommPort commPort = portIdentifier.open(this.getClass().getName(), 2000);

                        if (commPort instanceof SerialPort)
                        {
                                SerialPort serialPort = (SerialPort) commPort;
                                serialPort.setSerialPortParams(57600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

                                InputStream in = serialPort.getInputStream();
                                out = serialPort.getOutputStream();

                                (new Thread(new SerialWriter(out))).start();

                                serialPort.addEventListener(new SerialReader(in));
                                serialPort.notifyOnDataAvailable(true);

                        }
                        else
                        {
                                System.out.println("Error: Only serial ports are handled by this example.");
                        }
                }
        }

        public static String[] listAvailablePorts()
        {
                Vector localVector = new Vector();
                try
                {
                        Enumeration localEnumeration = CommPortIdentifier.getPortIdentifiers();
                        while (localEnumeration.hasMoreElements())
                        {
                                CommPortIdentifier localCommPortIdentifier = (CommPortIdentifier) localEnumeration.nextElement();
                                if (localCommPortIdentifier.getPortType() == 1)
                                {
                                        String str = localCommPortIdentifier.getName();
                                        localVector.addElement(str);
                                }
                        }
                }
                catch (UnsatisfiedLinkError localUnsatisfiedLinkError)
                {
                        // errorMessage("ports", localUnsatisfiedLinkError);
                }
                catch (Exception localException)
                {
                        // errorMessage("ports", localException);
                }
                String[] arrayOfString = new String[localVector.size()];
                localVector.copyInto(arrayOfString);
                return arrayOfString;
        }

        void writeToCOM(String str)
        {
                try
                {
                        this.out.write(str.getBytes());
                }
                catch (IOException e)
                {
                        e.printStackTrace();
                }
        }

        /**
         * Handles the input coming from the serial port. A new line character is treated as the end of a block in this example.
         */
        public static class SerialReader implements SerialPortEventListener
        {
                private InputStream     in;
                private byte[]          buffer  = new byte[1024];

                public SerialReader(InputStream in)
                {
                        this.in = in;
                }

                public void serialEvent(SerialPortEvent arg0)
                {
                        int data;

                        try
                        {
                                int len = 0;
                                while ((data = in.read()) > -1)
                                {
                                        if (data == '\n')
                                        {
                                                break;
                                        }
                                        buffer[len++] = (byte) data;
                                }
                                dataFromSerial = (new String(buffer, 0, len));
                                System.out.println(dataFromSerial);
                        }
                        catch (IOException e)
                        {
                                e.printStackTrace();
                                System.exit(-1);
                        }
                }

        }

        /** */
        public static class SerialWriter implements Runnable
        {
                OutputStream    out;

                public SerialWriter(OutputStream out)
                {
                        this.out = out;
                }

                public void run()
                {
                        try
                        {
                                int c = 0;
                                while ((c = System.in.read()) > -1)
                                {
                                        this.out.write(c);
                                }
                        }
                        catch (IOException e)
                        {
                                e.printStackTrace();
                                System.exit(-1);
                        }
                }
        }

        public static void main(String[] args)
        {
                Test twsc = new Test();
                for (int i = 0; i < twsc.listAvailablePorts().length; i++)
                {
                        System.out.println(twsc.listAvailablePorts()[i]);
                }
                try
                {
                        twsc.connect("COM3");
                        twsc.writeToCOM("AT+DMODE:000D6F00034F34E6\r\n");
                        Thread.sleep(500);
                        while(!dataFromSerial.contains("OPEN")) {
                        	
                        	System.out.println("Aguardando OPEN...");
                        	
                        }
                        twsc.writeToCOM("A1A6595\r\n");
                        Thread.sleep(100);
                        while(!dataFromSerial.contains("A2A6")) {
                        	
                        	System.out.println("Aguardando TEMP. ENT...");
                        	
                        }
                        twsc.writeToCOM("+++\r\n");
                        Thread.sleep(1000);
                        while(!dataFromSerial.contains("CLOSED")) {
                        	
                        	System.out.println("Aguardando CLOSED...");
                        	
                        }
                        System.exit(0);
                        
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }
        }

}
