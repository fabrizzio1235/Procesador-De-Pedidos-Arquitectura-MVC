import controlador.PedidoControlador;
import modelo.PedidoModelo;
import vista.PedidoVista;
import vista.PedidoVistaResumida;

/*
    Punto de entrada. Arma el MVC

    Segunda parte: se crea un solo Modelo y se usa con dos vistas distintas.
 */

public class Principal {
    public static void main(String[] args) {
        PedidoModelo modelo = new PedidoModelo();

        // Con vista normal
        PedidoVista vista = new PedidoVista();
        PedidoControlador controlador = new PedidoControlador(modelo, vista);
        controlador.registrarPedido();

        // Con vista reusmida, mismo modelo
        PedidoVistaResumida vistaResumida = new PedidoVistaResumida();
        PedidoControlador controladorResumido = new PedidoControlador(modelo, vistaResumida);
        controladorResumido.registrarPedido();
    }
}
