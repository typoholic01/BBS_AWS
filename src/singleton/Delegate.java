package singleton;

import bbs.BBSCtrl;
import member.MemberCtrl;

public class Delegate {
	private static Delegate single = null;
	
	//컨트롤러 변수
	public MemberCtrl memCtrl;
	public BBSCtrl BBSCtrl;
	
	private Delegate() {
		memCtrl = new MemberCtrl();		
		BBSCtrl = new BBSCtrl();
	}
	
	public static Delegate getInstance() {
		if (single == null) {
			single = new Delegate();
		}
		
		return single;
	}

}
