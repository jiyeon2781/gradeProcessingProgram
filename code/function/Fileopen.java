package function;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Vector;

public class Fileopen {
	private static Vector<CustomerVector> customerList = new Vector<CustomerVector>(); // ����Ʈ ������ ������ ���� ����
	private static CustomerVector vector;

	public static Vector<CustomerVector> fileopen(File file) { // �����͸� ������ ���Ϳ� ����
		String[] strings = null;
		String line = null;
		try {

			BufferedReader rd = new BufferedReader(new InputStreamReader(new FileInputStream(file), "euc-kr"));

			strings = null;
			while ((line = rd.readLine()) != null) {
				strings = null;
				strings = line.split("\t");
				for (int i = 0; i < strings.length; i++) {
					strings[i] = strings[i].trim();
				}
				
				vector = new CustomerVector(Integer.parseInt(strings[0]), strings[1], Integer.parseInt(strings[2]),
						strings[3],0);
				customerList.add(vector);

			}

			rd.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return customerList;

	}

}
