package com.github.chenjianjx.ssioextsample.spi.internal.base64csv.cellvaluebinder.bytype;

import com.github.chenjianjx.ssioextsample.spi.internal.base64csv.cellvaluebinder.Base64CsvCellValueBinder;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.Date;

import static com.github.chenjianjx.ssioextsample.spi.util.MyBase64Utils.fromBase64Text;
import static com.github.chenjianjx.ssioextsample.spi.util.MyBase64Utils.toBase64Text;

public class DateBase64CsvCellValueBinder extends Base64CsvCellValueBinder {

    @Override
    protected String convertNonNullValueToCellText(String format, Object value) {
        Date date = (Date) value;
        return toBase64Text(DateFormatUtils.format(date, format));
    }

    @Override
    protected Object parseFromCellText(String format, String text) {
        String string = StringUtils.trimToNull(text);
        string = fromBase64Text(string);
        return string == null ? null : parseDate(string, format);
    }

    private Date parseDate(String string, String format) {
        try {
            return DateUtils.parseDate(string, format);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
