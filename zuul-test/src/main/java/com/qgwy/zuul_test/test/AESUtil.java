package com.qgwy.zuul_test.test;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
     * AES 加密方法，是对称的密码算法(加密与解密的密钥一致)，这里使用最大的 256 位的密钥
     */
    public class AESUtil {
        /**
         * 获得一个 密钥长度为 256 位的 AES 密钥，
         * @return 返回经 BASE64 处理之后的密钥字符串
         */
        public static String getStrKeyAES() throws NoSuchAlgorithmException, UnsupportedEncodingException {
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = new SecureRandom(String.valueOf(System.currentTimeMillis()).getBytes("utf-8"));
//            keyGen.init(256, secureRandom);   // 这里可以是 128、192、256、越大越安全
            keyGen.init(128, secureRandom);   // 这里可以是 128、192、256、越大越安全
            SecretKey secretKey = keyGen.generateKey();
            return Base64.getEncoder().encodeToString(secretKey.getEncoded());
        }

        /**
         *  将使用 Base64 加密后的字符串类型的 secretKey 转为 SecretKey
         * @param strKey
         * @return SecretKey
         */
        public static SecretKey strKey2SecretKey(String strKey){
            byte[] bytes = Base64.getDecoder().decode(strKey);
            SecretKeySpec secretKey = new SecretKeySpec(bytes, "AES");
            return secretKey;
        }

        /**
         * 加密
         * @param content 待加密内容
         * @param secretKey 加密使用的 AES 密钥
         * @return 加密后的密文 byte[]
         */
        public static byte[] encryptAES(byte[] content, SecretKey secretKey,String src) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
            Cipher cipher = Cipher.getInstance("AES");
            /*cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey,);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey,);*/
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(src.getBytes()));
            return cipher.doFinal(content);
        }

        /**
         * 解密
         * @param content 待解密内容
         * @param secretKey 解密使用的 AES 密钥
         * @return 解密后的明文 byte[]
         */
        public static byte[] decryptAES(byte[] content, SecretKey secretKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return cipher.doFinal(content);
        }

    public static void main(String[] args){
        //String content = "abcdefg789+-*+="; // 待加密的字符串
        //String content = "1360089680326509"; // 待加密的字符串
        String content = "{\"merchant_code\":\"6jdic7kc\",\"third_user_id\":\"123\",\"password\":\"1360089680326509\"},\"sign\":\"1d7a501da0627f682335c5be6293f978\"}"; // 待加密的字符串
        String salt="3901182040014755";
        System.out.println("明文数据为：" + content);
        try {
            // 获得经 BASE64 处理之后的 AES 密钥
            String strKeyAES = AESUtil.getStrKeyAES();
            System.out.println("经BASE64处理之后的密钥：" + strKeyAES);

            // 将 BASE64 处理之后的 AES 密钥转为 SecretKey
            SecretKey secretKey = AESUtil.strKey2SecretKey(strKeyAES);

            // 加密数据
            byte[] encryptAESbytes = AESUtil.encryptAES(content.getBytes("utf-8"), secretKey,salt);
            System.out.println("加密后的数据经 BASE64 处理之后为：" + Base64.getEncoder().encodeToString(encryptAESbytes));
            // 解密数据
            String decryptAESStr = new String(AESUtil.decryptAES(encryptAESbytes, secretKey), "utf-8");
            System.out.println("解密后的数据为：" + decryptAESStr);

            if (content.equals(decryptAESStr)){
                System.out.println("测试通过！");
            }else {
                System.out.println("测试未通过！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
