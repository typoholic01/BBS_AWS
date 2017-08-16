package bbs;

import java.util.List;

public class BBSService implements BBSServiceImpl 
{
	BBSDaoImpl dao = BBSDao.getInstance();
	BBSDto dto = new BBSDto();

	@Override
	public int getTotalArticle() {
		return dao.getTotalArticle();
	}

	@Override
	public int getTotalArticle(String s_type, String q) {
		return dao.getTotalArticle(s_type, q);
	}

	@Override
	public boolean writeBbs(BBSDto dto) 
	{
		System.out.println("BBSService: "+dao.toString());
		
		return dao.writeBbs(dto);
	}

	@Override
	public List<BBSDto> getBbsList(int cur_page) {
		return dao.getBbsList(cur_page);
	}

	@Override
	public BBSDto getBbs(int seq) {
		return dao.getBbs(seq);
	}

	@Override
	public List<BBSDto> getBbsList(int cur_page, String s_type, String q) {		
		return dao.getBbsList(cur_page, s_type, q);
	}

	@Override
	public void updateBbs(int seq) {
		dao.updateBbs(seq);
		
	}

	@Override
	public void deleteBbs(int seq) {
		dao.deleteBbs(seq);
		
	}

}
