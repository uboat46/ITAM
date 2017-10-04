/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws_Mensaje;

import escdebaile.ClsGestorEscBaile;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import mensajes.ClsMensaje;

/**
 *
 * @author sdist
 */
@WebService(serviceName = "altaAlumno")
public class altaAlumno {

    /**
     * This is a sample web service operation
     */
    
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "altaAlumno")
    public ClsMensaje altaAlumno(@WebParam(name = "nombre") String nombre, @WebParam(name = "apPat") String apPat, @WebParam(name = "apMat") String apMat) {
        ClsGestorEscBaile gestor = new ClsGestorEscBaile();
        boolean res = false;
        mensajes.ClsMensaje m = new ClsMensaje();
        try{
            gestor.conectaBD("rafa", "rafa");
            if(gestor.conectado()){
                String arrCampos[] = {"clvAlumno","nombre","apPaterno","apMaterno"};
                String arrDatos[] = {""+((int)(Math.random()*100)),nombre,apPat,apMat};
                res = gestor.altaAlumno(arrCampos, arrDatos);
                m.setBlnRes(res);
                m.setIntRes(1);
                m.setStrCadenaResultado("A " + nombre + " le corresponde la clave 8888");
            }
        }catch(Exception e){
                m.setBlnRes(res);
                m.setIntRes(0);
                m.setStrCadenaResultado("No se puede dar de alta");
        }
        return m;
    }
}
