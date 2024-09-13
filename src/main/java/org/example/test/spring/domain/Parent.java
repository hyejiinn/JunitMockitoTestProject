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
public class Parent extends BaseEntity
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String phoneNo;
	private String birth;

	@Enumerated(EnumType.STRING)
	private ParentType parentType;

	@OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
	private List<Relative> relatives = new ArrayList<>();

	@Builder
	public Parent(String name, String phoneNo, String birth, ParentType parentType)
	{
		this.name = name;
		this.phoneNo = phoneNo;
		this.birth = birth;
		this.parentType = parentType;
	}
}
