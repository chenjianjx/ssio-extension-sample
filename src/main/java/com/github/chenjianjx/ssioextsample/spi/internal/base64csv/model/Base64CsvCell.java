package com.github.chenjianjx.ssioextsample.spi.internal.base64csv.model;

import com.github.chenjianjx.ssioextsample.spi.internal.base64csv.cellvaluebinder.Base64CsvCellValueBinderRepo;
import org.ssio.api.external.typing.SimpleTypeEnum;
import org.ssio.spi.developerexternal.abstractsheet.cellvaluebinder.SsCellValueBinder;
import org.ssio.spi.developerexternal.abstractsheet.model.SsCell;

public class Base64CsvCell implements SsCell {

    private String content;

    private Base64CsvCell() {

    }

    public static Base64CsvCell createEmptyCell() {
        Base64CsvCell cell = new Base64CsvCell();
        return cell;
    }

    public static Base64CsvCell createWithContent(String content) {
        Base64CsvCell cell = new Base64CsvCell();
        cell.content = content;
        return cell;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public SsCellValueBinder getCellValueBinder(SimpleTypeEnum javaType, Class<Enum<?>> enumClassIfEnum) {
        return Base64CsvCellValueBinderRepo.getCellValueBinder(javaType, enumClassIfEnum);
    }

    @Override
    public void styleAsError() {
        //do nothing
    }

    @Override
    public void styleAsHeader() {
        //do nothing
    }


}
