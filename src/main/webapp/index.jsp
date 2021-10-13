<%@page import="java.util.ArrayList"%>
<%@page import="com.emergentes.modelo.ListaTarea"%>
<%
    ArrayList<ListaTarea> lista = (ArrayList<ListaTarea>)session.getAttribute("listatar");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Lista de tareas</h1>
        
        <a href="ControladorPrincipal?op=nuevo">Nuevo Registro</a>
        <table border="1">
            <tr>
                <th>Id</th>
                <th>Tarea</th>
                <th>Prioridad</th>
                <th>Completado</th>
                <th></th>
                <th></th>
            </tr>
            <%
              if(lista!=null){
                  for(ListaTarea item:lista)
                  {
             %>
            <tr>
                <td><%=item.getId()%></td>
                <td><%=item.getTarea()%> </td>
                <td><%=item.getPrioridad()%></td>
                <td><%=item.getCompletado()%> </td>
                <td><a href="ControladorPrincipal?op=editar&id=<%=item.getId()%>">Modificar</a></td>
                <td><a href="ControladorPrincipal?op=eliminar&id=<%=item.getId()%>"
                       onclick="return ('onfirm(Esta seguro de eliminar del registro?');">Eliminar</a></td>
            </tr>
            <%
                   }
                }
             %>
        </table>
    </body>
</html>
