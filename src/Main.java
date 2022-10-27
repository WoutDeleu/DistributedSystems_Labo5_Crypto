//https://docs.oracle.com/javase/7/docs/technotes/guides/security/crypto/CryptoSpec.html

import javax.crypto.*;
import java.security.*;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Person person1 = new Person("Harry", "Geluveld", "0496742300");
        Person person2 = new Person("Barry", "Marke", "049651524");
        Person person1Fake = new Person("Garry", "Geluveld", "0496742300");

        // Preps
        byte[] person1_byteArray = person1.getBytes();
        byte[] person2_byteArray = person2.getBytes();
        byte[] person1Fake_byteArray = person1Fake.getBytes();
        System.out.println();


        // Hashing
        System.out.println("************* HASHING *************");
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        System.out.println("Original Person1");
        System.out.print(person1_byteArray);
        md.update(person1_byteArray);
        byte[] hash_person1 = md.digest();
        System.out.println();
        System.out.println("Hashed Person1");
        System.out.println(hash_person1);

        System.out.println();
        System.out.println("Original Person1Fake");
        System.out.print(person1Fake_byteArray);
        md.update(person1Fake_byteArray);
        byte[] hash_person1Fake = md.digest();
        System.out.println();
        System.out.println("Hashed Person1Fake");
        System.out.println(hash_person1Fake);


        // Symmetric Encryption
        System.out.println();
        System.out.println("************* SYM ENCR *************");
        // AES: 128, 192, and 256 bits to encrypt and decrypt data in blocks of 128 bits
        // AES: 512 -> 1024, (+64)
        System.out.println("Original Text");
        String textToEncrypt = "Franz Wilhelm Junghuhn (Mansfeld, 26 oktober 1809 – Lembang, 24 april 1864)";
        System.out.println();

        KeyGenerator keygen = KeyGenerator.getInstance("AES");
        SecretKey skey = keygen.generateKey();
        Cipher cipher = Cipher.getInstance("AES");

        System.out.println("Encrypted Text");
        cipher.init(Cipher.ENCRYPT_MODE, skey);
        byte[] encryptedBytes = cipher.doFinal(textToEncrypt.getBytes());
        String encryptedText = new String(encryptedBytes);
        System.out.println(encryptedText);
        System.out.println();

        System.out.println("Decrypted Text");
        cipher.init(Cipher.DECRYPT_MODE, skey);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        String decryptedText = new String(decryptedBytes);
        System.out.println(decryptedText);


        // Asymmetric Encryption
        System.out.println();
        System.out.println("************* ASYNC ENCR *************");

        KeyPairGenerator kpGen = KeyPairGenerator.getInstance("RSA");
        kpGen.initialize(1024);
        KeyPair keyPair = kpGen.generateKeyPair();

        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        System.out.println(publicKey);

        cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        System.out.println(encryptedBytes.length); //Lengte van de bytearray checken (meg niet langer zijn dan 117 bytes
        byte[] asymEncryptedText = cipher.doFinal(encryptedBytes);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] asymDecryptedText = cipher.doFinal(asymEncryptedText);
        System.out.println(asymDecryptedText);


    }
}

// Vragen:  Moet je altijd je gewenste algoritme meegeven?
//          Stel ene verandert van implementatie en jij hebt dit geïmplementeerd