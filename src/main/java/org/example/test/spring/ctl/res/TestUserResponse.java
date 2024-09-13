package org.example.test.spring.ctl.res;

import org.example.test.spring.domain.UserStatus;
import org.example.test.spring.domain.TestUser;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TestUserResponse
{
	private Long id;
	private String name;
	private String phoneNo;
	private String birth;
	private String gender;
	private UserStatus userStatus;

	@Builder
	public TestUserResponse(Long id, String name, String phoneNo, String birth, String gender, UserStatus userStatus)
	{
		this.id = id;
		this.name = name;
		this.phoneNo = phoneNo;
		this.birth = birth;
		this.gender = gender;
		this.userStatus = userStatus;
	}

	public static TestUserResponse of(TestUser testUser)
	{
		return TestUserResponse.builder()
				.id(testUser.getId())
				.name(testUser.getName())
				.gender(testUser.getGender())
				.phoneNo(testUser.getPhoneNo())
				.birth(testUser.getBirth())
				.userStatus(testUser.getUserStatus())
				.build();
	}
}
