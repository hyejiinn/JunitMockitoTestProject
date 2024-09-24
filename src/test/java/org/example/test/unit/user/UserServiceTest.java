package org.example.test.unit.user;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Mockito 를 활용한 UserService 단위 테스트
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
		Mockito.when(userRepository.findById(1L))
				.thenReturn(Optional.of(new User(1L, "hyejin")));

		// When
		String userName = userService.getUserName(userId);

		// Then : 호출 결과 검증
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
		Mockito.when(userRepository.findById(2L))
				.thenThrow(new RuntimeException("no data"));

		// When, Then : 예외 발생 여부 검증
		Assertions.assertThatThrownBy(() -> userService.getUserName(userId))
				.isInstanceOf(RuntimeException.class);

		// Verify : Mock 객체의 메서드가 제대로 호출되었는지 검증
		Mockito.verify(userRepository).findById(2L);
	}

	@Test
	@DisplayName("사용자 조회 시 리포지토리의 findById가 정확히 한 번 호출되었는지 검증하는 테스트")
	void testRepositoryMethodCallCount() {
		// Given
		Long userId = 1L;
		Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(new User(1L, "hyejin")));

		// When
		String userName = userService.getUserName(userId);

		// Then: findById가 한 번만 호출되었는지 검증
		Mockito.verify(userRepository, Mockito.times(1)).findById(userId);
		Assertions.assertThat(userName).isEqualTo("hyejin");
	}

	@Test
	@DisplayName("id가 1인 사용자의 이름을 조회하는 BDD 스타일 테스트")
	void testGetUserName_BDD() {
		// Given
		Long userId = 1L;
		BDDMockito.given(userRepository.findById(userId)).willReturn(Optional.of(new User(1L, "hyejin")));

		// When
		String userName = userService.getUserName(userId);

		// Then
		Assertions.assertThat(userName).isEqualTo("hyejin");
		BDDMockito.then(userRepository).should().findById(userId);
	}
}