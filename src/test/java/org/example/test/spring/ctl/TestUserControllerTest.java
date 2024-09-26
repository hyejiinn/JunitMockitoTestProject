package org.example.test.spring.ctl;

import java.util.List;

import org.example.test.spring.ctl.req.TestUserRequest;
import org.example.test.spring.ctl.res.TestUserResponse;
import org.example.test.spring.domain.UserStatus;
import org.example.test.spring.exception.TestUserNotFound;
import org.example.test.spring.service.TestUserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * MockMvc 를 활용한 Controller 테스트
 */
@SpringBootTest // 스프링 환경에서 테스트하기 위한 어노테이션
@AutoConfigureMockMvc // MockMvc를 자동으로 구성하는 데 사용하는 어노테이션
class TestUserControllerTest
{
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TestUserService testUserService;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	@DisplayName("모든 사용자를 조회한다.")
	void getAllTestUsers() throws Exception
	{
	    // given
		Mockito.when(testUserService.getAllTestUser())
				.thenReturn(List.of(
						new TestUserResponse(1L, "user1", "01011112222", "000101", "F", UserStatus.SERVICE_USER),
						new TestUserResponse(2L, "user2", "01033334444", "010101", "M", UserStatus.SERVICE_USER)
						));

	    // when.. then
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/test-users"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.code").value(200))
				.andExpect(MockMvcResultMatchers.jsonPath("$.status").value("OK"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.data").isArray());
	}
	
	
	@Test
	@DisplayName("id가 1인 사용자를 조회한다.")
	void getTestUserBy() throws Exception
	{
	    // given
		Mockito.when(testUserService.getTestUserBy(1L)).thenReturn(
			new TestUserResponse(1L, "user1", "01011112222", "000101", "F", UserStatus.SERVICE_USER)		
		);
	    
	    // when.. then
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/test-users/{id}", 1L))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.code").value("200"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.status").value("OK"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("OK"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.data.name").value("user1"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.data.phoneNo").value("01011112222"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.data.gender").value("F"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.data.userStatus").value(UserStatus.SERVICE_USER.toString()))
		;
	}

	@Test
	@DisplayName("새로운 사용자를 등록한다.")
	void createTestUser() throws Exception
	{
	    // given
		TestUserRequest request = TestUserRequest.builder()
				.name("user1")
				.phoneNo("01012345678")
				.birth("000101")
				.gender("M")
				.build();

		// when.. then
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/test-users")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(request))
				).andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.status").value("OK"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("OK"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.data").isEmpty());

	}

	@Test
	@DisplayName("id가 1인 테스트 사용자를 삭제한다.")
	void deleteTestUserBy() throws Exception
	{
	    // given
		long userId = 1L;
		Mockito.doNothing().when(testUserService).deleteTestUserBy(userId);

	    // When.. then
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/test-users/{id}", userId))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.status").value("OK"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("OK"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.data").isEmpty());

		// verify
		Mockito.verify(testUserService, Mockito.times(1)).deleteTestUserBy(userId);
	}

	@Test
	@DisplayName("이름이 없는 잘못된 데이터로 사용자를 등록할 때 400 에러를 반환한다.")
	void createTestUser_withInvalidData_shouldReturnBadRequest() throws Exception
	{
		// given: 필수 값이 누락된 요청
		TestUserRequest invalidRequest = TestUserRequest.builder()
				.name("") // 빈 이름
				.phoneNo("01012345678")
				.birth("000101")
				.gender("M")
				.build();

		// when.. then
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/test-users")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(invalidRequest)))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isBadRequest())
				.andExpect(MockMvcResultMatchers.jsonPath("$.status").value("BAD_REQUEST"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("BAD_REQUEST"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.data").isEmpty());
	}

	@Test
	@DisplayName("존재하지 않는 사용자 id로 사용자 조회할 때 400 에러를 반환한다.")
	void getTestUserBy_invalidId_shouldReturnNotFound() throws Exception
	{
		// given: 존재하지 않는 ID
		long userId = 99L;
		Mockito.when(testUserService.getTestUserBy(userId)).thenThrow(new TestUserNotFound());

		// when.. then
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/test-users/{id}", userId))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isBadRequest())
				.andExpect(MockMvcResultMatchers.jsonPath("$.status").value("BAD_REQUEST"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("BAD_REQUEST"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.data").isEmpty());
	}
}