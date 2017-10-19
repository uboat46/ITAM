/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedido;

import java.util.ArrayList;

/**
 *
 * @author sdist
 */
public class ClsPedido implements java.io.Serializable{
    
    private java.util.ArrayList<ClsItem> items;
    
    public ClsPedido()
    {
        this.items = null;
    }

    public ArrayList<ClsItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<ClsItem> items) {
        this.items = items;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
