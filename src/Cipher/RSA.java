package Cipher;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Scanner;

/**
 * 
 * @author Huyuxi
 * @date 2018.11.23
 */
public class RSA {
	 private BigInteger modulus;
	 private BigInteger privateKey;
	 private BigInteger publicKey;
	 
	 /**
	  * Initialize
	  * @param bits
	  */
	    public RSA(int bits) {
	        generateKeys(bits);
	    }

	    public synchronized String encrypt(String message) {
	        return (new BigInteger(message.getBytes())).modPow(publicKey, modulus).toString();
	    }

	    public synchronized BigInteger encrypt(BigInteger message) {
	        return message.modPow(publicKey, modulus);
	    }

	    public synchronized String decrypt(String message) {
	        return new String((new BigInteger(message)).modPow(privateKey, modulus).toByteArray());
	    }

	    public synchronized BigInteger decrypt(BigInteger message) {
	        return message.modPow(privateKey, modulus);
	    }

	    /** Generate a new public and private key set. */
	    public synchronized void generateKeys(int bits) {
	        SecureRandom r = new SecureRandom();
	        BigInteger p = new BigInteger(bits / 2, 100, r);
	        BigInteger q = new BigInteger(bits / 2, 100, r);
	        //p*q
	        modulus = p.multiply(q);
            //m=(p-1)(q-1)
	        BigInteger m = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
	        //initialize public key: 3
	        publicKey = new BigInteger("3");

	        //publicKey and m should coprime
	        while (m.gcd(publicKey).intValue() > 1) {
	            publicKey = publicKey.add(new BigInteger("2"));
	        }

	        //return a big integer whose value is (publicKey^-1 mod m)
	        privateKey = publicKey.modInverse(m);
	    }

	    public static void main(String[] args) {
	    	//Currently, the 2048 bits RSA algorithm is still safe.
	        RSA rsa = new RSA(2048);

	        Scanner input = new Scanner(System.in);
			System.out.println("Please enter the message. ");
			String text = input.nextLine();
	        System.out.println("Plaintext for encoding: " + text);

	        String ciphertext = rsa.encrypt(text);
	        System.out.println("Ciphertext after encoding: \n" + ciphertext);

	        System.out.println("Plaintext after decoding: \n" + rsa.decrypt(ciphertext));
	    }
}
