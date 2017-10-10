package utileriabd;

/*
 * ClsGestorEscBaile.java
 */

/**
 *
 * @author  RGGH
 */
public class ClsGestorEscBaile
{
    ClsConexion conexion;
// ---------------------------------------------------------------------------    
    public ClsGestorEscBaile ()
    {
       conexion = new ClsConexion("EscDeBaile");
    }
// ---------------------------------------------------------------------------        
    public boolean conectaBD(String strUsuario, String strContrasenha )
    {
        return conexion.conectate (strUsuario, strContrasenha );
    }
// ---------------------------------------------------------------------------        
    public boolean conectado() 
    {
        return conexion.conectado();
    } 
// --------------------------------------------------------------------------- 
   public void desconecta()
   {
     conexion.cierraCon();  
   }
// --------------------------------------------------------------------------- 
    public java.sql.ResultSet obtenCustomers()
    {
      return conexion.obtenRS ("Customers");
    }
// --------------------------------------------------------------------------- 
    public java.sql.ResultSet obtenProductos()
    {
      return conexion.obtenRS ("Products");
    }
// --------------------------------------------------------------------------- 
    public java.util.TreeMap<String, Double> lookProductCost(String idCliente, java.util.TreeMap<String, Integer> productoCantidad)
    {   
        java.util.TreeMap<String, Double> res = new java.util.TreeMap<>();
        StringBuilder sb = new StringBuilder();
        sb.append("Select StockCode,UnitPr from Products where StockCode in (");
        for(java.util.Map.Entry<String, Integer> entry : productoCantidad.entrySet()){
            sb.append("'" + entry.getKey() + "',");
        }
        sb.deleteCharAt(sb.length() - 1 );
        sb.append(");");
        java.sql.ResultSet rs = conexion.obtenRegSelect(sb.toString());
     
        try
        {
          while(rs.next())
          {
              res.put(rs.getString("StockCode"), rs.getDouble("UnitPr"));
          }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return res;
    }
// ---------------------------------------------------------------------------
    
   public MiModelo obtenModeloAlumno( String clvAlumno )
   {
      java.sql.ResultSet rs = conexion.obtenRegSelect("Select * from tblAlumnos where clvAlumno='" + clvAlumno + "'"); 
      MiModelo elModelo = new MiModelo(rs);
      return elModelo;
   }
// ---------------------------------------------------------------------------
   public MiModelo obtenModeloAlumnos()
   {
      java.sql.ResultSet rs = conexion.obtenRegSelect("Select * from tblAlumnos"); 
      MiModelo elModelo = new MiModelo(rs);
      return elModelo;
   }
// ---------------------------------------------------------------------------
                   
// ---------------------------------------------------------------------------
    public java.sql.ResultSet obtenProfesores()
    {
        return conexion.obtenRS ("tblProfesores");
    }
// ---------------------------------------------------------------------------    
    public java.sql.ResultSet obtenBailes()
    {
        return conexion.obtenRS ("tblBailes");
    }
// ---------------------------------------------------------------------------    
    public java.sql.ResultSet obtenNiveles()
    {
        return conexion.obtenRS ("tblNiveles");
    }
// ---------------------------------------------------------------------------    
    public java.sql.ResultSet obtenGrupos()
    {
        return conexion.obtenRS ("tblGrupos");
    }
// ---------------------------------------------------------------------------    
    public java.sql.ResultSet obtenAlumnosPorGrupo(String numGpo )
    { 
       return null;
    }
// ---------------------------------------------------------------------------
//                           Obtener un solo campo
// ---------------------------------------------------------------------------
   public java.sql.ResultSet obtenCampoParaLista(String strTV, String strCampo )
   {
     String strSelect = "Select " + strCampo + " From " + strTV; 
     return conexion.obtenRegSelect(strSelect);  
   }
// ---------------------------------------------------------------------------    
//                            Altas de Pedidos
// ---------------------------------------------------------------------------
    public boolean altaPedido(String arr_nomCampos[], String arr_datos[])
    {
      int i,n;
              
      // se obtiene la colección de campos de la tabla de los alumnos
      java.util.TreeMap<String,ClsCampoBD> colCampos = conexion.obtenMapaCampos(conexion.obtenRS("Invoice_Headers"));
      
      n = arr_nomCampos.length;
      
      // se rellena el valor para cada campo
      for( i = 0; i < n; i++ )
       ((ClsCampoBD) colCampos.get(arr_nomCampos[i])).valor = arr_datos[i];
      
      // se solicita al objeto conexión que inserte el registro y se espera el resultado
      return conexion.insertaReg("Invoice_Headers", colCampos);
    }
// ---------------------------------------------------------------------------    
    public boolean modificaAlumno( MiModelo mm)
    {
        int col,colCve = -1;
        String strCve = null; 
        
        int numCampos = mm.getColumnCount();
        String[] strNomDatos = new String[numCampos];
        String[] strDatos    = new String[numCampos];
        
        for( col = 0; col < numCampos; col++ )
        {
           strNomDatos[col] = mm.getColumnName(col);
           strDatos[col]    = (String) mm.getValueAt(0, col);
           System.out.println(col + " -> " + strNomDatos[col] + ":" + strDatos[col]);
           if( strNomDatos[col].toLowerCase().startsWith("clv"))
           {
               colCve = col;
               strCve = strDatos[col];
           }
        }
        java.util.TreeMap<String,ClsCampoBD> colCampos = 
                conexion.obtenMapaCampos(conexion.obtenRegSelect("Select * from tblAlumnos where clvAlumno ='" + strCve + "';"));
        
        for( col = 0; col < numCampos; col++ )
        {
            colCampos.get(strNomDatos[col]).valor = strDatos[col];
        }
        colCampos.get(strNomDatos[colCve]).valWhere = strCve;
       
        return conexion.modificaReg("tblAlumnos", colCampos);
        
    }
    
    
// ---------------------------------------------------------------------------    
    public boolean altaProfesor()
    {
     return true;   
    }  
// ---------------------------------------------------------------------------    
    public boolean altaBaile()
    {
     return true;   
    }
// ---------------------------------------------------------------------------    
    public boolean altaNivel()
    {
     return true;   
    }
// ---------------------------------------------------------------------------    
    public boolean altaGrupo()
    {
     return true;   
    }    
// ---------------------------------------------------------------------------    
    public boolean altaAlumnoEnGrupo(String numGpo, String clvAlumno )
    {
     return true;   
    }    
// ---------------------------------------------------------------------------    
// ---------------------------------------------------------------------------    
// ---------------------------------------------------------------------------    
// ---------------------------------------------------------------------------    
    
}