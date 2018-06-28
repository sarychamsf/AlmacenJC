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
public class RegistroDiario {
    
    private Date fecha;
    private float ventas;
    private float gastos;
    private float utilidad;

    public RegistroDiario() {
    }

    public RegistroDiario(Date fecha, float ventas, float gastos, float utilidad) {
        this.fecha = fecha;
        this.ventas = ventas;
        this.gastos = gastos;
        this.utilidad = utilidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public float getVentas() {
        return ventas;
    }

    public void setVentas(float ventas) {
        this.ventas = ventas;
    }

    public float getGastos() {
        return gastos;
    }

    public void setGastos(float gastos) {
        this.gastos = gastos;
    }

    public float getUtilidad() {
        return utilidad;
    }

    public void setUtilidad(float utilidad) {
        this.utilidad = utilidad;
    }

    @Override
    public String toString() {
        return "RegistroDiario{" + "fecha=" + fecha + ", ventas=" + ventas + ", gastos=" + gastos + ", utilidad=" + utilidad + '}';
    }
    
}
