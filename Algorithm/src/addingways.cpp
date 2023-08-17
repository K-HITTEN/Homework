#include <iostream>
#include <fstream>
#include <cstring>
#include <string>
#include <algorithm>

using namespace std;

long long dp[301][21] = {0};

long long sumCols(int cols, int rows) {
	long long sum = 0;
	if (cols < rows) {
		for (int i = 1; i <= cols; i++) {
			sum = sum + dp[cols][i];
		}
	}
	else {
		for (int i = 1; i <= rows; i++) {
			sum = sum + dp[cols][i];
		}
	}
	return sum;
}

void addingways() {
	for (int i = 1; i < 301; i++) {
		for (int j = 1; j < 21; j++) {
			if (i < j) {
				break;
			}
			else if (j == 1|| i == j) {
				dp[i][j] = 1;
			}
			else {
				dp[i][j] = sumCols(i-j,j);
			}
		}
	}
}

int main() {
	ifstream fin;
	fin.open("addingways.inp");
	ofstream fout;
	fout.open("addingways.out");
	string n, k;
	int nn, kk;
	addingways();
	while (true) {
		getline(fin, n, ' ');
		getline(fin, k);
		nn = stoi(n);
		kk = stoi(k);
		if (nn == 0 && kk == 0) {
			break;
		}
		if (nn < kk) {
			fout << 0 << endl;
		}
		else {
			fout << (dp[nn][kk]%1000000007) << endl;
		}
	}
	fin.close();
	fout.close();
}
