package member;


public class MemberService implements MemberServiceImpl {
	
	MemberDaoImpl memdao = MemberDao.getInstance();

	@Override
	public MemberDto login(MemberDto dto) {
		return memdao.login(dto);
	}
	
	@Override
	public boolean addMember(MemberDto dto) {
		return memdao.addMember(dto);
	}
	
	
	
	
	
	
	
	
	

}
