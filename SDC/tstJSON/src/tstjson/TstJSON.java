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
    /**
     * 
     * @param x
     * @param m
     * @return Object
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws InstantiationException 
     */
    public static Object convertToObject(Object x, java.util.Map<?, ?> m) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {

        //Get setters from x object
        Method[] meths = x.getClass().getMethods();

        /*
        For each method, if it is a setter invoke that
        method using as argument the value obtained from 
        the Map object
         */
        for (Method me : meths) {
            //Get the method name
            String methodName = me.getName();

            //Look for setters
            String type = methodName.substring(0, 3);
            if (type.equals("set")) {
                //Obtain the parameter name in proper case
                String property = methodName.substring(3, 4).toLowerCase() + methodName.substring(4);
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
                if (parameter == null) {
                    isNull = true;
                }

                /*
                Checks for Object types,
                if it encounters one it instantiates a new object
                of that type of class and recursively recovers that object
                by calling convertToObject with that object as parameter
                 */
                boolean isHashMap = false;
                if (!isNull && parameter.getClass().getName().equals("java.util.HashMap")) {
                    isHashMap = true;
                    Object attr = cl.newInstance();
                    attr = convertToObject(attr, (java.util.Map<?, ?>) parameter);
                    me.invoke(x, (Object) attr);
                }

                /*
                Checks for primitive types,
                if it encounters one it instantiates a Reference array
                of that type an uses it as parameter on the invoke method by 
                calling the function invokePrimitiveArrayParameter
                 */
                boolean isArrayList = false;
                if (!isNull && !isHashMap && cl.getName().charAt(0) == '[') {
                    isArrayList = true;
                    java.util.ArrayList<Object> param;
                    switch (cl.getName().charAt(1)) {
                        //Case int[]
                        case 'I':
                            param = (java.util.ArrayList<Object>) parameter;
                            int[] paramInt = new int[param.size()];
                            for (int i = 0; i < paramInt.length; i++) {
                                paramInt[i] = (int) param.get(i);
                            }
                            me.invoke(x, (Object) paramInt);
                            break;
                        //Case double[]
                        case 'D':
                            param = (java.util.ArrayList<Object>) parameter;
                            double[] paramDbl = new double[param.size()];
                            for (int i = 0; i < paramDbl.length; i++) {
                                paramDbl[i] = (double) (Double.parseDouble(param.get(i).toString()));
                            }
                            me.invoke(x, (Object) paramDbl);
                            break;
                        //Case byte[]
                        case 'B':
                            param = (java.util.ArrayList<Object>) parameter;
                            byte[] paramBt = new byte[param.size()];
                            for (int i = 0; i < paramBt.length; i++) {
                                paramBt[i] = (byte) (Byte.parseByte(param.get(i).toString()));
                            }
                            me.invoke(x, (Object) paramBt);
                            break;
                        //Case short[]
                        case 'S':
                            param = (java.util.ArrayList<Object>) parameter;
                            short[] paramSh = new short[param.size()];
                            for (int i = 0; i < paramSh.length; i++) {
                                paramSh[i] = (short) (Short.parseShort(param.get(i).toString()));
                            }
                            me.invoke(x, (Object) paramSh);
                            break;
                        //Case long[]
                        case 'J':
                            param = (java.util.ArrayList<Object>) parameter;
                            long[] paramLg = new long[param.size()];
                            for (int i = 0; i < paramLg.length; i++) {
                                paramLg[i] = (long) (Long.parseLong(param.get(i).toString()));
                            }
                            me.invoke(x, (Object) paramLg);
                            break;
                        //Case float []
                        case 'F':
                            param = (java.util.ArrayList<Object>) parameter;
                            float[] paramFl = new float[param.size()];
                            for (int i = 0; i < paramFl.length; i++) {
                                paramFl[i] = (float) (Float.parseFloat(param.get(i).toString()));
                            }
                            me.invoke(x, (Object) paramFl);
                            break;
                        //Case boolean[]
                        case 'Z':
                            param = (java.util.ArrayList<Object>) parameter;
                            boolean[] paramBl = new boolean[param.size()];
                            for (int i = 0; i < paramBl.length; i++) {
                                paramBl[i] = (boolean) param.get(i);
                            }
                            me.invoke(x, (Object) paramBl);
                            break;
                        //Case char[]
                        case 'C':
                            param = (java.util.ArrayList<Object>) parameter;
                            char[] paramCh = new char[param.size()];
                            for (int i = 0; i < paramCh.length; i++) {
                                paramCh[i] = (char) param.get(i);
                            }
                            me.invoke(x, (Object) paramCh);
                            break;
                    }
                }

                //Check if it was not a primitive type array
                boolean isPrimitive = false;
                if (!isNull && !isHashMap && !isArrayList) {
                    isPrimitive = true;
                    switch (cl.getName()) {
                        case "int":
                            cl = Integer.class;
                            me.invoke(x, cl.cast(parameter));
                            break;
                        case "double":
                            cl = Double.class;
                            me.invoke(x, cl.cast(Double.parseDouble(parameter.toString())));
                            break;
                        case "float":
                            cl = Float.class;
                            me.invoke(x, cl.cast(Float.parseFloat(parameter.toString())));
                            break;
                        case "short":
                            cl = Short.class;
                            me.invoke(x, cl.cast(Short.parseShort(parameter.toString())));
                            break;
                        case "long":
                            cl = Long.class;
                            me.invoke(x, cl.cast(Long.parseLong(parameter.toString())));
                            break;
                        case "byte":
                            cl = Byte.class;
                            me.invoke(x, cl.cast(Byte.parseByte(parameter.toString())));
                            break;
                        case "char":
                            cl = Character.class;
                            me.invoke(x, cl.cast(((String) parameter).charAt(0)));
                            break;
                        case "boolean":
                            cl = Boolean.class;
                            me.invoke(x, cl.cast(parameter));
                            break;
                        //not a primitive, it should be another class
                        default:
                            isPrimitive = false;
                    }
                    if (!isNull && !isHashMap && !isArrayList && !isPrimitive) {
                        me.invoke(x, cl.cast(parameter));
                    }
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
        byte[] nums = {1, 2, 3, 4, 5};
        p.setNumeros(nums);
        ClsAttr atrr = new ClsAttr();
        atrr.setAttrInt(15);
        double[] num = {1, 2, 3, 4, 5};
        atrr.setAttrDoubleArr(num);
        p.setAtrr(atrr);
        p.setBt((byte) 15);
        p.setCh('_');
        p.setBl(true);
        java.util.ArrayList<Integer> arrInt = new java.util.ArrayList<>();
        arrInt.add(0);
        arrInt.add(1);
        arrInt.add(2);
        arrInt.add(3);
        arrInt.add(5);
        arrInt.add(8);
        p.setArr(arrInt);

        //Encode p into JSON Object
        jo = new org.json.JSONObject(p);

        //Obtain String from JSON Object
        cadena = jo.toString();

        //Create new JSONObject from cadena
        org.json.JSONObject jNew = new org.json.JSONObject(cadena);

        //Obtain Map from JSON Object
        java.util.Map<String, Object> m = jNew.toMap();
        //java.util.Map<String, Object> m = jo.toMap();

        try {
            x = (ClsPersona) convertToObject((Object) x, m);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("p.getNombre(): " + p.getNombre() + "; p.getEdad(): " + p.getEdad());
        System.out.print("p.getNumeros: [");
        for (byte n : p.getNumeros()) {
            System.out.print(n + ", ");
        }
        System.out.println("]");
        System.out.println("======================================");
        System.out.println("cadena: " + cadena);
        System.out.println("======================================");
        System.out.println("x.getNombre(): " + x.getNombre() + "; x.getEdad(): " + x.getEdad());
        System.out.print("x.getNumeros: [");
        for (byte n : x.getNumeros()) {
            System.out.print(n + ", ");
        }
        System.out.println("]");
        System.out.print("x.getAtrr().getAttrDoubleArr(): [");
        for (double n : x.getAtrr().getAttrDoubleArr()) {
            System.out.print(n + ", ");
        }
        System.out.println("]");
        System.out.println("x.getAtrr().getAttrInt(): " + x.getAtrr().getAttrInt());
        System.out.println("x.getCh(): " + x.getCh());
        System.out.println("x.getBt(): " + x.getBt());
        System.out.println("x.isBl(): " + x.isBl());
        System.out.print("x.getArr(): [");
        for (int n : x.getArr()) {
            System.out.print(n + ", ");
        }
        System.out.println("]");
    }

}
