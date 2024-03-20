package com.swapit.commons.encryption;

public interface EncryptionService {
    String encrypt(String data) throws Exception;
    String decrypt(String encryptedData) throws Exception;
}
