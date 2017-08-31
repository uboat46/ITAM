/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tstmiclase;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sdist
 */
public class TstMiClase {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        try 
        {
            Class cArgs[] = new Class[1];
            cArgs[0] = String[].class;
            Class cl;
            if( args.length == 0)
                cl = Class.forName("tstmiclase.MiClasesita");
            else
                cl = Class.forName(args[0]);  
            Method m = cl.getMethod("main", cArgs);
            m.invoke(cl,(Object)args);
            System.out.println("OK");
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException ex) {
            Logger.getLogger(TstMiClase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
