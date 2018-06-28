/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.GastoDAO;
import dao.RegistroDiarioDAO;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Gasto;
import modelo.RegistroDiario;

/**
 *
 * @author Sary
 */
public class EliminarGasto extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
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

        try {

            response.sendRedirect("vergastos.jsp");
            processRequest(request, response);

            int opcion = Integer.parseInt(request.getParameter("opcion"));

            GastoDAO gastodao;

            gastodao = new GastoDAO();
            Gasto gasto = gastodao.getGastoById(opcion);
            float monto = gasto.getMonto();
            Date fecha = gasto.getFecha();
            gastodao.deleteGasto(opcion);

            // MODIFICAR REGISTRO SI SE ELIMINA.
            RegistroDiarioDAO registrodao = new RegistroDiarioDAO();
            RegistroDiario registro = registrodao.getRegistroById(fecha);

            if (registro.getUtilidad() == (-monto)) {
                registrodao.deleteRegistro(fecha);
            } else {
                registrodao.updateRegistro(fecha, new RegistroDiario(fecha, registro.getVentas(), registro.getGastos() - monto, registro.getUtilidad() + monto));
            }

        } catch (URISyntaxException | SQLException ex) {
            Logger.getLogger(CrearProducto.class.getName()).log(Level.SEVERE, null, ex);
        }

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
