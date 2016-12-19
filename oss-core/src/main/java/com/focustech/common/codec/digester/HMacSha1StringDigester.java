package com.focustech.common.codec.digester;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.focustech.common.codec.DigestedException;
import com.focustech.common.exception.SystemErrorException;

/**
 * HMAC-sha1算法的类
 * 
 * @author geliang
 */
public class HMacSha1StringDigester implements Digester {

	private Mac mac;
	private SecretKeySpec spec;
	private static final Log log = LogFactory
			.getLog(HMacSha1StringDigester.class);

	public HMacSha1StringDigester() {
		try {
			mac = Mac.getInstance("HmacSHA1");
		} catch (NoSuchAlgorithmException e) {
			log.error("", e);
			throw new SystemErrorException(e);
		}
	}

	/**
	 * 设置HMAC算法所需的key
	 * 
	 * @param key
	 */
	public void setKey(String key) {
		spec = new SecretKeySpec(key.getBytes(), "HmacSHA1");
		try {
			mac.init(spec);
		} catch (InvalidKeyException e) {
			log.error("", e);
			throw new SystemErrorException(e);
		}
	}

	@Override
	public byte[] digest(byte[] message) throws DigestedException {
		throw new DigestedException(
				"this is String Digester, not support byte[]");
	}

	@Override
	public boolean matches(byte[] message, byte[] digest)
			throws DigestedException {
		throw new DigestedException(
				"this is String Digester, not support byte[]");
	}

	@Override
	public String digest(String message) throws DigestedException {
		byte[] byteHMAC = mac.doFinal(message.getBytes());
		String oauth = new String(Base64.encodeBase64(byteHMAC));
		return oauth;
	}

	@Override
	public boolean matches(String message, String digest)
			throws DigestedException {
		String newDigest = digest(message);
		return newDigest.equals(digest);
	}

}
