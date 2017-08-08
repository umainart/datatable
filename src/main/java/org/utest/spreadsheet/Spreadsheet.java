package org.utest.spreadsheet;

import java.util.ArrayList;
import java.util.List;

public class Spreadsheet {
	
	private String filename;
	private List<Sheet> sheets;
	
	Spreadsheet(String filename, List<Sheet> sheets) {
		this.filename = filename;
		this.sheets = sheets;
	}
	
	Spreadsheet(String filename, Sheet sheet) {
		this.filename = filename;
		sheets = new ArrayList<Sheet>();
		sheets.add(sheet);
	}
	
	public List<Sheet> getSheets() {
		return sheets;
	}
	
	public String getFilename() {
		return filename;
	}

}
