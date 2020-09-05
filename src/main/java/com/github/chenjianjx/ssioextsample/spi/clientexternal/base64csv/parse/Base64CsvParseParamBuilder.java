package com.github.chenjianjx.ssioextsample.spi.clientexternal.base64csv.parse;

import com.github.chenjianjx.ssioextsample.spi.clientexternal.base64csv.Base64SpiConstants;
import org.ssio.api.external.parse.ParseParamBuilder;
import org.ssio.api.external.parse.PropFromColumnMappingMode;

import java.io.InputStream;
import java.util.List;

public class Base64CsvParseParamBuilder<BEAN> extends ParseParamBuilder<BEAN, Base64CsvParseParamBuilder<BEAN>> {
    private String inputCharset;
    private char cellSeparator = Base64SpiConstants.DEFAULT_CSV_CELL_SEPARATOR;


    public Base64CsvParseParamBuilder<BEAN> setInputCharset(String inputCharset) {
        this.inputCharset = inputCharset;
        return this;
    }

    /**
     * Default is ","
     */
    public Base64CsvParseParamBuilder<BEAN> setCellSeparator(char cellSeparator) {
        this.cellSeparator = cellSeparator;
        return this;
    }

    @Override
    protected Base64CsvParseParam fileTypeSpecificBuild(Class<BEAN> beanClass, PropFromColumnMappingMode propFromColumnMappingMode, InputStream spreadsheetInput, boolean sheetHasHeader) {
        return new Base64CsvParseParam(beanClass, propFromColumnMappingMode, spreadsheetInput, sheetHasHeader, inputCharset, cellSeparator);
    }

    @Override
    protected void fileTypeSpecificValidate(List<String> errors) {
        if (inputCharset == null) {
            errors.add("For CSV input the inputCharset is required");
        }
    }
}
