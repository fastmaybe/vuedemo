package com.example.vuedemo.util;

import javax.crypto.Cipher;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RSAUtil {

    /** *//**
     * 加密算法RSA
     */
    public static final String KEY_ALGORITHM = "RSA";

    /** *//**
     * 签名算法
     */
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

    /** *//**
     * 获取公钥的key
     */
    private static final String PUBLIC_KEY = "RSAPublicKey";

    /** *//**
     * 获取私钥的key
     */
    private static final String PRIVATE_KEY = "RSAPrivateKey";

    /** *//**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /** *//**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;

    public static Map<String, Object> genKeyPair() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(1024);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        Map<String, Object> keyMap = new HashMap<String, Object>(2);
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(UUID.fromString("a"));


    }

    /**
     * RSA公钥解密
     * @param keyStr
     * @param isPublicKey
     * @param msgStr
     * @return
     */
    public static String decrypt(String keyStr, boolean isPublicKey, String msgStr) {
        return crypt(keyStr, isPublicKey, Cipher.DECRYPT_MODE, msgStr);
    }

    /**
     * RSA公钥加密
     * @param keyStr
     * @param isPublicKey
     * @param msgStr
     * @return
     */
    public static String encrypt(String keyStr, boolean isPublicKey, String msgStr) {
        return crypt(keyStr, isPublicKey, Cipher.ENCRYPT_MODE,msgStr);
    }

    private static String crypt(String keyStr, boolean isPublicKey, int opmode, String msgStr) {
        String result = "";
        try {
            byte[] keyBytes = BytesTranslate.hexToBytes(keyStr);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            Key key = null;
            if(isPublicKey) {
                key = keyFactory.generatePublic(new X509EncodedKeySpec(keyBytes));
            }else {
                key = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(keyBytes));
            }
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(opmode, key);
            byte[] cipherTextBytes = cipher.doFinal(BytesTranslate.hexToBytes(msgStr));
            result = new String(cipherTextBytes,"utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


}
