package os;
import java.io.*;
import java.util.*;

public class fcfs {
	static int prog,rest = 0,minus = 0, alltime = 0;
	static ArrayList<Integer> inner;
	static ArrayList<ArrayList> outer = new ArrayList<ArrayList>();
	static LinkedList<Integer> queue = new LinkedList<Integer>();
	
	static void start() {
		int exit = 0;
		for(int i = 0; i <prog; i++) {
			if((int)outer.get(i).get(0) == 0) {
				queue.add(i);
				exit ++;
			}
		}
		if(exit >0) {
			return;
		}
		
		while(true) {
			int min = min1();
			alltime = alltime + min;
			rest = rest +min;
			for(int i = 0; i < prog; i++) {
				outer.get(i).set(0,(int)outer.get(i).get(0)-min);
				if((int)outer.get(i).get(0) == 0) {
					if((int)outer.get(i).get(1) == -1) {
						outer.get(i).add(alltime);
					}else {
						queue.add(i);
						exit++;
					}
				}
			}
			if(exit > 0) {
				break;
			}
		}
	}
	
	static void rest() {
		int exit = 0;
		minus = 0;
		int k =0;
		while(true) {
			int min = min1();
			alltime = alltime + min;
			for(int i = 0; i < prog; i++) {
				if((int)outer.get(i).get(0) == -1 || ((int)outer.get(i).get(0) == 0 && (int)outer.get(i).get(1) == -1)) {
					minus ++;
					continue;
				}
				if((int)outer.get(i).get(0) != 0) {
					outer.get(i).set(0,(int)outer.get(i).get(0)-min);
					if((int)outer.get(i).get(0) == 0) {
						if((int)outer.get(i).get(1) == -1) {
							outer.get(i).add(alltime);
							minus ++;
						}else {
							queue.add(i);
							exit++;
						}
					}else {
						if((int)outer.get(i).get(1) == -1) {
							k ++;
						}
					}
				}
			}
			if(exit > 0) {
				if(minus+k != prog) {
					rest = rest+min;
				}
				break;
			}
			if(minus == prog) {
				break;
			}else {
				if(minus+k != prog) {
					rest = rest+min;
				}
				minus = 0;
			}
		}
	}
	
	static void cpucalc(int n) {
		int exit = 0;
		while(true) {
			int min = min2(n);
			alltime = alltime + min;
			for(int i = 0; i < prog; i++) {
				if(i != n &&((int)outer.get(i).get(0) == -1 || (int)outer.get(i).get(0) == 0)) {
					continue;
				}
				if(i == n) {
					outer.get(i).set(1,(int)outer.get(i).get(1)-min);
					if((int)outer.get(i).get(1) == 0) {
						exit ++;
						if((int)outer.get(i).get(2) == -1) {
							outer.get(i).add(alltime);
						}
					}
				}else {
					outer.get(i).set(0,(int)outer.get(i).get(0)-min);
					if((int)outer.get(i).get(0) == 0) {
						if((int)outer.get(i).get(1) == -1) {
							outer.get(i).add(alltime);
						}else {
							queue.add(i);
						}
					}
				}
			}
			if(exit >0) {
				break;
			}
		}
	}
	
	static int min1() {
		int min = 1000000000;
		for(int i = 0; i<prog; i++) {
			if((int)outer.get(i).get(0)<min && (int)outer.get(i).get(0)>0) {
				min = (int)outer.get(i).get(0);
			}
		}
		return min;
	}
	
	static int min2(int n) {
		int min = (int)outer.get(n).get(1);
		for(int i = 0; i<prog; i++) {
			if((int)outer.get(i).get(0)<min && i !=n && (int)outer.get(i).get(0)>0) {
				min = (int)outer.get(i).get(0);
			}
		}
		return min;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileReader fr = new FileReader("fcfs.inp");
		BufferedReader br = new BufferedReader(fr);
		String aaa = br.readLine();
		prog = Integer.parseInt(aaa);
		
		for(int j = 0; j<prog; j++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			 inner = new ArrayList<Integer>();
			while(st.hasMoreTokens()) {
				int n = Integer.parseInt(st.nextToken());
				inner.add(n);
			}
			outer.add(inner);
			inner = null;
		}
		
		start();
		while(true) {
			if(queue.isEmpty()) {
				rest();
				if(minus == prog) {
					break;
				}else {
					minus = 0;
				}
			}
			int n = queue.peek();
			queue.poll();	
			cpucalc(n);
			for(int i = 0; i < prog; i++) {
				while((int)outer.get(i).get(0) == 0 && (int)outer.get(i).get(1) == 0) {
					outer.get(i).remove(0);
					outer.get(i).remove(0);
				}
			}
		}
		
		BufferedWriter bw = new BufferedWriter(new FileWriter("fcfs.out"));
		bw.write(rest + "\n");
		for(int i = 0; i < prog; i++) {
			bw.write((int) outer.get(i).get(outer.get(i).size()-1) + "\n");
		}
		bw.flush();
		bw.close();
		br.close();
		
	}

}
