package com.github.chenjianjx.ssioextsample.spi.internal.base64csv.cellvaluebinder;


import org.ssio.spi.developerexternal.abstractsheet.cellvaluebinder.SsCellValueBinder;
import com.github.chenjianjx.ssioextsample.spi.internal.base64csv.model.Base64CsvCell;

public abstract class Base64CsvCellValueBinder implements SsCellValueBinder<Base64CsvCell> {

    @Override
    public void setNonNullValue(Base64CsvCell cell, String format, Object value) {
        cell.setContent(this.convertNonNullValueToCellText(format, value));
    }

    @Override
    public void setNullValue(Base64CsvCell cell) {
        cell.setContent(null);
    }

    @Override
    public Object getValue(Base64CsvCell cell, String format) {
        return this.parseFromCellText(format, cell.getContent());
    }

    protected abstract String convertNonNullValueToCellText(String format, Object value);


    /**
     * @return the java type of the value will be the curated type of the binder
     */
    protected abstract Object parseFromCellText(String format, String text);

}
