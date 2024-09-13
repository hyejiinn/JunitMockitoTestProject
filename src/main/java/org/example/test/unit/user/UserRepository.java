package org.example.test.unit.user;

import java.util.Optional;

public interface UserRepository
{
	Optional<User> findById(Long id);
}
