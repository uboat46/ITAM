/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tstmiclase;

/**
 *
 * @author sdist
 */
public class MiClasesita {
    public static void main(String[] args) {
        int i = 0;
        System.out.println("Soy MiClasesita.main");
        if(args.length > 0) System.out.println("Argumentos:");
        for (String arg : args){
            System.out.println("args["+i+"] = " + arg);
            i++;
        }
    }
}
