public class Value {
	private static String fileName=null;
	private static StringBuffer buffer=new StringBuffer();
	
	public void setFileName(String name){
		fileName=name;
	}
	public String getFileName(){
		return fileName;
	}
	public void setBuffer(StringBuffer buf){
		buffer=buf;
	}
	public StringBuffer getBuffer(){
		return buffer;
	}
}
