<%@page import="com.emergentes.modelo.ListaTarea"%>
<%
 ListaTarea item= (ListaTarea)request.getAttribute("mitarea");
 %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1><%=(item.getId()==0)? "Nuevo registro":"Editar registro" %></h1>
        <form action="ControladorPrincipal"  method="post" >
            <input type="hidden" name="id" value="<%=item.getId()%>"/>
            <table>
                <tr>
                    <td>Tarea</td>
                    <td><input type="text" name="tarea" value="<%=item.getTarea()%>"/> </td>
                </tr>
                <tr>
                      <td>Prioridad</td>
                      <td><select name="prioridad">
                              <option value="<%=item.getPrioridad() %>">Alta</option>
                              <option value="<%=item.getPrioridad() %>">Media</option>
                              <option value="<%=item.getPrioridad() %>">Baja</option>
                          </select></td>
                </tr>
                 <tr>
                      <td>Completado</td>
                      <td><select name="completado">
                              <option value="<%=item.getCompletado() %>">Concluido</option>
                              <option value="<%=item.getCompletado() %>">Pendiente</option>
                          </select></td>
                </tr>
                 <tr>   
                     <td> </td>                    
                     <td><input type="submit" value="Enviar"/></td>               
                 </tr>   
            </table>
        </form>
    </body>
</html>
