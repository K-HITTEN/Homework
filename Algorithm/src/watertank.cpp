#include <iostream>
#include <fstream>
#include <cstring>
#include <string>
#include <algorithm>
#include <vector>
#include <map>
#include <tuple>

struct {
	int h = 0;
	int c = 0;
};

using namespace std;

int main(void) {
	ifstream fin;
	ofstream fout;
	fin.open("watertank.inp");
	fout.open("watertank.out");
	string num, N, M, H;;
	for (int i = 0; i < stoi(num); i++) {
		getline(fin, N, ' ');
		getline(fin, M, ' ');
		getline(fin, H);
		int n = stoi(N), m = stoi(M), h = stoi(H), limit = n * 2 + 1;
		string A, B, C;
		vector<tuple<int, int, int>> info;
		for (int j = 0; j < limit; j++) {
			getline(fin, A, ' ');
			getline(fin, B, ' ');
			getline(fin, C);
			info.push_back(make_tuple(stoi(A), stoi(B), stoi(C)));
		}
		
	}
}
