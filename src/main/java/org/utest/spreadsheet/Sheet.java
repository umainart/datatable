package org.utest.spreadsheet;

import java.util.ArrayList;
import java.util.List;

public class Sheet {

	private String name;
	private List<Row> rows;
	
	public Sheet(String name, List<Row> rows) {
		this.name = name;
		this.rows = rows;
	}
	
	public Sheet(String name, Row row) {
		this.name = name;
		rows = new ArrayList<Row>();
		rows.add(row);
	}
	
	public String getName() {
		return name;
	}
	
	public List<Row> getRows() {
		return rows;
	}
}
