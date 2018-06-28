/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.GastoDAO;
import dao.RegistroDiarioDAO;
import modelo.RegistroDiario;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Gasto;

/**
 *
 * @author Sary
 */
public class RegistrarGastos extends HttpServlet {

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

            // CREAR GASTO Y AGREGAR A BASE DE DATOS.
            response.sendRedirect("registrargastos.jsp");
            processRequest(request, response);

            String fechaS = request.getParameter("fecha");
            java.sql.Date fecha = (java.sql.Date.valueOf(fechaS));

            String nombre = request.getParameter("nombre");
            Float monto = Float.parseFloat(request.getParameter("monto"));

            Gasto gasto = new Gasto(fecha, nombre, monto);

            GastoDAO gastoDAO;

            gastoDAO = new GastoDAO();
            gastoDAO.addGasto(gasto);

            // ACTUALIZAR REGISTRO DIARIO.
            RegistroDiarioDAO registrodao = new RegistroDiarioDAO();
            RegistroDiario registro = registrodao.getRegistroById(fecha);

            if (registro.getFecha() == null) {
                // SI LA FECHA NO EXISTE EN EL REGISTRO DIARIO, CREARLA.
                registrodao.addRegistro(new RegistroDiario(fecha, 0, monto, ((registro.getUtilidad()) - monto)));
            } else {
                // SI LA FECHA YA EXISTE EN EL REGISTRO DIARIO, MODIFICAR GASTO Y UTILIDAD.
                registrodao.updateRegistro(fecha, new RegistroDiario(fecha, registro.getVentas(), (registro.getGastos() + monto), (registro.getVentas()-(registro.getGastos() + monto))));
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
