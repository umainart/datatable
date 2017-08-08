package org.utest.spreadsheet;

import java.io.IOException;
import java.util.NoSuchElementException;

public interface SpreadsheetBuilder {

	Spreadsheet build() throws IOException, NoSuchElementException;
	
	SpreadsheetBuilder withSheet(String sheet);
	
	SpreadsheetBuilder withRow(int row);
}
