package com.campotech.sgsoft.view;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class JTextFieldCharacterLimit extends PlainDocument {
	
	private int limit;
	
	
	public JTextFieldCharacterLimit(int limit) {
		this.limit = limit;
	}
	
	public void insertString(int offSet, String string, AttributeSet attributeSet) throws BadLocationException {
		
		if(string == null) {
			return;
		} else if((getLength() + string.length()) <= limit) {
			super.insertString(offSet, string, attributeSet);
		}
	}
}
