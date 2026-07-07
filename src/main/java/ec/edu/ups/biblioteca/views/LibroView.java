package ec.edu.ups.biblioteca.views;


import ec.edu.ups.biblioteca.controllers.AutorController;
import ec.edu.ups.biblioteca.controllers.EjemplarLibroController;
import ec.edu.ups.biblioteca.controllers.LibroController;
import ec.edu.ups.biblioteca.dao.LibroDAO;
import ec.edu.ups.biblioteca.models.Autor;
import ec.edu.ups.biblioteca.models.Libro;
import ec.edu.ups.biblioteca.utils.Idioma;
import ec.edu.ups.biblioteca.utils.Idiomatizable;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class LibroView extends javax.swing.JInternalFrame implements Idiomatizable{
    private LibroController libroController;
    private EjemplarLibroController ejemplarLibroController;
    private AutorController autorController;      // agregar
    private Autor autorSeleccionado;   
    private boolean creandoNuevo = false; 
 
    public LibroView() {
        initComponents();
        aplicarIdioma();
        libroController = new LibroController();
        autorController = new AutorController();
        ejemplarLibroController = new EjemplarLibroController();
        inicializarVista();
    }

    private void inicializarVista() {
        bloquearCampos();
        txtCodigo.setEditable(true);
        cargarAutores();
        listarLibros();
        tblLibros.getSelectionModel().addListSelectionListener(evt -> {
            if (!evt.getValueIsAdjusting() && tblLibros.getSelectedRow() != -1) {
                int fila = tblLibros.getSelectedRow();
                String codigo = tblLibros.getValueAt(fila, 0).toString(); // columna código
                Libro libroSeleccionado = libroController.buscarPorCodigo(codigo);

                int disponibles = ejemplarLibroController.contarDisponibles(libroSeleccionado.getCodigo());
                lblDisponibles.setText("Disponibles: " + disponibles);
            }
        });
    }

    private void bloquearCampos() {
        txtTitulo.setEditable(false);
        cbxAutores.setEnabled(false);
        txtEditorial.setEditable(false);
        txtGenero.setEditable(false);
        txtAnio.setEditable(false);
        txtEjemplares.setEditable(false);
    }

    private void habilitarCampos() {
        txtTitulo.setEditable(true);
        cbxAutores.setEnabled(true);
        txtEditorial.setEditable(true);
        txtGenero.setEditable(true);
        txtAnio.setEditable(true);
        txtEjemplares.setEditable(true);
    }

    private void limpiar() {
        txtCodigo.setText("");
        txtTitulo.setText("");
        cbxAutores.setSelectedItem(null); 
        txtEditorial.setText("");
        txtGenero.setText("");
        txtAnio.setText("");
        txtEjemplares.setText("");
        autorSeleccionado = null; 
    }

    private void listarLibros() {
        DefaultTableModel modelo = (DefaultTableModel) tblLibros.getModel();
        modelo.setRowCount(0);

        for (Libro l : libroController.listar()) {
            int disponibles = ejemplarLibroController.contarDisponibles(l.getCodigo());
            modelo.addRow(new Object[]{
                l.getCodigo(),
                l.getTitulo(),
                l.getAutor(),
                l.getEditorial(),
                l.getGenero(),
                l.getAnio(),
                disponibles
            });
        }
    }
    
    private void cargarAutores() {
        cbxAutores.setModel(new javax.swing.DefaultComboBoxModel<>(
                autorController.listar().toArray(new Autor[0])));
        cbxAutores.addActionListener(e -> {
            autorSeleccionado = (Autor) cbxAutores.getSelectedItem();
        });
    }
    
    @Override
    public void aplicarIdioma() {
        java.util.ResourceBundle bundle = Idioma.getBundle();

        jLabel7.setText(bundle.getString("mensaje.accion"));
        jLabel9.setText(bundle.getString("mensaje.campos"));
        lblCodigo.setText(bundle.getString("lbl.codigo"));
        jLabel3.setText(bundle.getString("lbl.titulo"));
        jLabel3.setText(bundle.getString("lbl.autor"));
        jLabel4.setText(bundle.getString("lbl.editorial"));
        jLabel5.setText(bundle.getString("lbl.genero"));
        jLabel6.setText(bundle.getString("lbl.anio"));
        jLabel8.setText(bundle.getString("lbl.ejemplares"));

        btnNuevo.setText(bundle.getString("btn.crear"));
        btnBuscar.setText(bundle.getString("btn.buscar"));
        btnActualizar.setText(bundle.getString("btn.actualizar"));
        btnEliminar.setText(bundle.getString("btn.eliminar"));
        btnListar.setText(bundle.getString("btn.listar"));
        btnLimpiar.setText(bundle.getString("btn.limpiar"));

        javax.swing.table.DefaultTableModel modelo = (javax.swing.table.DefaultTableModel) tblLibros.getModel();
        modelo.setColumnIdentifiers(new Object[]{
            bundle.getString("col.codigo"),
            bundle.getString("col.titulo"),
            bundle.getString("col.autor"),
            bundle.getString("col.editorial"),
            bundle.getString("col.genero"),
            bundle.getString("col.anio"),
            bundle.getString("col.ejemplares")
        });
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
        lblCodigo = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        txtTitulo = new javax.swing.JTextField();
        txtEditorial = new javax.swing.JTextField();
        txtGenero = new javax.swing.JTextField();
        txtAnio = new javax.swing.JTextField();
        btnNuevo = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnListar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLibros = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        txtEjemplares = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        btnLimpiar = new javax.swing.JButton();
        lblDisponibles = new javax.swing.JLabel();
        txtDisponibles = new javax.swing.JTextField();
        cbxAutores = new javax.swing.JComboBox<>();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jPanel1.setBackground(new java.awt.Color(114, 114, 82));

        lblCodigo.setText("Código:");

        lblTitulo.setText("Título:");

        jLabel3.setText("Autor:");

        jLabel4.setText("Editorial:");

        jLabel5.setText("Género:");

        jLabel6.setText("Año:");

        txtCodigo.setEditable(false);

        txtTitulo.setEditable(false);

        txtEditorial.setEditable(false);

        txtGenero.setEditable(false);
        txtGenero.addActionListener(this::txtGeneroActionPerformed);

        txtAnio.setEditable(false);
        txtAnio.setText("AAAA");
        txtAnio.addActionListener(this::txtAnioActionPerformed);

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(this::btnNuevoActionPerformed);

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(this::btnBuscarActionPerformed);

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(this::btnActualizarActionPerformed);

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(this::btnEliminarActionPerformed);

        btnListar.setText("Listar");
        btnListar.addActionListener(this::btnListarActionPerformed);

        jLabel7.setText("Elija la opción que desea utilizar:");

        tblLibros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Codigo", "Titulo", "Autor", "Editorial", "Genero", "Año", "Ejemplares"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblLibros);

        jLabel8.setText("Ejemplares:");

        txtEjemplares.setEditable(false);
        txtEjemplares.setText("0");

        jLabel9.setText("Los campos se habilitaran segun la accion.");

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(this::btnLimpiarActionPerformed);

        lblDisponibles.setText("Disponibles:");

        txtDisponibles.setText("0");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(222, 222, 222)
                        .addComponent(jLabel9))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(244, 244, 244)
                        .addComponent(jLabel7)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnLimpiar)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(44, 44, 44)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(lblTitulo)
                                        .addComponent(lblCodigo)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel4))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(txtEditorial, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(cbxAutores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(lblDisponibles, javax.swing.GroupLayout.Alignment.TRAILING)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                            .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel6))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(86, 86, 86)
                                            .addComponent(jLabel5)))
                                    .addGap(27, 27, 27)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtAnio, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                                        .addComponent(txtEjemplares, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(btnEliminar)
                                            .addComponent(txtGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(txtDisponibles)))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(227, 227, 227)
                                    .addComponent(btnActualizar)
                                    .addGap(42, 42, 42)
                                    .addComponent(btnListar))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(btnNuevo)
                                    .addGap(33, 33, 33)
                                    .addComponent(btnBuscar))))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(22, 22, 22)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 594, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel7)
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevo)
                    .addComponent(btnBuscar)
                    .addComponent(btnActualizar)
                    .addComponent(btnListar)
                    .addComponent(btnEliminar))
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCodigo)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTitulo)
                    .addComponent(jLabel6)
                    .addComponent(txtAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtEjemplares, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxAutores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtEditorial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblDisponibles)
                        .addComponent(txtDisponibles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(11, 11, 11)
                .addComponent(btnLimpiar)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(203, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtAnioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAnioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAnioActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        String codigo = txtCodigo.getText();
        libroController.eliminar(codigo);
        listarLibros();
        limpiar();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarActionPerformed
        listarLibros();
    }//GEN-LAST:event_btnListarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        Libro l = new Libro();
        l.setCodigo(txtCodigo.getText());
        l.setTitulo(txtTitulo.getText());
        l.setAutor(autorSeleccionado);
        l.setEditorial(txtEditorial.getText());
        l.setGenero(txtGenero.getText());
        l.setAnio(Integer.parseInt(txtAnio.getText()));

        libroController.actualizar(l);
        listarLibros();
        limpiar();
        
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
    String codigo = txtCodigo.getText();
    Libro l = libroController.buscarPorCodigo(codigo);

        if (l != null) {
            txtTitulo.setText(l.getTitulo());
            cbxAutores.setSelectedItem(l.getAutor());  
            txtEditorial.setText(l.getEditorial());
            txtGenero.setText(l.getGenero());
            txtAnio.setText(String.valueOf(l.getAnio()));
            int disponibles = ejemplarLibroController.contarDisponibles(codigo);
            txtDisponibles.setText(String.valueOf(disponibles));  
            habilitarCampos();
        } else {
            JOptionPane.showMessageDialog(this, "No encontrado");
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtGeneroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGeneroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGeneroActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        if (!creandoNuevo) {
            limpiar();
            habilitarCampos();
            txtCodigo.setEditable(true);
            btnNuevo.setText("Guardar");
            creandoNuevo = true;
        } else {
            Libro l = new Libro();
            l.setCodigo(txtCodigo.getText());
            l.setTitulo(txtTitulo.getText());
            l.setAutor(autorSeleccionado);
            l.setEditorial(txtEditorial.getText());
            l.setGenero(txtGenero.getText());
            l.setAnio(Integer.parseInt(txtAnio.getText()));
            
            int cantidadEjemplares = Integer.parseInt(txtEjemplares.getText());
            libroController.registrarLibro(l, cantidadEjemplares);
            listarLibros();
            limpiar();
            bloquearCampos();
            btnNuevo.setText("Nuevo");
            creandoNuevo = false;
        }
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnListar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JComboBox<Autor> cbxAutores;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblDisponibles;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTable tblLibros;
    private javax.swing.JTextField txtAnio;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDisponibles;
    private javax.swing.JTextField txtEditorial;
    private javax.swing.JTextField txtEjemplares;
    private javax.swing.JTextField txtGenero;
    private javax.swing.JTextField txtTitulo;
    // End of variables declaration//GEN-END:variables

   
}
