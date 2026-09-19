package vista;

import modelo.Pedido;
import modelo.Producto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/*
    VISTA.
    Presenta información, captura acciones y muestra mensajes.
    No calcula descuentos ni impuestos, no almacena pedidos ni aplica reglas de negocio.
 */

public class PedidoVista {

    public Pedido capturarPedido() {
        return new Pedido("Ana", new ArrayList<>(List.of(
                new Producto("Monitor", new BigDecimal("1250"), 3, 8))));
    }

    // Se usa cuando el Modelo registró el pedido correctamente
    public void mostrarResultado(Pedido pedido) {
        System.out.println();
        System.out.println("Pedido registrado exitosamente");
        mostrarPedido(pedido);
    }

    public void mostrarPedido(Pedido pedido) {
        System.out.println("ID del pedido: " + pedido.getId());
        System.out.println("Cliente: " + pedido.getCliente());
        System.out.println("Productos:");
        for (Producto producto : pedido.getProductos()) {
            System.out.println("  - " + producto.getNombre() + " | " + dinero(producto.getPrecio()) + " x " + producto.getCantidad());
        }
        System.out.println("Subtotal: " + dinero(pedido.getSubtotal()));
        System.out.println("Descuento: " + dinero(pedido.getDescuento()));
        System.out.println("Impuestos: " + dinero(pedido.getImpuestos()));
        System.out.println("Total: " + dinero(pedido.getTotal()));
        System.out.println("Estado: " + pedido.getEstado());
    }

    // Se usa cuando el Modelo rechaza un pedido (llega desde el controlador)
    public void mostrarError(String mensaje) {
        System.out.println("Error:" + mensaje);
    }

    //Da formato a la salida de dinero
    protected String dinero(BigDecimal valor) {
        return "$" + valor.setScale(2, RoundingMode.HALF_UP);
    }
}
