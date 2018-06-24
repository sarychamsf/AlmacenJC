/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.ProductoDAO;
import dao.StockDAO;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Locale;
import modelo.Producto;
import modelo.Stock;
import static modelo.Stock.convertir;

/**
 *
 * @author Sary
 */
public class Pruebas {

    public static void main(String[] args) throws URISyntaxException, SQLException {

        StockDAO stockdao = new StockDAO();
        ArrayList<Stock> stocks = stockdao.getAllStock();

        for (int i = 0; i < stocks.size(); i++) {

            Stock stock = stocks.get(i);
            String nombre = stock.getNombre();
            float cantidad = stock.getCantidad();

            ProductoDAO prodao = new ProductoDAO();
            Producto producto = prodao.getProductoById(nombre);

            String precio = convertir(producto.getPrecio());
        }

    }

}
