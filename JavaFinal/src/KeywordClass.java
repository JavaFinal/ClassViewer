import java.util.*;

public class KeywordClass {
	private StringBuffer buffer;
	private String[] context;
	private int i=0;
	private int tokenNum;
	private StringBuffer keywords;
	private boolean keywordFind;
	private String[] keywordBuffer;
	private int j=0;
	
	public KeywordClass(){
		buffer=Value.buffer;
		StringTokenizer st = new StringTokenizer(buffer.toString()," \n\r	():;{}",true);
		tokenNum=st.countTokens();
		context = new String[tokenNum];
		keywords=new StringBuffer();
		keywordBuffer = new String[tokenNum];
		
		while (st.hasMoreTokens()){
			String token=st.nextToken();
			context[i]=token;
			i++;
		}
		
		printKeyword(tokenNum);
	}
	
	public void printKeyword(int tokenNum) {
		for (i=0;i<tokenNum;i++){
			if((context[i].contains("class")) || (context[i].contains("public")) || (context[i].contains("void")) || 
					(context[i].contains("bool")) || (context[i].contains("int")) || (context[i].contains("private")) || 
					(context[i].contains("if")) || (context[i].contains("return")) || (context[i].contains("else")) ||
					(context[i].contains("true")) || (context[i].contains("false"))) {
				for(String value : keywordBuffer) { // keyword�� �ߺ��� �ؼ��ϱ� ���� ���ε� ���� �ذ���� ����
					if(value == context[i])
						continue; continue;
				}
				keywordBuffer[i] = context[i];
				j++;	
				System.out.print(context[i]+", ");
				System.out.println(keywordBuffer[i]);
				
			}
		}
	}

	public static void main(String[] args) {
		new KeywordClass();
	}

}
