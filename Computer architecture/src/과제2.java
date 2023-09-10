package cpuarc;
import java.util.Scanner;
public class 과제2 {
	public static void shift2(long a,long b) {
		System.out.println("");System.out.println("-Shift2-");
		long bit,count=0,i=0,carry=0,count2=0;
		long lengtha = (long)(Math.log10(a)+1);
		long lengthb = (long)(Math.log10(b)+1);
		if(lengtha<lengthb) {
			bit = lengthb;
		}else {
			bit = lengtha;
		}
		if(bit<=4) {
			bit =4;
		}else if(bit>64) {
			System.out.println("비트초과");
			return;
		}
		long ar[] = new long[(int)bit];
		long br[] = new long[(int)bit];
		long resulta[] = new long[(int)bit*2];
		long resultb[] = new long[(int)bit*2];
		while(i!=bit) {
			ar[(int)i] = a%10;
			br[(int)i] = b%10;
			resulta[(int)i] = 0;
			resulta[(int)i+(int)bit]=0;
			resultb[(int)i] = 0;
			resultb[(int)i+(int)bit]=0;
			a /=10;
			b /=10;
			i++;
	}i--;System.out.print(" ");
		for(;i>=0;i--) {
			System.out.print(ar[(int)i]);
		}System.out.println("<--피승수");System.out.print(" ");i=bit-1;
		if(ar[(int)bit-1]==1) {
			carry=1;
			for(long j=0; j<bit;j++) {
			if(ar[(int)j]==1) {
				if(carry==1) {
					ar[(int)j]=1;
					carry=0;
				}else {
					ar[(int)j]=0;
				}
			}else {
				if(carry==1) {
					ar[(int)j]=0;
				}else {
					ar[(int)j]=1;
				}
			}
			}count2++;
			for(;i>=0;i--) {
				System.out.print(ar[(int)i]);
			}System.out.println("<--피승수의 2의보수");System.out.print("X");i=bit-1;
		}carry=0;
		for(;i>=0;i--) {
			System.out.print(br[(int)i]);
		}System.out.println("<--승수");System.out.print(" ");i=bit-1;
		if(br[(int)bit-1]==1) {
			carry=1;
			for(long j=0; j<bit;j++) {
			if(br[(int)j]==1) {
				if(carry==1) {
					br[(int)j]=1;
					carry=0;
				}else {
					br[(int)j]=0;
				}
			}else {
				if(carry==1) {
					br[(int)j]=0;
				}else {
					br[(int)j]=1;
				}
			}
			}count2++;
			for(;i>=0;i--) {
				System.out.print(br[(int)i]);
			}System.out.println("<--승수의 2의보수");
		}carry=0;
		System.out.println("--------------------");
		for(long j=2*bit-1; j>=0; j--) {
			System.out.print(resultb[(int)j]);
		}System.out.println("<--누적부분곱");
		while(count!=bit) {
			for(long j =0;j<bit;j++) {
				resulta[(int)j+4]=ar[(int)j]*br[(int)count];
			}
			for(long j=2*bit-1; j>=0; j--) {
				System.out.print(resulta[(int)j]);	
			}System.out.println("<--계산");
			for(long j =0;j<2*bit;j++) {
				if(resultb[(int)j] == 0) { 
					if(carry ==1) {
						if(resulta[(int)j]==1){
							resultb[(int)j] = 0;
						}
						else {
							resultb[(int)j] = 1;
							carry=0;
						}
					}
					else {
						resultb[(int)j] = resulta[(int)j];
					}
				}
				else {
					if(resulta[(int)j]==1) {
							resultb[(int)j]=carry;
							carry=1;
					}
					else {
						if(carry==1) {
							resultb[(int)j]=0;
						}
						else {
							resultb[(int)j]=1;
						}
					}
				}
			}carry=0;
			for(long j=2*bit-1; j>=0; j--) {
				System.out.print(resultb[(int)j]);
			}System.out.println("<--누적부분곱");
			for(long j =0;j<2*bit-1;j++) {
				resultb[(int)j] = resultb[(int)j+1];
			}
			resultb[2*(int)bit-1]=0;
			count++;
			System.out.println("--------------------");
			for(long j=2*bit-1; j>=0; j--) {
				System.out.print(resultb[(int)j]);
		}System.out.println("<--오른쪽 쉬프트");
		}
		if(count2==1) {
			carry =1;
			for(long j = 0; j <2*bit;j++ ) {
				if(resultb[(int)j] == 1) {
					if(carry == 1) {
						resultb[(int)j] = 1;
						carry =0;
					}else {
						resultb[(int)j] = 0;
					}
				}else {
					if(carry == 1) {
						resultb[(int)j]=0;
					}else {
						resultb[(int)j]=1;
					}
				}
			}
			for(long j=2*bit-1; j>=0; j--) {
				System.out.print(resultb[(int)j]);
			}System.out.println("<--2의 보수전환");
		}
		
	}
	public static void booth(long a,long b){
		long ca = 0,cb = 0,bit,i=0,count=0,k=0,carry=0,carry2=0;
		long lengtha = (long)(Math.log10(a)+1);
		long lengthb = (long)(Math.log10(b)+1);
		if(lengtha<lengthb) {
			bit = lengthb;
		}else {
			bit = lengtha;
		}
		if(bit<=4) {
			bit =4;
		}else if(bit>64) {
			System.out.println("비트초과");
			return;
		}
		long ar[] = new long[(int)bit];
		long br[] = new long[(int)bit];
		long resulta[] = new long[(int)bit*2];
		long resultb[] = new long[(int)bit*2];
		while(i!=bit) {
				ar[(int)i] = a%10;
				br[(int)i] = b%10;
				resulta[(int)i] = 0;
				resulta[(int)i+(int)bit]=0;
				resultb[(int)i] = 0;
				resultb[(int)i+(int)bit]=0;
				a /=10;
				b /=10;
				i++;
		}i--;System.out.println("-Booth 알고리즘-");System.out.print(" ");
		for(;i>=0;i--) {
			System.out.print(ar[(int)i]);
		}System.out.println("<--피승수");System.out.print("x");i=bit-1;
		for(;i>=0;i--) {
			System.out.print(br[(int)i]);
		}System.out.println("<--승수");
		System.out.println("--------------------");
		for(long j=2*bit-1; j>=0; j--) {
			System.out.print(resultb[(int)j]);
		}System.out.println("<--누적부분곱");
		while(count!=bit) {
			if(count == 0) {
				k=0;
			}else {
				k=br[(int)count-1];
			}
			if(br[(int)count]==k) {
				for(int u =0; u<bit*2; u++) {
					resulta[(int)u]=0;
				}
			}else if(br[(int)count]>k){
				carry2=1;
				for(long j =0; j<2*bit; j++) {
					if(count>j) {
						resulta[(int)j] = 0;
					}
					else if(j>count+bit-1) {
						if(ar[(int)bit-1]==1) {
							resulta[(int)j]=1;
						}else {
							resulta[(int)j]=0;
						}
					}
					else {
							resulta[(int)j]=ar[(int)j-(int)count];
						}
					}
			}else {
				for(long j =0; j<2*bit; j++) {
					if(count>j)resulta[(int)j] = 0;
					else if(j>count+bit-1) {
						if(ar[(int)bit-1]==1) {
							resulta[(int)j]=1;
						}else {
							resulta[(int)j]=0;
						}
					}
					else resulta[(int)j] = ar[(int)j-(int)count];
					}
			}
			for(long j=2*bit-1; j>=0; j--) {
				System.out.print(resulta[(int)j]);	
			}
			System.out.println("<--계산");
			if(carry2==1) {
				for(long j =0; j<2*bit; j++) {
					if(resulta[(int)j]==1) {
						if(carry2==1) {
							resulta[(int)j]=1;
							carry2=0;
						}
						else {
							resulta[(int)j]=0;
						}
					}else {
						if(carry2==1) {
							resulta[(int)j]=0;
						}else {
							resulta[(int)j]=1;
						}
					}
				}carry2=0;
				for(long j=2*bit-1; j>=0; j--) {
					System.out.print(resulta[(int)j]);	
				}
				System.out.println("<--마이너스계산");
			}
			System.out.println("--------------------");
			carry=0;
			for(long j=0; j<2*bit; j++) {
				if(resultb[(int)j]==1&&resulta[(int)j]==1) {
					if(carry == 1) {
						resultb[(int)j]=1;
						carry =0;
					}
					else {
						resultb[(int)j]=0;
					}
					carry =1;
				}else if(resultb[(int)j]==0&&resulta[(int)j]==0) {
					if(carry == 1) {
						resultb[(int)j]=1;
						carry = 0;
					}
					else {
						resultb[(int)j]=0;
					}
				}else {
					if(carry == 1) {
						resultb[(int)j]=0;
					}
					else {
						resultb[(int)j]=1;
						carry=0;
				}
			}
		}
			for(long j=2*bit-1; j>=0; j--) {
				System.out.print(resultb[(int)j]);
			}count++;System.out.println("<--누적부분곱");
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.print("피승수 입력:");
		long a = sc.nextLong();
		System.out.print("승수 입력:");
		long b = sc.nextLong();
		booth(a,b);
		shift2(a,b);
	}
}
