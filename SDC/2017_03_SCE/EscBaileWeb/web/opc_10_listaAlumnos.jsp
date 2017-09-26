<%-- 
    Document   : respLoginDto
    Created on : 16/05/2008, 07:28:19 PM
    Author     : RGGH
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
        <%@page import="escdebaile.ClsGestorEscBaile"%>

    <body>
    <%
        
      response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
      response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
      response.setDateHeader("Expires", 0); // Proxies.
    
      escdebaile.MiModelo elModelo;
      
      ClsGestorEscBaile gestor = ((escdebaile.ClsGestorEscBaile) session.getAttribute("elGestor")); 
      
      if( gestor.conectado() )
      {         
             escdebaile.MiModelo mm = gestor.obtenModeloAlumnos();;

             int ren,col,mRen,nCol;
             mRen = mm.getRowCount();
             nCol = mm.getColumnCount();
             out.println("<h2>Página opc_10_listaAlumnos.jsp</h2>");
             out.println("<br><h3>Contenido de la tabla de alumnos</h3>");
             out.println("<br><Form action='opc_10_listaAlumnos_proc.jsp'>");
             out.println("<table border='1'><tr>");
             out.println("<th>Click</th>");
             for(col=0; col< nCol; col++ )
                 out.println("<th>" + mm.getColumnName(col) + "</th>");
             out.println("</tr>");
             for( ren = 0; ren < mRen; ren++ )
             {            
               out.println("<TR><TD><input type='radio' name='cve' value='" + mm.getValueAt(ren,0) + "'/></TD>");                

               for( col = 0; col < nCol; col++ )
               {
                  out.println("<TD>" + mm.getValueAt( ren,col) + "</TD>" );    
               }                    
               out.println("</TR>");
             }
             out.println("</table>");
             out.println("<br><input type='submit' value='ModDatosDeAlumno' name='btnModDatosAlumno'/>");                
             out.println("</form>");
      }
      else
      {
         request.getRequestDispatcher("/01_login.html").forward(request, response);               
      }                        
    %>
      <br><br><br>
      <br><br><a href="http:./02_menuEscBaile.html">Regresar al menú principal</a>
    </body>

</html>
