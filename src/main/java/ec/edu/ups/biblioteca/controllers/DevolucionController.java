    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.biblioteca.controllers;
import ec.edu.ups.biblioteca.dao.LibroDAO;
import ec.edu.ups.biblioteca.dao.PrestamoDAO;
import ec.edu.ups.biblioteca.dao.UsuarioDAO;
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
    private Prestamo prestamoSeleccionado;

    public DevolucionController(PrestamoDAO prestamoDAO, DevolucionView devolucionView) {
        this.prestamoDAO = prestamoDAO;
        this.devolucionView = devolucionView;
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
        prestamoSeleccionado = buscarPorCodigo(codigo);

        if (prestamoSeleccionado == null) {
            JOptionPane.showMessageDialog(devolucionView, "Préstamo no encontrado");
        } else {
            JOptionPane.showMessageDialog(devolucionView, "Préstamo encontrado.");
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
        prestamoSeleccionado = null;
        devolucionView.getTxtCodigoPrestamo().setText("");
    }

    public Prestamo buscarPorCodigo(String codigo) {
        return prestamoDAO.buscarPorCodigo(codigo);
    }

    public void registrarDevolucion(Prestamo prestamo) {
        prestamo.setEstado(false); // false cuando el préstamo ya se acabo (devolucion)
        prestamoDAO.actualizar(prestamo);
    }
}
