/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientep;

import wsTst.ClsMensaje;
import wsTst.ClsPedido;

/**
 *
 * @author ESOTOMAYG
 */
public class ClienteP {
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String idCliente = "";
        java.util.TreeMap<String, Integer> tree = new java.util.TreeMap<>();
        ClsPedido ped = new ClsPedido(idCliente, tree);
    }

    private static ClsMensaje alta(java.lang.String idCliente, wsTst.ClsPedido pedido) {
        wsTst.AltaPedido_Service service = new wsTst.AltaPedido_Service();
        wsTst.AltaPedido port = service.getAltaPedidoPort();
        return port.alta(idCliente, pedido);
    }
    
}
