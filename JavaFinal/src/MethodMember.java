import java.util.*;

public class MethodMember {
	private StringBuffer buffer;
	private String[] context;
	private int i=0;
	private int tokenNum;
	private ClassInfo ci;
	private ArrayList<String> methodDatas;
	private String node1;
	
	public MethodMember(String mNode){
		node1 = mNode;
		MethodClass mc=new MethodClass(mNode);
		buffer = mc.getMethod();
		StringTokenizer st = new StringTokenizer(buffer.toString()," \t\n\r()=%+-*/	{}:; ",true);
		tokenNum=st.countTokens();
		context = new String[tokenNum];
		methodDatas = new ArrayList<String>();
		
		while (st.hasMoreTokens()){
			String token=st.nextToken();
			context[i]=token;
//			System.out.println(context[i]);
			i++;
		}

		MemberPrint(tokenNum);
	}
	
	public void MemberPrint(int tokenNum) {
		ci = new ClassInfo();
		
		for(i=0;i<tokenNum;i++){
			for(int j=0; j<ci.getDName().size(); j++)
				if(context[i].contains(ci.getDName().get(j))){
					if(context[i].contains("[")) {
						String[] n = context[i].split("\\[");
						methodDatas.add(n[0]);
						break;
					} else if(methodDatas.contains(context[i]) == false){
						methodDatas.add(context[i]); 
						break;
					}
				}
		}	
	}

	
	public ArrayList<String> getMembers() {
		return methodDatas;
	}
}
