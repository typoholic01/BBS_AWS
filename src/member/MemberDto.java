package member;

import java.io.Serializable;

public class MemberDto implements Serializable{
	private static final long serialVersionUID = -332456593117366596L;
	
	private String email;
	private String pw;
	private String name;
	private String phone;
	private String auth;
	
	
	public MemberDto() {}


	public MemberDto(String email, String pw, String name, String phone, String auth) {
		super();
		this.email = email;
		this.pw = pw;
		this.name = name;
		this.phone = phone;
		this.auth = auth;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPw() {
		return pw;
	}


	public void setPw(String pw) {
		this.pw = pw;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getAuth() {
		return auth;
	}


	public void setAuth(String auth) {
		this.auth = auth;
	}


	@Override
	public String toString() {
		return "MemberDto [email=" + email + ", pw=" + pw + ", name=" + name + ", phone=" + phone + ", auth=" + auth
				+ "]";
	}
	
	
}
