package com.github.chenjianjx.ssioextsample.spi.internal.base64csv.cellvaluebinder.bytype;

import com.github.chenjianjx.ssioextsample.spi.internal.base64csv.cellvaluebinder.Base64CsvCellValueBinder;
import org.apache.commons.lang3.StringUtils;

import static com.github.chenjianjx.ssioextsample.spi.util.MyBase64Utils.fromBase64Text;
import static com.github.chenjianjx.ssioextsample.spi.util.MyBase64Utils.toBase64Text;

public class IntegerBase64CsvCellValueBinder extends Base64CsvCellValueBinder {

    @Override
    protected String convertNonNullValueToCellText(String format, Object value) {
        return toBase64Text(value.toString());
    }

    @Override
    protected Object parseFromCellText(String format, String text) {
        String string = StringUtils.trimToNull(text);
        string = fromBase64Text(string);
        return string == null ? null : Integer.parseInt(string);
    }
}
