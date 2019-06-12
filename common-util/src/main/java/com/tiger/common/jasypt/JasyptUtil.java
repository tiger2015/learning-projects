package com.tiger.common.jasypt;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class JasyptUtil {

    private final static StandardPBEStringEncryptor encryptor=new StandardPBEStringEncryptor();
    static {
        encryptor.setAlgorithm("PBEWithMD5AndDES");
        encryptor.setPassword("tiger");
    }

    public static String encrypt( String message){
        return encryptor.encrypt(message); // 会被初始化导致第二次设置密码时，出现错误
    }


    public static String decrypt(String encryptedMessage){
        return encryptor.decrypt(encryptedMessage);
    }

}
