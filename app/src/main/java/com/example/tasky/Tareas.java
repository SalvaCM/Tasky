package com.example.tasky;

import java.io.Serializable;

public class Tareas implements Serializable {
    private int codigo;
    private String nombre;
    private String descripcion;
    private String prioridad;
    private String fecha;
    private double precio;
    private int realizada;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("-").append(nombre).append('\n');
        sb.append("\t").append(fecha).append('\n');
        if (this.realizada==0)
        {
            sb.append("\t").append("No Realizada");
        }
        else {
            sb.append("\t").append("Realizada");
        }
        return sb.toString();
    }

    public Tareas() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getRealizada() {
        return realizada;
    }

    public void setRealizada(int realizada) {
        this.realizada = realizada;
    }
}
