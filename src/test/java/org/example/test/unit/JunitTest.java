package org.example.test.unit;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * 단위 테스트
 */
class JunitTest
{
	@Test
	@DisplayName("두 수가 주어지면 합을 구하는 테스트")
	void addTest()
	{
		// Given
		Calculator calculator = new Calculator();
		int num1 = 10;
		int num2 = 20;

		// When
		int result = calculator.add(num1, num2);

		// Then
		Assertions.assertThat(result).isEqualTo(30); // AssertJ Assertions isEqualTo
		org.junit.jupiter.api.Assertions.assertEquals(3, result); // Jupiter Assertions assertEquals
	}
}