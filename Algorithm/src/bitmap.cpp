#include <iostream>
#include <fstream>
#include <string>
#include <vector>
using namespace std;

int counting = 0;

void BtoD(vector<vector<int>> arr,int a, int b, ofstream &fout) {
	int a1 = a / 2, a2 = a / 2, b1 = b / 2, b2 = b / 2;
	if (a % 2 != 0) {
		a1++;
	}
	if (b % 2 != 0) {
		b1++;
	}
	fout << "D";
	counting++;
	if (counting == 50) {
		counting = 0;
		fout << endl;
	}
	int count = 0;
	vector<vector<int>> arr1;
	vector<int> v1(b1, 0);
	for (int i = 0; i < a1; i++) {
		arr1.push_back(v1);
	}
	for (int i = 0; i < a1; i++) {
		for (int j = 0; j < b1; j++) {
			if (arr[i][j] == 1) {
				arr1[i][j] = 1;
				count++;
			}
		}
	}
	if (count == a1 * b1) {
		fout << 1;
		counting++;
		if (counting == 50) {
			counting = 0;
			fout << endl;
		}
	}
	else if (count == 0) {
		fout << 0;
		counting++;
		if (counting == 50) {
			counting = 0;
			fout << endl;
		}
	}
	else {
		BtoD(arr1, a1, b1, fout);
	}
	count = 0;
	if (b2 != 0) {
		vector<vector<int>> arr2;
		vector<int> v2(b2, 0);
		for (int i = 0; i < a1; i++) {
			arr2.push_back(v2);
		}
		for (int i = 0; i < a1; i++) {
			for (int j = 0; j < b2; j++) {
				if (arr[i][b1+j] == 1) {
					arr2[i][j] = 1;
					count++;
				}
			}
		}
		if (count == a1 * b2) {
			fout << 1;
			counting++;
			if (counting == 50) {
				counting = 0;
				fout << endl;
			}
		}
		else if (count == 0) {
			fout << 0;
			counting++;
			if (counting == 50) {
				counting = 0;
				fout << endl;
			}
		}
		else {
			BtoD(arr2, a1, b2, fout);
		}
	}
	count = 0;
	if (a2 != 0) {
		vector<vector<int>> arr3;
		vector<int> v3(b1, 0);
		for (int i = 0; i < a2; i++) {
			arr3.push_back(v3);
		}
		for (int i = 0; i < a2; i++) {
			for (int j = 0; j < b1; j++) {
				if (arr[a1+i][j] == 1) {
					arr3[i][j] = 1;
					count++;
				}
			}
		}
		if (count == a2 * b1) {
			fout << 1;
			counting++;
			if (counting == 50) {
				counting = 0;
				fout << endl;
			}
		}
		else if (count == 0) {
			fout << 0;
			counting++;
			if (counting == 50) {
				counting = 0;
				fout << endl;
			}
		}
		else {
			BtoD(arr3, a2, b1, fout);
		}
	}
	count = 0;
	if (a2 != 0 && b2 != 0) {
		vector<vector<int>> arr4;
		vector<int> v4(b2, 0);
		for (int i = 0; i < a2; i++) {
			arr4.push_back(v4);
		}
		for (int i = 0; i < a2; i++) {
			for (int j = 0; j < b2; j++) {
				if (arr[a1+i][b1+j] == 1) {
					arr4[i][j] = 1;
					count++;
				}
			}
		}
		if (count == a2 * b2) {
			fout << 1;
			counting++;
			if (counting == 50) {
				counting = 0;
				fout << endl;
			}
		}
		else if (count == 0) {
			fout << 0;
			counting++;
			if (counting == 50) {
				counting = 0;
				fout << endl;
			}
		}
		else {
			BtoD(arr4, a2, b2, fout);
		}
	}
}

void DtoB(ifstream &fin, vector<vector<int>> &arr, int a, int b, int a_start, int b_start ,int a_end,int b_end) {
	int a1 = (a_end- a_start) / 2, a2 = (a_end - a_start) / 2, b1 = (b_end - b_start) / 2, b2 = (b_end - b_start) / 2;
	if ((a_end - a_start) % 2 != 0) {
		a1++;
	}
	if ((b_end - b_start) % 2 != 0) {
		b1++;
	}
	char c;
	c = fin.get();
	if (c == '\n') {
		c = fin.get();
	}
	if ((c-'0') == 0) {
		for (int i = a_start; i < a_start+a1; i++) {
			for (int j = b_start; j < b_start+b1; j++) {
				arr[i][j] = 0;
			}
		}
	}
	else if ((c-'0') == 1) {
		for (int i = a_start; i < a_start+a1; i++) {
			for (int j = b_start; j < b_start+b1; j++) {
				arr[i][j] = 1;
			}
		}
	}
	else {
		DtoB(fin, arr, a, b, a_start,b_start,a_start+a1,b_start+b1);
	}
	if (b2 != 0) {
		c = fin.get();
		if (c == '\n') {
			c = fin.get();
		}
		if ((c - '0') == 0) {
			for (int i = a_start; i < a_start + a1; i++) {
				for (int j = b1 + b_start; j < b_end; j++) {
					arr[i][j] = 0;
				}
			}
		}
		else if ((c - '0') == 1) {
			for (int i = a_start; i < a_start + a1; i++) {
				for (int j = b1 + b_start; j < b_end; j++) {
					arr[i][j] = 1;
				}
			}
		}
		else {
			DtoB(fin, arr, a, b, a_start, b1+b_start,a_start+a1,b_end);
		}
	}
	if (a2 != 0) {
		c = fin.get();
		if (c == '\n') {
			c = fin.get();
		}
		if ((c - '0') == 0) {
			for (int i = a1 + a_start; i < a_end; i++) {
				for (int j = b_start; j < b_start + b1; j++) {
					arr[i][j] = 0;
				}
			}
		}
		else if ((c - '0') == 1) {
			for (int i = a1 + a_start; i < a_end; i++) {
				for (int j = b_start; j < b_start + b1; j++) {
					arr[i][j] = 1;
				}
			}
		}
		else {
			DtoB(fin, arr, a, b, a1 + a_start, b_start, a_end, b_start + b1);
		}
	}
	if (a2 != 0 && b2 != 0) {
		c = fin.get();
		if (c == '\n') {
			c = fin.get();
		}
		if ((c - '0') == 0) {
			for (int i = a1 + a_start; i < a_end; i++) {
				for (int j = b1 + b_start; j < b_end; j++) {
					arr[i][j] = 0;
				}
			}
		}
		else if ((c - '0') == 1) {
			for (int i = a1 + a_start; i < a_end; i++) {
				for (int j = b1 + b_start; j < b_end; j++) {
					arr[i][j] = 1;
				}
			}
		}
		else {
			DtoB(fin, arr, a, b, a1 + a_start, b1 + b_start, a_end, b_end);
		}
	}
}

int main(void) {
	ifstream fin;
	fin.open("bitmap.inp");
	ofstream fout;
	fout.open("bitmap.out");
	string s,a,b;
	while (true) {
		getline(fin, s, ' ');
		if (s == "#" || s == "\n#" || s == "#\n" || s == "\n#\n") {
			break;
		}
		getline(fin, a, ' ');
		getline(fin, b);
		int aa = stoi(a), bb = stoi(b);
		vector<vector<int>> arr;
		vector<int> v(bb, 0);
		for (int i = 0; i < aa; i++) {
			arr.push_back(v);
		}
		if (s == "B" || s == "\nB") {
			fout << "D " << a << " " << b << endl;
			int count = 0;
			char c;
			for (int i = 0; i < aa; i++) {
				for (int j = 0; j < bb; j++) {
					c = fin.get();
					if (c == '\n') {
						c = fin.get();
					}
					arr[i][j] = c - '0';
					if ((c - '0') == 1) {
						count++;
					}
				}
			}
			if (count == aa * bb) {
				fout << 1 << endl;
			}
			else if (count == 0) {
				fout << 0 << endl;
			}
			else {
				BtoD(arr, aa, bb, fout);
				if (counting != 0) {
					fout << endl;
				}
				counting = 0;
			}
		}
		else {
			fout << "B " << a << " " << b << endl;
			char c;
			c = fin.get();
			if (c == '\n') {
				c = fin.get();
			}
			if ((c - '0') == 0) {
				for (int i = 0; i < aa * bb; i++) {
					continue;
				}
			}
			else if ((c - '0') == 1) {
				for (int i = 0; i < aa; i++) {
					for (int j = 0; j < bb; j++) {
						arr[i][j] = 1;
					}
				}
			}
			else {
				DtoB(fin,arr,aa,bb,0,0,aa,bb);
			}
			int count = 0;
			for (int i = 0; i < aa; i++) {
				for (int j = 0; j < bb; j++) {
					fout << arr[i][j];
					count++;
					if (count == 50) {
						count = 0;
						fout << endl;
					}
				}
			}
			if(count != 0){
				fout << endl;
			}
		}
	}
	fin.close();
	fout.close();
}
