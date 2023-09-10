#include <iostream>
#include <fstream>
#include <cstring>
#include <string>
#include <vector>
#include <algorithm>
#include <ctime>
using namespace std;

int arr[101][101][3] = {0,0,0};
int meet[101][101] = {0};
int cost[101][101][400] = {0};
int sum[101][101][400] = {0};

typedef struct result1 {
	vector <pair<int, int>>  path1;
}Result;

Result table[101][101][201] = {{}};


int main(void) {
	time_t start, end;
	start = time(NULL);
	ios_base::sync_with_stdio(false);
	ifstream fin;
	fin.open("path.inp");
	ofstream fout;
	fout.open("path.out");
	fin.tie(NULL);
	fout.tie(NULL);
	string num, trash;
	getline(fin, num);
	int num2 = stoi(num);
	for (int i = 0; i < num2; i++) {
		getline(fin, trash);
		string m, n, k;
		getline(fin, m, ' ');
		getline(fin, n, ' ');
		getline(fin, k);
		int mm = stoi(m), nn = stoi(n), kk = stoi(k) + 1;
		string a, b;
		if (kk != 1) {
			for (int aa = 0; aa < kk - 1; aa++) {
				getline(fin, a, ' ');
				getline(fin, b);
				arr[stoi(a)][stoi(b)][2] = 1;
			}
		}
		for (int aa = 0; aa < mm; aa++) {
			for (int bb = 0; bb < nn - 2; bb++) {
				getline(fin, a, ' ');
				arr[aa][bb][0] = stoi(a);
			}
			getline(fin, a);
			arr[aa][nn - 2][0] = stoi(a);
		}
		for (int aa = 0; aa < mm - 1; aa++) {
			for (int bb = 0; bb < nn - 1; bb++) {
				getline(fin, a, ' ');
				arr[aa][bb][1] = stoi(a);
			}
			getline(fin, a);
			arr[aa][nn - 1][1] = stoi(a);
		}
		for (int aa = 0; aa < mm; aa++) {
			for (int bb = 0; bb < nn; bb++) {
				if (arr[aa][bb][2] == 1) {
					meet[aa][bb] = meet[aa][bb] + 1;
				}
				if (aa == 0 && bb == 0) {
					sum[aa][bb][00] = 1;
					cost[aa][bb][00] = 1;
					table[aa][bb][00].path1.push_back({aa,bb});
				}
				else if (aa == 0) {
					meet[aa][bb] += meet[aa][bb - 1];
					for (int cc = 0; cc < meet[aa][bb] + 1; cc++) {
						if (cost[aa][bb - 1][cc] != 0) { 
							if (arr[aa][bb][2] == 1) { //memmove
								if (cc < meet[aa][bb]) {
									sum[aa][bb][cc + 1] = sum[aa][bb - 1][cc];
									cost[aa][bb][cc + 1] = cost[aa][bb - 1][cc] + arr[aa][bb - 1][0];
									table[aa][bb][cc + 1].path1 = table[aa][bb - 1][cc].path1;
									table[aa][bb][cc + 1].path1.push_back({aa,bb});
								}
								else {
									continue;
								}
							}
							else {
								sum[aa][bb][cc] = sum[aa][bb - 1][cc];
								cost[aa][bb][cc] = cost[aa][bb - 1][cc] + arr[aa][bb - 1][0];
								table[aa][bb][cc].path1 = table[aa][bb - 1][cc].path1;
								table[aa][bb][cc].path1.push_back({aa,bb});
							}
						}
						else {
							continue;
						}
					}
				}
				else if (bb == 0) {
					meet[aa][bb] += meet[aa - 1][bb];
					for (int cc = 0; cc < meet[aa][bb] + 1; cc++) {
						if (cost[aa - 1][bb][cc] != 0) { 
							if (arr[aa][bb][2] == 1) {
								if (cc < meet[aa][bb]) {
									sum[aa][bb][cc + 1] = sum[aa - 1][bb][cc];
									cost[aa][bb][cc + 1] = cost[aa - 1][bb][cc] + arr[aa - 1][bb][1];
									table[aa][bb][cc + 1].path1 = table[aa - 1][bb][cc].path1;
									table[aa][bb][cc + 1].path1.push_back({ aa,bb });
								}
								else {
									continue;
								}
							}
							else {
								sum[aa][bb][cc] = sum[aa - 1][bb][cc];
								cost[aa][bb][cc] = cost[aa - 1][bb][cc] + arr[aa - 1][bb][1];
								table[aa][bb][cc].path1 = table[aa - 1][bb][cc].path1;
								table[aa][bb][cc].path1.push_back({aa,bb});
							}
						}
						else {
							continue;
						}
					}
				}
				else {
					if (meet[aa - 1][bb] > meet[aa][bb - 1]) {
						meet[aa][bb] += meet[aa - 1][bb];
					}
					else {
						meet[aa][bb] += meet[aa][bb - 1];
					}
					for (int cc = 0; cc < meet[aa][bb] + 1; cc++) {
						if (cost[aa - 1][bb][cc] != 0 && (((cost[aa - 1][bb][cc] + arr[aa - 1][bb][1] < cost[aa][bb - 1][cc] + arr[aa][bb - 1][0] && cost[aa][bb - 1][cc] != 0)) || cost[aa][bb - 1][cc] == 0)) {//cost[aa - 1][bb][cc] != -1 && (((cost[aa - 1][bb][cc] + arr[aa - 1][bb][1] < cost[aa][bb - 1][cc] + arr[aa][bb - 1][0] && cost[aa][bb - 1][cc] != -1)) || cost[aa][bb - 1][cc] == -1)
							if (arr[aa][bb][2] == 1) {
								if (cc < meet[aa][bb]) {
									sum[aa][bb][cc + 1] = sum[aa - 1][bb][cc] + sum[aa][bb - 1][cc];
									if (sum[aa][bb][cc + 1] >= 100000) {
										sum[aa][bb][cc + 1] = sum[aa][bb][cc + 1] - 100000;
									}
									cost[aa][bb][cc + 1] = cost[aa - 1][bb][cc] + arr[aa - 1][bb][1];
									table[aa][bb][cc + 1].path1 = table[aa - 1][bb][cc].path1;
									table[aa][bb][cc + 1].path1.push_back({aa,bb});
								}
								else {
									continue;
								}
							}
							else {
								sum[aa][bb][cc] = sum[aa - 1][bb][cc] + sum[aa][bb - 1][cc];
								if (sum[aa][bb][cc] >= 100000) { 
									sum[aa][bb][cc] = sum[aa][bb][cc] - 100000;
								}
								cost[aa][bb][cc] = cost[aa - 1][bb][cc] + arr[aa - 1][bb][1];
								table[aa][bb][cc].path1 = table[aa - 1][bb][cc].path1;
								table[aa][bb][cc].path1.push_back({aa,bb});
							}
						}
						else if (cost[aa][bb - 1][cc] != 0 && (((cost[aa][bb - 1][cc] + arr[aa][bb - 1][0] <= cost[aa - 1][bb][cc] + arr[aa - 1][bb][1] && cost[aa - 1][bb][cc] != 0)) || cost[aa - 1][bb][cc] == 0)) { //cost[aa][bb - 1][cc] != -1 && (((cost[aa][bb - 1][cc] + arr[aa][bb - 1][0] <= cost[aa - 1][bb][cc] + arr[aa - 1][bb][1] && cost[aa - 1][bb][cc] != -1)) || cost[aa - 1][bb][cc] == -1)
							if (arr[aa][bb][2] == 1) {
								if (cc < meet[aa][bb]) {
									sum[aa][bb][cc + 1] = sum[aa - 1][bb][cc] + sum[aa][bb - 1][cc];
									if (sum[aa][bb][cc + 1] >= 100000) {
										sum[aa][bb][cc + 1] = sum[aa][bb][cc + 1] - 100000;
									}
									cost[aa][bb][cc + 1] = cost[aa][bb - 1][cc] + arr[aa][bb - 1][0];
									table[aa][bb][cc + 1].path1 = table[aa][bb - 1][cc].path1;
									table[aa][bb][cc + 1].path1.push_back({aa,bb});
								}
								else {
									continue;
								}
							}
							else {
								sum[aa][bb][cc] = sum[aa - 1][bb][cc] + sum[aa][bb - 1][cc];
								if (sum[aa][bb][cc] >= 100000) { 
									sum[aa][bb][cc] = sum[aa][bb][cc] - 100000;
								}
								cost[aa][bb][cc] = cost[aa][bb - 1][cc] + arr[aa][bb - 1][0];
								table[aa][bb][cc].path1 = table[aa][bb - 1][cc].path1;
								table[aa][bb][cc].path1.push_back({aa,bb});
							}
						}
						else {
							continue;
						}
					}
				}
			}
		}	
			fout << "Test Case No:" << i + 1 << "\n";
			
			for (int aa = 0; aa < kk; aa++) {
				if (sum[mm - 1][nn - 1][aa] != 0) {
					fout << "k:" << aa << " count:" << sum[mm - 1][nn - 1][aa] << " cost:" << cost[mm - 1][nn - 1][aa] -1 << "\n";
					for (int bb = 0; bb < table[mm - 1][nn - 1][aa].path1.size(); bb++) {
						fout << "(" << table[mm - 1][nn - 1][aa].path1[bb].first << "," << table[mm - 1][nn - 1][aa].path1[bb].second << ")";
						if (bb < table[mm - 1][nn - 1][aa].path1.size() - 1) {
							fout << "->";
						}
						else {
							fout << "\n";
						}
					}
				}
			}
			fout << "\n";
			memset(arr, 0, sizeof(arr));
			memset(meet, 0, sizeof(meet));
			memset(cost, 0, sizeof(cost));
			memset(sum, 0, sizeof(sum));
			memset(table, 0, sizeof(table));
		}
	fin.close();
	fout.close();
	end = time(NULL);
	double result = end - start;
	cout << result;
}
