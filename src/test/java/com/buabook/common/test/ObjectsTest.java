package com.buabook.common.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anEmptyMap;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import java.util.Map;

import org.junit.Test;

import com.buabook.common.Objects;

public class ObjectsTest {

	// Objects.convertStringToClass
	
	@Test(expected=IllegalArgumentException.class)
	public void testConvertStringToClassThrowsExceptionIfNoStringProvided() {
		Objects.convertStringToClass(null, Boolean.class);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testConvertStringToClassThrowsExceptionIfEmptyStringProvided() {
		Objects.convertStringToClass("", Boolean.class);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testConvertStringToClassThrowsExceptionIfNoConvertClassSpecified() {
		Objects.convertStringToClass("true", null);
	}
	
	@Test
	public void testConvertStringToClassReturnsNullIfConstructorRejectsInvalidArgument() {
		assertThat(Objects.convertStringToClass("blah", Double.class), is(nullValue()));
	}
	
	@Test
	public void testConvertStringToClassRetursNullIfClassDoesNotHaveStringConstructor() {
		assertThat(Objects.convertStringToClass("true", ObjectsTest.class), is(nullValue()));
	}
	
	@Test
	public void testConvertStringToClassReturnsConvertedObject() {
		assertThat(Objects.convertStringToClass("true", Boolean.class), is(equalTo(Boolean.TRUE)));
		assertThat(Objects.convertStringToClass("123", Integer.class), is(equalTo(123)));
		assertThat(Objects.convertStringToClass("1.231", Double.class), is(equalTo(1.231)));
	}
	
	@Test
	public void testConvertStringToClassReturnsIdenticalStringWhenStringClassSpecified() {
		assertThat(Objects.convertStringToClass("abcd", String.class), is(equalTo("abcd")));
	}

	// Objects.getObjectFieldsAsMap
	
	@Test
	public void testGetObjectFieldsAsMapReturnsEmptyMapForNullObject() {
		assertThat(Objects.getObjectFieldsAsMap(null), is(anEmptyMap()));
	}
	
	@Test
	public void testGetObjectFieldsAsMapReturnsEmptyMapForObjectWithNoFields() {
		assertThat(Objects.getObjectFieldsAsMap(new ObjectsTest()), is(anEmptyMap()));
	}
	
	@Test
	public void testGetObjectFieldsAsMapReturnsMapForObject() {
		ObjectWithFields obj = new ObjectWithFields("string", 123);
		
		Map<String, Object> result = Objects.getObjectFieldsAsMap(obj);
		
		assertThat(result, is(not(anEmptyMap())));
		
		assertThat(result, hasKey("field1"));
		assertThat(result.get("field1"), is(instanceOf(String.class)));
		assertThat((String) result.get("field1"), is(equalTo("string")));
		
		assertThat(result, hasKey("field2"));
		assertThat(result.get("field2"), is(instanceOf(Integer.class)));
		assertThat((Integer) result.get("field2"), is(equalTo(123)));
	}
	
	private class ObjectWithFields { 
		
		@SuppressWarnings("unused")
		private String field1;
		
		@SuppressWarnings("unused")
		private Integer field2;
		
		ObjectWithFields(String field1, Integer field2) {
			this.field1 = field1;
			this.field2 = field2;
		}
		
	}

}
