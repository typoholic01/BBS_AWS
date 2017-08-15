package member;


public class MemberCtrl {
	
    MemberServiceImpl memberSer = new MemberService();
    
    public MemberDto login(String email, String pw) {
    	MemberDto mem = new MemberDto(email, pw, null, null, null);
		return memberSer.login(mem);
	}
	
	public boolean addMember(String email, String pw, String name, String phone, String auth) {
		
		MemberDto mem = new MemberDto(email, pw, name, phone, auth);
		
		return memberSer.addMember(mem);
			
	}
	
}
