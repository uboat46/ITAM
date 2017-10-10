/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wsVtasOnline;

import java.sql.Timestamp;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import pedidos.ClsPedido;
import utileriabd.ClsGestorEscBaile;

/**
 *
 * @author ESOTOMAYG
 */
@WebService(serviceName = "altaPedido")
public class altaPedido {
    
    
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
    @WebMethod(operationName = "alta")
    public ClsMensaje alta(@WebParam(name = "idCliente") String idCliente, @WebParam(name = "pedido") ClsPedido pedido) {
        ClsGestorEscBaile gestor = new ClsGestorEscBaile();
        boolean res = false;
        ClsMensaje m = new ClsMensaje();
        try{
            gestor.conectaBD("rafa", "rafa");
            if(gestor.conectado()){
                String invoiceNo = this.createInvoiceHeader(gestor, idCliente, pedido);
                res = !invoiceNo.equals("false");
                m.setBlnRes(res);
                m.setIntRes(1);
                m.setStrCadenaResultado("Se inserto el pedido: " + invoiceNo);
            }
        }catch(Exception e){
                m.setBlnRes(res);
                m.setIntRes(0);
                m.setStrCadenaResultado("No se puede dar de alta");
        }
        return m;
    }
    
    //Create Invoice Headers of a Pedido
    private String createInvoiceHeader(ClsGestorEscBaile gestor, String idCliente, ClsPedido pedido)
    {
        java.util.TreeMap<String, Integer> productoCantidad = new java.util.TreeMap<>();
        java.util.TreeMap<String, Double> res = new java.util.TreeMap<>();
        boolean inserted;
        double costo = 0;
        String arrCampos[] = {"CustomerID","InvoiceNo","InvDate","InvAmount","InvRecordN","InvTotalQty"};
        
        for(java.util.Map.Entry<String, Integer> entry : pedido.getProductoCantidad().entrySet()){
            productoCantidad.put(entry.getKey(), 0);
        }
        
        res = gestor.lookProductCost(idCliente, productoCantidad);
        
        for(java.util.Map.Entry<String, Integer> entry : pedido.getProductoCantidad().entrySet()){
            costo = res.get(entry.getKey()) * entry.getValue();
        }
        long milis = System.currentTimeMillis();
        String invoiceNo = idCliente + "-" + milis;
        String arrDatos[] = {idCliente, invoiceNo,"#" + new Timestamp(milis) + "#", ""+costo,"" + pedido.getTotalRecords(),"" + pedido.getTotalQty()};
        
        inserted = gestor.altaPedido(arrCampos, arrDatos);
        
        return inserted ? invoiceNo: "false";
    }
    
}
