package org.example.test.spring.ctl.res;

import org.example.test.spring.domain.UserStatus;
import org.example.test.spring.domain.TestUser;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TestUserResponse
{
	private String name;
	private String phoneNo;
	private String birth;
	private String gender;
	private UserStatus userStatus;

	@Builder
	public TestUserResponse(String name, String gender, String phoneNo, String birth, UserStatus userStatus)
	{
		this.name = name;
		this.gender = gender;
		this.phoneNo = phoneNo;
		this.birth = birth;
		this.userStatus = userStatus;
	}

	public static TestUserResponse of(TestUser testUser)
	{
		return TestUserResponse.builder()
				.name(testUser.getName())
				.gender(testUser.getGender())
				.phoneNo(testUser.getPhoneNo())
				.birth(testUser.getBirth())
				.userStatus(testUser.getUserStatus())
				.build();
	}
}
