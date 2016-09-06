package com.campotech.sgsoft.controller.listener;

import java.io.BufferedReader;

public interface TwoWaySerialCommListener {

	public void btnOkClicked(BufferedReader bufferedReader);
	public void telegesisEncontrado();
	public void receivePacket(String packet);
	
}
