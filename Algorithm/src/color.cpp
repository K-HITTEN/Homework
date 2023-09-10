#include <iostream>
#include <fstream>
#include <cstring>
#include <string>
#include <algorithm>

using namespace std;

int dp[5002][5002] = { 9999999 }, firstC[2][26] = { 0 }, lastC[2][26] = {0};

int main(void) {
	ifstream fin;
	fin.open("color.inp");
	ofstream fout;
	fout.open("color.out");
	string num;
	char line1[5002], line2[5002];
	int c;
	getline(fin, num);
	for (int i = 0; i < stoi(num); i++) {
		fin.getline(line1 + 1, 5002);
		fin.getline(line2 + 1, 5002);
		for (int j = 1; line1[j] != '\0'; j++) {
			if (firstC[0][line1[j] - 'A'] == 0) {
				firstC[0][line1[j] - 'A'] = j;
				lastC[0][line1[j] - 'A'] = j;
			}
			else {
				lastC[0][line1[j] - 'A'] = j;
			}
		}
		for (int j = 1; line2[j] != '\0'; j++) {
			if (firstC[1][line2[j] - 'A'] == 0) {
				firstC[1][line2[j] - 'A'] = j;
				lastC[1][line2[j] - 'A'] = j;
			}
			else {
				lastC[1][line2[j] - 'A'] = j;
			}
		}
		memset(dp, 9999999, sizeof dp);
		line1[0] = '!';
		line2[0] = '!';
		dp[0][0] = 0;
		int j, k;
		for (j = 0; line1[j] != '\0'; j++){
			for (k = 0; line2[k] != '\0'; k++) {
				int a = 0, b = 0;
				if (line1[j + 1] != '\0') {
					if (firstC[0][line1[j + 1] - 'A'] == j + 1 && (firstC[1][line1[j + 1] - 'A'] == 0 || firstC[1][line1[j + 1] - 'A'] > k)) {
						a = -(j + k + 1);
					}
					if (lastC[0][line1[j + 1] - 'A'] == j + 1 && lastC[1][line1[j + 1] - 'A'] <= k) {
						a += j + k + 1;
					}
				}
				if (line2[k + 1] != '\0') {
					if (firstC[1][line2[k + 1] - 'A'] == k + 1 && (firstC[0][line2[k + 1] - 'A'] == 0 || firstC[0][line2[k + 1] - 'A'] > j)) {
						b = -(j + k + 1);
					}
					if (lastC[1][line2[k + 1] - 'A'] == k + 1 && lastC[0][line2[k + 1] - 'A'] <= j) {
						b += j + k + 1;
					}
				}
				if (dp[j][k] + a < dp[j + 1][k]) {
					dp[j + 1][k] = dp[j][k] + a;
				}
				if (dp[j][k] + b < dp[j][k + 1]) {
					dp[j][k + 1] = dp[j][k] + b;
				}
			}
		}
		fout << dp[j-1][k-1] << endl;
		memset(firstC, 0, sizeof firstC);
		memset(lastC, 0, sizeof lastC);
	}
	fin.close();
	fout.close();
}
