package os;
import java.io.*;
import java.util.*;

public class multi {
	static int prog, last=0, zz = 0, minus=0;
	static long cpu = 0,all = 0;
	static long [][] arr;
	
	static int rowfind() {
		int n = 0,m = 0,zcount = 0;
		while(n<prog) {
			while(m<arr[n].length) {
				if(arr[n][m] == -1) {
					zcount ++;
					if(m ==arr[n].length-1) {
						arr[n][0] = -1;
					}
					break;
				}
				if(m%2==0 && arr[n][m] != 0 ) {
					return n;
				}else if(arr[n][m] != 0){
					break;
				}else {
					m++;
				}
			}m = 0;
			if (zcount == prog) {
				break;
			}
			n++;
		}
		return -1;
	}
	
	static int colsfind() {
		int n = 0,m = 0,zcount = 0,zzcount = 0,i=0;
		long z=0, y =0;
		while(n<prog) {
			while(m<arr[n].length) {
				if(arr[n][m] == -1) {
					zzcount++;
					break;
				}else if(m%2==0 && arr[n][m] != 0 ) {
					return (int)m;
				}else if(arr[n][m] != 0){
					zcount++;
					if(z == 0) {
						z = arr[n][m];
						y = arr[n][m];
						if(m == arr[n].length-2) {
							i++;
						}
					}else if(z>arr[n][m]) {
						z = arr[n][m];
						if(m == arr[n].length-2) {
							i++;
						}
					}else if(y <arr[n][m]) {
						y = arr[n][m];
						if(m == arr[n].length-2) {
							i++;
						}
					}
					break;
				}else {
					m++;
				}
			}m = 0;
			n++;
		}
		if(zzcount == prog) {
			minus = 1;
		}else if((zzcount+i) == prog) {
			all += y;
			calc(y);
			zz =1;
			return 0;
		}else if((zzcount+zcount) == prog){
			zz = 1;
			calc(z);
			cpu += z;
			all += z;
			return 0;
		}
		
		return -1;
	}
	
	static void calc(long r) {
		int n = 0 , m = 0;
		while(n<prog) {
			while(m<arr[n].length) {
				if(m%2 == 1 && arr[n][m]>0) {
					if(arr[n][m] <= r) {
						arr[n][m] = 0;
					}else if(arr[n][m] == -1){
						break;
					}else {
						arr[n][m] -= r;
					}
					break;
				}else if(arr[n][m] != 0){
					break;
				}else {
					m++;
				}
			}m = 0;
			n++;
		}
	}

	public static void main(String[] args) throws IOException {

		int rows=0,cols=0; 
		long acting;
		FileReader fr = new FileReader("multi.inp");
		BufferedReader br = new BufferedReader(fr);
		String aaa = br.readLine();
		prog = Integer.parseInt(aaa);
		arr = new long[prog][];
		

		for(int j = 0; j <prog; j++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[j] = new long[st.countTokens()];
			int i = 0;
			while(st.hasMoreTokens()) {
				long n = Long.parseLong(st.nextToken());
				arr[j][i] = n;
				i++;
			}
		}
		while(minus!=1) {
			rows = rowfind();
			cols = colsfind();
			if(rows == -1 && cols == -1) {
				break;
			}
			if(zz == 1) {
				zz = 0;			
			}else {
				acting = arr[rows][cols];
				calc(acting);
				all += acting;
				arr[rows][cols] = 0;
			}
		}
		
		BufferedWriter bw = new BufferedWriter(new FileWriter("multi.out"));
		bw.write(cpu + " " + all);
		bw.flush();
		bw.close();
		br.close();
	}
}
