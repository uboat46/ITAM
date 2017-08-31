/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otroPaquete;

/**
 *
 * @author sdist
 */
public class MiClase {
    public static void main(String[] args) {
        int i = 0;
        System.out.println("Soy MiClase.main");
        if(args.length > 0) System.out.println("Argumentos:");
        for (String arg : args){
            System.out.println("args["+i+"] = "+arg);
            i++;
        }
    }
}
