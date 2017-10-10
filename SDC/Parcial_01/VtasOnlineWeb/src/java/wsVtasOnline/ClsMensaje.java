/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wsVtasOnline;

import java.io.Serializable;

/**
 *
 * @author ESOTOMAYG
 */
public class ClsMensaje implements Serializable{
    private boolean blnRes;
    private int intRes;
    private String strCadenaResultado;

    public ClsMensaje() {
        blnRes = false;
        intRes = 0;
        strCadenaResultado = "New Message";
    }
    
    
    public boolean isBlnRes() {
        return blnRes;
    }

    public void setBlnRes(boolean blnRes) {
        this.blnRes = blnRes;
    }

    public int getIntRes() {
        return intRes;
    }

    public void setIntRes(int intRes) {
        this.intRes = intRes;
    }

    public String getStrCadenaResultado() {
        return strCadenaResultado;
    }

    public void setStrCadenaResultado(String strCadenaResultado) {
        this.strCadenaResultado = strCadenaResultado;
    }
    
    
}
