package org.utest.datatable;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.NoSuchElementException;

import org.junit.*;
import org.utest.spreadsheet.ExcelBuilder;

public class DataTableTest {
	private static final String TEST_RESOURCES_MCR_LFSC_XLSX = "../datatable/src/test/resources/MCR_LFSC.xlsx";
	private static final String TEST_RESOURCES_Logins_XLSX = "../datatable/src/test/resources/Logins.xlsx";
	
	
	public void shouldGetCells() throws NoSuchElementException, IOException{
		DataTable dt = new DataTable(new ExcelBuilder(TEST_RESOURCES_MCR_LFSC_XLSX));
		assertEquals("cell", "get.nomeSimplPaiFamilia.3", dt.getCells("registraLFSC", 2).get(2));
	}
	
	
	public void shouldGetCellsFromChangedBuilder() throws NoSuchElementException, IOException{
		DataTable dt = new DataTable(new ExcelBuilder(TEST_RESOURCES_MCR_LFSC_XLSX));
		dt.changeBuilder(new ExcelBuilder(TEST_RESOURCES_Logins_XLSX));
		assertEquals("cell", "SUSEP", dt.getCells("logins", 29).get(7));
	}
}
