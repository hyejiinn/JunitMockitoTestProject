package org.example.test.unit;

import org.assertj.core.api.Assertions;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AssertionsTest
{

	@Test
	@DisplayName("Jupiter Assertions 를 활용한 문자열 검증 테스트")
	void testJupiter()
	{
		// Given
		String name = "Jupiter";

		org.junit.jupiter.api.Assertions.assertNotNull(name);
		org.junit.jupiter.api.Assertions.assertTrue(name.startsWith("J"));
		org.junit.jupiter.api.Assertions.assertTrue(name.endsWith("r"));
		org.junit.jupiter.api.Assertions.assertEquals(name.length(), 7);
	}

	@Test
	@DisplayName("AssertJ Assertions 를 활용한 문자열 검증 테스트")
	void testAssertJ()
	{
		// Given
		String name = "assertJ";

		// Then
		Assertions
				.assertThat(name)
				.isNotNull().
				startsWith("a")
				.endsWith("J")
				.hasSize(7);
	}

	@Test
	@DisplayName("Hamcrest Matcher 를 활용한 문자열 검증 테스트")
	void testHamcrest()
	{
		// Given
		String name = "hamcrest";

		// Then
		MatcherAssert.assertThat(name, Matchers.is(Matchers.notNullValue()));
		MatcherAssert.assertThat(name, Matchers.startsWith("h"));
		MatcherAssert.assertThat(name, Matchers.endsWith("t"));
		MatcherAssert.assertThat(name.length(), Matchers.is(8));
	}

}
