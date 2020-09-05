package com.github.chenjianjx.ssioextsample.spi.internal.base64csv.model;

import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.ssio.spi.developerexternal.abstractsheet.model.SsCell;
import org.ssio.spi.developerexternal.abstractsheet.model.SsRow;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Base64CsvRow implements SsRow {
    private List<Base64CsvCell> cells = new ArrayList<>();

    private Base64CsvRow() {

    }

    public static Base64CsvRow createEmptyRow() {
        return new Base64CsvRow();
    }

    public static Base64CsvRow createFromAcsRecord(CSVRecord acsRecord) {
        Base64CsvRow row = new Base64CsvRow();
        for (String s : acsRecord) {
            row.cells.add(Base64CsvCell.createWithContent(s));
        }
        return row;
    }

    @Override
    public int getNumberOfCells() {
        return cells.size();
    }

    @Override
    public SsCell getCell(int columnIndex) {
        return cells.get(columnIndex);
    }

    @Override
    public SsCell createCell(int columnIndex) {
        int currentSize = cells.size();
        int nextIndex = currentSize;
        Base64CsvCell newCell = null;
        for (int i = nextIndex; i <= columnIndex; i++) {
            newCell = Base64CsvCell.createEmptyCell();
            cells.add(newCell);
        }
        return newCell;
    }

    public void acceptPrinting(CSVPrinter csvPrinter) throws IOException {
        List<String> contentList = cells.stream().map(Base64CsvCell::getContent).collect(Collectors.toList());
        csvPrinter.printRecord(contentList.toArray());

    }
}
