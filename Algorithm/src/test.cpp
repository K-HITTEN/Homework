#include <iostream>
#include <fstream>
#include <vector>
#include <string>
#include <map>
#include <algorithm>
#pragma warning(disable:4996)
using namespace std;

int main(void) {
	string num;
	ifstream fin;
	fin.open("test.inp");
	ofstream fout;
	fout.open("test.out");

	getline(fin, num);
	vector<tuple<string, string, string>> arr;
	vector <pair<string, int>> name;
	int max = 0;
	for (int i = 0; i < stoi(num); i++) {
		string n, c1, c2;
		getline(fin, n, ' ');
		getline(fin, c1, ' ');
		getline(fin, c2);
		arr.push_back(make_tuple(n, c1, c2));
		int k = c1.size();
		if (max < k) {
			max = k;
		}
		if (name.empty() == true) {
			name.push_back(make_pair(c2, 1));
		}
		else {
			for (int j = 0; j < name.size(); j++) {
				if (c2 == get<0>(name[j])) {
					get<1>(name[j])++;
					break;
				}
				else {
					if (j == name.size() - 1) {
						name.push_back(make_pair(c2, 1));
						break;
					}
					else {
						continue;
					}
				}
			}
		}
	}
	sort(arr.begin(), arr.end());
	sort(name.begin(), name.end());

	for (int i = 0; i < arr.size(); i++) {
		fout << get<0>(arr[i]) << " " << get<1>(arr[i]);
		for (int j = 0; j <= max - get<1>(arr[i]).size(); j++) {
			fout << " ";
		}
		fout << get<2>(arr[i]) << endl;
	}
	for (int i = 0; i < name.size(); i++) {
		if (i == 0) {
			fout << endl;
		}
		if (get<1>(name[i]) > 1) {
			fout << get<0>(name[i]) << " " << get<1>(name[i])<<endl;
		}
	}
	fin.close();
	fout.close();
}
