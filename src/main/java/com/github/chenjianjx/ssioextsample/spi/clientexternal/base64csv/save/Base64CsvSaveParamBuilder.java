package com.github.chenjianjx.ssioextsample.spi.clientexternal.base64csv.save;

import com.github.chenjianjx.ssioextsample.spi.clientexternal.base64csv.Base64SpiConstants;
import org.ssio.api.external.save.DatumError;
import org.ssio.api.external.save.SaveParamBuilder;

import java.io.OutputStream;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

public class Base64CsvSaveParamBuilder<BEAN> extends SaveParamBuilder<BEAN, Base64CsvSaveParamBuilder<BEAN>> {

    private String outputCharset;
    private char cellSeparator = Base64SpiConstants.DEFAULT_CSV_CELL_SEPARATOR;

    /**
     * required for csv, ignored by office-like spreadsheet
     *
     * @return
     */
    public Base64CsvSaveParamBuilder<BEAN> setOutputCharset(String outputCharset) {
        this.outputCharset = outputCharset;
        return this;
    }

    /**
     * Only used for CSV.  Default is ","
     */
    public Base64CsvSaveParamBuilder<BEAN> setCellSeparator(char cellSeparator) {
        this.cellSeparator = cellSeparator;
        return this;
    }

    @Override
    protected void fileTypeSpecificValidate(List<String> errors) {
        if (outputCharset == null) {
            errors.add("For CSV output the outputCharset is required");
        }
    }

    @Override
    protected Base64CsvSaveParam<BEAN> fileTypeSpecificBuild(Collection<BEAN> beans, Class<BEAN> beanClass, OutputStream outputTarget, boolean createHeader, boolean stillSaveIfDataError, Function<DatumError, String> datumErrDisplayFunction) {
        return new Base64CsvSaveParam<>(beans, beanClass, outputTarget, createHeader, stillSaveIfDataError, datumErrDisplayFunction, cellSeparator, outputCharset);
    }
}
