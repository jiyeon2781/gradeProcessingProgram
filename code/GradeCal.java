package function;

import java.io.File;
import java.util.Collections;
import java.util.Vector;

public class GradeCal {
	private static Vector<StuInfoVector> siv = new Vector<StuInfoVector>();
	private static double Aclass,Bclass,Cclass,Dclass;
	private static int count;
	
	
	public static Vector<StuInfoVector> gradecal(File file) {
		siv = FileOpen.fileopen(file);
		Collections.sort(siv,new Sort.averageSort());
		count = siv.size();
		Aclass = (double)count*2/10;
		Bclass = Aclass + ((double)count*3/10);
		Cclass = Bclass + ((double)count*4/10);
		Dclass = Cclass + ((double)count*1/10);
		
		for(int i = 0; i < count;i++) {
			if(i +1 <= Aclass) {
				siv.elementAt(i).grade = "A";
			}
			else if (Aclass < i +1 && i +1 <= Bclass) {
				siv.elementAt(i).grade = "B";
			}
			else if (Bclass < i +1 && i +1 <= Cclass) {
				siv.elementAt(i).grade = "C";
			}
			else if (Cclass < i +1 && i +1 <= Dclass) {
				siv.elementAt(i).grade = "D";
			}
		}
		for(int i = 0; i < count - 1 ; i++) {
			if(siv.elementAt(i).average == siv.elementAt(i+1).average) {
				siv.elementAt(i).grade = siv.elementAt(i+1).grade;
			}
		}
		return siv;
		
	}
	
}
