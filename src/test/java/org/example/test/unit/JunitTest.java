package org.example.test.unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JunitTest
{
	@Test
	void calculateTwoNumbers()
	{
		// Given
		int num1 = 10;
		int num2 = 20;
		Calculator calculator = new Calculator();

		// When
		int result = calculator.add(num1, num2);

		// Then
		Assertions.assertEquals(3, result);
	}
}
