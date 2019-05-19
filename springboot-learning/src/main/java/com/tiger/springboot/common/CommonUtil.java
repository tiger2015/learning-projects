package com.tiger.springboot.common;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CommonUtil {
    private final static String[] hexDigits = {"0", "1", "2", "3", "4", "5", "6", "7",
            "8", "9", "a", "b", "c", "d", "e", "f"};
    private static MessageDigest messageDigest;

    static {
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static String md5Encode(String origin, String charsetName) {
        byte[] digest;
        if (charsetName == null || charsetName.length() == 0) {
            digest = messageDigest.digest(origin.getBytes());
        } else {
            digest = messageDigest.digest(origin.getBytes(Charset.forName(charsetName)));
        }
        return byteArrayToHexStr(digest);
    }


    public static String byteArrayToHexStr(byte[] bytes) {
        if (bytes == null) {
            return "";
        }
        StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            int low = bytes[i] & 0x0f;
            int heigh = (bytes[i] >> 4) & 0x0f;
            hexString.append(hexDigits[heigh]);
            hexString.append(hexDigits[low]);
        }
        return hexString.toString();
    }

}
