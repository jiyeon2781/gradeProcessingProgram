package function;

public class CustomerVector { //예약자 정보를 담을 벡터
	public int number; //접수번호
	public String name; //이름
	public int mileage; //마일리지
	public String join_year; //가입연도
	public double priority_customer; //우선순위
	
	public CustomerVector(int number,String name,int mileage,String join_year,double priority_customer) {
		this.number = number;
		this.name = name;
		this.mileage = mileage;
		this.join_year = join_year;
		this.priority_customer = priority_customer;
	}
	
	public int GetNumber() { //접수 번호의 값을 반환
		return number;
	}
	public String GetName() { //이름을 반환
		return name;
	}
	public int GetMileage() { //마일리지의 값을 반환
		return mileage;
	}
	public String GetJoin_year() { //가입연도를 반환
		return join_year;
	}
	public double GetPriority_customer() { //우선순위의 값을 반환
		return priority_customer;
	}
	
}
