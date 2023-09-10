#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <queue>
using namespace std;

int main(void) {
	ifstream fin;
	fin.open("gridpath.inp");
	ofstream fout;
	fout.open("gridpath.out");
	string a, b, c;
	getline(fin, a, ' ');
	getline(fin, b);
	int aa = stoi(a), bb = stoi(b);
	vector<vector<int>> arr;
	vector<vector<pair<int, queue<pair<int, int>>>>> arr2;
	for (int i = 0; i < aa; i++) {
		vector<int> v;
		vector<pair<int,queue<pair<int,int>>>> v2;
		queue <pair<int,int>> q;
		for (int j = 1; j < bb; j++) {
			getline(fin, c, ' ');
			v.push_back(stoi(c));
			v2.push_back(make_pair(- 1, q));
		}
		getline(fin, c);
		v.push_back(stoi(c));
		v2.push_back(make_pair(-1, q));
		arr.push_back(v);
		arr2.push_back(v2);
	}

	for (int i = 0; i < aa; i++) {
		for (int j = 0; j < bb; j++) {
			if (i == 0 && j == 0) {
				arr2[i][j].first = arr[i][j];
				arr2[i][j].second.push(make_pair(i, j));
			}
			else if (i == 0) {
				if (arr[i][j] == -1 || arr2[i][j - 1].first == -1) {
					continue;
				}
				else {
					arr2[i][j].first = arr[i][j] + arr2[i][j - 1].first;
					arr2[i][j].second = arr2[i][j - 1].second;
					arr2[i][j].second.push(make_pair(i, j));
				}
			}
			else if (j == 0) {
				if (arr[i][j] == -1 || arr2[i-1][j].first == -1) {
					continue;
				}
				else {
					arr2[i][j].first = arr[i][j] + arr2[i - 1][j].first;
					arr2[i][j].second = arr2[i - 1][j].second;
					arr2[i][j].second.push(make_pair(i, j));
				}
			}
			else {
				if (arr[i][j] == -1 || (arr2[i - 1][j].first == -1 && arr2[i][j - 1].first == -1)) {
					continue;
				}
				else if (arr2[i][j-1].first == -1 || (arr2[i-1][j].first != -1 && arr2[i - 1][j].first <= arr2[i][j - 1].first)) {
					arr2[i][j].first = arr[i][j] + arr2[i - 1][j].first;
					arr2[i][j].second = arr2[i - 1][j].second;
					arr2[i][j].second.push(make_pair(i, j));
				}
				else{
					arr2[i][j].first = arr[i][j] + arr2[i][j - 1].first;
					arr2[i][j].second = arr2[i][j - 1].second;
					arr2[i][j].second.push(make_pair(i, j));
				}
			}
		}
	}
	if (arr2[aa - 1][bb - 1].first == -1) {
		fout << "No path.";
	}
	else {
		fout << arr2[aa - 1][bb - 1].first << endl;
		while (!arr2[aa - 1][bb - 1].second.empty()) {
			fout << "(" << arr2[aa - 1][bb - 1].second.front().first << " " << arr2[aa - 1][bb - 1].second.front().second << ")" << endl;
			arr2[aa - 1][bb - 1].second.pop();
		}
	}
	fin.close();
	fout.close();
}
