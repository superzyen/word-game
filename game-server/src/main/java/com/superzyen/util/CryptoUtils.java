package com.superzyen.util;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.stereotype.Component;

@Component
public class CryptoUtils {

    private static BasicPasswordEncryptor basicPasswordEncryptor =new BasicPasswordEncryptor();
    private static StrongPasswordEncryptor strongPasswordEncryptor = new StrongPasswordEncryptor();

    /**
     * 随机盐值
     */
    public static String md5(String val) {
        return basicPasswordEncryptor.encryptPassword(val);
    }

    /**
     * 加密
     */
    public static String encrypt(String val){
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        //加密配置
        EnvironmentStringPBEConfig config = new EnvironmentStringPBEConfig();
        config.setAlgorithm("PBEWithMD5AndDES");
        //自己在用的时候更改此密码
        config.setPassword("superzyen");
        //应用配置
        encryptor.setConfig(config);
        //加密
        String ciphertext=encryptor.encrypt(val);
        return ciphertext;
    }

    /**
     * 解密
     */
    public static String decrypt(String val){
        //加密工具
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        //加密配置
        EnvironmentStringPBEConfig config = new EnvironmentStringPBEConfig();
        config.setAlgorithm("PBEWithMD5AndDES");
        //自己在用的时候更改此密码
        config.setPassword("superzyen");
        //应用配置
        encryptor.setConfig(config);
        //解密
        String plaintext=encryptor.decrypt(val);
        return plaintext;
    }
}
