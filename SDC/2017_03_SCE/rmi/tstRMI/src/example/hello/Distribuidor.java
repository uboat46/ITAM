/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package example.hello;

/**
 *
 * @author RGGH
 */
public class Distribuidor 
{
    public static void main( String args[])
    {

        System.out.println("Distribuidor, inciando con:");
        if( args.length == 0)
            System.out.println("Sin argumentos");
        else
            for(int i = 0; i < args.length; i++)
                System.out.println("args[" + i + "]:" + args[i]);
        System.out.println("------------------------------------------");

        String[] argsPar = new String[0];
        
        if( args.length > 1 )
        {
           argsPar = new String[args.length-1];
           for(int i=1;i<args.length;i++)
             argsPar[i-1] = args[i];  
        }
        if( args.length == 0 ) 
        {
            System.out.println("uso:\n" + 
                               "Distribuidor Master  ip_host_localhost reset deltaTEnSegs\n" +
                               "Distribuidor Master  ip_host_localhost [reporta]\n"            +
                               "Distribuidor Cliente ip_host_localhost");
        }
        else
        {
            if( args[0].compareToIgnoreCase("Master")  == 0) Master.main( argsPar );
            if( args[0].compareToIgnoreCase("Cliente") == 0) Client.main( argsPar );
            if( args[0].compareToIgnoreCase("Server") == 0) Server.main( argsPar );
            if( args[0].compareToIgnoreCase("ServidorDeDisparo") == 0) ServidorDeDisparo.main( argsPar );
        }
        
        
    }
    
    
    
}
