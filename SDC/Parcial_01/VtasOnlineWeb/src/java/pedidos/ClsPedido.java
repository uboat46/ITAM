/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedidos;

/**
 *
 * @author ESOTOMAYG
 */
public class ClsPedido {
    
    private final String idCliente;
    private final java.util.TreeMap<String, Integer> productoCantidad;
    private final int totalRecords;
    private final int totalQty;
    
    public ClsPedido(String idCliente, 
                     java.util.TreeMap<String, Integer> productoCantidad)
    {
        this.idCliente = idCliente;
        this.totalRecords = productoCantidad.size();
        this.productoCantidad = productoCantidad;
        this.totalQty = obtainQty();
    }
    
    private int obtainQty()
    {
        int res = 0;
        
        for(java.util.Map.Entry<String, Integer> entry : productoCantidad.entrySet()){
            res += entry.getValue();
        }
        return res;
    }
    
    //GETTERS
    //==================================================================
    
    //------------------------------------------------------------------
    public String getIdCliente(){return idCliente;}
    //------------------------------------------------------------------
    public java.util.TreeMap<String, Integer> getProductoCantidad(){return productoCantidad;}
    //------------------------------------------------------------------
    public int getTotalRecords(){return totalRecords;}
    //------------------------------------------------------------------
    public int getTotalQty(){return totalQty;}
    //------------------------------------------------------------------
    
    //==================================================================
    
    public static void main(String[] args) {
        java.util.TreeMap<String, Integer> tree = new java.util.TreeMap<>();
        tree.put("111", 1);
        ClsPedido ped = new ClsPedido("lol", tree);
        
        System.out.println("" + ped.totalQty);
    }
}
