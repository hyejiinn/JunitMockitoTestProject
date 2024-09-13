package org.example.test.spring.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ParentType
{
	MASTER("마스터 보호자"),
	SUB("보조 보호자");

	private final String codeDesc;
}
