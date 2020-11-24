package function;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Vector;

public class PriorityCal {
	private static double priority;
	private static Vector<Integer> date_v; //���Կ��� �ϼ� ����ؼ� ���Ϳ� ����
	private static int Nyear, Nmonth, Nday; //���� ��¥�� �޾ƿ��� ����
	private static double maxmileage; //�ִ� ���ϸ���
	private static double maxyear; //�ִ� �ϼ�

	public static void DateInterval(Vector<CustomerVector> vector, Vector<Integer> date_v) {
		Calendar calendar = new GregorianCalendar(Locale.KOREA);
		Nyear = calendar.get(Calendar.YEAR);
		Nmonth = calendar.get(Calendar.MONTH) + 1;
		Nday = calendar.get(Calendar.DAY_OF_MONTH);  //���� ��¥�� �޾ƿ�

		StringBuilder sb = new StringBuilder(); //"yyyy-MM-dd"�������� ��ħ
		sb.append(Integer.toString(Nyear));
		sb.append("-");
		sb.append(Integer.toString(Nmonth));
		sb.append("-");
		sb.append(Integer.toString(Nday));

		String str = null;

		for (int i = 0; i < vector.size(); i++) { //���糯¥�� �޾ƿ� ��¥�� �����
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			try {
				str = vector.elementAt(i).GetJoin_year();
				Date beginDate = formatter.parse(str);
				Date endDate = formatter.parse(sb.toString());
				long diff = endDate.getTime() - beginDate.getTime();
				long diffDays = diff / (24 * 60 * 60 * 1000);
				date_v.addElement((int) diffDays);

			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}

	public static Vector<CustomerVector> PriorityCalculation(Vector<CustomerVector> vector) {
		
		date_v = new Vector<Integer>();
		DateInterval(vector, date_v);

		maxmileage = 0; 
		for (int i = 0; i < vector.size(); i++) { //�ִ� ���ϸ��� ã��
			if (vector.elementAt(i).mileage > maxmileage) {
				maxmileage = vector.elementAt(i).mileage;
			}
		}

		maxyear = 0;
		for (int i = 0; i < vector.size(); i++) { //�ִ� �ϼ��� ã��
			if (date_v.elementAt(i) > maxyear) {
				maxyear = date_v.elementAt(i);
			}
		}

		for (int i = 0; i < vector.size(); i++) { //�켱������ ����� ���Ϳ� ����
			priority = (double) vector.elementAt(i).number - ((double) vector.elementAt(i).mileage / maxmileage * 10)
					- ((double) date_v.get(i) / maxyear * 5);
			vector.elementAt(i).priority_customer = priority;
		}

		

		return vector;
	}

}
