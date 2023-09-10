#include <iostream>
#include <fstream>
#include <cstring>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int dp1[501][501] = { 0 }, dp2[501][501][501] = { 0 }, dp3[501][501][501] = { 0 };

void bin2() {

}

int bin(int n, int m) {
	int d = m % 2;
	int p = m / 2;
	if (m == n) {
		return 0;
	}
	for (int i = 0; i < p; i++) {
		bin2();
	}
	
}

int main(void) {
	ifstream fin;
	fin.open("bin.inp");
	ofstream fout;
	fout.open("bin.out");
	string num;
	getline(fin, num);
	for (int i = 0; i < stoi(num); i++) {
		string n, m, h;
		getline(fin, n, ' ');
		getline(fin, m);
		int nn = stoi(n), mm = stoi(m);
		vector<int> street;
		for (int j = 0; j < nn; j++) {
			getline(fin, h, ' ');
			street.push_back(stoi(h));
		}
		getline(fin, h);
		street.push_back(stoi(h));
		sort(street.begin(), street.end());
		for (int j = 1; j <= nn; j++) {
			for (int k = 1; k <= nn; k++) {
				if (j == k) {
					continue;
				}
				else {
					dp1[j][k] = street[k - 1] - street[j - 1];
					if (dp1[j][k] < 0) {
						dp1[j][k] = dp1[j][k] * -1;
					}
				}
			}
		}
		for (int j = 1; j <= nn; j++) {
			for (int k = 1; k <= nn; k++) {
				for (int q = 1; q <= nn; q++) {
					if (j == k||j == q||k == q) {
						continue;
					}
					else {
						dp2[j][k][q] = min(dp1[j][q], dp1[k][q]);
					}
				}
			}
		}
		fout << bin(nn, mm) << endl;
	}
	fin.close();
	fout.close();
}
