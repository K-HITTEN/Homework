#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <algorithm>
using namespace std;

struct node {
	node * left;
	node * right;
	int deep;
};

void middle(vector<int> arr, node * n, int d) {
	vector <int> arr1;
	vector <int> arr2;
	node* left = (node*)malloc(sizeof(node));
	node* right = (node*)malloc(sizeof(node));;
	n -> deep = d;
	n -> left = left;
	n -> right = right;
	if (arr.size() > 1) {
		arr1 = { arr.begin(),max_element(arr.begin(),arr.end()) };
		arr2 = {max_element(arr.begin(),arr.end()),arr.end() };
		if (arr2.empty() != true) {
			arr2.erase(arr2.begin());
		}
		if (arr1.size() != 0) {
			middle(arr1, left, d + 1);
		}
		else {
			n->left = NULL;
		}

		if (arr2.size() != 0) {
			middle(arr2, right, d + 1);
		}
		else {
			n->right = NULL;
		}
	}
	else {
		n->left = NULL;
		n->right = NULL;
	}
}

void inorder(node* n, ofstream &fout) {
	if (n->left != NULL) {
		inorder(n->left,fout);
	}
	fout << n->deep << " ";
	if (n->right != NULL) {
		inorder(n->right,fout);
	}
}

int main(void) {
	string num;
	ifstream fin;
	fin.open("permutation.inp");
	ofstream fout;
	fout.open("permutation.out");
	getline(fin, num);
	for (int i = 0; i < stoi(num); i++) {
		string n;
		getline(fin, n);
		vector <int> arr;
		string s;
		for (int j = 1; j < stoi(n); j++) {
			getline(fin, s, ' ');
			arr.push_back(stoi(s));
		}
		getline(fin, s);
		arr.push_back(stoi(s));
		node * tree;
		tree = (node*)malloc(sizeof(node));
		int deep = 0;
		middle(arr, tree, deep);
		inorder(tree, fout);
		fout << endl;
	}
	fin.close();
	fout.close();
}
