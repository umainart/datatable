package org.utest.spreadsheet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.apache.poi.hssf.model.WorkbookRecordList;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelBuilder implements SpreadsheetBuilder{

	private String filename;
	private Optional<String> sheet;
	private Optional<Integer> row;
	
	public ExcelBuilder(String filename) {
		this.filename = filename;
		sheet = Optional.empty();
		row = Optional.empty();
	}
	
	@Override
	public SpreadsheetBuilder withSheet(String sheet) {
		this.sheet = Optional.of(sheet);
		return this;
	}
	
	@Override
	public SpreadsheetBuilder withRow(int row) {
		this.row = Optional.of(Integer.valueOf(row));
		return this;
	}
	
	@Override
	public Spreadsheet build() throws IOException, NoSuchElementException {
		return (sheet.isPresent() && row.isPresent()) ? 
						read(sheet.get(), row.get()) : 
			   (sheet.isPresent() ? 
					   read(sheet.get()) : readAll());			
	}
	
	
	private Spreadsheet read(String sheetName) throws NoSuchElementException, FileNotFoundException, IOException{
		FileInputStream fis = new FileInputStream(Optional.ofNullable(filename).get());
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet xssfSheet = workbook.getSheet(sheetName);
		if(xssfSheet == null) throw new NoSuchElementException(sheetName+" doesn't exists!");
		return new Spreadsheet(filename, new Sheet(sheetName, readRows(xssfSheet)));
	}
	
	private Spreadsheet read(String sheetnanme, int rowIdx) throws FileNotFoundException, IOException{
		FileInputStream fis = new FileInputStream(Optional.ofNullable(filename).get());
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		Row row = readRow(workbook.getSheet(sheetnanme).getRow(rowIdx));
		Sheet sheet = new Sheet(sheetnanme, row);
		return new Spreadsheet(filename, sheet);
	}
	
	private Spreadsheet readAll() throws FileNotFoundException, IOException{
		FileInputStream fis = new FileInputStream(Optional.ofNullable(filename).get());
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		List<Sheet> sheets = new ArrayList<>();
		try {
			for(int sheetIdx = 0; sheetIdx < workbook.getNumberOfSheets(); sheetIdx++) {
				XSSFSheet xsSheet = workbook.getSheetAt(sheetIdx);
				List<Row> rows = readRows(xsSheet);
				sheets.add(new Sheet(xsSheet.getSheetName(), rows));
			}
		}finally {
			fis.close();
			workbook.close();
		}
		return new Spreadsheet(filename, sheets);
	}
	
	private List<Row> readRows(XSSFSheet xsshet){
		List<Row> rows = new ArrayList<Row>();
		System.out.println("lastRowNum"+xsshet.getLastRowNum());
		for(int rowIdx = 0; rowIdx < xsshet.getLastRowNum(); rowIdx++) {
			Optional<XSSFRow> rowOptional = Optional.ofNullable(xsshet.getRow(rowIdx));
			if(rowOptional.isPresent()) {			
				rows.add(readRow(rowOptional.get()));
			}
		}
		return rows;
	}
	
	private Row readRow(XSSFRow xssrow) {
		Row row = new Row();
		for(short cellIdx = 0; cellIdx < xssrow.getLastCellNum(); cellIdx++) {
			Optional cellOptional = Optional.ofNullable(xssrow.getCell(cellIdx));
			row.addCell(cellOptional.orElse(new String("")).toString());
		}
		return row;
	}
}
