/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedido;

/**
 *
 * @author sdist
 */
public class ClsItem implements java.io.Serializable{
    private int idProducto;
    private int cantidad;
    
    
    public ClsItem()
    {
        this.idProducto = 0;
        this.cantidad = 0;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    public String getPrecio() {
       return "" + this.getIdProducto() + ": " + this.getCantidad();
    }
    
}
