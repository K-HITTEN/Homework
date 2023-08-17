#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>

int main(void) {
	int num;
	FILE* input_fp, * output_fp;
	input_fp = fopen("batch.inp", "r");
	output_fp = fopen("batch.out", "w");

	fscanf(input_fp, "%d", &num);
	if (num < 3 || num > 1000) {
		fprintf(output_fp, "overflow or underflow");
		return -1;
	}

	int cpu = 0, all = 0,n=0;

	for (int i = 1; i <= num; i++) {
		int j = 0;
		while (1) {
			fscanf(input_fp, "%d", &n);
			if (n == -1) {
				break;
			}
			j++;
			if (j % 2 == 0) {
				cpu = cpu + n;
			}
			all = all + n;
		}
	}
	
	fprintf(output_fp, "%d %d", cpu, all);
}
