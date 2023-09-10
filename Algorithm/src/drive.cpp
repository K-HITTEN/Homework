include <iostream>
#include <fstream>
#include <cstring>
#include <string>
#include <algorithm>

struct Gas {
	int right = 0;
	int down = 0;
};

using namespace std;

Gas gas[101][101];
int dp[101][101][201][2];

int main(void) {
	ifstream fin;
	ofstream fout;
	fin.open("drive.inp");
	fout.open("drive.out");
		string num;
		getline(fin, num);
	for (int i = 0; i < stoi(num); i++) {
		memset(dp, 0x3f, sizeof(dp));
		memset(gas, 0, sizeof(gas));
		string M, N, L, G, S;
		getline(fin, M, ' ');
		getline(fin, N, ' ');
		getline(fin, L, ' ');
		getline(fin, G);
		int m = stoi(M), n = stoi(N), l = stoi(L), g = stoi(G);
		for (int j = 0; j < m; j++) {
			for (int k = 0; k < n - 2; k++) {
				getline(fin, S, ' ');
				gas[j][k].right = stoi(S);
			}
			getline(fin, S);
			gas[j][n - 2].right = stoi(S);
		}
		for (int j = 0; j < m - 1; j++) {
			for (int k = 0; k < n - 1; k++) {
				getline(fin, S, ' ');
				gas[j][k].down = stoi(S);
			}
			getline(fin, S);
			gas[j][n - 1].down = stoi(S);
		}
		dp[0][0][0][0] = 0;
		dp[0][0][0][1] = 0;
		for (int j = 0; j < m; j++) {
			for (int k = 0; k < n; k++) {
				for (int q = 0; q < 201; q++) {
					if (j < m - 1) {
						dp[j + 1][k][q][1] = min(dp[j][k][q][1] + gas[j][k].down, dp[j + 1][k][q][1]);
						dp[j + 1][k][q + 1][1] = min(dp[j][k][q][0] + gas[j][k].down, dp[j + 1][k][q + 1][1]);
					}
					if (k < n - 1) {
						dp[j][k + 1][q][0] = min(dp[j][k][q][0] + gas[j][k].right, dp[j][k + 1][q][0]);
						dp[j][k + 1][q + 1][0] = min(dp[j][k][q][1] + gas[j][k].right, dp[j][k + 1][q + 1][0]);
					}
				}
			}
		}
		int min = 201;
		for (int j = 0; j < 201; j++) {
			if (dp[m - 1][n - 1][j][0] <= g || dp[m - 1][n - 1][j][1] <= g) {
				min = j;
				break;
			}
		}
		if (min == 201) {
			fout << -1 << endl;
		}
		else {
			fout << ((m - 1) + (n - 1)) * l + min << endl;
		}		
	}
	fin.close();
	fout.close();
}
