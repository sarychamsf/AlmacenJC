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
import modelo.Gasto;

/**
 *
 * @author Sary
 */
public class GastoDAO {

    private final Connection connection;

    public GastoDAO() throws URISyntaxException, SQLException {
        connection = dao.Conexion.getConnection();
    }

    public void addGasto(Gasto gasto) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("insert into gastos(fecha,nombre, monto) values (?,?,?)");
        preparedStatement.setDate(1, (java.sql.Date) gasto.getFecha());
        preparedStatement.setString(2, gasto.getNombre());
        preparedStatement.setFloat(3, gasto.getMonto());
        preparedStatement.executeUpdate();
    }

    public ArrayList<Gasto> getAllGastos() throws SQLException {
        ArrayList<Gasto> gastos = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from gastos");
        while (rs.next()) {
            Gasto gasto = new Gasto();
            gasto.setIdGasto(rs.getInt("idGasto"));
            gasto.setFecha(rs.getDate("fecha"));
            gasto.setNombre(rs.getString("nombre"));
            gasto.setMonto(rs.getFloat("monto"));
            gastos.add(gasto);
        }
        return gastos;
    }

    public void deleteGasto(int idGasto) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("delete from gastos where idGasto=?");
        preparedStatement.setInt(1, idGasto);
        preparedStatement.executeUpdate();
    }

    public void updateGasto(int opcion, Gasto gasto) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("update gastos set fecha=?,nombre=?,monto=?" + " where idGasto=?");
        preparedStatement.setDate(1, (java.sql.Date) gasto.getFecha());
        preparedStatement.setString(2, gasto.getNombre());
        preparedStatement.setFloat(3, gasto.getMonto());
        preparedStatement.setInt(4, opcion);
        preparedStatement.executeUpdate();
    }

    public Gasto getGastoById(int idGasto) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from gastos where idGasto=" + "\"" + idGasto + "\"");
        Gasto gasto = new Gasto();
        if (rs.next()) {
            gasto.setIdGasto(rs.getInt("idGasto"));
            gasto.setFecha(rs.getDate("fecha"));
            gasto.setNombre(rs.getString("nombre"));
            gasto.setMonto(rs.getFloat("monto"));
        }
        return gasto;
    }

}
