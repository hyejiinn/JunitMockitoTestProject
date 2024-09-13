package org.example.test.spring.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

// 엔티티가 생성되거나 변경될 때 시간 기록
@Getter
@MappedSuperclass
@EntityListeners(AutoCloseable.class)
public class BaseEntity
{

	@CreatedDate
	private LocalDateTime createdDateTime;

	@LastModifiedDate
	private LocalDateTime modifiedDatetime;
}
