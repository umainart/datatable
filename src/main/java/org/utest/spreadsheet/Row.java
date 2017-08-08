package org.utest.spreadsheet;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Row {

	private List<Cell> cells;
	
	Row() {
		cells = new ArrayList<>();
	}
	
	public void addCell(String cell) {
		cells.add(new Cell(cell));
	}
	
	public List<String> getCells(){
		return cells.stream()
				.map(Cell::getContent)
				.collect(Collectors.toList());
	}

	private class Cell{
		private String content;
		private Cell(String content) {
			this.content = content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public String getContent() {
			return content;
		}
	}
	
}
