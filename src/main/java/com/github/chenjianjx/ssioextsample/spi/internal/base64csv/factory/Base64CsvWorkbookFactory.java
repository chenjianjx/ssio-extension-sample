package com.github.chenjianjx.ssioextsample.spi.internal.base64csv.factory;

import com.github.chenjianjx.ssioextsample.spi.clientexternal.base64csv.parse.Base64CsvParseParam;
import com.github.chenjianjx.ssioextsample.spi.clientexternal.base64csv.save.Base64CsvSaveParam;
import com.github.chenjianjx.ssioextsample.spi.internal.base64csv.model.Base64CsvWorkbook;
import org.ssio.api.external.parse.ParseParam;
import org.ssio.api.external.save.SaveParam;
import org.ssio.spi.developerexternal.abstractsheet.factory.SsWorkbookFactory;

import java.io.IOException;
import java.io.InputStreamReader;

public class Base64CsvWorkbookFactory implements SsWorkbookFactory<Base64CsvWorkbook> {


    @Override
    public Base64CsvWorkbook newWorkbookForSave(SaveParam p) {
        Base64CsvSaveParam param = (Base64CsvSaveParam) p;
        return Base64CsvWorkbook.createNewWorkbook(param.getCellSeparator(), param.getOutputCharset());
    }

    @Override
    public Base64CsvWorkbook loadWorkbookToParse(ParseParam p) throws IOException {
        Base64CsvParseParam param = (Base64CsvParseParam) p;
        return Base64CsvWorkbook.createFromInput(new InputStreamReader(param.getSpreadsheetInput(), param.getInputCharset()), param.getCellSeparator());
    }
}
