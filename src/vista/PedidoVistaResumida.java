package vista;

import modelo.Pedido;

/*
    SEGUNDA VISTA.
    Muestra solo el Id,el total y el estado del pedido.
 */

public class PedidoVistaResumida extends PedidoVista{

    // Al registrar solo se muestra el resumen. No se usa la misma de la clase padre
    // porque agrega el mensaje, se usa uno nuevo porque pide solo mostrar las 3 líneas
    public void mostrarResultado(Pedido pedido){
        mostrarPedido(pedido);
    }

    public void mostrarPedido(Pedido pedido){
        System.out.println("Pedido: "+ pedido.getId());
        System.out.println("Total: " + dinero(pedido.getTotal())); // Viene de la clase padre
        System.out.println("Estado: "+ pedido.getEstado());
    }

}
