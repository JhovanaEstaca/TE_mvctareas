
package com.emergentes.controlador;

import com.emergentes.modelo.ListaTarea;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "ControladorPrincipal", urlPatterns = {"/ControladorPrincipal"})
public class ControladorPrincipal extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //verificacion si existe el atributo 
        HttpSession ses=request.getSession();
        if(ses.getAttribute("listatar")==null){
            //lista vacia listaux
            ArrayList<ListaTarea> listaux= new ArrayList<ListaTarea>();
            ses.setAttribute("listatar", listaux);
        }
        //para editar
        ArrayList<ListaTarea> lista= (ArrayList<ListaTarea>)ses.getAttribute("listatar");
        
       String op=request.getParameter("op");
       String opcion=(op!=null)? op: "view";
       //crear un objeto vacio para opcion nuevo
       ListaTarea obj1=new ListaTarea();
       int id, posicion;
       switch(opcion){
           case "nuevo":
               request.setAttribute("mitarea", obj1);
               request.getRequestDispatcher("editar.jsp").forward(request, response);
               break;
           case "editar":
               id=Integer.parseInt(request.getParameter("id"));
               posicion=buscarIndice(request,id);
               obj1=lista.get(posicion);
               request.setAttribute("mitarea", obj1);
               request.getRequestDispatcher("editar.jsp").forward(request, response);
               break;
           case "eliminar":
                id=Integer.parseInt(request.getParameter("id"));
               posicion=buscarIndice(request,id);
              lista.remove(posicion);
              ses.setAttribute("listatar", lista);
              response.sendRedirect("index.jsp");
               break;
           case "view":
               response.sendRedirect("index.jsp");
       }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession ses=request.getSession();
        ArrayList<ListaTarea> lista= (ArrayList<ListaTarea>)ses.getAttribute("listaper");
        ListaTarea obj1=new ListaTarea();
       
        obj1.setId(Integer.parseInt(request.getParameter("id")));
        obj1.setTarea(request.getParameter("tarea"));
        obj1.setPrioridad(request.getParameter("prioridad"));
        obj1.setCompletado(request.getParameter("completado"));
        
        int idt=obj1.getId();
        if(idt==0){
            //nuevo
            //actualizar
            int ulID;
            ulID=ultimoId(request);
            obj1.setId(ulID);
            lista.add(obj1);
        }else{
            //Edicion
            lista.set(buscarIndice(request,idt), obj1);
        }
        
        ses.setAttribute("listatar", lista);
        response.sendRedirect("index.jsp");
    }
    
    // crear un metodo
    private int buscarIndice(HttpServletRequest request, int id){
        //sacar session
        HttpSession ses=request.getSession();
        //
        ArrayList<ListaTarea> lista=(ArrayList<ListaTarea>)ses.getAttribute("listatar");
        //Recorrido de la lista
             int i=0;
             
             if(lista.size()> 0){
                 while (i < lista.size()){
                   if(lista.get(i).getId()==id){
                       break;
                   }else{
                       i++;
                   }
                }
             }
            return i; 
    }
    
    private int ultimoId(HttpServletRequest request){
        HttpSession ses=request.getSession();
        ArrayList<ListaTarea> lista = (ArrayList<ListaTarea>)ses.getAttribute("listatar");
            
            int idaux=0;
            for(ListaTarea item:lista){
                idaux=item.getId();
            }
            return idaux+1;
    }

}
