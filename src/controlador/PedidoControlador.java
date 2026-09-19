package controlador;

import modelo.Pedido;
import modelo.PedidoModelo;
import vista.PedidoVista;

/*TODO
* El Controlador coordinará la interacción:
*
*
*El Controlador no deberá contener las reglas de cálculo del pedido.
* */
public class PedidoControlador {

    private PedidoModelo modelo;
    private PedidoVista vista;

    public PedidoControlador(PedidoModelo modelo, PedidoVista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void registrarPedido() {

        try {
            Pedido pedido = vista.capturarPedido();
            Pedido resultado = modelo.registrarPedido(pedido);
            vista.mostrarResultado(resultado);

        } catch (IllegalArgumentException e) {
            vista.mostrarError(e.getMessage());
        }
    }
}