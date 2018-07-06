/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.CompraDAO;
import dao.ProductoDAO;
import dao.StockDAO;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Compra;
import modelo.Producto;
import modelo.Stock;

/**
 *
 * @author Sary
 */
public class RegistrarCompras extends HttpServlet {

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

        try {

            response.sendRedirect("registrarcompras.jsp");
            processRequest(request, response);

            String fechaS = request.getParameter("fecha");
            java.sql.Date fecha = (java.sql.Date.valueOf(fechaS));

            String producto = request.getParameter("producto");
            float cantidad = Float.parseFloat(request.getParameter("cantidad"));
            float total = Float.parseFloat(request.getParameter("total"));

            Compra compra = new Compra(producto, (java.sql.Date) fecha, cantidad, total);
            CompraDAO compraDAO;

            compraDAO = new CompraDAO();
            compraDAO.addCompra(compra);

            // ACTUALIZAR INVENTARIO.
            StockDAO stockdao = new StockDAO();
            Stock stock = stockdao.getStockByProductName(producto);

            float cantidadActual = stock.getCantidad();
            float cantidadNueva = cantidad + cantidadActual;
            stock.setCantidad(cantidadNueva);

            stockdao.updateStock(producto, stock);

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
