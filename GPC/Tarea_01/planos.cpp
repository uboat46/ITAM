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

  clsVector3D(double x, double y, double z)
	{ 
      dbl_x = x; 
      dbl_y = y; 
      dbl_z = z;
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
	  enum edo { S0, S1, SF, SError };
	  enum edo s = S0;
	  char c;
	  int i = 0;
	  double n;
          clsVector3D w;
          while ((s != SF) && (s != SError))
		{
			is.get(c);
			if (is.eof()) c = '\0';
			switch (s)
			{
			case S0:
        if (c == '(')
        {
            s = S1;
            is >> n;
            if(n != n) s = SError;
        }
        else
					s = SError;
				  break;
			case S1:
				if (c == ',')
				{
					switch(i++)
					{
				            case 0:
						w.x(n);
						break;
					    case 1:
						w.y(n);
						break;
					}
          is >> n;
          if(n != n) s = SError;
				}
				else if (c == ')' && i == 2)
					{
						w.z(n);
						s = SF;
					}
					else
						s = SError;
        break;
			}
		}
		if (s == SF)
			cout << "todo ok\n";
		else
		{
			cout << "error en la cadena\n";
			//throw 5;
		}
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
      dbl_D = -1 * ((dbl_A * v1.x()) + (dbl_B * v1.y()) + (dbl_C * v1.z()) );
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
     os << "(" << p.A() << ")x +(" << p.B() << ")y +(" << p.C() << ")z +(" << p.D() << ") = 0" << endl;
     return os;
   }
   
   // EJER 5) Lectura del plano como A * x + B * y + c * z + D = 0. 
   friend istream& operator >>( istream& is, clsPlano3D& p )
   { 
    enum edo { S0, S1, SF, SError };
	  enum edo s = S0;
	  char c;
	  double n;
    clsPlano3D w;
    int i = 0;
    while ((s != SF) && (s != SError))
		{
      is >> n;
      if( n != n ) s = SError;
			is.get(c);
			if (is.eof()) c = '\0';
			switch (s)
			{
        case S0:
          switch(i++)
          {
            case 0:
              w.A(n);
              break;
            case 1:
              w.B(n);
              break;
            case 2:
              w.C(n);
              break;
            case 3:
              w.D(n);
              s = SF;
              break;
          }
          break;
      }
    }
    if (s == SF)
      cout << "todo ok\n";
    else
    {
      cout << "error en la cadena\n";
      //throw 5;
    }
   p = w;
	 return is;
  }

  friend double Det( clsVector3D a, clsVector3D b, clsVector3D c)
  {
     return a.x() * ( b.y() * c.z() - b.z() * c.y() ) - a.y() * ( b.x() * c.z() - b.z() * c.x() ) + a.z() * ( b.x() * c.y() - b.y() * c.x() );
  }

  friend double DetPlanos( clsPlano3D a, clsPlano3D b, clsPlano3D c)
  {
     return Det( clsVector3D(a.A(), a.B(), a.C()), clsVector3D(b.A(), b.B(), b.C()), clsVector3D(c.A(), c.B(), c.C()) );
  }

  friend double kram( clsPlano3D a, clsPlano3D b, clsPlano3D c, int i)
  {
     double res;
     switch(i)
     {
       case 0:
         res = Det( clsVector3D(a.D(), a.B(), a.C()), clsVector3D(b.D(), b.B(), b.C()), clsVector3D(c.D(), c.B(), c.C()) );
         break;
       case 1:
         res = Det( clsVector3D(a.A(), a.D(), a.C()), clsVector3D(b.A(), b.D(), b.C()), clsVector3D(c.A(), c.D(), c.C()) );
         break;
       case 2:
         res = Det( clsVector3D(a.A(), a.B(), a.D()), clsVector3D(b.A(), b.B(), b.D()), clsVector3D(c.A(), c.B(), c.D()) );
         break;
     }
     return res; 
  }

   // EJER 6) Obtener el punto que resulta de la intersecci�n de tres planos 
   friend clsVector3D interseccion( clsPlano3D P1, clsPlano3D P2, clsPlano3D P3 )
   { 
    clsVector3D a;
    double det;

    det = DetPlanos(P1, P2, P3);
    a.x( kram(P1, P2, P3, 0) / det );
	  a.y( kram(P1, P2, P3, 1) / det );
    a.z( kram(P1, P2, P3, 2) / det );
     
	  return a;
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

  clsVector3D x(1,0,0), y(0,1,0), z(0,0,1);
  clsVector3D r(1,2,5), w(1,2,3), e(0,2,4);

  clsPlano3D p(x, y, z);

  cout << p << endl;

  clsPlano3D q1(x, y, z), q2(r, w, e),q3;

  cin >> q3;

  cout << "q3 = " << q3 << endl;

  cout << "Interseccion " << interseccion( q1,q2,q3 ) << endl;

}
// ========================================================
