import java.util.*;

public class ClassInfo {
	private ReadFileData rfd;
	private StringBuffer buffer;
	private String[] context;
	private int i=0;
	private int tokenNum;
	private String[] name;
	private String[] type;
	private String[] access;
	
	public ClassInfo(){
		rfd=new ReadFileData();
		buffer=rfd.getFileSB();
		StringTokenizer st = new StringTokenizer(buffer.toString()," \t\n\r	(){}: ",true);
		tokenNum=st.countTokens();
		context = new String[tokenNum];
		name = new String[tokenNum];
		access = new String[tokenNum];
		type = new String[tokenNum];
		
		while (st.hasMoreTokens()){
			String token=st.nextToken();
			context[i]=token;
//			System.out.println(context[i]);
			i++;
		}
		ClassPrint(tokenNum);
	}
	
	public void ClassPrint(int tokenNum) {
		for (i=0;i<tokenNum;i++){
			if(context[i].contains("class")){
				for(int j=i+1;j<tokenNum;j++){
					if(context[j].contains("public")){
						String accessTemp = context[j]; // access의 값을 임시로 저장하기 위한 변수
						for(int k=j+2;k<tokenNum;k++){
							if(context[k].equals("private")){
								break;
							}
								if((context[k].contains("bool")) || (context[k].contains("void")) || (context[k].contains("int"))) {
									if(context[k-1].contains("(")) // 메소드 인자 정보에 해당하는 경우 제외
										continue;
									else {
										type[k] = context[k];
										access[k] = accessTemp;
									}
									for(int l=k+2;l<tokenNum;l++) {
										name[l] = context[l];
										/*선언된 메소드의 이름 뒤 괄호들을 토큰에서 제거*/
										if(context[l+1].equals("(")){
											for(int m=l+1;m<tokenNum;m++){
												if(context[m].equals(")"))
													break;
											}
											break;
										}
									}
								}
								else if((context[k].equals("Queue")) || (context[k].contains("~"))) {
									access[k] = accessTemp;
									type[k] = "void";
									name[k] = context[k];
								}
							}					
					}else if(context[j].contains("private")){
						String accessTemp = context[j]; // access의 값을 임시로 저장하기 위한 변수
						for(int k=j+1;k<tokenNum;k++){
							if(context[k].contains("};")){
								break;
							}
							if(context[k].contains("int")){
								access[k] = accessTemp;
								System.out.println(access[k]);
								type[k] = "int";
							}
							for(int l=k+1;l<k+3;k++) {
								name[l] = context[l];
								if(context[l+1].equals("[")){
									type[k].concat("[]");
								}
								//System.out.println(access[k]+", "+type[k]+", "+name[l]);
							}
						}
					}
					
				}
				break;
			}
		}
	}
	
	public static void main(String[] args) {
		new ClassInfo();
	}

}
