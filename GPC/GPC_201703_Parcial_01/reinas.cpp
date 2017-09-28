#include "stdafx.h"
#include "reinas.h"
#include <iostream>
#include <time.h>

using namespace std;
// ----- NOTA -----
//La variable global cuantas aloja la cantidad de permutaciones que se van obteniendo
long GLOBAL_cuantas = 0;

// ----- NOTA -----
//La variable global g es un apuntador a la zona de memoria que guarda las permutaciones
int *GLOBAL_g;


//Resuelve el problema de las Reinas, asumiendo que el índice es su coordenada en x y el número que tiene su coordenada en y
bool reinas(int p[], int n)
{
	bool cond = 1;
	int i = 0, j, absIndex, absPos;

	while (cond && (i < n))
	{
		j = 0;
		while (cond && (j < n))
		{
			if (i != j) {
				absPos = abs(p[i] - p[j]);
				absIndex = abs(i - j);
				if (absPos == absIndex)
				{
					cond = false;
				}
			}
			j++;
		}
		i++;
	}
	return cond;
}

//Clases de equivalencia
bool relaciones(int p[], int q[], int n)
{
	int k = 0;
	//R1
	while (q[k] == (n - 1 - p[k]) && k < n) k++;
	if (k == n) return false; else k = 0;

	//R2
	while (p[k] == q[n - 1 - k] && k < n) k++;
	if (k == n) return false; else k = 0;

	//R3
	while (q[n - 1 - p[k]] == (n - 1 - k) && k < n) k++;
	if (k == n) return false; else k = 0;

	//R4
	while (q[n - 1 - k] == (n - 1 - p[k]) && k < n) k++;
	if (k == n) return false; else k = 0;

	//R5
	while (q[n - 1 - p[k]] == (n - 1 - p[k]) && k < n) k++;
	if (k == n) return false; else k = 0;

	//R6
	while (q[n - 1 - k] == (n - 1 - p[k]) && k < n) k++;
	if (k == n) return false; else k = 0;

	//R7
	while (q[p[k]] == (n - 1 - k) && k < n) k++;
	if (k == n) return false; else k = 0;

	return true;
}

//Guarda la permutación en su espacio en el arreglo
void guarda(int n, int p[])
{
	int k;

	// ----- Uso de variable GLOBAL_cuantas y GLOBAL_g -----
	long algo = (GLOBAL_cuantas - 1) * n;
	for (k = 0; k < n; k++)
		*(GLOBAL_g + algo++) = p[k];
}

//Imprime las permutaciones guardadas en el arreglo
void imp(int n)
{
	// ----- Uso de variable GLOBAL_cuantas y GLOBAL_g -----
	long cual;
	int k;
	long algo = 0;

	for (cual = 1; cual <= GLOBAL_cuantas; cual++)
	{
		cout << cual << " -> ";
		for (k = 0; k<n; k++)
			cout << *(GLOBAL_g + algo++) << " ";
		cout << endl;
	}

}

//Hace las permutaciones
void permut(int k, int n, int p[], int d[])
{
	int i;
	if (k == n)
	{
		// ----- Actualización de variable GLOBAL_cuantas -----
		if (reinas(p, n) == 1)
		{
			/*int fila = 0;
			bool flag = true;
			int qk;
			qk = *(GLOBAL_g + fila);
			while (fila < GLOBAL_cuantas && flag)
			{

			}*/

			GLOBAL_cuantas++;
			guarda(n, p);
		}

		/* Antes imprimía
		cout << GLOBAL_cuantas << " ... ";
		for (i = 0; i < n; i++)
		cout << p[i] << " ";
		cout << endl;
		*/
	}
	else
	{
		for (i = 0; i<n; i++)
			if (d[i])
			{
				p[i] = k;
				d[i] = 0;
				permut(k + 1, n, p, d);
				d[i] = 1;
			}
	}
}

//Calcula n * n! para determinar el espacio necesario
long nfactn(int n)
{
	long r;
	r = n;
	while (n) r *= n--;
	return r;
}


#define N 8
int main_reinas(int argc, char** argv)
{
	//Declaración de variables
	char c;
	clock_t t0, t1;
	float deltaT_seg;
	long espacio;

	int i, n;
	n = N;
	int *p = (int*)malloc(sizeof(int)*n);
	int *d = (int*)malloc(sizeof(int)*n);

	//Se asigna el espacio necesario para guardar
	espacio = nfactn(n);

	//Se revisa si se puede asignar la memoria
	if (!(GLOBAL_g = (int*)malloc(espacio * sizeof(int))))
	{
		cout << "No hay memoria, necesaria: " << espacio * sizeof(int) << "." << endl;
		cin >> c;
		return 1;
	}

	//Marcar como espacio disponible
	for (i = 0; i < n; i++)
		d[i] = 1;

	//Ejecutar y medir tiempo
	t0 = clock();
	permut(0, N, p, d);
	t1 = clock();
	deltaT_seg = (float)(t1 - t0) / CLOCKS_PER_SEC;

	imp(n);

	//Imprimir y recibir en la consola
	cout << endl << "Se tarda: " << deltaT_seg << " segundos, para N = " << N << "." << endl;
	cout << "Espacio necesario: " << espacio << endl;
	cin >> c;

	return 1;
}

