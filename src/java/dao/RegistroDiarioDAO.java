/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.RegistroDiario;

/**
 *
 * @author Sary
 */
public class RegistroDiarioDAO {

    private final Connection connection;

    public RegistroDiarioDAO() throws URISyntaxException, SQLException {
        connection = dao.Conexion.getConnection();
    }

    public void addRegistro(RegistroDiario registro) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("insert into registrodiario(fecha,ventas,gastos,utilidad) values (?,?,?,?)");
        preparedStatement.setDate(1, (java.sql.Date) (registro.getFecha()));
        preparedStatement.setFloat(2, registro.getVentas());
        preparedStatement.setFloat(3, registro.getGastos());
        preparedStatement.setFloat(4, registro.getUtilidad());
        preparedStatement.executeUpdate();
    }

    public ArrayList<RegistroDiario> getAllRegistros() throws SQLException {
        ArrayList<RegistroDiario> registros = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from registrodiario");
        while (rs.next()) {
            RegistroDiario registro = new RegistroDiario();
            registro.setFecha(rs.getDate("fecha"));
            registro.setVentas(rs.getFloat("ventas"));
            registro.setGastos(rs.getFloat("gastos"));
            registro.setUtilidad(rs.getFloat("utilidad"));
            registros.add(registro);
        }
        return registros;
    }

    public void deleteRegistro(Date fecha) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("delete from registrodiario where fecha=?");
        preparedStatement.setDate(1, fecha);
        preparedStatement.executeUpdate();
    }

    public void updateRegistro(Date fecha, RegistroDiario registro) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("update registrodiario set fecha=?,ventas=?,gastos=?,utilidad=?" + " where fecha=?");
        preparedStatement.setDate(1, (java.sql.Date) registro.getFecha());
        preparedStatement.setFloat(2, registro.getVentas());
        preparedStatement.setFloat(3, registro.getGastos());
        preparedStatement.setFloat(4, registro.getUtilidad());
        preparedStatement.setDate(5, fecha);
        preparedStatement.executeUpdate();
    }

    public RegistroDiario getRegistroById(Date fecha) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from registrodiario where fecha=" + "\"" + fecha + "\"");
        RegistroDiario registro = new RegistroDiario();
        if (rs.next()) {
            registro.setFecha(rs.getDate("fecha"));
            registro.setVentas(rs.getFloat("ventas"));
            registro.setGastos(rs.getFloat("gastos"));
            registro.setUtilidad(rs.getFloat("utilidad"));
        }
        return registro;
    }

}
