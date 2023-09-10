#include <iostream>
#include <fstream>
#include <string>
#pragma warning(disable:4996)
using namespace std;

int main(void) {
	string num;
	ifstream fin;
	fin.open("snail.inp");
	ofstream fout;
	fout.open("snail.out");
	getline(fin, num);
	for (int i = 0; i < stoi(num); i++) {
		string nn, aa, bb;
		getline(fin, nn, ' ');
		getline(fin, aa, ' ');
		getline(fin, bb);
		long long a = stoll(aa), b = stoll(bb), n = stoll(nn);
		long long z = 0, q = 1, minus = 0, minus2 = 0, a_e = 0, a_w = 0, b_e = 0, b_w = 0,aw = 0, ae = 0, bw = 0, be = 0, k =1,ar =0, br =0;
		if (1 <= a && a <= n) {
			ae = 0;
			aw = 0;
			ar = 1;
			z++;
		}
		if (1 <= b && b <= n) {
			bw = 0;
			be = 0;
			br = 1;
			z++;
		}
		while (z != 2) {
			minus += 2 * k - 2;  
			minus2 += 2 * (k + 1) - 2;
			if (q * n - minus < a && a <= (q + 2) * n - minus2) {
				aw = q;
				ae = n - 1 - (q / 4);
				ar = q * n - minus;
				z++;
			}
			if (q * n - minus < b && b <= (q + 2) * n - minus2) {
				bw = q;
				be = n - 1 - (q / 4);
				br = q * n - minus;
				z++;
			}
			q = q + 2;
			k++;
		}

		if (aw == 0) {
			a_w = 0;
			a_e = a -1;
		}
		else if (aw % 4 == 1) {
			aw = aw / 4;
			ar = ar + (ae-aw);
			aw = ae;
			if (ar == a) {
				a_w = aw;
				a_e = aw;
			}
			else if (ar > a) {
				a_w = aw - (ar - a);
				a_e = aw;
			}
			else {
				a_w = aw;
				a_e = ae - (a- ar);
			}
		}
		else {
			aw = ae;
			ae = n - 1 - aw;
			ar = ar + (aw - (ae + 1));
			aw = ae + 1;
			if (ar == a) {
				a_w = aw;
				a_e = ae;
			}
			else if (ar > a) {
				a_w = aw + (ar - a);
				a_e = ae;
			}
			else {
				a_w = aw;
				a_e = ae + (a - ar);
			}
		}

		if (bw == 0) {
			b_w = 0;
			b_e = b - 1;
		}
		else if (bw % 4 == 1) {
			bw = bw / 4;
			br = br + (be - bw);
			bw = be;
			if (br == b) {
				b_w = bw;
				b_e = bw;
			}
			else if (br > b) {
				b_w = bw - (br - b);
				b_e = bw;
			}
			else {
				b_w = bw;
				b_e = be - (b - br);
			}
		}
		else {
			bw = be;
			be = n - 1 - bw;
			br = br + (bw - (be + 1));
			bw = be + 1;
			if (br == b) {
				b_w = bw;
				b_e = be;
			}
			else if (br > b) {
				b_w = bw + (br - b);
				b_e = be;
			}
			else {
				b_w = bw;
				b_e = be + (b - br);
			}
		}

		if (a_w - b_w == a_e - b_e || a_w - b_w == -(a_e - b_e)) {
			fout << "YES" << endl;
		}
		else {
			fout << "NO" << endl;
		}
	}
	fin.close();
	fout.close();
}
