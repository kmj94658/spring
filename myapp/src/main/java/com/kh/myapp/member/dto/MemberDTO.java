package com.kh.myapp.member.dto;

public class MemberDTO {
	private String id; //아이디
	private String pwd; //비밀번호
	private String nickname; //별칭
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	@Override
	public String toString() {
		return "MemberDTO [id=" + id + ", pwd=" + pwd + ",nickname=" + nickname + "]";
	}
}
