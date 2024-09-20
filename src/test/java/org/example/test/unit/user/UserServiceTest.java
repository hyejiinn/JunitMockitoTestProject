package org.example.test.unit.user;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Mockito 테스트
 */
@ExtendWith(MockitoExtension.class)
class UserServiceTest
{
	@Mock
	UserRepository userRepository; // Mock 객체 생성

	@InjectMocks
	UserService userService; // Mock 객체를 주입할 실제 객체

	@Test
	@DisplayName("id가 1인 사용자의 이름 테스트")
	void testGetUserName()
	{
		// Given
		Long userId = 1L;

		// When,  Stub : 실제 객체에 가짜 동작을 수행하도록 설정
		Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(new User(1L, "hyejin")));

		// Then : 호출 결과 검증
		String userName = userService.getUserName(userId);
		Assertions.assertThat(userName).isEqualTo("hyejin");

		// Verify : Mock 객체의 메서드가 제대로 호출되었는지 검증
		Mockito.verify(userRepository).findById(1L);
	}

	@Test
	@DisplayName("없는 id인 사용자의 이름을 조회할 때, RuntimeException 예외가 발생함을 검증하는 테스트")
	void testExceptionHandling()
	{
		// Given
		Long userId = 2L;

		// When, Stub : 실제 객체에 가짜 동작을 수행하도록 설정
		Mockito.when(userRepository.findById(2L)).thenThrow(new RuntimeException("no data"));

		// Then : 예외 발생 여부 검증
		Assertions.assertThatThrownBy(() -> userService.getUserName(userId))
				.isInstanceOf(RuntimeException.class);

		// Verify : Mock 객체의 메서드가 제대로 호출되었는지 검증
		Mockito.verify(userRepository).findById(2L);
	}
}