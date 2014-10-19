package com.djcrocker.util;

import java.util.ArrayList;
import java.util.List;


public class TableBuilder {
	
	private List<List<String>> table;
	
	public TableBuilder(String... headers) {
		this.table = new ArrayList<List<String>>();
		addRow(headers);
	}
	
	public void addRow(String... cells) {
		List<String> row = new ArrayList<String>();
		for(int i=0; i<cells.length; i++) {
			row.add(cells[i]);
		}
		table.add(row);
	}
	
	public String getFormattedTable() {
		int numCols = table.get(0).size();
		String[] colFormats = new String[numCols];
		StringBuilder separatorBuilder = new StringBuilder();
		
		// Determine format string for each column
		for(int i=0; i<numCols; i++) {
			int width = 1;
			for(int j=0; j<table.size(); j++) {
				String cell = table.get(j).get(i);
				width = Math.max(width, cell.length() + 1);
			}
			colFormats[i] = "|%-" + width +"s";
			
			// Add to the separator
			separatorBuilder.append("+");
			for(int j=0; j<width; j++) {
				separatorBuilder.append("-");
			}
		}
		separatorBuilder.append("+\n");
		final String separator = separatorBuilder.toString();
		
		StringBuilder tableString = new StringBuilder();
		
		// Build the table
		tableString.append(separator);
		for(int i=0; i<table.size(); i++) {
			List<String> row = table.get(i);
			for(int j=0; j<numCols; j++) {
				String cell = row.get(j);
				tableString.append(String.format(colFormats[j], cell));
			}
			tableString.append("|\n");
			
			// Put separator after header
			if(i==0) {
				tableString.append(separator);
			}
		}
		
		// Put separator at end
		tableString.append(separator);
		
		return tableString.toString();
	}
	
	@Override
	public String toString() {
		return getFormattedTable();
	}
}

