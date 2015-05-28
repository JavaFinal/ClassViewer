import java.util.*;

public class ClassInfo {
	private Value v;
	private StringBuffer buffer;
	private String[] context;
	private int i=0;
	private int tokenNum;
	private String[] nameM, nameD;
	private String[] typeM, typeD;
	private String[] accessM, accessD;
	public static String className=null;
		
	public ClassInfo(){
		v = new Value();
		buffer=v.getBuffer();
		StringTokenizer st = new StringTokenizer(buffer.toString()," \t\n\r	{}: ",false);
		tokenNum=st.countTokens();
		context = new String[tokenNum];
		nameM = new String[tokenNum];
		accessM = new String[tokenNum];
		typeM = new String[tokenNum];
		nameD = new String[tokenNum];
		accessD = new String[tokenNum];
		typeD = new String[tokenNum];
		
		while (st.hasMoreTokens()){
			String token=st.nextToken();
			context[i]=token;
	//		System.out.println(context[i]);
			i++;
		}
		ClassPrint(tokenNum);
	}
	
	public void ClassPrint(int tokenNum) {
		int cntM=0, cntD=0;
		for (i=0;i<tokenNum;i++){
			if(context[i].contains("class")){
				className = context[i+1];
				for(int j=i+1;j<tokenNum;j++){
					if(context[j].equals("public")){
						String accessTemp = context[j]; // access�� ���� �ӽ÷� �����ϱ� ���� ����
						for(int k=j+1;k<tokenNum;k++){
							if(context[k].equals("private")){
								break;
							}
							if(context[k].contains(";")){ // �޼ҵ� ���� ������ �ش��ϴ� ��� ����
								if(!context[k-1].contains(";")&&!context[k-1].equals(accessTemp)){
									typeM[cntM] = context[k-1];
									String[] n=context[k].split(";");;
									nameM[cntM]=n[0];
									accessM[cntM] = accessTemp;
									cntM++;
								} else{
									typeM[cntM] = "void";
									String[] n=context[k].split(";");;
									nameM[cntM]=n[0];
									accessM[cntM] = accessTemp;
									cntM++;
								}
							}
						}					
					}else if(context[j].contains("private")){
						String accessTemp = context[j]; // access�� ���� �ӽ÷� �����ϱ� ���� ����
						for(int k=j+1;k<tokenNum;k++){
							if(context[k].equals(";")){
								break;
							}
							if(context[k].contains(";")){
								accessD[cntD] = accessTemp;
								if(context[k].contains("[")){
									String[] n=context[k].split("\\[");;
									nameD[cntD]=n[0];
									typeD[cntD] = context[k-1]+"[]";
								} else{
									String[] n=context[k].split(";");;
									nameD[cntD]=n[0];
									typeD[cntD] = context[k-1];
								}
								cntD++;
								//System.out.println(access[k]);
							}
						}
					}
				}
				break;
			}
		}
/*		for(int z=0; z<cntM;z++)
		System.out.println(nameM[z]+" "+typeM[z]+" "+accessM[z]);
		for(int z=0; z<cntD;z++)
		System.out.println(nameD[z]+" "+typeD[z]+" "+accessD[z]);*/
	}
	public String[] getMName(){
		return nameM;
	}
	public String[] getMType(){
		return typeM;
	}
	public String[] getMAccess(){
		return accessM;
	}
	public String[] getDName(){
		return nameD;
	}
	public String[] getDType(){
		return typeD;
	}
	public String[] getDAccess(){
		return accessD;
	}
}
