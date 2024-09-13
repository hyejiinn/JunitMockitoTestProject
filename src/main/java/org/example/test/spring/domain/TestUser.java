package org.example.test.spring.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class TestUser extends BaseEntity
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String gender;
	private String phoneNo;
	private String birth;

	@Enumerated(EnumType.STRING)
	private UserStatus userStatus;

	@Builder
	public TestUser(String name, String gender, String phoneNo, String birth, UserStatus userStatus)
	{
		this.name = name;
		this.gender = gender;
		this.phoneNo = phoneNo;
		this.birth = birth;
		this.userStatus = userStatus;
	}
}
