package com.example.cms.utils;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.cms.exceptions.ServiceException;

public class AESUtil {

	private static final String SECRET_KEY = "MySecretKey12345"; // 16-char key (128-bit)
    private static final String INIT_VECTOR = "RandomInitVector"; // 16-char IV
    
    private static final Logger LOGGER = LoggerFactory.getLogger(AESUtil.class);
    
    private AESUtil() {
        throw new IllegalStateException("AESUtil class");
      }

    public static String encrypt(String value) {
        try {
            IvParameterSpec iv = new IvParameterSpec(INIT_VECTOR.getBytes());
            SecretKeySpec skeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING"); // Secure mode
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception ex) {
        	LOGGER.error("Issue occured while encryption {}", ex.getMessage());
            throw new ServiceException("500", 500, "Encryption error");
        }
    }
}
