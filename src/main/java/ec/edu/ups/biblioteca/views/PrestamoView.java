/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package ec.edu.ups.biblioteca.views;

import ec.edu.ups.biblioteca.controllers.EjemplarLibroController;
import ec.edu.ups.biblioteca.controllers.PrestamoController;
import ec.edu.ups.biblioteca.controllers.UsuarioController;
import ec.edu.ups.biblioteca.models.EjemplarLibro;
import ec.edu.ups.biblioteca.models.Libro;
import ec.edu.ups.biblioteca.models.Prestamo;
import ec.edu.ups.biblioteca.models.Usuario;
import ec.edu.ups.biblioteca.utils.Idioma;
import ec.edu.ups.biblioteca.utils.Idiomatizable;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author jimen
 */
public class PrestamoView extends javax.swing.JInternalFrame implements Idiomatizable{
    private PrestamoController prestamoController = new PrestamoController();
    private EjemplarLibroController ejemplarLibroController = new EjemplarLibroController();
    private UsuarioController usuarioController = new UsuarioController();
    private boolean registrandoPrestamo = false;
    private Usuario usuarioSeleccionado;
    private EjemplarLibro ejemplarSeleccionado;


    /**
     * Creates new form RealizarPrestamoView
     */
    public PrestamoView() {
        initComponents();
        aplicarIdioma();
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Activo", "Devuelto"}));
        inicializarVista();
    }
    private void inicializarVista() {
        bloquearCampos();
        txtCodigo.setEditable(true);
        btnActualizar.setEnabled(false);
        btnEliminar.setEnabled(false);

        cargarUsuarios();
        cargarLibros();

        cbxUsuarios.addActionListener(e -> {
            usuarioSeleccionado = (Usuario) cbxUsuarios.getSelectedItem();
        });

        cbxLibros.addActionListener(e -> {
            Libro libroSeleccionado = (Libro) cbxLibros.getSelectedItem();
            if (libroSeleccionado != null) {
                cargarEjemplaresDelLibro(libroSeleccionado.getCodigo());
            }
        });

        cbxEjemplares.addActionListener(e -> {
            ejemplarSeleccionado = (EjemplarLibro) cbxEjemplares.getSelectedItem();
        });

        listarPrestamos();
    }

    private void cargarUsuarios() {
        cbxUsuarios.setModel(new javax.swing.DefaultComboBoxModel<>(
                usuarioController.listar().toArray(new Usuario[0])));
    }
    
    private void cargarLibros() {
        cbxLibros.setModel(new javax.swing.DefaultComboBoxModel<>(
                prestamoController.listarLibros().toArray(new Libro[0])));
    }

    private void bloquearCampos() {
        
        cbxUsuarios.setEnabled(false);
        cbxLibros.setEnabled(false);
        cbxEjemplares.setEnabled(false);
        txtFechaPrestamo.setEditable(false);
        txtFechaDevolucion.setEditable(false);
    }

    private void modoActualizar() {
        habilitarCampos();
        btnActualizar.setEnabled(true);
        btnEliminar.setEnabled(true);
        btnRegistrarPrestamo.setEnabled(false);
    }

    private void habilitarCampos() {
        cbxUsuarios.setEnabled(true);
        cbxLibros.setEnabled(true);
        cbxEjemplares.setEnabled(true);
        txtFechaPrestamo.setEditable(true);
        txtFechaDevolucion.setEditable(true);
    }

    private void limpiarCampos() {
        txtCodigo.setText("");
        cbxUsuarios.setSelectedItem(null);
        cbxLibros.setSelectedItem(null);
        cbxEjemplares.setSelectedItem(null);
        txtFechaPrestamo.setText("");
        txtFechaDevolucion.setText("");
        ejemplarSeleccionado = null;
        usuarioSeleccionado = null;
    }

    private void listarPrestamos() {
        javax.swing.table.DefaultTableModel modelo = (javax.swing.table.DefaultTableModel) jTable1.getModel();
        modelo.setRowCount(0);

        List<Prestamo> prestamos = prestamoController.listar();

        for (Prestamo p : prestamos) {
            modelo.addRow(new Object[]{
                p.getCodigo(),
                p.getUsuario().getNombre(),
                p.getEjemplar().getLibro().getTitulo(),
                p.getEjemplar().getCodigoBarras(),
                p.getFechaPrestamo(),
                p.getFechaDevolucion(),

            });
        }
    }
    
    private void cargarEjemplaresDelLibro(String codigoLibro) {
        List<EjemplarLibro> disponibles = ejemplarLibroController.listarDisponiblesPorLibro(codigoLibro);
        cbxEjemplares.setModel(new javax.swing.DefaultComboBoxModel<>(
                disponibles.toArray(EjemplarLibro[]::new)));
    } // ESTOY PERDIENDO LA CABEZA
    
    @Override
    public void aplicarIdioma() {
        java.util.ResourceBundle bundle = Idioma.getBundle();
        jLabel1.setText(bundle.getString("prestamo.lbl.codigo"));
        jLabel2.setText(bundle.getString("prestamo.lbl.usuario"));
        jLabel3.setText(bundle.getString("prestamo.lbl.libro"));
        jLabel4.setText(bundle.getString("prestamo.lbl.fechaPrestamo"));
        jLabel5.setText(bundle.getString("prestamo.lbl.fechaDevolucion"));
        jLabel6.setText(bundle.getString("prestamo.lbl.estado"));
        jLabel7.setText(bundle.getString("prestamo.lbl.accion"));
        jLabel8.setText(bundle.getString("prestamo.lbl.camposInfo"));

        btnRegistrarPrestamo.setText(bundle.getString("prestamo.btn.registrar"));
        btnBuscar.setText(bundle.getString("prestamo.btn.buscar"));
        btnActualizar.setText(bundle.getString("prestamo.btn.actualizar"));
        btnEliminar.setText(bundle.getString("prestamo.btn.eliminar"));
        btnListar.setText(bundle.getString("prestamo.btn.listar"));
        btnLimpiar.setText(bundle.getString("prestamo.btn.limpiar"));
        //jdksjdklsj
        //Ojala funcione
    
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        txtFechaPrestamo = new javax.swing.JTextField();
        txtFechaDevolucion = new javax.swing.JTextField();
        btnRegistrarPrestamo = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnListar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        btnLimpiar = new javax.swing.JButton();
        cbxUsuarios = new javax.swing.JComboBox<>();
        cbxLibros = new javax.swing.JComboBox<>();
        cbxEjemplares = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(114, 114, 82));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Prestamo-Vista");

        jPanel1.setBackground(new java.awt.Color(114, 114, 82));

        jLabel1.setText("Codigo del Prestamo:");

        jLabel2.setText("Usuario:");

        jLabel3.setText("Libro:");

        jLabel4.setText("Fecha de Préstamo:");

        jLabel5.setText("Fecha Devolución:");

        jLabel6.setText("Estado:");

        txtCodigo.addActionListener(this::txtCodigoActionPerformed);

        txtFechaPrestamo.setText("DD/MM/AA");

        txtFechaDevolucion.setText("DD/MM/AA");

        btnRegistrarPrestamo.setText("Registrar Prestamo");
        btnRegistrarPrestamo.addActionListener(this::btnRegistrarPrestamoActionPerformed);

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(this::btnBuscarActionPerformed);

        btnActualizar.setText("Actualizar");

        btnEliminar.setText("Eliminar");

        btnListar.setText("Listar");
        btnListar.addActionListener(this::btnListarActionPerformed);

        jLabel7.setText("Elija la opción que desea utilizar:");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Codigo Prestamo", "Usuario", "Libro", "Fecha Prestamo", "Fecha Devolucion", "Estado"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel8.setText("Los campos se habilitaran segun la accion");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activo", "Devolución" }));

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(this::btnLimpiarActionPerformed);

        cbxUsuarios.addActionListener(this::cbxUsuariosActionPerformed);

        cbxEjemplares.addActionListener(this::cbxEjemplaresActionPerformed);

        jLabel9.setText("Ejemplares:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnLimpiar)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 713, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnRegistrarPrestamo)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel1)
                                .addComponent(jLabel3)
                                .addComponent(jLabel9)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(117, 117, 117)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel4))
                                        .addGap(18, 18, 18))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(51, 51, 51)
                                        .addComponent(btnBuscar)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnListar)
                                        .addGap(77, 77, 77)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btnActualizar)
                                        .addGap(62, 62, 62)
                                        .addComponent(btnEliminar))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtFechaPrestamo)
                                        .addGap(67, 67, 67))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cbxUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbxLibros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbxEjemplares, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(63, 63, 63)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtFechaDevolucion)
                                    .addComponent(jComboBox1, 0, 149, Short.MAX_VALUE))))))
                .addContainerGap(261, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnActualizar)
                    .addComponent(btnEliminar)
                    .addComponent(btnRegistrarPrestamo)
                    .addComponent(btnBuscar)
                    .addComponent(btnListar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtFechaPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbxUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtFechaDevolucion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxLibros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxEjemplares, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLimpiar)
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(367, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btnListarActionPerformed

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed
        // TODO add your handling code here:
        limpiarCampos();
    }//GEN-LAST:event_txtCodigoActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        String codigo = txtCodigo.getText();
        Prestamo prestamo = prestamoController.buscarPorCodigo(codigo);

        if (prestamo != null) {
            
            cbxUsuarios.setSelectedItem(prestamo.getUsuario());
            cbxLibros.setSelectedItem(prestamo.getEjemplar().getLibro().getTitulo());
            txtFechaPrestamo.setText(String.valueOf(prestamo.getFechaPrestamo()));
            txtFechaDevolucion.setText(String.valueOf(prestamo.getFechaDevolucion()));

            modoActualizar();

            btnEliminar.setEnabled(true);
        } else {
            
            JOptionPane.showMessageDialog(this, "Préstamo no encontrado");
        }
        limpiarCampos();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnRegistrarPrestamoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarPrestamoActionPerformed
        if (!registrandoPrestamo) {
            limpiarCampos();
            habilitarCampos();
            txtCodigo.setEditable(true);
            btnRegistrarPrestamo.setText("Guardar");
            registrandoPrestamo = true;
        } else {
            String codigo = txtCodigo.getText();
            if (codigo.isEmpty() || usuarioSeleccionado == null || ejemplarSeleccionado == null) {
                JOptionPane.showMessageDialog(this, "Debes ingresar código, usuario y ejemplar válidos");
                return;
            }
            try {
                java.text.SimpleDateFormat formato = new java.text.SimpleDateFormat("dd/MM/yy");
                java.util.Date fechaPrestamo = formato.parse(txtFechaPrestamo.getText());
                java.util.Date fechaDevolucion = formato.parse(txtFechaDevolucion.getText());
                boolean estado = jComboBox1.getSelectedItem().equals("Activo");
                Prestamo prestamo = new Prestamo(codigo, usuarioSeleccionado, ejemplarSeleccionado,
                        fechaPrestamo, fechaDevolucion, estado);

                prestamoController.registrarPrestamo(prestamo);
                cargarEjemplaresDelLibro(ejemplarSeleccionado.getLibro().getCodigo());
                listarPrestamos();
                limpiarCampos();
                bloquearCampos();
                txtCodigo.setEditable(true);
                btnRegistrarPrestamo.setText("Registrar Prestamo");
                registrandoPrestamo = false;

                JOptionPane.showMessageDialog(this, "Préstamo registrado con éxito");
            } catch (java.text.ParseException e) {
                JOptionPane.showMessageDialog(this, "Formato de fecha inválido. Usa dd/MM/aa");
            }
        }
    }//GEN-LAST:event_btnRegistrarPrestamoActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiarCampos();// TODO add your handling code here:
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void cbxUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxUsuariosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxUsuariosActionPerformed

    private void cbxEjemplaresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxEjemplaresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxEjemplaresActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnListar;
    private javax.swing.JButton btnRegistrarPrestamo;
    private javax.swing.JComboBox<EjemplarLibro> cbxEjemplares;
    private javax.swing.JComboBox<Libro> cbxLibros;
    private javax.swing.JComboBox<Usuario> cbxUsuarios;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtFechaDevolucion;
    private javax.swing.JTextField txtFechaPrestamo;
    // End of variables declaration//GEN-END:variables
}
