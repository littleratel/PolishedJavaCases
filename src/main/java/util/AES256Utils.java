package util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;

public class AES256Utils {

    private AES256Utils() {
    }

    private static final String ALGORITHM = "AES";
    private static final String DEFAULT_CIPHER_TRANSFORMATION = "AES";

    /**
     * AES 256位加密
     *
     * @param data 需要加密的数据
     * @param key  AES 256位密钥, 密钥长度必须是16、24或32字节，分别对应于AES-128、AES-192和AES-256
     * @return 加密后的Base64编码字符串
     */
    public static String encrypt(String data, String key) throws Exception {
        Key secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), ALGORITHM);
        Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    /**
     * AES 256位解密
     *
     * @param encryptedData 加密后的Base64编码字符串
     * @param key           AES 256位密钥
     * @return 解密后的字符串
     */
    public static String decrypt(String encryptedData, String key) throws Exception {
        Key secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), ALGORITHM);
        Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }
}
