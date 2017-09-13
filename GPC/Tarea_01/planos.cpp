// planos.cpp : Defines the entry point for the console application.
//

//#include "stdafx.h"
/* En este c�digo usted debe agregar las sentencias 
   necesarias ( funciones, operadores y script de pruebas )
   para: 
   // EJER 1) operador de entrada del clsVector3D
   // EJER 2) operador Producto Cruz de dos clsVector3D
   // EJER 3) Construir el plano a partir de los tres puntos v1, v2 y v3.
   // EJER 4) Impresi�n del plano A * x + B * y + C * z + D = 0.
   // EJER 5) Lectura del plano como A * x + B * y + c * z + D = 0. 
   // EJER 6) Obtener el punto que resulta de la intersecci�n de tres planos 

  En los casos de los operadores de lectura agregue lectura de input streams
  definidos en base a cadenas de caracteres, de tal forma que pueda probar
  de manera r�pida sus operadores.

*/

#include <iostream>
using namespace std;
// ============================================
class clsVector3D
{
private:
	double dbl_x, dbl_y, dbl_z ;
public:
	clsVector3D()
	{ 
      dbl_x = dbl_y = dbl_z = 0.0;
	}
	void x( double unX ) { dbl_x = unX; }
	double x() { return dbl_x; }

	void y( double unY ) { dbl_y = unY; }
	double y() { return dbl_y; }

	void z( double unZ ) { dbl_z = unZ; }
	double z() { return dbl_z; }

    friend ostream& operator <<( ostream& os, clsVector3D& p )
	{
      os << "( " << p.x() << ", " << p.y() << ", " << p.z() << " )";
	  return os;
	}

	friend clsVector3D operator +( clsVector3D a, clsVector3D b )
	{
      clsVector3D s;
      s.x( a.x() + b.x() );
	  s.y( a.y() + b.y() );
	  s.z( a.z() + b.z() );

	  return s;
	}

  friend clsVector3D operator -( clsVector3D a, clsVector3D b )
	{
      clsVector3D s;
      s.x( a.x() - b.x() );
	    s.y( a.y() - b.y() );
	    s.z( a.z() - b.z() );

	  return s;
  }
  
	friend clsVector3D operator * ( double esc, clsVector3D v )
	{
      clsVector3D w;

	  w.x( esc * v.x() );
	  w.y( esc * v.y() );
	  w.z( esc * v.z() );

	  return w;
	}

	friend clsVector3D operator * ( clsVector3D v, double esc )
	{
      return esc * v;
	}

   // EJER 1) operador de entrada del clsVector3D:
	friend istream& operator >>( istream& is, clsVector3D& v )
	{
      // por lo pronto solo es un dummy que regresa el (0,1.0,2.0)
    clsVector3D w;
	  w.y(1.0);
	  w.z(2.0);

	  v = w;
	  return is;
	}
  // EJER 2) operador Producto Cruz de dos clsVector3D
	friend clsVector3D operator *( clsVector3D& a, clsVector3D& b )
	{
      clsVector3D w;
      w.x(a.y() * b.z() - b.y() * a.z());
      w.y(b.x() * a.z() - a.x() * b.z());
      w.z(a.x() * b.y() - a.y() * b.x());
	  return w;
	}
};
// =============================================
class clsPlano3D
{
private:
   double dbl_A, dbl_B, dbl_C, dbl_D; // representa el plano Ax + By + Cz + D = 0.
public:
   clsPlano3D()
   {
      dbl_A = dbl_B = dbl_C = dbl_D = 0.0;
   }

   // EJER 3)  Construir el plano a partir de los tres puntos v1, v2 y v3.
   clsPlano3D( clsVector3D v1, clsVector3D v2, clsVector3D v3 )
   {  
      clsVector3D p1 = v2 - v1;
      clsVector3D p2 = v3 - v1;
      clsVector3D s = p1 * p2;
      dbl_A = s.x();
      dbl_B = s.y();
      dbl_C = s.z();
      dbl_D = 5.0;
   }
	  
   void A( double unA ) { dbl_A = unA; }
   double A() { return dbl_A; }

   void B( double unB ) { dbl_B = unB; }
   double B() { return dbl_B; }

   void C( double unC ) { dbl_C = unC; }
   double C() { return dbl_C; }

   void D( double unD ) { dbl_D = unD; }
   double D() { return dbl_D; }

   // EJER 4) Impresi�n del plano A * x + B * y + c * z + D = 0.
   friend ostream& operator <<( ostream& os, clsPlano3D& p )
   {
     os << "(" << p.A() << ")x +(" << p.B() << ")y +(" << p.C() << ")y " << p.D() << " = 0" << endl;
     return os;
   }
   
   // EJER 5) Lectura del plano como A * x + B * y + c * z + D = 0. 
   friend istream& operator >>( istream& is, clsPlano3D& p )
   { 
     clsPlano3D w;
	   p = w;
	 return is;
   }

   // EJER 6) Obtener el punto que resulta de la intersecci�n de tres planos 
   friend clsVector3D interseccion( clsPlano3D P1, clsPlano3D P2, clsPlano3D P3 )
   { 
    // Para ello defina una rutina que obtenga el determinante de un sistema de 3 x 3:
    // Sea el sistema dado por los tres vectores ( en forma horizontal ) h1,h2,h3. El determinante de tal sistema lo obtenemos como:
    
    //   Det(h1,h2,h3)	=	   h1.x * ( h2.y * h3.z – h2.z * h3.y )
    //  - h1.y * ( h2.x * h3.z – h2.z * h3.x )
    //     h1.z * ( h2.x * h3.y – h2.y * h3.x )
    
    // para encontrar los valores de x,y,z que satisfacen los tres planos lo que debe hacer, siguiendo la regla de Kramer es
    
    //   x = kram( Plano1, Plano2,Plano3, 0 ) / DetPlanos
    
    //   y = kram( Plano1,Plano2,Plano3, 1 ) / DetPlanos
    
    //   z = kram( Plano1, Plano2, Plano3, 2 ) / DetPlanos.
    
    // Siendo:
    
    //   DetPlanos es el valor del determinante de los vectores (A,B,C) de cada uno de los planos, en disposición horizontal 
    //   kram(Plano1,Plano2,Plano3,i ) obtiene el determinante de la disposición de los vectores como en DetPlanos, pero ahora se sustituye la columna i ( i de 0 a 2 ) de la matriz de los planos por la columna dada por los valores de D de cada plano.
    
     // va el dummy con el ( 100.0 ,200.0, 300.0)
     clsVector3D a;
     a.x( 100.0 );
	 a.y( 200.0 );
	 a.z( 300.0 );
	 return a;
   }

   friend double Det( clsVector3D a, clsVector3D b, clsVector3D c)
   {
      return a.x() * ( b.y() * c.z() - b.z() * c.y() ) - a.y() * ( b.x() * c.z() - b.z() * c.x() ) + a.z() * ( b.x() * c.y() - b.y() * c.x() );
   }

};
// =============================================
void main()
{
  clsVector3D a,b,c;

  a.x( 1.0 );
  a.y( 2.0 );
  a.z( 3.0 );

  b.x( -2.0 );
  b.y( -5.0 );
  b.z( -7.0 );

  cout << a << " + " << b << " = " << a + b << endl;

  cout << 5.0 << " * " << a << " = " << 5.0 * a << endl;

  cout << a << " * " << 5.0 << " = " << a * 5.0 << endl;

  cin >> c;

  cout << "c = " << c << endl;

  cout << " a * b = " << a << " * " << b << " = " << a * b << endl;

  clsPlano3D q1,q2,q3;

  cout << q1 << endl;

  cout << "Interseccion " << interseccion( q1,q2,q3 ) << endl;

}
// ========================================================
