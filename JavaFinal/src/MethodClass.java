import java.util.*;


public class MethodClass {
	private String[] context;
	private int i=0;
	private int tokenNum;
	private StringBuffer method;
	private Value v;
	private String node1;
	public MethodClass(String cNode){
		this.node1=cNode;
//		System.out.println(this.node);
		v=new Value();
		StringTokenizer st = new StringTokenizer(v.getBuffer().toString(),"\n\r	(){}",true);
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
		for (int k=0;k<tokenNum;k++){
			if(context[k].contains("Queue::"+node1)){
				for(int j=k+1;j<tokenNum;j++){
					if(context[j].equals("{"))
						flag1++;
					if(context[j].equals("}"))
						flag2++;
					if(flag1!=flag2){
						if(context[j].equals("{")&&flag1==1){}
						else method=method.append(context[j]);
					} else if(flag1==flag2&&flag1!=0&&flag2!=0)
						break;
				}
			}
		}
	}
/*	
	public void printMethod(int tokenNum){
		for (i=0;i<tokenNum;i++){
			if(context[i].contains("Queue::"+node1)){
				for(int j=i+1;j<tokenNum;j++){
					if(context[j].equals("{")){
						method=method.append(context[j]);
//						System.out.print(context[j]);
						for(int k=j+1;k<tokenNum;k++){
							if(context[k].contains("Queue::"))
								break;
							method=method.append(context[k]);
//							System.out.print(context[k]);
						}
						break;
					}
				}
				break;
			}
		}
	}
*/
	public StringBuffer getMethod() {
		// TODO Auto-generated method stub
		return method;
	}
}
