#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <algorithm>
using namespace std;

int main(void) {
	ifstream fin;
	fin.open("coin.inp");
	ofstream fout;
	fout.open("coin.out");
	string num;
	getline(fin, num);
	for (int i = 0; i < stoi(num); i++) {
		string a, b, c;
		getline(fin, a, ' ');
		getline(fin, b, ' ');
		getline(fin, c);
		int aa = stoi(a)%4, bb = stoi(b)%4, cc = stoi(c)%4;
		vector<int> arr;
		arr.push_back(aa);
		arr.push_back(bb);
		arr.push_back(cc);
		sort(arr.begin(), arr.end());
		int answer;
		if ((arr[0] == 0 && arr[1] == 0 && arr[2] == 1) || (arr[0] == 0 && arr[1] == 2 && arr[2] == 2) || (arr[0] == 0 && arr[1] == 3 && arr[2] == 3)||(arr[0] == 1 && arr[1] == 1 && arr[2] == 1) || (arr[0] == 1 && arr[1] == 2 && arr[2] == 3)) {
			answer = -1;
		}
		else {
			answer = 1;
		}
		fout << "(" << a << " " << b << " " << c << ") : " << answer << endl;
	}
	fin.close();
	fout.close();
}
