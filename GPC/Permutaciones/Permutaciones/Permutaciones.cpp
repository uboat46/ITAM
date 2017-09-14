// Permutaciones.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <allocators>
#include <iostream>
using namespace std;

void generaPermutacion(int k, int n, int p[], int d[]) {
	if (k > n) {
		for (int i = 0; i < n; i++) {
			cout << p[i];
		}
		cout << endl;
		return;
	}
	else {
		for (int j = 0; j < n; j++) {
			if (d[j] == 0) {
				d[j] = 1;
				p[j] = k;
				generaPermutacion(k + 1, n, p, d);
				d[j] = 0;
			}
		}
	}
}
int  *r;
void generaPermutacionReferencias(int k, int n, int *p, int *d, int *s) {
	if (k > n) {
		r = s;
		for (int i = 0; i < n; i++) {
			//s--;
			*(r) = *(p + i);
			r++;
		}
	}
	else {
		for (int j = 0; j < n; j++)
		{
			if (*(d + j) == 0) {
				*(d + j) = 1;
				*(p + j) = k;
				generaPermutacionReferencias(k + 1, n, p, d, s);
				s = r;
				*(d + j) = 0;
			}
		}
	}
}

int tamListPermutaciones(int n) {
	int factorial = 1;

	for (int i = 1; i <= n; i++) {
		factorial *= i;
	}

	return factorial;
}

int main() {
	// int n = 3;
	// int k = 1;
	// int p[3];
	// int d[3];

	// for( int i = 0; i < n; i++){
	//     d[i] = 0;
	// }

	// generaPermutacion(k, n, p, d);

	char c;
	int *d, *p, *s;
	int n = 3;
	int tam = tamListPermutaciones(n) * n;
	d = (int*)(malloc(n + 1 * sizeof(int)));
	p = (int*)(malloc(n + 1 * sizeof(int)));
	s = (int*)(malloc(tam + 2 * sizeof(int)));
	if (d != NULL && p != NULL && s != NULL) {
		for (int i = 0; i < n; i++) {
			*(d + i) = 0;
			*(p + i) = 0;
		}
		cout << endl;
		generaPermutacionReferencias(1, n, p, d, s);
		int t = 0;
		for (int j = 0; j < tam; j++) {
			t = *(s + j);
			if (j % n == 0) {
				cout << endl;
			}
			cout << t;
		}

		cin >> c;
	}
	return 0;
}