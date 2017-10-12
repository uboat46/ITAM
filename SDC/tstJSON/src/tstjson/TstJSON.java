/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tstjson;

import java.lang.reflect.Method;


/**
 *
 * @author uboat46
 */
public class TstJSON {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        org.json.JSONObject jo = null;
        String cadena = null;
        ClsPersona x = new ClsPersona();
        java.util.Map<String, Object> m = null;
        
        ClsPersona p = new ClsPersona();
        p.setNombre("Prueba");
        p.setEdad(20);
        
        jo = new org.json.JSONObject(p);
        cadena = jo.toString();
        m = jo.toMap();
        
        for (java.util.Map.Entry<String, Object> entry : m.entrySet())
        {   
            String meth = entry.getKey().toString();
            String first = meth.substring(0, 1).toUpperCase();
            String method = "set"+first + meth.substring(1);
            System.out.println(method);
            Class[] s = {String.class};
            try{
                Method me = x.getClass().getMethod(method, s);
                System.out.println(me);
                me.invoke(x, entry.getValue());
            }catch(Exception e){
                
            }
        }
        
        System.out.println("p.getNombre():" + p.getNombre() + "; p.getEdad():" + p.getEdad());
        
        System.out.println("cadena: " + cadena);
        
        System.out.println("x.getNombre():" + x.getNombre() + "; x.getEdad():" + x.getEdad());
    }
    
}
