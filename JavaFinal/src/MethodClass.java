import java.util.*;


public class MethodClass {
	private String[] context;
	private int i=0;
	private int tokenNum;
	private StringBuffer method;

	private String node;
	public MethodClass(String cNode){
		this.node=cNode;
		StringTokenizer st = new StringTokenizer(Value.buffer.toString(),"\n\r	(){}",true);
//		System.out.println(v.getBuffer());
		tokenNum=st.countTokens();
		context = new String[tokenNum];
		method=new StringBuffer();
		
		while (st.hasMoreTokens()){
			String token=st.nextToken();
			context[i]=token;
//			System.out.println(context[i]);
			i++;
		}
		printMethod1(tokenNum);
//		System.out.println(method);		
	}
	public void printMethod1(int tokenNum){
		int flag1=0;
		int flag2=0;
		ClassInfo ci = new ClassInfo();
		for (int k=0;k<tokenNum;k++){
			if(context[k].contains(ci.className+"::"+node)){
				for(int j=k+1;j<tokenNum;j++){
					if(context[j].equals("{"))
						flag1++;
					if(context[j].equals("}"))
						flag2++;
					if(flag1!=flag2){
						if(!(context[j].equals("{")&&flag1==1)){
							method=method.append(context[j]);
//							System.out.println(j);
						}
					} else if(flag1==flag2&&flag1!=0&&flag2!=0)
						break;
				}
			}
		}
	}
	public StringBuffer getMethod() {
		// TODO Auto-generated method stub
		return method;
	}
}
