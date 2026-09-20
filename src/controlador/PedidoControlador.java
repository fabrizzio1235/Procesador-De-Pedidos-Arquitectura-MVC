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
            // La vista devuelve null si el usuario regresó o eligió una opción inválida
            // no hay pedido que mandar al Modelo
            if (pedido == null){
                return;
            }
            Pedido resultado = modelo.registrarPedido(pedido);
            vista.mostrarResultado(resultado);

        } catch (IllegalArgumentException e) {
            // El Modelo rechazó el pedido (ya sea por cliente vacío, sin productos, etc.).
            // El Controlador no lo muestra, se lo pasa a la Vista.
            vista.mostrarError(e.getMessage());
        }
    }

    // Tercera Parte
    public void consultarPedido (int id){
        Pedido pedido = modelo.consultarPedido(id);

        //El Modelo devuelve null cuando el id no existe
        if (pedido == null){
            vista.mostrarError("Pedido no encontrado");
        } else {
            vista.mostrarResultado(pedido);
        }
    }

    // Ciclo principal del sistema. La vista hace el menú y lee la opción,
    // el controlador interpreta esa opción y llama a la operación correcta.
    public void iniciar() {
        String opcion;

        do{
            vista.mostrarMenu();
            opcion = vista.leerOpcion();

            switch (opcion) {
                case "1" : registrarPedido();
                break;
                case "2" : consultarPedido(vista.capturarId());
                break;
                case "3" : listarPedidos();
                break;
                case "4" : vista.mostrarMensaje("Saliendo...");
                break;
                default: vista.mostrarError("Opción no válida");
            }
        } while (!opcion.equals("4"));
    }

    //Pide la lista al Modelo y se la entrega a la Vista para que la muestre
    public void listarPedidos(){
        vista.mostrarListaPedidos(modelo.listarPedidos());
    }
}