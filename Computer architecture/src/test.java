package cpuarc;
import java.util.*;

public class test {
	
	public static int Case1(int i) {
		if(i == 1) {
			return 1;
		}else  {
			return i* Case1(i-1);
		}
	}
	
	public static int Case2(int i) {
		int result = 1;
		for(int j = i; j > 0; j--) {
			result = result * j;
		}
		return result;
	}
	
	public static int Case3(int i) {
		int result = 1;
		int j = i;
		if(j == 0) {
			return 0;
		}
		while(j !=0) {
			result = result *j;
			j--;
		}
		return result;
	}
	
	public static int Case4(int i) {
		int result = 1;
		int j = i;
		do {
			result = result *j;
			j--;
		}while(j != 0);
		return result;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int Case,num,result;
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("자연수를 계승하는 계산하는 메소드의 번호를 입력하세요:");
			System.out.println("(1)    회귀적메소드");
			System.out.println("(2)    반복적메소드 - For 문");
			System.out.println("(3)    반복적메소드 - While 문");
			System.out.println("(4)    반복적메소드 - Do While 문");
			System.out.println("(5)    종료");
			Case = sc.nextInt();
			if(Case == 5) {
				break;
			}
			System.out.print("계승값이 계산될 자연수 값을 입력하세요:");
			num = sc.nextInt();
			if(Case == 1) {
				result = Case1(num);
				System.out.println("회귀적 메소드를 사용해 얻어진 "+ num +"의 계승은" +result+"입니다");
			}
			else if(Case == 2) {
				result = Case2(num);
				System.out.println("For문을 사용해 얻어진 "+ num +"의 계승은" +result+"입니다");
			}
			else if(Case == 3) {
				result = Case3(num);
				System.out.println("While문을 사용해 얻어진 "+ num +"의 계승은" +result+"입니다");
				
			}
			else if(Case == 4) {
				result = Case4(num);
				System.out.println("Do While문을 사용해 얻어진 "+ num +"의 계승은" +result+"입니다");
	
			}
		}
		
		
	}
	
	
}
