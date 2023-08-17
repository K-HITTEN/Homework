import java.util.*;
import java.io.*;

public class allocation {
	static int prog, n= 1000;
	static int firstfit = 0,bestfit =0,worstfit = 0;
	static ArrayList <ArrayList> alloc = new ArrayList<ArrayList>();
	static ArrayList<ArrayList> first = new ArrayList<ArrayList>(); 
	static ArrayList<ArrayList> best = new ArrayList<ArrayList>();
	static ArrayList<ArrayList> worst = new ArrayList<ArrayList>();
	static LinkedList<ArrayList> fqueue = new LinkedList<ArrayList>();
	static LinkedList<ArrayList> bqueue = new LinkedList<ArrayList>();
	static LinkedList<ArrayList> wqueue = new LinkedList<ArrayList>();
	static ArrayList<Integer> NN;
	
	static void first() {
		int time = (int)alloc.get(0).get(0);
		int minremovetime = 1000000000;
		LinkedList<ArrayList> removetime = new LinkedList<ArrayList>();
		NN = new ArrayList<Integer>();
		NN.add(0);
		NN.add(-1);
		NN.add(1000);
		first.add(NN);
		NN = null;
		int i2 = 0;
		int i = 0;
		while(true) {
			if((i<prog && minremovetime > (int)alloc.get(i).get(0))) {
				time = (int)alloc.get(i).get(0);
				int n = firstfound((int)alloc.get(i).get(2));
				NN = new ArrayList<Integer>();
				NN.add((int)alloc.get(i).get(0));NN.add((int)alloc.get(i).get(1));NN.add((int)alloc.get(i).get(2));NN.add((int)alloc.get(i).get(3));
				if(n == -1) {
					fqueue.add(NN);
					NN= null;
					i++;
					continue;
				}
				if(NN.get(2) == (int)first.get(n).get(2) - (int)first.get(n).get(0)) {
					first.get(n).set(1, i);
					i2 = i;
				}else {
					int remain = (int)first.get(n).get(2);
					first.get(n).set(1, i);
					i2 = i;
					first.get(n).set(2,(int)first.get(n).get(0)+NN.get(2));
					NN = null;
					NN = new ArrayList<Integer>();
					NN.add((int)first.get(n).get(2));
					NN.add(-1);NN.add(remain);
					first.add(n+1, NN);
				} 
				NN= null;
				NN = new ArrayList<Integer>();
				NN.add(i);
				NN.add(time+(int)alloc.get(i).get(1));
				removetime.add(NN);
				if(time+(int)alloc.get(i).get(1) < minremovetime) {
					minremovetime = (int)time + (int)alloc.get(i).get(1);
				}
				NN= null; 
				i++;
			}else {
				time = minremovetime;
				int u = 0;
				int w = removetime.size();
				for(int j = 0; j < w; j++) {
					if((int)removetime.get(u).get(1) ==  time) {
						int k = 0;
						for(int y = 0; y<first.size(); y++) {
							if((int)removetime.get(u).get(0) == (int)first.get(k).get(1)) {
								first.get(k).set(1,-1);
								if(k > 0 && k < first.size() -1 && (int)first.get(k-1).get(1) == -1 && (int)first.get(k+1).get(1) == -1) {
									first.get(k-1).set(2, first.get(k+1).get(2));
									first.remove(k);
									first.remove(k);
								}else if(k > 0 && (int)first.get(k-1).get(1) == -1) {
									first.get(k-1).set(2, first.get(k).get(2));
									first.remove(k);
								}else if(k < first.size() -1 && (int)first.get(k+1).get(1) == -1) {
									first.get(k).set(2, first.get(k+1).get(2));
									first.remove(k+1);
									k++;
								}
							}else {
								k++;
							}
						}
						removetime.remove(u);
					}else {
						u++;
					}
				}
				u = 0;
				w = fqueue.size();
				for(int j = 0; j < w;j++) {
					int y = firstfound((int)fqueue.get(u).get(2));
					if(y == -1) {
						u++;
						continue;
					}
					if((int)fqueue.get(u).get(2) <= ((int)first.get(y).get(2)-(int)first.get(y).get(0))) {
						if((int)fqueue.get(u).get(2) == ((int)first.get(y).get(2)-(int)first.get(y).get(0))) {
							first.get(y).set(1, fqueue.get(u).get(3));
							i2 = (int)first.get(y).get(1);
							NN = new ArrayList<Integer>();
							NN.add(i2);
							NN.add(time+(int)fqueue.get(u).get(1));
							removetime.add(NN);
							NN = null;
							fqueue.remove(u);
						}else {
							first.get(y).set(1, fqueue.get(u).get(3));
							i2 = (int)first.get(y).get(1);
							int remain = (int)first.get(y).get(2);
							first.get(y).set(2, (int)first.get(y).get(0)+(int)fqueue.get(u).get(2));
							NN = new ArrayList<Integer>();
							NN.add((int)first.get(y).get(2));
							NN.add(-1); NN.add(remain);
							first.add(y+1,NN);
							NN = null;
							NN = new ArrayList<Integer>();
							NN.add(i2);
							NN.add(time+(int)fqueue.get(u).get(1));
							removetime.add(NN);
							fqueue.remove(u);
							NN = null;
						}
					}else {
						u++;
					}
				}
				int min = 1000000000;
				
				for(int j = 0; j <removetime.size(); j++) {
					if(min > (int)removetime.get(j).get(1)) {
						min = (int)removetime.get(j).get(1);
					}
				}
				minremovetime = min;
			}
			if(i2 == prog-1) break;
		}
		for(int j =0; j<first.size(); j++) {
			if((int)first.get(j).get(1) == i2) {
				i2 = j;
				break;
			}
		}
		firstfit = (int)first.get(i2).get(0);
	}
	
	static int firstfound(int size){
		for(int i = 0; i<first.size(); i++) {
			if((int)first.get(i).get(1) == -1) {
				if((int)first.get(i).get(2) - (int)first.get(i).get(0)>= size) {
					return i;	
				}
			}
		}
		return -1;
	}
	
	static void best() {
		int time = (int)alloc.get(0).get(0);
		int minremovetime = 1000000000;
		LinkedList<ArrayList> removetime = new LinkedList<ArrayList>();
		NN = new ArrayList<Integer>();
		NN.add(0);
		NN.add(-1);
		NN.add(1000);
		best.add(NN);
		NN = null;
		int i2 =0;
		int i = 0;
		while(true) {
			if((i<prog &&  minremovetime > (int)alloc.get(i).get(0))) {
				time = (int)alloc.get(i).get(0);
				int n = (int)bestfound((int)alloc.get(i).get(2));
				NN = new ArrayList<Integer>();
				NN.add((int)alloc.get(i).get(0));NN.add((int)alloc.get(i).get(1));NN.add((int)alloc.get(i).get(2));NN.add((int)alloc.get(i).get(3));
				if(n == -1) {
					bqueue.add(NN);
					NN= null;
					i++;
					continue;
				}
				if(NN.get(2) == (int)best.get(n).get(2) - (int)best.get(n).get(0)) {
					best.get(n).set(1, i);
					i2 = i;
				}else {
					int remain = (int)best.get(n).get(2);
					best.get(n).set(1, i);
					i2 = i;
					best.get(n).set(2,(int)best.get(n).get(0)+NN.get(2));
					NN = null;
					NN = new ArrayList<Integer>();
					NN.add((int)best.get(n).get(2));
					NN.add(-1);NN.add(remain);
					best.add(n+1, NN);
				} 
				NN= null;
				NN = new ArrayList<Integer>();
				NN.add(i);
				NN.add((int)time+(int)alloc.get(i).get(1));
				removetime.add(NN);
				if(time+(int)alloc.get(i).get(1) < minremovetime) {
					minremovetime = (int)time + (int)alloc.get(i).get(1);
				}
				NN= null; i++;
			}else {
				time = minremovetime;
				int u =0;
				int w = removetime.size();
				for(int j = 0; j < w; j++) {
					if((int)removetime.get(u).get(1) ==  time) {
						int k = 0;
						for(int y = 0; y<best.size(); y++) {
							if((int)removetime.get(u).get(0) == (int)best.get(k).get(1)) {
								best.get(k).set(1,-1);
								if(k > 0 && k < best.size() -1 && (int)best.get(k-1).get(1) == -1 && (int)best.get(k+1).get(1) == -1) {
									best.get(k-1).set(2, best.get(k+1).get(2));
									best.remove(k);
									best.remove(k);
								}else if(k > 0 && (int)best.get(k-1).get(1) == -1) {
									best.get(k-1).set(2, best.get(k).get(2));
									best.remove(k);
								}else if(k < best.size() -1 && (int)best.get(k+1).get(1) == -1) {
									best.get(k).set(2, best.get(k+1).get(2));
									best.remove(k+1);
									k++;
								}
							}else{
								k++;
							}
						}
						removetime.remove(u);
					}else {
						u++;
					}
				}
				u = 0;
				w = bqueue.size();
				for(int j = 0; j <w; j++) {
					int y = bestfound((int)bqueue.get(u).get(2));
					if(y == -1) {
						u++;
						continue;
					}
					if((int)bqueue.get(u).get(2) <= ((int)best.get(y).get(2)-(int)best.get(y).get(0))) {
						if((int)bqueue.get(u).get(2) == ((int)best.get(y).get(2)-(int)best.get(y).get(0))) {
							best.get(y).set(1, bqueue.get(u).get(3));
							i2 = (int)best.get(y).get(1);
							NN = new ArrayList<Integer>();
							NN.add(i2);
							NN.add(time+(int)bqueue.get(u).get(1));
							removetime.add(NN);
							NN = null;
							bqueue.remove(u);
						}else {
							best.get(y).set(1, bqueue.get(u).get(3));
							i2 = (int)best.get(y).get(1);
							int remain = (int)best.get(y).get(2);
							best.get(y).set(2, (int)best.get(y).get(0)+(int)bqueue.get(u).get(2));
							NN = new ArrayList<Integer>();
							NN.add((int)best.get(y).get(2));
							NN.add(-1); NN.add(remain);
							best.add(y+1,NN);
							NN = null;
							NN = new ArrayList<Integer>();
							NN.add(i2);
							NN.add(time+(int)bqueue.get(u).get(1));
							removetime.add(NN);
							bqueue.remove(u);
							NN = null;
						}
					}else {
						u++;
					}
				}
				int min = 1000000000;
				
				for(int j = 0; j <removetime.size(); j++) {
					if(min > (int)removetime.get(j).get(1)) {
						min = (int)removetime.get(j).get(1);
					}
				}
				minremovetime = min;
			}
			if(i2 == prog-1) break;
		}
		for(int j =0; j<best.size(); j++) {
			if((int)best.get(j).get(1) == i2) {
				i2 = j;
				break;
			}
		}
		bestfit = (int)best.get(i2).get(0);
	}
	
	static int bestfound(int size){
		int minsize=10000000;
		int minalloc = 0;
		for(int i = 0; i<best.size(); i++) {
			if((int)best.get(i).get(1) < 0) {
				if((int)best.get(i).get(2) - (int)best.get(i).get(0)>= size) {
					if(minsize > (int)best.get(i).get(2) - (int)best.get(i).get(0)) {
						minsize = (int)best.get(i).get(2) - (int)best.get(i).get(0);
						minalloc = i;
					}
				}
			}
		}
		if(minsize>1000) {
			return -1;
		}else {
			return minalloc;
		}
	}
	
	static void worst() {
		int time = (int)alloc.get(0).get(0);
		int minremovetime = 1000000000;
		LinkedList<ArrayList> removetime = new LinkedList<ArrayList>();
		NN = new ArrayList<Integer>();
		NN.add(0);
		NN.add(-1);
		NN.add(1000);
		worst.add(NN);
		NN = null;
		int i2 =0;
		int i = 0;
		while(true) {
			if((i<prog &&  minremovetime > (int)alloc.get(i).get(0))) {
				time = (int)alloc.get(i).get(0);
				int n = worstfound((int)alloc.get(i).get(2));
				NN = new ArrayList<Integer>();
				NN.add((int)alloc.get(i).get(0));NN.add((int)alloc.get(i).get(1));NN.add((int)alloc.get(i).get(2));NN.add((int)alloc.get(i).get(3));
				if(n == -1) {
					wqueue.add(NN);
					NN= null;
					i++;
					continue;
				}
				if(NN.get(2) == (int)worst.get(n).get(2) - (int)worst.get(n).get(0)) {
					worst.get(n).set(1, i);
					i2 = i;
				}else {
					int remain = (int)worst.get(n).get(2);
					worst.get(n).set(1, i);
					i2 = i;
					worst.get(n).set(2,(int)worst.get(n).get(0)+NN.get(2));
					NN = null;
					NN = new ArrayList<Integer>();
					NN.add((int)worst.get(n).get(2));
					NN.add(-1);NN.add(remain);
					worst.add(n+1, NN);
				} 
				NN= null;
				NN = new ArrayList<Integer>();
				NN.add(i);
				NN.add((int)time+(int)alloc.get(i).get(1));
				removetime.add(NN);
				if(time+(int)alloc.get(i).get(1) < minremovetime) {
					minremovetime = time + (int)alloc.get(i).get(1);
				}
				NN= null; i++;
			}else {
				time = minremovetime;
				int u = 0;
				int w = removetime.size();
				for(int j = 0; j < w; j++) {
					if((int)removetime.get(u).get(1) ==  time) {
						int k = 0;
						for(int y = 0; y<worst.size(); y++) {
							if((int)removetime.get(u).get(0) == (int)worst.get(k).get(1)) {
								worst.get(k).set(1,-1);
								if(k > 0 && k < worst.size() -1 && (int)worst.get(k-1).get(1) == -1 && (int)worst.get(k+1).get(1) == -1) {
									worst.get(k-1).set(2, worst.get(k+1).get(2));
									worst.remove(k);
									worst.remove(k);								
								}else if(k > 0 && (int)worst.get(k-1).get(1) == -1) {
									worst.get(k-1).set(2, worst.get(k).get(2));
									worst.remove(k);
								}else if(k < worst.size() -1 && (int)worst.get(k+1).get(1) == -1) {
									worst.get(k).set(2, worst.get(k+1).get(2));
									worst.remove(k+1);
									k++;
								}
							}else {
								k++;
							}
						}
						removetime.remove(u);
					}else {
						u++;
					}
				}
				u = 0;
				w = wqueue.size();
				for(int j = 0; j <w; j++) {
					int y = worstfound((int)wqueue.get(u).get(2));
					if(y == -1) {
						u++;
						continue;
					}
					if((int)wqueue.get(u).get(2) <= ((int)worst.get(y).get(2)-(int)worst.get(y).get(0))) {
						if((int)wqueue.get(u).get(2) == ((int)worst.get(y).get(2)-(int)worst.get(y).get(0))) {
							worst.get(y).set(1, wqueue.get(u).get(3));
							i2 = (int)worst.get(y).get(1);
							NN = new ArrayList<Integer>();
							NN.add(i2);
							NN.add(time+(int)wqueue.get(u).get(1));
							removetime.add(NN);
							NN = null;
							wqueue.remove(u);
						}else {
							worst.get(y).set(1, wqueue.get(u).get(3));
							i2 = (int)worst.get(y).get(1);
							int remain = (int)worst.get(y).get(2);
							worst.get(y).set(2, (int)worst.get(y).get(0)+(int)wqueue.get(u).get(2));
							NN = new ArrayList<Integer>();
							NN.add((int)worst.get(y).get(2));
							NN.add(-1); NN.add(remain);
							worst.add(y+1,NN);
							NN = null;
							NN = new ArrayList<Integer>();
							NN.add(i2);
							NN.add(time+(int)wqueue.get(u).get(1));
							removetime.add(NN);
							wqueue.remove(u);
							NN = null;
						}
						
					}else {
						u++;
					}
				}
				int min = 1000000000;
				
				for(int j = 0; j <removetime.size(); j++) {
					if(min > (int)removetime.get(j).get(1)) {
						min = (int)removetime.get(j).get(1);
					}
				}
				minremovetime = min;
			}
			if(i2 == prog-1) break;
		}
		for(int j =0; j<worst.size(); j++) {
			if((int)worst.get(j).get(1) == i2) {
				i2 = j;
				break;
			}
		}
		worstfit = (int)worst.get(i2).get(0);
	}
	
	static int worstfound(int size){
		int maxsize = -1;
		int maxalloc = 0;
		for(int i = 0; i<worst.size(); i++) {
			if((int)worst.get(i).get(1) == -1) {
				if((int)worst.get(i).get(2) - (int)worst.get(i).get(0)>= size) {
					if(maxsize < (int)worst.get(i).get(2) - (int)worst.get(i).get(0)) {
						maxsize = (int)worst.get(i).get(2) - (int)worst.get(i).get(0);
						maxalloc = i;
					}
				}
			}
		}
		if(maxsize == -1) {
			return -1;
		}else {
			return maxalloc;
		}
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileReader fr = new FileReader("allocation.inp");
		BufferedReader br = new BufferedReader(fr);
		StringTokenizer st = new StringTokenizer(br.readLine());
		prog = Integer.parseInt(st.nextToken());
		for(int i = 0; i<prog; i++) {
			st = new StringTokenizer(br.readLine());
			NN = new ArrayList<Integer>();
			for(int j = 0; j<4; j++) {
				if(j< 3) {
					NN.add(Integer.parseInt(st.nextToken()));
				}else {
					NN.add(i);
				}
			}
			alloc.add(NN);
			NN = null;
		}
		first();
		best();
		worst();
		
		BufferedWriter bw = new BufferedWriter(new FileWriter("allocation.out"));
		bw.write(firstfit + "\n");
		bw.write(bestfit + "\n");
		bw.write(worstfit + "\n");
		
		bw.flush();
		bw.close();
		br.close();
	}

}
