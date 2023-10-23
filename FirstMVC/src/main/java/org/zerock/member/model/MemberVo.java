package org.zerock.member.model;

public class MemberVo {
	
	private String memId;
	private String memPw;
	private String name;
	private String email;
	private String phone;
	private String addr;
	
	public MemberVo() {
	}

	public MemberVo(String memId, String memPw, String name, String email, String phone, String addr) {
		super();
		this.memId = memId;
		this.memPw = memPw;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.addr = addr;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMemPw() {
		return memPw;
	}

	public void setMemPw(String memPw) {
		this.memPw = memPw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	@Override
	public String toString() {
		return "MemberVo [memId=" + memId + ", memPw=" + memPw + ", name=" + name + ", email=" + email + ", phone="
				+ phone + ", addr=" + addr + "]";
	}
	
	

}
