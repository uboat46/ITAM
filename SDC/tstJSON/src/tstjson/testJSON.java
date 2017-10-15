/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tstjson;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author uboat
 */
public class testJSON {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TstJSON json = new TstJSON();
        org.json.JSONObject jo = null;
        
        ClsPersona p = new ClsPersona();
        ClsAttr atr = new ClsAttr();
        ClsPersona res = new ClsPersona();
        
        double[] d = {4,5,6,7,8,9};
        atr.setAttrDoubleArr(d);
        atr.setAttrInt(15);
        
        p.setEdad(21);
        p.setNombre("Emiliano Sotomayor Gonzalez");
        byte[] b = {0,1,2,3};
        p.setNumeros(b);
        //p.setAtrr(atr);
        
        jo = new org.json.JSONObject(p);
        
        try {
            res = (ClsPersona) TstJSON.convertToObject(res, jo.toMap());
            System.out.println(res.getEdad());
            System.out.println(res.getNombre());
            System.out.println(res.getNumeros());
            System.out.println(res.getAtrr());
            
        } catch (IllegalAccessException ex) {
            Logger.getLogger(testJSON.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(testJSON.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(testJSON.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(testJSON.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
