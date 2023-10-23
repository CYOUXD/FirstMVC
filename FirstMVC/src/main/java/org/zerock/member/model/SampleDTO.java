package org.zerock.member.model;

public class SampleDTO {
	
	private String name;
	private String age;
	
	public SampleDTO() {}

	public SampleDTO(String name, String age) {
		super();
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "SampleDTO [name=" + name + ", age=" + age + "]";
	}
	
	
}
