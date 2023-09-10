#include <iostream>
#include <fstream>
#include <string>
#include <vector> // #include <bits/stdc++.h>
using namespace std;

int main(void) {
	string num;
	ifstream fin;
	fin.open("grid.inp");
	ofstream fout;
	fout.open("grid.out");
	getline(fin, num);
	for (int i = 0; i < stoi(num); i++) {
		string nn, mm, kk;
		getline(fin, nn, ' ');
		getline(fin, mm, ' ');
		getline(fin, kk);
		long long  n = stoll(nn),m = stoll(mm), k = stoll(kk);
		vector <long long> arr1;
		vector <long long> arr2;
		for (int j = 0; j < k-1; j++) {
			getline(fin, nn, ' ');
			arr1.push_back(stoll(nn)/n);
			arr2.push_back(stoll(nn)/m);
		}
		getline(fin, nn);
		arr1.push_back(stoll(nn)/n);
		arr2.push_back(stoll(nn)/m);
		long long case_n = 0, case_m = 0;
		for (int j = 0; j < arr1.size(); j++) {
			if (arr1[j] < 2 ) {
				continue;
			}
			else {
				case_n += n * arr1[j];
			}
		}
		for (int j = 0; j < arr2.size(); j++) {
			if (arr2[j] < 2) {
				continue;
			}
			else {
				case_m += m * arr2[j];
			}
		}
		if (case_m >= n * m || case_n >= n * m) {
			fout << "Yes" << endl;
		}
		else {
			fout << "No" << endl;
		}
	}
	fin.close();
	fout.close();
}
