package org.example.test.spring.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Child extends BaseEntity
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String gender;
	private String phoneNo;
	private String birth;

	@Enumerated(EnumType.STRING)
	private ServiceType serviceType;

	@OneToMany(mappedBy = "child", cascade = CascadeType.ALL)
	private List<Relative> relatives = new ArrayList<>();

	@Builder
	public Child(String name, String gender, String phoneNo, String birth, ServiceType serviceType)
	{
		this.name = name;
		this.gender = gender;
		this.phoneNo = phoneNo;
		this.birth = birth;
		this.serviceType = serviceType;
	}
}
