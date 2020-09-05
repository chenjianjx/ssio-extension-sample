package com.github.chenjianjx.ssioextsample.spi.internal.base64csv.cellvaluebinder.bytype;

import com.github.chenjianjx.ssioextsample.spi.internal.base64csv.cellvaluebinder.Base64CsvCellValueBinder;
import org.apache.commons.lang3.StringUtils;

import static com.github.chenjianjx.ssioextsample.util.MyBase64Utils.fromBase64Text;
import static com.github.chenjianjx.ssioextsample.util.MyBase64Utils.toBase64Text;

public class StringBase64CsvCellValueBinder extends Base64CsvCellValueBinder {

    @Override
    protected String convertNonNullValueToCellText(String format, Object value) {
        return toBase64Text(value.toString());
    }

    @Override
    protected Object parseFromCellText(String format, String text) {
        if (StringUtils.isEmpty(text)) {
            return null;  // "" should be treated as null
        } else {
            String string = StringUtils.trimToNull(text);
            return fromBase64Text(string);
        }

    }
}
