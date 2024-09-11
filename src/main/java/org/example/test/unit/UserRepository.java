package org.example.test.unit;

import java.util.Optional;

public interface UserRepository
{
	Optional<User> findById(Long id);
}
