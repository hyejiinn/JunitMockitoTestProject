package org.example.test.spring.service;

import java.util.List;
import java.util.stream.Collectors;

import org.example.test.spring.ctl.req.TestUserRequest;
import org.example.test.spring.ctl.res.TestUserResponse;
import org.example.test.spring.exception.TestUserNotFound;
import org.example.test.spring.repository.TestUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service(value = "testUserService")
public class TestUserService
{
	private final TestUserRepository testUserRepository;

	public List<TestUserResponse> getAllTestUser()
	{
		return testUserRepository.findAll().stream()
				.map(TestUserResponse::of).collect(Collectors.toList());
	}

	public TestUserResponse getTestUserBy(Long id)
	{
		return TestUserResponse.of(testUserRepository.findById(id).orElseThrow(TestUserNotFound::new));
	}

	public TestUserResponse createTestUser(TestUserRequest request)
	{
		 return TestUserResponse.of(testUserRepository.save(request.toTestUserEntity()));
	}

	public void deleteTestUserBy(Long id)
	{
		testUserRepository.deleteById(id);
	}
}
