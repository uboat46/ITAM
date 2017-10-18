/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tstjson;

import java.util.ArrayList;

/**
 *
 * @author uboat46
 */
public class ClsPersona {
    private String nombre = null;
    private int edad = 0;
    private double fl = 0;
    private byte bt = 0;
    private char ch = ' ';
    private boolean bl = false;
    private short sh = 0;
    private long lg = 0;
    private java.util.ArrayList<Integer> arr = null;
    
    public ArrayList<Integer> getArr() {
        return arr;
    }

    public void setArr(ArrayList<Integer> arr) {
        this.arr = arr;
    }
    
    
    public double getFl() {
        return fl;
    }

    public void setFl(double fl) {
        this.fl = fl;
    }

    public byte getBt() {
        return bt;
    }

    public void setBt(byte bt) {
        this.bt = bt;
    }

    public char getCh() {
        return ch;
    }

    public void setCh(char ch) {
        this.ch = ch;
    }

    public boolean isBl() {
        return bl;
    }

    public void setBl(boolean bl) {
        this.bl = bl;
    }

    public short getSh() {
        return sh;
    }

    public void setSh(short sh) {
        this.sh = sh;
    }

    public long getLg() {
        return lg;
    }

    public void setLg(long lg) {
        this.lg = lg;
    }
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
