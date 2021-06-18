/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javabeans.EjemplarBean;
import javabeans.PrestamoBean;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelos.EjemplarModel;
import modelos.PrestamoModel;

/**
 *
 * @author elaniin
 */
@WebServlet(name = "PrestarController", urlPatterns = {"/PrestarController"})
public class PrestarController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            EjemplarModel ejemplarModel = new EjemplarModel();
            // Validar que exista el ejemplar buscado
            System.out.println(request.getParameter("id"));

            Integer id = Integer.parseInt(request.getParameter("id"));

            if (ejemplarModel.find(id) == 0) {
                response.sendRedirect(request.getContextPath());
                return;
            }

            // Llenar lista de ejemplares disponibles
            EjemplarBean ejemplarBean = new EjemplarBean();
            List<EjemplarBean> ejemplarLista = ejemplarModel.list(ejemplarBean);
            request.setAttribute("ejemplarLista", ejemplarLista);

            if (request.getAttribute("prestamoForm") != null) {
                // Si viene el formulario para prestar, almacenar registro y redireccionar
                PrestamoBean prestamoBean = (PrestamoBean) request.getAttribute("prestamoForm");

                PrestamoModel prestamoModel = new PrestamoModel();

                // Validar que el usuario no sea nulo
                if (prestamoBean.getIdUsuario() != null) {
                    if (prestamoModel.usuarioConMora(prestamoBean.getIdUsuario())) {
                        // Validar si el usuario no tiene mora
                        request.setAttribute("error", "Usuario con mora, no puede realizar más prestamos.");
                    } else if (prestamoModel.usuarioConLimite(prestamoBean.getIdUsuario(), (int) request.getSession(false).getAttribute("idRol"))) {
                        // Validar si el usuario no ha alcanzado el limite de prestamos
                        request.setAttribute("error", "Usuario ha alcanzado el limite de prestamos.");
                    } else {
                        // Guardar prestamo
                        HashMap<Boolean, String> result = prestamoModel.insert(prestamoBean);

                        Map.Entry<Boolean, String> entry = result.entrySet().iterator().next();

                        Boolean status = entry.getKey();
                        String message = entry.getValue();

                        if (status) {
                            response.sendRedirect(request.getContextPath());
                            return;
                        } else {
                            request.setAttribute("error", message);
                        }
                    }
                }
            }

            request.getRequestDispatcher("/prestar.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
