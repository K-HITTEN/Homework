#include <iostream>
#include <fstream>
#include <string>
#pragma warning(disable:4996)
using namespace std;

int main(void) {
	string num;
	ifstream fin;
	fin.open("stairs.inp");
	ofstream fout;
	fout.open("stairs.out");
	getline(fin, num);
	for (int i = 0; i < stoi(num); i++) {
		string m, f, n;
		getline(fin, m, ' ');
		getline(fin, f, ' ');
		getline(fin, n);
		int k = ((stoi(n) - (stoi(m) - stoi(f))) / (stoi(m) - 1));
		if (((stoi(n) - (stoi(m) - stoi(f))) % (stoi(m) - 1)) != 0 || stoi(m) != stoi(f)) {
			k++;
			if (((stoi(n) - (stoi(m) - stoi(f))) % (stoi(m) - 1)) > stoi(f) - 1) {
				k++;
			}
		}
		fout << k << endl;
	}
	fin.close();
	fout.close();
}
