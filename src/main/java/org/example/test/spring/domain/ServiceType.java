package org.example.test.spring.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ServiceType
{
	FREE_USER("무료 사용자"),
	SERVICE_USER("유료 사용자"),
	TERMINATION_USER("해지자");

	private final String codeDesc;
}
