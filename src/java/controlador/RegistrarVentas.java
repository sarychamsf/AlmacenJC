/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.ProductoDAO;
import dao.RegistroDiarioDAO;
import dao.StockDAO;
import dao.VentaDAO;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Producto;
import modelo.Stock;
import modelo.Venta;

/**
 *
 * @author Sary
 */
public class RegistrarVentas extends HttpServlet {

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

            response.sendRedirect("registrarventas.jsp");
            processRequest(request, response);

            String fechaS = request.getParameter("fecha");
            java.sql.Date fecha = (java.sql.Date.valueOf(fechaS));

            String producto = request.getParameter("producto");
            float cantidad = Float.parseFloat(request.getParameter("cantidad"));

            ProductoDAO productodao = new ProductoDAO();
            Producto productoobj = productodao.getProductoById(producto);
            Float precio = productoobj.getPrecio();

            float total = precio * cantidad;

            Venta venta = new Venta(producto, (java.sql.Date) fecha, cantidad, total);
            VentaDAO ventaDAO;

            ventaDAO = new VentaDAO();
            ventaDAO.addVenta(venta);

            StockDAO stockdao = new StockDAO();
            Stock stock = stockdao.getStockByProductName(producto);

            float cantidadActual = stock.getCantidad();
            float cantidadNueva = cantidadActual - cantidad;
            stock.setCantidad(cantidadNueva);

            stockdao.updateStock(producto, stock);

            // ACTUALIZAR REGISTRO DIARIO.
            RegistroDiarioDAO registrodao = new RegistroDiarioDAO();
            modelo.RegistroDiario registro = registrodao.getRegistroById(fecha);

            if (registro.getFecha() == null) {
                // SI LA FECHA NO EXISTE EN EL REGISTRO DIARIO, CREARLA.
                registrodao.addRegistro(new modelo.RegistroDiario(fecha, total, 0, total));
            } else {
                // SI LA FECHA YA EXISTE EN EL REGISTRO DIARIO, MODIFICAR VENTA Y UTILIDAD.
                registrodao.updateRegistro(fecha, new modelo.RegistroDiario(fecha, registro.getVentas() + total, registro.getGastos(), registro.getUtilidad() + total));
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
