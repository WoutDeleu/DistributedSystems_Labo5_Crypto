//https://docs.oracle.com/javase/7/docs/technotes/guides/security/crypto/CryptoSpec.html

import javax.crypto.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        Person person1 = new Person("Harry", "Geluveld", "0496742300");
        Person person2 = new Person("Barry", "Marke", "049651524");
        Person person1Fake = new Person("Garry", "Geluveld", "0496742300");

        // Preps
        byte[] person1_byteArray = person1.getBytes();
        byte[] person2_byteArray = person2.getBytes();
        byte[] person1Fake_byteArray = person1Fake.getBytes();


        // Hashing
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
        // AES: 128, 192, and 256 bits to encrypt and decrypt data in blocks of 128 bits
//        KeyGenerator keygen = KeyGenerator.getInstance("SHA-256");

    }
}

// Vragen:  Moet je altijd je gewenste algoritme meegeven?
//          Stel ene verandert van implementatie en jij hebt dit geïmplementeerd