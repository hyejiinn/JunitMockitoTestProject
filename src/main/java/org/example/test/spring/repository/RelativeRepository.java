package org.example.test.spring.repository;

import org.example.test.spring.domain.Relative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelativeRepository extends JpaRepository<Relative, Long>
{

}
