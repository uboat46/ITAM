/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedido;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sdist
 */
public class testClsPedido {
    
    /**
    *@param args The command line arguments.
    */
    public static void main(String[] args) {
        
        java.util.ArrayList<ClsItem> items = new java.util.ArrayList<>();

        for(int i = 0; i < 15; i++)
        {
            ClsItem item = new ClsItem();
            item.setIdProducto(i);
            item.setCantidad((int)((Math.random() + 1) * i * (Math.random() * 20)));
            items.add(item);
        }

        ClsPedido pedido = new ClsPedido();
        pedido.setItems(items);

        org.json.JSONObject jo = new org.json.JSONObject(pedido);

        System.out.println(jo.toString());
        
        try {    
            ClsPedido newPedido = new ClsPedido();
            tstjson.TstJSON.convertToObject(newPedido, jo.toMap());
            System.out.println("newPedido.getItems(): " + newPedido.getItems());
        } catch (IllegalAccessException ex) {
            Logger.getLogger(testClsPedido.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(testClsPedido.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(testClsPedido.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(testClsPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
