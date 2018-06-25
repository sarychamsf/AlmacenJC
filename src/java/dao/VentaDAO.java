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
import modelo.Venta;

/**
 *
 * @author Sary
 */
public class VentaDAO {

    private final Connection connection;

    public VentaDAO() throws URISyntaxException, SQLException {
        connection = dao.Conexion.getConnection();
    }

    public void addVenta(Venta venta) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("insert into ventas(nombre,fecha,cantidad,total) values (?,?,?,?)");
        preparedStatement.setString(1, venta.getNombre());
        preparedStatement.setDate(2, (java.sql.Date) (venta.getFecha()));
        preparedStatement.setFloat(3, venta.getCantidad());
        preparedStatement.setFloat(4, venta.getTotal());
        preparedStatement.executeUpdate();
    }

    public ArrayList<Venta> getAllVentas() throws SQLException {
        ArrayList<Venta> ventas = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from ventas");
        while (rs.next()) {
            Venta venta = new Venta();
            venta.setIdVenta(rs.getInt("idVenta"));
            venta.setNombre(rs.getString("nombre"));
            venta.setFecha(rs.getDate("fecha"));
            venta.setCantidad(rs.getFloat("cantidad"));
            venta.setTotal(rs.getFloat("total"));
            ventas.add(venta);
        }

        return ventas;
    }

    public void deleteVenta(int idVenta) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("delete from ventas where idVenta=?");
        preparedStatement.setInt(1, idVenta);
        preparedStatement.executeUpdate();
    }

    public void updateVenta(int opcion, Venta venta) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("update ventas set nombre=?,fecha=?,cantidad=?,total=?" + " where idVenta=?");
        preparedStatement.setString(1, venta.getNombre());
        preparedStatement.setDate(2, (java.sql.Date) venta.getFecha());
        preparedStatement.setFloat(3, venta.getCantidad());
        preparedStatement.setFloat(4, venta.getTotal());
        preparedStatement.setInt(5, opcion);
        preparedStatement.executeUpdate();
    }

    public Venta getVentaById(int idVenta) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from ventas where idVenta=" + "\"" + idVenta + "\"");
        Venta venta = new Venta();
        if (rs.next()) {
            venta.setIdVenta(rs.getInt("idVenta"));
            venta.setNombre(rs.getString("nombre"));
            venta.setFecha(rs.getDate("fecha"));
            venta.setCantidad(rs.getFloat("cantidad"));
            venta.setTotal(rs.getFloat("total"));
        }
        return venta;
    }

}
