package cpuarc;
import java.util.*;
public class 과제1 {
	public static long sqr(int a){
		int count = 0;
		long b = 1;
		while(count != a) {b*=2;count ++;}
		return b;}
	public static void trans(long k,int sig) {
		int []arr1 = new int[8] ;
		int []arr2 = new int[16];
		int []arr3 = new int[32];
		long []arr4 = new long[64];
		int i = 0,j=0,h=0,u=0,t=(int)k;
		long w = k;
		System.out.println("부호_크기");
		while(i!=8) {
			if(t == 0) {arr1[i] = 0;
			}else if(k<=sqr(7)-1 && k>=-sqr(7)+1) {
				arr1[i] = t%2; t /=2;
			}else {
				System.out.println("8비트로 표현할 수 없습니다");
				break;}i++;	}t= (int)k;
		while(j!=16) {
			if(t == 0) {
				arr2[j] = 0;
			}else if(k<=sqr(15)-1 && k>=-sqr(15)+1) {
				arr2[j] = (int) (t%2);
				t /= 2;
			}else {
				System.out.println("16비트로 표현할 수 없습니다");
				break;}j++;}t=(int)k;
		while(h!=32) {
			if(t == 0) {
				arr3[h]=0;
			}else if(k<=sqr(31)-1 && k>=-sqr(31)+1){
				arr3[h] = (int) (t%2);
				t /=2;
			}else {
				System.out.println("32비트로 표현할 수 없습니다");
				break;}h++;}
		while(u!=64) {
			if(w == 0) {
				arr4[u] = 0;
			}
			else if(k<=sqr(63)-1 && k>=-sqr(63)+1){
				arr4[u] = w%2;
				w /= 2;
			}
			else {
				System.out.print("64비트로 표현할 수 없습니다");
				break;}u++;}i--;j--;h--;u--;
		for(; i>=0; i--) {
			if(i == 7) {
				System.out.print("8비트:");
				if(sig == 1) {
					System.out.print(1);
				}else {
					System.out.print(0);}
			}else {
			System.out.print(arr1[i]);}
			if(i == 0) {
				System.out.println("");}}
		for(; j>=0; j--) {
			if(j == 15) {
				System.out.print("16비트:");
				if(sig == 1) {
					System.out.print(1);
				}else {System.out.print(0);}
			}else {System.out.print(arr2[j]);}
			if(j == 0) {System.out.println("");}}
		for(; h>=0; h--) {
			if(h == 31) {
				System.out.print("32비트:");
				if(sig == 1) {
					System.out.print(1);
				}
				else {
					System.out.print(0);
				}
			}
			else {
			System.out.print(arr3[h]);
			}
			if(h == 0) {
				System.out.println("");
			}
		}
		for(; u>=0; u--) {
			if(u == 63) {
				System.out.print("64비트:");
				if(sig == 1) {
					System.out.print(1);
				}else {
					System.out.print(0);
				}
			}
			else {
			System.out.print(arr4[u]);
			}
			if(u == 0) {
				System.out.println("");
			}
		}
		return;
	}
	
	public static void bo1(long b,int sig) {
		int []arr1 = new int[8] ;
		int []arr2 = new int[16];
		int []arr3 = new int[32];
		long []arr4 = new long[64];
		int i = 0,j=0,h=0,u=0,t=(int)b;
		long w=b;
		System.out.println("-1의 보수-");
		while(i!=8) {
			if(t == 0) {
				arr1[i] = 0;
			}else if(b<=sqr(7)-1 && b>=-sqr(7)+1) {
				arr1[i] = (int) (t%2);
				t /=2;
			}else {
				System.out.println("8비트로 표현할 수 없습니다");
				break;
			}i++;	
		}t=(int)b;
		while(j!=16) {
			if(t == 0) {
				arr2[j] = 0;
			}else if(b<=sqr(15)-1 && b>=-sqr(15)+1) {
				arr2[j] = (int) (t%2);
				t /= 2;
			}else {
				System.out.println("16비트로 표현할 수 없습니다");
				break;
			}j++;
		}t=(int)b;
		while(h!=32) {
			if(t == 0) {
				arr3[h]=0;
			}else if(b<=sqr(31)-1 && b>=-sqr(31)+1){
				arr3[h] = (int) (t%2);
				t /=2;
			}else {
				System.out.println("32비트로 표현할 수 없습니다");
				break;
			}h++;
		}
		while(u!=64) {
			if(w == 0) {
				arr4[u] = 0;
			}
			else if(b<=sqr(63)-1 && b>=-sqr(63)+1){
				arr4[u] = w%2;
				w /= 2;
			}
			else {
				System.out.print("64비트로 표현할 수 없습니다");
				break;
			}
			u++;
		}
		i--;j--;h--;u--;
		for(; i>=0; i--) {
			if(i == 7) {
				System.out.print("8비트:");
			}
			if(sig == 1) {
				if(arr1[i]==0) {
					System.out.print("1");
				}
				else {
					System.out.print("0");
				}
			}
			else {
			System.out.print(arr1[i]);
			}
			if(i == 0) {
				System.out.println("");
			}
		}
		for(; j>=0; j--) {
			if(j == 15) {
				System.out.print("16비트:");
			}
			if(sig == 1) {
				if(arr2[j]==0) {
					System.out.print("1");
				}
				else {
					System.out.print("0");
				}
			}
			else {
			System.out.print(arr2[j]);
			}
			if(j == 0) {
				System.out.println("");
			}
		}
		for(; h>=0; h--) {
			if(h == 31) {
				System.out.print("32비트:");
			}
			if(sig == 1) {
				if(arr3[h]==0) {
					System.out.print("1");
				}
				else {
					System.out.print("0");
				}
			}
			else {
			System.out.print(arr3[h]);
			}
			if(h == 0) {
				System.out.println("");
			}
		}
		for(; u>=0; u--) {
			if(u == 63) {
				System.out.print("64비트:");
			}
			if(sig == 1) {
				if(arr4[u]==0) {
					System.out.print("1");
				}
				else {
					System.out.print("0");
				}
			}
			else {
			System.out.print(arr4[u]);
			}
			if(u == 0) {
				System.out.println("");
			}
		}
		return;
	}
	
	public static void bo2(long b,int sig) {
		int []arr1 = new int[8] ;
		int []arr2 = new int[16];
		int []arr3 = new int[32];
		long []arr4 = new long[64];
		if(sig ==1) {
			b -= 1;
		}
		int i = 0,j=0,h=0,u=0,t=(int)b;
		long w = b;
		System.out.println("-2의 보수-");
		while(i!=8) {
			if(t == 0) {
				arr1[i] = 0;
			}else if(b<=sqr(7)-1 && b>=-sqr(7)) {
				arr1[i] = (int) (t%2);
				t /=2;
			}else {
				System.out.println("8비트로 표현할 수 없습니다");
				break;
			}i++;	
		}t=(int)b;
		while(j!=16) {
			if(t == 0) {
				arr2[j] = 0;
			}else if(b<=sqr(15)-1 && b>=-sqr(15)) {
				arr2[j] = (int) (t%2);
				t /= 2;
			}else {
				System.out.println("16비트로 표현할 수 없습니다");
				break;
			}j++;
		}t=(int)b;
		while(h!=32) {
			if(t == 0) {
				arr3[h]=0;
			}else if(b<=sqr(31)-1 && b>=-sqr(31)){
				arr3[h] = (int) (t%2);
				t /=2;
			}else {
				System.out.println("32비트로 표현할 수 없습니다");
				break;
			}h++;
		}t=(int)b;
		while(u!=64) {
			if(w == 0) {
				arr4[u] = 0;
			}
			else if(b<=sqr(63)-1 && b>=-sqr(63)){
				arr4[u] = w%2;
				w /= 2;
			}
			else {
				System.out.print("64비트로 표현할 수 없습니다");
				break;
			}
			u++;
		}
		i--;j--;h--;u--;
		for(; i>=0; i--) {
			if(i == 7) {
				System.out.print("8비트:");
			}
			if(sig == 1) {
				if(arr1[i]==0) {
					System.out.print("1");
				}
				else {
					System.out.print("0");
				}
			}
			else {
			System.out.print(arr1[i]);
			}
			if(i == 0) {
				System.out.println("");
			}
		}
		for(; j>=0; j--) {
			if(j == 15) {
				System.out.print("16비트:");
			}
			if(sig == 1) {
				if(arr2[j]==0) {
					System.out.print("1");
				}
				else {
					System.out.print("0");
				}
			}
			else {
			System.out.print(arr2[j]);
			}
			if(j == 0) {
				System.out.println("");
			}
		}
		for(; h>=0; h--) {
			if(h == 31) {
				System.out.print("32비트:");
			}
			if(sig == 1) {
				if(arr3[h]==0) {
					System.out.print("1");
				}
				else {
					System.out.print("0");
				}
			}
			else {
			System.out.print(arr3[h]);
			}
			if(h == 0) {
				System.out.println("");
			}
		}
		for(; u>=0; u--) {
			if(u == 63) {
				System.out.print("64비트:");
			}
			if(sig == 1) {
				if(arr4[u]==0) {
					System.out.print("1");
				}
				else {
					System.out.print("0");
				}
			}
			else {
			System.out.print(arr4[u]);
			}
			if(u == 0) {
				System.out.println("");
			}
		}
		return;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int sign1 = 0,sign2 = 0;
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.print("10진수 입력:(단, 종료를 원할경우 break 입력):");
			String a = sc.nextLine();
			if(a.equals("break")) {
				System.out.println("종료");
				break;
			}
			else {
				if(a.equals("-0")) {
					sign1 = 1;
				}
			long b = Long.parseLong(a);
				if(b<0) {
				b = -b;
				sign1 = 1;
				sign2 = 1;
				}
			trans(b,sign1);
			bo1(b,sign1);
			bo2(b,sign2);
			}
		}
	}
}
