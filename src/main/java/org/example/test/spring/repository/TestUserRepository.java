package org.example.test.spring.repository;

import org.example.test.spring.domain.TestUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestUserRepository extends JpaRepository<TestUser, Long>
{
}
