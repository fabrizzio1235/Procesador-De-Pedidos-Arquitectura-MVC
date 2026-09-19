package modelo;

import java.math.BigDecimal;

/* (REUTILIZADO)
    Modelo de pedido que está dentro de un pedido.
    "cantidad" es lo que el cliente está solicitando, y "existencia" es lo
    que hay disponible al momento del pedido.
 */

public class Producto {
    private String nombre;
    private BigDecimal precio;
    private int cantidad;
    private int existencia;

    public Producto(String nombre, BigDecimal precio, int cantidad, int existencia) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.existencia = existencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }
}