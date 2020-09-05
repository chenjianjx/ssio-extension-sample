package com.github.chenjianjx.ssioextsample.spi.internal.base64csv.cellvaluebinder.bytype;

import com.github.chenjianjx.ssioextsample.spi.internal.base64csv.cellvaluebinder.Base64CsvCellValueBinder;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

import static com.github.chenjianjx.ssioextsample.spi.util.MyBase64Utils.fromBase64Text;
import static com.github.chenjianjx.ssioextsample.spi.util.MyBase64Utils.toBase64Text;

public class BigDecimalBase64CsvCellValueBinder extends Base64CsvCellValueBinder {

    @Override
    protected String convertNonNullValueToCellText(String format, Object value) {
        return toBase64Text(value.toString());
    }

    @Override
    protected Object parseFromCellText(String format, String text) {
        String string = fromBase64Text(StringUtils.trimToNull(text));
        return string == null ? null : new BigDecimal(string);
    }
}
