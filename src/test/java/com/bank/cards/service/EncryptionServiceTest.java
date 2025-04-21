package com.bank.cards.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

public class EncryptionServiceTest {

    private EncryptionService encryptionService;

    @BeforeEach
    void setUp() {
        encryptionService = new EncryptionService();
        ReflectionTestUtils.setField(encryptionService, "secretKey", "1234567890123456");
        encryptionService.init();
    }

    @Test
    void testEncryptAndDecrypt() throws Exception {
        String original = "4111111111111111";

        String encrypted = encryptionService.encrypt(original);
        Assertions.assertNotNull(encrypted);
        Assertions.assertNotEquals(original, encrypted);

        String decrypted = encryptionService.decrypt(encrypted);
        Assertions.assertEquals(original, decrypted);
    }

    @Test
    void testDifferentInputProducesDifferentEncryptedValues() throws Exception {
        String card1 = "4111111111111111";
        String card2 = "5500000000000004";

        String encrypted1 = encryptionService.encrypt(card1);
        String encrypted2 = encryptionService.encrypt(card2);

        Assertions.assertNotEquals(encrypted1, encrypted2);
    }

    @Test
    void testSameInputProducesSameOutput() throws Exception {
        String card = "4111111111111111";

        String encrypted1 = encryptionService.encrypt(card);
        String encrypted2 = encryptionService.encrypt(card);

        Assertions.assertEquals(encrypted1, encrypted2);
    }
}
