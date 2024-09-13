package org.example.test.spring.ctl;

import java.util.List;

import org.example.test.spring.ctl.req.TestUserRequest;
import org.example.test.spring.ctl.res.ApiResponse;
import org.example.test.spring.ctl.res.TestUserResponse;
import org.example.test.spring.service.TestUserService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/test-users")
public class TestUserController
{
	private final TestUserService testUserService;

	@GetMapping
	public ApiResponse<List<TestUserResponse>> getAllTestUsers()
	{
		return ApiResponse.ok(testUserService.getAllTestUser());
	}

	@GetMapping("/{id}")
	public ApiResponse<TestUserResponse> getTestUserBy(@PathVariable Long id)
	{
		return ApiResponse.ok(testUserService.getTestUserBy(id));
	}

	@PostMapping
	public ApiResponse<TestUserResponse> createTestUser(@Valid @RequestBody TestUserRequest request)
	{
		return ApiResponse.ok(testUserService.createTestUser(request));
	}

	@DeleteMapping("/{id}")
	public ApiResponse<Object> deleteTestUserBy(@PathVariable Long id)
	{
		testUserService.deleteTestUserBy(id);
		return ApiResponse.ok(null);
	}
}
