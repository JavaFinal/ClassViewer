import java.util.*;

public class MethodUsingMember {
	private String[] context;
	private int i=0;
	private int tokenNum;
	private ClassInfo ci;
	private ArrayList<String> methodName;
	private String node;
	
	public MethodUsingMember(String uNode){
		this.node=uNode;
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
				if(context[k].contains(node) && !(methodName.contains(temp[0]))) {
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
