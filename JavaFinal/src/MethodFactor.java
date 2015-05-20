import java.util.StringTokenizer;

public class MethodFactor {
	private ReadFileData rfd;
	private StringBuffer buffer;
	private String[] context;
	private int i=0;
	private int tokenNum;
	public MethodFactor(){
		rfd=new ReadFileData();
		buffer=rfd.getFileSB();
		StringTokenizer st = new StringTokenizer(buffer.toString(),"\n\r	(){}",true);
		tokenNum=st.countTokens();
		context = new String[tokenNum];
		while (st.hasMoreTokens()){
			String token=st.nextToken();
			context[i]=token;
//			System.out.println(context[i]);
			i++;
		}
		printMF(tokenNum);
	}
	
	public void printMF(int tokenNum){
		for (i=0;i<tokenNum;i++){
			if(context[i].contains("Queue::"+"~Queue")){
				for(int j=i+1;j<tokenNum;j++){
					if(context[j].equals("(")){
						for(int k=j+1;k<tokenNum;k++){
							if(context[k].equals(")"))
								break;
							System.out.print(context[k]);
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
		new MethodFactor();
	}

}
