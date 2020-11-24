package function;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

public class SaveStuInfo {
	public static void save(File file, Vector<StuInfoVector> siv, Vector<StuInfoVector> saveVec) {
		try {
			FileWriter fw = new FileWriter(file);
			fw.write("�й�,�̸�,�߰� ����,�⸻ ����,���� ����,����,���,����\r\n");
			for (int i = 0; i < siv.size(); i++) { // ������ �κ��� ��
				fw.write(siv.elementAt(i).num + "," + siv.elementAt(i).name + "," + siv.elementAt(i).midScore + ","
						+ siv.elementAt(i).finScore + "," + siv.elementAt(i).homeScore + "," + siv.elementAt(i).total
						+ "," + siv.elementAt(i).average + "," + siv.elementAt(i).grade + "\r\n");
			}
			fw.write(saveVec.elementAt(0).nu1 + "," + saveVec.elementAt(0).nu2 + "," + saveVec.elementAt(0).midAver
					+ "," + saveVec.elementAt(0).finAver + "," + saveVec.elementAt(0).homeAver + ","
					+ saveVec.elementAt(0).totalAver + "," + saveVec.elementAt(0).aver + "," + saveVec.elementAt(0).nu3
					+ "\r\n");
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
