import java.io.*;
import java.util.StringTokenizer;

public class ReadFileData {
	private StringBuffer buffer;
	private FileInputStream file;
	int b;
	
	public ReadFileData() {
		b=0;
		buffer=new StringBuffer();
		file = null;
		try{
			file = new FileInputStream("Queue.cpp");
			b=file.read();
			while(b!=-1){
				buffer.append((char)b);
				b=file.read();
			}
//			System.out.println(buffer);
		} catch(FileNotFoundException e){
			System.out.println("Oops : FileNotFoundException");
		} catch(IOException e){
			System.out.println("Input error");
		}
	}
	public StringBuffer getFileSB(){
		return buffer;
	}
	public static void main(String[] args){
		new ReadFileData();
	}
}
