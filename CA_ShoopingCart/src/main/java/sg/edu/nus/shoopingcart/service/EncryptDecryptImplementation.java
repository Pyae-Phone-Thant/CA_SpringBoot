package sg.edu.nus.shoopingcart.service;

//Author Pyae Phone Thant
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;

@Service
public class EncryptDecryptImplementation {
	
	private String key="oiGYxb/z3VpMAItlNDyC6taDA2uZjPUBYClA58Zf+tQ=";
	
	/*
	 * public EncryptDecryptImplementation() { this.key = generateKey(); }
	 * 
	 * public static String generateKey() { try { KeyGenerator keyGen =
	 * KeyGenerator.getInstance("AES"); keyGen.init(256); // AES 256-bit key
	 * SecretKey secretKey = keyGen.generateKey(); return
	 * Base64.getEncoder().encodeToString(secretKey.getEncoded()); } catch
	 * (Exception e) { throw new CustomException(); }
	 * 
	 * }
	 */

	public String encrypt(String text) {
		try {
			SecretKeySpec secretKey = new SecretKeySpec(Base64.getDecoder().decode(key), "AES");
	        Cipher cipher = Cipher.getInstance("AES");
	        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
	        byte[] encryptedText = cipher.doFinal(text.getBytes("UTF-8"));
	        return Base64.getEncoder().encodeToString(encryptedText);
		} catch (Exception e) {
			throw new CustomException();
		}
	}

	public String decrypt(String encryptedText) {
		try {
			SecretKeySpec secretKey = new SecretKeySpec(Base64.getDecoder().decode(key), "AES");
	        Cipher cipher = Cipher.getInstance("AES");
	        cipher.init(Cipher.DECRYPT_MODE, secretKey);
	        byte[] decryptedText = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
	        return new String(decryptedText, "UTF-8");
		} catch (Exception e) {
			throw new CustomException();
		}
	}
}
