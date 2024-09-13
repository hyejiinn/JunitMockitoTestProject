package org.example.test.unit.user;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserService
{
	private final UserRepository userRepository;

	public String getUserName(Long id)
	{
		return userRepository.findById(id)
				.map(User::getName).orElse("Unknown User");
	}
}
