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
public class Gasto {
    
    private int idGasto;
    private Date fecha;
    private String nombre;
    private float monto;

    public Gasto() {
    }

    public Gasto(Date fecha, String nombre, float monto) {
        this.fecha = fecha;
        this.nombre = nombre;
        this.monto = monto;
    }

    public Gasto(int idGasto, Date fecha, String nombre, float monto) {
        this.idGasto = idGasto;
        this.fecha = fecha;
        this.nombre = nombre;
        this.monto = monto;
    }

    public int getIdGasto() {
        return idGasto;
    }

    public void setIdGasto(int idGasto) {
        this.idGasto = idGasto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }
    
    
    
}
