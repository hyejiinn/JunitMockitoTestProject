package org.example.test.spring.ctl.req;

import org.example.test.spring.domain.TestUser;
import org.example.test.spring.domain.UserStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TestUserRequest
{
	@NotBlank(message = "이름은 필수입니다.")
	private String name;

	@Pattern(regexp = "[FM]", message = "F / M 값만 입력 가능합니다.")
	private String gender;

	@NotBlank(message = "전화번호는 필수입니다.")
	private String phoneNo;

	@NotBlank(message = "생년월일은 필수입니다.")
	private String birth;

	@Builder
	public TestUserRequest(String name, String gender, String phoneNo, String birth)
	{
		this.name = name;
		this.gender = gender;
		this.phoneNo = phoneNo;
		this.birth = birth;
	}

	public TestUser toTestUserEntity()
	{
		return TestUser.builder()
				.name(name)
				.gender(gender)
				.phoneNo(phoneNo)
				.birth(birth)
				.userStatus(UserStatus.SERVICE_USER)
				.build();
	}
}
