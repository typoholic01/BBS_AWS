package bbs;

import java.util.List;

public interface BBSDaoImpl 
{
	public int getTotalArticle();
	public int getTotalArticle(String s_type, String q);	
	
	public boolean writeBbs(BBSDto dto);

	public List<BBSDto> getBbsList(int cur_page);
	public List<BBSDto> getBbsList(int cur_page, String s_type, String q);
	
	public BBSDto getBbs(int seq);
	public void updateBbs(int seq, BBSDto dto);
	public void deleteBbs(int seq);

}
