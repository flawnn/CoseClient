package me.cose.crypter;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;

public class ClientCrypter {
	
	private static Cipher encryption;
	private static Cipher decryption;
	
	static
	{
		try
		{
			String algorithm = "AES/ECB/PKCS5Padding";
			SecureRandom rnd = new SecureRandom();
			KeyGenerator keyGen = KeyGenerator.getInstance(algorithm.substring(0, algorithm.indexOf('/')));
			keyGen.init(128, rnd);
			SecretKey key = keyGen.generateKey();
			encryption = Cipher.getInstance(algorithm);
			encryption.init(Cipher.ENCRYPT_MODE, key, rnd);
			decryption = Cipher.getInstance(algorithm);
			decryption.init(Cipher.DECRYPT_MODE, key, rnd);
		}
		catch(Exception err) {}
	}
	
	public static String encrypt(String input)
	{
		try
		{
			return DatatypeConverter.printBase64Binary(encryption.doFinal(input.getBytes("UTF-8")));
		}
		catch(Exception err) {}
		return input;
	}
	
	public static String decrypt(String input)
	{
		try
		{
			return new String(decryption.doFinal(DatatypeConverter.parseBase64Binary(input)), "UTF-8");
		}
		catch(Exception err) {}
		return input;
	}
	
}
