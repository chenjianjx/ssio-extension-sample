package com.github.chenjianjx.ssioextsample.spi.util;


import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class MyBase64Utils {

    public static String toBase64Text(String clearText) {
        if (clearText == null) {
            return null;
        }
        try {
            return Base64.getEncoder().encodeToString(clearText.getBytes("utf8"));
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }
    }

    public static String fromBase64Text(String base64) {
        if (base64 == null) {
            return null;
        }
        try {
            return new String(Base64.getDecoder().decode(base64), "utf8");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }
    }
}
