package controlador;

import modelo.Pedido;
import modelo.PedidoModelo;
import vista.PedidoVista;

/*
    CONTROLADOR.
    Interpreta la acción que pide el usuario y coordina la Vista y el Modelo.
    El Controlador no calcula descuentos ni impuestos ni valida reglas de negocio.
 */

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
            // El Modelo rechazó el pedido (ya sea por cliente vacío, sin productos, etc.).
            // El Controlador no lo muestra, se lo pasa a la Vista.
            vista.mostrarError(e.getMessage());
        }
    }
}