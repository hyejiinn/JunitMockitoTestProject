package org.example.test.spring.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserStatus
{
	SERVICE_USER("서비스 사용자"),
	TERMINATION_USER("서비스 중지 사용자");

	private final String codeDesc;
}
