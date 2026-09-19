import controlador.PedidoControlador;
import modelo.PedidoModelo;
import vista.PedidoVista;

/*
    Punto de entrada. Arma el MVC
 */

public class Principal {
    public static void main(String[] args) {
        PedidoModelo modelo = new PedidoModelo();
        PedidoVista vista = new PedidoVista();
        //El Controlador recibe las otras dos piezas, el Controlador las comunica
        PedidoControlador controlador = new PedidoControlador(modelo, vista);
        controlador.registrarPedido();
    }
}
