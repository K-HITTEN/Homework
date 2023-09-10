#include <iostream>
#include <fstream>
#include <cstring>
#include <string>
#include <vector>
#include <algorithm>
using namespace std;

int arr[1001][5] = {0};

void make_Biggest(vector <int> card, int CN) {
	arr[1][2] = card[0]; arr[1][3] = card[0]; arr[1][4] = card[0];
	arr[2][2] = card[1]; arr[2][3] = card[1]; arr[2][4] = card[1];
	arr[3][2] = card[2]; arr[3][3] = card[2]; arr[3][4] = card[2];
	arr[4][3] = card[3] + arr[1][2];
	arr[4][2] = card[3] + arr[2][2];
	arr[4][4] = arr[4][3];
	if (CN >= 5) {
		for (int i = 5; i <= CN; i++) {
			arr[i][2] = card[i-1] + max(arr[i - 2][3], arr[i - 2][4]);
			arr[i][3] = card[i-1] + max(arr[i - 3][2], max(arr[i - 3][3], arr[i - 3][4]));
			arr[i][4] = card[i-1] + max(arr[i - 4][2], max(arr[i - 4][3], arr[i - 4][4]));
		}
	}
}

int main(void) {
	ifstream fin;
	fin.open("card.inp");
	ofstream fout;
	fout.open("card.out");
	string num;
	getline(fin, num);
	for (int i = 0; i < stoi(num); i++) {
		string cardnumber;
		getline(fin, cardnumber);
		int CN = stoi(cardnumber);
		vector <int> card;
		string n;
		for (int j = 1; j < CN; j++) {
			getline(fin, n, ' ');
			card.push_back(stoi(n));
		}
		getline(fin, n);
		card.push_back(stoi(n));
		make_Biggest(card, CN);
		fout << max(arr[CN][2],max(arr[CN][3],arr[CN][4])) << endl;
		memset(arr, 0, sizeof(arr));
	}
	fin.close();
	fout.close();
}
