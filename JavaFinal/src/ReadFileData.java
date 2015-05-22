import java.io.*;

public class ReadFileData {
	private FileInputStream file;
	private Value v;
	int b;
	
	public ReadFileData(String fileName) {
		b=0;
		file = null;
		v = new Value();
		try{
			file = new FileInputStream(fileName);
			b=file.read();
			while(b!=-1){
				v.getBuffer().append((char)b);
				b=file.read();
			}
//			System.out.println(v.getBuffer());
//			v.setBuffer(v.getBuffer());
		} catch(FileNotFoundException e){
			System.out.println("Oops : FileNotFoundException");
		} catch(IOException e){
			System.out.println("Input error");
		}
	}
}
