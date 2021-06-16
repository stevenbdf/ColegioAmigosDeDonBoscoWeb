/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.IOException;
import java.io.PrintWriter;
import javabeans.UsuarioBean;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelos.UsuarioModel;

/**
 *
 * @author elaniin
 */
@WebServlet(name = "Login", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

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
            String usuario = request.getParameter("usuario");
            String contrasena = request.getParameter("contrasena");

            Boolean isFormNull = usuario == null && contrasena == null;

            if (isFormNull) {
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                return;
            }

            Boolean isFormValid = !usuario.trim().isEmpty() && !contrasena.trim().isEmpty();

            if (!isFormValid) {
                request.setAttribute("result", "error");
                request.setAttribute("message", "Formulario invalido");
            }

            UsuarioModel model = new UsuarioModel();

            UsuarioBean usuarioBean = model.login(usuario, contrasena);

            if (usuarioBean.getId() > 0) {
                HttpSession session = request.getSession();
                session.setAttribute("id", usuarioBean.getId());
                session.setAttribute("usuario", usuarioBean.getUsuario());
                session.setAttribute("idRol", usuarioBean.getIdRol());
                response.sendRedirect(request.getContextPath());
                return;
            } else {
                request.setAttribute("result", "error");
                request.setAttribute("message", "Credenciales incorrectas");
            }

            request.getRequestDispatcher("/login.jsp").forward(request, response);

        } catch (Exception e) {
            System.out.println(e);
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
