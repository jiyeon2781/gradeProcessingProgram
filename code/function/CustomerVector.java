package function;

public class CustomerVector { //������ ������ ���� ����
	public int number; //������ȣ
	public String name; //�̸�
	public int mileage; //���ϸ���
	public String join_year; //���Կ���
	public double priority_customer; //�켱����
	
	public CustomerVector(int number,String name,int mileage,String join_year,double priority_customer) {
		this.number = number;
		this.name = name;
		this.mileage = mileage;
		this.join_year = join_year;
		this.priority_customer = priority_customer;
	}
	
	public int GetNumber() { //���� ��ȣ�� ���� ��ȯ
		return number;
	}
	public String GetName() { //�̸��� ��ȯ
		return name;
	}
	public int GetMileage() { //���ϸ����� ���� ��ȯ
		return mileage;
	}
	public String GetJoin_year() { //���Կ����� ��ȯ
		return join_year;
	}
	public double GetPriority_customer() { //�켱������ ���� ��ȯ
		return priority_customer;
	}
	
}
