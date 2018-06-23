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
import modelo.Compra;

/**
 *
 * @author Sary
 */
public class CompraDAO {

    private final Connection connection;

    public CompraDAO() throws URISyntaxException, SQLException {
        connection = dao.Conexion.getConnection();
    }

    public void addCompra(Compra compra) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("insert into compras(nombre,fecha,cantidad, total) values (?,?,?,?)");
        preparedStatement.setString(1, compra.getNombre());
        preparedStatement.setDate(2, (java.sql.Date)(compra.getFecha()));
        preparedStatement.setFloat(3, compra.getCantidad());
        preparedStatement.setFloat(4, compra.getTotal());
        preparedStatement.executeUpdate();
    }

    public ArrayList<Compra> getAllCompras() throws SQLException {
        ArrayList<Compra> compras = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from compras");
        while (rs.next()) {
            Compra compra = new Compra();
            compra.setIdCompra(rs.getInt("idCompra"));
            compra.setNombre(rs.getString("nombre"));
            compra.setFecha(rs.getDate("fecha"));
            compra.setCantidad(rs.getFloat("cantidad"));
            compra.setTotal(rs.getFloat("total"));
            compras.add(compra);
        }

        return compras;
    }

    public void deleteCompra(int idCompra) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("delete from compras where idCompra=?");
        preparedStatement.setInt(1, idCompra);
        preparedStatement.executeUpdate();
    }

    public void updateCompra(int opcion, Compra compra) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("update compras set nombre=?,fecha=?,cantidad=?,total=?" + " where idCompra=?");
        preparedStatement.setString(1, compra.getNombre());
        preparedStatement.setDate(2, (java.sql.Date)compra.getFecha());
        preparedStatement.setFloat(3, compra.getCantidad());
        preparedStatement.setFloat(4, compra.getTotal());
        preparedStatement.setInt(5, opcion);
        preparedStatement.executeUpdate();
    }

}
