package org.example.test.spring.service;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.example.test.spring.ctl.req.TestUserRequest;
import org.example.test.spring.ctl.res.TestUserResponse;
import org.example.test.spring.domain.TestUser;
import org.example.test.spring.repository.TestUserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * TestUserService + TestUserRepository 통합 테스트
 */
@SpringBootTest
class TestUserServiceTest
{
	@Autowired
	private TestUserRepository testUserRepository;

	@Autowired
	private TestUserService testUserService;

	@AfterEach
	void tearDown()
	{
		testUserRepository.deleteAllInBatch();
	}

	@DisplayName("테스트 사용자를 정보를 입력받아 테스트 사용자를 생성한다.")
	@Test
	void createTestUser() {
		// given
		TestUserRequest request = TestUserRequest.builder()
				.name("hyejin").phoneNo("01011112222").birth("981231").gender("F").build();

		// when
		TestUserResponse testUserResponse = testUserService.createTestUser(request);

		// then
		Assertions.assertThat(testUserResponse.getId()).isNotNull();
		Assertions.assertThat(testUserResponse)
				.extracting("name", "phoneNo")
				.contains("hyejin", "01011112222");
	}

	@DisplayName("모든 테스트 사용자를 조회할 수 있다.")
	@Test
	void getAllTestUser() {
	    // given
		TestUser testUser1 = createTestUserEntity("user1", "F", "01012345678", "000101");
		TestUser testUser2 = createTestUserEntity("user2", "M", "01078945612", "010101");
		TestUser testUser3 = createTestUserEntity("user3", "F", "01056891245", "020101");
		testUserRepository.saveAll(List.of(testUser1, testUser2, testUser3));

		// when
		List<TestUserResponse> allTestUserRes = testUserService.getAllTestUser();

		// then
		Assertions.assertThat(allTestUserRes).hasSize(3);
		Assertions.assertThat(allTestUserRes).extracting("name", "birth")
				.contains(
						Tuple.tuple("user1", "000101"),
						Tuple.tuple("user2", "010101"),
						Tuple.tuple("user3", "020101")
				);
	}


	@DisplayName("테스트 사용자 id를 받아 테스트 사용자를 조회한다.")
	@Test
	void getTestUserById() {
	    // given
		TestUser testUser1 = createTestUserEntity("user1", "F", "01012345678", "000101");
		TestUser testUser2 = createTestUserEntity("user2", "M", "01078945612", "010101");
		TestUser testUser3 = createTestUserEntity("user3", "F", "01056891245", "020101");
		testUserRepository.saveAll(List.of(testUser1, testUser2, testUser3));

	    // when
		TestUserResponse response = testUserService.getTestUserBy(testUser2.getId());

		// then
		Assertions.assertThat(response)
				.extracting("name", "birth").contains("user2", "010101");
	}


	@DisplayName("테스트 사용자를 삭제할 수 있다.")
	@Test
	void deleteTestUser() {
	    // given
		TestUser testUser1 = createTestUserEntity("user1", "F", "01012345678", "000101");
		testUserRepository.save(testUser1);

	    // when
		testUserService.deleteTestUserBy(testUser1.getId());

	    // then
		long count = testUserRepository.count();
		Assertions.assertThat(count).isZero();
	}

	// 존재하지 않는 사용자 삭제 테스트

	private TestUser createTestUserEntity(String name, String gender, String phoneNo, String birth)
	{
		return TestUser.builder()
				.name(name)
				.gender(gender)
				.phoneNo(phoneNo)
				.birth(birth)
				.build();
	}
}