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
	private static Vector<Integer> date_v; //가입연도 일수 계산해서 벡터에 넣음
	private static int Nyear, Nmonth, Nday; //현재 날짜를 받아오는 정수
	private static double maxmileage; //최대 마일리지
	private static double maxyear; //최대 일수

	public static void DateInterval(Vector<CustomerVector> vector, Vector<Integer> date_v) {
		Calendar calendar = new GregorianCalendar(Locale.KOREA);
		Nyear = calendar.get(Calendar.YEAR);
		Nmonth = calendar.get(Calendar.MONTH) + 1;
		Nday = calendar.get(Calendar.DAY_OF_MONTH);  //현재 날짜를 받아옴

		StringBuilder sb = new StringBuilder(); //"yyyy-MM-dd"형식으로 고침
		sb.append(Integer.toString(Nyear));
		sb.append("-");
		sb.append(Integer.toString(Nmonth));
		sb.append("-");
		sb.append(Integer.toString(Nday));

		String str = null;

		for (int i = 0; i < vector.size(); i++) { //현재날짜와 받아온 날짜를 계산함
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
		for (int i = 0; i < vector.size(); i++) { //최대 마일리지 찾음
			if (vector.elementAt(i).mileage > maxmileage) {
				maxmileage = vector.elementAt(i).mileage;
			}
		}

		maxyear = 0;
		for (int i = 0; i < vector.size(); i++) { //최대 일수를 찾음
			if (date_v.elementAt(i) > maxyear) {
				maxyear = date_v.elementAt(i);
			}
		}

		for (int i = 0; i < vector.size(); i++) { //우선순위를 계산해 벡터에 넣음
			priority = (double) vector.elementAt(i).number - ((double) vector.elementAt(i).mileage / maxmileage * 10)
					- ((double) date_v.get(i) / maxyear * 5);
			vector.elementAt(i).priority_customer = priority;
		}

		

		return vector;
	}

}
