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
        //Create object JSON
        org.json.JSONObject jo = null;
        
        //Create String to stringify JSON
        String cadena = null;
        
        //Create Map to iterate over JSON
        java.util.Map<String, Object> m = null;
        
        /*
        Create ClsPersonas p which will be coded and decoded
        onto x
        */
        ClsPersona x = new ClsPersona();
        ClsPersona p = new ClsPersona();
        
        //Add values to p
        p.setNombre("Prueba");
        p.setEdad(20);
        int[] nums = {1,2,3,4,5};
        p.setNumeros(nums);
        
        //Encode p into JSON Object
        jo = new org.json.JSONObject(p);
        
        //Obtain String from JSON Object
        cadena = jo.toString();
        
        //Obtain Map from JSON Object
        m = jo.toMap();
        
        try
        {
            //Get setters from x object
            Method[] meths = x.getClass().getMethods();
            
            /*
            For each method, if it is a setter invoke that
            method using as argument the value obtained from 
            the Map object
            */
            for (Method me : meths)
            {
                String type = me.getName().substring(0, 3);
                if( type.equals("set"))
                {
                    Object parameter = m.get(me.getName().substring(3).toLowerCase());
                    Class[] cls = me.getParameterTypes();
                    //Class cl = cls[0];
                    for (Class cl : cls)
                    {
                        System.out.println(cl.getName());
                    }
                    
                    //me.invoke(x, cls[0].cast(parameter));
                }
            }
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        
        System.out.println("p.getNombre(): " + p.getNombre() + "; p.getEdad(): " + p.getEdad());
        System.out.print("[");
        for (int n : p.getNumeros())
        {
            System.out.print(n + ", ");
        }
        System.out.println("]");
        
        System.out.println("cadena: " + cadena);
        
        System.out.println("x.getNombre(): " + x.getNombre() + "; x.getEdad(): " + x.getEdad());
        System.out.print("[");
//        for (int n : x.getNumeros())
//        {
//            System.out.print(n + ", ");
//        }
//        System.out.println("]");
    }
    
}
