    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.biblioteca.controllers;
import ec.edu.ups.biblioteca.dao.PrestamoDAO;
import ec.edu.ups.biblioteca.enumeraciones.EstadoEjemplarLibro;
import ec.edu.ups.biblioteca.enumeraciones.EstadoPrestamo;
import ec.edu.ups.biblioteca.excepciones.ValidacionException;
import ec.edu.ups.biblioteca.excepciones.Validador;
import ec.edu.ups.biblioteca.models.EjemplarLibro;
import ec.edu.ups.biblioteca.models.Libro;
import ec.edu.ups.biblioteca.models.Prestamo;
import ec.edu.ups.biblioteca.models.Usuario;
import ec.edu.ups.biblioteca.views.PrestamoView;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
/**
 *
 * @author jimen
 */

public class PrestamoController {
    private PrestamoDAO prestamoDAO;
    private UsuarioController usuarioController;
    private LibroController libroController;
    private EjemplarLibroController ejemplarLibroController;
    private PrestamoView prestamoView;
    
    private boolean registrandoPrestamo = false;
    private Usuario usuarioSeleccionado;
    private EjemplarLibro ejemplarSeleccionado;

    public PrestamoController(PrestamoDAO prestamoDAO, UsuarioController usuarioController, 
            LibroController libroController, EjemplarLibroController ejemplarLibroController,
            PrestamoView prestamoView) {
        this.prestamoDAO = prestamoDAO;
        this.usuarioController = usuarioController;
        this.libroController = libroController;
        this.ejemplarLibroController = ejemplarLibroController;
        this.prestamoView = prestamoView;
        
        configurarEventos();
        cargarUsuariosCombo();
        cargarLibrosCombo();
        listarPrestamos();
    }

    public void configurarEventos(){
        configurarEventosCambioUsuario();
        configurarEventosCambioLibro();
        configurarEventosCambioEjemplar();
        configurarEventosBuscarPrestamo();
        configurarEventosRegistrarPrestamo();
        configurarEventosActualizarPrestamo();
        configurarEventosEliminarPrestamo();
        configurarEventosListarPrestamo();
        configurarEventosLimpiar();
        configurarEventosComboUsuariosYLibros();
        
    }
    
    private void configurarEventosCambioUsuario() {
        prestamoView.getCbxUsuarios().addActionListener(e -> {
            usuarioSeleccionado = (Usuario) prestamoView.getCbxUsuarios().getSelectedItem();
        });
    }
    private void configurarEventosComboUsuariosYLibros() {
        prestamoView.getCbxUsuarios().addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent e) {
                cargarUsuariosCombo();
            }

            @Override
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent e) {
            }

            @Override
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent e) {
            }
        });

        prestamoView.getCbxLibros().addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent e) {
                cargarLibrosCombo();
            }

            @Override
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent e) {
            }

            @Override
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent e) {
            }
        });

        prestamoView.getCbxEjemplares().addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent e) {
                Libro libroSeleccionado = (Libro) prestamoView.getCbxLibros().getSelectedItem();
                if (libroSeleccionado != null) {
                    List<EjemplarLibro> disponibles = ejemplarLibroController.listarDisponiblesPorLibro(libroSeleccionado.getCodigo());
                    prestamoView.cargarEjemplares(disponibles);
                }
            }

            @Override
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent e) {
            }

            @Override
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent e) {
            }
        });
}
    
    private void configurarEventosCambioLibro() {
        prestamoView.getCbxLibros().addActionListener(e -> {
            Libro libroSeleccionado = (Libro) prestamoView.getCbxLibros().getSelectedItem();
            if (libroSeleccionado != null) {
                List<EjemplarLibro> disponibles = ejemplarLibroController.listarDisponiblesPorLibro(libroSeleccionado.getCodigo());
                prestamoView.cargarEjemplares(disponibles);
            }
        });
    }
    
    private void configurarEventosCambioEjemplar() {
        prestamoView.getCbxEjemplares().addActionListener(e -> {
            ejemplarSeleccionado = (EjemplarLibro) prestamoView.getCbxEjemplares().getSelectedItem();
        });
    }
    
    private void cargarUsuariosCombo() {
        prestamoView.cargarUsuarios(usuarioController.listar());
    }
    
    private void cargarLibrosCombo() {
        prestamoView.cargarLibros(libroController.listar());
    }
    
    private void configurarEventosRegistrarPrestamo() {
        prestamoView.getBtnRegistrarPrestamo().addActionListener(e -> registrarPrestamoDesdeVista());
    }
    
    private void registrarPrestamoDesdeVista() {
        if (!registrandoPrestamo) {
            prestamoView.limpiarCampos();
            prestamoView.habilitarCampos();
            prestamoView.getTxtCodigo().setEditable(true);
            prestamoView.getBtnRegistrarPrestamo().setText("Guardar");
            registrandoPrestamo = true;
        } else {
            String codigo = prestamoView.getTxtCodigo().getText();

            if (usuarioSeleccionado == null || ejemplarSeleccionado == null) {
                JOptionPane.showMessageDialog(prestamoView, "Debes seleccionar un usuario y un ejemplar.");
                return;
            }

            Date fechaPrestamo = prestamoView.getDateChooserFechaPrestamo().getDate();
            Date fechaDevolucion = prestamoView.getDateChooserFechaDevolucion().getDate();

            if (fechaPrestamo == null || fechaDevolucion == null) {
                JOptionPane.showMessageDialog(prestamoView, "Debes seleccionar ambas fechas.");
                return;
            }

            try {
                Validador.validarNoVacio(codigo, "Código");
                Validador.validarSoloNumeros(codigo, "Código");
                EstadoPrestamo estado = (EstadoPrestamo) prestamoView.getCbxEstado().getSelectedItem();

                Prestamo prestamo = new Prestamo(codigo, usuarioSeleccionado, ejemplarSeleccionado,
                        fechaPrestamo, fechaDevolucion, estado);

                registrarPrestamo(prestamo);

                List<EjemplarLibro> disponibles = ejemplarLibroController.listarDisponiblesPorLibro(ejemplarSeleccionado.getLibro().getCodigo());
                prestamoView.cargarEjemplares(disponibles);

                listarPrestamos();
                prestamoView.limpiarCampos();
                prestamoView.bloquearCampos();
                prestamoView.getTxtCodigo().setEditable(true);
                prestamoView.getBtnRegistrarPrestamo().setText("Registrar Prestamo");
                registrandoPrestamo = false;

        JOptionPane.showMessageDialog(prestamoView, "Préstamo registrado con éxito");
    } catch (ValidacionException e) {
        JOptionPane.showMessageDialog(prestamoView, e.getMessage());
    }

            
    }
}
    
    private void configurarEventosActualizarPrestamo() {
        prestamoView.getBtnActualizar().addActionListener(e -> actualizarPrestamo());
    }
    
    private void actualizarPrestamo() {
        String codigo = prestamoView.getTxtCodigo().getText();
        Prestamo existente = buscarPorCodigo(codigo);

        if (existente == null) {
            JOptionPane.showMessageDialog(prestamoView, "No existe un préstamo con ese código.");
            return;
        }

        Date fechaPrestamo = prestamoView.getDateChooserFechaPrestamo().getDate();
        Date fechaDevolucion = prestamoView.getDateChooserFechaDevolucion().getDate();

        if (fechaPrestamo == null || fechaDevolucion == null) {
            JOptionPane.showMessageDialog(prestamoView, "Debes seleccionar ambas fechas.");
            return;
        }

        existente.setFechaPrestamo(fechaPrestamo);
        existente.setFechaDevolucion(fechaDevolucion);
        existente.setEstado((EstadoPrestamo) prestamoView.getCbxEstado().getSelectedItem());

        actualizar(existente);
        listarPrestamos();
        prestamoView.limpiarCampos();
        prestamoView.bloquearCampos();
        prestamoView.getTxtCodigo().setEditable(true);
    }
    
    private void configurarEventosBuscarPrestamo() {
        prestamoView.getBtnBuscar().addActionListener(e -> buscarPrestamo());
    }
    
    private void buscarPrestamo() {
        String codigo = prestamoView.getTxtCodigo().getText();
        Prestamo prestamo = buscarPorCodigo(codigo);

        if (prestamo != null) {
            prestamoView.mostrarPrestamoEnCampos(prestamo);
            prestamoView.modoActualizar();
        } else {
            JOptionPane.showMessageDialog(prestamoView, "Préstamo no encontrado");
        }
    }
    
    private void configurarEventosEliminarPrestamo() {
        prestamoView.getBtnEliminar().addActionListener(e -> eliminarPrestamo());
    }
    
    private void eliminarPrestamo() {
        String codigo = prestamoView.getTxtCodigo().getText();
        Prestamo existente = buscarPorCodigo(codigo);

        if (existente == null) {
            JOptionPane.showMessageDialog(prestamoView, "No existe un préstamo con ese código.");
            return;
        }

        EjemplarLibro ejemplar = existente.getEjemplar();
        ejemplar.setEstadoEjemplar(EstadoEjemplarLibro.DISPONIBLE);
        ejemplarLibroController.actualizar(ejemplar);

        eliminar(codigo);
        listarPrestamos();
        prestamoView.limpiarCampos();
        prestamoView.bloquearCampos();
        prestamoView.getTxtCodigo().setEditable(true);
        
    }
    
    private void configurarEventosListarPrestamo() {
        prestamoView.getBtnListar().addActionListener(e -> listarPrestamos());
    }

    private void listarPrestamos() {
        prestamoView.listarPrestamos(listar());
    }
    
    private void configurarEventosLimpiar() {
        prestamoView.getBtnLimpiar().addActionListener(e -> prestamoView.limpiarCampos());
    }

    public void agregar(Prestamo prestamo) {
        prestamoDAO.agregar(prestamo);
    }

    public Prestamo buscarPorCodigo(String codigo) {
        if (ec.edu.ups.biblioteca.views.MenuBibliotecaView.USAR_ARCHIVOS) {
            return new ec.edu.ups.biblioteca.dao.archivos.PrestamoDAOArchivo().buscarPorCodigo(codigo);
        } else {
            return prestamoDAO.buscarPorCodigo(codigo);
        }
    }

    public void actualizar(Prestamo prestamo) {
        if (ec.edu.ups.biblioteca.views.MenuBibliotecaView.USAR_ARCHIVOS) {
            new ec.edu.ups.biblioteca.dao.archivos.PrestamoDAOArchivo().actualizar(prestamo.getCodigo());
        } else {
            prestamoDAO.actualizar(prestamo);
        }
    }

    public void eliminar(String codigo) {
        if (ec.edu.ups.biblioteca.views.MenuBibliotecaView.USAR_ARCHIVOS) {
            new ec.edu.ups.biblioteca.dao.archivos.PrestamoDAOArchivo().eliminar(codigo);
        } else {
            prestamoDAO.eliminar(codigo);
        }
    }

    public List<Prestamo> listar() {
        return prestamoDAO.listar();
    }
    
    public void registrarPrestamo(Prestamo prestamo) {
        EjemplarLibro ejemplar = prestamo.getEjemplar();
        ejemplar.setEstadoEjemplar(EstadoEjemplarLibro.PRESTADO); // se marca como prestado
        ejemplarLibroController.actualizar(ejemplar);
        agregar(prestamo);
    }
}
