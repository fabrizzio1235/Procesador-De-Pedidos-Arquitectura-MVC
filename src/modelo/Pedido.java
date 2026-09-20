package modelo;

import java.math.BigDecimal;
import java.util.List;

/* (REUTILIZADO)
    Modelo de Pedido hecho por un cliente.
    Solo guarda datos, los campos subtotal, descuento, impuestos, total
    y estado empiezan vacíos.
 */

public class Pedido {
    private int id;
    private String cliente;
    private List<Producto> productos;
    private BigDecimal subtotal;
    private BigDecimal descuento;
    private BigDecimal impuestos;
    private BigDecimal total;
    private String estado;

    // El id se asigna después, cuando el repositorio guarda el pedido.
    public Pedido (String cliente, List<Producto> productos) {
        this.cliente = cliente;
        this.productos = productos;
        this.estado = "REGISTRANDO";
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getDescuento() {
        return descuento;
    }

    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }

    public BigDecimal getImpuestos() {
        return impuestos;
    }

    public void setImpuestos(BigDecimal impuestos) {
        this.impuestos = impuestos;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}