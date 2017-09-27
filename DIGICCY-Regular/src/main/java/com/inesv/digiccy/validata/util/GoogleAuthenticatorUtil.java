package com.inesv.digiccy.validata.util;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Base64;

import com.inesv.digiccy.api.utils.PropertiesUtil;

public class GoogleAuthenticatorUtil {

    public static final int     SECRET_SIZE             = 10;

    public static final String  SEED                    = "g8GjEvTbW5oVSV7avLBdwIHqGlUYNzKFI7izOF8GwLDVKs2m0QN7vxRs2im5MDaNCWGmcD2rvcZx";

    public static final String  RANDOM_NUMBER_ALGORITHM = "SHA1PRNG";

    private static final String format                  = PropertiesUtil.getPropertiesValue("regular.properties", "format");

    int                         window_size             = 3;                                                                             // default 3 - max 17 (from google docs)最多可偏移的时间


    public void setWindowSize(int s) {
        if (s >= 1 && s <= 17) window_size = s;
    }

    /**
     * 
     * @param codes 用户的动态秘钥
     * @param savedSecret 生成的秘钥key
     * @return
     */
    //校验秘钥
    public static Boolean authcode(String codes, String savedSecret) {
        long code = Long.parseLong(codes);
        long t = System.currentTimeMillis();
        GoogleAuthenticatorUtil ga = new GoogleAuthenticatorUtil();
        ga.setWindowSize(15);
        boolean r = ga.check_code(savedSecret, code, t);
        return r;
    }

    /**
     * 
     * @param username 用户账号
     * @return 秘钥key和二维码的url
     */
    //获取验证的秘钥和二维码的url
    public static String[] genSecret(String username) {
        String secret = GoogleAuthenticatorUtil.generateSecretKey();
        String url = com.inesv.digiccy.validata.util.GoogleAuthenticatorUtil.getQRBarcodeURL(username, "inesv", secret);
        String[] result = { secret, url };
        return result;
    }

    public static String generateSecretKey() {
        SecureRandom sr = null;
        try {
            sr = SecureRandom.getInstance(RANDOM_NUMBER_ALGORITHM);
            sr.setSeed(Base64.decodeBase64(SEED));
            byte[] buffer = sr.generateSeed(SECRET_SIZE);
            Base32 codec = new Base32();
            byte[] bEncodedKey = codec.encode(buffer);
            String encodedKey = new String(bEncodedKey);
            return encodedKey;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getQRBarcodeURL(String user, String host, String secret) {
        return String.format(format, user, host, secret);
    }

    public boolean check_code(String secret, long code, long timeMsec) {
        Base32 codec = new Base32();
        byte[] decodedKey = codec.decode(secret);
        long t = (timeMsec / 1000L) / 30L;
        for (int i = -window_size; i <= window_size; ++i) {
            long hash;
            try {
                hash = verify_code(decodedKey, t + i);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            }
            if (hash == code) { return true; }
        }
        return false;
    }

    private static int verify_code(byte[] key, long t) throws NoSuchAlgorithmException, InvalidKeyException {
        byte[] data = new byte[8];
        long value = t;
        for (int i = 8; i-- > 0; value >>>= 8) {
            data[i] = (byte) value;
        }
        SecretKeySpec signKey = new SecretKeySpec(key, "HmacSHA1");
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(signKey);
        byte[] hash = mac.doFinal(data);
        int offset = hash[20 - 1] & 0xF;
        long truncatedHash = 0;
        for (int i = 0; i < 4; ++i) {
            truncatedHash <<= 8;
            truncatedHash |= (hash[offset + i] & 0xFF);
        }
        truncatedHash &= 0x7FFFFFFF;
        truncatedHash %= 1000000;
        return (int) truncatedHash;
    }
}
