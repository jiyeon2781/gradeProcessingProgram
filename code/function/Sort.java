package function;

import java.util.Comparator;


public class Sort { //����

	public static class prioritycompare implements Comparator<CustomerVector> { //�켱���� ��������
		public int compare(CustomerVector arg0, CustomerVector arg1) {
			return arg0.GetPriority_customer() > arg1.GetPriority_customer() ? -1
					: arg0.GetPriority_customer() < arg1.GetPriority_customer() ? 1 : 0;
		}
		
	}
	
	public static class prioritycompare2 implements Comparator<CustomerVector> { //�켱���� ��������
		public int compare(CustomerVector arg0, CustomerVector arg1) {
			return arg0.GetPriority_customer() < arg1.GetPriority_customer() ? -1: arg0.GetPriority_customer() > arg1.GetPriority_customer() ? 1 : 0;
		}
		
	}
	
	
	public static class numberascend implements Comparator<CustomerVector> { //������ȣ ��������
		public int compare(CustomerVector arg0, CustomerVector arg1) {
			return arg0.GetNumber()<arg1.GetNumber() ? -1 : arg0.GetNumber() > arg1.GetNumber() ? 1:0;
		}
		
	}
	public static class numberdescend implements Comparator<CustomerVector> { //������ȣ ��������
		public int compare(CustomerVector arg0, CustomerVector arg1) {
			return arg0.GetNumber()> arg1.GetNumber() ? -1 : arg0.GetNumber() < arg1.GetNumber() ? 1:0;
		}
		
	}
	public static class nameascend implements Comparator<CustomerVector> { //�̸� ��������
		public int compare(CustomerVector arg0, CustomerVector arg1) {
			return arg0.GetName().compareTo(arg1.GetName());
		}
		
	}
	public static class nameadescend implements Comparator<CustomerVector> { //�̸� ��������
		public int compare(CustomerVector arg0, CustomerVector arg1) {
			return arg1.GetName().compareTo(arg0.GetName());
		}
		
	}
	public static class mileageascend implements Comparator<CustomerVector> { //���ϸ��� ��������
		public int compare(CustomerVector arg0, CustomerVector arg1) {
			return arg0.GetMileage()<arg1.GetMileage() ? -1 : arg0.GetMileage() > arg1.GetMileage() ? 1:0;
		}
		
	}
	public static class mileagedescend implements Comparator<CustomerVector> { //���ϸ��� ��������
		public int compare(CustomerVector arg0, CustomerVector arg1) {
			return arg0.GetMileage()>arg1.GetMileage() ? -1 : arg0.GetMileage() < arg1.GetMileage() ? 1:0;
		}
		
	}
	public static class joinyearascend implements Comparator<CustomerVector> { //���Գ⵵ ��������
		public int compare(CustomerVector arg0, CustomerVector arg1) {
			return arg0.GetJoin_year().compareTo(arg1.GetJoin_year());
		}
		
	}
	public static class joinyeardescend implements Comparator<CustomerVector> { //���Գ⵵ ��������
		public int compare(CustomerVector arg0, CustomerVector arg1) {
			return arg1.GetJoin_year().compareTo(arg0.GetJoin_year());
		}
		
	}
}
