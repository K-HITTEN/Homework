#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <algorithm>
#pragma warning(disable:4996)
using namespace std;

int main(void) {
	string num;
	ifstream fin;
	fin.open("gain.inp");
	ofstream fout;
	fout.open("gain.out");
	getline(fin, num);
	for (int i = 0; i < stoi(num); i++) {
		string n;
		getline(fin, n);
		vector <int> arr;
		string s;
		int maxresult = 0;
		for (long i = 0; i < stoi(n)-1; i++) {
			getline(fin, s, ' ');
			arr.push_back(stoi(s));
		}
		getline(fin, s);
		arr.push_back(stoi(s));
		sort(arr.begin(), arr.end());
		maxresult = arr[stoi(n) - 1] + arr[stoi(n) - 2] - arr[1] - arr[0];
		fout << maxresult<<endl;
	}
	fin.close();
	fout.close();
}
