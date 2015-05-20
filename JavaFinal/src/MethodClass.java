import java.util.*;


public class MethodClass {
	private ReadFileData rfd;
	private StringBuffer buffer;
	private String[] context;
	private int i=0;
	private int tokenNum;
	private StringBuffer method;
	
	public MethodClass(){
		rfd=new ReadFileData();
		buffer=rfd.getFileSB();
		StringTokenizer st = new StringTokenizer(buffer.toString(),"\n\r	(){}",true);
		tokenNum=st.countTokens();
		context = new String[tokenNum];
		method=new StringBuffer();
		
		while (st.hasMoreTokens()){
			String token=st.nextToken();
			context[i]=token;
//			System.out.println(context[i]);
			i++;
		}
		
		printMethod(tokenNum);
		System.out.println(method);		
	}
	
	public void printMethod(int tokenNum){
		for (i=0;i<tokenNum;i++){
			if(context[i].contains("Queue::"+"IsEmpty")){
				for(int j=i+1;j<tokenNum;j++){
					if(context[j].equals("{")){
						method=method.append(context[j]);
//						System.out.print(context[j]);
						for(int k=j+1;k<tokenNum;k++){
							if(context[k].contains("Queue::"+"IsFull"))
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MethodClass();
	}

}
