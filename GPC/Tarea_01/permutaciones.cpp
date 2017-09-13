#include <allocators>
#include <iostream>
using namespace std;

void generaPermutacion(int k, int n, int p[], int d[]){
    if( k > n ){
        for( int i = 0; i < n; i++){
            cout << p[i];
        }
        cout << endl;
        return;
    } else {
        for( int j = 0; j < n; j++){
            if( d[j] == 0){
                d[j] = 1;
                p[j] = k;
                generaPermutacion(k + 1, n, p, d);
                d[j] = 0;
            }
        }        
    }
}

void generaPermutacionReferencias(int k, int n, int *p, int *d){
    if( k > n ){
        for( int i = 0; i < n; i++){
            cout << *(p + i);
        }
        cout << endl;
    } else {
        for ( int j = 0; j < n; j++)
        {
               if ( *(d + j) == 0 ) {
                    *(d + j) = 1;
                    *(p + j) = k;
                    generaPermutacionReferencias(k + 1, n, p, d);
                    *(d + j) = 0;
               }
        }
    }
}

int main(){
    // int n = 3;
    // int k = 1;
    // int p[3];
    // int d[3];

    // for( int i = 0; i < n; i++){
    //     d[i] = 0;
    // }

    // generaPermutacion(k, n, p, d);
    
    
    int *d, *p;
    int n = 4;
    d = (int*)(malloc(n+1 * sizeof(int)));
    p = (int*)(malloc(n+1 * sizeof(int)));

    for (int i = 0; i < n; i++){
        *(d + i) = 0;
        *(p + i) = 0;
        cout << *d << endl;
    }
    cout << endl;
    generaPermutacionReferencias(1, n, p, d);
    cout << endl;
    return 0;
}