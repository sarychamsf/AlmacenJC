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
public class Venta {
    
    private int idVenta;
    private String nombre;
    private Date fecha;
    private float cantidad;
    private float total;

    public Venta() {
    }

    public Venta(String nombre, Date fecha, float cantidad, float total) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.total = total;
    }

    public Venta(int idVenta, String nombre, Date fecha, float cantidad, float total) {
        this.idVenta = idVenta;
        this.nombre = nombre;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.total = total;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
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

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
    
}
