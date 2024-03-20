package com.swapit.commons.encryption;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.Base64;

@Service
@RequiredArgsConstructor
@Slf4j
public class EncryptionServiceImpl implements EncryptionService{
    private static final String ALGORITHM = "AES/CBC/PKCS5Padding"; // Use CBC mode and PKCS#5 Padding
    private final SecretKeySpec secretKeySpec;

    @Override
    public String encrypt(String data) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
        ByteBuffer byteBuffer = ByteBuffer.allocate(iv.length + encryptedBytes.length);
        byteBuffer.put(iv);
        byteBuffer.put(encryptedBytes);
        return Base64.getEncoder().encodeToString(byteBuffer.array());
    }

    @Override
    public String decrypt(String encryptedData) throws Exception {
        byte[] decodedData = Base64.getDecoder().decode(encryptedData);
        ByteBuffer byteBuffer = ByteBuffer.wrap(decodedData);
        byte[] iv = new byte[16];
        byteBuffer.get(iv);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        byte[] encryptedBytes = new byte[byteBuffer.remaining()];
        byteBuffer.get(encryptedBytes);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes);
    }
}
