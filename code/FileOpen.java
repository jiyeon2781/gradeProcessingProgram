package function;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Vector;

public class FileOpen {
	
	private static StuInfoVector std;
	private static Vector<StuInfoVector> siv = new Vector<StuInfoVector>();
	private static int num,mid,fin,home,total;
	private static double average;
	
	public static Vector<StuInfoVector> fileopen(File file) {
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line = "";
			
			while((line = br.readLine()) != null) {
				String[] lines = line.split(",");
				
				num = Integer.parseInt(lines[0]);
				mid = Integer.parseInt(lines[2]);
				fin = Integer.parseInt(lines[3]);
				home = Integer.parseInt(lines[4]);
				
				total = mid+fin+home;
				average = (double)total/3;
				DecimalFormat fm = new DecimalFormat(".##");
				String st = fm.format(average);
				std = new StuInfoVector(num,lines[1],mid,fin,home,total,Double.parseDouble(st),"NULL");
				siv.add(std);
				
			}
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return siv;
	}


}
