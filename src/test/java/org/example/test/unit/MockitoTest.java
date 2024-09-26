package org.example.test.unit;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Mockito 테스트
 */
@ExtendWith(MockitoExtension.class)
public class MockitoTest
{
	@Captor
	ArgumentCaptor<String> captor;

	@Spy
	List<String> spyList = new ArrayList<>();

	@Test
	@DisplayName("Mockito 테스트")
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

	@Test
	@DisplayName("Spy 테스트")
	void testSpy()
	{
		// 실제 메서드 호출 검증
		spyList.add("test");
		Mockito.verify(spyList).add("test");

		// 가짜 메서드 호출 검증
		Mockito.when(spyList.size()).thenReturn(100);
		Assertions.assertThat(spyList).hasSize(100);
	}

	@Test
	@DisplayName("Captor : 메서드 호출 시 인자 캡쳐해 테스트 검증")
	void testCaptor()
	{
		// Given : List Mock 객체 생성
		List<String> mockList = Mockito.mock(List.class);

		// When
		mockList.add("captor");

		// Verify
		Mockito.verify(mockList).add(captor.capture());
		org.junit.jupiter.api.Assertions.assertEquals("captor", captor.getValue());
	}
}
