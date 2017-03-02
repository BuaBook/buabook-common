package com.buabook.common.random;

import java.math.BigInteger;
import java.security.SecureRandom;

import com.google.common.math.IntMath;

/**
 * <h3>Random <i>Uber</i>-like Code Generator</h3>
 * <p>Generates random code sequences that can be given to users as referral codes for example.</p>
 * <br/><br/>(c) 2016 Sport Trades Ltd
 * 
 * @author Jas Rajasansir
 * @version 1.0.0
 * @since 27 Jul 2016
 */
public class RandomCodeGenerator {
	
	private static final int TO_STRING_RADIX = 36;
	
	
	private final SecureRandom random;

	
	public RandomCodeGenerator() {
		this.random = new SecureRandom();
	}
	
	
	/**
	 * <b>NOTE</b>: The generated code is not guaranteed to be unique. You should validate it with other generated
	 * codes where uniqueness is required before using it.
	 * @param requiredCodeLength
	 * @return A random sequence of letters and/or numbers of the specified length
	 * @throws IllegalArgumentException If the specified length is less than or equal to 0
	 */
	public String generateCode(int requiredCodeLength) throws IllegalArgumentException {
		if(requiredCodeLength <= 0)
			throw new IllegalArgumentException("Required code length must be positive");
		
		int randomBitLength = IntMath.checkedPow(requiredCodeLength, 2);
		
		String newReferralCode = new BigInteger(randomBitLength, random).toString(TO_STRING_RADIX);
		
		if(newReferralCode.length() > requiredCodeLength)
			return newReferralCode.substring(0, requiredCodeLength);
		else if(newReferralCode.length() < requiredCodeLength)
			return generateCode(requiredCodeLength);
		
		return newReferralCode;
	}

}
