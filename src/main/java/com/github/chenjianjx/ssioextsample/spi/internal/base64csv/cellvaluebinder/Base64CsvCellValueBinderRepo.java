package com.github.chenjianjx.ssioextsample.spi.internal.base64csv.cellvaluebinder;

import com.github.chenjianjx.ssioextsample.spi.internal.base64csv.cellvaluebinder.bytype.BigDecimalBase64CsvCellValueBinder;
import com.github.chenjianjx.ssioextsample.spi.internal.base64csv.cellvaluebinder.bytype.BigIntegerBase64CsvCellValueBinder;
import com.github.chenjianjx.ssioextsample.spi.internal.base64csv.cellvaluebinder.bytype.BooleanBase64CsvCellValueBinder;
import com.github.chenjianjx.ssioextsample.spi.internal.base64csv.cellvaluebinder.bytype.DateBase64CsvCellValueBinder;
import com.github.chenjianjx.ssioextsample.spi.internal.base64csv.cellvaluebinder.bytype.DoubleBase64CsvCellValueBinder;
import com.github.chenjianjx.ssioextsample.spi.internal.base64csv.cellvaluebinder.bytype.EnumBase64CsvCellValueBinder;
import com.github.chenjianjx.ssioextsample.spi.internal.base64csv.cellvaluebinder.bytype.FloatBase64CsvCellValueBinder;
import com.github.chenjianjx.ssioextsample.spi.internal.base64csv.cellvaluebinder.bytype.IntegerBase64CsvCellValueBinder;
import com.github.chenjianjx.ssioextsample.spi.internal.base64csv.cellvaluebinder.bytype.LocalDateBase64CsvCellValueBinder;
import com.github.chenjianjx.ssioextsample.spi.internal.base64csv.cellvaluebinder.bytype.LocalDateTimeBase64CsvCellValueBinder;
import com.github.chenjianjx.ssioextsample.spi.internal.base64csv.cellvaluebinder.bytype.LongBase64CsvCellValueBinder;
import com.github.chenjianjx.ssioextsample.spi.internal.base64csv.cellvaluebinder.bytype.PrimitiveBooleanBase64CsvCellValueBinder;
import com.github.chenjianjx.ssioextsample.spi.internal.base64csv.cellvaluebinder.bytype.PrimitiveDoubleBase64CsvCellValueBinder;
import com.github.chenjianjx.ssioextsample.spi.internal.base64csv.cellvaluebinder.bytype.PrimitiveFloatBase64CsvCellValueBinder;
import com.github.chenjianjx.ssioextsample.spi.internal.base64csv.cellvaluebinder.bytype.PrimitiveIntBase64CsvCellValueBinder;
import com.github.chenjianjx.ssioextsample.spi.internal.base64csv.cellvaluebinder.bytype.PrimitiveLongBase64CsvCellValueBinder;
import com.github.chenjianjx.ssioextsample.spi.internal.base64csv.cellvaluebinder.bytype.PrimitiveShortBase64CsvCellValueBinder;
import com.github.chenjianjx.ssioextsample.spi.internal.base64csv.cellvaluebinder.bytype.ShortBase64CsvCellValueBinder;
import com.github.chenjianjx.ssioextsample.spi.internal.base64csv.cellvaluebinder.bytype.StringBase64CsvCellValueBinder;
import org.ssio.api.external.typing.SimpleTypeEnum;
import org.ssio.util.lang.SsioReflectionUtils;

import java.util.LinkedHashMap;
import java.util.Map;


public final class Base64CsvCellValueBinderRepo {
    private static Map<SimpleTypeEnum, Class<? extends Base64CsvCellValueBinder>> javaTypeToBinderType = new LinkedHashMap<>();


    static {
        javaTypeToBinderType.put(SimpleTypeEnum.PrimitiveBoolean, PrimitiveBooleanBase64CsvCellValueBinder.class);
        javaTypeToBinderType.put(SimpleTypeEnum.PrimitiveShort, PrimitiveShortBase64CsvCellValueBinder.class);
        javaTypeToBinderType.put(SimpleTypeEnum.PrimitiveInt, PrimitiveIntBase64CsvCellValueBinder.class);
        javaTypeToBinderType.put(SimpleTypeEnum.PrimitiveLong, PrimitiveLongBase64CsvCellValueBinder.class);
        javaTypeToBinderType.put(SimpleTypeEnum.PrimitiveFloat, PrimitiveFloatBase64CsvCellValueBinder.class);
        javaTypeToBinderType.put(SimpleTypeEnum.PrimitiveDouble, PrimitiveDoubleBase64CsvCellValueBinder.class);

        javaTypeToBinderType.put(SimpleTypeEnum.Boolean, BooleanBase64CsvCellValueBinder.class);
        javaTypeToBinderType.put(SimpleTypeEnum.Short, ShortBase64CsvCellValueBinder.class);
        javaTypeToBinderType.put(SimpleTypeEnum.Integer, IntegerBase64CsvCellValueBinder.class);
        javaTypeToBinderType.put(SimpleTypeEnum.Long, LongBase64CsvCellValueBinder.class);
        javaTypeToBinderType.put(SimpleTypeEnum.Float, FloatBase64CsvCellValueBinder.class);
        javaTypeToBinderType.put(SimpleTypeEnum.Double, DoubleBase64CsvCellValueBinder.class);

        javaTypeToBinderType.put(SimpleTypeEnum.BigInteger, BigIntegerBase64CsvCellValueBinder.class);
        javaTypeToBinderType.put(SimpleTypeEnum.BigDecimal, BigDecimalBase64CsvCellValueBinder.class);


        javaTypeToBinderType.put(SimpleTypeEnum.Date, DateBase64CsvCellValueBinder.class);
        javaTypeToBinderType.put(SimpleTypeEnum.LocalDate, LocalDateBase64CsvCellValueBinder.class);
        javaTypeToBinderType.put(SimpleTypeEnum.LocalDateTime, LocalDateTimeBase64CsvCellValueBinder.class);

        javaTypeToBinderType.put(SimpleTypeEnum.String, StringBase64CsvCellValueBinder.class);

        javaTypeToBinderType.put(SimpleTypeEnum.Enum, EnumBase64CsvCellValueBinder.class);
    }

    public static Base64CsvCellValueBinder getCellValueBinder(SimpleTypeEnum javaType, Class<Enum<?>> enumClassIfEnum) {
        Class<? extends Base64CsvCellValueBinder> binderType = javaTypeToBinderType.get(javaType);
        if (binderType == null) {
            return null;
        }
        if (binderType.equals(EnumBase64CsvCellValueBinder.class)) {
            return new EnumBase64CsvCellValueBinder(enumClassIfEnum);
        } else {
            return SsioReflectionUtils.createInstance(binderType);
        }
    }
}
