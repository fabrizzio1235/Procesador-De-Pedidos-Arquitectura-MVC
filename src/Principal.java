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
        System.out.println("Vista Normal");
        PedidoVista vista = new PedidoVista();
        PedidoControlador controlador = new PedidoControlador(modelo, vista);
        controlador.iniciar();

        // Con vista resumida, mismo modelo
        System.out.println();
        System.out.println("Abriendo con Vista Resumida");
        System.out.println("Vista Resumida");
        PedidoVistaResumida vistaResumida = new PedidoVistaResumida();
        PedidoControlador controladorResumido = new PedidoControlador(modelo, vistaResumida);
        controladorResumido.iniciar();

    }
}
