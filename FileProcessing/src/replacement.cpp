#include <iostream>
#include <fstream>
#include <string>

using namespace std;

typedef struct makeNode {
	int value;
	struct makeNode* left;
	struct makeNode* right;
}Node;

Node* root = NULL;

Node* insert(Node* root, int value) {
	if (root == NULL) {
		root = (Node*)malloc(sizeof(Node));
		root->value = value;
		root->left = NULL;
		root->right = NULL;
	}
	else {
		if (root->value > value) {
			root->left = insert(root->left, value);
		}
		else {
			root->right = insert(root->right, value);
		}
	}
	return root;
}

Node* find(Node* root, int value, ofstream& fout) {
	if (root == NULL) {
		return NULL;
	}

	if (root->value == value) {
		return root;
	}
	else if (root->value > value) {
		fout << "0";
		return find(root->left, value, fout);
	}
	else {
		fout << "1";
		return find(root->right, value, fout);
	}
}

Node* Delete(Node* root, int value) {
	if (root == NULL) {
		return NULL;
	}

	Node* replace = NULL;

	if (root->value > value) {
		root->left = Delete(root->left, value);
	}
	else if (root->value < value) {
		root->right = Delete(root->right, value);
	}
	else {
		if (root->left != NULL && root->right != NULL) {
			replace = root->right;
			while (replace->left != NULL) {
				replace = replace->left;
			}
			root->value = replace->value;
			root->right = Delete(root->right, replace->value);
		}
		else {
			replace = (root->left == NULL) ? root->right : root->left;
			free(root);
			return replace;
			if (root->right != NULL) {
				replace = root;
				root = root->right;
				free(replace);
			}
			else {
				replace = root;
				root = root->left;
				free(replace);
			}
		}
	}
	return root;
}

int main() {
	ifstream fin;
	fin.open("bst_input.txt");
	ofstream fout;
	fout.open("bst_output.txt");
	string Testcase;
	getline(fin, Testcase);
	for (int i = 0; i < stoi(Testcase); i++) {
		string key,value;
		getline(fin, key);
		for (int j = 1; j < stoi(key); j++) {
			getline(fin, value, ' ');
			root = insert(root, stoi(value));
		}
		getline(fin, value);
		root = insert(root, stoi(value));
		string search;
		getline(fin, search);
		for (int j = 1; j< stoi(search); j++) {
			getline(fin, value, ' ');
			fout << "R";
			find(root, stoi(value),fout);
			fout << endl;
		}
		getline(fin, value);
		fout << "R";
		find(root, stoi(value), fout);
		fout << endl;
		string trash;
		getline(fin, trash);
		for (int j = 1; j < stoi(trash); j++) {
			getline(fin, value, ' ');
			root = Delete(root,stoi(value));
		}
		getline(fin, value);
		root = Delete(root, stoi(value));
		getline(fin, search);

		for (int j = 1; j < stoi(search); j++) {
			getline(fin, value, ' ');
			fout << "R";
			find(root, stoi(value), fout);
			fout << endl;
		}
		getline(fin, value);
		fout << "R";
		find(root, stoi(value), fout);
		fout << endl;
		root = NULL;
	}
	fin.close();
	fout.close();
}
