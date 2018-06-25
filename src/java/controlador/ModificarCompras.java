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
import java.io.PrintWriter;
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
public class ModificarCompras extends HttpServlet {

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

            response.sendRedirect("vercompras.jsp");
            processRequest(request, response);

            int opcion = Integer.parseInt(request.getParameter("opcion"));

            String fechaS = request.getParameter("fecha");
            java.sql.Date fecha = (java.sql.Date.valueOf(fechaS));

            String nombre = request.getParameter("producto");
            Float cantidadNueva = Float.parseFloat(request.getParameter("cantidad"));

            ProductoDAO prodao = new ProductoDAO();
            Producto producto = prodao.getProductoById(nombre);
            float total = (producto.getPrecio())*cantidadNueva;

            CompraDAO compradao = new CompraDAO();
            Compra compra = new Compra(nombre, fecha, cantidadNueva, total);
            Compra compraAnterior = compradao.getCompraById(opcion);
            float cv = compraAnterior.getCantidad();
            
            compradao.updateCompra(opcion, compra);

            // MODIFICAR INVENTARIO
            
            StockDAO stockdao = new StockDAO();
            Stock stock = stockdao.getStockByProductName(nombre);
            
            float iv = stock.getCantidad();
            
            float dif = iv - cv;
            float in = dif + cantidadNueva;
            
            Stock stockNuevo = new Stock(nombre, in);
            stockdao.updateStock(nombre, stockNuevo);                        
            
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
