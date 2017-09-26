/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escbailewebfakeclient;

import wsClient_Mensaje.ClsMensaje;

/**
 *
 * @author sdist
 */
public class EscBaileWebFakeClient {
    private static ClsMensaje altaAlumno(java.lang.String nombre, java.lang.String apPat, java.lang.String apMat) {
        wsClient_Mensaje.AltaAlumno_Service service = new wsClient_Mensaje.AltaAlumno_Service();
        wsClient_Mensaje.AltaAlumno port = service.getAltaAlumnoPort();
        return port.altaAlumno(nombre, apPat, apMat);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ClsMensaje test = altaAlumno("Emiliano","Sotomayor","Gonzalez");
        
        System.out.println(test.getStrCadenaResultado());
    }
}
