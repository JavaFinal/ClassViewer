import java.util.*;

public class ClassInfo {
	private Value v;
	private StringBuffer buffer;
	private String[] context;
	private int i=0;
	private int tokenNum;
//	private String[] nameM, nameD;
//	private String[] typeM, typeD;
//	private String[] accessM, accessD;
	private ArrayList<String> nameM, nameD;
	private ArrayList<String> typeM, typeD;
	private ArrayList<String> accessM, accessD;

	public static String className=null;
		
	public ClassInfo(){
		v = new Value();
		buffer=v.getBuffer();
		StringTokenizer st = new StringTokenizer(buffer.toString()," \t\n\r	{}: ",false);
		tokenNum=st.countTokens();
		context = new String[tokenNum];
		nameM = new ArrayList<String>();
		accessM = new ArrayList<String>();
		typeM = new ArrayList<String>();
		nameD = new ArrayList<String>();
		accessD = new ArrayList<String>();
		typeD = new ArrayList<String>();
		
		while (st.hasMoreTokens()){
			String token=st.nextToken();
			context[i]=token;
	//		System.out.println(context[i]);
			i++;
		}
		ClassPrint(tokenNum);
	}
	
	public void ClassPrint(int tokenNum) {
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
									typeM.add(context[k-1]);
									String[] n=context[k].split(";");;
									nameM.add(n[0]);
									accessM.add(accessTemp);
								} else{
									typeM.add("void");
									String[] n=context[k].split(";");;
									nameM.add(n[0]);
									accessM.add(accessTemp);
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
								accessD.add(accessTemp);
								if(context[k].contains("[")){
									String[] n=context[k].split("\\[");;
									nameD.add(n[0]);
									typeD.add(context[k-1]+"[]");
								} else{
									String[] n=context[k].split(";");;
									nameD.add(n[0]);
									typeD.add(context[k-1]);
								}
								//System.out.println(access[k]);
							}
						}
					}
				}
				break;
			}
		}
/*		for(int z=0; z<nameM.size();z++)
		System.out.println(nameM.get(z)+" "+typeM.get(z)+" "+accessM.get(z));
		for(int z=0; z<nameD.size();z++)
		System.out.println(nameD.get(z)+" "+typeD.get(z)+" "+accessD.get(z));*/
	}
	public ArrayList<String> getMName(){
		return nameM;
	}
	public ArrayList<String> getMType(){
		return typeM;
	}
	public ArrayList<String> getMAccess(){
		return accessM;
	}
	public ArrayList<String> getDName(){
		return nameD;
	}
	public ArrayList<String> getDType(){
		return typeD;
	}
	public ArrayList<String> getDAccess(){
		return accessD;
	}
}
