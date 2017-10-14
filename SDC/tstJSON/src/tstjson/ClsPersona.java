/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tstjson;

/**
 *
 * @author uboat46
 */
public class ClsPersona {
    private String nombre = null;
    private int edad = 0;
    private byte[] numeros = null;
    private ClsAttr atrr = null;

    public ClsAttr getAtrr() {
        return atrr;
    }

    public void setAtrr(ClsAttr atrr) {
        this.atrr = atrr;
    }

    public byte[] getNumeros() {
        return numeros;
    }

    public void setNumeros(byte[] numeros) {
        this.numeros = numeros;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
    
}
