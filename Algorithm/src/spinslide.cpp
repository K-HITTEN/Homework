#include <iostream>
#include <fstream>
#include <string>
#pragma warning(disable:4996)

using namespace std;

int main(void) {
	string num;
	ifstream fin;
	fin.open("spinslide.inp");
	ofstream fout;
	fout.open("spinslide.out");
	getline(fin, num);
	for (int i = 0; i < stoi(num); i++) {
		string n, m;
		getline(fin, n, ' ');
		getline(fin, m);
		string** arr = new string * [stoi(n)];
		string** result = new string * [stoi(n)];
		for (int j = 0; j < stoi(n); j++) {
			arr[j] = new string[stoi(n)];
			result[j] = new string[stoi(n)];
		}
		for (int k = 0; k < stoi(n); k++) {
			for (int u = 0; u < stoi(n); u++) {
				arr[k][u] = fin.get();
			}
			fin.get();
		}
		for (int q = 0; q < stoi(m); q++) {
			for (int w = 0; w < stoi(n); w++) {
				for (int e = 0; e < stoi(n); e++) {
					result[w][e] = arr[stoi(n) - 1 - e][w];
				}
			}
			for (int e = 0; e < stoi(n); e++) {
				for (int w = stoi(n) - 1; w > 0; w--) {
					if (result[w][e] != ".") {
						continue;
					}
					else {
						int s = w;
						while (result[s][e] == ".") {
							if (s == 0) {
								break;
							}
							s--;
						}
						if (result[s][e] == "." && s == 0) {
							continue;
						}
						else {
							result[w][e] = result[s][e];
							result[s][e] = ".";
						}
					}
				}
			}
			for (int w = 0; w < stoi(n); w++) {
				for (int e = 0; e < stoi(n); e++) {
					arr[w][e] = result[w][e];
				}
			}
		}
		fout << "Case #" << i + 1 << ":" << endl;
		for (int w = 0; w < stoi(n); w++) {
			for (int e = 0; e < stoi(n); e++) {
				fout << arr[w][e];
			}
			fout << endl;
		}
		fout << endl;
	}

	fin.close();
	fout.close();
}
