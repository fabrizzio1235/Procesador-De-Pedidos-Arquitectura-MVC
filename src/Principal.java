import controlador.PedidoControlador;
import modelo.PedidoModelo;
import vista.PedidoVista;

public class Principal {
    public static void main(String[] args) {
        PedidoModelo modelo = new PedidoModelo();
        PedidoVista vista = new PedidoVista();
        PedidoControlador controlador = new PedidoControlador(modelo, vista);
        controlador.registrarPedido();
    }
}
