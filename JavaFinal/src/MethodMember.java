import java.util.*;

public class MethodMember {
	private ReadFileData rfd;
	private StringBuffer buffer;
	private String[] context;
	private int i=0;
	private int tokenNum;
	private String[] method;
	private int methodCount;
	
	public MethodMember(){
		rfd=new ReadFileData();
		buffer=rfd.getFileSB();
		StringTokenizer st = new StringTokenizer(buffer.toString()," \t\n\r	(){}: ",true);
		tokenNum=st.countTokens();
		context = new String[tokenNum];

		while (st.hasMoreTokens()){
			String token=st.nextToken();
			context[i]=token;
//			System.out.println(context[i]);
			i++;
		}
		MemberPrint(tokenNum);
	}
	
	public void MemberPrint(int tokenNum) {
		MethodClass mc = new MethodClass();
		ClassInfo ci = new ClassInfo();
		/*for(i=0; i<tokenNum; i++) {
			trim();
			if(�޼ҵ峻��.contains(Ŭ������ �������))
				return �޼ҵ� �̸�;
		}*/
	}

	public static void main(String[] args) {
		new MethodMember();
	}

}
