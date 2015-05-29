/* MethodUsingMember 클래스 
 * 클래스 파일의 멤버 변수가 어느 메소드에서 사용되는지 파악하는 클래스
 * input : 'Queue.cpp'에 속한 멤버 변수의 이름 uNode(string)
 * output
 * MethodUsingPrint() : classInfo 클래스에서 멤버 변수 이름(node)을 받아와서, 각 메소드 별로 해당 멤버 변수가 존재하는지 파악.
 * 						메소드 바디를 다시 토큰으로 나눠 파싱한 후, 존재한다면 그 메소드의 이름을 methodName에 저장.
 * getMemberUsing() : 해당 멤버 변수를 사용한 메소드의 이름(ArrayList methodName) 반환
 */

import java.util.*;

public class MethodUsingMember {
	private String[] context;
	private int i=0;
	private int tokenNum;
	private ClassInfo ci;
	private ArrayList<String> methodName; //
	private String dNode; // 생성자에서 매개변수로 받은 uNode 값 저장
	
	public MethodUsingMember(String dNode){ //생성자
		this.dNode=dNode;
		methodName = new ArrayList<String>();
		
		MethodUsingPrint(tokenNum);
	}
	
	public void MethodUsingPrint(int tokenNum) {
		ci = new ClassInfo();
		
		for(int j=0; j<ci.getMName().size(); j++){
			String[] temp = (ci.getMName().get(j)).split("\\(");
			MethodClass mc=new MethodClass(temp[0]);
			String s = mc.getMethod().toString();
			StringTokenizer st = new StringTokenizer(s," \t\n\r=%+-*/	{}:;()[] ",true);
			tokenNum=st.countTokens();
			context = new String[tokenNum];
			i = 0;
			
			while (st.hasMoreTokens()){
				String token=st.nextToken();
				context[i]=token;
				i++;
			}
			
			for(int k=0; k<tokenNum; k++) {
				if(context[k].contains(dNode) && !(methodName.contains(temp[0]))) {
					methodName.add(temp[0]);
					break;
				}		
			}
		}
}
	
	public ArrayList<String> getMembersUsing() {
		return methodName;
	}
}
