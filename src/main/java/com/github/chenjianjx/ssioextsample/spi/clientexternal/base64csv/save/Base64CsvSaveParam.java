package com.github.chenjianjx.ssioextsample.spi.clientexternal.base64csv.save;

import com.github.chenjianjx.ssioextsample.spi.clientexternal.base64csv.Base64SpiConstants;
import org.ssio.api.external.save.DatumError;
import org.ssio.api.external.save.SaveParam;

import java.io.OutputStream;
import java.util.Collection;
import java.util.function.Function;

public class Base64CsvSaveParam<BEAN> extends SaveParam<BEAN> {

    private char cellSeparator;

    private String outputCharset;

    /**
     * Please use the builder to create an instance
     */
    protected Base64CsvSaveParam(Collection<BEAN> beans, Class<BEAN> beanClass, OutputStream outputTarget,
                                 boolean createHeader, boolean stillSaveIfDataError, Function<DatumError, String> datumErrDisplayFunction,
                                 char cellSeparator, String outputCharset) {
        super(beans, beanClass, outputTarget, createHeader, stillSaveIfDataError, datumErrDisplayFunction);
        this.cellSeparator = cellSeparator;
        this.outputCharset = outputCharset;
    }

    public char getCellSeparator() {
        return cellSeparator;
    }

    public String getOutputCharset() {
        return outputCharset;
    }

    @Override
    public String getSpreadsheetFileType() {
        return Base64SpiConstants.BASE64_CSV_FILE_TYPE;
    }
}
