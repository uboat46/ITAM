/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteescbaile;

import example.hello.IServDisparo;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import ws_mensaje.ClsMensaje;

/**
 *
 * @author sdist
 */
public class ClienteEscBaile {

    /**
     * @param args the command line arguments
     */
    private static ClsMensaje altaAlumno(java.lang.String nombre, java.lang.String apPat, java.lang.String apMat) {
        ws_mensaje.AltaAlumno_Service service = new ws_mensaje.AltaAlumno_Service();
        ws_mensaje.AltaAlumno port = service.getAltaAlumnoPort();
        return port.altaAlumno(nombre, apPat, apMat);
    }
    
    public static void main(String[] args) {
        ClsMensaje m;
        
       long lngQuienSoy;
       long sumDeltaT, sumDeltaT2, dtMin = 0,dtMax = 0;
       long lngCuantosMilisFaltan;
       
       String host = (args.length < 1) ? null : args[0];
       long i,n,t0,t1,dt;
       String response;
       
       n = 2000;
       
       try 
        {
             Registry registry = LocateRegistry.getRegistry(host);
             IServDisparo servDisparo = (IServDisparo) registry.lookup("ServidorDeDisparo");
             lngQuienSoy = servDisparo.quienSoy();
             lngCuantosMilisFaltan = servDisparo.deltaTEnMilis();
             System.out.println("Cliente " + lngQuienSoy + " faltan " + lngCuantosMilisFaltan  + " milisegundos");
             sumDeltaT  = 0;
             sumDeltaT2 = 0;
             Thread.currentThread().sleep(lngCuantosMilisFaltan);
             
             for(i= 0; i < n; i++ )
             {
               t0 = System.currentTimeMillis();  
               m = altaAlumno("prueba"+Math.random(),"pruebaPat","pruebaMat");
               t1 = System.currentTimeMillis();
               dt = t1 - t0;
               sumDeltaT  += dt;
               sumDeltaT2 += dt * dt;
               if( i == 0 )
               {
                   dtMin = dt;
                   dtMax = dt;
               }
               else
               {
                   if( dt < dtMin ) dtMin = dt;
                   if( dt > dtMax ) dtMax = dt;
               }
               System.out.println("Clte " + lngQuienSoy + ": " + m.getStrCadenaResultado());
             }
             servDisparo.acumula(sumDeltaT, sumDeltaT2, n, dtMax, dtMin);
          } 
          catch (Exception e)
          {
              System.err.println("Client exception: " + e.toString());
               e.printStackTrace();
           }
    }
}
