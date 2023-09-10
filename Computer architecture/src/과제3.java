package cpuarc;
import java.util.*;
public class 과제3 {
	public static long sqr(int a){
		int count = 0;
		long b = 1;
		while(count != a) {b*=2;count ++;}
		return b;}
	public static void restored(long a,long b) {
		long asig=0,bsig=0,bit,carry=0;
		if(a<0) {
			a=-a;
			asig=1;
		}
		if(b<0) {
			b=-b;
			bsig=1;
		}
		if(a>b) {
			bit=a;
		}else {
			bit=b;
		}
		if(bit<sqr(3)-1) {
			bit=4;
		}else if(bit>sqr(31)-1) {
			System.out.println("비트초과");
			return;
		}else {
			for(int q=3; q<=31; q++) {
				if(bit<sqr(q)-1) {
					bit=(long)q+1;
					break;
				}
			}
		}
		long ar[] = new long[(int)bit*2];
		long br[] = new long[(int)bit];
		long result[] = new long[(int)bit];
		for(long i =0;i<2*bit;i++) {
			if(a>0) {
			ar[(int)i]=a%2;
			a/=2;}else {
				ar[(int)i]=0;
			}
		}
		for(long i =0;i<bit;i++) {
			if(b>0) {
			br[(int)i]=b%2;
			b/=2;}else {
				br[(int)i]=0;
			}
			result[(int)i]=0;
		}
		System.out.println("-복원 나눗셈-");
		System.out.print(" ");
		for(long i = 2*bit-1; i>=0;i--) {
			System.out.print(ar[(int)i]+" ");
		}System.out.println("");System.out.println("--------------------");
		for(long j = 0; j<bit;j++) {
			for(long i= 2*bit-1; i>0;i--) {
				ar[(int)i]=ar[(int)i-1];
			}ar[0]=0;System.out.print(" ");
			for(long i = 2*bit-1; i>=0;i--) {
				System.out.print(ar[(int)i]+" ");
			}System.out.println("        <-왼쪽시프트");System.out.print("-");
			for(long i = bit-1; i>=0;i--) {
				System.out.print(br[(int)i]+" ");
			}System.out.println("");System.out.println("--------------------");
			for(long i =0; i<bit; i++) {
				ar[(int)bit+(int)i]=ar[(int)bit+(int)i]-br[(int)i];
			}
			for(long i= 2*bit-1; i>=0;i--) {
				if(ar[(int)i]>=0) {
					System.out.print(" "+ar[(int)i]);
				}else {
					System.out.print(ar[(int)i]);
				}
			}
			for(long i =0; i<2*bit-1; i++) {
				if(carry==-1) {
					if(ar[(int)i]==-1) {
						ar[(int)i]=0;
					}else if(ar[(int)i]==1) {
						carry=0;
						ar[(int)i]=0;
					}else {
						ar[(int)i]=1;
					}
				}else {
					if(ar[(int)i]==-1) {
						ar[(int)i]=1;
						carry=-1;
					}
				}
			}
			if(carry==-1) {
				carry=0;
				if(ar[(int)bit*2-1]==0) {
					ar[(int)bit*2-1]=-1;
				}else {
					ar[(int)bit*2-1]=0;
				}
				System.out.println("");
				for(long i= 2*bit-1; i>=0;i--) {
					if(ar[(int)i]>=0) {
						System.out.print(" "+ar[(int)i]);
					}else {
						System.out.print(ar[(int)i]);
					}
				}System.out.println("         <-계산");
			}
			if(ar[(int)bit*2-1]==-1) {
				result[(int)j]=0;
				System.out.print("+");
				for(long i = bit-1; i>=0;i--) {
					System.out.print(br[(int)i]+" ");
				}System.out.println("                <-복원");
				for(long i =0; i<bit; i++) {
					ar[(int)bit+(int)i]=ar[(int)bit+(int)i]+br[(int)i]+carry;
					carry=0;
					if(ar[(int)bit+(int)i] == 2) {
						carry=1;
						ar[(int)bit+(int)i] =0;
					}else if(ar[(int)bit+(int)i]==3) {
						carry=1;
						ar[(int)bit+(int)i] =1;
					}
				}System.out.println("--------------------");
				for(long i= 2*bit-1; i>=0;i--) {
					if(ar[(int)i]>=0) {
						System.out.print(" "+ar[(int)i]);
					}else {
						System.out.print(ar[(int)i]);
					}
				}System.out.println(" ");
			}else{
				result[(int)j]=1;
				System.out.println(" ");
			}
		}
		if(asig!=bsig) {
			result[0]=1;
		}
		if(asig==1) {
			ar[2*(int)bit-1]=1;
		}
		System.out.println("몫:");
		for(long i = 0; i<bit;i++) {
			System.out.print(result[(int)i]+" ");
		}System.out.println(" ");System.out.println("나머지:");
		for(long i = 2*bit-1; i>bit-1; i--) {
			System.out.print(ar[(int)i]+" ");
		}
		
}
	public static void stored(long a,long b) {	
		long asig=0,bsig=0,bit,sign=0,count=0,carry=0;
		if(a<0) {
			a=-a;
			asig=1;
		}
		if(b<0) {
			b=-b;
			bsig=1;
		}
		if(a>b) {
			bit=a;
		}else {
			bit=b;
		}
		if(bit<sqr(3)-1) {
			bit=4;
		}else if(bit>sqr(31)-1) {
			System.out.println("비트초과");
			return;
		}else {
			for(int q=3; q<=31; q++) {
				if(bit<sqr(q)-1) {
					bit=(long)q+1;
					break;
				}
			}
		}
		long ar[] = new long[(int)bit*2];
		long br[] = new long[(int)bit];
		long result[] = new long[(int)bit];
		for(long i =0;i<2*bit;i++) {
			if(a>0) {
			ar[(int)i]=a%2;
			a/=2;}else {
				ar[(int)i]=0;
			}
		}
		for(long i =0;i<bit;i++) {
			if(b>0) {
			br[(int)i]=b%2;
			b/=2;}else {
				br[(int)i]=0;
			}
			result[(int)i]=0;
		}
		System.out.println("-비복원 나눗셈-");
		System.out.print(" ");
		for(long i = 2*bit-1; i>=0;i--) {
			System.out.print(ar[(int)i]+" ");
		}System.out.println("");
		for(long j = 0; j<bit; j++) {
			for(long i= 2*bit-1; i>0;i--) {
				ar[(int)i]=ar[(int)i-1];
			}ar[0]=0;System.out.print("");
			System.out.println("--------------------");
			for(long i = 2*bit-1; i>=0;i--) {
				if(ar[(int)i]>=0) {
					System.out.print(" "+ar[(int)i]);
				}else {
					System.out.print(ar[(int)i]);
				}
			}System.out.println("        <-왼쪽시프트");
			if(sign == 0) {
				System.out.print("-");
				for(long i =0; i<bit; i++) {
					ar[(int)bit+(int)i]=ar[(int)bit+(int)i]-br[(int)i];
				}
			}else if(sign == 1){
				System.out.print("+");
				for(long i =0; i<bit; i++) {
					ar[(int)bit+(int)i]=ar[(int)bit+(int)i]+br[(int)i];
				}
			}
			if(sign !=2) {
				for(long i = bit-1; i>=0;i--) {
					System.out.print(br[(int)i]+" ");
				}System.out.println("");
			}
			System.out.println("--------------------");
			for(long i= 2*bit-1; i>=0;i--) {
				if(ar[(int)i]>=0) {
					System.out.print(" "+ar[(int)i]);
				}else {
					System.out.print(ar[(int)i]);
				}
			}System.out.println(" ");
			for(long i =2*bit-1; i>0; i--) {
				if(ar[(int)i]==-1&&ar[(int)i-1]==1) {
					ar[(int)i]=0;
					ar[(int)i-1]=-1;
				}else if(ar[(int)i]==1&&ar[(int)i-1]==-1) {
					ar[(int)i]=0;
					ar[(int)i-1]=1;
				}
			}
			for(long i= 2*bit-1; i>=0;i--) {
				if(ar[(int)i]>=0) {
					System.out.print(" "+ar[(int)i]);
				}else {
					System.out.print(ar[(int)i]);
				}
			}System.out.println("        <-계산");
			for(long i =2*bit-1; i>=0; i--) {
				if(ar[(int)i]==1) {
					sign=0;
					result[(int)j]=1;
					break;
				}else if(ar[(int)i]==-1) {
					sign=1;
					result[(int)j]=0;
					break;
				}else {
					if(i==0) {
						sign=2;
						if(count==0) {
							result[(int)j]=1;
							count++;
						}else {	
							result[(int)j]=0;
					}
					}
				}
			}
		}
		if(sign == 1) {
			System.out.print("+");
			for(long i =0; i<bit; i++) {
				ar[(int)bit+(int)i]=ar[(int)bit+(int)i]+br[(int)i];
			}
			for(long i = bit-1; i>=0;i--) {
				System.out.print(br[(int)i]+" ");
			}System.out.println("");
			for(long i =2*bit-1; i>0; i--) {
				if(ar[(int)i]==-1&&ar[(int)i-1]==1) {
					ar[(int)i]=0;
					ar[(int)i-1]=-1;
				}else if(ar[(int)i]==1&&ar[(int)i-1]==-1) {
					ar[(int)i]=0;
					ar[(int)i-1]=1;
				}
			}
			
		}
		for(long i =0; i<2*bit; i++) {
			if(carry==1) {
				if(ar[(int)i] == 2) {
					ar[(int)i]=1;
				}else if(ar[(int)i]==1) {
					ar[(int)i]=0;
				}else {
					ar[(int)i]=1;
					carry=0;
				}
			}else{
				if(ar[(int)i]==2) {
					carry=1;
					ar[(int)i]=0;
				}
			}
		}
		if(asig!=bsig) {
			result[0]=1;
		}
		if(asig==1) {
			ar[2*(int)bit-1]=1;
		}
		System.out.println("");
		System.out.print("몫:");
		for(long i = 0; i<bit;i++) {
			System.out.print(result[(int)i]+" ");
		}System.out.println("");System.out.print("나머지:");
		for(long i = 2*bit-1; i>bit-1; i--) {
			System.out.print(ar[(int)i]+" ");
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.print("피제수 입력(10진수):");
		long a = sc.nextLong();
		System.out.print("제수 입력(10진수):");
		long b = sc.nextLong();
		restored(a,b);
		System.out.println();
		stored(a,b);
	}
}
