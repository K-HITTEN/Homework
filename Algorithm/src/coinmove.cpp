#include <iostream>
#include <fstream>
#include <cstring>
#include <string>
#include <algorithm>
using namespace std;

int arr[1000001][8];

int PossibleWin(int i, int j, int p,int k) {
	if ((i - j) % p == 0 && (i - j) != 0) {
		return -1;
	}
	for (int q = 1; q <= k; q++) {
		if (q == j) {
			continue;
		}
		else {
			if (arr[(i - j)][q] == 1) {
				return -1;
			}
		}
	}
	return 1;
}

void coinmove(int p, int k, int s) {
	for (int i = 1; i <= s; i++) {
		if (i % p != 0 || i == s) {
			for (int j = 1; j <= k; j++) {
				if (i - j >= 0) {
					arr[i][j] = PossibleWin(i, j, p, k);
				}
			}
		}
	}
}

void find(int s, ofstream& fout) {
	for (int i = 1; i <= s; i++) {
		if (arr[s][i] == 1) {
			fout << s - i << endl;
			return;
		}
	}
	fout << -1 << endl;
}

int main(void) {
	ifstream fin;
	fin.open("coinmove.inp");
	ofstream fout;
	fout.open("coinmove.out");
	string num;
	getline(fin, num);
	for (int i = 0; i < stoi(num); i++) {
		string p, k, s;
		getline(fin, p, ' ');
		getline(fin, k, ' ');
		getline(fin, s);
		coinmove(stoi(p), stoi(k), stoi(s));
		find(stoi(s), fout);
		memset(arr, 0, sizeof(arr));
	}
	fin.close();
	fout.close();
}
