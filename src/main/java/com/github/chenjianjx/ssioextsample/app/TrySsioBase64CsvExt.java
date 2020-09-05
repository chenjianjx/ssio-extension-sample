package com.github.chenjianjx.ssioextsample.app;

import com.github.chenjianjx.ssioextsample.spi.clientexternal.base64csv.Base64SpiConstants;
import com.github.chenjianjx.ssioextsample.spi.clientexternal.base64csv.parse.Base64CsvParseParamBuilder;
import com.github.chenjianjx.ssioextsample.spi.clientexternal.base64csv.save.Base64CsvSaveParamBuilder;
import com.github.chenjianjx.ssioextsample.spi.internal.base64csv.factory.Base64CsvWorkbookFactory;
import org.apache.commons.io.FileUtils;
import org.ssio.api.external.SsioManager;
import org.ssio.api.external.parse.ParseParam;
import org.ssio.api.external.parse.ParseResult;
import org.ssio.api.external.save.SaveParam;
import org.ssio.api.external.save.SaveResult;
import org.ssio.api.factory.SsioManagerFactory;
import org.ssio.spi.developerexternal.abstractsheet.factory.SsWorkbookFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class TrySsioBase64CsvExt {

    public static void main(String[] args) throws IOException {


        //register this spi and get a manager instance
        Map<String, SsWorkbookFactory> extraWorkbookFactories = new LinkedHashMap<>();
        extraWorkbookFactories.put(Base64SpiConstants.BASE64_CSV_FILE_TYPE, new Base64CsvWorkbookFactory());
        SsioManager manager = SsioManagerFactory.newInstance(extraWorkbookFactories);

        //bean saving
        Collection<MyBean> beans = Arrays.asList(
                MyBeanFactory.allEmpty(),
                MyBeanFactory.normalValues(),
                MyBeanFactory.bigValues());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        SaveParam<MyBean> savdParam =
                new Base64CsvSaveParamBuilder<MyBean>()
                        .setBeanClass(MyBean.class)
                        .setBeans(beans)
                        .setOutputTarget(outputStream)
                        .setOutputCharset("utf8")
                        .build();

        SaveResult saveResult = manager.save(savdParam);
        System.out.println(saveResult);


        byte[] spreadsheet = outputStream.toByteArray();
        File csvFile = Files.createTempFile("TrySsioBase64CsvExt", ".csv").toFile();
        FileUtils.writeByteArrayToFile(csvFile, spreadsheet);
        System.out.println("Beans saved as " + csvFile);


        //parse it back
        try (FileInputStream input = new FileInputStream(csvFile)) {
            ParseParam<MyBean> parseParam =
                    new Base64CsvParseParamBuilder<MyBean>()
                            .setBeanClass(MyBean.class)
                            .setSpreadsheetInput(input)
                            .setInputCharset("utf8")
                            .build();

            ParseResult<MyBean> parseResult = manager.parse(parseParam);
            parseResult.getBeans().forEach(b -> System.out.println(b));
        }
    }


}
