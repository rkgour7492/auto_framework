package com.gk.test.framework.helpers.utils;

import java.util.Base64;

public class Base64Decoder {

    public static String decodeBase64ImageToUrl(String base64Code) {
        byte[] decodedURLBytes = Base64.getDecoder().decode(base64Code);
        return new String(decodedURLBytes);
    }
}
