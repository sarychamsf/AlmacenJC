/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.GastoDAO;
import dao.RegistroDiarioDAO;
import dao.StockDAO;
import dao.VentaDAO;
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
import modelo.Stock;
import modelo.Venta;

/**
 *
 * @author Sary
 */
public class EliminarVenta extends HttpServlet {

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

            response.sendRedirect("verventas.jsp");
            processRequest(request, response);

            int opcion = Integer.parseInt(request.getParameter("opcion"));

            StockDAO stockdao = new StockDAO();
            VentaDAO ventadao = new VentaDAO();

            Venta venta = ventadao.getVentaById(opcion);
            float cantidad = venta.getCantidad();
            String nombre = venta.getNombre(); // Nombre del producto comprado.       
            Stock stock = stockdao.getStockByProductName(nombre); // Inventario anterior.
            float cv = stock.getCantidad(); // Cantidad disponible en inventario.

            stock.setCantidad(cv + cantidad); // 
            stockdao.updateStock(nombre, stock);
            
            float total = venta.getTotal();
            Date fecha = venta.getFecha();
            ventadao.deleteVenta(opcion);

            // MODIFICAR REGISTRO SI SE ELIMINA.
            RegistroDiarioDAO registrodao = new RegistroDiarioDAO();
            modelo.RegistroDiario registro = registrodao.getRegistroById(fecha);

            if (registro.getUtilidad() == (total)) {
                registrodao.deleteRegistro(fecha);
            } else {
                registrodao.updateRegistro(fecha, new modelo.RegistroDiario(fecha, registro.getVentas()-total, registro.getGastos(), registro.getUtilidad() - total));
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
