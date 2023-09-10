#include <iostream>
#include <fstream>
#include <cstring>
#include <string>
#include <algorithm>

using namespace std;

int arr[201][201][201] = {0};

void Make_Square() {
	for (int i = 1; i <= 200; i++) {
		for (int j = 1; j <= 200; j++) {
			for (int k = 1; k <= 200; k++) {
				if (arr[i][j][k] == 0){
					if (i == 1) {
						arr[i][j][k] = j * k;
					}
					else if (j == 1) {
						arr[i][j][k] = i * k;
					}
					else if (k == 1) {
						arr[i][j][k] = i * j;
					}
					else if (j % i == 0 && k % i == 0) {
						arr[i][j][k] = (j / i) * (k / i);
					}
					else if (i % j == 0 && k % j == 0) {
						arr[i][j][k] = (i / j) * (k / j);
					}
					else if (i % k == 0 && j % k == 0) {
						arr[i][j][k] = (i / k) * (j / k);
					}
					else {
						int min = 8000000;
						for (int qi = 1; qi < i; qi++) {
							if (min > arr[qi][j][k] + arr[i - qi][j][k]) {
								min = arr[qi][j][k] + arr[i - qi][j][k];
							}
						}
						for (int qj = 1; qj < j; qj++) {
							if (min > arr[i][qj][k] + arr[i][j - qj][k]) {
								min = arr[i][qj][k] + arr[i][j - qj][k];
							}
						}
						for (int qk = 1; qk < k; qk++) {
							if (min > arr[i][j][qk] + arr[i][j][k - qk]) {
								min = arr[i][j][qk] + arr[i][j][k - qk];
							}
						}
						arr[i][j][k] = min;
					}
					arr[i][k][j] = arr[i][j][k];
					arr[j][i][k] = arr[i][j][k];
					arr[j][k][i] = arr[i][j][k];
					arr[k][i][j] = arr[i][j][k];
					arr[k][j][i] = arr[i][j][k];
				}
			}
		}
	}
}

int main(void) {
	ifstream fin;
	fin.open("cube.inp");
	ofstream fout;
	fout.open("cube.out");
	string num;
	getline(fin, num);
	Make_Square();
	for (int i = 0; i < stoi(num); i++) {
		string a, b, c;
		getline(fin, a, ' ');
		getline(fin, b, ' ');
		getline(fin, c);
		fout << arr[stoi(a)][stoi(b)][stoi(c)] << endl;
	}
	fin.close();
	fout.close();
}
