package com.buabook.common.random.test;

import org.junit.Test;

import com.buabook.common.random.RandomCodeGenerator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class RandomCodeGeneratorTest {

	// RandomCodeGenerator.generateCode
	
	@Test(expected=IllegalArgumentException.class)
	public void testGenerateCodeThrowsExceptionIfNegativeCodeLength() {
		new RandomCodeGenerator().generateCode(-1);
	}
	
	@Test
	public void testGenerateCodeGeneratesSingleCharacterCode() {
		String code = new RandomCodeGenerator().generateCode(1);
		
		assertThat(code.length(), is(equalTo(1)));
	}
	
	@Test
	public void testGenerateCodeGeneratesDifferentLengthCodes() {
		RandomCodeGenerator generator = new RandomCodeGenerator();
	
		assertThat(generator.generateCode(4).length(), is(equalTo(4)));
		assertThat(generator.generateCode(9).length(), is(equalTo(9)));
		assertThat(generator.generateCode(40).length(), is(equalTo(40)));
	}
	
	@Test
	public void testGenerateCodeGeneratesCodesRepeatedly() {
		RandomCodeGenerator codeGenerator = new RandomCodeGenerator();
		
		int expectedLength = 6;
		
		for(int cnt = 0; cnt < 1000; cnt++) {
			String refCode = codeGenerator.generateCode(expectedLength);
			assertThat(refCode, is(not(blankOrNullString())));
			assertThat(refCode.length(), is(equalTo(expectedLength)));
		}
	}
}
