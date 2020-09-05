package com.github.chenjianjx.ssioextsample.spi.internal.base64csv.model;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.IOUtils;
import org.ssio.spi.developerexternal.abstractsheet.model.SsSheet;
import org.ssio.spi.developerexternal.abstractsheet.model.SsWorkbook;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;

public class Base64CsvWorkbook implements SsWorkbook {

    private CSVPrinter csvPrinterForOutput;
    private Base64CsvSheet sheet;

    private String charsetForSave;

    private Base64CsvWorkbook() {

    }

    /**
     * create an empty workbook to write out to somewhere
     *
     * @param cellSeparator
     * @return
     */
    public static Base64CsvWorkbook createNewWorkbook(char cellSeparator, String charsetForSave) {
        Base64CsvWorkbook workbook = new Base64CsvWorkbook();

        StringWriter out = new StringWriter();
        try {
            CSVFormat csvFormat = getCsvFormat(cellSeparator);
            workbook.csvPrinterForOutput = new CSVPrinter(out, csvFormat);
        } catch (IOException e) {
            //shouldn't happen
            throw new IllegalStateException(e);
        }

        workbook.charsetForSave = charsetForSave;
        return workbook;
    }

    public static Base64CsvWorkbook createFromInput(Reader reader, char cellSeparator) throws IOException {
        Base64CsvWorkbook workbook = new Base64CsvWorkbook();
        Iterable<CSVRecord> records = getCsvFormat(cellSeparator).parse(reader);
        Base64CsvSheet sheet = Base64CsvSheet.createSheetFromAcsRecords(records);
        workbook.sheet = sheet;
        return workbook;
    }

    private static CSVFormat getCsvFormat(char cellSeparator) {
        return CSVFormat.EXCEL.withDelimiter(cellSeparator);
    }

    @Override
    public SsSheet createNewSheet() {
        this.sheet = Base64CsvSheet.createEmptySheet();
        return this.sheet;
    }

    @Override
    public void write(OutputStream outputTarget) throws IOException {
        sheet.acceptPrinting(this.csvPrinterForOutput);

        StringWriter out = (StringWriter) this.csvPrinterForOutput.getOut();
        StringReader reader = new StringReader(out.toString());
        IOUtils.copy(reader, outputTarget, charsetForSave);
    }

    @Override
    public SsSheet getSheetToParse() {
        return this.sheet;
    }


    @Override
    public SsSheet getSheetByName(String sheetName) {
        return this.sheet;
    }

    @Override
    public SsSheet getSheetAt(int sheetIndex) {
        return this.sheet;
    }
}
