/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteprueba;

import wsTstAlta.ClsMensaje;
import wsTstAlta.ClsPedido;

/**
 *
 * @author ESOTOMAYG
 */
public class ClientePrueba {

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
     String idCliente = "12346";
     java.util.TreeMap<String, Integer> prods = null;
     prods.put("10002", 2);
     ClsPedido pedido = new ClsPedido(idCliente, prods);
    }

    private static ClsMensaje alta(java.lang.String idCliente, wsTstAlta.ClsPedido pedido) {
        wsTstAlta.AltaPedido_Service service = new wsTstAlta.AltaPedido_Service();
        wsTstAlta.AltaPedido port = service.getAltaPedidoPort();
        return port.alta(idCliente, pedido);
    }
    
}
