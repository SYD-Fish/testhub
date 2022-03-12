package com.syd.tshub.common.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * 加解密工具类
 *
 * @ClassName AESUtil
 * @Description TODO
 * @Author syd
 * @Date 2022/1/5 11:30
 * @Version 1.0
 */
@Component
public class AESUtil {

    @Value("${security.key.seed: liuyuchen}")
    private String keySeed = "liuyuchen";

    private static Cipher cipher;
    private static KeyGenerator kgen;
    private static Base64.Encoder encoder = Base64.getEncoder();
    private static Base64.Decoder decoder = Base64.getDecoder();

    static {
        try {
            cipher = Cipher.getInstance("AES");
            kgen = KeyGenerator.getInstance("AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }

    public String encode(String value) throws Exception {
        byte[] bytes = aesEncryptToBytes(value, keySeed);
        return new String(encoder.encode(bytes));
    }

    public String decode(String secretContent) throws Exception {
        byte[] bytes = decoder.decode(secretContent);
        String value = aesDecryptByBytes(bytes, keySeed);
        return value;
    }

    /**
     * AES加密
     * @param content 待加密的内容
     * @param encryptKey 加密密钥
     * @return 加密后的byte[]
     * @throws Exception
     */
    private byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception {
        kgen.init(128, new SecureRandom(encryptKey.getBytes()));
//        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));
        return cipher.doFinal(content.getBytes("utf-8"));
    }

    /**
     * AES解密
     * @param encryptBytes 待解密的byte[]
     * @param decryptKey 解密密钥
     * @return 解密后的String
     * @throws Exception
     */
    private String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {
        kgen.init(128, new SecureRandom(decryptKey.getBytes()));
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        return new String(decryptBytes, "utf-8");
    }


    public static void main(String[] args) throws Exception {
        AESUtil aesUtil = new AESUtil();
        String encode = aesUtil.encode("qwerasdf");
        System.out.println("encode: " + encode);
        String decode = aesUtil.decode(encode);
        System.out.println("decode: " + decode);
    }

}
