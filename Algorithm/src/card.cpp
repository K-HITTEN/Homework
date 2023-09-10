#include <iostream>
#include <fstream>
#include <cstring>
#include <string>
#include <algorithm>

using namespace std;

int dp[1001][1001] = {0}, arr[1001];

int card(int start, int end, int turn) {
	if (start > end) return 0;
	if (dp[start][end] > 0) return dp[start][end];

	if (turn % 2 == 1) {
		return dp[start][end] = max(arr[start] + card(start + 1, end, turn + 1), arr[end] + card(start, end - 1, turn + 1));
	}
	else {
		return dp[start][end] = min(card(start + 1, end, turn + 1), card(start, end - 1, turn + 1));
	}
}

int main() {
	ifstream fin;
	fin.open("card.inp");
	ofstream fout;
	fout.open("card.out");
	string num;
	getline(fin, num);
	for (int i = 0; i < stoi(num); i++) {
		string n,m;
		getline(fin, n);
		for (int j = 1; j < stoi(n); j++) {
			getline(fin, m, ' ');
			arr[j] = stoi(m);
		}
		getline(fin, m);
		arr[stoi(n)] = stoi(m);
		card(1,stoi(n),1);
		fout << dp[1][stoi(n)] << endl;
		memset(dp, 0, sizeof(dp));
	}
	fin.close();
	fout.close();
}
