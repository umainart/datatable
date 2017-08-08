package org.utest.datatable;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import org.utest.spreadsheet.Spreadsheet;
import org.utest.spreadsheet.SpreadsheetBuilder;

public class DataTable {

	private SpreadsheetBuilder builder;
	
	public DataTable(SpreadsheetBuilder builder) {
		this.builder = builder;
	}
	
	public DataTable changeBuilder(SpreadsheetBuilder builder) {
		this.builder = builder;
		return this;
	}
	
	public List<String> getCells(String sheetName, int row) throws NoSuchElementException, IOException{
		builder.withSheet(sheetName);
		builder.withRow(row);
		Spreadsheet spreadsheet = builder.build();
		return spreadsheet.getSheets().get(0).getRows().get(0).getCells();
	}
}
