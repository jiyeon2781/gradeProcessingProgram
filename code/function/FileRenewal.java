package function;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Vector;

public class FileRenewal { //텍스트 파일을 처음부터 다시 씀
	public static void renewal(File file,Vector<CustomerVector> priorityVector) {
		try {
			
			BufferedWriter fw = new BufferedWriter(new FileWriter(file)); 
			for(int i = 0; i < priorityVector.size(); i++) {
				fw.write(priorityVector.elementAt(i).number +"\t"+ priorityVector.elementAt(i).name +"\t"+ priorityVector.elementAt(i).mileage +"\t"+ priorityVector.elementAt(i).join_year +"\r\n");
			}
			
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
