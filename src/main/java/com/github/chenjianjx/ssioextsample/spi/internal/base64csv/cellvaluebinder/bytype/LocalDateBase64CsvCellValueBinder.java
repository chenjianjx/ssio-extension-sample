package com.github.chenjianjx.ssioextsample.spi.internal.base64csv.cellvaluebinder.bytype;

import com.github.chenjianjx.ssioextsample.spi.internal.base64csv.cellvaluebinder.Base64CsvCellValueBinder;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.github.chenjianjx.ssioextsample.util.MyBase64Utils.fromBase64Text;
import static com.github.chenjianjx.ssioextsample.util.MyBase64Utils.toBase64Text;

public class LocalDateBase64CsvCellValueBinder extends Base64CsvCellValueBinder {

    @Override
    protected String convertNonNullValueToCellText(String format, Object value) {
        LocalDate date = (LocalDate) value;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return toBase64Text(formatter.format(date));
    }

    @Override
    protected Object parseFromCellText(String format, String text) {
        String string = StringUtils.trimToNull(text);
        string = fromBase64Text(string);
        return string == null ? null : parseLocalDate(string, format);
    }

    private LocalDate parseLocalDate(String string, String format) {
        return LocalDate.parse(string, DateTimeFormatter.ofPattern(format));
    }

}
