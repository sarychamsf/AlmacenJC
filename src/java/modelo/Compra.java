/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Date;


/**
 *
 * @author Sary
 */
public class Compra {

    private int idCompra;
    private String nombre;
    private Date fecha;
    private int cantidad;

    public Compra() {
    }

    public Compra(String nombre, Date fecha, int cantidad) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.cantidad = cantidad;
    }

    public Compra(int idVenta, String nombre, Date fecha, int cantidad) {
        this.idCompra = idVenta;
        this.nombre = nombre;
        this.fecha = fecha;
        this.cantidad = cantidad;
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idVenta) {
        this.idCompra = idVenta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

}
