/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tstjson;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author uboat46
 */
public class TstJSON {

    /*=====================================================
        SOME CODE TO TEST DYNAMIC PRIMITIVE ARRAY CONVERTION
                       STILL FIXING                       
    =====================================================*/
//    public static void invokePrimitiveArrayParameter(String param, java.util.ArrayList<?> arr, 
//                                                    Object obj, Method m){
//        try 
//        {   
//            Class<?> cl = Class.forName(param);
//            java.lang.reflect.Constructor<?> cons = cl.getConstructor();
//            Object parameter = cons.newInstance();
//            for(int i = 0; i < ([?)parameter.length; i++)
//            {
//                parameter[i] = ((java.util.ArrayList<Integer>)param).get(i);
//            }
//        m.invoke(obj, (Object)param);
//        }catch (Exception ex) {
//            Logger.getLogger(TstJSON.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        
//    }

    public static Object convertToObject(Object x, java.util.Map<?,?> m) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException{
        
        //Get setters from x object
        Method[] meths = x.getClass().getMethods();

        /*
        For each method, if it is a setter invoke that
        method using as argument the value obtained from 
        the Map object
        */
        for (Method me : meths)
        {
            //Get the method name
            String methodName = me.getName();
            
            //Look for setters
            String type = methodName.substring(0, 3);
            if( type.equals("set"))
            {
                //Obtain the parameter name in proper case
                String property = methodName.substring(3,4).toLowerCase() + methodName.substring(4);
                //Get the value of the key specified to the set Method
                Object parameter = m.get(property);
                //Get the Class type of the parameter
                Class[] cls = me.getParameterTypes();
                Class<?> cl = cls[0];
                
                /*
                Checks for null parameters and do nothing
                this should set this parameters as null
                */
                boolean isNull = false;
                if(parameter == null){
                    isNull = true;
                }
                
                /*
                Checks for Object types,
                if it encounters one it instantiates a new object
                of that type of class and recursively recovers that object
                by calling convertToObject with that object as parameter
                */
                boolean isHashMap = false;
                if(!isNull && parameter.getClass().getName().equals("java.util.HashMap"))
                {
                    isHashMap = true;
                    Object attr = cl.newInstance();
                    attr = convertToObject(attr, (java.util.Map<?,?>)parameter);
                    me.invoke(x, (Object)attr);
                }
                
                /*
                Checks for primitive types,
                if it encounters one it instantiates a Reference array
                of that type an uses it as parameter on the invoke method by 
                calling the function invokePrimitiveArrayParameter
                */
                boolean isArrayList = false;
                if(!isNull && !isHashMap && cl.getName().charAt(0) == '[')
                {
                    isArrayList = true;
                    switch(cl.getName().charAt(1))
                    {
                        //Case int[]
                        case 'I':
                            int[] paramInt = new int[((java.util.ArrayList<Integer>)parameter).size()];
                            for(int i = 0; i < paramInt.length; i++)
                            {
                                paramInt[i] = ((java.util.ArrayList<Integer>)parameter).get(i);
                            }
                            me.invoke(x, (Object)paramInt);
                            break;
                        //Case double[]
                        case 'D':
                            double[] paramDbl = new double[((java.util.ArrayList<Double>)parameter).size()];
                            for(int i = 0; i < paramDbl.length; i++)
                            {
                                paramDbl[i] = ((java.util.ArrayList<Double>)parameter).get(i);
                            }
                            me.invoke(x, (Object)paramDbl);
                            break;
                        //Case byte[]
                        case 'B':
                            byte[] paramBt = new byte[((java.util.ArrayList<Byte>)parameter).size()];
                            for(int i = 0; i < paramBt.length; i++)
                            {
                                paramBt[i] = ((java.util.ArrayList<Byte>)parameter).get(i);
                            }
                            me.invoke(x, (Object)paramBt);
                            break;
                        //Case short[]
                        case 'S':
                            short[] paramSh = new short[((java.util.ArrayList<Short>)parameter).size()];
                            for(int i = 0; i < paramSh.length; i++)
                            {
                                paramSh[i] = ((java.util.ArrayList<Short>)parameter).get(i);
                            }
                            me.invoke(x, (Object)paramSh);
                            break;
                        //Case long[]
                        case 'J':
                            long[] paramLg = new long[((java.util.ArrayList<Long>)parameter).size()];
                            for(int i = 0; i < paramLg.length; i++)
                            {
                                paramLg[i] = ((java.util.ArrayList<Long>)parameter).get(i);
                            }
                            me.invoke(x, (Object)paramLg);
                            break;
                        //Case float []
                        case 'F':
                            float[] paramFl = new float[((java.util.ArrayList<Float>)parameter).size()];
                            for(int i = 0; i < paramFl.length; i++)
                            {
                                paramFl[i] = ((java.util.ArrayList<Float>)parameter).get(i);
                            }
                            me.invoke(x, (Object)paramFl);
                            break;
                        //Case boolean[]
                        case 'Z':
                            boolean[] paramBl = new boolean[((java.util.ArrayList<Boolean>)parameter).size()];
                            for(int i = 0; i < paramBl.length; i++)
                            {
                                paramBl[i] = ((java.util.ArrayList<Boolean>)parameter).get(i);
                            }
                            me.invoke(x, (Object)paramBl);
                            break;
                        //Case char[]
                        case 'C':
                            char[] paramCh = new char[((java.util.ArrayList<Character>)parameter).size()];
                            for(int i = 0; i < paramCh.length; i++)
                            {
                                paramCh[i] = ((java.util.ArrayList<Character>)parameter).get(i);
                            }
                            me.invoke(x, (Object)paramCh);
                            break;
                    }
                }

                //Check if it was not a primitive type array
                if(!isNull && !isHashMap && !isArrayList)
                {
                    if(cl.getName().equals("int"))
                    {
                        cl = Integer.class;
                    }
                    if(cl.getName().equals("double"))
                    {
                        cl = Double.class;
                    }

                    me.invoke(x, cl.cast(parameter));
                }
            }
        }
        return x;
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Create object JSON
        org.json.JSONObject jo = null;
        
        //Create String to stringify JSON
        String cadena = null;
        
        
        /*
        Create ClsPersonas p which will be coded and decoded
        onto x
        */
        ClsPersona x = new ClsPersona();
        ClsPersona p = new ClsPersona();
        
        //Add values to p
        p.setNombre("Prueba");
        p.setEdad(20);
        byte[] nums = {1,2,3,4,5};
        p.setNumeros(nums);
        ClsAttr atrr = new ClsAttr();
        atrr.setAttrInt(15);
        double[] num = {1,2,3,4,5};
        atrr.setAttrDoubleArr(num);
        p.setAtrr(atrr);
        
        //Encode p into JSON Object
        jo = new org.json.JSONObject(p);
        
        //Obtain String from JSON Object
        cadena = jo.toString();
        
        //Obtain Map from JSON Object
        java.util.Map<String, Object> m = jo.toMap();
        
        try
        {
           x = (ClsPersona) convertToObject((Object)x, m);
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        
        System.out.println("p.getNombre(): " + p.getNombre() + "; p.getEdad(): " + p.getEdad());
        System.out.print("p.getNumeros: [");
        for (byte n : p.getNumeros())
        {
            System.out.print(n + ", ");
        }
        System.out.println("]");
        System.out.println("======================================");
        System.out.println("cadena: " + cadena);
        System.out.println("======================================");
        System.out.println("x.getNombre(): " + x.getNombre() + "; x.getEdad(): " + x.getEdad());
        System.out.print("x.getNumeros: [");
        for (byte n : x.getNumeros())
        {
            System.out.print(n + ", ");
        }
        System.out.println("]");
        System.out.print("x.getAtrr().getAttrDoubleArr(): [");
        for (double n : x.getAtrr().getAttrDoubleArr())
        {
            System.out.print(n + ", ");
        }
        System.out.println("]");
        System.out.println("x.getAtrr().getAttrInt(): " + x.getAtrr().getAttrInt());
    }
    
}
