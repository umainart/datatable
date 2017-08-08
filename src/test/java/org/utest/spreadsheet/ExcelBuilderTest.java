package org.utest.spreadsheet;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ExcelBuilderTest {

	private static final String TEST_DATA_SOURCE = "../datatable/src/test/resources/datasource.xlsx";
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void shouldReadXSSFile() throws NoSuchElementException, IOException {
		assertNotNull(new ExcelBuilder(TEST_DATA_SOURCE).build());
	}
	
	@Test(expected = FileNotFoundException.class)
	public void shouldthrowNoSuchElementExceptionXSSFile() throws FileNotFoundException, IOException{
		new ExcelBuilder("does_not_exists.xlsx").build();
	}
	
	@Test
	public void shouldReadASheet() throws NoSuchElementException, IOException{
		Spreadsheet spreadsheet = new ExcelBuilder(TEST_DATA_SOURCE).build();
		assertEquals(TEST_DATA_SOURCE, spreadsheet.getFilename());
		
		List<Sheet> sheets = spreadsheet.getSheets().stream()
				.filter(sheet -> sheet.getName().equalsIgnoreCase("login"))
				.collect(Collectors.toList());
		
		
		assertEquals("sheet", "Login", sheets.get(0).getName());
	}
	
	@Test
	public void shouldReadCellsFromAll() throws NoSuchElementException, IOException{
		Spreadsheet spreadsheet = new ExcelBuilder(TEST_DATA_SOURCE).build();
		
		List<Sheet> sheets = spreadsheet.getSheets().stream()
				.filter(sheet -> sheet.getName().equalsIgnoreCase("Login"))
				.collect(Collectors.toList());
		
		Row row = sheets.get(0).getRows().get(14);
		assertEquals("cell", "Input Username and Password",row.getCells().get(0));
	}

	public void shouldReadCellsFromSheet() throws NoSuchElementException, IOException{
		Spreadsheet spreadsheet = new ExcelBuilder(TEST_DATA_SOURCE)
				.withSheet("Login")
				.build();
		
		Sheet sheet = spreadsheet.getSheets().get(0);
		Row row = sheet.getRows().get(3);
		assertEquals("cell", "Commercial Papers", row.getCells().get(4));
	}
	
	public void shouldReadCellsFromRow() throws NoSuchElementException, IOException{
		Spreadsheet spreadsheet = new ExcelBuilder(TEST_DATA_SOURCE)
				.withSheet("Initial")
				.withRow(9)
				.build();
		
		Sheet sheet = spreadsheet.getSheets().get(0);
		assertEquals("cell", "Data3", sheet.getRows().get(9).getCells().get(2));
	}
}
