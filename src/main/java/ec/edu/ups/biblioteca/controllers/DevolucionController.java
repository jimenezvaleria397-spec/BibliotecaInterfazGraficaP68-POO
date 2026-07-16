    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.biblioteca.controllers;
import ec.edu.ups.biblioteca.dao.LibroDAO;
import ec.edu.ups.biblioteca.dao.PrestamoDAO;
import ec.edu.ups.biblioteca.dao.UsuarioDAO;
import ec.edu.ups.biblioteca.enumeraciones.EstadoPrestamo;
import ec.edu.ups.biblioteca.models.EjemplarLibro;
import ec.edu.ups.biblioteca.models.Prestamo;
import ec.edu.ups.biblioteca.views.DevolucionView;
import javax.swing.JOptionPane;
/**
 *
 * @author jimen
 */

public class DevolucionController {

    private PrestamoDAO prestamoDAO;
    private DevolucionView devolucionView;
    private EjemplarLibroController ejemplarLibroController;
    private Prestamo prestamoSeleccionado;

    public DevolucionController(PrestamoDAO prestamoDAO, DevolucionView devolucionView,
            EjemplarLibroController ejemplarLibroController) {
        this.prestamoDAO = prestamoDAO;
        this.devolucionView = devolucionView;
        this.ejemplarLibroController = ejemplarLibroController;
        configurarEventos();
    }

    private void configurarEventos() {
        configurarEventosBuscarPrestamo();
        configurarEventosRegistrarDevolucion();
    }

    private void configurarEventosBuscarPrestamo() {
        devolucionView.getBtnPrestamo().addActionListener(e -> buscarPrestamo());
    }

    private void buscarPrestamo() {
        String codigo = devolucionView.getTxtCodigoPrestamo().getText();
        Prestamo prestamo = buscarPorCodigo(codigo);

        if (prestamo == null) {
            JOptionPane.showMessageDialog(devolucionView, "Préstamo no encontrado");
            devolucionView.limpiarTabla();
            prestamoSeleccionado = null;
            return;
        }

        // mostramos el préstamo encontrado en la tabla (esto es lo que faltaba)
        devolucionView.mostrarPrestamoEnTabla(prestamo);

        if (prestamo.getEstado() != EstadoPrestamo.ACTIVO) {
            JOptionPane.showMessageDialog(devolucionView, "Este préstamo ya fue devuelto anteriormente.");
            prestamoSeleccionado = null;
        } else {
            prestamoSeleccionado = prestamo;
        }
    }

    private void configurarEventosRegistrarDevolucion() {
        devolucionView.getBtnDevolucion().addActionListener(e -> registrarDevolucionDesdeVista());
    }

    private void registrarDevolucionDesdeVista() {
        if (prestamoSeleccionado == null) {
            JOptionPane.showMessageDialog(devolucionView, "Primero busca un préstamo válido.");
            return;
        }
        registrarDevolucion(prestamoSeleccionado);
        JOptionPane.showMessageDialog(devolucionView, "Devolución registrada correctamente");
        devolucionView.limpiarTabla();
        devolucionView.getTxtCodigoPrestamo().setText("");
        prestamoSeleccionado = null;
    }

    public Prestamo buscarPorCodigo(String codigo) {
        return prestamoDAO.buscarPorCodigo(codigo);
    }

    public void registrarDevolucion(Prestamo prestamo) {
        prestamo.setEstado(EstadoPrestamo.DEVUELTO); // enum (devolución)
        prestamoDAO.actualizar(prestamo);

        // esto es lo que faltaba: liberar el ejemplar para que vuelva a estar disponible
        EjemplarLibro ejemplar = prestamo.getEjemplar();
        ejemplar.setDisponible(true);
        ejemplarLibroController.actualizar(ejemplar);
    }
}
