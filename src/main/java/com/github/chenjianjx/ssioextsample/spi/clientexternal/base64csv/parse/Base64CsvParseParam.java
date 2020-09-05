package com.github.chenjianjx.ssioextsample.spi.clientexternal.base64csv.parse;

import com.github.chenjianjx.ssioextsample.spi.clientexternal.base64csv.Base64SpiConstants;
import org.ssio.api.external.parse.ParseParam;
import org.ssio.api.external.parse.PropFromColumnMappingMode;

import java.io.InputStream;

public class Base64CsvParseParam<BEAN> extends ParseParam<BEAN> {

    private String inputCharset;

    private char cellSeparator;

    /**
     * Please the builder to build it
     */
    protected Base64CsvParseParam(Class<BEAN> beanClass, PropFromColumnMappingMode propFromColumnMappingMode, InputStream spreadsheetInput, boolean sheetHasHeader,
                                  String inputCharset, char cellSeparator) {
        super(beanClass, propFromColumnMappingMode, spreadsheetInput, sheetHasHeader);
        this.inputCharset = inputCharset;
        this.cellSeparator = cellSeparator;
    }

    public char getCellSeparator() {
        return cellSeparator;
    }

    public String getInputCharset() {
        return inputCharset;
    }


    @Override
    public String getSpreadsheetFileType() {
        return Base64SpiConstants.BASE64_CSV_FILE_TYPE;
    }
}
