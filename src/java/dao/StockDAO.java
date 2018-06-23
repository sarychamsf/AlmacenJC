/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.Stock;

/**
 *
 * @author Sary
 */
public class StockDAO {

    private final Connection connection;

    public StockDAO() throws URISyntaxException, SQLException {
        connection = dao.Conexion.getConnection();
    }

    public void addStock(Stock stock) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("insert into stock(nombre,cantidad) values (?,?)");
        preparedStatement.setString(1, stock.getNombre());
        preparedStatement.setFloat(2, stock.getCantidad());
        preparedStatement.executeUpdate();
    }

    public ArrayList<Stock> getAllStock() throws SQLException {
        ArrayList<Stock> stocks = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from stock");
        while (rs.next()) {
            Stock stock = new Stock();
            stock.setIdStock(rs.getInt("idStock"));
            stock.setNombre(rs.getString("nombre"));
            stock.setCantidad(rs.getFloat("cantidad"));
            stocks.add(stock);
        }
        return stocks;
    }

    public void deleteStock(int idStock) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("delete from stock where idStock=?");
        preparedStatement.setInt(1, idStock);
        preparedStatement.executeUpdate();
    }

    public void updateStock(String nombre, Stock stock) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("update stock set cantidad=?" + " where nombre=?");
        preparedStatement.setFloat(1, stock.getCantidad());
        preparedStatement.setString(2, nombre);
        preparedStatement.executeUpdate();
    }

    public Stock getStockByProductName(String nombre) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from stock where nombre=" + "\"" + nombre + "\"");
        Stock stock = new Stock();
        if (rs.next()) {
            stock.setIdStock(rs.getInt("idStock"));
            stock.setNombre(rs.getString("nombre"));
            stock.setCantidad(rs.getFloat("cantidad"));
        }
        return stock;
    }

}
