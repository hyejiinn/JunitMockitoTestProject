package org.example.test.unit;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * Mockito 테스트
 */
public class MockitoTest
{

	@Test
	void testMockito()
	{
		// Given : List Mock 객체 생성
		List<String> mockList = Mockito.mock(List.class);

		// When : mock 객체의 get 메서드가 호출되면 "first" 반환되도록 설정
		Mockito.when(mockList.get(0)).thenReturn("first");

		// Then : mock 객체의 get 메서드가 호출되었을 때, "first"가 반환되는지 검증
		String result = mockList.get(0);
		Assertions.assertThat(result).isEqualTo("first");

		// Verify : get(0)이 한 번 호출되었는지 검증
		Mockito.verify(mockList).get(0);
	}
}
